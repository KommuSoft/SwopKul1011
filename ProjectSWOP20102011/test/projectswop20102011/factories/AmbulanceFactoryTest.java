package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

public class AmbulanceFactoryTest {

	private String name1;
	private String homeLocation1;
	private String speed1;
	private String s1;
	private AmbulanceFactory af;

	@Before
	public void setUp() {
		name1 = "Ambulance";
		homeLocation1 = "(15,15)";
		speed1 = "5";

		s1 = name1 + homeLocation1 + speed1;
	}

	@Test
	public void testConstructor() throws InvalidMapItemTypeNameException {
		af = new AmbulanceFactory();
	}

	@Test
	public void testCreateMapItem() throws InvalidMapItemTypeNameException {
		af = new AmbulanceFactory();

		Ambulance a = af.createMapItem(s1);
		assertEquals(name1, a.getName());
		assertEquals(homeLocation1, a.getHomeLocation());
		assertEquals(speed1, a.getSpeed());
	}
}
