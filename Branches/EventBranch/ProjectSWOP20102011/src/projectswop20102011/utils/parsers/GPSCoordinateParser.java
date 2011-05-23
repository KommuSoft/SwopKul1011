package projectswop20102011.utils.parsers;

import java.util.regex.Matcher;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser that can parse GPSCoordinates
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class GPSCoordinateParser extends RegexParser<GPSCoordinate> {

    /**
     * Creates a new instance of a GPSCoordinateParser.
     */
    public GPSCoordinateParser() {
        super(GPSCoordinate.class, "format: (x,y)", "\\((-?[0-9]+),(-?[0-9]+)\\)");
    }

    /**
     * Parses a GPSCoordinate out of the matcher of the defined regex.
     * @param matcher The matcher containing data about the match of the regex and the text it parses.
     * @return An object that is the equivalent of the given textual representation in the matcher.
     * @throws ParsingException If the given representation can't be parsed.
     */
    @Override
    protected GPSCoordinate parseFromMatcher(Matcher matcher) throws ParsingException {
        try {
            long x = Long.parseLong(matcher.group(1));
            long y = Long.parseLong(matcher.group(2));
            return new GPSCoordinate(x, y);
        } catch (Exception e) {
            throw new ParsingException(e.getMessage());
        }
    }
}
