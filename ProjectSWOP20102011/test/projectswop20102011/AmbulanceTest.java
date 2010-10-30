package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class AmbulanceTest {
	private long x1,x2,y1,y2,x3,y3,speed1;
	private String name;
	private boolean assigned;
	private GPSCoordinate currentLocation,homeLocation,destination;
	private Ambulance ziekenwagen;

    @Before
    public void setUp(){
		x1 = 11;
		y1 = 4;
		x2 = 19;
		y2 = 90;
		x3 = 13;
		y3 = 2;
		speed1 = 120;
		name = "Ziekenwagen";
		assigned = false;
		currentLocation = new GPSCoordinate(x1,y1);
		homeLocation = new GPSCoordinate(x2,y2);
		destination = new GPSCoordinate(x3,y3);

	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		ziekenwagen = new Ambulance(name,homeLocation, speed1, currentLocation,destination,assigned);
		assertEquals(name,ziekenwagen.getName());
		assertEquals(x2,ziekenwagen.getHomeLocation().getX());
		assertEquals(y2,ziekenwagen.getHomeLocation().getY());
		assertEquals(speed1,ziekenwagen.getSpeed());
		assertEquals(x1,ziekenwagen.getCurrentLocation().getX());
		assertEquals(y1,ziekenwagen.getCurrentLocation().getY());
		assertEquals(x3,ziekenwagen.getDestination().getX());
		assertEquals(y3,ziekenwagen.getDestination().getY());
		assertEquals(assigned,ziekenwagen.isAssigned());
	}
}
