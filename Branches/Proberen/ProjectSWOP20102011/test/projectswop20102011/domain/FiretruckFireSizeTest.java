package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FiretruckFireSizeTest {

	private long x1, x2, y1, y2, speed1, duration;
	private String name;
	private GPSCoordinate homeLocation, emergencyLocation;
	private Firetruck brandweerwagen;
	private Emergency f1;
	private FireSize fs1, fs2, fs3;

	@Before
	public void setUp() {
		x1 = -21;
		y1 = 120;
		x2 = 19;
		y2 = 90;
		speed1 = 5 * 3600;
		duration = 111;
		name = "Brandweerwagen";
		emergencyLocation = new GPSCoordinate(x1, y1);
		homeLocation = new GPSCoordinate(x2, y2);
		fs1 = FireSize.FACILITY;
		fs2 = FireSize.HOUSE;
		fs3 = FireSize.LOCAL;
	}

	@Test
	public void testValidConstructor() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException {
		brandweerwagen = new Firetruck(name, homeLocation, speed1, fs1);
		assertEquals(name, brandweerwagen.getName());
		assertEquals(x2, brandweerwagen.getHomeLocation().getX());
		assertEquals(y2, brandweerwagen.getHomeLocation().getY());
		assertEquals(speed1, brandweerwagen.getSpeed());
		assertEquals(x2, brandweerwagen.getCurrentLocation().getX());
		assertEquals(y2, brandweerwagen.getCurrentLocation().getY());
		assertFalse(brandweerwagen.isAssigned());
	}

	@Test
	public void testTimeAhead() throws InvalidLocationException, InvalidMapItemNameException,
			InvalidSpeedException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyStatusException, InvalidDurationException, InvalidEmergencyException, Exception {
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 0, 0);
		brandweerwagen = new Firetruck(name, homeLocation, speed1, fs1);

		HashSet<Unit> units = new HashSet<Unit>();
		units.add(brandweerwagen);
		f1.assignUnits(units);

		brandweerwagen.timeAhead(duration);
		assertEquals(x1, brandweerwagen.getCurrentLocation().getX());
		assertEquals(y1, brandweerwagen.getCurrentLocation().getY());
		assertEquals(x1, brandweerwagen.getDestination().getX());
		assertEquals(y1, brandweerwagen.getDestination().getY());
	}

	@Test(expected = InvalidDurationException.class)
	public void testInvalidTimeAhead() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, InvalidMapItemNameException, InvalidSpeedException,
			NumberOutOfBoundsException, InvalidEmergencyStatusException, InvalidDurationException, InvalidEmergencyException, InvalidAmbulanceException, Exception {
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 0, 0);
		brandweerwagen = new Firetruck(name, homeLocation, speed1, fs1);

		HashSet<Unit> units = new HashSet<Unit>();
		units.add(brandweerwagen);
		f1.assignUnits(units);
		brandweerwagen.timeAhead(-23);
		assertEquals(x2, brandweerwagen.getCurrentLocation().getX());
		assertEquals(y2, brandweerwagen.getCurrentLocation().getY());
		assertEquals(x1, brandweerwagen.getDestination().getX());
		assertEquals(y1, brandweerwagen.getDestination().getY());
	}

	@Test(expected = InvalidFireSizeException.class)
	public void testInvalidMaxSize() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException {
		brandweerwagen = new Firetruck(name, homeLocation, speed1, null);
	}

	@Test
	public void testValidMaxSize() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException {
		brandweerwagen = new Firetruck(name, homeLocation, speed1, fs1);
		assertEquals(fs1, brandweerwagen.getMaxSize());
		brandweerwagen = new Firetruck(name, homeLocation, speed1, fs2);
		assertEquals(fs2, brandweerwagen.getMaxSize());
		brandweerwagen = new Firetruck(name, homeLocation, speed1, fs3);
		assertEquals(fs3, brandweerwagen.getMaxSize());
	}
}
