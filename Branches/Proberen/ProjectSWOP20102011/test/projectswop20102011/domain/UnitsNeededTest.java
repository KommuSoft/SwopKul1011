package projectswop20102011.domain;

import java.io.InvalidClassException;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.validators.NumberDispatchUnitsConstraint;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class UnitsNeededTest {

    private long number,x,y;
    private GPSCoordinate emergencyLocation;
    private Emergency f1;
    private TypeUnitValidator tuv;
    private NumberDispatchUnitsConstraint nduc;

    @Before
    public void setUp() throws InvalidLocationException, InvalidMapItemNameException {
        number = 5;
        x = 2;
        y = 4;
        emergencyLocation = new GPSCoordinate(x,y);
    }

    @Test
    public void testValidEmergency () throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException{
        f1 = new Fire(emergencyLocation, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 1, 2);
        assertTrue(UnitsNeeded.isValidEmergency(f1));
    }

    @Test
    public void testInvalidEmergency () {
        assertFalse(UnitsNeeded.isValidEmergency(null));
    }

    @Test
    public void testValidConstraint() throws InvalidClassException, NumberOutOfBoundsException, InvalidUnitValidatorException{
        tuv = new TypeUnitValidator(Firetruck.class);
        nduc = new NumberDispatchUnitsConstraint(tuv, number);
        assertTrue(UnitsNeeded.isValidConstraint(nduc));

    }
    @Test
    public void testInvalidConstraint(){
        assertFalse(UnitsNeeded.isValidConstraint(null));
    }

    @Test(expected=InvalidEmergencyException.class)
    public void testIsConstructorEmergency1 () throws InvalidEmergencyException, InvalidDispatchUnitsConstraintException {
        new UnitsNeeded(null,null);
    }


}
