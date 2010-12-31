package projectswop20102011.userinterface.parsers;

/**
 * A parser to parse String expressions (including aborting condition)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class StringParser extends UserInterfaceParser<String> {

    /**
     * Creates a new instance of a StringParser.
     */
    public StringParser () {
        super(String.class);
    }

    /**
     * Parsing the string from the given input.
     * @param input The given input to parse.
     * @return The given input.
     */
    @Override
    public String parse(String input) {
        return input;
    }

}
