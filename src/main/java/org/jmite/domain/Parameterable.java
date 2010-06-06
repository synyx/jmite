package org.jmite.domain;

import org.jmite.domain.support.QueryParameter;


/**
 * Interface to express that a given type can be used as {@link QueryParameter}.
 * This will mainly be used to formulate restrictions on queries for resources.
 * 
 * @author Oliver Gierke
 */
interface Parameterable {

    /**
     * Returns the {@link QueryParameter} the type can be used as.
     * 
     * @return
     */
    QueryParameter toQueryParameter();
}
