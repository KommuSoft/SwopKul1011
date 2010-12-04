package projectswop20102011.domain;

/**
 * A class that represents a constraint that checks the number of units.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NumberConstraintValidator implements AtomicConstraint {

	/**
	 * A variable registering the desired number of units.
	 */
	private long number;
	/**
	 * A variable registering the validator of this number constraint validator.
	 */
	private UnitValidator validator;

	/**
	 * Creates a NumberConstraint with the given units and number.
	 * 
	 * @param number
	 *		The desired number of units.
	 * @param validator
	 *		The validator of this constraint.
	 * @effect This number is equal to the given number.
	 *		|this.number.equals(number)
	 * @effect This validator is equal to the given validator.
	 *		|this.validator.equals(validator)
	 */
	public NumberConstraintValidator(long number, UnitValidator validator) {
		setNumber(number);
		setValidator(validator);
	}

	/**
	 * Returns the desired number of units.
	 * @return The desired number of units.
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Sets the number of this number constraint validator to the given value.
	 * @param number
	 *		The new value of this number constraint validator.
	 * @post This number is equal to the given number.
	 *		|new.number.equals(number)
	 */
	private void setNumber(long number) {
		this.number = number;
	}

	/**
	 * Returns the validator of this number constraint validator.
	 * @return The validator of this number constraint validator.
	 */
	private UnitValidator getValidator() {
		return validator;
	}

	/**
	 * Sets the validator of this number constraint validator to the given validator.
	 * @param validator
	 *		The new validator of this number constraint validator.
	 * @post This validator is equal to the given validator.
	 *		|new.validator.equals(validator)
	 */
	private void setValidator(UnitValidator validator) {
		this.validator = validator;
	}

	/**
	 * Checks if the units are valid units.
	 * @param <T>
	 *		The type of units to validate.
	 * @param units
	 *		The units to validate.
	 * @return True if the units are valid; false otherwise.
	 */
	@Override
	public <T extends Unit> boolean isValid(T... units) {
		if (units.length < getNumber()) {
			return false;
		}

		boolean result = true;
		for (T unit : units) {
			if (!getValidator().isValid(unit)) {
				result = false;
			}
		}

		return result;
	}
}
