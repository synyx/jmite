package org.synyx.jmite.domain;

import org.synyx.jmite.Mite;
import org.synyx.jmite.domain.internal.TrackerWrapper;
import org.synyx.jmite.internal.MiteRestTemplate;


/**
 * Abstraction of the stopwatch available for Mite.
 * 
 * @author Oliver Gierke - gierke@synyx.de
 */
public class Tracker {

    private static final String URL = "/tracker";
    private static final String ENTITY_URL = URL + "/{id}";

    private MiteRestTemplate template;
    private TimeEntry entry;
    private TrackerInfo info;


    /**
     * Returns a new {@link Tracker}.
     */
    public Tracker(MiteRestTemplate template, TimeEntry entry) {

        this.template = template;
        this.entry = entry;
    }


    /**
     * Starts the tracker.
     */
    public void start() {

        info =
                template.putForObject(ENTITY_URL, TrackerWrapper.class,
                        entry.getId()).getInfo();
    }


    /**
     * Stops the {@link Tracker}.
     */
    public void stop() {

        info =
                template.deleteForObject(ENTITY_URL, TrackerWrapper.class,
                        entry.getId()).getInfo();
    }


    public void stopAndApply(Mite mite) {

        stop();
        mite.timeEntries().save(toTimeEntry());
    }


    /**
     * Returns whether the {@link Tracker} for the {@link TimeEntry} is
     * currently running.
     * 
     * @return
     */
    public boolean isRunning() {

        info =
                template.getForObject(ENTITY_URL, TrackerWrapper.class,
                        String.valueOf(entry.getId())).getInfo();
        return info.isRunning();
    }


    /**
     * Applies the current {@link Tracker} to the {@link TimeEntry} the
     * {@link Tracker} is applied to. Stops the {@link Tracker}.
     * 
     * @return
     */
    public TimeEntry toTimeEntry() {

        stop();

        return entry.setDuration(info.getMinutes());
    }


    /**
     * Applies the current {@link Tracker} state to the {@link TimeEntry}
     * setting the given note, project and service.
     * 
     * @param note
     * @param project
     * @param service
     * @return
     */
    public TimeEntry toTimeEntry(String note, Project project, Service service) {

        entry.setProject(project);
        entry.setService(service);
        entry.setNote(note);
        entry.setDuration(info.getMinutes());

        return entry;
    }
}
