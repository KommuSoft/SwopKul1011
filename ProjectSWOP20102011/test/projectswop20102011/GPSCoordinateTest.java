package projectswop20102011;

import projectswop20102011.exceptions.InvalidCoordinateException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GPSCoordinateTest{
	private GPSCoordinate g1, g2, g3;
	private long x1,y1,x2, y2, x3, y3;

	@Before
	public void setUp() throws InvalidCoordinateException{
		x1 = 10;
		y1 = 15;
		x2 = 10;
		y2 = 15;
		x3 = 6548;
		y3 = 3;
		g1 = new GPSCoordinate(x1,y1);
		g2 = new GPSCoordinate(x2,y2);
		g3 = new GPSCoordinate(x3,y3);
	}

	@Test
	public void testConstructor(){
		assertEquals(x1, g1.getX());
		assertEquals(y1, g1.getY());
		assertEquals(x2, g2.getX());
		assertEquals(y2, g2.getY());
		assertEquals(x3, g3.getX());
		assertEquals(y3, g3.getY());
	}

	@Test
	public void testEquals(){
		assertTrue(g1.equals(g1));
		assertTrue(g1.equals(g2));
		assertTrue(g2.equals(g1));
		assertFalse(g1.equals(g3));
		assertFalse(g2.equals(g3));
		assertFalse(g3.equals(g1));
		assertFalse(g3.equals(g2));
	}
}