package projectswop20102011.userinterface.parsers;

import projectswop20102011.FireSize;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parsers that can parse a textual representation to it's equivalent
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireSizeParser extends Parser<FireSize> {

    /**
     * Creates a new fire size parser.
     */
    public FireSizeParser () {
        super("format: local/house/facility");
    }

    /**
     * Parses the textual representation of a firesize to a firesize type.
     * @param input The textual representation to parse.
     * @return A FireSize that is equivalent to the given textual representation.
     * @throws ParsingException If the input can not be parsed.
     */
    @Override
    public FireSize parseInput(String input) throws ParsingException {
        try {
            return FireSize.parse(input);
        } catch (InvalidFireSizeException ex) {
            throw new ParsingException(ex.getMessage());
        }
    }

}
