package projectswop20102011.domain.validators;

import java.io.InvalidClassException;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Unit;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class NumberDispatchUnitsConstraintTest {

    private NumberDispatchUnitsConstraint nduc;
    private long number1, number2, number3, number4, number5;
    private UnitValidator uv1, uv2, uv3, uv4, uv5;
    private Unit u1, u2, u3, u4;
    private String name1, name2, name3, name4;
    private GPSCoordinate gps1, gps2, gps3, gps4;
    private long speed1, speed2, speed3, speed4;
    private long x1, y1, x2, y2, x3, y3, x4, y4;
	private long fs1;

    @Before
    public void setUp() throws InvalidClassException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException {
        number1 = 1;
        number2 = 1;
        number3 = 2;
        number4 = 0;
        number5 = -1;

        uv1 = new TypeUnitValidator(Ambulance.class);
        uv2 = new TypeUnitValidator(Policecar.class);
        uv3 = new TypeUnitValidator(Ambulance.class);
        uv4 = new TypeUnitValidator(Firetruck.class);
        uv5 = null;

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

		fs1 = 100000;

        u1 = new Ambulance(name1, gps1, speed1);
        u2 = new Policecar(name2, gps2, speed2);
        u3 = new Ambulance(name3, gps3, speed3);
        u4 = new Firetruck(name4, gps4, speed4, fs1);
    }

    @Test
    public void testConstructor() throws NumberOutOfBoundsException, InvalidValidatorException, InvalidUnitValidatorException {
        nduc = new NumberDispatchUnitsConstraint(uv1, number1);
        assertEquals(number1, nduc.getNumber());

        nduc = new NumberDispatchUnitsConstraint(uv2, number2);
        assertEquals(number2, nduc.getNumber());

        nduc = new NumberDispatchUnitsConstraint(uv3, number3);
        assertEquals(number3, nduc.getNumber());

        nduc = new NumberDispatchUnitsConstraint(uv4, number4);
        assertEquals(number4, nduc.getNumber());
    }

    @Test(expected = NumberOutOfBoundsException.class)
    public void testIllegalConstructor1() throws NumberOutOfBoundsException, InvalidValidatorException {
        nduc = new NumberDispatchUnitsConstraint(uv1, number5);
    }

    @Test(expected = InvalidValidatorException.class)
    public void testIllegalConstructor2() throws NumberOutOfBoundsException, InvalidValidatorException {
        nduc = new NumberDispatchUnitsConstraint(uv5, number1);
    }

    
    public void test () {

    }

}
