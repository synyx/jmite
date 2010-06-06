package org.jmite.domain.support;

import java.util.Arrays;
import java.util.List;


/**
 * Query type that is based on a {@code name} attribute.
 * 
 * @author Oliver Gierke
 */
public class NameBasedQuery<T extends AbstractNamedEntity> extends
        AbstractQuery<T> {

    private QueryParameter name;


    /**
     * Creates a new {@link NameBasedQuery}.
     * 
     * @param handler
     */
    public NameBasedQuery(AbstractReadableResourceHandler<T> handler) {

        super(handler);
    }


    /**
     * Restricts the query to return only resources with the given name.
     * 
     * @param name
     * @return
     */
    public NameBasedQuery<T> withName(String name) {

        this.name = new QueryParameter("name", name);

        return this;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.AbstractQuery#getQueryParameters()
     */
    @Override
    protected List<QueryParameter> getQueryParameters() {

        return Arrays.asList(name);
    }
}
