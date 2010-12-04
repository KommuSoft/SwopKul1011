package projectswop20102011.domain;

/**
 * A class that represents a constraint that checks the number of units.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NumberConstraint implements AtomicConstraint{
	/**
	 * A variable registering the desired number of units.
	 */
	private long number;

	/**
	 * Creates a NumberConstraint with the given units and number.
	 *
	 * @param number
	 *		The desired number of units.
	 * @param validator
	 *		The validator of this constraint.
	 * @effect This number is equal to the given number.
	 *		|this.number.equals(number)
	 */
	public NumberConstraint(long number){
		setNumber(number);
	}

	/**
	 * Returns the desired number of units.
	 * @return The desired number of units.
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * Sets the number of this number constraint to the given value.
	 * @param number
	 *		The new value of this number constraint.
	 * @post This number is equal to the given number.
	 *		|new.number.equals(number)
	 */
	private void setNumber(long number) {
		this.number = number;
	}

	/**
	 * Checks if the given units are valid.
	 * @param <T>
	 *		The type of units to check.
	 * @param units
	 *		The units to validater.
	 * @return True if the units are valid; false otherwise.
	 */
	@Override
	public <T extends Unit> boolean isValid(T... units) {
		if(units.length < getNumber()){
			return false;
		} else {
			return true;
		}
	}

}