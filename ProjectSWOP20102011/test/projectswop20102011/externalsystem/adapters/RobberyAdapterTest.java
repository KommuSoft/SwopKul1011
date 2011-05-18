package projectswop20102011.externalsystem.adapters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Robbery;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class RobberyAdapterTest {

	private Robbery r1, r2;
	private RobberyAdapter robbery1, robbery2;
	private GPSCoordinate location1, location2;
	private SendableSeverity severity1, severity2;
	private String description1, description2;
	private boolean armed1, armed2;
	private boolean inProgress1, inProgress2;
	private TimeAdapter time;

	@Before
	public void setUp() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
		location1 = new GPSCoordinate(-10, 5);
		location2 = new GPSCoordinate(10, 66);

		severity1 = SendableSeverity.BENIGN;
		severity2 = SendableSeverity.SERIOUS;

		description1 = "Overval1";
		description2 = "Overval2";

		armed1 = true;
		armed2 = false;

		inProgress1 = false;
		inProgress2 = true;

		r1 = new Robbery(location1, severity1, description1, armed1, inProgress1);
		r2 = new Robbery(location2, severity2, description2, armed2, inProgress2);

		time = new TimeAdapter(1, 1);
	}

	@Test
	public void testConstructor() {
		robbery1 = new RobberyAdapter(r1);
		robbery2 = new RobberyAdapter(r2, time);

		assertEquals(armed1, robbery1.isArmed());
		assertEquals(armed2, robbery2.isArmed());

		assertEquals(inProgress1, robbery1.isInProgress());
		assertEquals(inProgress2, robbery2.isInProgress());

		assertEquals(location1.getX(), robbery1.getLocation().getX());
		assertEquals(location1.getY(), robbery1.getLocation().getY());
		assertEquals(location2.getX(), robbery2.getLocation().getX());
		assertEquals(location2.getY(), robbery2.getLocation().getY());

		assertEquals(severity1.toString().toLowerCase(), robbery1.getSeverity().toString().toLowerCase());
		assertEquals(severity2.toString().toLowerCase(), robbery2.getSeverity().toString().toLowerCase());

		assertEquals(0, robbery1.getTime().getHours());
		assertEquals(0, robbery1.getTime().getMinutes());
		assertEquals(1, robbery2.getTime().getHours());
		assertEquals(1, robbery2.getTime().getMinutes());

		assertFalse(robbery1.isPartOfDisaster());
		assertFalse(robbery2.isPartOfDisaster());

		assertEquals(0, robbery1.getAssignedUnits().size());
		assertEquals(0, robbery2.getAssignedUnits().size());

		assertEquals("unhandled", robbery1.getState().toString().toLowerCase());
		assertEquals("unhandled", robbery2.getState().toString().toLowerCase());
	}
}
