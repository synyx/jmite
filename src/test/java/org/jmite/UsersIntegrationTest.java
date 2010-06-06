package org.jmite;

import static org.junit.Assert.*;

import org.jmite.domain.User;
import org.jmite.domain.UserQuery;
import org.jmite.domain.Users;
import org.junit.Test;


/**
 * Integration test for Users resource.
 * 
 * @author Oliver Gierke
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

        assertEquals(user, query().withEmail(user.getEmail()).execute().get(0));
    }


    private UserQuery query() {

        return mite.users().query();
    }
}
