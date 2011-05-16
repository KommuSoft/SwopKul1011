package projectswop20102011.externalsystem.adapters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.TrafficAccident;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class TrafficAccidentAdapterTest {

	private TrafficAccident ta1, ta2;
	private TrafficAccidentAdapter trafficAccident1, trafficAccident2;
	private GPSCoordinate location1, location2;
	private EmergencySeverity severity1, severity2;
	private String description1, description2;
	private long numberOfCars1, numberOfCars2;
	private long numberOfInjured1, numberOfInjured2;
	private TimeAdapter time;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
		location1 = new GPSCoordinate(-10, 5);
		location2 = new GPSCoordinate(10, 66);

		severity1 = EmergencySeverity.BENIGN;
		severity2 = EmergencySeverity.SERIOUS;

		description1 = "Ongeval1";
		description2 = "Ongeval2";

		numberOfCars1 = 5;
		numberOfCars2 = 0;

		numberOfInjured1 = 0;
		numberOfInjured2 = 96;

		ta1 = new TrafficAccident(location1, severity1, description1, numberOfCars1, numberOfInjured1);
		ta2 = new TrafficAccident(location2, severity2, description2, numberOfCars2, numberOfInjured2);

		time = new TimeAdapter(1, 1);
	}

	@Test
	public void testConstructor() {
		trafficAccident1 = new TrafficAccidentAdapter(ta1);
		trafficAccident2 = new TrafficAccidentAdapter(ta2, time);

		assertEquals(numberOfCars1, trafficAccident1.getNumberOfCars());
		assertEquals(numberOfCars2, trafficAccident2.getNumberOfCars());

		assertEquals(numberOfInjured1, trafficAccident1.getNumberOfInjured());
		assertEquals(numberOfInjured2, trafficAccident2.getNumberOfInjured());

		assertEquals(location1.getX(), trafficAccident1.getLocation().getX());
		assertEquals(location1.getY(), trafficAccident1.getLocation().getY());
		assertEquals(location2.getX(), trafficAccident2.getLocation().getX());
		assertEquals(location2.getY(), trafficAccident2.getLocation().getY());

		assertEquals(severity1.toString().toLowerCase(), trafficAccident1.getSeverity().toString().toLowerCase());
		assertEquals(severity2.toString().toLowerCase(), trafficAccident2.getSeverity().toString().toLowerCase());

		assertEquals(0, trafficAccident1.getTime().getHours());
		assertEquals(0, trafficAccident1.getTime().getMinutes());
		assertEquals(1, trafficAccident2.getTime().getHours());
		assertEquals(1, trafficAccident2.getTime().getMinutes());

		assertFalse(trafficAccident1.isPartOfDisaster());
		assertFalse(trafficAccident2.isPartOfDisaster());

		assertEquals(0, trafficAccident1.getAssignedUnits().size());
		assertEquals(0, trafficAccident2.getAssignedUnits().size());

		assertEquals("unhandled", trafficAccident1.getState().toString().toLowerCase());
		assertEquals("unhandled", trafficAccident2.getState().toString().toLowerCase());
	}
}
