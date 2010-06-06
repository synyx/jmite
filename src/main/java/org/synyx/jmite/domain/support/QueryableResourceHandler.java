package org.synyx.jmite.domain.support;



/**
 * Interface for resource handlers that allow defining queries on the resource.
 * 
 * @author Oliver Gierke
 */
public interface QueryableResourceHandler<T extends AbstractQuery<S>, S extends AbstractEntity> {

    /**
     * Returns a new query to access selected resources. Allows to define
     * criterias the results have to fulfil.
     * 
     * @return
     */
    T query();
}
