package projectswop20102011;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidTimeSensitiveException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class TimeSensitiveListTest {

    private TimeSensitiveList tsl1, tsl2;
    private Iterator<TimeSensitive> it1;
    private Ambulance a1, a2;
    private String name1, name2;
    private GPSCoordinate homeLocation1, homeLocation2, emergencyLocation;
    private long speed1, speed2;
    private long hx1, hy1, hx2, hy2, ex, ey;
	private Emergency f1;
	private long duration;

    @Before
    public void setUp() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        hx1 = 11;
        hy1 = 4;
        homeLocation1 = new GPSCoordinate(hx1, hy1);
        name1 = "Eir";
        speed1 = 90;
        a1 = new Ambulance(name1, homeLocation1, speed1);

        hx2 = 520;
        hy2 = 64;
        homeLocation2 = new GPSCoordinate(hx2, hy2);
        name2 = "Sleipnir";
        speed2 = 90000;
        a2 = new Ambulance(name2, homeLocation2, speed2);

		ex = 1302;
		ey = 2031;
		duration = 9*3600;
		emergencyLocation = new GPSCoordinate(ex, ey);

        tsl1 = new TimeSensitiveList();
        tsl2 = new TimeSensitiveList();
    }

    @Test
    public void testConstructor() {
        assertNotNull(tsl1);
        assertEquals(0, tsl1.getTimeSensitives().size());
    }

    @Test
    public void testAdd() throws InvalidTimeSensitiveException {
        tsl1.addTimeSensitive(a1);
        assertEquals(1, tsl1.getTimeSensitives().size());
        assertTrue(tsl1.getTimeSensitives().contains(a1));
        assertFalse(tsl1.getTimeSensitives().contains(a2));
        //assertEquals(a1, tsl1.getTimeSensitives().get(0));
        tsl1.addTimeSensitive(a2);
        assertEquals(2, tsl1.getTimeSensitives().size());
        //ssertEquals(a2, tsl1.getTimeSensitives().get(1));
        assertTrue(tsl1.getTimeSensitives().contains(a1));
        assertTrue(tsl1.getTimeSensitives().contains(a2));
    }

    @Test()
    public void testAddException() throws InvalidTimeSensitiveException {
        assertEquals(0,tsl2.getTimeSensitives().size());
        tsl2.addTimeSensitive(a1);
        assertEquals(1,tsl2.getTimeSensitives().size());
        tsl2.addTimeSensitive(a1);
        assertEquals(1,tsl2.getTimeSensitives().size());
    }

    @Test
    public void testIterator() throws InvalidTimeSensitiveException {
        it1 = tsl1.iterator();
        assertFalse(it1.hasNext());

        tsl1.addTimeSensitive(a1);
        it1 = tsl1.iterator();
        assertTrue(it1.hasNext());
        assertEquals(a1, it1.next());
        assertFalse(it1.hasNext());

        tsl1.addTimeSensitive(a2);
        it1 = tsl1.iterator();
        assertTrue(it1.hasNext());
        TimeSensitive element1 = it1.next();
        assertTrue(a1 == element1 || a2 == element1);
        assertTrue(it1.hasNext());
        TimeSensitive element2 = it1.next();
        assertTrue(element1 != element2 && (element2 == a1 || element2 == a2));
        assertFalse(it1.hasNext());
    }

	@Test
	public void testTimeAhead() throws InvalidLocationException, InvalidEmergencySeverityException,
			InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyStatusException, InvalidDurationException, InvalidAmbulanceException{
		f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, FireSize.LOCAL, false, 0, 1337);

		a1.assignTo(f1);
		a2.assignTo(f1);
		tsl1.addTimeSensitive(a1);
        tsl1.addTimeSensitive(a2);

		tsl1.timeAhead((int) duration);

		assertEquals(446,a1.getCurrentLocation().getX());
		assertEquals(687,a1.getCurrentLocation().getY());
		assertEquals(1302,a2.getCurrentLocation().getX());
		assertEquals(2031,a2.getCurrentLocation().getY());
	}
}
