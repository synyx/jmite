package org.synyx.jmite.domain.support;

import static org.junit.Assert.*;

import org.junit.Test;
import org.synyx.jmite.domain.Customer;


/**
 * Unit test for {@link IdExtractor}.
 * 
 * @author Oliver Gierke
 */
public class IdExtractorUnitTest {

    private static final Integer ID = 2;
    private static final String TEMPLATE = "http://foo.bar/{id}.xml";
    private static final String URI = String.format("http://foo.bar/%s.xml", 2);
    private static final String ILLEGAL_TEMPLATE = "http://bar.foo/{id}.xml";
    private static final String TEMPLATE_WITHOUT_ID =
            "http://foo.bar/foobar.xml";


    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullUri() throws Exception {

        new IdExtractor<Customer>().extract(null, TEMPLATE, new Customer());
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullTemplate() throws Exception {

        new IdExtractor<Customer>().extract(URI, null, new Customer());
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullEntity() throws Exception {

        new IdExtractor<Customer>().extract(URI, TEMPLATE, null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejectsIllegalTemplate() throws Exception {

        new IdExtractor<Customer>().extract(URI, ILLEGAL_TEMPLATE,
                new Customer());
    }


    @Test(expected = IllegalArgumentException.class)
    public void rejectsTemplateWithoutId() throws Exception {

        new IdExtractor<Customer>().extract(URI, TEMPLATE_WITHOUT_ID,
                new Customer());
    }


    @Test
    public void extractsUriCorrectly() throws Exception {

        IdExtractor<Customer> extractor = new IdExtractor<Customer>();

        assertEquals(ID, extractor.extract(URI, TEMPLATE, new Customer())
                .getId());
    }
}
