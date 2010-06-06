package org.synyx.jmite;

import static org.junit.Assert.*;
import static org.synyx.jmite.MiteTestConstants.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.synyx.jmite.domain.support.AbstractEntity;
import org.synyx.jmite.domain.support.AbstractReadableResourceHandler;


/**
 * Integration test for readable resources.
 * 
 * @author Oliver Gierke
 */
public abstract class AbstractReadingMiteIntegrationTests<T extends AbstractEntity, S extends AbstractReadableResourceHandler<T>> {

    protected Mite mite;
    private Integer id;


    @Before
    public void setUp() {

        mite = new MiteClient.Builder(URL).withApiKey(API_KEY).build();
    }


    @Test
    public void all() {

        List<T> all = getCollectionResource().all();

        assertNotNull(all);
        assertFalse(all.isEmpty());
    }


    @Test
    public void single() {

        T result = getCollectionResource().get(getId());

        assertNotNull(result);
        assertNotNull(result.getId());

        // Trigger a secnd time to check caching
        getCollectionResource().get(getId());
    }


    @Test
    public void getInvalidResource() {

        assertNull(getCollectionResource().get(4711));
    }


    /**
     * Returns the id of a single resource to be retrieved.
     * 
     * @return
     */
    protected Integer getId() {

        if (null == id) {
            id = getCollectionResource().all().get(0).getId();
        }

        return id;
    }


    /**
     * Returns a single resource.
     * 
     * @return
     */
    protected T get() {

        return getCollectionResource().get(getId());
    }


    /**
     * Return a newly created resource
     * 
     * @return
     */
    protected T getResourceToCreate() {

        return null;
    }


    /**
     * Modify a resource to be updated.
     * 
     * @param resource
     */
    protected void modifyResource(T resource) {

    }


    /**
     * Return the concrete collection resource to work with.
     * 
     * @return
     */
    protected abstract S getCollectionResource();
}
