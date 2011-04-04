package projectswop20102011.utils.parsers;

import java.util.regex.Matcher;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser to parse String expressions.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class StringParser extends RegexParser<String> {

    /**
     * Creates a new instance of a StringParser.
     */
    public StringParser () {
        super(String.class,"(.*)");
    }

    /**
     * Parsing the given matcher to the string.
     * @param matcher The matcher object containing information about the match against the regex.
     * @return A string object parsed from the textual representation.
     */
    @Override
    protected String parseFromMatcher(Matcher matcher) {
        return matcher.group(1);
    }

}
