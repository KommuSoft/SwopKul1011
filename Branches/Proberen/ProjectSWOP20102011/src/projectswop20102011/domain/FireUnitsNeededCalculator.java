package projectswop20102011.domain;

public class FireUnitsNeededCalculator {

	/**
	 * private constructor: this class is a static class
	 */
	private FireUnitsNeededCalculator() {
	}

	public static long[] calculate(FireSize fireSize) {
		long[] result = new long[2];
		switch (fireSize) {
			case LOCAL:
				result[0] = 1;
				result[1] = 0;
				break;
			case HOUSE:
				result[0] = 2;
				result[1] = 1;
				break;
			case FACILITY:
				result[0] = 4;
				result[1] = 3;
		}
		return result;
	}
}
