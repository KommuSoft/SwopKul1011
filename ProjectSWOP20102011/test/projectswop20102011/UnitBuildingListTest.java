package projectswop20102011;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class UnitBuildingListTest {

    private UnitBuildingList ubl1, ubl2;
    private Fire f1;
    private Ambulance a1;
    private Iterator<UnitBuilding> it1;
    private Hospital h2, h1;
    private String name1, name2;
    private GPSCoordinate homeLocation1, homeLocation2;
    private long x1, y1, x2, y2, x3, y3;
    private HospitalDistanceComparator hdc;
    private long speed1;
    private String name;
    private GPSCoordinate fireLocation;
    private GPSCoordinate homeLocation;

    @Before
    public void setUp() throws InvalidUnitBuildingNameException, InvalidLocationException {
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

        ubl1 = new UnitBuildingList();
        ubl2 = new UnitBuildingList();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ubl1);
        assertEquals(0, ubl1.getUnitBuildings().size());
    }

    @Test
    public void testAdd() throws InvalidUnitBuildingException {
        ubl1.addUnitBuilding(h2);
        assertEquals(1, ubl1.getUnitBuildings().size());
        //assertEquals(h2, ubl1.getUnitBuildings().get(0));
        assertFalse(ubl1.getUnitBuildings().contains(h1));
        assertTrue(ubl1.getUnitBuildings().contains(h2));
        ubl1.addUnitBuilding(h1);
        assertEquals(2, ubl1.getUnitBuildings().size());
        //assertEquals(h1, ubl1.getUnitBuildings().get(1));
        assertTrue(ubl1.getUnitBuildings().contains(h1));
        assertTrue(ubl1.getUnitBuildings().contains(h2));
    }

    @Test()
    public void testAddException() throws InvalidUnitBuildingException {
        assertEquals(0,ubl2.getUnitBuildings().size());
        ubl2.addUnitBuilding(h2);
        assertEquals(1,ubl2.getUnitBuildings().size());
        ubl2.addUnitBuilding(h2);
        assertEquals(1,ubl2.getUnitBuildings().size());
    }

    @Test
    public void testIterator() throws InvalidUnitBuildingException {
        it1 = ubl1.iterator();
        assertFalse(it1.hasNext());
        ubl1.addUnitBuilding(h2);
        it1 = ubl1.iterator();
        assertTrue(it1.hasNext());
        assertEquals(h2, it1.next());
        assertFalse(it1.hasNext());
        ubl1.addUnitBuilding(h1);
        it1 = ubl1.iterator();
        assertTrue(it1.hasNext());
        UnitBuilding el1 = it1.next();
        assertTrue(h1 == el1 || h2 == el1);
        assertTrue(it1.hasNext());
        UnitBuilding el2 = it1.next();
        assertTrue(el1 != el2 && (h1 == el2 || h2 == el2));
        assertFalse(it1.hasNext());
    }

    @Test
    public void testUnitBuildingsByCriterium() throws InvalidUnitBuildingException, InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        a1 = new Ambulance(name, homeLocation, speed1);

        ubl1.addUnitBuilding(h2);
        ubl1.addUnitBuilding(a1);
        ubl1.addUnitBuilding(h1);

        TypeUnitBuildingEvaluationCriterium tubec = new TypeUnitBuildingEvaluationCriterium(Hospital.class);
        UnitBuildingList ubl2 = ubl1.getUnitBuildingsByCriterium(tubec);

        assertEquals(2, ubl2.getUnitBuildings().size());
        assertTrue(ubl2.getUnitBuildings().contains(h1));
        assertTrue(ubl2.getUnitBuildings().contains(h2));
        assertFalse(ubl2.getUnitBuildings().contains(a1));
        //assertEquals(h2, ubl2.getUnitBuildings().get(0));
        //assertEquals(h1, ubl2.getUnitBuildings().get(1));
    }

    @Test
    public void testSort() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidUnitBuildingException {
        f1 = new Fire(fireLocation, EmergencySeverity.BENIGN, FireSize.LOCAL, true, true, 6);
        hdc = new HospitalDistanceComparator(f1);
        ubl1.addUnitBuilding(h2);
        ubl1.addUnitBuilding(h1);

        ArrayList<Hospital> h = ubl1.sort(hdc);
        assertEquals(2, h.size());
        assertEquals(h1, h.get(0));
        assertEquals(h2, h.get(1));
    }
}
