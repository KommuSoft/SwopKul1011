package projectswop20102011.utils.parsers;

import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.ParsingException;

/**
 * A parser for the emergency severity status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencySeverityParser extends EnumParser<SendableSeverity> {

    /**
     * Creates a new instance of an EmergencySeverityParser.
     */
    public EmergencySeverityParser () {
        super(SendableSeverity.class,"format: benign/normal/serious/urgent");
    }

}
