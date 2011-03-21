package projectswop20102011.userinterface;

import java.util.ArrayList;
import projectswop20102011.controllers.EmergencyMapper;
import projectswop20102011.controllers.RemoveUnitAssignmentController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.utils.parsers.LongParser;
import projectswop20102011.utils.parsers.StringParser;

/**
 * A user interface that handles the remove unit assignment use case
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class RemoveUnitAssignmentInterface extends CommandUserInterface {

	private final RemoveUnitAssignmentController removeUnitAssignmentController;
	private final EmergencyMapper emergencyController;

	/**
	 *
	 * @param removeUnitAssignmentController
	 * @param emergencyController
	 * @throws InvalidCommandNameException
	 * @throws InvalidControllerException
	 */
	public RemoveUnitAssignmentInterface(RemoveUnitAssignmentController removeUnitAssignmentController, EmergencyMapper emergencyController) throws InvalidCommandNameException, InvalidControllerException {
		super("remove unit assignment");
		if (removeUnitAssignmentController == null || emergencyController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.removeUnitAssignmentController = removeUnitAssignmentController;
		this.emergencyController = emergencyController;
	}

	@Override
	public RemoveUnitAssignmentController getController() {
		return this.removeUnitAssignmentController;
	}

	private EmergencyMapper getEmergencyController() {
		return emergencyController;
	}

	@Override
	public void handleUserInterface() {
		long emergencyId = 0;
		try {
			emergencyId = this.parseInputToType(new LongParser(), "The id of the emergency");
		} catch (ParsingAbortedException ex) {
			writeOutput(String.format("ERROR: %s", ex.getMessage()));
		}
		Emergency selectedEmergency = getEmergencyController().getEmergencyFromId(emergencyId);
		if (selectedEmergency == null) {
			this.writeOutput("Emergency not found.");
		} else {
			this.writeOutput("WORKING UNITS:");
			ArrayList<Unit> workingUnits = this.getController().getWorkingUnits(selectedEmergency);
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
