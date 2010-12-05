package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
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
    public void setUp(){
        x1 = 3141592;
        y1 = 2718281;

        gp1 = new GPSCoordinate(x1, y1);
        es1 = EmergencySeverity.SERIOUS;
        nmbOfPeople1 = 666;
    }

    @Test
    public void testConstructor() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, "", nmbOfPeople1);
        assertEquals(x1, pd1.getLocation().getX());
        assertEquals(y1, pd1.getLocation().getY());
        assertEquals(EmergencySeverity.SERIOUS, pd1.getSeverity());
        assertEquals(nmbOfPeople1, pd1.getNumberOfPeople());
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, pd1.getStatus());
    }

    @Test(expected = InvalidLocationException.class)
    public void testConstructorException1() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(null, es1, "", nmbOfPeople1);
    }

    @Test(expected = InvalidEmergencySeverityException.class)
    public void testConstructorException2() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, null, "", nmbOfPeople1);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException3() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, "", 0);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException4() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, "", -1425);
    }

	@Test(expected = InvalidEmergencyStatusException.class)
	public void testSetStatus() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidEmergencyStatusException, NumberOutOfBoundsException{
		EmergencyStatus es = null;
		pd1 = new PublicDisturbance(gp1, es1, "", nmbOfPeople1);
		pd1.setStatus(es);
	}
	
	@Test
	public void testGetId() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException{
		pd1 = new PublicDisturbance(gp1, es1, "", nmbOfPeople1);
		assertTrue(pd1.getId()>=0);
	}
}
