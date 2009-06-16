package org.synyx.jmite;

import static org.junit.Assert.*;

import org.junit.Test;
import org.synyx.jmite.domain.User;
import org.synyx.jmite.domain.UserQuery;
import org.synyx.jmite.domain.Users;


/**
 * Integration test for Users resource.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class UsersIntegrationTest extends
        AbstractReadingMiteIntegrationTests<User, Users> {

    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.AbstractMiteIntegrationTests#getCollectionResource()
     */
    @Override
    protected Users getCollectionResource() {

        return mite.users();
    }


    @Test
    public void myself() throws Exception {

        mite.users().myself();
    }


    @Test
    public void readWithEmail() throws Exception {

        User user = get();

        assertEquals(user, query().withEmail(user.getEmail()).unique());
    }


    private UserQuery query() {

        return mite.users().query();
    }
}
