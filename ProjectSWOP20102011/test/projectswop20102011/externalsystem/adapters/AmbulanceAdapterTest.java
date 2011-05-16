package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.UnitState;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class AmbulanceAdapterTest {

	private Ambulance am1, am2;
	private AmbulanceAdapter ambulance1, ambulance2;
	private String name1, name2;
	private GPSCoordinate location1, location2;
	private long speed1, speed2;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
		name1 = "Ziekenwagen1";
		name2 = "Ziekenwagen2";

		location1 = new GPSCoordinate(-10, 10);
		location2 = new GPSCoordinate(6, 9);

		speed1 = 125;
		speed2 = 89;
		
		am1 = new Ambulance(name1, location1, speed1);
		am2 = new Ambulance(name2, location2, speed2);

	}

	@Test
	public void testConstructor() {
		ambulance1 = new AmbulanceAdapter(am1);
		ambulance2 = new AmbulanceAdapter(am2);
		
		assertEquals(name1, ambulance1.getName());
		assertEquals(name2, ambulance2.getName());
		
		assertEquals(location1.getX(), ambulance1.getHomeLocation().getX());
		assertEquals(location1.getY(), ambulance1.getHomeLocation().getY());
		assertEquals(location2.getX(), ambulance2.getHomeLocation().getX());
		assertEquals(location2.getY(), ambulance2.getHomeLocation().getY());
		
		assertEquals(location1.getX(), ambulance1.getLocation().getX());
		assertEquals(location1.getY(), ambulance1.getLocation().getY());
		assertEquals(location2.getX(), ambulance2.getLocation().getX());
		assertEquals(location2.getY(), ambulance2.getLocation().getY());
		
		assertEquals(UnitState.IDLE, ambulance1.getState());
		assertEquals(UnitState.IDLE, ambulance2.getState());
		
		assertFalse(ambulance1.isOccupied());
		assertFalse(ambulance2.isOccupied());
	}
}
