package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class PoliceCarFactoryTest {

	private long x1, y1;
	private String name1;
	private GPSCoordinate homeLocation1;
	private long speed1;
	private PolicecarFactory pcf;

	@Before
	public void setUp() {
		x1 = 15;
		y1 = 15;
		name1 = "Politieauto";
		homeLocation1 = new GPSCoordinate(x1, y1);
		speed1 = 5;
	}

	@Test
	public void testConstructor() throws InvalidMapItemTypeNameException {
		pcf = new PolicecarFactory();
	}

	@Test
	public void testCreateMapItem() throws InvalidMapItemTypeNameException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException {
		pcf = new PolicecarFactory();

		Policecar pc = pcf.createMapItem(new Object[] {name1, homeLocation1, speed1});
		assertEquals(name1, pc.getName());
		assertEquals(homeLocation1, pc.getHomeLocation());
		assertEquals(speed1, pc.getSpeed());
	}
}
