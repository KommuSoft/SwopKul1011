package projectswoptest20102011;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.Emergency;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.Severity;

public class EmergencyTest extends TestCase{
	private Emergency e1;
	private GPSCoordinate g1;
	private Severity s1;
	private long x,y;

	@Override
	@Before
	public void setUp(){
		x = 10;
		y = 165;
		g1 = new GPSCoordinate(x,y);
		e1 = new Emergency(g1, s1.BENIGN);
	}

	@Test
	public void testConstructor(){
		assertEquals(e1.getLocation().getX(),x);
		assertEquals(e1.getLocation().getY(),y);
		assertEquals(e1.getSeverity(), s1.BENIGN);
	}
}