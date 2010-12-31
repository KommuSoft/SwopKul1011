package projectswop20102011.utils;

import projectswop20102011.exceptions.ParsingException;

/**
 * An abstraction layer for a parser that can parse a type <T> out of a textual representation.
 * @param <T> The type where the parser will parse to.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface Parser<T> {

    /**
     * Gets the type of object the parser will parse.
     * @return The type of object the parser will parse.
     */
    public abstract Class<T> getParsingType ();

    /**
     * A method that tests if the given textual representation contains any substring that can be parsed.
     * @param textualRepresentation The text to check.
     * @return True if the given textual representation can be parsed, otherwise false.
     */
    public abstract boolean canParse(String textualRepresentation);

    /**
     * Parses the given textual representation to an object (placed in the objectHolder) and returns the amount of characters that are consumed.
     * @param textualRepresentation The textual representation that needs to be parsed.
     * @param objectHolder The object holder that will store the parsed object.
     * @return The number of characters used by the parsing process.
     * @throws ParsingException If the given textual representation can't get parsed.
     */
    public abstract int parse (String textualRepresentation, ObjectHolder<? super T> objectHolder) throws ParsingException;

}
