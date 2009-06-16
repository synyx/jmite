package org.synyx.jmite.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.synyx.jmite.domain.support.AbstractNamedEntity;


/**
 * User resource.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
@XmlRootElement
public class User extends AbstractNamedEntity {

    @XmlElement
    private String email;

    @XmlElement
    private Boolean archived;

    @XmlElement
    private Boolean resticted;


    /**
     * @return the email
     */
    public String getEmail() {

        return email;
    }


    /**
     * @return the archived
     */
    public Boolean isArchived() {

        return archived;
    }


    /**
     * @return the resticted
     */
    public Boolean isResticted() {

        return resticted;
    }
}
