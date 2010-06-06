package org.jmite.domain;

import org.jmite.domain.internal.CustomerWrapper;
import org.jmite.domain.support.AbstractResourceHandler;
import org.jmite.internal.MiteRestTemplate;


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
