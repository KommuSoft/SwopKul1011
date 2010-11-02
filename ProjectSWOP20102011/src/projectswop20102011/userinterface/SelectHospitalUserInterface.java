package projectswop20102011.userinterface;

import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class SelectHospitalUserInterface extends CommandUserInterface {

    private final SelectHospitalController controller;
    
    public SelectHospitalUserInterface (SelectHospitalController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("select hospital");
        if(controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public SelectHospitalController getController() {
        return this.controller;
    }

    @Override
    public void HandleUserInterface() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}