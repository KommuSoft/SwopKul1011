package projectswop20102011.domain;

import java.io.InvalidClassException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class FiretruckValidatorTest {

	FireSize fs1, fs2, fs3;
	FireSize fts1, fts2, fts3;
	Firetruck ft1, ft2, ft3;
	String name1, name2, name3;
	GPSCoordinate gps1, gps2, gps3;
	long x1, y1, x2, y2, x3, y3;
	long speed1, speed2, speed3;
	FiretruckValidator ftv1, ftv2, ftv3;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException {
		fs1 = FireSize.HOUSE;
		fs2 = FireSize.LOCAL;
		fs3 = FireSize.FACILITY;

		fts1 = FireSize.HOUSE;
		fts2 = FireSize.LOCAL;
		fts3 = FireSize.FACILITY;

		name1 = "Mini-Vuurwagen";
		name2 = "Vuurwagen";
		name3 = "Maxi-Vuurwagen";

		x1 = 10;
		y1 = 10;
		x2 = 20;
		y2 = 20;
		x3 = 30;
		y3 = 30;

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = new GPSCoordinate(x2, y2);
		gps3 = new GPSCoordinate(x3, y3);

		speed1 = 10 * 3600;
		speed2 = 7 * 3600;
		speed3 = 5 * 3600;

		ft1 = new Firetruck(name1, gps1, speed1, fts1);
		ft2 = new Firetruck(name2, gps2, speed2, fts2);
		ft3 = new Firetruck(name3, gps3, speed3, fts3);
	}

	@Test
	public void testConstructor() throws InvalidClassException {
		ftv1 = new FiretruckValidator(fs1);
		ftv2 = new FiretruckValidator(fs2);
		ftv3 = new FiretruckValidator(fs3);

		assertEquals(fs1, ftv1.getFireSize());
		assertEquals(fs2, ftv2.getFireSize());
		assertEquals(fs3, ftv3.getFireSize());
	}

	@Test
	public void testIsValid() throws InvalidClassException {
		ftv1 = new FiretruckValidator(fs1);
		ftv2 = new FiretruckValidator(fs2);
		ftv3 = new FiretruckValidator(fs3);

		assertTrue(ftv1.isValid(ft1));
		assertTrue(ftv1.isValid(ft2));
		assertTrue(ftv1.isValid(ft3));

		assertFalse(ftv2.isValid(ft1));
		assertTrue(ftv2.isValid(ft2));
		assertTrue(ftv2.isValid(ft3));

		assertFalse(ftv3.isValid(ft1));
		assertFalse(ftv3.isValid(ft2));
		assertTrue(ftv3.isValid(ft3));
	}
}
