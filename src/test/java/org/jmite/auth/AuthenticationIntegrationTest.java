package org.jmite.auth;

import static org.jmite.MiteTestConstants.*;

import org.jmite.MiteClient;
import org.junit.Test;


/**
 * Integration test for different authentication mechanisms
 * 
 * @author Oliver Gierke
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
