package projectswop20102011.externalsystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

	private int x1, x2, x3;
	private int y1, y2, y3;
	private Location location;

	@Before
	public void setUp() {
		x1 = 10;
		y1 = 11;

		x2 = 0;
		y2 = 0;

		x3 = -10;
		y3 = -9;
	}

	@Test
	public void testConstructor() {
		location = new Location(x1, y1);
		assertEquals(x1, location.getX());
		assertEquals(y1, location.getY());

		location = new Location(x2, y2);
		assertEquals(x2, location.getX());
		assertEquals(y2, location.getY());

		location = new Location(x3, y3);
		assertEquals(x3, location.getX());
		assertEquals(y3, location.getY());
	}
}
