package projectswop20102011.userinterface.parsers;

import projectswop20102011.exceptions.ParsingException;

/**
 * A parser for the Boolean type.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class BooleanParser extends Parser<Boolean> {

    /**
     * Creates a new instance of a boolean parser.
     */
    public BooleanParser () {
        super("true/yes/no/false",Boolean.class);
    }

    /**
     * Returns the boolean equivalent of the input.
     * @param input The textual representation.
     * @return The boolean equivalent of the input.
     * @throws ParsingException When the input can not be parsed to a boolean.
     */
    @Override
    public Boolean parseInput(String input) throws ParsingException {
        String inputL = input.toLowerCase();
        if (inputL.equals("yes") || inputL.equals("true")) {
            return true;
        } else if (inputL.equals("no") || inputL.equals("false")) {
            return false;
        } else {
            throw new ParsingException("Input is not true, yes, no or false.");
        }
    }
}
