package org.synyx.jmite.domain.internal;

import java.util.List;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
public interface EntityCollectionWrapper<T> {

    List<T> getEntities();
}
