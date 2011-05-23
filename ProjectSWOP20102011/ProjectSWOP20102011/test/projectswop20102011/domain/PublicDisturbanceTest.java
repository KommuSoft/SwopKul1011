package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class PublicDisturbanceTest {
    private PublicDisturbance pd1;
    private GPSCoordinate gp1;
    private SendableSeverity es1;
    private long nmbOfPeople1;
    private long x1;
    private long y1;

    @Before
    public void setUp(){
        x1 = 3141592;
        y1 = 2718281;

        gp1 = new GPSCoordinate(x1, y1);
        es1 = SendableSeverity.SERIOUS;
        nmbOfPeople1 = 666;
    }

    @Test
    public void testConstructor() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, "", nmbOfPeople1);
        assertEquals(x1, pd1.getLocation().getX());
        assertEquals(y1, pd1.getLocation().getY());
        assertEquals(SendableSeverity.SERIOUS, pd1.getSeverity());
        assertEquals(nmbOfPeople1, pd1.getNumberOfPeople());
		assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED, pd1.getStatus());
    }

    @Test(expected = InvalidLocationException.class)
    public void testConstructorException1() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(null, es1, "", nmbOfPeople1);
    }

    @Test(expected = InvalidSendableSeverityException.class)
    public void testConstructorException2() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, null, "", nmbOfPeople1);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException3() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, "", 0);
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testConstructorException4() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
        pd1 = new PublicDisturbance(gp1, es1, "", -1425);
    }

	@Test(expected = InvalidSendableStatusException.class)
	public void testSetStatus() throws InvalidLocationException, InvalidSendableSeverityException, InvalidSendableStatusException, NumberOutOfBoundsException{
		SendableStatus es = null;
		pd1 = new PublicDisturbance(gp1, es1, "", nmbOfPeople1);
		pd1.setStatus(es);
	}
}
