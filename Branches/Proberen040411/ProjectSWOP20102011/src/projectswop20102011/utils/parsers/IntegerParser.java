package projectswop20102011.utils.parsers;

import java.util.regex.Matcher;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser that can parse ints.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class IntegerParser extends RegexParser<Integer> {

    /**
     * Creates a new instance of a IntegerParser.
     */
    public IntegerParser() {
        super(Integer.class, "(-?[0-9]+)");
    }

    /**
     * Parses a Integer out of the matcher of the defined regex.
     * @param matcher The matcher containing data about the match of the regex and the text it parses.
     * @return An object that is the equivalent of the given textual representation in the matcher.
     * @throws ParsingException If the given representation can't be parsed.
     */
    @Override
    protected Integer parseFromMatcher(Matcher matcher) throws ParsingException {
        try {
            return Integer.parseInt(matcher.group(1));
        } catch (Exception e) {
            throw new ParsingException(e.getMessage());
        }
    }
}