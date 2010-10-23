package projectswop20102011;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class EmergencyListTest{
	private EmergencyList el1;
	private Emergency e1, e2;
	private GPSCoordinate l1, l2;
	
	private long x1, y1, x2, y2;

	@Before
	public void setUp() throws InvalidCoordinateException{
		x1 = 12;
		y1 = 789;
		x2 = 95;
		y2 = 65;
		l1 = new GPSCoordinate(x1, y1);
		l2 = new GPSCoordinate(x2, y2);
		
		el1 = new EmergencyList();
	}

	@Test
	public void testConstructor(){
		assertNotNull(el1);
		assertEquals(0, el1.getEmergencies().size());
	}
	
	@Test(expected=InvalidEmergencyException.class)
	public void testAdd() throws InvalidEmergencyException{
		e1 = new Emergency(el1, l1, EmergencySeverity.URGENT);
		el1.addEmergency(e1);
		assertEquals(1, el1.getEmergencies().size());
		assertEquals(e1, el1.getEmergencies().get(0));

		e2 = new Emergency(el1, l2, EmergencySeverity.BENIGN, e1.getId());
		assertEquals(1, el1.getEmergencies().size());
		assertEquals(e1, el1.getEmergencies().get(0));
	}
}