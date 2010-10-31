package projectswop20102011;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidTimeSensitiveUnitBuildingException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class TimeSensitiveUnitBuildingListTest{
	private TimeSensitiveUnitBuildingList tsubl1, tsubl2;
	private Iterator<TimeSensitiveUnitBuilding> it1;
	private Ambulance a1, a2;
	private String name1, name2;
	private GPSCoordinate homeLocation1, homeLocation2;
	private long speed1, speed2;
	private GPSCoordinate currentLocation1, currentLocation2;
	private GPSCoordinate destination1, destination2;
	private boolean assigned1, assigned2;
	private long hx1, hy1, hx2, hy2;
	private long cx1, cy1, cx2, cy2;
	private long dx1, dy1, dx2, dy2;

	@Before
    public void setUp() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		hx1 = 456; hy1 = 654;
		cx1 = 963; cy1 = 369;
		dx1 = 741; dy1 = 147;
		homeLocation1 = new GPSCoordinate(hx1, hy1);
		currentLocation1 = new GPSCoordinate(cx1, cy1);
		destination1 = new GPSCoordinate(dx1, dy1);
		name1 = "Eir";
		speed1 = 50;
		assigned1 = false;

		a1 = new Ambulance(name1, homeLocation1, speed1, currentLocation1, destination1, assigned1);

		hx2 = 46; hy2 = 64;
		cx2 = 93; cy2 = 69;
		dx2 = 71; dy2 = 14;
		homeLocation2 = new GPSCoordinate(hx2, hy2);
		currentLocation2 = new GPSCoordinate(cx2, cy2);
		destination2 = new GPSCoordinate(dx2, dy2);
		name2 = "Sleipnir";
		speed2 = 9;
		assigned2 = false;

		a2 = new Ambulance(name2, homeLocation2, speed2, currentLocation2, destination2, assigned2);

		tsubl1 = new TimeSensitiveUnitBuildingList();
		tsubl2 = new TimeSensitiveUnitBuildingList();
	}

	@Test
    public void testConstructor() {
		assertNotNull(tsubl1);
        assertEquals(0, tsubl1.getTimeSensitiveUnitBuildings().size());
    }

    @Test
    public void testAdd() throws InvalidTimeSensitiveUnitBuildingException {
		tsubl1.addTimeSensitiveUnitBuilding(a1);
        assertEquals(1, tsubl1.getTimeSensitiveUnitBuildings().size());
        assertEquals(a1, tsubl1.getTimeSensitiveUnitBuildings().get(0));
        tsubl1.addTimeSensitiveUnitBuilding(a2);
        assertEquals(2, tsubl1.getTimeSensitiveUnitBuildings().size());
        assertEquals(a2, tsubl1.getTimeSensitiveUnitBuildings().get(1));

	}

	@Test(expected = InvalidTimeSensitiveUnitBuildingException.class)
    public void testAddException() throws InvalidTimeSensitiveUnitBuildingException {
		tsubl2.addTimeSensitiveUnitBuilding(a1);
		tsubl2.addTimeSensitiveUnitBuilding(a1);
    }

	@Test
	public void testIterator() throws InvalidTimeSensitiveUnitBuildingException {
		it1 = tsubl1.iterator();
		assertFalse(it1.hasNext());
		tsubl1.addTimeSensitiveUnitBuilding(a1);
		it1 = tsubl1.iterator();
		assertTrue(it1.hasNext());
		assertEquals(a1, it1.next());
		assertFalse(it1.hasNext());
        tsubl1.addTimeSensitiveUnitBuilding(a2);
		it1 = tsubl1.iterator();
		assertTrue(it1.hasNext());
		assertEquals(a1, it1.next());
		assertTrue(it1.hasNext());
		assertEquals(a2, it1.next());
		assertFalse(it1.hasNext());
	}
}