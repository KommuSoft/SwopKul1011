package projectswop20102011.domain.validators;

import projectswop20102011.domain.validators.TypeUnitValidator;
import java.io.InvalidClassException;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Unit;
import static org.junit.Assert.*;
import projectswop20102011.domain.FireSize;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;

public class TypeUnitValidatorTest {

	private TypeUnitValidator tuv1, tuv2, tuv3, tuv4;
	private Unit u1, u2, u3, u4;
	private String name1, name2, name3, name4;
	private GPSCoordinate gps1, gps2, gps3, gps4;
	private long speed1, speed2, speed3, speed4;
	private long x1, y1, x2, y2, x3, y3, x4, y4;
	private long capacity;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException {
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

		capacity = 100000;

		u1 = new Ambulance(name1, gps1, speed1);
		u2 = new Policecar(name2, gps2, speed2);
		u3 = new Ambulance(name3, gps3, speed3);
		u4 = new Firetruck(name4, gps4, speed4, capacity);
	}

	@Test
	public void testConstructor() throws InvalidClassException {
		tuv1 = new TypeUnitValidator(Ambulance.class);
		tuv2 = new TypeUnitValidator(Policecar.class);
		tuv3 = new TypeUnitValidator(Ambulance.class);
		tuv4 = new TypeUnitValidator(Firetruck.class);

		assertEquals(Ambulance.class, tuv1.getUnitClass());
		assertEquals(Policecar.class, tuv2.getUnitClass());
		assertEquals(Ambulance.class, tuv3.getUnitClass());
		assertEquals(Firetruck.class, tuv4.getUnitClass());
	}

	@Test(expected = InvalidClassException.class)
	public void testIllegalConstructor() throws InvalidClassException {
		tuv1 = new TypeUnitValidator(null);
	}

	@Test
	public void testIsValid() throws InvalidClassException {
		tuv1 = new TypeUnitValidator(Ambulance.class);
		tuv2 = new TypeUnitValidator(Policecar.class);
		tuv3 = new TypeUnitValidator(Ambulance.class);
		tuv4 = new TypeUnitValidator(Firetruck.class);

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
