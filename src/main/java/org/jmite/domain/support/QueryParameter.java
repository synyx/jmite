package org.jmite.domain.support;

import org.joda.time.DateMidnight;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


/**
 * Class to abstract query parameters used to define filters on resources.
 * 
 * @author Oliver Gierke
 */
public class QueryParameter {

    private static final DateTimeFormatter FORMAT =
            DateTimeFormat.forPattern("yyyy-MM-dd");

    private String key;
    private Object value;


    /**
     * Creates a new {@link QueryParameter}. {@code value} parameters will be
     * turned into {@link String}s prior to usage.
     * 
     * @param key
     * @param value
     */
    public QueryParameter(String key, Object value) {

        this.key = key;
        this.value = value;
    }


    /**
     * Creates a new {@link QueryParameter}.
     * 
     * @param key
     * @param date
     */
    public QueryParameter(String key, DateMidnight date) {

        this(key, date.toString(FORMAT));
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        return key + "=" + (value == null ? "" : value.toString());
    }
}
