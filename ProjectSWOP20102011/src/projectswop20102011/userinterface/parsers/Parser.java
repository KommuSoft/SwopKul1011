package projectswop20102011.userinterface.parsers;

import projectswop20102011.exceptions.ParsingException;

/**
 * An abstract class to parse user input.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class Parser<T> {

    /**
     * A message explaining the user what options are aviable.
     */
    private final String parserFormatInformation;
    
    /**
     * Returns the parser format information for the user.
     * @return returns the parser format information fo the user.
     */
    public final String getParserFormatInformation () {
        return this.parserFormatInformation;
    }

    /**
     * Creates a new parser without given parser format information for the user.
     */
    protected Parser () {
        this("");
    }

    /**
     * Creates a new parser with given parser format information for the user.
     * @param parserFormatInformation Format information, that will be displayed for the user.
     */
    protected Parser (String parserFormatInformation) {
        if(parserFormatInformation == null) {
            this.parserFormatInformation = "";
        }
        else {
            this.parserFormatInformation = parserFormatInformation;
        }
    }

    /**
     * Parsing user input intro a specific type.
     * @param input The textual input of the user.
     * @return The information parsed intro the type T.
     * @pre input is effective | !input.equals(null)
     * @throws ParsingException If the parsers can't parse the textual representation to the type T.
     */
    public abstract T parseInput (String input) throws ParsingException;

}
