package org.synyx.jmite.domain;

import org.synyx.jmite.domain.internal.UserWrapper;
import org.synyx.jmite.domain.support.AbstractReadableResourceHandler;
import org.synyx.jmite.domain.support.QueryableResourceHandler;
import org.synyx.jmite.internal.MiteRestTemplate;


/**
 * Users resource.
 * 
 * @author Oliver Gierke
 */
public class Users extends AbstractReadableResourceHandler<User> implements
        QueryableResourceHandler<UserQuery, User> {

    private static final String URL = "/users";
    public static final String MYSELF_URL = "/myself";


    /**
     * @param template
     */
    public Users(MiteRestTemplate template) {

        super(template, URL, User.class, UserWrapper.class);
    }


    /**
     * Returns the {@link User} that is currently used for authentication agains
     * the Mite server.
     * 
     * @return
     */
    public User myself() {

        return getTemplate().getForObject(MYSELF_URL, User.class);
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.QueryableResourceHandler#query()
     */
    public UserQuery query() {

        return new UserQuery(this);
    }
}
