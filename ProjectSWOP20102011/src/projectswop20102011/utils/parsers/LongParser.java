package projectswop20102011.utils.parsers;

import java.util.regex.Matcher;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser that can parse longs.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class LongParser extends RegexParser<Long> {

    /**
     * Creates a new instance of a LongParser.
     */
    public LongParser() {
        super(Long.class, "((-?[0-9]+)|true|false)");
    }

    /**
     * Parses a Long out of the matcher of the defined regex.
     * @param matcher The matcher containing data about the match of the regex and the text it parses.
     * @return An object that is the equivalent of the given textual representation in the matcher.
     * @throws ParsingException If the given representation can't be parsed.
     */
    @Override
    protected Long parseFromMatcher(Matcher matcher) throws ParsingException {
        String input = matcher.group(1);
        if(input.equals("true")) {
            return 1L;
        }
        else if(input.equals("false")) {
            return 0L;
        }
        try {
            return Long.parseLong(matcher.group(1));
        } catch (Exception e) {
            throw new ParsingException(e.getMessage());
        }
    }
}
