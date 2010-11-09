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
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class Global1Test {

	private long x, y, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, speed1, speed2, speed3, speed4, speed5, speed6, duration1, duration2;
	private String name1, name2, name3, name4, name5, name6, name7;
	private GPSCoordinate homeLocation1, homeLocation2, homeLocation3, homeLocation4, homeLocation5, homeLocation6, emergencyLocation;
	private Ambulance ziekenwagen1, ziekenwagen2, ziekenwagen3, ziekenwagen4, ziekenwagen5;
	private Firetruck brandweerwagen1;
	private Emergency f1;
	private Hospital hospital1;

	@Before
	public void setUp() throws InvalidLocationException, InvalidUnitBuildingNameException {
		x = 1302;
		y = 2031;
		x1 = 11;
		y1 = 4;
		x2 = 123;
		y2 = 789;
		x3 = -46;
		y3 = 4521;
		x4 = 132;
		y4 = 123;
		x5 = 615;
		y5 = 945;
		x6 = 12;
		y6 = -36;

		speed1 = 90;
		speed2 = 100;
		speed3 = 152;
		speed4 = 30;
		speed5 = 45;
		speed6 = 75;

		duration1 = 9 * 3600;
		duration2 = 9513 * 3600;
		name1 = "Ziekenwagen1";
		name2 = "Ziekenwagen2";
		name3 = "Ziekenwagen3";
		name4 = "Ziekenwagen4";
		name5 = "Ziekenwagen5";
		name6 = "Brandweerwagen";
		name7 = "Sjukhus";

		homeLocation1 = new GPSCoordinate(x1, y1);
		homeLocation2 = new GPSCoordinate(x2, y2);
		homeLocation3 = new GPSCoordinate(x3, y3);
		homeLocation4 = new GPSCoordinate(x4, y4);
		homeLocation5 = new GPSCoordinate(x5, y5);
		homeLocation6 = new GPSCoordinate(x6, y6);
		emergencyLocation = new GPSCoordinate(x, y);

		hospital1 = new Hospital(name7, homeLocation6);
	}

	@Test
	public void testAssignToFire() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidUnitBuildingNameException,
			InvalidSpeedException, InvalidEmergencyException, InvalidDurationException {
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, 1, 2);

		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		ziekenwagen3 = new Ambulance(name3, homeLocation3, speed3);
		ziekenwagen4 = new Ambulance(name4, homeLocation4, speed4);
		ziekenwagen5 = new Ambulance(name5, homeLocation5, speed5);

		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6);

		Unit[] units = {ziekenwagen1, ziekenwagen2, ziekenwagen3, brandweerwagen1};
		f1.getUnitsNeeded().assignUnitsToEmergency(units);

		assertFalse(ziekenwagen1.isAtDestination());

		ziekenwagen1.timeAhead(duration1);
		assertEquals(446, ziekenwagen1.getCurrentLocation().getX());
		assertEquals(687, ziekenwagen1.getCurrentLocation().getY());
		assertEquals(x, ziekenwagen1.getDestination().getX());
		assertEquals(y, ziekenwagen1.getDestination().getY());

		assertEquals(f1, ziekenwagen1.getEmergency());
		assertEquals(f1.getLocation().getX(), ziekenwagen1.getDestination().getX());
		assertEquals(f1.getLocation().getY(), ziekenwagen1.getDestination().getY());
		assertTrue(ziekenwagen1.isAssigned());
	}

	@Test
	public void testFinishedJob() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidUnitBuildingNameException,
			InvalidSpeedException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidDurationException {
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, 0, 1337);
		//ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);

		Unit[] units = new Unit[1338];
		int aantal = units.length;
		units[0] = new Firetruck("Naam", homeLocation1, 100);
		for (int i = 1; i < aantal; i++) {
			units[i] = new Ambulance("Naam", homeLocation2, 100);
		}
		f1.getUnitsNeeded().assignUnitsToEmergency(units);

		assertEquals(1338, f1.getUnitsNeeded().getWorkingUnits());

		for (int i = 0; i < aantal; i++) {
			units[i].timeAhead(1000000000);
		}
		for (int i = 0; i < aantal; i++) {
			units[i].finishedJob();
		}
		for (int i = 0; i < aantal; i++) {
			assertFalse(units[i].isAssigned());
		}
	}

	@Test
	public void testSelectHospital() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidUnitBuildingNameException, InvalidSpeedException, InvalidEmergencyException, InvalidDurationException, InvalidAmbulanceException, InvalidHospitalException {
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, 1, 2);

		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		ziekenwagen3 = new Ambulance(name3, homeLocation3, speed3);
		ziekenwagen4 = new Ambulance(name4, homeLocation4, speed4);
		ziekenwagen5 = new Ambulance(name5, homeLocation5, speed5);

		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6);

		Unit[] units = {ziekenwagen1, ziekenwagen2, ziekenwagen3, brandweerwagen1};
		f1.getUnitsNeeded().assignUnitsToEmergency(units);

		assertFalse(ziekenwagen1.isAtDestination());

		ziekenwagen1.timeAhead(duration2);
		ziekenwagen1.selectHospital(hospital1);
		ziekenwagen1.timeAhead(duration2);

		assertEquals(x6, ziekenwagen1.getCurrentLocation().getX());
		assertEquals(y6, ziekenwagen1.getCurrentLocation().getY());
	}
}
