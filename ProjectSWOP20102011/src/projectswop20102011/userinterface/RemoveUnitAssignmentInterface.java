package projectswop20102011.userinterface;

import projectswop20102011.controllers.Controller;
import projectswop20102011.controllers.RemoveUnitAssignmentController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;

/**
 *
 * @author Pieter-Jan
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
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void HandleUserInterface() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
