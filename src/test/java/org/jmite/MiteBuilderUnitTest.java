package org.jmite;

import static org.jmite.MiteTestConstants.*;

import org.jmite.MiteClient;
import org.jmite.MiteClient.Builder;
import org.junit.Test;


/**
 * Unit test for {@link Builder}.
 * 
 * @author Oliver Gierke
 */
public class MiteBuilderUnitTest {

    @Test(expected = IllegalStateException.class)
    public void preventsNoAuthentication() {

        new MiteClient.Builder(URL).build();
    }
}
