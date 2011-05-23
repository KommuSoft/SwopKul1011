package projectswop20102011.userinterface;

import java.util.List;
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
		List<Unit> units = this.getController().findAllUnits();
		if (units.isEmpty()) {
			this.writeOutput("ERROR: There are no units.");
		} else {
			for (int i = 0; i < units.size(); ++i) {
				this.writeOutput(String.format("\t%s\t%s", i, units.get(i).getName()));
			}
		}
		
		this.writeOutput("Type in a unit id");
		String expression = null;
		try {
			expression = this.parseInputToType(new StringParser(), "id");
		} catch (ParsingAbortedException ex) {
			writeOutput(String.format("ERROR: %s", ex.getMessage()));
		}

		Unit u = null;
		int id = -1;
		try {
			id = Integer.parseInt(expression);
		} catch (NumberFormatException ex) {
			writeOutput(String.format("ERROR: %s", ex.getMessage()));
		}
		if (id >= 0) {
			try {
				u = units.get(id);
			} catch (Exception e) {
				writeOutput(String.format("ERROR: %s", e.getMessage()));
			}
		}

		if (id < units.size()) {
			this.writeOutput(String.format("login %s.", u));
			try {
				this.getController().indicateEndOfTask(u);
			} catch (Exception ex) {
				this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
			}
			this.writeOutput(String.format("logout %s.", u));
		} else {
			this.writeOutput(String.format("Can't find a unit with id \"%s\"", id));
		}


	}

	@Override
	public EndOfTaskController getController() {
		return this.controller;
	}
}