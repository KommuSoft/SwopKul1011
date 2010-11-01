package projectswop20102011.userinterface;

import projectswop20102011.controllers.Controller;
import projectswop20102011.exceptions.InvalidCommandNameException;

/**
 * A user interface that handles the indicate end of emergency use case (#6)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EndOfEmergencyUserInterface extends CommandUserInterface {

    public EndOfEmergencyUserInterface () throws InvalidCommandNameException {
        super("end of emergency");
    }

    @Override
    public void HandleUserInterface() {
        
    }

    @Override
    public Controller getController() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
