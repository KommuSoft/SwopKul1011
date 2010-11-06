package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UnitTest{
	private GPSCoordinate gp1, gp2;
	private long x1, y1;

	@Before
    public void setUp(){
        x1 = 3141592;
        y1 = 2718281;

        gp1 = new GPSCoordinate(x1, y1);
		gp2 = null;
    }

	@Test
	public void testIsValidLocation(){
		assertTrue(Unit.isValidCurrentLocation(gp1));
		assertFalse(Unit.isValidCurrentLocation(gp2));
	}
}