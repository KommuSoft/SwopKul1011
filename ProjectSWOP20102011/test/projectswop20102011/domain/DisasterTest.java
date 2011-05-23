package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;

import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidFinishJobException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
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
	private String name1, name2;
	private long capacity1;
	
	@Before
	public void setUp() throws InvalidLocationException, InvalidSendableSeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		x1 = 12;
		y1 = 789;
		x2 = 95;
		y2 = 65;
		l1 = new GPSCoordinate(x1, y1);
		l2 = new GPSCoordinate(x2, y2);
		armed1 = true;
		inProgress1 = false;

		e1 = new Robbery(l1, SendableSeverity.BENIGN, "", armed1, inProgress1);
		e2 = new PublicDisturbance(l2, SendableSeverity.BENIGN, "", 6);
		e3 = new Fire(l1, SendableSeverity.NORMAL, "", FireSize.LOCAL, false, 0, 1337);
		e4 = new PublicDisturbance(l2, SendableSeverity.NORMAL, "", 1302);
		e5 = new Fire(l1, SendableSeverity.SERIOUS, "", FireSize.LOCAL, false, 0, 1337);
		e6 = new PublicDisturbance(l2, SendableSeverity.SERIOUS, "", 1302);
		e7 = new Fire(l1, SendableSeverity.URGENT, "", FireSize.LOCAL, false, 0, 1337);
		e8 = new PublicDisturbance(l2, SendableSeverity.URGENT, "", 6);

		description1 = "A couple of emergencies";

		speed1 = 10;
		homeLocation1 = new GPSCoordinate(10, 10);
		name1 = "Unit1";
		name2 = "Unit2";
		capacity1 = 2000;
	}

	@Test
	public void testValidEmergencies() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		d = new Disaster(emergencies, description1);
		assertEquals(1, d.getEmergencies().size());
		emergencies.clear();

		emergencies.add(e2);
		emergencies.add(e3);
		d = new Disaster(emergencies, description1);
		assertEquals(2, d.getEmergencies().size());
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

		assertEquals(SendableSeverity.BENIGN, d.getEmergencies().get(0).getSeverity());
		assertEquals(SendableSeverity.BENIGN, d.getEmergencies().get(1).getSeverity());
		assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED, d.getEmergencies().get(0).getStatus());
		assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED, d.getEmergencies().get(1).getStatus());
	}

	@Test
	public void testSeverityNormalBenign() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();

		emergencies.add(e1);
		emergencies.add(e3);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.NORMAL, d.getSeverity());
		emergencies.clear();

	}

	@Test
	public void testSeverityNormalNormal() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e3);
		emergencies.add(e4);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.NORMAL, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeveritySeriousBenign() throws InvalidEmergencyException, InvalidEmergencyException, InvalidEmergencyException, InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();

		emergencies.add(e1);
		emergencies.add(e5);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.SERIOUS, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeveritySeriousSerious() throws InvalidEmergencyException, InvalidEmergencyException, InvalidEmergencyException, InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e5);
		emergencies.add(e6);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.SERIOUS, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeveritySeriousNormal() throws InvalidEmergencyException, InvalidEmergencyException, InvalidEmergencyException, InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e3);
		emergencies.add(e5);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.SERIOUS, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeveritySeriousNormalBening() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e4);
		emergencies.add(e6);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.SERIOUS, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeverityUrgent() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e5);
		emergencies.add(e7);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.URGENT, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeverityUrgentBenign() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e7);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.URGENT, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeverityUrgentNormal() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e3);
		emergencies.add(e7);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.URGENT, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeverityUrgentSerious() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e7);
		emergencies.add(e8);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.URGENT, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testSeverityBenign() throws InvalidEmergencyException, InvalidConstraintListException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableSeverity.BENIGN, d.getSeverity());
		emergencies.clear();
	}

	@Test
	public void testStatus() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidSendableStatusException, InvalidEmergencyException, InvalidConstraintListException, InvalidDurationException, InvalidFinishJobException {
		ArrayList<Emergency> emergencies = new ArrayList<Emergency>(2);
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED, d.getStatus());

		Set<Unit> units = new LinkedHashSet<Unit>(3);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);

		d.assignUnits(units);
		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, d.getStatus());

		politiewagen1.timeAhead(1000000);
		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, d.getStatus());
		politiewagen2.timeAhead(1000000);
		politiewagen3.timeAhead(1000000);
		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, d.getStatus());

		politiewagen1.finishedJob();
		politiewagen2.finishedJob();
		politiewagen3.finishedJob();
		assertEquals(SendableStatus.COMPLETED, d.getStatus());
	}

	@Test
	public void testCanPartiallyAssign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidSendableStatusException, InvalidEmergencyException, InvalidConstraintListException {
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
	}

	@Test
	public void testCanTotallyAssign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidSendableStatusException, InvalidEmergencyException, InvalidConstraintListException {
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
		assertTrue(d.canAssignUnits(units));
	}

	@Test
	public void testCanNotAssign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidSendableStatusException, InvalidEmergencyException, InvalidConstraintListException {
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
	public void testCanBeResolved1() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException {
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
	public void testAssign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidSendableStatusException, InvalidEmergencyException, InvalidConstraintListException {
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
	public void testFinishUnit() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException, InvalidSendableStatusException, InvalidDurationException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>(2);
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED, d.getStatus());
		d.assignUnits(units);

		politiewagen1.timeAhead(1000000000);
		politiewagen2.timeAhead(1000000000);
		politiewagen3.timeAhead(1000000000);

		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, d.getStatus());
		d.finishUnit(politiewagen1);
		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, d.getStatus());
		d.finishUnit(politiewagen2);
		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, d.getStatus());
		d.finishUnit(politiewagen3);
		assertEquals(SendableStatus.COMPLETED, d.getStatus());
	}

	@Test
	public void testFinishUnitAutoDispatch() throws InvalidSendableStatusException, InvalidDurationException, InvalidEmergencyException, InvalidConstraintListException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFinishJobException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name2, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>(2);
		emergencies.add(e8);
		emergencies.add(e1);
		d = new Disaster(emergencies, description1);
		
		d.assignUnits(units);
		assertEquals(0, e1.getWorkingUnits().size());

		politiewagen1.timeAhead(1000000000);
		politiewagen2.timeAhead(1000000000);

		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, d.getStatus());
		politiewagen1.finishedJob();
		assertEquals(1, e1.getWorkingUnits().size());
		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, d.getStatus());
		politiewagen2.finishedJob();
		
		politiewagen1.timeAhead(1000000000);
		politiewagen1.finishedJob();
		
		assertEquals(0, e1.getWorkingUnits().size());
		assertEquals(SendableStatus.COMPLETED, d.getStatus());
	}

	@Test
	public void testWithdrawUnit() throws InvalidMapItemNameException, InvalidLocationException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException, InvalidSendableStatusException, InvalidDurationException, InvalidWithdrawalException {
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
		politiewagen3.withdraw();
		assertEquals(2, d.getWorkingUnits().size());
	}

	@Test(expected = InvalidWithdrawalException.class)
	public void testInvalidWithdrawUnit() throws InvalidMapItemNameException, InvalidLocationException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException, InvalidSendableStatusException, InvalidDurationException, InvalidWithdrawalException {
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

		politiewagen1.timeAhead(1000000000);
		politiewagen2.timeAhead(1000000000);
		politiewagen3.timeAhead(1000000000);

		politiewagen3.withdraw();
	}

	@Test
	public void testIsPartiallyAssigned() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException, InvalidSendableStatusException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertFalse(d.isPartiallyAssigned());
		d.assignUnits(units);
		assertTrue(d.isPartiallyAssigned());
	}

	@Test
	public void testIsPartiallyAssigned2() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException, InvalidSendableStatusException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);
		assertFalse(d.isPartiallyAssigned());
		d.assignUnits(units);
		assertFalse(d.isPartiallyAssigned());
	}

	@Test
	public void testPartiallyAssign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException, InvalidSendableStatusException {
		Set<Unit> units1 = new LinkedHashSet<Unit>(5);
		Set<Unit> units2 = new LinkedHashSet<Unit>(5);

		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		units1.add(politiewagen1);
		units2.add(politiewagen2);
		units2.add(politiewagen3);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>(2);
		emergencies.add(e1);
		emergencies.add(e2);
		d = new Disaster(emergencies, description1);

		assertEquals(0, d.getWorkingUnits().size());
		d.assignUnits(units1);
		assertEquals(1, d.getWorkingUnits().size());
		d.assignUnits(units2);
		assertEquals(3, d.getWorkingUnits().size());
	}

	@Test
	public void testPolicyProposalBenign() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException {
		HashSet<Unit> units = new HashSet<Unit>(3);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen4 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);
		units.add(politiewagen4);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>(2);
		emergencies.add(e2);
		Disaster d = new Disaster(emergencies, description1);
		assertEquals(0, d.getPolicyProposal(units).size());
	}

	@Test
	public void testPolicyProposalUrgent() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException {
		HashSet<Unit> units = new HashSet<Unit>(3);
		Policecar politiewagen1 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen2 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen3 = new Policecar(name1, homeLocation1, speed1);
		Policecar politiewagen4 = new Policecar(name1, homeLocation1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);
		units.add(politiewagen4);

		ArrayList<Emergency> emergencies = new ArrayList<Emergency>(2);
		emergencies.add(e8);
		Disaster d = new Disaster(emergencies, description1);
		assertEquals(2, d.getPolicyProposal(units).size());
	}
}
