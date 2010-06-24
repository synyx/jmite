package org.jmite.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jmite.domain.support.EntityCollectionWrapper;


/**
 * @author Oliver Gierke
 */
@XmlRootElement(name = "time-entries")
class TimeEntryWrapper implements EntityCollectionWrapper<TimeEntry> {

    @XmlElement(name = "time-entry")
    private List<TimeEntry> entries = new ArrayList<TimeEntry>();


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.domain.internal.AbstractCollection#getEntities()
     */
    public List<TimeEntry> getEntities() {

        return entries;
    }
}
