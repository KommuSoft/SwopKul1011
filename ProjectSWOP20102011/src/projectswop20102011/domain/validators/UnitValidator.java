package projectswop20102011.domain.validators;

import projectswop20102011.domain.Unit;

/**
 * An interface that represents a unit validator.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface UnitValidator {

    /**
     * Validates the given unit.
     * @param unit
     *		The unit to validate
     * @return
     *		True if the given unit is valid; false otherwise.
     */
    public abstract boolean isValid(Unit unit);

}
