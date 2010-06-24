package org.jmite.domain;

import org.jmite.domain.support.AbstractResourceHandler;
import org.jmite.internal.MiteRestTemplate;


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
