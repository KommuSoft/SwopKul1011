package projectswop20102011.domain.validators;

import projectswop20102011.domain.Unit;

/**
 * An class that represents a unit validator.
 * @param <T> an optional type parameter used for list conversion reasons.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @note If <T> has another type than Unit, the UnitValidator should fail on every instance that has another type than <T>.
 */
public abstract class UnitValidator<T extends Unit> extends BooleanValidatorNumberator<T> {

}
