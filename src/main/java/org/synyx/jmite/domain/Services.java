package org.synyx.jmite.domain;

import org.synyx.jmite.domain.internal.ServiceWrapper;
import org.synyx.jmite.domain.support.AbstractResourceHandler;
import org.synyx.jmite.domain.support.NameBasedQuery;
import org.synyx.jmite.domain.support.QueryableResourceHandler;
import org.synyx.jmite.internal.MiteRestTemplate;


/**
 * @author Oliver Gierke
 */
public class Services extends AbstractResourceHandler<Service> implements
        QueryableResourceHandler<NameBasedQuery<Service>, Service> {

    /**
     * @param template
     */
    public Services(MiteRestTemplate template) {

        super(template, "/services", Service.class, ServiceWrapper.class);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.QueryableResourceHandler#query()
     */
    public NameBasedQuery<Service> query() {

        return new NameBasedQuery<Service>(this);
    }
}
