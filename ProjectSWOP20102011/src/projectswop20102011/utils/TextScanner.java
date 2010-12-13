package projectswop20102011.utils;

import projectswop20102011.exceptions.ParsingException;

/**
 * A scanner class that provides reading methods for basic domain datastructures (FireSize, GPSCoordinate,...)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TextScanner {

    private String remainingText;

    /**
     * Creates a new TextScanner with a given text to scan.
     * @param text The text to get scannen.
     */
    public TextScanner (String text) {
        this.remainingText = text;
    }

    /**
     * Checks if the scanner still contains an object that can be parsed by the given scanner.
     * @param <T> The type of the object the parser will parse.
     * @param parser The given parser containing information about the objet to parse.
     * @return True if the given parser is still able to parse an oject out of the scanner.
     */
    public<T> boolean canRead (Parser<T> parser) {
        return parser.canParse(this.getRemainingText());
    }
    /**
     * Reads a object by a parser and advances the scanner.
     * @param <T> The type of object the parser will parse.
     * @param parser The parser used to parse the object (multiple parser for the same object are possible)
     * @return The parsed object.
     * @throws ParsingException
     *          If the given parser can't find a textual representation of the object it parses.
     */
    public synchronized<T> T read (Parser<T> parser) throws ParsingException {
        ObjectHolder<T> holder = new ObjectHolder<T>();
        int length = parser.parse(this.getRemainingText(),holder);
        this.usedCharacters(length);
        return holder.getObject();
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

    /**
     * Moving the scanner with a given amount of characters.
     * @param length The number of characters the scanner will advance.
     */
    private synchronized void usedCharacters(int length) {
        this.setRemainingText(this.getRemainingText().substring(length));
    }

}
