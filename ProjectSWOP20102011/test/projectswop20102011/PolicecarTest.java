package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class PolicecarTest {
	private long x1,x2,y1,y2,x3,y3,speed1;
	private String name;
	private boolean assigned;
	private GPSCoordinate currentLocation,homeLocation,destination;
	private Policecar politiewagen;

    @Before
    public void setUp(){
		x1 = 14;
		y1 = 2;
		x2 = 20;
		y2 = 90;
		x3 = 13;
		y3 = 2;
		speed1 = 120;
		name = "Politiewagen";
		assigned = false;
		currentLocation = new GPSCoordinate(x1,y1);
		homeLocation = new GPSCoordinate(x2,y2);
		destination = new GPSCoordinate(x3,y3);

	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		politiewagen = new Policecar(name,homeLocation, speed1, currentLocation,destination,assigned);
		assertEquals(name,politiewagen.getName());
		assertEquals(x2,politiewagen.getHomeLocation().getX());
		assertEquals(y2,politiewagen.getHomeLocation().getY());
		assertEquals(speed1,politiewagen.getSpeed());
		assertEquals(x1,politiewagen.getCurrentLocation().getX());
		assertEquals(y1,politiewagen.getCurrentLocation().getY());
		assertEquals(x3,politiewagen.getDestination().getX());
		assertEquals(y3,politiewagen.getDestination().getY());
		assertEquals(assigned,politiewagen.isAssigned());
	}
}
