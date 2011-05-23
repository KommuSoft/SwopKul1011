package projectswop20102011.domain.validators;

import projectswop20102011.domain.Unit;

/**
 * An class that represents a unit validator.
 * @param <T> an optional type parameter used for list conversion reasons.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @note If <T> has another type than Unit, the UnitValidator should fail on every instance that has another type than <T>.
 */
public abstract class UnitValidator<T extends Unit> implements ValidatorNumberator<Unit> {

    /**
     * A primitive implementation of the getNumberMethod.
     * @param object The object to get the number from.
     * @return One (1) if the constraint is true, and zero (0) of the constraint is false.
     * @see ValidatorNumberator#getNumber(java.lang.Object)
     */
    @Override
    public long getNumber(Unit object) {
        if (this.isValid(object)) {
            return 1;
        } else {
            return 0;
        }
    }
}
