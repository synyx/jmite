package org.synyx.jmite;

import org.synyx.jmite.domain.Customers;
import org.synyx.jmite.domain.Projects;
import org.synyx.jmite.domain.Services;
import org.synyx.jmite.domain.TimeEntries;
import org.synyx.jmite.domain.Users;


/**
 * Interface to offer access to Mite resources.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public interface Mite {

    /**
     * Returns a resource to access {@link Projects}.
     * 
     * @return
     */
    Projects projects();


    /**
     * Returns a resource to access {@link Customers}.
     * 
     * @return
     */
    Customers customers();


    /**
     * Returns a resource to access {@link TimeEntries}.
     * 
     * @return
     */
    TimeEntries timeEntries();


    /**
     * Returns a resource to access {@link Services}.
     * 
     * @return
     */
    Services services();


    /**
     * Returns a resource to access {@link Users}.
     * 
     * @return
     */
    Users users();
}