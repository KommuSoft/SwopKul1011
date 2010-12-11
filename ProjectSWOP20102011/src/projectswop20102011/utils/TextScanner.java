package projectswop20102011.utils;

import projectswop20102011.exceptions.ParsingException;

/**
 * A scanner class that provides reading methods for basic domain datastructures (FireSize, GPSCoordinate,...)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TextScanner {

    private String remainingText;

    public TextScanner (String text) {
        this.remainingText = text;
    }

    public boolean canRead (Parser<?> parser) {
        return parser.canParse(this.getRemainingText());
    }
    public<T> T read (Parser<T> parser) throws ParsingException {
        T parsedObject = parser.parse(this.getRemainingText());
        return parsedObject;
    }

    /**
     * Returns the remaining text to read in the scanner.
     * @return The remaining text to read in the scanner.
     */
    private String getRemainingText() {
        return remainingText;
    }

    /**
     * Set the remaining text of the TextScanner to the given remaining text.
     * @param The given remaining text.
     */
    private void setRemainingText(String remainingText) {
        this.remainingText = remainingText;
    }

}
