package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidNotTimeSensitiveUnitBuildingException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class NotTimeSensitiveUnitBuildingListTest{
	private NotTimeSensitiveUnitBuildingList ntsubl1, ntsubl2;
	private Hospital h1, h2;
	private String name1, name2;
	private GPSCoordinate homeLocation1, homeLocation2;
	private long x1, y1, x2, y2;

	@Before
    public void setUp() throws InvalidLocationException, InvalidUnitBuildingNameException{
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

		ntsubl1 = new NotTimeSensitiveUnitBuildingList();
		ntsubl2 = new NotTimeSensitiveUnitBuildingList();
	}

	@Test
    public void testConstructor() {
		assertNotNull(ntsubl1);
        assertEquals(0, ntsubl1.getNotTimeSensitiveUnitBuildings().size());
    }

    @Test
    public void testAdd() throws InvalidNotTimeSensitiveUnitBuildingException {
		ntsubl1.addNotTimeSensitiveUnitBuilding(h1);
        assertEquals(1, ntsubl1.getNotTimeSensitiveUnitBuildings().size());
        assertEquals(h1, ntsubl1.getNotTimeSensitiveUnitBuildings().get(0));
        ntsubl1.addNotTimeSensitiveUnitBuilding(h2);
        assertEquals(2, ntsubl1.getNotTimeSensitiveUnitBuildings().size());
        assertEquals(h2, ntsubl1.getNotTimeSensitiveUnitBuildings().get(1));
	}

	@Test(expected = InvalidNotTimeSensitiveUnitBuildingException.class)
    public void testAddException() throws InvalidNotTimeSensitiveUnitBuildingException {
		ntsubl2.addNotTimeSensitiveUnitBuilding(h1);
		ntsubl2.addNotTimeSensitiveUnitBuilding(h1);
    }

}