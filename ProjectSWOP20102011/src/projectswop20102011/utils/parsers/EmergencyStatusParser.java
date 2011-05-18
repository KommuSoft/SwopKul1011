package projectswop20102011.utils.parsers;

import projectswop20102011.domain.SendableStatus;

/**
 * A parser that can parse the status of an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencyStatusParser extends EnumParser<SendableStatus> {

    /**
     * Creates a new instance of an EmergencyStatusParser
     */
    public EmergencyStatusParser() {
        super(SendableStatus.class,"format: recorded but unhandled/response in progress/completed");
    }
    
}
