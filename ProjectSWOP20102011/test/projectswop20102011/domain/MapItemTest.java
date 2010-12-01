package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapItemTest{
	private GPSCoordinate gp1, gp2;
	private long x1, y1;
	private String name1, name2, name3, name4, name5, name6;

	@Before
    public void setUp(){
        x1 = 3141592;
        y1 = 2718281;

        gp1 = new GPSCoordinate(x1, y1);
		gp2 = null;

		name1 = "";
		name2 = " ";
		name3 = "Loki";
		name4 = "Room 101";
		name5 = "CAPS LOCK IS CRUISE CONTROL FOR AWESOME!!!!!!";
		name6 = null;
    }

	@Test
	public void testIsValidLocation(){
		assertTrue(Unit.isValidHomeLocation(gp1));
		assertFalse(Unit.isValidHomeLocation(gp2));
	}

	@Test
	public void testIsValidName(){
		assertFalse(MapItem.isValidName(name1));
		assertTrue(MapItem.isValidName(name2));
		assertTrue(MapItem.isValidName(name3));
		assertTrue(MapItem.isValidName(name4));
		assertTrue(MapItem.isValidName(name5));
		assertFalse(MapItem.isValidName(name6));
	}
}