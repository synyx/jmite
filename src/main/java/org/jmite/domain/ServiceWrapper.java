package org.jmite.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jmite.domain.support.EntityCollectionWrapper;


/**
 * @author Oliver Gierke
 */
@XmlRootElement(name = "services")
class ServiceWrapper implements EntityCollectionWrapper<Service> {

    @XmlElement(name = "service")
    private List<Service> services = new ArrayList<Service>();


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.synyx.jmite.domain.internal.EntityCollectionWrapper#getEntities()
     */
    public List<Service> getEntities() {

        return services;
    }
}
