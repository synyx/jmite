package org.synyx.jmite.domain;

import org.synyx.jmite.domain.internal.CustomerWrapper;
import org.synyx.jmite.domain.support.AbstractResourceHandler;
import org.synyx.jmite.internal.MiteRestTemplate;


/**
 * Customers resource.
 * 
 * @author Oliver Gierke
 */
public class Customers extends AbstractResourceHandler<Customer> {

    /**
     * Creates a new {@link Customers} resource.
     * 
     * @param template
     */
    public Customers(MiteRestTemplate template) {

        super(template, "/customers", Customer.class, CustomerWrapper.class);
    }
}
