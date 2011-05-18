package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidLocationException;

public class RobberyTest {

	private Robbery r1;
	private GPSCoordinate gp1;
	private SendableSeverity es1;
	private boolean armed1;
	private boolean inProgress1;
	private long x1;
	private long y1;

	@Before
	public void setUp() {
		x1 = 31412;
		y1 = 27281;

		gp1 = new GPSCoordinate(x1, y1);
		es1 = SendableSeverity.SERIOUS;
		armed1 = false;
		inProgress1 = false;
	}

	@Test
	public void testConstructor() throws InvalidLocationException, InvalidSendableSeverityException {
		r1 = new Robbery(gp1, es1, "", armed1, inProgress1);
		assertEquals(x1, r1.getLocation().getX());
		assertEquals(y1, r1.getLocation().getY());
		assertEquals(SendableSeverity.SERIOUS, r1.getSeverity());
		assertEquals(armed1, r1.isArmed());
		assertEquals(inProgress1, r1.isInProgress());
	}

	@Test(expected = InvalidLocationException.class)
	public void testConstructorException1() throws InvalidLocationException, InvalidSendableSeverityException {
		r1 = new Robbery(null, es1, "", armed1, inProgress1);
	}

	@Test(expected = InvalidSendableSeverityException.class)
	public void testConstructorException2() throws InvalidLocationException, InvalidSendableSeverityException {
		r1 = new Robbery(gp1, null, "", armed1, inProgress1);
	}
}
