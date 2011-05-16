package projectswop20102011.externalsystem.adapters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class PublicDisturbanceAdapterTest {

	private PublicDisturbance pd1, pd2;
	private PublicDisturbanceAdapter publicDisturbance1, publicDisturbance2;
	private GPSCoordinate location1, location2;
	private EmergencySeverity severity1, severity2;
	private String description1, description2;
	private long numberOfPeople1, numberOfPeople2;
	private TimeAdapter time;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
		location1 = new GPSCoordinate(-10, 5);
		location2 = new GPSCoordinate(10, 66);

		severity1 = EmergencySeverity.BENIGN;
		severity2 = EmergencySeverity.SERIOUS;

		description1 = "Brand1";
		description2 = "Brand2";

		numberOfPeople1 = 5;
		numberOfPeople2 = 1;

		pd1 = new PublicDisturbance(location1, severity1, description1, numberOfPeople1);
		pd2 = new PublicDisturbance(location2, severity2, description2, numberOfPeople2);

		time = new TimeAdapter(1, 1);
	}

	@Test
	public void testConstructor() {
		publicDisturbance1 = new PublicDisturbanceAdapter(pd1);
		publicDisturbance2 = new PublicDisturbanceAdapter(pd2, time);

		assertEquals(numberOfPeople1, publicDisturbance1.getNumberOfPeople());
		assertEquals(numberOfPeople2, publicDisturbance2.getNumberOfPeople());

		assertEquals(location1.getX(), publicDisturbance1.getLocation().getX());
		assertEquals(location1.getY(), publicDisturbance1.getLocation().getY());
		assertEquals(location2.getX(), publicDisturbance2.getLocation().getX());
		assertEquals(location2.getY(), publicDisturbance2.getLocation().getY());

		assertEquals(severity1.toString().toLowerCase(), publicDisturbance1.getSeverity().toString().toLowerCase());
		assertEquals(severity2.toString().toLowerCase(), publicDisturbance2.getSeverity().toString().toLowerCase());

		assertEquals(0, publicDisturbance1.getTime().getHours());
		assertEquals(0, publicDisturbance1.getTime().getMinutes());
		assertEquals(1, publicDisturbance2.getTime().getHours());
		assertEquals(1, publicDisturbance2.getTime().getMinutes());

		assertFalse(publicDisturbance1.isPartOfDisaster());
		assertFalse(publicDisturbance2.isPartOfDisaster());

		assertEquals(0, publicDisturbance1.getAssignedUnits().size());
		assertEquals(0, publicDisturbance2.getAssignedUnits().size());

		assertEquals("unhandled", publicDisturbance1.getState().toString().toLowerCase());
		assertEquals("unhandled", publicDisturbance2.getState().toString().toLowerCase());
	}
}
