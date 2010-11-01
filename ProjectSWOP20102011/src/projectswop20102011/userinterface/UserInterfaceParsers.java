package projectswop20102011.userinterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.EmergencyStatus;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A static utility class providing several static methods for parsing several structures.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UserInterfaceParsers {

    private static Pattern GPS_COORDINATE_PATTERN = Pattern.compile("\\(([^(,)]+),([^(,)]+)\\)");

    /**
     * No instances can be created
     */
    private UserInterfaceParsers() {
    }

    public static GPSCoordinate parseGPSCoordinate(UserInterface channel, String parameterName) throws ParsingAbortedException {
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

    public static EmergencySeverity parseGPSSeverity(UserInterface channel, String parameterName) throws ParsingAbortedException {
        while (true) {
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

    public static EmergencyStatus parseEmergencyStatus(UserInterface channel, String parameterName) throws ParsingAbortedException {
        while (true) {
            channel.writeOutput(String.format("%s=? (recorded but unhandled/response in progress/finished)", parameterName));
            String input = channel.readInput();
            if (input.toLowerCase().equals("abort")) {
                throw new ParsingAbortedException("Parsing EmergencyStatus aborted.");
            }
            try {
                return EmergencyStatus.parse(input);
            } catch (InvalidEmergencyStatusException ex) {
                channel.writeOutput("Unknown status, please retry.");
            }
        }
    }

    public static String parseOptionsString(UserInterface channel, String parameterName, String... options) throws ParsingAbortedException {
        String list = "";
        if (options != null && options.length > 0) {
            list = options[0];
            for (int i = 1; i < options.length; i++) {
                list += "/" + options[i];
            }
        }
        while (true) {
            channel.writeOutput(String.format("%s=? (%s)", parameterName, list));
            String input = channel.readInput();
            if (input.toLowerCase().equals("abort")) {
                throw new ParsingAbortedException("Parsing options aborted.");
            }
            for (String option : options) {
                if (option.equals(input)) {
                    return option;
                }
            }
            channel.writeOutput("Unknown option, please retry.");
        }
    }

    public static long parseLong(UserInterface channel, String parameterName) throws ParsingAbortedException {
        while (true) {
            channel.writeOutput(String.format("%s=? (number)", parameterName));
            String input = channel.readInput();
            if (input.toLowerCase().equals("abort")) {
                throw new ParsingAbortedException("Parsing number aborted.");
            }
            try {
                return Long.parseLong(input);
            } catch (Exception e) {
                channel.writeOutput(String.format("ERROR: %s", e.getMessage()));
            }
            channel.writeOutput("Unknown number, please retry.");
        }
    }
}
