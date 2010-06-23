package org.jmite;

import org.jmite.domain.Project;
import org.jmite.domain.Projects;


/**
 * Integration test for {@link Project} instances.
 * 
 * @author Oliver Gierke
 */
public class ProjectsIntegrationTest extends
        AbstractMiteIntegrationTests<Project, Projects> {

    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.AbstractMiteIntegrationTests#getCollectionResource()
     */
    @Override
    protected Projects getCollectionResource() {

        return mite.projects();
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.jmite.AbstractReadingMiteIntegrationTests#getResourceToCreate()
     */
    @Override
    protected Project getResourceToCreate() {

        Project project = new Project();
        project.setName("Testproject");
        return project;
    }
}
