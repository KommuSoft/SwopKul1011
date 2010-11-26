package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;

public class PolicecarTest {
	private long x2,y2,speed1;
	private String name;
	private GPSCoordinate homeLocation;
	private Policecar politiewagen;

    @Before
    public void setUp(){
		x2 = 20;
		y2 = 90;
		speed1 = 120;
		name = "Politiewagen";
		homeLocation = new GPSCoordinate(x2,y2);
	}

	@Test
    public void testValidConstructor() throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException{
		politiewagen = new Policecar(name,homeLocation, speed1);
		assertEquals(name,politiewagen.getName());
		assertEquals(x2,politiewagen.getHomeLocation().getX());
		assertEquals(y2,politiewagen.getHomeLocation().getY());
		assertEquals(speed1,politiewagen.getSpeed());
		assertEquals(homeLocation, politiewagen.getCurrentLocation());
		assertFalse(politiewagen.isAssigned());
	}
}
