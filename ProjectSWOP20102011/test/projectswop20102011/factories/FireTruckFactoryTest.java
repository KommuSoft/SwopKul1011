package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;

public class FireTruckFactoryTest {

	private String name1;
	private String homeLocation1;
	private String speed1;
	private String s1;
	private FiretruckFactory ftf;

	@Before
	public void setUp() {
		name1 = "Brandweerwagen";
		homeLocation1 = "(15,15)";
		speed1 = "5";

		s1 = name1 + homeLocation1 + speed1;
	}

	@Test
	public void testConstructor() throws InvalidMapItemTypeNameException {
		ftf = new FiretruckFactory();
	}

	@Test
	public void testCreateMapItem() throws InvalidMapItemTypeNameException {
		ftf = new FiretruckFactory();

		Firetruck ft = ftf.createMapItem(s1);
		assertEquals(name1, ft.getName());
		assertEquals(homeLocation1, ft.getHomeLocation());
		assertEquals(speed1, ft.getSpeed());
	}
}
