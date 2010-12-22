package projectswop20102011.factories;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class FiretruckFactoryTest {

	private long x1, y1;
	private String name1;
	private GPSCoordinate homeLocation1;
	private long speed1;
        private FireSize fireSize1;
	private FiretruckFactory ftf;

	@Before
	public void setUp() {
		x1 = 15;
		y1 = 15;
		name1 = "Brandweerwagen";
		homeLocation1 = new GPSCoordinate(x1,y1);
		speed1 = 5;
                fireSize1 = FireSize.FACILITY;
	}

	@Test
	public void testConstructor() throws InvalidMapItemTypeNameException {
		ftf = new FiretruckFactory();
	}

	@Test
	public void testCreateMapItem() throws InvalidMapItemTypeNameException, InvalidLocationException, InvalidSpeedException, InvalidMapItemNameException, InvalidAmountOfParametersException, InvalidFireSizeException {
		ftf = new FiretruckFactory();

		Firetruck ft = ftf.createMapItem(new Object[] {name1, homeLocation1, speed1,fireSize1});
		assertEquals(name1, ft.getName());
		assertEquals(homeLocation1, ft.getHomeLocation());
		assertEquals(speed1, ft.getSpeed());
                assertEquals(fireSize1,ft.getMaxSize());
	}

	@Test(expected = InvalidAmountOfParametersException.class)
	public void testCreateMapItemInvalidAmount() throws InvalidMapItemTypeNameException, InvalidLocationException, InvalidSpeedException, InvalidMapItemNameException, InvalidAmountOfParametersException, InvalidFireSizeException {
		ftf = new FiretruckFactory();
		Firetruck ft = ftf.createMapItem(new Object[] {name1, homeLocation1});
	}
}
