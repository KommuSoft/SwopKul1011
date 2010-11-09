package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class AmbulanceTest {

    private long x2, y2, x3, y3, speed1, duration1, duration2;
    private String name;
    private GPSCoordinate homeLocation, emergencyLocation;
    private Ambulance ziekenwagen;
    private Emergency f1;
    private long speed2;

    @Before
    public void setUp() {
        x2 = 11;
        y2 = 4;
        x3 = 1302;
        y3 = 2031;
        speed1 = 90;
        speed2 = -96;
        duration1 = 9 * 3600;
        duration2 = -5653;
        name = "Ziekenwagen";
        homeLocation = new GPSCoordinate(x2, y2);
        emergencyLocation = new GPSCoordinate(x3, y3);
    }

    @Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        ziekenwagen = new Ambulance(name, homeLocation, speed1);
        assertEquals(name, ziekenwagen.getName());
        assertEquals(x2, ziekenwagen.getHomeLocation().getX());
        assertEquals(y2, ziekenwagen.getHomeLocation().getY());
        assertEquals(speed1, ziekenwagen.getSpeed());
        assertEquals(x2, ziekenwagen.getCurrentLocation().getX());
        assertEquals(y2, ziekenwagen.getCurrentLocation().getY());
        assertFalse(ziekenwagen.isAssigned());
        assertFalse(ziekenwagen.isAtDestination());
        assertEquals(homeLocation, ziekenwagen.getDestination());
    }

    @Test(expected = InvalidSpeedException.class)
    public void testInValidSpeedConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        ziekenwagen = new Ambulance(name, homeLocation, speed2);
    }
}
