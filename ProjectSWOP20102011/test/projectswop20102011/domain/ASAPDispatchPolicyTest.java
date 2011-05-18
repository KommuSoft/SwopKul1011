package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class ASAPDispatchPolicyTest {

	private Emergency e1;
	private long x1, y1, x2, y2, x3, y3;
	private GPSCoordinate location1, location2, location3;
	private SendableSeverity severity1;
	private boolean armed1;
	private boolean inProgress1;
	private String name1, name2;
	private long speed1, speed2;
	private Unit u1, u2;

	@Before
	public void setUp() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException {
		x1 = 0;
		y1 = 0;
		x2 = 10;
		y2 = 10;
		x3 = 20;
		y3 = 20;

		location1 = new GPSCoordinate(x1, y1);
		location2 = new GPSCoordinate(x2, y2);
		location3 = new GPSCoordinate(x3, y3);

		severity1 = SendableSeverity.BENIGN;
		armed1 = false;
		inProgress1 = false;

		name1 = "PC1";
		name2 = "PC2";

		speed1 = 5;
		speed2 = 5;

		e1 = new Robbery(location1, severity1, "", armed1, inProgress1);
		u1 = new Policecar(name1, location2, speed1);
		u2 = new Policecar(name2, location3, speed2);
	}

	@Test
	public void testCompare() {
		assertTrue(e1.getDispatchPolicy().compare(u1, u2) < 0);
		assertTrue(e1.getDispatchPolicy().compare(u1, u1) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u2, u1) > 0);
	}

}
