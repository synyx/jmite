package org.synyx.jmite;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Integration test to check setup in a Spring context.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mite-context.xml")
public class SpringMiteIntegrationTest {

    @Autowired
    private Mite client;


    @Test
    public void testname() throws Exception {

        assertNotNull(client);
    }
}
