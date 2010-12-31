package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.TrafficAccident;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidParametersException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class TrafficAccidentFactoryTest {

	private long x1;
	private long y1;
	private GPSCoordinate gps1, gps2;
	private EmergencySeverity severity1, severity2;
	private String description1, description2;
	private long numberOfCars1;
	private long numberOfInjured1;
	private TrafficAccidentFactory taf;

	@Before
	public void setUp() {
		x1 = 10;
		y1 = 10;

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = null;
		severity1 = EmergencySeverity.NORMAL;
		severity2 = null;
		description1 = "Diefstal";
		description2 = null;
		numberOfCars1 = 10;
		numberOfInjured1 = 2;
	}

	@Test
	public void testConstructor() throws InvalidEmergencyTypeNameException {
		taf = new TrafficAccidentFactory();
	}

	@Test
	public void testCreateEmergency() throws InvalidEmergencyTypeNameException, InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException, InvalidParametersException{
		taf = new TrafficAccidentFactory();
		TrafficAccident ta = (TrafficAccident) taf.createInstance(new Object[]{gps1, severity1, description1, numberOfCars1, numberOfInjured1});

		assertEquals(gps1, ta.getLocation());
		assertEquals(severity1, ta.getSeverity());
		assertEquals(description1, ta.getDescription());
		assertEquals(numberOfCars1, ta.getNumberOfCars());
		assertEquals(numberOfInjured1, ta.getNumberOfInjured());
	}

	@Test(expected = InvalidParametersException.class)
	public void testIllegalCreateEmergency() throws InvalidEmergencyTypeNameException, InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException, InvalidParametersException {
		taf = new TrafficAccidentFactory();
		taf.createInstance(new Object[]{});
	}

	@Test
	public void testAreValidParameters() throws InvalidEmergencyTypeNameException {
		taf = new TrafficAccidentFactory();
		assertTrue(taf.areValidParameters(new Object[]{gps1, severity1, description1, numberOfCars1, numberOfInjured1}));
		assertFalse(taf.areValidParameters(new Object[]{gps2, severity1, description1, numberOfCars1, numberOfInjured1}));
		assertFalse(taf.areValidParameters(new Object[]{gps1, severity2, description1, numberOfCars1, numberOfInjured1}));
		assertFalse(taf.areValidParameters(new Object[]{gps1, severity1, description2, numberOfCars1, numberOfInjured1}));
	}

	@Test
	public void testGetEmergencyTypeName() throws InvalidEmergencyTypeNameException {
		taf = new TrafficAccidentFactory();

		assertEquals("traffic accident", taf.getName());
	}
}
