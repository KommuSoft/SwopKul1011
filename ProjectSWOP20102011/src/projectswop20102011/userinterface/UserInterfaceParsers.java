package projectswop20102011.userinterface;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A static utility class providing several static methods for parsing several structures.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UserInterfaceParsers {

    private static Pattern GPS_COORDINATE_PATTERN = Pattern.compile("\\(([0-9]+),([0-9]+)\\)");

    /**
     * No instances can be created
     */
    private UserInterfaceParsers() {
    }

    public static GPSCoordinate ParseGPSCoordinate(UserInterface channel, String parameterName) throws ParsingAbortedException {
        while (true) {
            channel.writeOutput(String.format("%s=? (format: \"(x,y)\")", parameterName));
            String input = channel.readInput();
            if (input.toLowerCase().equals("abort")) {
                throw new ParsingAbortedException("Parsing GPSCoordinate aborted.");
            }
            Matcher matcher = GPS_COORDINATE_PATTERN.matcher(input);
            if (matcher.find()) {
                try {
                    long x = Long.parseLong(matcher.group(1));
                    long y = Long.parseLong(matcher.group(2));
                    return new GPSCoordinate(x, y);
                } catch (Exception e) {
                    channel.writeOutput(String.format("ERROR: %s", e.getMessage()));
                }
            } else {
                channel.writeOutput("GPSCoordinate wrong formatted, please retry.");
            }
        }
    }

    public static EmergencySeverity ParseGPSSeverity (UserInterface channel, String parameterName) throws ParsingAbortedException {
        while(true) {
            channel.writeOutput(String.format("%s=? (benign/normal/serious/urgent)", parameterName));
            String input = channel.readInput();
            if (input.toLowerCase().equals("abort")) {
                throw new ParsingAbortedException("Parsing EmergencySeverity aborted.");
            }
            try {
                return EmergencySeverity.parse(input);
            } catch (InvalidEmergencySeverityException ex) {
                channel.writeOutput("Unknown severity type, please retry.");
            }
        }
    }
}
