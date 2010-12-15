package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Hospital;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

public class HospitalFactoryTest {

	private String name1;
	private String homeLocation1;
	private String s1;
	private HospitalFactory hf;

	@Before
	public void setUp() {
		name1 = "ziekenhuis";
		homeLocation1 = "(10,10)";
		s1 = name1 + homeLocation1;
	}

	@Test
	public void testConstructor() throws InvalidMapItemTypeNameException {
		hf = new HospitalFactory();
	}

	@Test
	public void testCreateMapItem() throws InvalidMapItemTypeNameException {
		hf = new HospitalFactory();

		Hospital h = hf.createMapItem(s1);
		assertEquals(name1, h.getName());
		assertEquals(homeLocation1, h.getHomeLocation());
	}
}
