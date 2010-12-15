package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Policecar;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

public class PoliceCarFactoryTest {

	private String name1;
	private String homeLocation1;
	private String speed1;
	private String s1;
	private PolicecarFactory pcf;

	@Before
	public void setUp() {
		name1 = "Politieauto";
		homeLocation1 = "(15,15)";
		speed1 = "5";

		s1 = name1 + homeLocation1 + speed1;
	}

	@Test
	public void testConstructor() throws InvalidMapItemTypeNameException {
		pcf = new PolicecarFactory();
	}

	@Test
	public void testCreateMapItem() throws InvalidMapItemTypeNameException {
		pcf = new PolicecarFactory();

		Policecar pc = pcf.createMapItem(s1);
		assertEquals(name1, pc.getName());
		assertEquals(homeLocation1, pc.getHomeLocation());
		assertEquals(speed1, pc.getSpeed());
	}
}
