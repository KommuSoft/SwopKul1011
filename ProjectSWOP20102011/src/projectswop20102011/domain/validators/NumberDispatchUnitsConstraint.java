package projectswop20102011.domain.validators;

import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a constraint that checks the number of units. This class is a special case of the MinMaxNumberDispatchUnitsConstraint.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NumberDispatchUnitsConstraint extends MinMaxNumberDispatchUnitsConstraint {

    /**
     * Creates a MinMaxNumberDispatchUnitsConstraint with the given units and number.
     * @param validatornumberator
     *		The validator that specifies what will be counted.
     * @param number
     *          The desired number of units.
     * @effect
     *          super(validatornumberator,number,number)
     */
    public NumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatornumberator, long number) throws NumberOutOfBoundsException, InvalidValidatorException {
        super(validatornumberator, number, number);
    }

    /**
     * Creates a MinMaxNumberDispatchUnitsConstraint with the given units and number.
     * @param validatornumberator
     *		The validator that specifies what will be counted.
     * @param number
     *          The desired number of units.
     * @param quadraticValidator
     *          An optional additional constraint to test a unit to be assigned againt the units that are already assigned (or proposed to).
     * @effect
     *          super(validatornumberator,number,number,quadraticValidator)
     */
    public NumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatornumberator, long number, QuadraticValidator<Unit, Unit> quadraticValidator) throws NumberOutOfBoundsException, InvalidValidatorException {
        super(validatornumberator, number, number, quadraticValidator);
    }

    /**
     * Tests if the given validator is a valid validator for a CountDispatchUnitsConstraint.
     * @param validator
     *		The validator to validate.
     * @return True if the given validator is effective.
     */
    public static boolean isValidValidator(UnitValidator<? super Unit> validator) {
        return MinMaxNumberDispatchUnitsConstraint.isValidValidator(validator);
    }

    /**
     * Tests if the given number is a valid number for a CountDispatchUnitsConstraint.
     * @param number
     *		The number to validate.
     * @return True if the given number is larger or equal to zero, false otherwise.
     */
    public static boolean isValidNumber(long number) {
        return MinMaxNumberDispatchUnitsConstraint.isValidNumber(number);
    }

    /**
     * Returns the number of required units that hold to the constraint.
     * @return The number of the required units that hold to the constraint.
     */
    public long getNumber() {
        return this.getMinimum();
    }

    /**
     * Returns a textual representation of the NumberDispatchUnitsConstraint.
     * @return A textual representation of the NumberDispatchUnitsConstraint.
     */
    @Override
    public String toString() {
        return String.format("number of %s must be %s", this.getValidator().toString(), this.getNumber());
    }
}
