package org.synyx.jmite.domain.internal;

import java.util.List;


/**
 * @author Oliver Gierke
 */
public interface EntityCollectionWrapper<T> {

    List<T> getEntities();
}
