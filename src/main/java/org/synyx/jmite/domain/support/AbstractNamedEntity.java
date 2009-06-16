package org.synyx.jmite.domain.support;

import javax.xml.bind.annotation.XmlElement;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
public class AbstractNamedEntity extends AbstractEntity {

    @XmlElement
    private String name;

    @XmlElement
    private String note;


    /**
     * @return the name
     */
    public String getName() {

        return name;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {

        this.name = name;
    }


    /**
     * @return the note
     */
    public String getNote() {

        return note;
    }


    /**
     * @param note the note to set
     */
    public void setNote(String note) {

        this.note = note;
    }
}
