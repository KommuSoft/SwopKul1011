package projectswop20102011.domain;

import java.io.InvalidClassException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class AndDispatchUnitsConstraintTest {

	NumberDispatchUnitsConstraint c1, c2, c3, c4;
	AndDispatchUnitsConstraint c5, c6;
	UnitValidator uv1, uv2, uv3;
	FiretruckValidator uv4;
	long number1, number2, number3, number4;
	Unit u1, u2, u3, u4;
	String name1, name2, name3, name4;
	GPSCoordinate gps1, gps2, gps3, gps4;
	long speed1, speed2, speed3, speed4;
	FireSize fs1;
	long x1, y1, x2, y2, x3, y3, x4, y4;

	@Before
	public void setUp() throws NumberOutOfBoundsException, InvalidUnitValidatorException, InvalidClassException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException {
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

		fs1 = FireSize.HOUSE;

		u1 = new Ambulance(name1, gps1, speed1);
		u2 = new Policecar(name2, gps2, speed2);
		u3 = new Ambulance(name3, gps3, speed3);
		u4 = new Firetruck(name4, gps4, speed4, fs1);

		uv1 = new TypeUnitValidator(u1.getClass());
		uv2 = new TypeUnitValidator(u2.getClass());
		uv3 = new TypeUnitValidator(u3.getClass());
		uv4 = new FiretruckValidator(fs1);

		number1 = 1;
		number2 = 1;
		number3 = 2;
		number4 = 1;

		c1 = new NumberDispatchUnitsConstraint(uv1, number1);
		c2 = new NumberDispatchUnitsConstraint(uv2, number2);
		c3 = new NumberDispatchUnitsConstraint(uv3, number3);
		c4 = new NumberDispatchUnitsConstraint(uv4, number4);
	}

	@Test
	public void testConstructor() throws InvalidConstraintListException {
		c5 = new AndDispatchUnitsConstraint(c1, c2, c3);

		assertNotNull(c5.getConstraints());
		assertEquals(3, c5.getConstraints().length);

		assertTrue(c1.areValidDispatchUnits(new Unit[]{u1}) == c5.getConstraints()[0].areValidDispatchUnits(new Unit[]{u1}));
		assertTrue(c2.areValidDispatchUnits(new Unit[]{u2}) == c5.getConstraints()[1].areValidDispatchUnits(new Unit[]{u2}));
		assertTrue(c3.areValidDispatchUnits(new Unit[]{u1, u3}) == c5.getConstraints()[2].areValidDispatchUnits(new Unit[]{u1, u3}));
	}

	@Test
	public void testConstructorRecursive() throws InvalidConstraintListException {
		c5 = new AndDispatchUnitsConstraint(c1, c2);
		c6 = new AndDispatchUnitsConstraint(c4, c5);

		assertNotNull(c6.getConstraints());
		assertEquals(2, c6.getConstraints().length);

		assertTrue(c4.areValidDispatchUnits(new Unit[]{u4}) == c6.getConstraints()[0].areValidDispatchUnits(new Unit[]{u4}));
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u1, u2}) == c6.getConstraints()[1].areValidDispatchUnits(new Unit[]{u1, u2}));
	}

	@Test
	public void testAreValidDispatchUnits() throws InvalidConstraintListException {
		c5 = new AndDispatchUnitsConstraint(c1);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u1}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u2}));

		c5 = new AndDispatchUnitsConstraint(c2);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u2}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u1}));

		c5 = new AndDispatchUnitsConstraint(c3);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u1, u3}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u4}));

		c5 = new AndDispatchUnitsConstraint(c4);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u4}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u1}));

		c5 = new AndDispatchUnitsConstraint(c1, c2);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u1, u2}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u2}));

		c5 = new AndDispatchUnitsConstraint(c1, c4);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u1, u4}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u1}));

		c5 = new AndDispatchUnitsConstraint(c2, c3);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u1, u2, u3}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u1, u2}));

		c5 = new AndDispatchUnitsConstraint(c2, c4);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u2, u4}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u2}));

		c5 = new AndDispatchUnitsConstraint(c3, c4);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u1, u3, u4}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u1, u4}));

		c5 = new AndDispatchUnitsConstraint(c1, c2, c4);
		assertTrue(c5.areValidDispatchUnits(new Unit[]{u1, u2, u3, u4}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u1, u2, u4}));

		c5 = new AndDispatchUnitsConstraint(c1, c2);
		c6 = new AndDispatchUnitsConstraint(c4, c5);
		assertTrue(c6.areValidDispatchUnits(new Unit[]{u1, u2, u4}));
		assertFalse(c5.areValidDispatchUnits(new Unit[]{u4, u1}));
	}

	@Test(expected = InvalidConstraintListException.class)
	public void testInvalidConstraintList1() throws InvalidConstraintListException{
		c5 = new AndDispatchUnitsConstraint();
	}

	@Test(expected = InvalidConstraintListException.class)
	public void testInvalidConstraintList2() throws InvalidConstraintListException{
		c6 = null;
		c5 = new AndDispatchUnitsConstraint(c6);
	}
}
