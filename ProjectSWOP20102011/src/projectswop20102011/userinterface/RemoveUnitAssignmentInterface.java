package projectswop20102011.userinterface;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.RemoveUnitAssignmentController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.LongParser;

/**
 * A user interface that handles the remove unit assignment use case
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class RemoveUnitAssignmentInterface extends CommandUserInterface {

    private final RemoveUnitAssignmentController controller;

    /**
     * 
     * @param controller
     * @throws InvalidCommandNameException
     * @throws InvalidControllerException
     */
    public RemoveUnitAssignmentInterface(RemoveUnitAssignmentController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("remove unit assignment");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public RemoveUnitAssignmentController getController() {
        return this.controller;
    }

    @Override
    public void HandleUserInterface() {
        try {
            long emergencyId = this.parseInputToType(new LongParser(), "The id of the emergency");
            Emergency selectedEmergency = this.getController().getEmergencyFromId(emergencyId);
            if (selectedEmergency == null) {
                this.writeOutput("Emergency not found.");
            } else {
                //TODO: Hier komt dan het echte werk
            }
        } catch (ParsingAbortedException ex) {
            Logger.getLogger(RemoveUnitAssignmentInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
