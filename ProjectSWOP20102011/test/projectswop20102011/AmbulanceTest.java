package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitNameException;

public class AmbulanceTest {
	private long x1,x2,y1,y2;
	private String name;
	private boolean assigned;
	private GPSCoordinate currentLocation,homeLocation;
	private Ambulance ziekenwagen;

    @Before
    public void setUp(){
		x1 = 11;
		y1 = 4;
		x2 = 19;
		y2 = 90;
		name = "Ziekenwagen";
		assigned = false;
		currentLocation = new GPSCoordinate(x1,y1);
		homeLocation = new GPSCoordinate(x2,x2);

	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitNameException{
		ziekenwagen = new Ambulance(name,assigned,currentLocation,homeLocation);
		assertEquals(name,ziekenwagen.getName());
		assertEquals(assigned,ziekenwagen.isAssigned());
		assertEquals(x1,ziekenwagen.getCurrentLocation().getX());
		assertEquals(y1,ziekenwagen.getCurrentLocation().getY());
		assertEquals(x2,ziekenwagen.getHomeLocation().getX());
		assertEquals(y2,ziekenwagen.getHomeLocation().getY());
	}
}
