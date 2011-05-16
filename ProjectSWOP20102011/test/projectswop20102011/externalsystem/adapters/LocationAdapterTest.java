package projectswop20102011.externalsystem.adapters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.GPSCoordinate;

public class LocationAdapterTest {

	private GPSCoordinate l1, l2;
	private LocationAdapter location1, location2;
	private int x1, x2;
	private int y1, y2;

	@Before
	public void setUp() {
		l1 = new GPSCoordinate(x1, y1);
		l2 = new GPSCoordinate(x2, y2);
	}

	@Test
	public void testConstructor() {
		location1 = new LocationAdapter(l1);
		location2 = new LocationAdapter(l2);

		assertEquals(x1, location1.getX());
		assertEquals(y1, location1.getY());
		assertEquals(x2, location2.getX());
		assertEquals(y2, location2.getY());

		assertEquals(l1.getDistanceTo(l2), location1.getDistanceTo(location2), 0.001);
		assertEquals(l2.getDistanceTo(l1), location2.getDistanceTo(location1), 0.001);
		assertEquals(location1.getDistanceTo(location2), location2.getDistanceTo(location1), 0.001);
		assertEquals(l1.getDistanceTo(l1), location1.getDistanceTo(location1), 0.001);
		assertEquals(l2.getDistanceTo(l2), location2.getDistanceTo(location2), 0.001);

		assertEquals("(" + x1 + "," + y1 + ")", location1.toString());
		assertEquals("(" + x2 + "," + y2 + ")", location2.toString());
	}
}
