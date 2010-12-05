package projectswop20102011.domain;

import java.io.InvalidClassException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class TypeUnitValidatorTest {

	TypeUnitValidator tuv1, tuv2, tuv3, tuv4;
	long number1, number2, number3, number4;
	Unit u1, u2, u3, u4;
	String name1, name2, name3, name4;
	GPSCoordinate gps1, gps2, gps3, gps4;
	long speed1, speed2, speed3, speed4;
	FireSize fs1;
	long x1, y1, x2, y2, x3, y3, x4, y4;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException {
		x1 = 10;
		y1 = 10;
		x2 = 20;
		y2 = 20;
		x3 = -10;
		y3 = -10;
		x4 = -20;
		y4 = -20;

		name1 = "ambulanceOne";
		name2 = "policecar";
		name3 = "ambulanceTwo";
		name4 = "firetruck";

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = new GPSCoordinate(x2, y2);
		gps3 = new GPSCoordinate(x3, y3);
		gps4 = new GPSCoordinate(x4, y4);

		speed1 = 5 * 3600;
		speed2 = 4 * 3600;
		speed3 = 3 * 3600;
		speed4 = 2 * 3600;

		u1 = new Ambulance(name1, gps1, speed1);
		u2 = new Policecar(name2, gps2, speed2);
		u3 = new Ambulance(name3, gps3, speed3);
		u4 = new Firetruck(name4, gps4, speed4);
	}

	@Test
	public void testConstructor() throws InvalidClassException {
		tuv1 = new TypeUnitValidator(u1.getClass());
		tuv2 = new TypeUnitValidator(u2.getClass());
		tuv3 = new TypeUnitValidator(u3.getClass());
		tuv4 = new TypeUnitValidator(u4.getClass());

		assertEquals(u1.getClass(), tuv1.getUnitClass());
		assertEquals(u2.getClass(), tuv2.getUnitClass());
		assertEquals(u3.getClass(), tuv3.getUnitClass());
		assertEquals(u4.getClass(), tuv4.getUnitClass());
	}

	@Test(expected = InvalidClassException.class)
	public void testIllegalConstructor() throws InvalidClassException {
		tuv1 = new TypeUnitValidator(null);
	}

	@Test
	public void testIsValid() throws InvalidClassException {
		tuv1 = new TypeUnitValidator(u1.getClass());
		tuv2 = new TypeUnitValidator(u2.getClass());
		tuv3 = new TypeUnitValidator(u3.getClass());
		tuv4 = new TypeUnitValidator(u4.getClass());

		assertTrue(tuv1.isValid(u1));
		assertTrue(tuv1.isValid(u3));
		assertTrue(tuv2.isValid(u2));
		assertTrue(tuv3.isValid(u3));
		assertTrue(tuv3.isValid(u1));
		assertTrue(tuv4.isValid(u4));

		assertFalse(tuv1.isValid(u2));
		assertFalse(tuv1.isValid(u4));
		assertFalse(tuv2.isValid(u1));
		assertFalse(tuv2.isValid(u3));
		assertFalse(tuv2.isValid(u4));
		assertFalse(tuv3.isValid(u2));
		assertFalse(tuv3.isValid(u4));
		assertFalse(tuv4.isValid(u1));
		assertFalse(tuv4.isValid(u2));
		assertFalse(tuv4.isValid(u3));
	}
}
