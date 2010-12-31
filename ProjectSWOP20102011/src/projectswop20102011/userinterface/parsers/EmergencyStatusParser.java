package projectswop20102011.userinterface.parsers;

import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser that can parse the status of an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencyStatusParser extends UserInterfaceParser<EmergencyStatus> {

    /**
     * Creates a new instance of an EmergencyStatusParser
     */
    public EmergencyStatusParser() {
        super("recorded but unhandled/response in progress/completed",EmergencyStatus.class);
    }

    /**
     * Returns the EmergencyStatus equivalent of the given input.
     * @param input The textual representation of the an EmergencyStatus.
     * @return The EmergencyStatus that is equivalent to the input.
     * @throws ParsingException If the given input can't be parsed to an EmergencyStatus equivalent.
     */
    @Override
    public EmergencyStatus parse(String input) throws ParsingException {
        try {
            return EmergencyStatus.parse(input);
        } catch (InvalidEmergencyStatusException ex) {
            throw new ParsingException(ex.getMessage());
        }
    }
}
