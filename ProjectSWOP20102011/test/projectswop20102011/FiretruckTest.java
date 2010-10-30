package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class FiretruckTest {
	private long x1,x2,y1,y2,x3,y3,speed1;
	private String name;
	private boolean assigned;
	private GPSCoordinate currentLocation,homeLocation,destination;
	private Firetruck brandweerwagen;

    @Before
    public void setUp(){
		x1 = 17;
		y1 = 4;
		x2 = 19;
		y2 = 90;
		x3 = 40;
		y3 = 11;
		speed1 = 70;
		name = "Brandweerwagen";
		assigned = true;
		currentLocation = new GPSCoordinate(x1,y1);
		homeLocation = new GPSCoordinate(x2,y2);
		destination = null;

	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		brandweerwagen = new Firetruck(name,homeLocation, speed1, currentLocation,destination,assigned);
		assertEquals(name,brandweerwagen.getName());
		assertEquals(x2,brandweerwagen.getHomeLocation().getX());
		assertEquals(y2,brandweerwagen.getHomeLocation().getY());
		assertEquals(speed1,brandweerwagen.getSpeed());
		assertEquals(x1,brandweerwagen.getCurrentLocation().getX());
		assertEquals(y1,brandweerwagen.getCurrentLocation().getY());
		assertEquals(destination,brandweerwagen.getDestination());
		assertEquals(assigned,brandweerwagen.isAssigned());
	}
}
