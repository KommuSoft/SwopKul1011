package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitNameException;

public class PolicecarTest {
	private long x1,x2,y1,y2;
	private String name;
	private boolean assigned;
	private GPSCoordinate currentLocation,homeLocation;
	private Policecar politiewagen;

    @Before
    public void setUp(){
		x1 = 14;
		y1 = 2;
		x2 = 20;
		y2 = 90;
		name = "Politiewagen";
		assigned = false;
		currentLocation = new GPSCoordinate(x1,y1);
		homeLocation = new GPSCoordinate(x2,y2);

	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitNameException{
		politiewagen = new Policecar(name,assigned,currentLocation,homeLocation);
		assertEquals(name,politiewagen.getName());
		assertEquals(assigned,politiewagen.isAssigned());
		assertEquals(x1,politiewagen.getCurrentLocation().getX());
		assertEquals(y1,politiewagen.getCurrentLocation().getY());
		assertEquals(x2,politiewagen.getHomeLocation().getX());
		assertEquals(y2,politiewagen.getHomeLocation().getY());
	}
}
