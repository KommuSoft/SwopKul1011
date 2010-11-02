package projectswop20102011;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitBuildingException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class UnitBuildingListTest{
	private UnitBuildingList ubl1, ubl2;
	private Iterator<UnitBuilding> it1;
	private Hospital h1, h2;
	private String name1, name2;
	private GPSCoordinate homeLocation1, homeLocation2;
	private long x1, y1, x2, y2;

	@Before
    public void setUp() throws InvalidUnitBuildingNameException, InvalidLocationException{
		x1 = 123;
		y1 = 321;
		homeLocation1 = new GPSCoordinate(x1, y1);
		name1 = "Krankenhaus";
		h1 = new Hospital(name1, homeLocation1);

		x2 = 987;
		y2 = 789;
		homeLocation2 = new GPSCoordinate(x2, y2);
		name2 = "Sjukhus";
		h2 = new Hospital(name2, homeLocation2);

		ubl1 = new UnitBuildingList();
		ubl2 = new UnitBuildingList();
	}

	@Test
    public void testConstructor() {
		assertNotNull(ubl1);
        assertEquals(0, ubl1.getUnitBuildings().size());
    }

    @Test
    public void testAdd() throws InvalidUnitBuildingException{
		ubl1.addUnitBuilding(h1);
        assertEquals(1, ubl1.getUnitBuildings().size());
        assertEquals(h1, ubl1.getUnitBuildings().get(0));
        ubl1.addUnitBuilding(h2);
        assertEquals(2, ubl1.getUnitBuildings().size());
        assertEquals(h2, ubl1.getUnitBuildings().get(1));
	}

	@Test(expected = InvalidUnitBuildingException.class)
    public void testAddException() throws InvalidUnitBuildingException {
		ubl2.addUnitBuilding(h1);
		ubl2.addUnitBuilding(h1);
    }

	@Test
	public void testIterator() throws InvalidUnitBuildingException{
		it1 = ubl1.iterator();
		assertFalse(it1.hasNext());
		ubl1.addUnitBuilding(h1);
		it1 = ubl1.iterator();
		assertTrue(it1.hasNext());
		assertEquals(h1, it1.next());
		assertFalse(it1.hasNext());
        ubl1.addUnitBuilding(h2);
		it1 = ubl1.iterator();
		assertTrue(it1.hasNext());
		assertEquals(h1, it1.next());
		assertTrue(it1.hasNext());
		assertEquals(h2, it1.next());
		assertFalse(it1.hasNext());
	}

}