package projectswop20102011.utils.parsers;

import java.util.regex.Matcher;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser for the Boolean type.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class BooleanParser extends RegexParser<Boolean> {

    /**
     * Creates a new instance of a boolean parser.
     */
    public BooleanParser () {
        super(Boolean.class,"format: true/yes/no/false","(true|yes|no|false)");
    }

    /**
     * Returns the boolean equivalent of the matcher.
     * @param matcher The matcher containing the results after matching with the regex.
     * @return The boolean equivalent of the matcher.
     * @throws ParsingException When the matcher can not be parsed to a boolean.
     */
    @Override
    protected Boolean parseFromMatcher(Matcher matcher) throws ParsingException {
        String input = matcher.group(1);
        if (input.equals("yes") || input.equals("true")) {
            return true;
        } else if (input.equals("no") || input.equals("false")) {
            return false;
        } else {
            throw new ParsingException("Input is not true, yes, no or false.");
        }
    }
}
