package projectswop20102011.domain;

/**
 * An interface that represents a constraint.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface AtomicConstraint{

	/**
	 * Checks if the given units are valid.
	 * @param  <T>
	 *		The type of units to validate.
	 * @param units
	 *		The units to check
	 * @return True if the given units are valid, false otherwise.
	 */
	public <T extends Unit> boolean isValid(T... units);
}