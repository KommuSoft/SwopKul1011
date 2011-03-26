package projectswop20102011.domain;

import projectswop20102011.domain.lists.MapItemList;
import projectswop20102011.domain.validators.TypeMapItemValidator;
import projectswop20102011.domain.validators.HospitalToEmergencyDistanceComparator;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class MapItemListTest {

    private MapItemList ubl1, ubl2;
    private Fire f1;
    private Ambulance a1;
    private Iterator<MapItem> it1;
    private Hospital h2, h1;
    private String name1, name2;
    private GPSCoordinate homeLocation1, homeLocation2;
    private long x1, y1, x2, y2, x3, y3;
    private HospitalToEmergencyDistanceComparator hdc;
    private long speed1;
    private String name;
    private GPSCoordinate fireLocation;
    private GPSCoordinate homeLocation;

    @Before
    public void setUp() throws InvalidMapItemNameException, InvalidLocationException {
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

        x3 = 64;
        y3 = -6789;
        fireLocation = new GPSCoordinate(x3, y3);

        speed1 = 90;
        name = "Ziekenwagen";
        homeLocation = new GPSCoordinate(x2, y2);

        ubl1 = new MapItemList();
        ubl2 = new MapItemList();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ubl1);
        assertEquals(0, ubl1.getMapItems().size());
    }

    @Test
    public void testAdd() throws InvalidMapItemException {
        ubl1.addMapItem(h2);
        assertEquals(1, ubl1.getMapItems().size());
        assertFalse(ubl1.getMapItems().contains(h1));
        assertTrue(ubl1.getMapItems().contains(h2));
        ubl1.addMapItem(h1);
        assertEquals(2, ubl1.getMapItems().size());
        assertTrue(ubl1.getMapItems().contains(h1));
        assertTrue(ubl1.getMapItems().contains(h2));
    }

    @Test
    public void testAddAlreadyContains() throws InvalidMapItemException {
        assertEquals(0,ubl2.getMapItems().size());
        ubl2.addMapItem(h2);
        assertEquals(1,ubl2.getMapItems().size());
        ubl2.addMapItem(h2);
        assertEquals(1,ubl2.getMapItems().size());
    }

    @Test
    public void testIterator() throws InvalidMapItemException {
        it1 = ubl1.iterator();
        assertFalse(it1.hasNext());
        ubl1.addMapItem(h2);
        it1 = ubl1.iterator();
        assertTrue(it1.hasNext());
        assertEquals(h2, it1.next());
        assertFalse(it1.hasNext());
        ubl1.addMapItem(h1);
        it1 = ubl1.iterator();
        assertTrue(it1.hasNext());
        MapItem el1 = it1.next();
        assertTrue(h1 == el1 || h2 == el1);
        assertTrue(it1.hasNext());
        MapItem el2 = it1.next();
        assertTrue(el1 != el2 && (h1 == el2 || h2 == el2));
        assertFalse(it1.hasNext());
    }

    @Test
    public void testMapItemsByCriterium() throws InvalidMapItemException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
        a1 = new Ambulance(name, homeLocation, speed1);

        ubl1.addMapItem(h2);
        ubl1.addMapItem(a1);
        ubl1.addMapItem(h1);

        TypeMapItemValidator tubec = new TypeMapItemValidator(Hospital.class);
        ubl2 = ubl1.getSubMapItemListByValidator(tubec);

        assertEquals(2, ubl2.getMapItems().size());
        assertTrue(ubl2.getMapItems().contains(h1));
        assertTrue(ubl2.getMapItems().contains(h2));
        assertFalse(ubl2.getMapItems().contains(a1));
    }

    @Test
    public void testSort() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemException, InvalidEmergencyException {
        f1 = new Fire(fireLocation, EmergencySeverity.BENIGN, "",FireSize.LOCAL, true, 1, 6);
        hdc = new HospitalToEmergencyDistanceComparator(f1);
        ubl1.addMapItem(h2);
        ubl1.addMapItem(h1);

        ArrayList<Hospital> h = ubl1.getSortedCopy(hdc);
        assertEquals(2, h.size());
        assertEquals(h1, h.get(0));
        assertEquals(h2, h.get(1));
    }

	@Test
	public void testGetMapItemFromName(){
		ubl1.addMapItem(h2);
        ubl1.addMapItem(h1);

		MapItem mi = ubl1.getMapItemFromName("Sjukhus");
		assertEquals("Sjukhus", mi.getName());
		assertEquals(homeLocation2, mi.getHomeLocation());

		mi = ubl1.getMapItemFromName("Ziekewagen");
		assertNull(mi);
	}
}
