package org.jmite;

import org.apache.commons.httpclient.HttpClient;
import org.jmite.auth.ApiKeyAuthentication;
import org.jmite.auth.Authentication;
import org.jmite.auth.UsernamePasswordAuthentication;
import org.jmite.domain.Customers;
import org.jmite.domain.Project;
import org.jmite.domain.Projects;
import org.jmite.domain.Service;
import org.jmite.domain.Services;
import org.jmite.domain.TimeEntries;
import org.jmite.domain.TimeEntry;
import org.jmite.domain.Tracker;
import org.jmite.domain.Users;
import org.jmite.internal.CachingHttpRequestFactory;
import org.jmite.internal.MiteRestTemplate;
import org.jmite.internal.UrlBuilder;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.util.Assert;


/**
 * Implementation to access Mite. To setup a client you can use the public
 * constructor {@link #MiteClient(String, Authentication, boolean)} providing
 * the location of the Mite server as well as an {@link Authentication}
 * mechanism. Besides that there is a {@link Builder} available that allows
 * setting up the client via a fluent interface.
 * 
 * @author Oliver Gierke
 */
public class MiteClient implements Mite {

    private static final String DEFAULT_USER_AGENT = "jmite/0.1";

    private MiteRestTemplate template;

    private Customers customers;
    private Services services;
    private TimeEntries timeEntries;
    private Users users;
    private Projects projects;


    /**
     * Creates a new {@link MiteClient} to access a Mite server at the given
     * location as. Uses the given {@link Authentication} to access a certain
     * user account. The client will use ETag based caching. To disable caching
     * use {@link #MiteClient(String, Authentication, boolean)}.
     * 
     * @see #MiteClient(String, Authentication, boolean)
     * @param location th location of the Mite server
     * @param authentication the {@link Authentication} mechanism to use
     */
    public MiteClient(String location, Authentication authentication) {

        this(location, authentication, true, DEFAULT_USER_AGENT);
    }


    /**
     * Creates a new {@link MiteClient} to access a Mite server at the given
     * location as. Uses the given {@link Authentication} to access a certain
     * user account.
     * 
     * @param location the location of the Mite server
     * @param authentication the {@link Authentication} mechanism to use
     * @param doCaching whether to use ETag based caching or not
     */
    public MiteClient(String location, Authentication authentication,
            boolean doCaching, String userAgent) {

        this.template =
                new MiteRestTemplate(new UrlBuilder(location), userAgent);

        ClientHttpRequestFactory factory =
                authentication.getFactory(new HttpClient());

        if (doCaching) {
            factory = new CachingHttpRequestFactory(factory);
        }

        this.template.setRequestFactory(factory);

        this.customers = new Customers(template);
        this.projects = new Projects(template);
        this.services = new Services(template);
        this.timeEntries = new TimeEntries(template);
        this.users = new Users(template);
    }


    /**
     * Pings the Mite server to check whether the connection can be established.
     */
    private void ping() {

        template.headForHeaders(Users.MYSELF_URL);
    }

    /**
     * Builder to create and configure a {@link Mite} instance.
     * 
     * @author Oliver Gierke
     */
    public static class Builder {

        private Authentication authentication;
        private String baseUrl;
        private boolean doCaching = true;
        private String userAgent = DEFAULT_USER_AGENT;


        public Builder(String baseUrl) {

            Assert.notNull(baseUrl);
            this.baseUrl = baseUrl;
        }


        /**
         * Configures the builder to use given username and password for
         * authentication.
         * 
         * @param username
         * @param password
         * @return
         */
        public Builder withUsernameAndPassword(String username, String password) {

            this.authentication =
                    new UsernamePasswordAuthentication(username, password);

            return this;
        }


        /**
         * Configures the builder to use the given API key for authentication.
         * 
         * @param apiKey
         * @return
         */
        public Builder withApiKey(String apiKey) {

            this.authentication = new ApiKeyAuthentication(apiKey);
            return this;
        }


        public Builder withCaching() {

            this.doCaching = true;
            return this;
        }


        public Builder withoutCaching() {

            this.doCaching = false;
            return this;
        }


        public Builder withUserAgent(String userAgent) {

            this.userAgent = userAgent;
            return this;
        }


        /**
         * Builds the mite client.
         * 
         * @return
         */
        public Mite build() {

            if (null == authentication) {
                throw new IllegalStateException("Authentication required!");
            }

            MiteClient client =
                    new MiteClient(baseUrl, authentication, doCaching,
                            userAgent);
            client.ping();

            return client;
        }
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.Mite#customers()
     */
    public Customers customers() {

        return this.customers;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.Mite#projects()
     */
    public Projects projects() {

        return this.projects;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.Mite#services()
     */
    public Services services() {

        return this.services;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.Mite#timeEntries()
     */
    public TimeEntries timeEntries() {

        return this.timeEntries;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.Mite#users()
     */
    public Users users() {

        return this.users;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.Mite#tracker(org.synyx.jmite.domain.TimeEntry)
     */
    public Tracker tracker(TimeEntry entry) {

        return new Tracker(template, entry);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.Mite#tracker()
     */
    public Tracker tracker() {

        return new Tracker(template, timeEntries().save(new TimeEntry()));
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.Mite#tracker(java.lang.String,
     * org.synyx.jmite.domain.Project, org.synyx.jmite.domain.Service)
     */
    public Tracker tracker(String note, Project project, Service service) {

        Tracker tracker = tracker();
        tracker.toTimeEntry(note, project, service);

        return tracker;
    }
}
