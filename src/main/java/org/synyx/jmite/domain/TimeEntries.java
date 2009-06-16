package org.synyx.jmite.domain;

import org.synyx.jmite.domain.internal.TimeEntryWrapper;
import org.synyx.jmite.domain.support.AbstractResourceHandler;
import org.synyx.jmite.domain.support.QueryableResourceHandler;
import org.synyx.jmite.internal.MiteRestTemplate;


/**
 * Resource to handle {@link TimeEntry} resources. Inherits most of its
 * functionality from {@link AbstractResourceHandler} but adds the possibility
 * to lookup a {@link TimeEntryQuery} vie {@link #query()}.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class TimeEntries extends AbstractResourceHandler<TimeEntry> implements
        QueryableResourceHandler<TimeEntryQuery, TimeEntry> {

    /**
     * Creates a new {@link TimeEntries} resource.
     * 
     * @param template
     */
    public TimeEntries(MiteRestTemplate template) {

        super(template, "/time_entries", TimeEntry.class,
                TimeEntryWrapper.class);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.QueryableResourceHandler#query()
     */
    public TimeEntryQuery query() {

        return new TimeEntryQuery(this);
    }
}
