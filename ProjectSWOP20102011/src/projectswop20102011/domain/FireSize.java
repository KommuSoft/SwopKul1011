package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidFireSizeException;

/**
 * An enumeration that represents the size of a fire.
 * @note The enum defines automatically the total order relation amongst the fire sizes. Where the largest fire size is the fire that is the most difficult to handle.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum FireSize {

	/**
	 * A local fire. The needed capacity of this fire is 1000.
	 */
	LOCAL("local", 1000),
	/**
	 * A fire size that indicates that a house is on a fire. The needed capacity of this fire is 100000.
	 */
	HOUSE("house", 100000),
	/**
	 * A facility fire. The needed capacity of this fire is 500000.
	 */
	FACILITY("facility", 500000);
	/**
	 * The textual representation of the fire size.
	 */
	private final String textual;
	/**
	 * The needed amount of capacity that is needed for this firesize
	 */
	private final long neededCapacity;

	/**
	 * Creates a new instance of the FireSize class with a given textual representation.
	 * @param textual
	 *		The textual representation of the FireSize, used for parsing and user interaction.
	 * @post The textual representation is set to the given textual representation.
	 *		| new.getTextual().equals(textual)
	 * @post The needed capacity is set to the given needed capacity.
	 *		| new.neededCapacity().equals(neededCapacity)
	 */
	private FireSize(String textual, long neededCapacity) {
		this.textual = textual;
		this.neededCapacity = neededCapacity;
	}

	/**
	 * Gets the textual representation of the FireSize.
	 * @return A textual representation of the FireSize.
	 */
	public String getTextual() {
		return textual;
	}

	/**
	 * Returns the needed capacity of this fire.
	 * @return The needed capacity of this fire.
	 */
	public long getNeededCapacity() {
		return neededCapacity;
	}

	/**
	 * Returns a textual representation of the FireSize.
	 * @return A textual representation of the FireSize.
	 */
	@Override
	public String toString() {
		return getTextual();
	}

	/**
	 * Tests if a given textual representation of a FireSize matches this FireSize.
	 * @param textualRepresentation
	 *		The textual representation to test.
	 * @return True if the textual representation matches, otherwise false.
	 */
	public boolean matches(String textualRepresentation) {
		return getTextual().equals(textualRepresentation.toLowerCase());
	}

	/**
	 * Parses a textual representation into its FireSize equivalent.
	 * @param textualRepresentation
	 *		The textual representation to parse.
	 * @return A FireSize that is the equivalent of the textual representation.
	 * @throws InvalidFireSizeException
	 *		If no FireSize matches the textual representation.
	 */
	public static FireSize parse(String textualRepresentation) throws InvalidFireSizeException {
		for (FireSize fs : FireSize.values()) {
			if (fs.matches(textualRepresentation)) {
				return fs;
			}
		}
		throw new InvalidFireSizeException(String.format("Unknown fire size level \"%s\".", textualRepresentation));
	}
}
