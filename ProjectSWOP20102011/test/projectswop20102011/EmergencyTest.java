package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmergencyTest{
	private Emergency e1, e2, e3;
	private GPSCoordinate g1;
	private EmergencySeverity s1;
	private EmergencyList el1, el2;
	private long x,y;
	private long id1, id2;

	@Before
	public void setUp() throws InvalidCoordinateException{
		x = 10;
		y = 165;
		id1 = 1;
		id2 = 2;
		
		g1 = new GPSCoordinate(x,y);
		s1 = EmergencySeverity.BENIGN;
		el1 = new EmergencyList();
		el2 = new EmergencyList();
	}

	@Test
	public void testConstructor(){
		e1 = new Emergency(el1, g1, s1, id1);
		assertEquals(x, e1.getLocation().getX());
		assertEquals(y, e1.getLocation().getY());
		assertEquals(EmergencySeverity.BENIGN, e1.getSeverity());
		assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, e1.getStatus());
		assertEquals(id1, e1.getId());
		assertEquals(e1, el1.getEmergencies().get(0));
	}

	@Test
	public void testIllegalConstructor(){
		e2 = new Emergency(el2, g1, s1, id2);
		e3 = new Emergency(el2, g1, s1, id2);
		assertEquals(1, el2.getEmergencies().size());
	}
}