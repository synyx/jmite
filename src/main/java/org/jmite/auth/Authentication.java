package org.jmite.auth;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.http.client.ClientHttpRequestFactory;


/**
 * Interface to abstract authentication mechanisms against Mite. Implementations
 * will modify the given {@link HttpClient} instance and return an appropriate
 * {@link ClientHttpRequestFactory} that returns prepared
 * {@link org.springframework.http.client.ClientHttpRequest} instances that
 * carry proper authentication.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public interface Authentication {

    /**
     * Returns the {@link ClientHttpRequestFactory} to create properly
     * authenticated {@link org.springframework.http.client.ClientHttpRequest}
     * instances.
     * 
     * @param client
     * @return
     */
    ClientHttpRequestFactory getFactory(HttpClient client);
}
