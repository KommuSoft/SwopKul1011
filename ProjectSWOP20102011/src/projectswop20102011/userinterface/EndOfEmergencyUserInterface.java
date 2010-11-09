package projectswop20102011.userinterface;

import projectswop20102011.Unit;
import projectswop20102011.controllers.EndOfEmergencyController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.StringParser;

/**
 * A user interface that handles the indicate end of emergency use case (#6)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EndOfEmergencyUserInterface extends CommandUserInterface {

    private final EndOfEmergencyController controller;

    public EndOfEmergencyUserInterface(EndOfEmergencyController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("end of emergency");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public void HandleUserInterface() {
        try {
            String name = this.parseInputToType(new StringParser(), "name of the unit");
            Unit unit = this.getController().findUnit(name);
            if (unit != null) {
                this.writeOutput(String.format("login %s.", unit));
                try {
                    this.getController().indicateEndOfEmergency(unit);
                } catch (Exception ex) {
                    this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                }
                this.writeOutput(String.format("logout %s.", unit));
            } else {
                this.writeOutput(String.format("Can't find a unit named \"%s\"", name));
            }
        } catch (ParsingAbortedException ex) {
            this.writeOutput("Command aborted.");
        }

    }

    @Override
    public EndOfEmergencyController getController() {
        return this.controller;
    }
}
