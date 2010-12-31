package projectswop20102011.utils.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.utils.ObjectHolder;
import projectswop20102011.utils.Parser;

/**
 * An abstract class providing the structure for a generic regex parser.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class RegexParser<T> extends BasicUserInterfaceParser<T> {

    /**
     * Regex for pattern matching.
     */
    private final Pattern parserPattern;

    /**
     * Creates a new RegexParser with a given regex structure.
     * @param parsingType The type of objects that will be parsed.
     * @param parserRegex A regex that can validate on a given string if parsing is possible.
     * @throws PatternSyntaxException If the given Regex has an invalid format.
     */
    protected RegexParser(Class<T> parsingType, String parserRegex) throws PatternSyntaxException {
        super(parsingType);
        this.parserPattern = Pattern.compile(parserRegex);
    }
    /**
     * Creates a new RegexParser with a given regex structure.
     * @param parsingType The type of objects that will be parsed.
     * @param formatInformation Information about the format of the parser.
     * @param parserRegex A regex that can validate on a given string if parsing is possible.
     * @throws PatternSyntaxException If the given Regex has an invalid format.
     */
    protected RegexParser(Class<T> parsingType, String formatInformation, String parserRegex) throws PatternSyntaxException {
        super(parsingType, formatInformation);
        this.parserPattern = Pattern.compile(parserRegex);
    }

    /**
     * Returns the regex pattern uses by the parser.
     * @return The regex pattern used by the parser.
     */
    public Pattern getRegex() {
        return this.parserPattern;
    }

    /**
     * Checks if the given string can be parsed by this parser.
     * @param text The text to test.
     * @return True if the given text can be parsed, otherwise false.
     */
    public boolean canParse(String text) {
        return this.getRegex().matcher(text).find();
    }

    /**
     * Parse a given string to the type of this parser.
     * @param text The text to parse.
     * @return An object of <T> containing the object equivalent of the text.
     * @throws ParsingException If the given text can't be parsed.
     */
    public T parse(String text) throws ParsingException {
        ObjectHolder<T> holder = new ObjectHolder<T>();
        parse(text,holder);
        return holder.getObject();
    }

    /**
     * Parses the object out of the string returning the number of used characters in the string and the parsed object.
     * @param text The text that will be parsed.
     * @param parsedObjectHolder An output parameter thet contains a reference to the created object.
     * @return The number of characters used by the parser.
     * @throws ParsingException If the given text does not contains a textual representation of the object to parse.
     */
    @Override
    public int parse (String text, final ObjectHolder<? super T> parsedObjectHolder) throws ParsingException {
        if (!canParse(text)) {
            throw new ParsingException("Unable to parse text to a generic type: Invalid format.");
        }
        Matcher matcher = this.getRegex().matcher(text);
        matcher.find();
        parsedObjectHolder.setObject(parseFromMatcher(matcher));
        return matcher.end(0);
    }

    /**
     * Parsing the Matcher to an object of the type <T> of this parser.
     * @param matcher The matcher containing the matching information of the parser.
     * @return An object that is equivalent to the textual representation analyzed by the matcher.
     * @throws ParsingException If out of the given matcher no object could be created.
     */
    protected abstract T parseFromMatcher(Matcher matcher) throws ParsingException;
}
