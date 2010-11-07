package projectswop20102011.userinterface;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.IntegerParser;

/**
 * A user interface to handle the time ahead use case.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TimeAheadUserInterface extends CommandUserInterface {

    private final TimeAheadController controller;

    public TimeAheadUserInterface(TimeAheadController controller) throws InvalidControllerException, InvalidCommandNameException {
        super("time ahead");
        if (controller == null) {
            throw new InvalidControllerException("controller must be effective.");
        }
        this.controller = controller;
    }

    /**
     * Gets the time ahead controller used by this command user interface.
     * @return The controller used by this command user interface.
     */
    @Override
    public TimeAheadController getController() {
        return this.controller;
    }

    @Override
    public void HandleUserInterface() {
        try {
            int seconds = this.parseInputToType(new IntegerParser(), "time expired in seconds");
			try {
				this.getController().doTimeAheadAction(seconds);
			} catch (InvalidDurationException ex) {
				Logger.getLogger(TimeAheadUserInterface.class.getName()).log(Level.SEVERE, null, ex);
			}
        } catch (ParsingAbortedException ex) {
            this.writeOutput("command aborted.");
        }
    }
}
