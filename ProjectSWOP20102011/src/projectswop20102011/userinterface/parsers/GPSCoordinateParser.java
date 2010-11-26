package projectswop20102011.userinterface.parsers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser class to parse a GPSCoodinateParsers
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class GPSCoordinateParser extends Parser<GPSCoordinate> {

    private static Pattern GPS_COORDINATE_PATTERN = Pattern.compile("\\(([^(,)]+),([^(,)]+)\\)");

    /**
     * Creates a new GPSCoordinate parser.
     */
    public GPSCoordinateParser() {
        super("format: (x,y)",GPSCoordinateParser.class);
    }

    /**
     * Parsing the given input to a GPSCoordinate.
     * @param input The given input to parse.
     * @return The GPSCoordinate that is equivalent to the given input.
     * @throws ParsingException If the given input can't be parsed.
     */
    @Override
    public GPSCoordinate parseInput(String input) throws ParsingException {
        Matcher matcher = GPS_COORDINATE_PATTERN.matcher(input);
        if (matcher.find()) {
            try {
                long x = Long.parseLong(matcher.group(1));
                long y = Long.parseLong(matcher.group(2));
                return new GPSCoordinate(x, y);
            } catch (Exception e) {
                throw new ParsingException(e.getMessage());
            }
        } else {
            throw new ParsingException("GPSCoordinate wrong formatted, please retry.");
        }
    }
}
