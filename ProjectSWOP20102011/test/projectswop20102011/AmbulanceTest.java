package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class AmbulanceTest {
	private long x2,y2,x3,y3,speed1,duration1, duration2;
	private String name;
	private GPSCoordinate homeLocation, emergencyLocation;
	private Ambulance ziekenwagen;
	private Emergency f1;
	private long speed2;

    @Before
    public void setUp(){
		x2 = 11;
		y2 = 4;
		x3 = 1302;
		y3 = 2031;
		speed1 = 90;
		speed2 = -96;
		duration1 = 9*3600;
		duration2 = -5653;
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
		assertFalse(ziekenwagen.isAtDestination());
		assertEquals(homeLocation, ziekenwagen.getDestination());
	}

	@Test(expected = InvalidSpeedException.class)
    public void testInValidSpeedConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		ziekenwagen = new Ambulance(name,homeLocation, speed2);
	}

	@Test
	public void testTimeAhead() throws InvalidLocationException, InvalidUnitBuildingNameException,
			InvalidSpeedException, InvalidEmergencyStatusException,
			InvalidEmergencySeverityException, InvalidFireSizeException,
			NumberOutOfBoundsException, InvalidDurationException, InvalidEmergencyException, InvalidAmbulanceException{
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, false, 1337);
		ziekenwagen = new Ambulance(name,homeLocation,speed1);
		Unit[] units = {ziekenwagen};
		f1.getUnitsNeeded().assignUnitsToEmergency(units);
		ziekenwagen.timeAhead(duration1);
		assertEquals(446, ziekenwagen.getCurrentLocation().getX());
		assertEquals(687, ziekenwagen.getCurrentLocation().getY());
		assertEquals(x3, ziekenwagen.getDestination().getX());
		assertEquals(y3, ziekenwagen.getDestination().getY());
	}

	@Test (expected = InvalidDurationException.class)
	public void testInvalidTimeAhead() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, InvalidUnitBuildingNameException, InvalidSpeedException,
			NumberOutOfBoundsException, InvalidEmergencyStatusException, InvalidDurationException, InvalidEmergencyException, InvalidAmbulanceException{
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, false, 1337);
		ziekenwagen = new Ambulance(name, homeLocation, speed1);

		Unit[] units = {ziekenwagen};
		f1.getUnitsNeeded().assignUnitsToEmergency(units);
		ziekenwagen.timeAhead(duration2);
		assertEquals(x2, ziekenwagen.getCurrentLocation().getX());
		assertEquals(y2, ziekenwagen.getCurrentLocation().getY());
		assertEquals(x3, ziekenwagen.getDestination().getX());
		assertEquals(y3, ziekenwagen.getDestination().getY());
	}

	@Test
	public void testAssignTo() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidUnitBuildingNameException,
			InvalidSpeedException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidAmbulanceException{
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, false, 1337);
		ziekenwagen = new Ambulance(name,homeLocation, speed1);

		Unit[] units = {ziekenwagen};
		f1.getUnitsNeeded().assignUnitsToEmergency(units);
		assertEquals(f1.getLocation().getX(), ziekenwagen.getDestination().getX());
		assertEquals(f1.getLocation().getY(), ziekenwagen.getDestination().getY());
		assertTrue(ziekenwagen.isAssigned());
		assertFalse(ziekenwagen.isAtDestination());
		assertEquals(f1, ziekenwagen.getEmergency());
		//TODO working units
		//assertEquals(1, f1.getWorkingUnits());
	}

	@Test
	public void testFinishedJob() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidUnitBuildingNameException, InvalidSpeedException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidAmbulanceException{
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, false, 1337);
		ziekenwagen = new Ambulance(name,homeLocation, speed1);

		Unit[] units = {ziekenwagen};
		f1.getUnitsNeeded().assignUnitsToEmergency(units);
		ziekenwagen.finishedJob();
		assertFalse(ziekenwagen.isAssigned());
		//TODO working units
		//assertEquals(0, f1.getWorkingUnits());
	}
}
