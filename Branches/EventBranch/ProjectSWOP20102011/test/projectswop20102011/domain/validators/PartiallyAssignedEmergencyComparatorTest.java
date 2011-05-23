package projectswop20102011.domain.validators;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Robbery;
import projectswop20102011.domain.TrafficAccident;
import projectswop20102011.domain.Unit;
import projectswop20102011.eventhandlers.NullEventHandler;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PartiallyAssignedEmergencyComparatorTest {

    public PartiallyAssignedEmergencyComparatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of compare method, of class PartiallyAssignedEmergencyComparator.
     */
    @Test
    public void testCompare() throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException, InvalidSendableStatusException, Exception {
        PartiallyAssignedEmergencyComparator paect = new PartiallyAssignedEmergencyComparator();
        Emergency e1 = new Robbery(new GPSCoordinate(0,0),SendableSeverity.BENIGN,"",true,true);
        Emergency e2 = new TrafficAccident(new GPSCoordinate(0,0),SendableSeverity.URGENT,"",3,0);
        assertEquals(0,paect.compare(e1, e2));

        Firetruck ft1 = new Firetruck("engine1",new GPSCoordinate(5,0),180,500000);

        HashSet<Unit> alloc1 = new HashSet<Unit>();
        alloc1.add(ft1);

        e2.assignUnits(alloc1, new NullEventHandler());//e1 is partially allocated

        assertTrue(paect.compare(e1, e2)>0);
        assertTrue(paect.compare(e2, e1)<0);

        Policecar pc1 = new Policecar("unit1",new GPSCoordinate(5,0),180);
        Policecar pc2 = new Policecar("unit2",new GPSCoordinate(5,0),180);

        HashSet<Unit> alloc2 = new HashSet<Unit>();
        alloc2.add(pc1);
        alloc2.add(pc2);

        e2.assignUnits(alloc2, new NullEventHandler());//e2 is totally assigned

        assertEquals(0,paect.compare(e1, e2));
        assertEquals(0,paect.compare(e2, e1));

        Policecar pc3 = new Policecar("unit3",new GPSCoordinate(5,0),180);
        Policecar pc4 = new Policecar("unit4",new GPSCoordinate(5,0),180);

        HashSet<Unit> alloc3 = new HashSet<Unit>();
        alloc3.add(pc3);
        alloc3.add(pc4);

        e1.assignUnits(alloc3, new NullEventHandler());//e1 is partially allocated

        assertTrue(paect.compare(e1, e2)<0);
        assertTrue(paect.compare(e2, e1)>0);

        Policecar pc5 = new Policecar("unit5",new GPSCoordinate(5,0),180);

        HashSet<Unit> alloc4 = new HashSet<Unit>();
        alloc4.add(pc5);

        e1.assignUnits(alloc4, new NullEventHandler());//e1 is totally assigned

        assertEquals(0,paect.compare(e1, e2));
        assertEquals(0,paect.compare(e2, e1));

    }

}