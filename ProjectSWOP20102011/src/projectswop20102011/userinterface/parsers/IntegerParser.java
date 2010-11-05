package projectswop20102011.userinterface.parsers;

import projectswop20102011.exceptions.ParsingException;

/**
 * A parser that can parse integers.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class IntegerParser extends Parser<Integer> {

    /**
     * Creates a new instance of an IntegerParser.
     */
    public IntegerParser () {
        super(Integer.class);
    }

    /**
     * Returns the integer equivalent of the given input.
     * @param input A textual representation that needs to be converted.
     * @return The integer equivalent of the textual input.
     * @throws ParsingException If the input can't be converted to an Integer.
     */
    @Override
    public Integer parseInput(String input) throws ParsingException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new ParsingException(e.getMessage());
        }
    }
}
