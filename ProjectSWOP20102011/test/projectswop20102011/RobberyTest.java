package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidCoordinateException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RobberyTest {

    private Robbery r1;
    private EmergencyList el1;
    private GPSCoordinate gp1;
    private EmergencySeverity es1;
    private boolean armed1;
    private boolean inProgress1;
    private long x1;
    private long y1;

    @Before
    public void setUp() throws InvalidCoordinateException {
        x1 = 31412;
        y1 = 27281;

        el1 = new EmergencyList();
        gp1 = new GPSCoordinate(x1, y1);
        es1 = EmergencySeverity.SERIOUS;
        armed1 = false;
        inProgress1 = true;
    }

    @Test
    public void testShortConstructor() throws InvalidEmergencyException {
        r1 = new Robbery(el1, gp1, es1, armed1, inProgress1);
        assertEquals(x1, r1.getLocation().getX());
        assertEquals(y1, r1.getLocation().getY());
        assertEquals(EmergencySeverity.SERIOUS, r1.getSeverity());
        assertEquals(armed1, r1.isArmed());
        assertEquals(inProgress1, r1.isInProgress());
        assertEquals(1, el1.getEmergencies().size());
        assertEquals(r1, el1.getEmergencies().get(0));
    }
    
}