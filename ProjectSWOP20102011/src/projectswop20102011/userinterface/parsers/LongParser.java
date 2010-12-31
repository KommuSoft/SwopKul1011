package projectswop20102011.userinterface.parsers;

import projectswop20102011.exceptions.ParsingException;

/**
 * A parser that can parse longs.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class LongParser extends UserInterfaceParser<Long> {

    /**
     * Creates a new instance of a LongParser.
     */
    public LongParser () {
        super(Long.class);
    }

    /**
     * Returns the long equivalent of the given input.
     * @param input A textual representation that needs to be converted.
     * @return The long equivalent of the textual input.
     * @throws ParsingException If the input can't be converted to an Long.
     */
    @Override
    public Long parse(String input) throws ParsingException {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new ParsingException(e.getMessage());
        }
    }

}
