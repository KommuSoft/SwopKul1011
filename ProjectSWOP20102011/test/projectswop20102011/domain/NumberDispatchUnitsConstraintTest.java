package projectswop20102011.domain;

import java.io.InvalidClassException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class NumberDispatchUnitsConstraintTest {

	NumberDispatchUnitsConstraint nduc1, nduc2, nduc3, nduc4;
	long number1, number2, number3, number4, number5;
	UnitValidator uv1, uv2, uv3, uv4, uv5;

	@Before
	public void setUp() throws InvalidClassException {
		number1 = 1;
		number2 = 2;
		number3 = 1;
		number4 = 0;
		number5 = -1;

		uv1 = new TypeUnitValidator(Ambulance.class);
		uv2 = new TypeUnitValidator(Policecar.class);
		uv3 = new TypeUnitValidator(Ambulance.class);
		uv4 = new TypeUnitValidator(Firetruck.class);
		uv5 = null;
	}

	@Test
	public void testConstructor() throws NumberOutOfBoundsException, InvalidUnitValidatorException {
		nduc1 = new NumberDispatchUnitsConstraint(uv1, number1);
		nduc2 = new NumberDispatchUnitsConstraint(uv2, number2);
		nduc3 = new NumberDispatchUnitsConstraint(uv3, number3);
		nduc4 = new NumberDispatchUnitsConstraint(uv4, number4);

		assertEquals(number1, nduc1.getNumber());
		assertEquals(number2, nduc2.getNumber());
		assertEquals(number3, nduc3.getNumber());
		assertEquals(number4, nduc4.getNumber());
	}

	@Test(expected = NumberOutOfBoundsException.class)
	public void testIllegalConstructor1() throws NumberOutOfBoundsException, InvalidUnitValidatorException {
		nduc1 = new NumberDispatchUnitsConstraint(uv1, number5);
	}

	@Test(expected = InvalidUnitValidatorException.class)
	public void testIllegalConstructor2() throws NumberOutOfBoundsException, InvalidUnitValidatorException {
		nduc1 = new NumberDispatchUnitsConstraint(uv5, number1);
	}

	@Test
	public void testAreValidDispatchUnits(){
		//nduc1.areValidDispatchUnits(units);
	}
}
