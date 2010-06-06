package org.synyx.jmite.domain.support;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.web.client.HttpClientErrorException;
import org.synyx.jmite.domain.internal.EntityCollectionWrapper;
import org.synyx.jmite.internal.MiteRestTemplate;


/**
 * Resource handler to access readable resources.
 * 
 * @author Oliver Gierke
 */
public class AbstractReadableResourceHandler<T extends AbstractEntity> {

    private static final String ENTITY_TEMPLATE = "%s/{id}";

    private MiteRestTemplate template;
    private Class<T> entityType;
    private Class<? extends EntityCollectionWrapper<T>> wrapper;
    private String url;


    /**
     * Creates a new {@link AbstractReadableResourceHandler}.
     */
    public AbstractReadableResourceHandler(MiteRestTemplate template,
            String url, Class<T> entityType,
            Class<? extends EntityCollectionWrapper<T>> wrapper) {

        this.template = template;
        this.url = url;
        this.entityType = entityType;
        this.wrapper = wrapper;
    }


    /**
     * Returns the resource with the given id. Returns {@code null} if no entity
     * with the given id was found.
     * 
     * @param id
     * @return
     */
    public T get(int id) {

        try {
            return template.getForObject(getEntityUrl(), entityType, String
                    .valueOf(id));
        } catch (HttpClientErrorException e) {

            if (NOT_FOUND.equals(e.getStatusCode())) {
                return null;
            }

            throw e;
        }
    }


    /**
     * Returns all resources.
     * 
     * @return
     */
    public List<T> all() {

        return template.getForObject(url, wrapper).getEntities();
    }


    /**
     * Returns the template to access the Mite server.
     * 
     * @return
     */
    protected MiteRestTemplate getTemplate() {

        return this.template;
    }


    /**
     * Returns the URL to access a single resource.
     * 
     * @return
     */
    protected String getEntityUrl() {

        return String.format(ENTITY_TEMPLATE, url);
    }


    /**
     * Returns the URL to the resource.
     * 
     * @return
     */
    protected String getUrl() {

        return url;
    }


    List<T> all(QueryParameter... parameters) {

        return template.getForObject(url, wrapper, parameters).getEntities();
    }
}
