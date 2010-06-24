package org.jmite.internal;

import java.io.IOException;
import java.util.Map;

import org.jmite.domain.support.QueryParameter;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;


/**
 * Custom {@link RestTemplate} that creates URLs through a {@link UrlBuilder}
 * transaprently. Mite URLs conform to a strong scheme thus we just need to hand
 * the varying parts to the template.
 * 
 * @author Oliver Gierke
 */
public class MiteRestTemplate extends RestTemplate {

    private static final Object[] NO_PARAMETERS = new String[] {};

    private final UrlBuilder urlBuilder;
    private final String userAgent;


    /**
     * Creates a new {@link MiteRestTemplate}.
     * 
     * @param urlBuilder
     */
    public MiteRestTemplate(UrlBuilder urlBuilder, String userAgent) {

        super();
        this.urlBuilder = urlBuilder;
        this.userAgent = userAgent;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.client.RestTemplate#execute(java.lang.String,
     * org.springframework.http.HttpMethod,
     * org.springframework.web.client.RequestCallback,
     * org.springframework.web.client.ResponseExtractor, java.util.Map)
     */
    @Override
    public <T> T execute(String url, HttpMethod method,
            RequestCallback requestCallback,
            ResponseExtractor<T> responseExtractor, Map<String, ?> urlVariables) {

        return super.execute(urlBuilder.build(url), method,
                new UserAgentAwareRequestCallback(requestCallback),
                responseExtractor, urlVariables);
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.client.RestTemplate#execute(java.lang.String,
     * org.springframework.http.HttpMethod,
     * org.springframework.web.client.RequestCallback,
     * org.springframework.web.client.ResponseExtractor, java.lang.String[])
     */
    @Override
    public <T> T execute(String url, HttpMethod method,
            RequestCallback requestCallback,
            ResponseExtractor<T> responseExtractor, Object... urlVariables) {

        return super.execute(urlBuilder.build(url), method,
                new UserAgentAwareRequestCallback(requestCallback),
                responseExtractor, urlVariables);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.client.RestTemplate#getForObject(java.lang
     * .String, java.lang.Class, java.lang.String[])
     */
    public <T> T getForObject(String url, Class<T> responseType,
            QueryParameter... parameters) {

        return getForObject(urlBuilder.build(url, parameters), responseType);
    }


    /**
     * Convenience method to resolve ambiguity between
     * {@link #getForObject(String, Class, String...)} and
     * {@link #getForObject(String, Class, QueryParameter...)} when using only
     * the first two parameters.
     * 
     * @param <T>
     * @param url
     * @param responseType
     * @return
     */
    public <T> T getForObject(String url, Class<T> responseType) {

        return getForObject(url, responseType, NO_PARAMETERS);
    }


    /**
     * Invokes a DELETE to the given URL and extracts the response body to be
     * unmarshalled to the given repsonse type.
     * 
     * @param <T>
     * @param url
     * @param responseType
     * @param parameters
     * @return
     */
    public <T> T deleteForObject(String url, Class<T> responseType, int id) {

        return execute(urlBuilder.build(url), HttpMethod.DELETE, null,
                new HttpMessageConverterExtractor<T>(responseType,
                        getMessageConverters()), String.valueOf(id));
    }


    public <T> T putForObject(String url, Class<T> responseType, int id) {

        return execute(urlBuilder.build(url), HttpMethod.PUT, null,
                new HttpMessageConverterExtractor<T>(responseType,
                        getMessageConverters()), String.valueOf(id));
    }


    public String build(String url) {

        return urlBuilder.build(url);
    }

    /**
     * Delegating {@link RequestCallback} adding a {@code User-Agent} header to
     * the request being created.
     * 
     * @author Oliver Gierke
     */
    private class UserAgentAwareRequestCallback implements RequestCallback {

        private final RequestCallback delegate;


        /**
         * Creates a new {@link UserAgentAwareRequestCallback}.
         * 
         * @param delegate the actual callback to decorate
         */
        public UserAgentAwareRequestCallback(RequestCallback delegate) {

            this.delegate = delegate;
        }


        /*
         * (non-Javadoc)
         * 
         * @see
         * org.springframework.web.client.RequestCallback#doWithRequest(org.
         * springframework.http.client.ClientHttpRequest)
         */
        @Override
        public void doWithRequest(ClientHttpRequest request) throws IOException {

            request.getHeaders().add("User-Agent", userAgent);
            if (null != delegate) {
                delegate.doWithRequest(request);
            }
        }
    }
}