package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class FiretruckTest {
	private long x1,x2,y1,y2,speed1,duration;
	private String name;
	private GPSCoordinate homeLocation, emergencyLocation;
	private Firetruck brandweerwagen;
	private Emergency f1;

    @Before
    public void setUp(){
		x1 = -21;
		y1 = 120;
		x2 = 19;
		y2 = 90;
		speed1 = 5*3600;
		duration = 111;
		name = "Brandweerwagen";
		emergencyLocation = new GPSCoordinate(x1, y1);
		homeLocation = new GPSCoordinate(x2,y2);

	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		brandweerwagen = new Firetruck(name,homeLocation, speed1);
		assertEquals(name,brandweerwagen.getName());
		assertEquals(x2,brandweerwagen.getHomeLocation().getX());
		assertEquals(y2,brandweerwagen.getHomeLocation().getY());
		assertEquals(speed1,brandweerwagen.getSpeed());
		assertEquals(x2,brandweerwagen.getCurrentLocation().getX());
		assertEquals(y2,brandweerwagen.getCurrentLocation().getY());
		assertFalse(brandweerwagen.isAssigned());
	}

	@Test
	public void testTimeAhead() throws InvalidLocationException, InvalidUnitBuildingNameException,
			InvalidSpeedException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyStatusException{
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, false, 1337);
		brandweerwagen = new Firetruck(name,homeLocation,speed1);
		brandweerwagen.assignTo(f1);
		brandweerwagen.timeAhead(duration);
		assertEquals(x1,brandweerwagen.getCurrentLocation().getX());
		assertEquals(y1,brandweerwagen.getCurrentLocation().getY());
		assertEquals(x1,brandweerwagen.getDestination().getX());
		assertEquals(y1,brandweerwagen.getDestination().getY());
	}
}
