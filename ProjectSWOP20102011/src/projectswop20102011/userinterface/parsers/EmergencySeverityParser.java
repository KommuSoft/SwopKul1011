package projectswop20102011.userinterface.parsers;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser for the emergency severity status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencySeverityParser extends Parser<EmergencySeverity> {

    public EmergencySeverityParser () {
        super("format: benign/normal/serious/urgent");
    }

    @Override
    public EmergencySeverity parseInput(String input) throws ParsingException {
        try {
            return EmergencySeverity.parse(input);
        } catch (InvalidEmergencySeverityException ex) {
            throw new ParsingException(ex.getMessage());
        }
        
    }

}
