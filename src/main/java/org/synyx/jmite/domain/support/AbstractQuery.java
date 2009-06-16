package org.synyx.jmite.domain.support;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Oliver Gierke - gierke@synyx.de
 */
public abstract class AbstractQuery<T extends AbstractEntity> {

    private AbstractReadableResourceHandler<T> handler;


    /**
     * 
     */
    protected AbstractQuery(AbstractReadableResourceHandler<T> handler) {

        this.handler = handler;
    }


    public List<T> execute() {

        List<QueryParameter> parameters = new ArrayList<QueryParameter>();

        for (QueryParameter parameter : getQueryParameters()) {
            if (null != parameter) {
                parameters.add(parameter);
            }
        }

        return handler.all((QueryParameter[]) parameters
                .toArray(new QueryParameter[parameters.size()]));
    }


    /**
     * Returns a unique query result. Will throw an
     * {@link IllegalStateException} if the query results in multiple resources.
     * 
     * @return
     */
    public T unique() {

        List<T> resources = execute();

        if (resources.size() > 1) {
            throw new IllegalStateException("Only one resource expected!");
        }

        return resources.isEmpty() ? null : resources.get(0);
    }


    protected abstract List<QueryParameter> getQueryParameters();
}
