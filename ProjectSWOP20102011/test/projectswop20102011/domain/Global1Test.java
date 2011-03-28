package projectswop20102011.domain;

import java.util.Iterator;
import projectswop20102011.domain.lists.MapItemList;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class Global1Test {

	private long x, y, x1, y1, x2, y2, x3, y3, x4, y4, x5, y5, x6, y6, speed1, speed2, speed3, speed4, speed5, speed6, duration1, duration2, duration3;
	private String name1, name2, name3, name4, name5, name6, name7, name8;
	private GPSCoordinate homeLocation1, homeLocation2, homeLocation3, homeLocation4, homeLocation5, homeLocation6, emergencyLocation;
	private Ambulance ziekenwagen1, ziekenwagen2, ziekenwagen3, ziekenwagen4, ziekenwagen5;
	private Firetruck brandweerwagen1;
	private Policecar politiewagen1;
	private Emergency e1;
	private Hospital hospital1;
	private MapItemList<Unit> mapitemList;
	private boolean armed1, armed2;
	private boolean inProgress1, inProgress2;
	private long numberOfPeople1;
	private long capacity;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException {
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
		duration3 = 100;

		name1 = "Ziekenwagen1";
		name2 = "Ziekenwagen2";
		name3 = "Ziekenwagen3";
		name4 = "Ziekenwagen4";
		name5 = "Ziekenwagen5";
		name6 = "Brandweerwagen";
		name7 = "Sjukhus";
		name8 = "Politiewagen1";

		homeLocation1 = new GPSCoordinate(x1, y1);
		homeLocation2 = new GPSCoordinate(x2, y2);
		homeLocation3 = new GPSCoordinate(x3, y3);
		homeLocation4 = new GPSCoordinate(x4, y4);
		homeLocation5 = new GPSCoordinate(x5, y5);
		homeLocation6 = new GPSCoordinate(x6, y6);
		emergencyLocation = new GPSCoordinate(x, y);

		hospital1 = new Hospital(name7, homeLocation6);

		mapitemList = new MapItemList<Unit>();

		armed1 = false;
		armed2 = true;
		inProgress1 = false;
		inProgress2 = true;

		numberOfPeople1 = 2;

		capacity = 999;
	}

	@Test(expected = InvalidEmergencyException.class)
	public void testInvalidAssignToFire() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException,
			InvalidSpeedException, InvalidEmergencyException, InvalidDurationException, InvalidEmergencyStatusException, Exception {
		e1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 1, 2);

		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		ziekenwagen3 = new Ambulance(name3, homeLocation3, speed3);
		ziekenwagen4 = new Ambulance(name4, homeLocation4, speed4);
		ziekenwagen5 = new Ambulance(name5, homeLocation5, speed5);

		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6, capacity);
		Set<Unit> units = new LinkedHashSet<Unit>();
		units.add(ziekenwagen1);
		units.add(ziekenwagen2);
		units.add(ziekenwagen3);
		units.add(ziekenwagen4);
		units.add(ziekenwagen5);
		e1.assignUnits(units);

		assertFalse(ziekenwagen1.isAtDestination());

		ziekenwagen1.timeAhead(duration1);
		assertEquals(446, ziekenwagen1.getCurrentLocation().getX());
		assertEquals(687, ziekenwagen1.getCurrentLocation().getY());
		assertEquals(x, ziekenwagen1.getDestination().getX());
		assertEquals(y, ziekenwagen1.getDestination().getY());

		assertEquals(e1, ziekenwagen1.getEmergency());
		assertEquals(e1.getLocation().getX(), ziekenwagen1.getDestination().getX());
		assertEquals(e1.getLocation().getY(), ziekenwagen1.getDestination().getY());
		assertTrue(ziekenwagen1.isAssigned());
	}

	@Test
	public void testValidAssignToFire() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException,
			InvalidSpeedException, InvalidEmergencyException, InvalidDurationException, InvalidEmergencyStatusException, Exception {
		e1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 1, 2);

		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		ziekenwagen3 = new Ambulance(name3, homeLocation3, speed3);
		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6, capacity);
		Set<Unit> units = new LinkedHashSet<Unit>();
		units.add(ziekenwagen1);
		units.add(ziekenwagen2);
		units.add(ziekenwagen3);
		e1.assignUnits(units);

		assertFalse(ziekenwagen1.isAtDestination());

		ziekenwagen1.timeAhead(duration1);
		assertEquals(446, ziekenwagen1.getCurrentLocation().getX());
		assertEquals(687, ziekenwagen1.getCurrentLocation().getY());
		assertEquals(x, ziekenwagen1.getDestination().getX());
		assertEquals(y, ziekenwagen1.getDestination().getY());

		assertEquals(e1, ziekenwagen1.getEmergency());
		assertEquals(e1.getLocation().getX(), ziekenwagen1.getDestination().getX());
		assertEquals(e1.getLocation().getY(), ziekenwagen1.getDestination().getY());
		assertTrue(ziekenwagen1.isAssigned());
	}

	@Test
	public void testNormalWithdrawUnits() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException,
			InvalidSpeedException, InvalidEmergencyException, InvalidDurationException, InvalidWithdrawalException, InvalidEmergencyStatusException, Exception {
		//Brand aanmaken + alle units
		e1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 1, 2);
		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		ziekenwagen3 = new Ambulance(name3, homeLocation3, speed3);
		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6, capacity);

		//units toevoegen aan de mapitemlist
		mapitemList.addMapItem(ziekenwagen1);
		mapitemList.addMapItem(ziekenwagen2);
		mapitemList.addMapItem(ziekenwagen3);
		mapitemList.addMapItem(brandweerwagen1);

		//We wijzen de units toe aan een emergency
		Set<Unit> units = new LinkedHashSet<Unit>();
		units.add(ziekenwagen1);
		units.add(ziekenwagen2);
		units.add(ziekenwagen3);
		units.add(brandweerwagen1);
		e1.assignUnits((Set<Unit>) units);

		//We spoelen de tijd door
		ziekenwagen1.timeAhead(duration3);
		ziekenwagen2.timeAhead(duration3);
		ziekenwagen3.timeAhead(duration3);
		brandweerwagen1.timeAhead(duration3);

		//We veronderstellen dat de units nog niet op hun bestemming zijn,
		//aangezien het tijdsinterval heel klein werd gekozen.
		assertFalse(ziekenwagen1.isAtDestination());
		assertFalse(ziekenwagen2.isAtDestination());
		assertFalse(ziekenwagen3.isAtDestination());
		assertFalse(brandweerwagen1.isAtDestination());

		//We veronderstellen dus ook dat de units nog niet op de plaats van het ongeval geweest zijn
		assertFalse(ziekenwagen1.wasAlreadyAtSite());
		assertFalse(ziekenwagen2.wasAlreadyAtSite());
		assertFalse(ziekenwagen3.wasAlreadyAtSite());
		assertFalse(brandweerwagen1.wasAlreadyAtSite());

		//We trekken nu twee units terug
		String[] unitNames = {ziekenwagen1.getName(), ziekenwagen2.getName()};
		for (int i = 0; i < unitNames.length; i++) {
			((Unit) mapitemList.getMapItemFromName(unitNames[i])).withdraw();
		}

		//Nog steeds is geen enkele unit op de plaats van het ongeval geweest
		assertFalse(ziekenwagen1.wasAlreadyAtSite());
		assertFalse(ziekenwagen2.wasAlreadyAtSite());
		assertFalse(ziekenwagen3.wasAlreadyAtSite());
		assertFalse(brandweerwagen1.wasAlreadyAtSite());

		//Enkel de eerste 2 units kunnen weer aan een emergency toegewezen worden
		assertTrue(ziekenwagen1.canBeAssigned());
		assertTrue(ziekenwagen2.canBeAssigned());
		assertFalse(ziekenwagen3.canBeAssigned());
		assertFalse(brandweerwagen1.canBeAssigned());

		//we veronderstellen dat er nog 4 units zijn toegewezen
		assertEquals(2, e1.getWorkingUnits().size());

		//
		ziekenwagen1.timeAhead(duration2);
		ziekenwagen2.timeAhead(duration2);
		ziekenwagen3.timeAhead(duration2);
		brandweerwagen1.timeAhead(duration2);

		//We veronderstellen nu (door het grote tijdsinterval) dat de 4 units
		//reeds op de plaats van het ongeval zijn geweest
		assertFalse(ziekenwagen1.wasAlreadyAtSite());
		assertFalse(ziekenwagen2.wasAlreadyAtSite());
		assertTrue(ziekenwagen3.wasAlreadyAtSite());
		assertTrue(brandweerwagen1.wasAlreadyAtSite());

		//We spoelen de tijd nog een beetje door
		ziekenwagen3.timeAhead(duration2);
		brandweerwagen1.timeAhead(duration2);

		//We veronderstellen nog steeds dat de 4 units al op de plaats van het ongeval zijn geweest
		assertFalse(ziekenwagen1.wasAlreadyAtSite());
		assertFalse(ziekenwagen2.wasAlreadyAtSite());
		assertTrue(ziekenwagen3.wasAlreadyAtSite());
		assertTrue(brandweerwagen1.wasAlreadyAtSite());



	}

	@Test(expected = InvalidWithdrawalException.class)
	public void testNotWithdrawUnits() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException,
			InvalidSpeedException, InvalidEmergencyException, InvalidDurationException, InvalidWithdrawalException, InvalidEmergencyStatusException, Exception {
		e1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 0, 2);
		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6, capacity);

		mapitemList.addMapItem(ziekenwagen1);
		mapitemList.addMapItem(ziekenwagen2);
		mapitemList.addMapItem(brandweerwagen1);

		Set<Unit> units = new LinkedHashSet<Unit>();
		units.add(ziekenwagen1);
		units.add(ziekenwagen2);
		units.add(brandweerwagen1);
		e1.assignUnits((Set<Unit>) units);
		ziekenwagen1.timeAhead(duration3);
		ziekenwagen2.timeAhead(duration3);
		brandweerwagen1.timeAhead(duration3);

		assertFalse(ziekenwagen1.isAtDestination());
		assertFalse(ziekenwagen2.isAtDestination());
		assertFalse(brandweerwagen1.isAtDestination());

		assertFalse(ziekenwagen1.wasAlreadyAtSite());
		assertFalse(ziekenwagen2.wasAlreadyAtSite());
		assertFalse(brandweerwagen1.wasAlreadyAtSite());

		String[] unitNames = {brandweerwagen1.getName()};

		for (int i = 0; i < unitNames.length; i++) {
			((Unit) mapitemList.getMapItemFromName(unitNames[i])).withdraw();
		}
	}

	@Test(expected = InvalidWithdrawalException.class)
	public void testInvalidNormalWithdrawUnits() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException,
			InvalidSpeedException, InvalidEmergencyException, InvalidDurationException, InvalidWithdrawalException, InvalidEmergencyStatusException, Exception {
		e1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 1, 2);
		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6, capacity);

		mapitemList.addMapItem(ziekenwagen1);
		mapitemList.addMapItem(ziekenwagen2);
		mapitemList.addMapItem(brandweerwagen1);

		Set<Unit> units = new LinkedHashSet<Unit>();
		units.add(ziekenwagen1);
		units.add(ziekenwagen2);
		units.add(brandweerwagen1);

		e1.assignUnits(units);
		ziekenwagen1.timeAhead(duration2);
		ziekenwagen2.timeAhead(duration2);
		brandweerwagen1.timeAhead(duration2);

		assertTrue(ziekenwagen1.isAtDestination());
		assertTrue(ziekenwagen2.isAtDestination());
		assertTrue(brandweerwagen1.isAtDestination());

		String[] unitNames = {ziekenwagen1.getName(), ziekenwagen2.getName()};
		for (int i = 0; i < unitNames.length; i++) {
			((Unit) mapitemList.getMapItemFromName(unitNames[i])).withdraw();
		}
	}

	@Test
	public void testFinishedJob() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException,
			InvalidSpeedException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidDurationException,
			InvalidAmbulanceException, InvalidHospitalException, Exception {
		e1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 0, 1337);

		Set<Unit> units = new LinkedHashSet<Unit>(1338);
		int aantal = 1338;
		units.add(new Firetruck("Naam", homeLocation1, 100, capacity));
		for (int i = 1; i < aantal; i++) {
			units.add(new Ambulance("Naam", homeLocation2, 100));
		}

		e1.assignUnits(units);
		assertEquals(1338, e1.getWorkingUnits().size());

		for(Unit u : units) {
			u.timeAhead(1000000000);
		}
		
		Iterator<Unit> unitsIterator = units.iterator();
		unitsIterator.next();
		while(unitsIterator.hasNext()){
			((Ambulance) unitsIterator.next()).selectHospital(hospital1);
		}

		for(Unit u : units) {
			u.timeAhead(1000000000);
		}

		for(Unit u : units) {
			u.finishedJob();
		}

		for(Unit u : units) {
			assertFalse(u.isAssigned());
		}
	}

	@Test
	public void testSelectHospital() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidDurationException, InvalidAmbulanceException, InvalidHospitalException, InvalidEmergencyStatusException, Exception {
		e1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 1, 2);

		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		ziekenwagen3 = new Ambulance(name3, homeLocation3, speed3);
		ziekenwagen4 = new Ambulance(name4, homeLocation4, speed4);
		ziekenwagen5 = new Ambulance(name5, homeLocation5, speed5);

		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6, capacity);

		Set<Unit> units = new LinkedHashSet<Unit>();
		units.add(ziekenwagen1);
		units.add(ziekenwagen2);
		units.add(ziekenwagen3);
		units.add(brandweerwagen1);
		e1.assignUnits(units);

		assertFalse(ziekenwagen1.isAtDestination());

		ziekenwagen1.timeAhead(duration2);
		ziekenwagen1.selectHospital(hospital1);
		ziekenwagen1.timeAhead(duration2);

		assertEquals(x6, ziekenwagen1.getCurrentLocation().getX());
		assertEquals(y6, ziekenwagen1.getCurrentLocation().getY());
	}

	@Test
	public void testRobberyUnitsNeeded() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException{
		e1 = new Robbery(emergencyLocation, EmergencySeverity.URGENT, "", armed1, inProgress1);

		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6, capacity);
		politiewagen1 = new Policecar(name8, homeLocation3, speed3);

		Set<Unit> units = new LinkedHashSet<Unit>();
		units.add(ziekenwagen1);
		units.add(ziekenwagen2);
		units.add(brandweerwagen1);

		assertFalse(e1.canAssignUnits(units));

		units.add(politiewagen1);
		assertFalse(e1.canAssignUnits(units));
	}

	@Test
	public void testPublicDisturbanceUnitsNeeded() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException{
		e1 = new PublicDisturbance(emergencyLocation, EmergencySeverity.URGENT, "", numberOfPeople1);

		ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		ziekenwagen2 = new Ambulance(name2, homeLocation2, speed2);
		brandweerwagen1 = new Firetruck(name6, homeLocation6, speed6, capacity);
		politiewagen1 = new Policecar(name8, homeLocation3, speed3);

		Set<Unit> units = new LinkedHashSet<Unit>();
		units.add(ziekenwagen1);
		units.add(ziekenwagen2);
		units.add(brandweerwagen1);

		assertFalse(e1.canAssignUnits(units));

		units.add(politiewagen1);
		assertFalse(e1.canAssignUnits(units));
	}
}
