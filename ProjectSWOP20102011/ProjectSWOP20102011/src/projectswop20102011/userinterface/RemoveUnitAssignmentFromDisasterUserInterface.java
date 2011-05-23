package projectswop20102011.userinterface;

import java.util.ArrayList;
import projectswop20102011.controllers.DisasterMapper;
import projectswop20102011.controllers.RemoveUnitAssignmentFromDisasterController;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.utils.parsers.LongParser;
import projectswop20102011.utils.parsers.StringParser;

public class RemoveUnitAssignmentFromDisasterUserInterface extends CommandUserInterface {

	private final RemoveUnitAssignmentFromDisasterController removeUnitAssignmentFromDisasterController;
	private final DisasterMapper disasterController;

	public RemoveUnitAssignmentFromDisasterUserInterface(RemoveUnitAssignmentFromDisasterController removeUnitAssignmentFromDisasterController, DisasterMapper disasterController) throws InvalidControllerException, InvalidCommandNameException {
		super("remove unit assignment from disaster");
		if (removeUnitAssignmentFromDisasterController == null || disasterController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.removeUnitAssignmentFromDisasterController = removeUnitAssignmentFromDisasterController;
		this.disasterController = disasterController;
	}

	@Override
	public RemoveUnitAssignmentFromDisasterController getController() {
		return this.removeUnitAssignmentFromDisasterController;
	}

	private DisasterMapper getDisasterController() {
		return disasterController;
	}

	@Override
	public void handleUserInterface() {
		long disasterID = 0;
		try {
			disasterID = this.parseInputToType(new LongParser(), "The id of the disaster");
		} catch (ParsingAbortedException ex) {
			writeOutput(String.format("ERROR: %s", ex.getMessage()));
		}
		Disaster selectedDisaster = getDisasterController().getDisasterFromId(disasterID);
		if (selectedDisaster == null) {
			this.writeOutput("Disaster not found.");
		} else {
			this.writeOutput("WORKING UNITS:");
			ArrayList<Unit> workingUnits = this.getController().getWorkingUnits(selectedDisaster);
			if (workingUnits.isEmpty()) {
				this.writeOutput("ERROR: There are no working units.");
			} else {
				for (int i = 0; i < workingUnits.size(); i++) {
					this.writeOutput(String.format("\t%s\t%s\t%s", i, workingUnits.get(i).getClass().getSimpleName(), workingUnits.get(i).getName()));
				}
				String expression = null;
				do {
					this.writeOutput("Type in a unit id, or type \"stop\" to finish the list.");
					try {
						expression = this.parseInputToType(new StringParser(), "id");
					} catch (ParsingAbortedException ex) {
						writeOutput(String.format("ERROR: %s", ex.getMessage()));
					}
					if (!expression.equals("stop")) {
						int id = Integer.parseInt(expression);
						try {
							this.getController().withdrawUnit(workingUnits.get(id));
							this.writeOutput("Unit removed.");
						} catch (Exception e) {
							writeOutput(String.format("ERROR: %s", e.getMessage()));
						}

					}
				} while (!expression.equals("stop"));
				this.writeOutput("Units are removed");
			}
		}
	}
}
