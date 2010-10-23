package projectswop20102011;

import projectswop20102011.exceptions.InvalidCoordinateException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GPSCoordinateTest{
	private GPSCoordinate g1;
	private long x,y;

	@Before
	public void setUp() throws InvalidCoordinateException{
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