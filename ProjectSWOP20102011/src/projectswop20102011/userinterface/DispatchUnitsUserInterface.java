package projectswop20102011.userinterface;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Emergency;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.LongParser;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsUserInterface extends CommandUserInterface {

    private final DispatchUnitsController controller;

    public DispatchUnitsUserInterface(DispatchUnitsController controller) throws InvalidControllerException, InvalidCommandNameException {
        super("dispatch units");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public DispatchUnitsController getController() {
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
                
            }
        } catch (ParsingAbortedException ex) {
            this.writeOutput("Command aborted.");
        }

    }
}
