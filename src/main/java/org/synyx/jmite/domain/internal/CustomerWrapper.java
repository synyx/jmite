package org.synyx.jmite.domain.internal;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.synyx.jmite.domain.Customer;


/**
 * Wrapper class to allow JAXB marshalling of {@link Customer} collections.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
@XmlRootElement(name = "customers")
public class CustomerWrapper implements EntityCollectionWrapper<Customer> {

    @XmlElement(name = "customer")
    private List<Customer> customers;


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.synyx.jmite.domain.internal.EntityCollectionWrapper#getEntities()
     */
    public List<Customer> getEntities() {

        return customers;
    }
}
