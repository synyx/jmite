package org.jmite;

import org.jmite.domain.Customer;
import org.jmite.domain.Customers;
import org.junit.Test;


/**
 * Integration test for {@link Customers} resource.
 * 
 * @author Oliver Gierke
 */
public class CustomersIntegrationTest extends
        AbstractMiteIntegrationTests<Customer, Customers> {

    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.AbstractMiteIntegrationTests#getCollectionResource()
     */
    @Override
    protected Customers getCollectionResource() {

        return mite.customers();
    }


    @Test
    public void getsTimeEntriesForCustomer() throws Exception {

        mite.timeEntries().query().with(getCustomer());
        mite.timeEntries().query().withCustomer(getId());
    }


    private Customer getCustomer() {

        return mite.customers().get(getId());
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jmite.AbstractReadingMiteIntegrationTests#getResourceToCreate()
     */
    @Override
    protected Customer getResourceToCreate() {

        Customer customer = new Customer();
        customer.setName("Testcustomer");
        return customer;
    }
}
