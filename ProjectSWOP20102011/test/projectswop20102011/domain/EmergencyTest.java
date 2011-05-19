package projectswop20102011.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.LinkedHashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.eventhandlers.PlaceHolderEventHandler;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class EmergencyTest {

	private GPSCoordinate gp1, gp2, gp3;
	private long x1, y1, x2, y2;
	private SendableStatus es1, es2;
	private String name1, name2, name3, name4;
	private long speed1;
	private SendableSeverity severity;
	private String description1, description2;
	private long numberOfPeople1, numberOfPeople2;
	private boolean armed1;
	private boolean inProgress1;

	@Before
	public void setUp() {
		x1 = 3141592;
		y1 = 2718281;

		x2 = 3565;
		y2 = 245453;

		gp1 = new GPSCoordinate(x1, y1);
		gp2 = null;
		gp3 = new GPSCoordinate(x2, y2);

		es1 = SendableStatus.COMPLETED;
		es2 = null;

		name1 = "politiewagen1";
		name2 = "politiewagen2";
		name3 = "politiewagen3";
		name4 = "politiewagen4";
		speed1 = 100;

		severity = SendableSeverity.NORMAL;
		description1 = "Volksopstand";
		description2 = "Overvalletje";
		numberOfPeople1 = 1000;
		numberOfPeople2 = 8;
		armed1 = true;
		inProgress1 = true;
	}

	@Test
	public void testIsValidLocation() {
		assertTrue(Emergency.isValidLocation(gp1));
		assertFalse(Emergency.isValidLocation(gp2));
	}

	@Test
	public void testIsValidStatus() {
		assertTrue(Emergency.isValidStatus(es1));
		assertFalse(Emergency.isValidStatus(es2));
	}

	@Test
	public void testIsPartiallyAssigned() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidSendableSeverityException, NumberOutOfBoundsException, InvalidSendableStatusException, InvalidEmergencyException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, gp1, speed1);
		units.add(politiewagen1);

		Emergency e1 = new PublicDisturbance(gp3, severity, description1, numberOfPeople1);
		assertFalse(e1.isPartiallyAssigned());
		e1.assignUnits(units, new PlaceHolderEventHandler());
		assertTrue(e1.isPartiallyAssigned());
		
		units.clear();
		Policecar politiewagen2 = new Policecar(name1, gp1, speed1);
		Policecar politiewagen3 = new Policecar(name1, gp1, speed1);
		units.add(politiewagen2);
		units.add(politiewagen3);
		Emergency e2 = new PublicDisturbance(gp3, severity, description1, numberOfPeople2);
		assertFalse(e2.isPartiallyAssigned());
		e2.assignUnits(units, new PlaceHolderEventHandler());
		assertFalse(e2.isPartiallyAssigned());
	}

	@Test
	public void testFinishUnit() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidSendableSeverityException, InvalidDurationException, InvalidSendableStatusException, InvalidEmergencyException {
		Set<Unit> units = new HashSet<Unit>(3);
		Policecar politiewagen1 = new Policecar(name1, gp1, speed1);
		Policecar politiewagen2 = new Policecar(name1, gp1, speed1);
		Policecar politiewagen3 = new Policecar(name1, gp1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);

		Emergency e = new Robbery(gp1, severity, description2, armed1, inProgress1);
		e.assignUnits(units, new PlaceHolderEventHandler());

		politiewagen1.timeAhead(1000000000);
		politiewagen2.timeAhead(1000000000);
		politiewagen3.timeAhead(1000000000);
		e.finishUnit(politiewagen1, new PlaceHolderEventHandler());
		e.finishUnit(politiewagen2, new PlaceHolderEventHandler());
		e.finishUnit(politiewagen3, new PlaceHolderEventHandler());
		assertEquals(SendableStatus.COMPLETED, e.getStatus());
	}

	@Test
	public void testProposal() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidSendableSeverityException{
		Set<Unit> units = new HashSet<Unit>(3);
		Policecar politiewagen1 = new Policecar(name1, gp1, speed1);
		Policecar politiewagen2 = new Policecar(name2, gp1, speed1);
		Policecar politiewagen3 = new Policecar(name3, gp1, speed1);
		Policecar politiewagen4 = new Policecar(name4, gp1, speed1);
		units.add(politiewagen1);
		units.add(politiewagen2);
		units.add(politiewagen3);
		units.add(politiewagen4);

		Emergency e = new Robbery(gp1, severity, description2, armed1, inProgress1);
		assertEquals(3, e.getPolicyProposal(units).size());
	}
}
