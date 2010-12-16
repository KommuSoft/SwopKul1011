package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

public class AmbulanceFactoryTest {

	private long x1, y1;
	private String name1;
	private GPSCoordinate homeLocation1;
	private long speed1;
	private AmbulanceFactory af;

	@Before
	public void setUp() {
		name1 = "Ambulance";
		homeLocation1 = new GPSCoordinate(x1, y1);
		speed1 = 5;
	}

	@Test
	public void testConstructor() throws InvalidMapItemTypeNameException {
		af = new AmbulanceFactory();
	}

	@Test
	public void testCreateMapItem() throws InvalidMapItemTypeNameException {
		af = new AmbulanceFactory();

		Ambulance a = af.createMapItem(new Object[] {name1, homeLocation1, speed1});
		assertEquals(name1, a.getName());
		assertEquals(homeLocation1, a.getHomeLocation());
		assertEquals(speed1, a.getSpeed());
	}

	@Test
	public void testGetMapItemTypeName() throws InvalidMapItemTypeNameException{
		af = new AmbulanceFactory();

		assertEquals("ambulance", af.getMapItemTypeName());
	}
}
