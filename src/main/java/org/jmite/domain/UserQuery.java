package org.jmite.domain;

import java.util.ArrayList;
import java.util.List;

import org.jmite.domain.support.NameBasedQuery;
import org.jmite.domain.support.QueryParameter;


/**
 * Custom {@link NameBasedQuery} that adds additional methods to add
 * {@link User} related filters.
 * 
 * @author Oliver Gierke
 */
public class UserQuery extends NameBasedQuery<User> {

    private QueryParameter email;


    /**
     * Creates a new {@link UserQuery}.
     * 
     * @param template
     */
    public UserQuery(Users template) {

        super(template);
    }


    /**
     * Configures the query to select users with the given email address only.
     * 
     * @param email
     * @return
     */
    public UserQuery withEmail(String email) {

        this.email = new QueryParameter("email", email);

        return this;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.queries.NameBasedQuery#getQueryParameters()
     */
    @Override
    protected List<QueryParameter> getQueryParameters() {

        List<QueryParameter> parameters = new ArrayList<QueryParameter>();

        parameters.addAll(super.getQueryParameters());
        parameters.add(email);

        return parameters;
    }
}
