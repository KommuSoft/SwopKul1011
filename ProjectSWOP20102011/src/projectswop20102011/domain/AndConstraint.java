package projectswop20102011.domain;

/**
 * A class that represents a constraint that checks if all the given parameters are true or not.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AndConstraint {

	/**
	 * Checks if all the given parameters are true or not.
	 * @param constraints
	 *		The constraints to check if they are true or not.
	 * @return True if all the parameters are true; false otherwise.
	 */
	public static boolean isValid(AtomicConstraint... constraints) {
		boolean result = true;

		for (AtomicConstraint constraint : constraints) {
			result &= constraint.isValid();
		}

		return result;
	}
}
