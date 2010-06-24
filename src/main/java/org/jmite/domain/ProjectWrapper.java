package org.jmite.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.jmite.domain.support.EntityCollectionWrapper;


/**
 * @author Oliver Gierke
 */
@XmlRootElement(name = "projects")
class ProjectWrapper implements EntityCollectionWrapper<Project> {

    @XmlElement(name = "project")
    private List<Project> projects = new ArrayList<Project>();


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.domain.internal.AbstractCollection#getEntities()
     */
    public List<Project> getEntities() {

        return projects;
    }
}
