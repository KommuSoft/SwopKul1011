package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.UnitState;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class PoliceCarAdapterTest {
	private Policecar p1, p2;
	private PoliceCarAdapter policeCar1, policeCar2;
	private String name1, name2;
	private GPSCoordinate location1, location2;
	private long speed1, speed2;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
		name1 = "politiewagen1";
		name2 = "politiewagen2";

		location1 = new GPSCoordinate(-10, 10);
		location2 = new GPSCoordinate(6, 9);

		speed1 = 125;
		speed2 = 89;
		
		p1 = new Policecar(name1, location1, speed1);
		p2 = new Policecar(name2, location2, speed2);

	}

	@Test
	public void testConstructor() {
		policeCar1 = new PoliceCarAdapter(p1);
		policeCar2 = new PoliceCarAdapter(p2);
		
		assertEquals(name1, policeCar1.getName());
		assertEquals(name2, policeCar2.getName());
		
		assertEquals(location1.getX(), policeCar1.getHomeLocation().getX());
		assertEquals(location1.getY(), policeCar1.getHomeLocation().getY());
		assertEquals(location2.getX(), policeCar2.getHomeLocation().getX());
		assertEquals(location2.getY(), policeCar2.getHomeLocation().getY());
		
		assertEquals(location1.getX(), policeCar1.getLocation().getX());
		assertEquals(location1.getY(), policeCar1.getLocation().getY());
		assertEquals(location2.getX(), policeCar2.getLocation().getX());
		assertEquals(location2.getY(), policeCar2.getLocation().getY());
		
		assertEquals(UnitState.IDLE, policeCar1.getState());
		assertEquals(UnitState.IDLE, policeCar2.getState());
	}
}
