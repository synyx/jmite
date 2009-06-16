package org.synyx.jmite.domain.internal;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.synyx.jmite.domain.Service;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
@XmlRootElement(name = "services")
public class ServiceWrapper implements EntityCollectionWrapper<Service> {

    @XmlElement(name = "service")
    private List<Service> services;


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
