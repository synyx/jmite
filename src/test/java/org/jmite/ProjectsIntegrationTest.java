package org.jmite;

import org.jmite.domain.Project;
import org.jmite.domain.Projects;


/**
 * @author Oliver Gierke
 */
public class ProjectsIntegrationTest extends
        AbstractReadingMiteIntegrationTests<Project, Projects> {

    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.AbstractMiteIntegrationTests#getCollectionResource()
     */
    @Override
    protected Projects getCollectionResource() {

        return mite.projects();
    }

}
