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

public class AmbulanceTest {
	private long x2,y2,x3,y3,speed1,duration;
	private String name;
	private GPSCoordinate homeLocation, emergencyLocation;
	private Ambulance ziekenwagen;
	private Emergency f1;


    @Before
    public void setUp(){
		x2 = 11;
		y2 = 4;
		x3 = 1302;
		y3 = 2031;
		speed1 = 90;
		duration = 9*3600;
		name = "Ziekenwagen";
		homeLocation = new GPSCoordinate(x2,y2);
		emergencyLocation = new GPSCoordinate(x3, y3);
	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		ziekenwagen = new Ambulance(name,homeLocation, speed1);
		assertEquals(name,ziekenwagen.getName());
		assertEquals(x2,ziekenwagen.getHomeLocation().getX());
		assertEquals(y2,ziekenwagen.getHomeLocation().getY());
		assertEquals(speed1,ziekenwagen.getSpeed());
		assertEquals(x2,ziekenwagen.getCurrentLocation().getX());
		assertEquals(y2,ziekenwagen.getCurrentLocation().getY());
		assertFalse(ziekenwagen.isAssigned());
	}

	@Test
	public void testTimeAhead() throws InvalidLocationException, InvalidUnitBuildingNameException,
			InvalidSpeedException, InvalidEmergencyStatusException,
			InvalidEmergencySeverityException, InvalidFireSizeException,
			NumberOutOfBoundsException{
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, false, 1337);
		ziekenwagen = new Ambulance(name,homeLocation,speed1);
		ziekenwagen.assignTo(f1);
		ziekenwagen.timeAhead(duration);
		assertEquals(446,ziekenwagen.getCurrentLocation().getX());
		assertEquals(687,ziekenwagen.getCurrentLocation().getY());
		assertEquals(x3,ziekenwagen.getDestination().getX());
		assertEquals(y3,ziekenwagen.getDestination().getY());
	}

	@Test
	public void testAssignTo() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidUnitBuildingNameException,
			InvalidSpeedException, InvalidEmergencyStatusException{
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, false, 1337);
		ziekenwagen = new Ambulance(name,homeLocation, speed1);
		ziekenwagen.assignTo(f1);
		assertEquals(f1.getLocation().getX(), ziekenwagen.getDestination().getX());
		assertEquals(f1.getLocation().getY(), ziekenwagen.getDestination().getY());
	}
}
