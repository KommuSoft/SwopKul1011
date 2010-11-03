package projectswop20102011;

import java.util.Arrays;

/**
 * A class that represents the units for an Emergency.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UnitsNeeded{

	/**
	 * A variable registering the numbersNeeded of this UnitsNeeded.
	 */
	private long[] numbersNeeded;

	/**
	 * A variable registering the units of the emergency.
	 */
	private Class<? extends Unit>[] units;

	/**
	 * Creates a new Object that calculates the units needed for an emergency.
	 *
	 * @param numbersNeeded
	 *		The numbers of units needed for this emergency.
	 * @param units
	 *		The units needed for this emergency.
	 */
	public UnitsNeeded(long[] numbersNeeded, Class<? extends Unit>[] units){
		setNumbersNeeded(numbersNeeded);
		setUnits(units);
	}

	/**
	 * Returns the number of units needed for the emergency.
	 * @return the number of units needed for the emergency.
	 */
	public long[] getNumbersNeeded() {
		return numbersNeeded.clone();
	}

	/**
	 * Sets the numbers of units needed for this emergency.
	 * @param numbersNeeded
	 *		The new numbers of units needed for this emergency.
	 */
	private void setNumbersNeeded(long[] numbersNeeded) {
		this.numbersNeeded = Arrays.copyOf(numbersNeeded, numbersNeeded.length);
	}

	/**
	 * Returns the units needed for this emergency.
	 * @return the units needed for this emergency.
	 */
	public Class<? extends Unit>[] getUnits() {
		return units.clone();
	}

	/**
	 * Sets the units for this emergency.
	 * @param units
	 *		The new units needed for this emergency.
	 */
	private void setUnits(Class<? extends Unit>[] units) {
		this.units = Arrays.copyOf(units, units.length);
	}

	/**
	 *
	 */
	public void assign(){
		if(getUnits().length == getNumbersNeeded().length){

		}
	}
}