package org.synyx.jmite.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class TrackerInfo {

    @XmlElement(name = "minutes")
    private int minutes;

    @XmlElement(name = "since")
    private Date runningSince;


    /**
     * @return the minutes
     */
    int getMinutes() {

        return minutes;
    }


    boolean isRunning() {

        return null != runningSince;
    }
}