package org.synyx.jmite.auth;

import static org.synyx.jmite.MiteTestConstants.*;

import org.junit.Test;
import org.synyx.jmite.MiteClient;


/**
 * Integration test for different authentication mechanisms
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class AuthenticationIntegrationTest {

    @Test
    public void authenticateWithApiKey() throws Exception {

        new MiteClient.Builder(URL).withApiKey(API_KEY).build();
    }


    @Test
    public void authenticateWithUsernameAndPassword() throws Exception {

        new MiteClient.Builder(URL).withUsernameAndPassword(EMAIL, PASSWORD)
                .build();
    }
}
