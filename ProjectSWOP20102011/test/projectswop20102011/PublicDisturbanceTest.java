package projectswop20102011;

import projectswop20102011.exceptions.InvalidCoordinateException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class PublicDisturbanceTest {

    private PublicDisturbance pd1;
    private GPSCoordinate gp1;
    private EmergencySeverity es1;
    private long nmbOfPeople1;
    private long x1;
    private long y1;

    @Before
    public void setUp() throws InvalidCoordinateException {
        x1 = 3141592;
        y1 = 2718281;

        gp1 = new GPSCoordinate(x1, y1);
        es1 = EmergencySeverity.SERIOUS;
        nmbOfPeople1 = 666;
    }

    @Test
    public void testShortConstructor() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, nmbOfPeople1);
        assertEquals(x1, pd1.getLocation().getX());
        assertEquals(y1, pd1.getLocation().getY());
        assertEquals(EmergencySeverity.SERIOUS, pd1.getSeverity());
        assertEquals(nmbOfPeople1, pd1.getNumberOfPeople());
    }

    @Test(expected = InvalidLocationException.class)
    public void testShortConstructorException1() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(null, es1, nmbOfPeople1);
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testShortConstructorException2() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, null, nmbOfPeople1);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testShortConstructorException3() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, 0);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testShortConstructorException4() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, -1425);
    }
}
