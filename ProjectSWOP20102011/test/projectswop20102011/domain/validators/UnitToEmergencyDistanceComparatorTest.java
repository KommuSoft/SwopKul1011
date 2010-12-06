package projectswop20102011.domain.validators;

import projectswop20102011.domain.validators.UnitToEmergencyDistanceComparator;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.domain.Unit;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class UnitToEmergencyDistanceComparatorTest {

	private UnitToEmergencyDistanceComparator uc;
	private Emergency e1, e2, e3;
	private Unit u1, u2, u3, u4;
	private GPSCoordinate gps1, gps2, gps3, gps4;
	private long x1, y1, x2, y2, x3, y3, x4, y4;
	private EmergencySeverity es1, es2;
	private long number1, number2;
	private String name1, name2, name3, name4;
	private long speed1, speed2, speed3, speed4;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException {
		x1 = 10;
		y1 = 10;

		x2 = 25;
		y2 = 0;

		x3 = 9;
		y3 = 9;

		x4 = 24;
		y4 = -1;

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = new GPSCoordinate(x2, y2);
		gps3 = new GPSCoordinate(x3, y3);
		gps4 = new GPSCoordinate(x4, y4);

		es1 = EmergencySeverity.BENIGN;
		es2 = EmergencySeverity.NORMAL;

		number1 = 10;
		number2 = 2;

		e1 = new PublicDisturbance(gps1, es1, "", number1);
		e2 = new PublicDisturbance(gps2, es2, "", number2);
		e3 = null;

		name1 = "A1";
		name2 = "A2";
		name3 = "A3";
		name4 = "A4";

		speed1 = 10 * 3600;
		speed2 = 7 * 3600;
		speed3 = 5 * 3600;
		speed4 = 3 * 3600;

		u1 = new Ambulance(name1, gps1, speed1);
		u2 = new Ambulance(name2, gps2, speed2);
		u3 = new Ambulance(name3, gps3, speed3);
		u4 = new Ambulance(name4, gps4, speed4);
	}

	@Test
	public void testConstructor() throws InvalidEmergencyException {
		uc = new UnitToEmergencyDistanceComparator(e1);
	}

	@Test(expected = InvalidEmergencyException.class)
	public void testIllegalConstructor() throws InvalidEmergencyException {
		uc = new UnitToEmergencyDistanceComparator(e3);
	}

	@Test
	public void testCompare() throws InvalidEmergencyException {
		uc = new UnitToEmergencyDistanceComparator(e1);

		assertTrue(uc.compare(u1, u2) < 0);
		assertTrue(uc.compare(u2, u1) > 0);
		assertTrue(uc.compare(u1, u1) == 0);
		assertTrue(uc.compare(u1, u3) < 0);
		assertTrue(uc.compare(u1, u4) < 0);
		assertTrue(uc.compare(u3, u2) < 0);
		assertTrue(uc.compare(u3, u4) < 0);

		uc = new UnitToEmergencyDistanceComparator(e2);
		assertTrue(uc.compare(u2, u1) < 0);
		assertTrue(uc.compare(u2, u3) < 0);
		assertTrue(uc.compare(u2, u4) < 0);
		assertTrue(uc.compare(u4, u3) < 0);
		assertTrue(uc.compare(u4, u1) < 0);
	}
}
