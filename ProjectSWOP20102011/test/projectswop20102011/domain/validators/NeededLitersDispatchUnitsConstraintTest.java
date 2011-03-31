package projectswop20102011.domain.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InvalidClassException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class NeededLitersDispatchUnitsConstraintTest {

	private NumberDispatchUnitsConstraint c1, c2;
	private NeededLitersDispatchUnitsConstraint c4, c5, c6;
	private AndDispatchUnitsConstraint c7;
	private UnitValidator uv1, uv2, uv3;
	private long number1, number2;
	private Unit u1, u2, u3, u4, u5;
	private String name1, name2, name3, name4, name5;
	private GPSCoordinate gps1, gps2, gps3, gps4, gps5;
	private long speed1, speed2, speed3, speed4, speed5;
	private long capacity1, capacity2, capacity3, capacity4;
	private long numberOfLitersRequired1, numberOfLitersRequired2, numberOfLitersRequired3;
	private long x1, y1, x2, y2, x3, y3, x4, y4, x5, y5;

	@Before
	public void setUp() throws NumberOutOfBoundsException,
			InvalidUnitValidatorException, InvalidClassException,
			InvalidLocationException, InvalidMapItemNameException,
			InvalidSpeedException, InvalidFireSizeException,
			InvalidCapacityException {
		x1 = 10;
		y1 = 10;
		x2 = 20;
		y2 = 20;
		x3 = -10;
		y3 = -10;
		x4 = -20;
		y4 = -20;
		x5 = -69;
		y5 = 69;

		name1 = "policecar1";
		name2 = "firetruck2";
		name3 = "firetruck3";
		name4 = "firetruck4";
		name5 = "firetruck5";

		gps1 = new GPSCoordinate(x1, y1);
		gps2 = new GPSCoordinate(x2, y2);
		gps3 = new GPSCoordinate(x3, y3);
		gps4 = new GPSCoordinate(x4, y4);
		gps5 = new GPSCoordinate(x5, y5);

		speed1 = 5 * 3600;
		speed2 = 4 * 3600;
		speed3 = 3 * 3600;
		speed4 = 2 * 3600;
		speed5 = 8 * 3600;

		capacity1 = 1000;
		capacity2 = 100000;
		capacity3 = 500000;
		capacity4 = 400000;

		u1 = new Policecar(name1, gps1, speed1);
		u2 = new Firetruck(name2, gps2, speed2, capacity1);
		u3 = new Firetruck(name3, gps3, speed3, capacity2);
		u4 = new Firetruck(name4, gps4, speed4, capacity3);
		u5 = new Firetruck(name5, gps5, speed5, capacity4);

		uv1 = new TypeUnitValidator(Ambulance.class);
		uv2 = new TypeUnitValidator(Policecar.class);
		uv3 = new TypeUnitValidator(Firetruck.class);

		number1 = 3;
		number2 = 1;

		c1 = new NumberDispatchUnitsConstraint(uv1, number1);
		c2 = new NumberDispatchUnitsConstraint(uv2, number2);

		numberOfLitersRequired1 = 1000;
		numberOfLitersRequired2 = 100000;
		numberOfLitersRequired3 = 500000;

		c4 = new NeededLitersDispatchUnitsConstraint(uv3, numberOfLitersRequired1);
		c5 = new NeededLitersDispatchUnitsConstraint(uv3, numberOfLitersRequired2);
		c6 = new NeededLitersDispatchUnitsConstraint(uv3, numberOfLitersRequired3);

	}

	@Test
	public void testAreValidDispatchUnits() throws InvalidConstraintListException{
		ArrayList<Unit> units1 = new ArrayList<Unit>();
		units1.add(u1);
		
		ArrayList<Unit> units2 = new ArrayList<Unit>();
		units2.add(u2);
		
		ArrayList<Unit> units3 = new ArrayList<Unit>();
		units3.add(u3);
		
		ArrayList<Unit> units4 = new ArrayList<Unit>();
		units4.add(u4);
		
		ArrayList<Unit> units12 = new ArrayList<Unit>();
		units12.add(u1);
		units12.add(u2);
		
		ArrayList<Unit> units13 = new ArrayList<Unit>();
		units13.add(u1);
		units13.add(u3);
		
		ArrayList<Unit> units14 = new ArrayList<Unit>();
		units14.add(u1);
		units14.add(u4);
		
		ArrayList<Unit> units23 = new ArrayList<Unit>();
		units23.add(u2);
		units23.add(u3);
		
		ArrayList<Unit> units24 = new ArrayList<Unit>();
		units24.add(u2);
		units24.add(u4);
		
		ArrayList<Unit> units34 = new ArrayList<Unit>();
		units34.add(u3);
		units34.add(u4);
		
		ArrayList<Unit> units123 = new ArrayList<Unit>();
		units123.add(u1);
		units123.add(u2);
		units123.add(u3);
		
		ArrayList<Unit> units124 = new ArrayList<Unit>();
		units124.add(u1);
		units124.add(u2);
		units124.add(u4);
		
		ArrayList<Unit> units134 = new ArrayList<Unit>();
		units134.add(u1);
		units134.add(u3);
		units134.add(u4);
		
		ArrayList<Unit> units234 = new ArrayList<Unit>();
		units234.add(u2);
		units234.add(u3);
		units234.add(u4);
		
		ArrayList<Unit> units135 = new ArrayList<Unit>();
		units135.add(u1);
		units135.add(u3);
		units135.add(u5);		
		
		ArrayList<Unit> units1234 = new ArrayList<Unit>();
		units1234.add(u1);
		units1234.add(u2);
		units1234.add(u3);
		units1234.add(u4);
	
		//3 ambulances vereist en 1000 liter water
		c7 = new AndDispatchUnitsConstraint(c1,c4);
		assertFalse(c7.areValidDispatchUnits(units1));
		assertFalse(c7.areValidDispatchUnits(units2));
		assertFalse(c7.areValidDispatchUnits(units3));
		assertFalse(c7.areValidDispatchUnits(units4));
		assertFalse(c7.areValidDispatchUnits(units12));
		assertFalse(c7.areValidDispatchUnits(units13));
		assertFalse(c7.areValidDispatchUnits(units14));
		assertFalse(c7.areValidDispatchUnits(units23));
		assertFalse(c7.areValidDispatchUnits(units24));
		assertFalse(c7.areValidDispatchUnits(units34));
		assertFalse(c7.areValidDispatchUnits(units123));
		assertFalse(c7.areValidDispatchUnits(units124));
		assertFalse(c7.areValidDispatchUnits(units134));
		assertFalse(c7.areValidDispatchUnits(units234));
		assertFalse(c7.areValidDispatchUnits(units1234));

		
		//One policar is required and 1000 liters required
		c7 = new AndDispatchUnitsConstraint(c2,c4);
		assertFalse(c7.areValidDispatchUnits(units2));
		assertTrue(c7.areValidDispatchUnits(units12));
		assertTrue(c7.areValidDispatchUnits(units13));
		assertTrue(c7.areValidDispatchUnits(units14));
		assertTrue(c7.areValidDispatchUnits(units123));
		assertFalse(c7.areValidDispatchUnits(units234));
		assertTrue(c7.areValidDispatchUnits(units1234));
		
		//One policar is required and 100000 liters required
		c7 = new AndDispatchUnitsConstraint(c2,c5);
		assertFalse(c7.areValidDispatchUnits(units2));
		assertTrue(c7.areValidDispatchUnits(units13));
		assertTrue(c7.areValidDispatchUnits(units14));
		assertFalse(c7.areValidDispatchUnits(units12));
		assertFalse(c7.areValidDispatchUnits(units23));
		assertFalse(c7.areValidDispatchUnits(units24));
		assertFalse(c7.areValidDispatchUnits(units34));
		
		//One policar is required and 500000 liters required
		c7 = new AndDispatchUnitsConstraint(c2,c6);
		assertFalse(c7.areValidDispatchUnits(units3));
		assertFalse(c7.areValidDispatchUnits(units4));
		assertTrue(c7.areValidDispatchUnits(units1234));
		assertFalse(c7.areValidDispatchUnits(units12));
		assertFalse(c7.areValidDispatchUnits(units13));
		assertFalse(c7.areValidDispatchUnits(units234));
		assertTrue(c7.areValidDispatchUnits(units135));
		
		//500000 liters are required
		assertFalse(c6.areValidDispatchUnits(units1));
		assertFalse(c6.areValidDispatchUnits(units2));
		assertFalse(c6.areValidDispatchUnits(units3));
		assertTrue(c6.areValidDispatchUnits(units4));
		assertFalse(c6.areValidDispatchUnits(units12));
		assertFalse(c6.areValidDispatchUnits(units13));
		assertTrue(c6.areValidDispatchUnits(units14));
		assertFalse(c6.areValidDispatchUnits(units23));
		assertTrue(c6.areValidDispatchUnits(units24));
		assertTrue(c6.areValidDispatchUnits(units34));
		assertFalse(c6.areValidDispatchUnits(units123));
		assertTrue(c6.areValidDispatchUnits(units124));
		assertTrue(c6.areValidDispatchUnits(units134));
		assertTrue(c6.areValidDispatchUnits(units234));
		assertTrue(c6.areValidDispatchUnits(units1234));
		
	}
}
