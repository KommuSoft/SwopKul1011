package projectswop20102011;



import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.GPSCoordinate;

public class GPSCoordinateTest extends TestCase{
	private GPSCoordinate g1;
	private long x,y;

	@Override
	@Before
	public void setUp(){
		x = 10;
		y = 15;
		g1 = new GPSCoordinate(x,y);
	}

	@Test
	public void testConstructor(){
		assertEquals(x, g1.getX());
		assertEquals(y, g1.getY());
	}
}