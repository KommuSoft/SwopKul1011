package projectswop20102011.domain.validators;

import projectswop20102011.domain.Unit;

/**
 * An interface that represents a unit validator.
 * @param <T> an optional type parameter used for list conversion reasons.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @note If <T> has another type than Unit, the UnitValidator should fail on every instance that has another type than <T>.
 */
public interface UnitValidator<T extends Unit> extends Validator<T> {

    /**
     * Validates the given unit.
     * @param unit
     *		The unit to validate
     * @return
     *		True if the given unit is valid; false otherwise.
     */
    @Override
    public abstract boolean isValid(T unit);

}
