package projectswop20102011.domain;

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
    public <T extends Unit> boolean isValid(T unit);
}