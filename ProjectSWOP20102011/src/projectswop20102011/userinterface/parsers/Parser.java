package projectswop20102011.userinterface.parsers;

import projectswop20102011.exceptions.ParsingException;

/**
 * An abstract class to parse user input.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class Parser<T> {

    /**
     * A message explaining the user what options are available.
     */
    private final String parserFormatInformation;
    /**
     * The class to where the parser is parsing.
     */
    private final Class parserClass;

    /**
     * Creates a new parser without given parser format information for the user.
     * @param parserClass The class to where the parser is parsing.
     */
    protected Parser(Class parserClass) {
        this("", parserClass);
    }

    /**
     * Creates a new parser with given parser format information for the user.
     * @param parserFormatInformation Format information, that will be displayed for the user.
     * @param parserClass The class to where the parser is parsing.
     * @pre The parserClass is effective and equal to the type parameter <T>.
     */
    protected Parser(String parserFormatInformation, Class parserClass) {
        if (parserFormatInformation == null) {
            this.parserFormatInformation = "";
        } else {
            this.parserFormatInformation = parserFormatInformation;
        }
        this.parserClass = parserClass;
    }

     /**
     * Returns the parser format information for the user.
     * @return The parser format information fo the user.
     */
    public final String getParserFormatInformation() {
        return this.parserFormatInformation;
    }

     /**
     * Returns the class to where the parser is parsing.
     * @return .The class to where the parser is parsing.
     */
    public final Class getParserClass() {
        return this.parserClass;
    }

    /**
     * Parsing user input intro a specific type.
     * @param input The textual input of the user.
     * @return The information parsed intro the type T.
     * @pre input is effective | !input.equals(null)
     * @throws ParsingException If the parsers can't parse the textual representation to the type T.
     */
    public abstract T parseInput(String input) throws ParsingException;
}
