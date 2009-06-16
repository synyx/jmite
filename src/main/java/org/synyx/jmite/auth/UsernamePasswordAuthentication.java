package org.synyx.jmite.auth;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.CommonsClientHttpRequestFactory;


/**
 * Authentication using username and password.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class UsernamePasswordAuthentication implements Authentication {

    private String username;
    private String password;


    /**
     * Creates a new {@link UsernamePasswordAuthentication} with the given
     * username and password.
     * 
     * @param username
     * @param password
     */
    public UsernamePasswordAuthentication(String username, String password) {

        this.username = username;
        this.password = password;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.synyx.samples.spring3.mite.Authentication#getFactory(org.apache.commons
     * .httpclient.HttpClient)
     */
    public ClientHttpRequestFactory getFactory(HttpClient client) {

        client.getState().setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        return new CommonsClientHttpRequestFactory(client);
    }
}
