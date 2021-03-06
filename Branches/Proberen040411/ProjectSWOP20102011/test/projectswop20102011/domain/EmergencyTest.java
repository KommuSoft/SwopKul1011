package projectswop20102011.domain;

import java.util.Set;
import java.util.LinkedHashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class EmergencyTest {

	private GPSCoordinate gp1, gp2, gp3;
	private long x1, y1, x2, y2;
	private EmergencyStatus es1, es2;
	private String name1;
	private long speed1;
	private EmergencySeverity severity;
	private String description;
	private long numberOfPeople;

	@Before
	public void setUp() {
		x1 = 3141592;
		y1 = 2718281;

		x2 = 3565;
		y2 = 245453;

		gp1 = new GPSCoordinate(x1, y1);
		gp2 = null;
		gp3 = new GPSCoordinate(x2, y2);

		es1 = EmergencyStatus.COMPLETED;
		es2 = null;

		name1 = "politiewagen";
		speed1 = 100;

		severity = EmergencySeverity.NORMAL;
		description = "Volksopstand";
		numberOfPeople = 1000;
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
	public void testIsPartiallyAssigned() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencySeverityException, NumberOutOfBoundsException, InvalidEmergencyStatusException, InvalidEmergencyException {
		Set<Unit> units = new LinkedHashSet<Unit>(5);
		Policecar politiewagen1 = new Policecar(name1, gp1, speed1);
		units.add(politiewagen1);

		Emergency e = new PublicDisturbance(gp3, severity, description, numberOfPeople);
		e.assignUnits(units);
		assertTrue(e.isPartiallyAssigned());
	}
}
