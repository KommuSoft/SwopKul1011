package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitNameException;

public class FiretruckTest {
	private long x1,x2,y1,y2;
	private String name;
	private boolean assigned;
	private GPSCoordinate currentLocation,homeLocation;
	private Firetruck brandweerwagen;

    @Before
    public void setUp(){
		x1 = 17;
		y1 = 4;
		x2 = 19;
		y2 = 90;
		name = "Brandweerwagen";
		assigned = true;
		currentLocation = new GPSCoordinate(x1,y1);
		homeLocation = new GPSCoordinate(x2,x2);

	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitNameException{
		brandweerwagen = new Firetruck(name,assigned,currentLocation,homeLocation);
		assertEquals(name,brandweerwagen.getName());
		assertEquals(assigned,brandweerwagen.isAssigned());
		assertEquals(x1,brandweerwagen.getCurrentLocation().getX());
		assertEquals(y1,brandweerwagen.getCurrentLocation().getY());
		assertEquals(x2,brandweerwagen.getHomeLocation().getX());
		assertEquals(y2,brandweerwagen.getHomeLocation().getY());
	}
}
