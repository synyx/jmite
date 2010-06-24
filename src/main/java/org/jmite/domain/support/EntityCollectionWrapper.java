package org.jmite.domain.support;

import java.util.List;


/**
 * Simple interface for JAXB wrapper classes for collections.
 * 
 * @author Oliver Gierke
 */
public interface EntityCollectionWrapper<T> {

    /**
     * Returns the wrapped entities.
     * 
     * @return
     */
    List<T> getEntities();
}
