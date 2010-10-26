package projectswop20102011.controllers;
import java.util.regex.Pattern;
import projectswop20102011.exceptions.ParsingException;

/**
 * A static class providing several utilities for Controllers, mostly parsers.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class ControllerUtilities {

    private static final Pattern PARSE_BOOLEAN_TRUE = Pattern.compile("^(true|yes|y)$");
    private static final Pattern PARSE_BOOLEAN_FALSE = Pattern.compile("^(false|no|n)$");

    //no instances can be created (this class is static)
    private ControllerUtilities() {
    }

    public static boolean parseToBoolean (String textualRepresentation) throws ParsingException {
        if(PARSE_BOOLEAN_TRUE.matcher(textualRepresentation).matches()) {
            return true;
        }
        else if(PARSE_BOOLEAN_FALSE.matcher(textualRepresentation).matches()) {
            return false;
        }
        throw new ParsingException(String.format("No boolean value known as \"%s\".",textualRepresentation));
    }

}
