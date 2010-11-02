package projectswop20102011.userinterface;

import projectswop20102011.controllers.EndOfEmergencyController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;

/**
 * A user interface that handles the indicate end of emergency use case (#6)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EndOfEmergencyUserInterface extends CommandUserInterface {

    private final EndOfEmergencyController controller;
    
    public EndOfEmergencyUserInterface (EndOfEmergencyController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("end of emergency");
        if(controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public void HandleUserInterface() {
        
    }

    @Override
    public EndOfEmergencyController getController() {
        return this.controller;
    }

}
