package org.jmite.domain;

import org.jmite.domain.support.AbstractResourceHandler;
import org.jmite.domain.support.NameBasedQuery;
import org.jmite.domain.support.QueryableResourceHandler;
import org.jmite.internal.MiteRestTemplate;


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
