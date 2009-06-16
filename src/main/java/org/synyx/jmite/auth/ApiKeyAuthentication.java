package org.synyx.jmite.auth;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.CommonsClientHttpRequestFactory;


/**
 * Authentication based on an API key.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class ApiKeyAuthentication implements Authentication {

    private static final String API_KEY_HEADER = "X-MiteApiKey";

    private String apiKey;


    /**
     * Creates a new {@link ApiKeyAuthentication} with the given key.
     * 
     * @param apiKey
     */
    public ApiKeyAuthentication(String apiKey) {

        this.apiKey = apiKey;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.synyx.samples.spring3.mite.Authentication#getFactory(org.apache.commons
     * .httpclient.HttpClient)
     */
    public ClientHttpRequestFactory getFactory(HttpClient client) {

        return new CommonsClientHttpRequestFactory(client) {

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.springframework.http.client.CommonsClientHttpRequestFactory
             * #postProcessCommonsHttpMethod
             * (org.apache.commons.httpclient.HttpMethodBase)
             */
            @Override
            protected void postProcessCommonsHttpMethod(
                    HttpMethodBase httpMethod) {

                httpMethod.addRequestHeader(API_KEY_HEADER, apiKey);
            }
        };
    }
}
