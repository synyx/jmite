package org.jmite.domain;

import org.jmite.domain.support.QueryParameter;


/**
 * Time periods Mite understands in filtering.
 * 
 * @author Oliver Gierke
 */
public enum TimePeriod implements Parameterable {

    TODAY, YESTERDAY, THIS_WEEK, LAST_WEEK, THIS_MONTH, LAST_MONTH;

    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.domain.Parameterable#toQueryParameter()
     */
    public QueryParameter toQueryParameter() {

        return new QueryParameter("at", this.toString().toLowerCase());
    }
}
