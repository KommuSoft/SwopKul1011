package projectswop20102011.domain;

import java.util.HashSet;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
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
	private long capacity1,capacity2,capacity3,capacity4;

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
		capacity1 = 100;
		capacity2 = 0;
		capacity3 = 1000;
		capacity4 = -10;
	}

	@Test
	public void testValidConstructor() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException{
		brandweerwagen = new Firetruck(name, homeLocation, speed1, capacity1);
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
			InvalidSpeedException, InvalidSendableSeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidSendableStatusException, InvalidDurationException, InvalidEmergencyException, InvalidCapacityException{
		f1 = new Fire(emergencyLocation, SendableSeverity.URGENT, "", FireSize.LOCAL, false, 0, 0);
		brandweerwagen = new Firetruck(name, homeLocation, speed1, capacity1);

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
	public void testInvalidTimeAhead() throws InvalidLocationException, InvalidSendableSeverityException,
			InvalidMapItemNameException, InvalidSpeedException,
			NumberOutOfBoundsException, InvalidSendableStatusException, InvalidDurationException, InvalidEmergencyException, InvalidAmbulanceException, InvalidFireSizeException, InvalidCapacityException{
		f1 = new Fire(emergencyLocation, SendableSeverity.URGENT, "", FireSize.LOCAL, false, 0, 0);
		brandweerwagen = new Firetruck(name, homeLocation, speed1, capacity1);

		HashSet<Unit> units = new HashSet<Unit>();
		units.add(brandweerwagen);
		f1.assignUnits(units);
		brandweerwagen.timeAhead(-23);
		assertEquals(x2, brandweerwagen.getCurrentLocation().getX());
		assertEquals(y2, brandweerwagen.getCurrentLocation().getY());
		assertEquals(x1, brandweerwagen.getDestination().getX());
		assertEquals(y1, brandweerwagen.getDestination().getY());
	}

	@Test(expected = InvalidCapacityException.class)
	public void testInvalidCapacity() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException {
		brandweerwagen = new Firetruck(name, homeLocation, speed1, capacity4);
	}

	@Test
	public void testValidCapacity() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException{
		brandweerwagen = new Firetruck(name, homeLocation, speed1, capacity1);
		assertEquals(capacity1, brandweerwagen.getCapacity());
		brandweerwagen = new Firetruck(name, homeLocation, speed1, capacity2);
		assertEquals(capacity2, brandweerwagen.getCapacity());
		brandweerwagen = new Firetruck(name, homeLocation, speed1, capacity3);
		assertEquals(capacity3, brandweerwagen.getCapacity());
	}
}
