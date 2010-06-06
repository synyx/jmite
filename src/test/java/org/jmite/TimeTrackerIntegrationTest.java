package org.jmite;

import static org.jmite.MiteTestConstants.*;
import static org.junit.Assert.*;

import java.util.List;

import org.jmite.Mite;
import org.jmite.MiteClient;
import org.jmite.domain.TimeEntry;
import org.jmite.domain.Tracker;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Oliver Gierke
 */
public class TimeTrackerIntegrationTest {

    private TimeEntry firstEntry;

    private Tracker firstTracker;
    private Tracker secondTracker;


    @Before
    public void setUp() {

        Mite mite =
                new MiteClient.Builder(URL).withUsernameAndPassword(EMAIL,
                        PASSWORD).build();

        List<TimeEntry> entries = mite.timeEntries().all();

        firstEntry = entries.get(0);
        firstTracker = mite.tracker(firstEntry);
        firstTracker.stop();

        secondTracker = mite.tracker(entries.get(1));
        secondTracker.stop();
    }


    @Test
    public void basicSetup() throws Exception {

        assertFalse("Expected tracker not to be running!", firstTracker
                .isRunning());
    }


    @Test
    public void startingTracker() throws Exception {

        firstTracker.start();
        assertTrue(firstTracker.isRunning());

        firstTracker.stop();
        assertFalse(firstTracker.isRunning());
    }


    @Test
    public void runsMutually() throws Exception {

        firstTracker.start();
        secondTracker.start();

        assertFalse(firstTracker.isRunning());
        assertTrue(secondTracker.isRunning());

        firstTracker.stop();
        secondTracker.stop();
    }


    @Test
    public void getTimeEntry() throws Exception {

        assertEquals(firstEntry, firstTracker.toTimeEntry());
    }
}
