package org.synyx.jmite.internal;

import java.util.Map;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.synyx.jmite.domain.support.QueryParameter;


/**
 * Custom {@link RestTemplate} that creates URLs through a {@link UrlBuilder}
 * transaprently. Mite URLs conform to a strong scheme thus we just need to hand
 * the varying parts to the template.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class MiteRestTemplate extends RestTemplate {

    private static final String[] NO_PARAMETERS = new String[] {};

    private UrlBuilder urlBuilder;


    /**
     * Creates a new {@link MiteRestTemplate}.
     * 
     * @param urlBuilder
     */
    public MiteRestTemplate(UrlBuilder urlBuilder) {

        super();
        this.urlBuilder = urlBuilder;
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
            ResponseExtractor<T> responseExtractor,
            Map<String, String> urlVariables) throws RestClientException {

        return super.execute(urlBuilder.build(url), method, requestCallback,
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
            ResponseExtractor<T> responseExtractor, String... urlVariables)
            throws RestClientException {

        return super.execute(urlBuilder.build(url), method, requestCallback,
                responseExtractor, urlVariables);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.client.RestTemplate#getForObject(java.lang
     * .String, java.lang.Class, java.lang.String[])
     */
    public <T> T getForObject(String url, Class<T> responseType,
            QueryParameter... parameters) throws RestClientException {

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


    public String build(String url) {

        return urlBuilder.build(url);
    }
}