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
	 * Returns how much liters are needed for the given fire size.
	 * @param fireSize
	 *		The size of the fire.
	 * @return The number of liters that are needed for the give fire size.
	 */
	public static long calculateLiters(FireSize fireSize){
		switch (fireSize) {
			case LOCAL:
				return 1000;
			case HOUSE:
				return 100000;
			case FACILITY:
				return 500000;
			default:
				return 0;
		}		
	}
	
	/**
	 * Returns how much police cars are needed for the given fire size.
	 * @param fireSize
	 *		The size of the fire.
	 * @return The number of police cars that are needed for the give fire size.
	 */
	public static long calculatePoliceCars(FireSize fireSize){
		switch (fireSize) {
			case LOCAL:
				return 0;
			case HOUSE:
				return 1;
			case FACILITY:
				return 3;
			default:
				return 0;
		}
	}
}
