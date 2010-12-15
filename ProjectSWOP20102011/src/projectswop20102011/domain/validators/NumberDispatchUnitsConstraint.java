package projectswop20102011.domain.validators;

import java.util.List;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a constraint that checks the number of units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NumberDispatchUnitsConstraint extends DispatchUnitsConstraint {

    /**
     * A variable registering the desired number of units.
     */
    private final long number;
    /**
     * A variable registering the validator that specifies what will be counted.
     */
    private final UnitValidator validator;

    /**
     * Creates a NumberConstraint with the given units and number.
     * @param validator
     *		The validator that specifies what will be counted.
     * @param number
     *		The desired number of units.
     * @throws NumberOutOfBoundsException If the given number is invalid.
     * @throws InvalidUnitValidatorException If the given UnitValidator is invalid.
     * @post This number is equal to the given number.
     *		|this.number.equals(number)
     * @post This validator is equal to the given validator.
     *		|this.validator.equals(validator)
     */
    public NumberDispatchUnitsConstraint(UnitValidator validator, long number) throws NumberOutOfBoundsException, InvalidUnitValidatorException {
        if (!isValidNumber(number)) {
            throw new NumberOutOfBoundsException("The number needs to be larger or equal to zero.");
        }
        if (!isValidValidator(validator)) {
            throw new InvalidUnitValidatorException("UnitValidator must be effective.");
        }
        this.number = number;
        this.validator = validator;
    }

    /**
     * Returns the desired number of units.
     * @return The desired number of units.
     */
    public long getNumber() {
        return number;
    }

    /**
     * Returns the validator that specifies what will be counted.
     * @return the validator that specifies what will be counted.
     */
    private UnitValidator getValidator() {
        return validator;
    }

    /**
     * Tests if the given validator is a valid validator for a CountDispatchUnitsConstraint.
     * @param validator The validator to validate.
     * @return True if the given validator is effective.
     */
    public static boolean isValidValidator(UnitValidator validator) {
        return (validator != null);
    }

    /**
     * Tests if the given number is a valid number for a CountDispatchUnitsConstraint.
     * @param number The number to validate.
     * @return True if the given number is larger or equal to zero, false otherwise.
     */
    public static boolean isValidNumber(long number) {
        return (number >= 0);
    }

    /**
     * Tests if at least a specified number of units are validated by the specified validator.
     * @param units An iterable object containing only unique and only effective units.
     * @pre The given units parameter contains only unique (no duplicates) effective units.
     * @return True if at least the specified number of units succeed on the specified validator, otherwise false.
     */
    @Override
    public boolean areValidDispatchUnits (List<Unit> units, boolean[] used) {
        long n = 0;
        long needed = this.getNumber();
        UnitValidator uv = this.getValidator();
        for(int i = 0; i < units.size(); i++) {
            if(uv.isValid(units.get(i))) {
                used[i] = true;
                n++;
                if(n >= needed) {
                    return true;
                }
            }
        }
        return n >= needed;
    }

    /**
     * Returns a textual representation of the NumberDispatchUnitsConstraint.
     * @return A textual representation of the NumberDispatchUnitsConstraint.
     */
    @Override
    public String toString() {
        return String.format("number of %s must be %s",this.getValidator().toString(),this.getNumber());
    }

}
