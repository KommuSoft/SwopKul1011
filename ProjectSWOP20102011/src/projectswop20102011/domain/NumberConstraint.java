package projectswop20102011.domain;

/**
 * A class that represents a constraint that checks the number of units.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class NumberConstraint implements Constraint{
	/**
	 * A variable registering the units of this NumberConstraint.
	 */
	private Unit[] units;
	/**
	 * A variable registering the desired number of units.
	 */
	private long number;
	
	/**
	 * Creates a NumberConstraint with the given units and number.
	 * 
	 * @param units
	 *		The units to check.
	 * @param number
	 *		The desired number of units.
	 */
	public NumberConstraint(Unit[] units, long number){
		setUnits(units);
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
	 *
	 * @param number
	 */
	private void setNumber(long number) {
		this.number = number;
	}

	/**
	 * Returns the units to check.
	 * @return The units of this constraint.
	 */
	public Unit[] getUnits() {
		return units.clone();
	}

	/**
	 *
	 * @param units
	 */
	private void setUnits(Unit[] units) {
		this.units = units.clone();
	}

	/**
	 * Checks if the constraint is valid.
	 *
	 * @return True if the constraint is valid; false otherwise.
	 */
	@Override
	public boolean isValid() {
		return getUnits().length > getNumber();
	}

}