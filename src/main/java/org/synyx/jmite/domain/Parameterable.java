package org.synyx.jmite.domain;

import org.synyx.jmite.domain.support.QueryParameter;


/**
 * Interface to express that a given type can be used as {@link QueryParameter}.
 * This will mainly be used to formulate restrictions on queries for resources.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
interface Parameterable {

    /**
     * Returns the {@link QueryParameter} the type can be used as.
     * 
     * @return
     */
    QueryParameter toQueryParameter();
}
