package org.synyx.jmite;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.Assert;
import org.synyx.jmite.auth.ApiKeyAuthentication;
import org.synyx.jmite.auth.Authentication;
import org.synyx.jmite.auth.UsernamePasswordAuthentication;
import org.synyx.jmite.domain.Customers;
import org.synyx.jmite.domain.Projects;
import org.synyx.jmite.domain.Services;
import org.synyx.jmite.domain.TimeEntries;
import org.synyx.jmite.domain.User;
import org.synyx.jmite.domain.Users;
import org.synyx.jmite.domain.internal.UserWrapper;
import org.synyx.jmite.internal.CachingHttpRequestFactory;
import org.synyx.jmite.internal.MiteRestTemplate;
import org.synyx.jmite.internal.UrlBuilder;


/**
 * Implementation to access Mite. To setup a client you can use the public
 * constructor {@link #MiteClient(String, Authentication, boolean)} providing
 * the location of the Mite server as well as an {@link Authentication}
 * mechanism. Besides that there is a {@link Builder} available that allows
 * setting up the client via a fluent interface.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class MiteClient implements Mite {

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

        this(location, authentication, true);
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
            boolean doCaching) {

        this.template = new MiteRestTemplate(new UrlBuilder(location));

        ClientHttpRequestFactory factory =
                authentication.getFactory(new HttpClient());

        if (doCaching) {
            factory = new CachingHttpRequestFactory(factory);
        }

        this.template.setRequestFactory(factory);
        this.template
                .setMessageConverters(new HttpMessageConverter[] { new MarshallingHttpMessageConverter(
                        createMarshaller()) });

        this.customers = new Customers(template);
        this.projects = new Projects(template);
        this.services = new Services(template);
        this.timeEntries = new TimeEntries(template);
        this.users = new Users(template);
    }


    /**
     * Creates a new marshaller to map Mite data against.
     * 
     * @return
     */
    private Marshaller createMarshaller() {

        try {
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

            StringBuilder contextPath = new StringBuilder();
            contextPath.append(User.class.getPackage().getName());
            contextPath.append(":");
            contextPath.append(UserWrapper.class.getPackage().getName());

            marshaller.setContextPath(contextPath.toString());
            marshaller.afterPropertiesSet();

            return marshaller;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
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
     * @author Oliver Gierke - gierke@synyx.de
     */
    public static class Builder {

        private Authentication authentication;
        private String baseUrl;
        private boolean doCaching = true;


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
                    new MiteClient(baseUrl, authentication, doCaching);
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
