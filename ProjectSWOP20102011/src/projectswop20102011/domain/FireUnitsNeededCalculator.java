package projectswop20102011.domain;

/**
 *A class that calculates how much units a Fire needs.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireUnitsNeededCalculator {

	/**
	 * A class that represents a FireUnitsNeededCalculator.
	 * It calculates the units needed for a fire.
	 * @note This constructor is private, no instances of this calculator should be made.
	 */
	private FireUnitsNeededCalculator() {
	}

	/**
	 * Returns an array that contains how much firetrucks and police cars are needed for this fireSize.
	 * @param fireSize
	 *		The size of the fire.
	 * @return An array that contains how much firetrucks (first element) and police cars (second element) are needed for this fireSize.
	 */
	//TODO: opsplitsen in policecars en firetrucks
	public static long[] calculate(FireSize fireSize) {
		long[] result = new long[2];
		switch (fireSize) {
			case LOCAL:
				result[0] = 1000;
				result[1] = 0;
				break;
			case HOUSE:
				result[0] = 100000;
				result[1] = 1;
				break;
			case FACILITY:
				result[0] = 500000;
				result[1] = 3;
		}
		return result;
	}
}
