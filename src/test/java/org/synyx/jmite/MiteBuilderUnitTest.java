package org.synyx.jmite;

import static org.synyx.jmite.MiteTestConstants.*;

import org.junit.Test;
import org.synyx.jmite.MiteClient.Builder;


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
