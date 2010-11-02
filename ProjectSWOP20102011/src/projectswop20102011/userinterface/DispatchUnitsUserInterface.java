package projectswop20102011.userinterface;

import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsUserInterface extends CommandUserInterface {

    private final DispatchUnitsController controller;

    public DispatchUnitsUserInterface (DispatchUnitsController controller) throws InvalidControllerException, InvalidCommandNameException {
        super("dispatch units");
        if(controller == null) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
