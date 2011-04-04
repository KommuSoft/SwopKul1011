package projectswop20102011.utils.parsers;

import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser for the emergency severity status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencySeverityParser extends EnumParser<EmergencySeverity> {

    /**
     * Creates a new instance of an EmergencySeverityParser.
     */
    public EmergencySeverityParser () {
        super(EmergencySeverity.class,"format: benign/normal/serious/urgent");
    }

}
