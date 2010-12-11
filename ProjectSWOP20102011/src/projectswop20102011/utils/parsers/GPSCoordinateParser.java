package projectswop20102011.utils.parsers;

import java.util.regex.Matcher;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.utils.Parser;

/**
 * A parser that can parse GPSCoordinates
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class GPSCoordinateParser extends Parser<GPSCoordinate> {

    public GPSCoordinateParser () {
        super("\\((-?[0-9]+),(-?[0-9]+)\\)");
    }

    @Override
    protected GPSCoordinate parseFromMatcher(Matcher matcher) throws ParsingException {
        long x = Long.parseLong(matcher.group(1));
        long y = Long.parseLong(matcher.group(2));
        return new GPSCoordinate(x,y);
    }

}