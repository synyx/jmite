package org.jmite;

import org.jmite.domain.Service;
import org.jmite.domain.Services;


/**
 * Integration test for {@link Services} resource.
 * 
 * @author Oliver Gierke
 */
public class ServicesIntegrationTest extends
        AbstractMiteIntegrationTests<Service, Services> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.synyx.jmite.AbstractReadingMiteIntegrationTests#getCollectionResource
     * ()
     */
    protected Services getCollectionResource() {

        return mite.services();
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.AbstractMiteIntegrationTests#getResourceToCreate()
     */
    @Override
    protected Service getResourceToCreate() {

        Service service = new Service();
        service.setName("JMiteTestService");
        service.setNote("Simple service for JMite integration tests");

        return service;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.synyx.jmite.AbstractReadingMiteIntegrationTests#modifyResource(org
     * .synyx.jmite.domain.support.AbstractEntity)
     */
    @Override
    protected void modifyResource(Service resource) {

        resource.setNote("Simple service after modification");
    }
}
