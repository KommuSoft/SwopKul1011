package projectswop20102011.domain.validators;

import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A special case of a NumberDispatchUnitsConstraint but where units needs to be assigned at once.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AtOnceNumberDispatchUnitsConstraint extends NumberDispatchUnitsConstraint {

    /**
     * Creates a new AtOnce
     * @param validatornumberator
     * @param number
     * @throws NumberOutOfBoundsException
     * @throws InvalidValidatorException
     */
    public AtOnceNumberDispatchUnitsConstraint (ValidatorNumberator<? super Unit> validatornumberator, long number) throws NumberOutOfBoundsException, InvalidValidatorException {
        super(validatornumberator,number);
    }
    public AtOnceNumberDispatchUnitsConstraint (ValidatorNumberator<? super Unit> validatornumberator, long number, QuadraticValidator<Unit, Unit> quadraticValidator) throws NumberOutOfBoundsException, InvalidValidatorException {
        super(validatornumberator,number,quadraticValidator);
    }

}
