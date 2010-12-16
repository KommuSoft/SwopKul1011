package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

public class HospitalFactoryTest {

	private long x1, y1;
	private String name1;
	private GPSCoordinate homeLocation1;
	private HospitalFactory hf;

	@Before
	public void setUp() {
		x1 = 10;
		y1 = 10;
		name1 = "ziekenhuis";
		homeLocation1 = new GPSCoordinate(x1, y1);
	}

	@Test
	public void testConstructor() throws InvalidMapItemTypeNameException {
		hf = new HospitalFactory();
	}

	@Test
	public void testCreateMapItem() throws InvalidMapItemTypeNameException {
		hf = new HospitalFactory();

		Hospital h = hf.createMapItem(new Object[]{name1, homeLocation1});
		assertEquals(name1, h.getName());
		assertEquals(homeLocation1, h.getHomeLocation());
	}
}
