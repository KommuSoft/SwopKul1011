package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmergencyTest{
	private Emergency e1, e2, e3;
	private GPSCoordinate g1, g2;
	private EmergencySeverity s1, s2;
	private EmergencyList el1, el2;
	private long x1, y1, x2, y2;
	private long id1, id2;

	@Before
	public void setUp() throws InvalidCoordinateException{
		x1 = 10;
		y1 = 165;
		x2 = -50;
		y2 = -75;
		id1 = 1;
		id2 = 2;
		
		g1 = new GPSCoordinate(x1,y1);
		g2 = new GPSCoordinate(x2, y2);
		s1 = EmergencySeverity.BENIGN;
		s2 = EmergencySeverity.URGENT;
		el1 = new EmergencyList();
		el2 = new EmergencyList();
	}

	@Test
	public void testShortConstructor() throws InvalidEmergencyException{
		e1 = new Emergency(el1, g1, s1);
		assertEquals(x1, e1.getLocation().getX());
		assertEquals(y1, e1.getLocation().getY());
		assertEquals(EmergencySeverity.BENIGN, e1.getSeverity());
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, e1.getStatus());
		assertEquals(1, el1.getEmergencies().size());
		assertEquals(e1, el1.getEmergencies().get(0));

		e2 = new Emergency(el1, g2, s2);
		assertEquals(x2, e2.getLocation().getX());
		assertEquals(y2, e2.getLocation().getY());
		assertEquals(EmergencySeverity.URGENT, e2.getSeverity());
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, e2.getStatus());
		assertEquals(2, el1.getEmergencies().size());
		assertEquals(e2, el1.getEmergencies().get(1));
	}

	@Test
	public void testExtendedConstructor() throws InvalidEmergencyException{
		e1 = new Emergency(el1, g1, s1, id1);
		assertEquals(x1, e1.getLocation().getX());
		assertEquals(y1, e1.getLocation().getY());
		assertEquals(EmergencySeverity.BENIGN, e1.getSeverity());
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, e1.getStatus());
		assertEquals(id1, e1.getId());
		assertEquals(1, el1.getEmergencies().size());
		assertEquals(e1, el1.getEmergencies().get(0));
	}

	@Test(expected=InvalidEmergencyException.class)
	public void testIllegalConstructor() throws InvalidEmergencyException{
		e2 = new Emergency(el2, g1, s1, id2);
		e3 = new Emergency(el2, g1, s1, id2);
		assertEquals(1, el2.getEmergencies().size());
		assertEquals(e2, el2.getEmergencies().get(0));
	}
}