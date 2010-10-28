package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidCoordinateException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class TrafficAccidentTest {

    private TrafficAccident ta1;
    private GPSCoordinate gp1;
    private EmergencySeverity es1;
    private long nmbOfCars1;
    private long nmbOfInjured1;
    private long x1;
    private long y1;

    @Before
    public void setUp() throws InvalidCoordinateException {
        x1 = 31412;
        y1 = 27281;

        gp1 = new GPSCoordinate(x1, y1);
        es1 = EmergencySeverity.SERIOUS;
        nmbOfCars1 = 12;
        nmbOfInjured1 = 94;
    }

    @Test
    public void testConstructor() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        ta1 = new TrafficAccident(gp1, es1, nmbOfCars1, nmbOfInjured1);
        assertEquals(x1, ta1.getLocation().getX());
        assertEquals(y1, ta1.getLocation().getY());
        assertEquals(EmergencySeverity.SERIOUS, ta1.getSeverity());
        assertEquals(nmbOfCars1, ta1.getNumberOfCars());
        assertEquals(nmbOfInjured1, ta1.getNumberOfInjured());
    }

    @Test(expected = InvalidLocationException.class)
    public void testConstructorException1() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        ta1 = new TrafficAccident(null, es1, nmbOfCars1, nmbOfInjured1);
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testConstructorException2() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        ta1 = new TrafficAccident(gp1, null, nmbOfCars1, nmbOfInjured1);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException3() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        ta1 = new TrafficAccident(gp1, es1, -1, nmbOfInjured1);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException4() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        ta1 = new TrafficAccident(gp1, es1, nmbOfCars1, -1);
    }
}
