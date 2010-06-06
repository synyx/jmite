package org.jmite.domain.internal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import org.jmite.domain.TrackerInfo;


/**
 * @author Oliver Gierke
 */
@XmlRootElement(name = "tracker")
public class TrackerWrapper {

    @XmlElements( { @XmlElement(name = "stopped-time-entry"),
            @XmlElement(name = "tracking-time-entry") })
    private TrackerInfo info;


    /**
     * @return the info
     */
    public TrackerInfo getInfo() {

        return info;
    }
}
