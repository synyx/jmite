package org.synyx.jmite;

import org.junit.Test;
import org.synyx.jmite.domain.Customer;
import org.synyx.jmite.domain.Customers;


/**
 * Integration test for {@link Customers} resource.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class CustomersIntegrationTest extends
        AbstractReadingMiteIntegrationTests<Customer, Customers> {

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
}
