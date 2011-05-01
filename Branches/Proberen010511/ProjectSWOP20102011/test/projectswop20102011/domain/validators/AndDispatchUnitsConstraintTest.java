package projectswop20102011.domain.validators;

import projectswop20102011.domain.validators.FiretruckFireSizeValidator;
import projectswop20102011.domain.validators.AndDispatchUnitsConstraint;
import projectswop20102011.domain.validators.NumberDispatchUnitsConstraint;
import projectswop20102011.domain.validators.UnitValidator;
import projectswop20102011.domain.validators.TypeUnitValidator;
import java.io.InvalidClassException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Unit;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class AndDispatchUnitsConstraintTest {

    private NumberDispatchUnitsConstraint c1, c2, c3, c4;
    private AndDispatchUnitsConstraint c5, c6;
    private UnitValidator uv1, uv2, uv3;
    private FiretruckFireSizeValidator uv4;
    private long number1, number2, number3, number4;
    private Unit u1, u2, u3, u4;
    private String name1, name2, name3, name4;
    private GPSCoordinate gps1, gps2, gps3, gps4;
    private long speed1, speed2, speed3, speed4;
    private long capacity;
	private FireSize fs1;
    private long x1, y1, x2, y2, x3, y3, x4, y4;

    @Before
    public void setUp() throws NumberOutOfBoundsException, InvalidUnitValidatorException, InvalidClassException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidFireSizeException, InvalidCapacityException {
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

        capacity = 1000;
		fs1 = FireSize.LOCAL;
		
        u1 = new Ambulance(name1, gps1, speed1);
        u2 = new Policecar(name2, gps2, speed2);
        u3 = new Ambulance(name3, gps3, speed3);
        u4 = new Firetruck(name4, gps4, speed4, capacity);

        uv1 = new TypeUnitValidator(Ambulance.class);
        uv2 = new TypeUnitValidator(Policecar.class);
        uv3 = new TypeUnitValidator(Ambulance.class);
        uv4 = new FiretruckFireSizeValidator(fs1);

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
        ArrayList<Unit> units1 = new ArrayList<Unit>();
        units1.add(u1);
        ArrayList<Unit> units2 = new ArrayList<Unit>();
        units2.add(u2);
        ArrayList<Unit> units3 = new ArrayList<Unit>();
        units3.add(u1);
        units3.add(u3);

        assertTrue(c1.areValidDispatchUnits(units1) == c5.getConstraints()[0].areValidDispatchUnits(units1));
        assertTrue(c2.areValidDispatchUnits(units2) == c5.getConstraints()[1].areValidDispatchUnits(units2));
        assertTrue(c3.areValidDispatchUnits(units3) == c5.getConstraints()[2].areValidDispatchUnits(units3));
    }

    @Test
    public void testConstructorRecursive() throws InvalidConstraintListException {
        c5 = new AndDispatchUnitsConstraint(c1, c2);
        c6 = new AndDispatchUnitsConstraint(c4, c5);

        assertNotNull(c6.getConstraints());
        assertEquals(2, c6.getConstraints().length);

        ArrayList<Unit> units1 = new ArrayList<Unit>();
        units1.add(u4);
        ArrayList<Unit> units2 = new ArrayList<Unit>();
        units2.add(u1);
        units2.add(u2);

        assertTrue(c4.areValidDispatchUnits(units1) == c6.getConstraints()[0].areValidDispatchUnits(units1));
        assertTrue(c5.areValidDispatchUnits(units2) == c6.getConstraints()[1].areValidDispatchUnits(units2));
    }

    @Test
    public void testAreValidDispatchUnits() throws InvalidConstraintListException {
        ArrayList<Unit> units1 = new ArrayList<Unit>();
        units1.add(u1);
        ArrayList<Unit> units2 = new ArrayList<Unit>();
        units2.add(u2);
        ArrayList<Unit> units13 = new ArrayList<Unit>();
        units13.add(u1);
        units13.add(u3);
        ArrayList<Unit> units4 = new ArrayList<Unit>();
        units4.add(u4);
        ArrayList<Unit> units12 = new ArrayList<Unit>();
        units12.add(u1);
        units12.add(u2);
        ArrayList<Unit> units14 = new ArrayList<Unit>();
        units14.add(u1);
        units14.add(u4);
        ArrayList<Unit> units123 = new ArrayList<Unit>();
        units123.add(u1);
        units123.add(u2);
        units123.add(u3);
        ArrayList<Unit> units24 = new ArrayList<Unit>();
        units24.add(u2);
        units24.add(u4);
        ArrayList<Unit> units134 = new ArrayList<Unit>();
        units134.add(u1);
        units134.add(u3);
        units134.add(u4);
        ArrayList<Unit> units124 = new ArrayList<Unit>();
        units124.add(u1);
        units124.add(u2);
        units124.add(u4);
        ArrayList<Unit> units1234 = new ArrayList<Unit>();
        units1234.add(u1);
        units1234.add(u2);
        units1234.add(u3);
        units1234.add(u4);
        ArrayList<Unit> units41 = new ArrayList<Unit>();
        units41.add(u4);
        units41.add(u1);

        c5 = new AndDispatchUnitsConstraint(c1);
        assertTrue(c5.areValidDispatchUnits(units1));
        assertFalse(c5.areValidDispatchUnits(units2));

        c5 = new AndDispatchUnitsConstraint(c2);
        assertTrue(c5.areValidDispatchUnits(units2));
        assertFalse(c5.areValidDispatchUnits(units1));

        c5 = new AndDispatchUnitsConstraint(c3);
        assertTrue(c5.areValidDispatchUnits(units13));
        assertFalse(c5.areValidDispatchUnits(units4));

        c5 = new AndDispatchUnitsConstraint(c4);
        assertTrue(c5.areValidDispatchUnits(units4));
        assertFalse(c5.areValidDispatchUnits(units1));

        c5 = new AndDispatchUnitsConstraint(c1, c2);
        assertTrue(c5.areValidDispatchUnits(units12));
        assertFalse(c5.areValidDispatchUnits(units2));

        c5 = new AndDispatchUnitsConstraint(c1, c4);
        assertTrue(c5.areValidDispatchUnits(units14));
        assertFalse(c5.areValidDispatchUnits(units1));

        c5 = new AndDispatchUnitsConstraint(c2, c3);
        assertTrue(c5.areValidDispatchUnits(units123));
        assertFalse(c5.areValidDispatchUnits(units12));

        c5 = new AndDispatchUnitsConstraint(c2, c4);
        assertTrue(c5.areValidDispatchUnits(units24));
        assertFalse(c5.areValidDispatchUnits(units2));

        c5 = new AndDispatchUnitsConstraint(c3, c4);
        assertTrue(c5.areValidDispatchUnits(units134));
        assertFalse(c5.areValidDispatchUnits(units14));

        c5 = new AndDispatchUnitsConstraint(c1, c2, c4);
        assertTrue(c5.areValidDispatchUnits(units1234));
        assertFalse(c5.areValidDispatchUnits(units134));

        c5 = new AndDispatchUnitsConstraint(c1, c2);
        c6 = new AndDispatchUnitsConstraint(c4, c5);
        assertTrue(c6.areValidDispatchUnits(units124));
        assertFalse(c5.areValidDispatchUnits(units41));
    }

    @Test(expected = InvalidConstraintListException.class)
    public void testInvalidConstraintList1() throws InvalidConstraintListException {
        c5 = new AndDispatchUnitsConstraint(null);
    }

    @Test(expected = InvalidConstraintListException.class)
    public void testInvalidConstraintList2() throws InvalidConstraintListException {
        c6 = null;
        c5 = new AndDispatchUnitsConstraint(c6);
    }
}
