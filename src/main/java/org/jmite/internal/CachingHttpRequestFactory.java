package org.jmite.internal;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpStatus.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;


/**
 * Wrapps a {@link ClientHttpRequestFactory} and applies caching behaviour to
 * all created {@link ClientHttpRequest} instances.
 * 
 * @author Oliver Gierke
 */
public class CachingHttpRequestFactory implements ClientHttpRequestFactory {

    private static final String ETAG_HEADER = "ETag";
    private static final String IF_NONE_MATCH_HEADER = "If-None-Match";

    private ClientHttpRequestFactory delegate;
    private Cache cache = new Cache();


    /**
     * Creates a new {@link CachingHttpRequestFactory}.
     * 
     * @param delegate
     */
    public CachingHttpRequestFactory(ClientHttpRequestFactory delegate) {

        this.delegate = delegate;
    }


    public ClientHttpRequest createRequest(URI uri, HttpMethod method)
            throws IOException {

        ClientHttpRequest request = delegate.createRequest(uri, method);

        if (isPurgingRequest(method)) {

            synchronized (cache) {
                cache.remove(uri);
            }

            return request;
        }

        synchronized (cache) {
            if (isCacheableRequest(method) && cache.hasEntry(uri)) {
                request.getHeaders().add(IF_NONE_MATCH_HEADER,
                        cache.getEtag(uri));
            }
        }

        return isCacheableRequest(method) ? new CachingHttpRequest(uri, request)
                : request;
    }


    private boolean isPurgingRequest(HttpMethod method) {

        return Arrays.asList(POST, PUT, DELETE).contains(method);
    }


    private boolean isCacheableRequest(HttpMethod method) {

        return GET.equals(method);
    }

    /**
     * Buffers the {@link InputStream} contained in the wrapped
     * {@link ClientHttpResponse}. Delegates all other calls to the original
     * instance.
     * 
     * @author Oliver Gierke
     */
    private static class CachingHttpResponse implements ClientHttpResponse {

        private static final int BUFFER_SIZE = 1024;
        private ByteArrayOutputStream output = new ByteArrayOutputStream();
        private ClientHttpResponse delegate;


        /**
         * @throws IOException
         */
        public CachingHttpResponse(ClientHttpResponse response)
                throws IOException {

            byte[] buffer = new byte[BUFFER_SIZE];
            InputStream stream = response.getBody();
            int n = 0;

            while (-1 != (n = stream.read(buffer))) {
                output.write(buffer, 0, n);
            }

            this.delegate = response;
        }


        /*
         * (non-Javadoc)
         * 
         * @see org.springframework.http.client.ClientHttpResponse#close()
         */
        public void close() {

            delegate.close();
        }


        /*
         * (non-Javadoc)
         * 
         * @see
         * org.springframework.http.client.ClientHttpResponse#getStatusCode()
         */
        public HttpStatus getStatusCode() throws IOException {

            return delegate.getStatusCode();
        }


        public String getStatusText() throws IOException {

            return delegate.getStatusText();
        }


        public InputStream getBody() throws IOException {

            return new ByteArrayInputStream(output.toByteArray());
        }


        public HttpHeaders getHeaders() {

            return delegate.getHeaders();
        }
    }

    private class CachingHttpRequest implements ClientHttpRequest {

        private URI uri;
        private ClientHttpRequest request;


        public CachingHttpRequest(URI uri, ClientHttpRequest request) {

            this.uri = uri;
            this.request = request;
        }


        public HttpMethod getMethod() {

            return request.getMethod();
        }


        /*
         * (non-Javadoc)
         * 
         * @see org.springframework.http.client.ClientHttpRequest#execute()
         */
        public ClientHttpResponse execute() throws IOException {

            ClientHttpResponse response = request.execute();

            HttpHeaders responseHeaders = response.getHeaders();

            boolean isNotModified =
                    NOT_MODIFIED.equals(response.getStatusCode());

            synchronized (cache) {
                // Return cached instance on 304
                if (isNotModified && cache.hasEntry(uri)) {
                    return cache.get(uri);
                }
            }

            // Put into cache if ETag returned
            if (isCacheableRequest(request.getMethod())
                    && responseHeaders.containsKey(ETAG_HEADER)) {

                ClientHttpResponse wrapper = new CachingHttpResponse(response);

                synchronized (cache) {
                    cache.put(new CacheEntry(responseHeaders
                            .getFirst(ETAG_HEADER), uri, wrapper));
                }

                return wrapper;
            }

            return response;
        }


        public OutputStream getBody() throws IOException {

            return request.getBody();
        }


        public HttpHeaders getHeaders() {

            return request.getHeaders();
        }


        public URI getURI() {

            return this.uri;
        }
    }

    private static class Cache {

        private Map<URI, CacheEntry> entries =
                new WeakHashMap<URI, CacheEntry>();


        public void put(CacheEntry entry) {

            entries.put(entry.getUri(), entry);
        }


        public ClientHttpResponse get(URI uri) {

            return entries.get(uri).getResource();
        }


        public String getEtag(URI uri) {

            return entries.get(uri).getEtag();
        }


        public boolean hasEntry(URI uri) {

            return entries.containsKey(uri);
        }


        public void remove(URI uri) {

            entries.remove(uri);
        }
    }

    private static class CacheEntry {

        private String etag;
        private URI uri;
        private ClientHttpResponse resource;


        /**
         * @param etag
         * @param uri
         * @param resource
         */
        public CacheEntry(String etag, URI uri, ClientHttpResponse resource) {

            Assert.notNull(etag);
            Assert.notNull(uri);

            this.etag = etag;
            this.uri = uri;
            this.resource = resource;
        }


        public String getEtag() {

            return etag;
        }


        public URI getUri() {

            return uri;
        }


        public ClientHttpResponse getResource() {

            return resource;
        }


        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {

            StringBuffer buffer = new StringBuffer();
            buffer.append("URI: ").append(uri);
            buffer.append(" - ETag: ").append(etag);
            buffer.append(" - Resource: ").append(resource);

            return buffer.toString();
        }


        @Override
        public boolean equals(Object obj) {

            if (obj == this) {
                return true;
            }

            if (!(obj instanceof CacheEntry)) {
                return false;
            }

            CacheEntry that = (CacheEntry) obj;

            boolean uriAndEtag =
                    this.uri.equals(that.uri) && this.etag.equals(that.etag);

            return uriAndEtag
                    && (null == this.resource ? null == that.resource
                            : this.resource.equals(that.resource));
        }


        @Override
        public int hashCode() {

            int result = 31;

            result += 17 * etag.hashCode();
            result += 17 * uri.hashCode();

            result += null == resource ? 0 : resource.hashCode();

            return result;
        }
    }
}
