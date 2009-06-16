package org.synyx.jmite.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.synyx.jmite.domain.support.AbstractNamedEntity;
import org.synyx.jmite.domain.support.QueryParameter;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
@XmlRootElement
public class Service extends AbstractNamedEntity implements Parameterable {

    private static final String KEY = "service-id";

    @XmlElement
    private boolean billable = true;

    @XmlElement(name = "hourly-rate")
    private Integer hourlyRate;

    @XmlElement
    private boolean archived;


    /**
     * @param serviceId
     * @return
     */
    static QueryParameter getReference(Integer id) {

        return new QueryParameter(KEY, id);
    }


    /**
     * @return the billable
     */
    public boolean isBillable() {

        return billable;
    }


    public Service isBillable(boolean billable) {

        this.billable = billable;
        return this;
    }


    /**
     * @return the hourlyRate
     */
    public Integer getHourlyRate() {

        return hourlyRate;
    }


    /**
     * @param hourlyRate the hourlyRate to set
     */
    public Service setHourlyRate(Integer hourlyRate) {

        this.hourlyRate = hourlyRate;
        return this;
    }


    /**
     * @return the archived
     */
    public boolean isArchived() {

        return archived;
    }


    public Service isArchived(boolean archived) {

        this.archived = archived;
        return this;
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.synyx.samples.spring3.mite.domain.Parameterable#toQueryParameter()
     */
    public QueryParameter toQueryParameter() {

        return getReference(getId());
    }
}
