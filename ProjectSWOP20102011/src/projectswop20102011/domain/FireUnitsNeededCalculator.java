package projectswop20102011.domain;

/**
 *
 * @author Pieter-Jan
 */
public class FireUnitsNeededCalculator {

	/**
	 * A class that represents a FireUnitsNeededCalculator.
	 * It calculates the units needed for a fire.
	 *
	 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
	 */
	private FireUnitsNeededCalculator() {
	}

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
