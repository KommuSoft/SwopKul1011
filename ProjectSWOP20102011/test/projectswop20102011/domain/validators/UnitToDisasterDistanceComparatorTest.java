package projectswop20102011.domain.validators;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidDisasterException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class UnitToDisasterDistanceComparatorTest {

	private UnitToDisasterDistanceComparator uc;
	private Emergency e1, e2, e3;
	private Disaster d1, d2, d3;
	private Unit u1, u2, u3, u4;
	private GPSCoordinate gps1, gps2, gps3, gps4;
	private long x1, y1, x2, y2, x3, y3, x4, y4;
	private SendableSeverity es1, es2;
	private long number1, number2;
	private String name1, name2, name3, name4;
	private long speed1, speed2, speed3, speed4;

	@Before
	public void setUp() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException, InvalidEmergencyException, InvalidConstraintListException {
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

		es1 = SendableSeverity.BENIGN;
		es2 = SendableSeverity.NORMAL;

		number1 = 10;
		number2 = 2;

		e1 = new PublicDisturbance(gps1, es1, "", number1);
		e2 = new PublicDisturbance(gps2, es2, "", number2);
		
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
		
		ArrayList<Emergency> emergencies1 = new ArrayList<Emergency>();
		
		emergencies1.add(e1);
		emergencies1.add(e2);
		
		d1 = new Disaster(emergencies1, "ramp1");
		d3 = null;
	}

	@Test
	public void testConstructor() throws InvalidDisasterException {
		uc = new UnitToDisasterDistanceComparator(d1);
	}

	@Test(expected = InvalidDisasterException.class)
	public void testIllegalConstructor() throws InvalidDisasterException {
		uc = new UnitToDisasterDistanceComparator(d3);
	}

	@Test
	public void testCompare() throws InvalidEmergencyException, InvalidDisasterException {
		uc = new UnitToDisasterDistanceComparator(d1);

		assertTrue(uc.compare(u3, u2) < 0);
		assertTrue(uc.compare(u2, u1) > 0);
		assertTrue(uc.compare(u1, u1) == 0);
	}
}
