package org.synyx.jmite.domain.internal;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.synyx.jmite.domain.Project;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
@XmlRootElement(name = "projects")
public class ProjectWrapper implements EntityCollectionWrapper<Project> {

    @XmlElement(name = "project")
    private List<Project> projects;


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.domain.internal.AbstractCollection#getEntities()
     */
    public List<Project> getEntities() {

        return projects;
    }
}
