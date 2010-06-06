package org.jmite.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.jmite.domain.support.AbstractNamedEntity;
import org.jmite.domain.support.QueryParameter;


/**
 * Customer resource.
 * 
 * @author Oliver Gierke
 */
@XmlRootElement
@XmlType
public class Customer extends AbstractNamedEntity implements Parameterable {

    private static final String KEY = "customer-id";

    @XmlElement
    private Boolean archived;


    /**
     * Returns a reference to a {@link Customer}.
     * 
     * @param id
     * @return
     */
    static QueryParameter getReference(final Integer id) {

        return new QueryParameter(KEY, id);
    }


    /**
     * Returns whether the customer is archived.
     * 
     * @return
     */
    public Boolean isArchived() {

        return archived;
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
