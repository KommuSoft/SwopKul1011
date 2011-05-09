package projectswop20102011.domain.validators;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a constraint that checks the number of units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NumberDispatchUnitsConstraint extends MinMaxNumberDispatchUnitsConstraint {

    /**
     * Creates a NumberConstraint with the given units and number.
     * @param validator
     *		The validator that specifies what will be counted.
     * @param number
     *		The desired number of units.
     * @post This number is equal to the given number.
     *		|this.number.equals(number)
     * @post This validator is equal to the given validator.
     *		|this.validator.equals(validator)
     * @throws NumberOutOfBoundsException
	 *		If the given number is invalid.
     * @throws InvalidUnitValidatorException
	 *		If the given UnitValidator is invalid.
     */
    public NumberDispatchUnitsConstraint(UnitValidator validator, long number) throws NumberOutOfBoundsException, InvalidUnitValidatorException {
        super(validator,number,number);
    }

    /**
     * Returns a textual representation of the NumberDispatchUnitsConstraint.
     * @return A textual representation of the NumberDispatchUnitsConstraint.
     */
    @Override
    public String toString() {
        return String.format("number of %s must be %s",this.getValidator().toString(),this.getMinimum());
    }

}
