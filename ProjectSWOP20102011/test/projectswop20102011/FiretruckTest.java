package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class FiretruckTest {
	private long x1,x2,y1,y2,speed1;
	private String name;
	private GPSCoordinate homeLocation;
	private Firetruck brandweerwagen;

    @Before
    public void setUp(){
		x1 = 17;
		y1 = 4;
		x2 = 19;
		y2 = 90;
		speed1 = 70;
		name = "Brandweerwagen";
		homeLocation = new GPSCoordinate(x2,y2);
	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		brandweerwagen = new Firetruck(name,homeLocation, speed1);
		assertEquals(name,brandweerwagen.getName());
		assertEquals(x2,brandweerwagen.getHomeLocation().getX());
		assertEquals(y2,brandweerwagen.getHomeLocation().getY());
		assertEquals(speed1,brandweerwagen.getSpeed());
		assertEquals(x2,brandweerwagen.getCurrentLocation().getX());
		assertEquals(y2,brandweerwagen.getCurrentLocation().getY());
		assertFalse(brandweerwagen.isAssigned());
	}
}
