package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.UnitState;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class FireTruckAdapterTest {
	
	private Firetruck ft1, ft2;
	private FireTruckAdapter fireTruck1, fireTruck2;
	private String name1, name2;
	private GPSCoordinate location1, location2;
	private long speed1, speed2;
	private long capacity1, capacity2;
	

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException {
		name1 = "Brandweerwagen1";
		name2 = "Brandweerwagen2";

		location1 = new GPSCoordinate(-10, 10);
		location2 = new GPSCoordinate(6, 9);

		speed1 = 125;
		speed2 = 89;
		
		capacity1= 1234;
		capacity2=123456;
		
		ft1 = new Firetruck(name1, location1, speed1, capacity1);
		ft2 = new Firetruck(name2, location2, speed2, capacity2);
	}

	@Test
	public void testConstructor() {
		fireTruck1 = new FireTruckAdapter(ft1);
		fireTruck2 = new FireTruckAdapter(ft2);
		
		assertEquals(name1, fireTruck1.getName());
		assertEquals(name2, fireTruck2.getName());
		
		assertEquals(location1.getX(), fireTruck1.getHomeLocation().getX());
		assertEquals(location1.getY(), fireTruck1.getHomeLocation().getY());
		assertEquals(location2.getX(), fireTruck2.getHomeLocation().getX());
		assertEquals(location2.getY(), fireTruck2.getHomeLocation().getY());
		
		assertEquals(location1.getX(), fireTruck1.getLocation().getX());
		assertEquals(location1.getY(), fireTruck1.getLocation().getY());
		assertEquals(location2.getX(), fireTruck2.getLocation().getX());
		assertEquals(location2.getY(), fireTruck2.getLocation().getY());
		
		assertEquals(UnitState.IDLE, fireTruck1.getState());
		assertEquals(UnitState.IDLE, fireTruck2.getState());
		
		assertEquals(capacity1, fireTruck1.getCapacity());
		assertEquals(capacity2, fireTruck2.getCapacity());
	}
}
