package org.synyx.jmite;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.synyx.jmite.domain.support.AbstractEntity;
import org.synyx.jmite.domain.support.AbstractResourceHandler;


/**
 * Integration test for resources that can be manipulated.
 * 
 * @author Oliver Gierke
 */
public abstract class AbstractMiteIntegrationTests<T extends AbstractEntity, S extends AbstractResourceHandler<T>>
        extends AbstractReadingMiteIntegrationTests<T, S> {

    @Test
    @Ignore
    public void create() {

        T resource = createResource();

        if (null == resource) {
            return;
        }

        getCollectionResource().delete(resource);
    }


    @Test
    @Ignore
    public void update() {

        T resource = createResource();

        if (null == resource) {
            return;
        }

        modifyResource(resource);

        Integer id = resource.getId();
        getCollectionResource().save(resource);
        assertEquals(id, resource.getId());

        getCollectionResource().delete(resource);

    }


    /**
     * Creates a resource and returns it.
     * 
     * @return
     */
    private T createResource() {

        T resource = getResourceToCreate();

        if (null == resource) {
            return null;
        }

        getCollectionResource().save(resource);

        assertNotNull(resource.getId());
        return resource;
    }
}
