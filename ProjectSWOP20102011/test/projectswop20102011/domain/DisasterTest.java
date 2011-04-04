package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidEmergencyException;

import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class DisasterTest {

	private Emergency e1, e2, e3, e4, e5, e6, e7, e8;
	private GPSCoordinate l1, l2;
	private long x1, y1, x2, y2;
	private boolean armed1;
	private boolean inProgress1;
	private String description1;
	private Disaster d;
	private long speed1;
	private GPSCoordinate homeLocation1;
	private String name1;
	private long capacity1;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		x1 = 12;
		y1 = 789;
		x2 = 95;
		y2 = 65;
		l1 = new GPSCoordinate(x1, y1);
		l2 = new GPSCoordinate(x2, y2);
		armed1 = true;
		inProgress1 = false;

		e1 = new Robbery(l1, EmergencySeverity.BENIGN, "", armed1, inProgress1);
		e2 = new PublicDisturbance(l2, EmergencySeverity.BENIGN, "", 6);
		e3 = new Fire(l1, EmergencySeverity.NORMAL, "", FireSize.LOCAL, false, 0, 1337);
		e4 = new PublicDisturbance(l2, EmergencySeverity.NORMAL, "", 1302);
		e5 = new Fire(l1, EmergencySeverity.SERIOUS, "", FireSize.LOCAL, false, 0, 1337);
		e6 = new PublicDisturbance(l2, EmergencySeverity.SERIOUS, "", 1302);
		e7 = new Fire(l1, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 0, 1337);
		e8 = new PublicDisturbance(l2, EmergencySeverity.URGENT, "", 1302);

		description1 = "A couple of emergencies";

		speed1 = 10;
		homeLocation1 = new GPSCoordinate(10, 10);
		name1 = "Unit1";
		capacity1 = 2000;
	}

	@Test
	public void testValidEmergencies() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		d = new Disaster(emergencies, description1);
		emergencies.clear();

		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
	}

	@Test(expected = InvalidEmergencyException.class)
	public void testInValidEmergencies() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		d = new Disaster(emergencies, description1);
		
	}

	@Test
	public void testLocation() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertEquals(new GPSCoordinate(53, 427), d.getLocation());
	}

	@Test
	public void testEmergencies() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		if ((new GPSCoordinate(x1, y1).equals(d.getEmergencies().get(0).getLocation()))) {
			assertEquals(new GPSCoordinate(x2, y2), d.getEmergencies().get(1).getLocation());
		} else {
			assertEquals(new GPSCoordinate(x2, y2), d.getEmergencies().get(0).getLocation());
			assertEquals(new GPSCoordinate(x1, y1), d.getEmergencies().get(1).getLocation());
		}

		assertEquals(EmergencySeverity.BENIGN, d.getEmergencies().get(0).getSeverity());
		assertEquals(EmergencySeverity.BENIGN, d.getEmergencies().get(1).getSeverity());
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, d.getEmergencies().get(0).getStatus());
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, d.getEmergencies().get(1).getStatus());
	}

	@Test
	public void testSeverity() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.BENIGN, d.getSeverity());
		emergencies.clear();

		emergencies.add(e3);
		emergencies.add(e4);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.NORMAL, d.getSeverity());
		emergencies.clear();

		emergencies.add(e5);
		emergencies.add(e6);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.SERIOUS, d.getSeverity());
		emergencies.clear();

		emergencies.add(e7);
		emergencies.add(e8);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.URGENT, d.getSeverity());
		emergencies.clear();

		emergencies.add(e1);
		emergencies.add(e3);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.NORMAL, d.getSeverity());
		emergencies.clear();

		emergencies.add(e1);
		emergencies.add(e5);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.SERIOUS, d.getSeverity());
		emergencies.clear();

		emergencies.add(e1);
		emergencies.add(e7);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.URGENT, d.getSeverity());
		emergencies.clear();

		emergencies.add(e3);
		emergencies.add(e5);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.SERIOUS, d.getSeverity());
		emergencies.clear();

		emergencies.add(e3);
		emergencies.add(e7);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.URGENT, d.getSeverity());
		emergencies.clear();

		emergencies.add(e5);
		emergencies.add(e7);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.URGENT, d.getSeverity());
		emergencies.clear();

		emergencies.add(e1);
		emergencies.add(e4);
		emergencies.add(e6);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencySeverity.SERIOUS, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testStatus() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, d.getStatus());

		Set<Unit> units = new LinkedHashSet<Unit>();
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);

		e1.assignUnits(units);
		assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS, d.getStatus());
		//TODO Testen op finished.
	}

	@Test
	public void testCanAssign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidConstraintListException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertTrue(d.canAssignUnits(units));

		units.add(politiewagen2);
		units.add(politiewagen3);
		d = new Disaster(emergencies, description1);
		assertTrue(d.canAssignUnits(units));
	}

	@Test
	public void testCanNotAssign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidConstraintListException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Ambulance ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		units.add(ziekenwagen1);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertFalse(d.canAssignUnits(units));
	}

	@Test
	public void testCanBeResolved() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertTrue(d.canBeResolved(units));
	}

	@Test
	public void testCanNotBeResolved1() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Ambulance ziekenwagen1 = new Ambulance(name1, homeLocation1, speed1);
		units.add(ziekenwagen1);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertFalse(d.canBeResolved(units));
	}

	@Test
	public void testCanNotBeResolved2() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertTrue(d.canBeResolved(units));
	}

	@Test
	public void testAssign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidConstraintListException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		d.assignUnits(units);
		assertEquals(3, d.getWorkingUnits().size());
	}

	@Test
	public void testFinishUnit() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException{
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);

		assertFalse(true);
	}

	@Test
	public void testWithdrawUnit() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException{
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);

		assertFalse(true);
	}
}