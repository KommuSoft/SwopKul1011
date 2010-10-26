package projectswop20102011;

import projectswop20102011.exceptions.InvalidFireSizeException;

/**
 * An enumeration that represents the size of a fire.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum FireSize {
	LOCAL("local"),
	HOUSE("house"),
	FACILITY("facility");
        private final String textual;

    /**
     * Creates a new instance of the FireSize class with a given textual representation.
     * @param textual The textual representation of the FireSize, used for parsing and user interaction.
     */
    FireSize(String textual) {
        this.textual = textual;
    }

    /**
     * Gets the textual representation of the FireSize.
     * @return A textual representation of the FireSize.
     */
    public String getTextual() {
        return this.textual;
    }

    /**
     * Returns a textual representation of the FireSize.
     * @return A textual representation of the FireSize.
     */
    @Override
    public String toString() {
        return this.getTextual();
    }

    /**
     * Tests if a given textual representation of an FireSize matches this FireSize.
     * @param textualRepresentation the textual representation to test.
     * @return True if the textual representation matches, otherwise false.
     */
    public boolean matches(String textualRepresentation) {
        return this.getTextual().equals(textualRepresentation);
    }

    /**
     * Parses a textual representation intro its FireSize equivalent.
     * @param textualRepresentation the textual representation to parse.
     * @return An FireSize that is the equivalent of the textual representation.
     * @throws InvalidFireSizeException If no FireSize matches the textual representation.
     */
    public static FireSize parse(String textualRepresentation) throws InvalidFireSizeException {
        for (FireSize es : FireSize.values()) {
            if (es.matches(textualRepresentation)) {
                return es;
            }
        }
        throw new InvalidFireSizeException(String.format("Unknown fire size level \"%s.\"", textualRepresentation));
    }
}
