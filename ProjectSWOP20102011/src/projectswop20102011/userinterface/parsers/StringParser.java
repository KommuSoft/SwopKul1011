package projectswop20102011.userinterface.parsers;

/**
 * A parser to parse String expressions (including aborting condition)
 * @author willem
 */
public class StringParser extends Parser<String> {

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
    public String parseInput(String input) {
        return input;
    }

}
