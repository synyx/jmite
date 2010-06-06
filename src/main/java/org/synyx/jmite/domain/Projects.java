package org.synyx.jmite.domain;

import org.synyx.jmite.domain.internal.ProjectWrapper;
import org.synyx.jmite.domain.support.AbstractResourceHandler;
import org.synyx.jmite.internal.MiteRestTemplate;


/**
 * Projects resource.
 * 
 * @author Oliver Gierke
 */
public class Projects extends AbstractResourceHandler<Project> {

    /**
     * Creates a new {@link Projects} resource.
     * 
     * @param template
     */
    public Projects(MiteRestTemplate template) {

        super(template, "/projects", Project.class, ProjectWrapper.class);
    }
}
