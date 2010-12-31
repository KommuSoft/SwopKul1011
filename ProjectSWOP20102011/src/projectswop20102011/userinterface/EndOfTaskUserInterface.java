package projectswop20102011.userinterface;

import projectswop20102011.domain.Unit;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.utils.parsers.StringParser;

/**
 * A user interface that handles the indicate end of emergency use case (#6)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EndOfTaskUserInterface extends CommandUserInterface {

    private final EndOfTaskController controller;

    public EndOfTaskUserInterface(EndOfTaskController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("end of task");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public void handleUserInterface() {
        try {
            String name = this.parseInputToType(new StringParser(), "name of the unit");
            Unit unit = this.getController().findUnit(name);
            if (unit != null) {
                this.writeOutput(String.format("login %s.", unit));
                try {
                    this.getController().indicateEndOfTask(unit);
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
    public EndOfTaskController getController() {
        return this.controller;
    }
}
