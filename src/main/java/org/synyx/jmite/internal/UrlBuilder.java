package org.synyx.jmite.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.synyx.jmite.domain.support.QueryParameter;


/**
 * Builder class to create full URLs to access Mite.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class UrlBuilder {

    private static final String DEFAULT_SUFFIX = ".xml";

    private String baseUrl;
    private String suffix;


    /**
     * Creates a {@link UrlBuilder}
     */
    public UrlBuilder(String baseUrl, String suffix) {

        this.baseUrl = baseUrl;
        this.suffix = suffix;
    }


    /**
     * Creates a {@link UrlBuilder} using the default suffix
     * {@value #DEFAULT_SUFFIX}.
     * 
     * @param baseUrl
     */
    public UrlBuilder(String baseUrl) {

        this.baseUrl = baseUrl;
        this.suffix = DEFAULT_SUFFIX;
    }


    /**
     * Builds an URL from the given URL part.
     * 
     * @param url
     * @param parameters
     * @return
     */
    private String build(String url, Collection<QueryParameter> parameters) {

        if (url.startsWith(baseUrl)) {
            return url;
        }

        StringBuilder builder = new StringBuilder(baseUrl);
        builder.append(url);
        builder.append(suffix);

        if (null == parameters) {
            return builder.toString();
        }

        builder.append("?");

        Iterator<QueryParameter> list = parameters.iterator();

        while (list.hasNext()) {

            QueryParameter parameter = list.next();

            if (null == parameter) {
                continue;
            }

            builder.append(parameter.toString());
            builder.append("&");
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }


    /**
     * Builds the complete url for the given snippet and applies the given
     * {@link QueryParameter}s.
     * 
     * @param url
     * @param parameters
     * @return
     */
    public String build(String url, QueryParameter... parameters) {

        return build(url, Arrays.asList(parameters));
    }


    /**
     * Builds the complete URL for the given snippet.
     * 
     * @param url
     * @return
     */
    public String build(String url) {

        return build(url, (Collection<QueryParameter>) null);
    }
}
