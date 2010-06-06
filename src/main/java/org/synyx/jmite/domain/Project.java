package org.synyx.jmite.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.synyx.jmite.domain.support.AbstractNamedEntity;
import org.synyx.jmite.domain.support.QueryParameter;


/**
 * Project resource.
 * 
 * @author Oliver Gierke
 */
@XmlRootElement
public class Project extends AbstractNamedEntity implements Parameterable {

    private static final String KEY = "project-id";

    @XmlElement(name = "customer-id")
    private Integer customerId;


    static QueryParameter getReference(Integer id) {

        return new QueryParameter(KEY, id);
    }


    /**
     * @return the customerId
     */
    public Integer getCustomerId() {

        return customerId;
    }


    QueryParameter getCustomer() {

        return Customer.getReference(getCustomerId());
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
