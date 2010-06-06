package org.synyx.jmite.domain.support;

import org.synyx.jmite.domain.internal.EntityCollectionWrapper;
import org.synyx.jmite.internal.MiteRestTemplate;


/**
 * @author Oliver Gierke
 */
public class AbstractResourceHandler<T extends AbstractEntity> extends
        AbstractReadableResourceHandler<T> {

    private IdExtractor<T> extractor = new IdExtractor<T>();


    /**
     * 
     */
    public AbstractResourceHandler(MiteRestTemplate template, String url,
            Class<T> entityType,
            Class<? extends EntityCollectionWrapper<T>> wrapper) {

        super(template, url, entityType, wrapper);
    }


    /**
     * Deletes the given entity.
     * 
     * @param entity
     */
    public void delete(T entity) {

        getTemplate().delete(getEntityUrl(), entity.getId().toString());
    }


    /**
     * Saves the given entity.
     * 
     * @param entity
     */
    public T save(T entity) {

        if (null == entity.getId()) {

            String location =
                    getTemplate().postForLocation(getUrl(), entity).toString();
            extractor.extract(location, getTemplate().build(getEntityUrl()),
                    entity);

        } else {
            getTemplate()
                    .put(getEntityUrl(), entity, entity.getId().toString());
        }

        return entity;
    }
}
