package org.synyx.jmite.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateMidnight;
import org.joda.time.Duration;
import org.synyx.jmite.Mite;
import org.synyx.jmite.domain.support.AbstractEntity;
import org.synyx.jmite.domain.support.QueryParameter;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
@XmlRootElement(name = "time-entry")
public class TimeEntry extends AbstractEntity {

    @XmlElement(name = "date-at")
    private Date dateAt;

    @XmlElement
    private Integer minutes = 0;

    @XmlElement
    private String note;

    @XmlElement(name = "project-id")
    private Integer projectId;

    @XmlElement(name = "cusomer-id")
    private Integer customerId;

    @XmlElement(name = "service-id")
    private Integer serviceId;


    /**
     * @return the note
     */
    public String getNote() {

        return note;
    }


    /**
     * Returns the day the {@link TimeEntry} was captured for.
     * 
     * @return
     */
    public DateMidnight getAt() {

        return null == dateAt ? null : new DateMidnight(dateAt);
    }


    /**
     * Returns the duration of the {@link TimeEntry} (in minutes).
     * 
     * @return
     */
    public Duration getDuration() {

        return Duration.standardMinutes(minutes);
    }


    /**
     * Returns
     * 
     * @return
     */
    QueryParameter getProject() {

        return Project.getReference(projectId);
    }


    public Project getProject(Mite mite) {

        return mite.projects().get(projectId);
    }


    public Project getProject(Projects projects) {

        return projects.get(projectId);
    }


    QueryParameter getCustomer() {

        return Customer.getReference(customerId);
    }


    public Customer getCustomer(Mite mite) {

        return mite.customers().get(customerId);
    }


    public Customer getCustomer(Customers customers) {

        return customers.get(customerId);
    }


    QueryParameter getService() {

        return Service.getReference(serviceId);
    }


    public Service getService(Mite mite) {

        return mite.services().get(serviceId);
    }


    public Service getService(Services services) {

        return services.get(serviceId);
    }
}
