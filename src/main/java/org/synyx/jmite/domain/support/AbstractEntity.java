package org.synyx.jmite.domain.support;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.joda.time.DateTime;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractEntity {

    @XmlElement
    private Integer id;

    @XmlElement(name = "updated-at")
    private Date updatedAt;

    @XmlElement(name = "created-at")
    private Date createdAt;


    /**
     * @return the id
     */
    public Integer getId() {

        return id;
    }


    public void setId(Integer id) {

        this.id = id;
    }


    /**
     * @return the updatedAt
     */
    public DateTime getUpdatedAt() {

        return null == updatedAt ? null : new DateTime(updatedAt);
    }


    /**
     * @return the createdAt
     */
    public DateTime getCreatedAt() {

        return null == createdAt ? null : new DateTime(createdAt);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AbstractEntity)) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) obj;

        return this.id == null ? null == that.id : this.id.equals(that.id);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int result = 17;

        return result + (null == id ? 0 : id.hashCode());
    }
}
