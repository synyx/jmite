package org.jmite.domain;

import java.util.Arrays;
import java.util.List;

import org.jmite.domain.support.AbstractQuery;
import org.jmite.domain.support.QueryParameter;
import org.joda.time.DateMidnight;


/**
 * Custom {@link AbstractQuery} implementation that allows to define filters to
 * access {@link TimeEntry} resources.
 * 
 * @author Oliver Gierke
 */
public class TimeEntryQuery extends AbstractQuery<TimeEntry> {

    private QueryParameter customer;
    private QueryParameter service;
    private QueryParameter project;

    private QueryParameter at;
    private QueryParameter from;
    private QueryParameter to;


    /**
     * Creates a new {@link TimeEntryQuery}.
     */
    public TimeEntryQuery(TimeEntries mite) {

        super(mite);
    }


    /**
     * Configures the query to return {@link TimeEntry} resources with the given
     * {@link Customer}.
     * 
     * @param customer
     * @return
     */
    public TimeEntryQuery with(Customer customer) {

        this.customer = customer.toQueryParameter();
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources from the given
     * project.
     * 
     * @param project
     * @return
     */
    public TimeEntryQuery with(Project project) {

        this.project = project.toQueryParameter();
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources captured for
     * the given {@link Service}.
     * 
     * @param service
     * @return
     */
    public TimeEntryQuery with(Service service) {

        this.service = service.toQueryParameter();
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources for the
     * {@link Project} with the given id.
     * 
     * @param id
     * @return
     */
    public TimeEntryQuery withProject(int id) {

        customer = Project.getReference(id);
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources for the
     * {@link Project} of the given {@link TimeEntry}.
     * 
     * @param entry
     * @return
     */
    public TimeEntryQuery withProject(TimeEntry entry) {

        project = entry.getProject();
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources for the
     * {@link Customer} of the given {@link Project}.
     * 
     * @param project
     * @return
     */
    public TimeEntryQuery withCustomer(Project project) {

        customer = project.getCustomer();
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources for the
     * {@link Customer} of the given {@link TimeEntry}.
     * 
     * @param entry
     * @return
     */
    public TimeEntryQuery withCustomer(TimeEntry entry) {

        customer = entry.getCustomer();
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources for the
     * {@link Customer} with the given id.
     * 
     * @param id
     * @return
     */
    public TimeEntryQuery withCustomer(int id) {

        customer = Customer.getReference(id);
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources captured for
     * the given {@link Service}.
     * 
     * @param entry
     * @return
     */
    public TimeEntryQuery withService(TimeEntry entry) {

        service = entry.getService();
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources captured for
     * the {@link Service} with the given id.
     * 
     * @param id
     * @return
     */
    public TimeEntryQuery withService(int id) {

        service = Service.getReference(id);
        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources within the
     * given {@link TimePeriod}. Overrides any other time restriction on the
     * query.
     * 
     * @param period
     * @return
     */
    public TimeEntryQuery within(TimePeriod period) {

        this.at = period.toQueryParameter();

        this.from = null;
        this.to = null;

        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources with the given
     * time period. Overrides any other time restriction on the query.
     * 
     * @param from
     * @param to
     * @return
     */
    public TimeEntryQuery within(DateMidnight from, DateMidnight to) {

        this.at = null;

        this.from = new QueryParameter("from", from);
        this.to = new QueryParameter("to", to);

        return this;
    }


    /**
     * Configures the query to return {@link TimeEntry} resources for the given
     * day. Overrides any other time restriction on the query.
     * 
     * @param day
     * @return
     */
    public TimeEntryQuery at(DateMidnight day) {

        this.at = new QueryParameter("at", day);

        this.from = null;
        this.to = null;

        return this;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.synyx.jmite.AbstractQuery#getQueryParameters()
     */
    @Override
    protected List<QueryParameter> getQueryParameters() {

        return Arrays.asList(at, from, to, project, customer, service);
    }
}
