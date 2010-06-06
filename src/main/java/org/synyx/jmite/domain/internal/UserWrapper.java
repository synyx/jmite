package org.synyx.jmite.domain.internal;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.synyx.jmite.domain.User;


/**
 * @author Oliver Gierke
 */
@XmlRootElement(name = "users")
public class UserWrapper implements EntityCollectionWrapper<User> {

    @XmlElement(name = "user")
    private List<User> users = new ArrayList<User>();


    /*
     * (non-Javadoc)
     * 
     * @see
     * org.synyx.jmite.domain.internal.EntityCollectionWrapper#getEntities()
     */
    public List<User> getEntities() {

        return users;
    }
}
