package org.synyx.jmite;

import org.synyx.jmite.domain.Project;
import org.synyx.jmite.domain.Projects;


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
