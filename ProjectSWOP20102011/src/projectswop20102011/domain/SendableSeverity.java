package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidSendableSeverityException;

/**
 * An enumeration that represents the severity of a sendable.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public enum SendableSeverity {

    BENIGN("benign"),
    NORMAL("normal"),
    SERIOUS("serious"),
    URGENT("urgent");
    /**
     * The textual representation of the sendable severity.
     */
    private final String textual;

    /**
     * Creates a new instance of the SendableSeverity class with a given textual representation.
     * @param textual
     *		The textual representation of the SendableSeverity, used for parsing and user interaction.
     * @post The textual representation is set to the given textual representation.
     *		| new.toString().equals(textual)
     */
    private SendableSeverity(String textual) {
        this.textual = textual;
    }

    /**
     * Returns the textual representation of the SendableSeverity.
     * @return A textual representation of the SendableSeverity.
     */
    @Override
    public String toString() {
        return textual;
    }

    /**
     * Tests if a given textual representation of an SendableSeverity matches this SendableSeverity.
     * @param textualRepresentation
     *		The textual representation to test.
     * @return True if the textual representation matches, otherwise false.
     */
    public boolean matches(String textualRepresentation) {
        return toString().equals(textualRepresentation.toLowerCase());
    }

    /**
     * Parses a textual representation into its SendableSeverity equivalent.
     * @param textualRepresentation
     *		The textual representation to parse.
     * @return An SendableSeverity that is the equivalent of the textual representation.
     * @throws InvalidSendableSeverityException
     *		If no SendableSeverity matches the textual representation.
     */
    public static SendableSeverity parse(String textualRepresentation) throws InvalidSendableSeverityException {
        for (SendableSeverity es : SendableSeverity.values()) {
            if (es.matches(textualRepresentation)) {
                return es;
            }
        }
        throw new InvalidSendableSeverityException(String.format("Unknown severity level \"%s\".", textualRepresentation));
    }

    /**
     * Calculates the maximum severity of this severity and the otherSeverity.
     * @param otherSeverity The other severity to calculate the severity level from.
     * @return The maximum severity level of this and otherSeverity.
     */
    public SendableSeverity getMaximum(SendableSeverity otherSeverity) {
        if (this.ordinal() >= otherSeverity.ordinal()) {
            return this;
        } else {
            return otherSeverity;
        }
    }
}
