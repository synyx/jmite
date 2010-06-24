package org.jmite.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jmite.domain.support.EntityCollectionWrapper;



/**
 * Wrapper class to allow JAXB marshalling of {@link Customer} collections.
 * 
 * @author Oliver Gierke
 */
@XmlRootElement(name = "customers")
class CustomerWrapper implements EntityCollectionWrapper<Customer> {

    @XmlElement(name = "customer")
    private List<Customer> customers = new ArrayList<Customer>();


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
