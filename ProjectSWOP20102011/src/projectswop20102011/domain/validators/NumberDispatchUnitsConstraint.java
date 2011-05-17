package projectswop20102011.domain.validators;

import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a constraint that checks the number of units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NumberDispatchUnitsConstraint extends MinMaxNumberDispatchUnitsConstraint {
    
    /**
     * A variable registering the validator that specifies what will be counted.
     */

    /**
     * Creates a MinMaxNumberDispatchUnitsConstraint with the given units and number.
     * @param validatornumberator
     *		The validator that specifies what will be counted.
     * @param number
     *          The desired number of units.
     * @post This minimum is equal to the given minimum.
     *		|this.minimum.equals(minimum)
     * @post This maximum is equal to the given maximum.
     *		|this.maximum.equals(maximum)
     * @post This validator is equal to the given validator.
     *		|this.validator.equals(validator)
     * @throws NumberOutOfBoundsException
     *		If the given number is invalid.
     * @throws InvalidValidatorException
     *          If the given validatornumberator is invalid.
     */
    public NumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatornumberator, long number) throws NumberOutOfBoundsException, InvalidValidatorException {
        super(validatornumberator,number,number);
    }

    /**
     * Creates a MinMaxNumberDispatchUnitsConstraint with the given units and number.
     * @param validatornumberator
     *		The validator that specifies what will be counted.
     * @param number
     *          The desired number of units.
     * @param quadraticValidator
     *          An optional additional constraint to test a unit to be assigned againt the units that are already assigned (or proposed to).
     * @post This minimum is equal to the given minimum.
     *		|this.minimum.equals(minimum)
     * @post This maximum is equal to the given maximum.
     *		|this.maximum.equals(maximum)
     * @post This validator is equal to the given validator.
     *		|this.validator.equals(validator)
     * @throws NumberOutOfBoundsException
     *		If the given number is invalid.
     * @throws InvalidValidatorException
     *          If the given validatornumberator is invalid.
     */
    public NumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatornumberator, long number, QuadraticValidator<Unit,Unit> quadraticValidator) throws NumberOutOfBoundsException, InvalidValidatorException {
        super(validatornumberator,number,number,quadraticValidator);
    }

    /**
     * Tests if the given validator is a valid validator for a CountDispatchUnitsConstraint.
     * @param validator
	 *		The validator to validate.
     * @return True if the given validator is effective.
     */
    public static boolean isValidValidator(UnitValidator validator) {
        return (validator != null);
    }

    /**
     * Tests if the given number is a valid number for a CountDispatchUnitsConstraint.
     * @param number
	 *		The number to validate.
     * @return True if the given number is larger or equal to zero, false otherwise.
     */
    public static boolean isValidNumber(long number) {
        return (number >= 0);
    }

    /**
     * Returns the number of required units that hold to the constraint.
     * @return The number of the required units that hold to the constraint.
     */
    public long getNumber () {
        return this.getMinimum();
    }

    /**
     * Returns a textual representation of the NumberDispatchUnitsConstraint.
     * @return A textual representation of the NumberDispatchUnitsConstraint.
     */
    @Override
    public String toString() {
        return String.format("number of %s must be %s",this.getValidator().toString(), this.getNumber());
    }

}
