package org.synyx.jmite.domain.internal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.synyx.jmite.domain.TimeEntry;


/**
 * @author Oliver Gierke
 */
@XmlRootElement(name = "time-entries")
public class TimeEntryWrapper implements EntityCollectionWrapper<TimeEntry> {

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
