package projectswop20102011.userinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateDisasterController;
import projectswop20102011.controllers.EmergencyMapper;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.utils.parsers.StringParser;

public class CreateDisasterUserInterface extends CommandUserInterface {

	private final CreateDisasterController createDisasterController;
	private final InspectEmergenciesController inspectEmergenciesController;
	private final EmergencyMapper emergencyMapper;

	public CreateDisasterUserInterface(CreateDisasterController createDisasterController, InspectEmergenciesController inspectEmergenciesController, EmergencyMapper emergencyMapper) throws InvalidCommandNameException, InvalidControllerException {
		super("create disaster");

		if (createDisasterController == null || inspectEmergenciesController == null || emergencyMapper == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.createDisasterController = createDisasterController;
		this.inspectEmergenciesController = inspectEmergenciesController;
		this.emergencyMapper = emergencyMapper;
	}

	@Override
	public CreateDisasterController getController() {
		return this.createDisasterController;
	}

	private InspectEmergenciesController getInspectEmergenciesController() {
		return inspectEmergenciesController;
	}

	private EmergencyMapper getEmergencyMapper() {
		return emergencyMapper;
	}

	/**
	 * Build a short textual representation of the given emergency.
	 * @param emergency The given emergency to convert.
	 * @return A textual representation of the given Emergency.
	 */
	private String getShortInformationString(Emergency emergency) {
		//String result = String.format("[type=%s", emergency.getClass().getSimpleName());
		String result = String.format("[assignable=%s", this.getInspectEmergenciesController().canBeResolved(emergency));
		Set<Entry<String, String>> set = this.getInspectEmergenciesController().getEmergencyShortInformation(emergency);
		for (Entry<String, String> e : set) {
			result += String.format("; %s=%s", e.getKey(), e.getValue());
		}
		return result + "]";
	}

	/**
	 * Build a short textual representation of the given emergency.
	 * @param emergency The given emergency to convert.
	 * @return A textual representation of the given Emergency.
	 */
	private String getLongInformationString(Emergency emergency) {
		//String result = String.format("\ttype=%s", emergency.getClass().getSimpleName());
		String result = String.format("\tassignable=%s", this.getInspectEmergenciesController().canBeResolved(emergency));
		Set<Entry<String, String>> set = this.getInspectEmergenciesController().getEmergencyLongInformation(emergency);
		for (Entry<String, String> e : set) {
			result += String.format("\n\t%s=%s", e.getKey(), e.getValue());
		}
		return result;
	}

	@Override
	public void handleUserInterface() {
		boolean viewEmergencyDetail;
		String description = "";
		try {
			description = this.parseInputToType(new StringParser(), "description of the disaster");
		} catch (ParsingAbortedException ex) {
			Logger.getLogger(CreateDisasterUserInterface.class.getName()).log(Level.SEVERE, null, ex);
		}
		Emergency[] emergencies = this.getInspectEmergenciesController().inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED);
		this.writeOutput(String.format("Founded emergencies (%s):", emergencies.length));
		for (Emergency em : emergencies) {
			this.writeOutput(String.format("\t%s", this.getShortInformationString(em)) + " id: " + getEmergencyMapper().getEmergencyId(em).toString());
		}

		do {
			viewEmergencyDetail = true;
			this.writeOutput("Type in an id to view any details of an emergency,");
			this.writeOutput("finally type \"quit\" to end inspecting details of emergencies.");
			String input = this.readInput().toLowerCase();
			if (input.equals("quit")) {
				viewEmergencyDetail = false;
			} else if (!input.matches("[0-9]+")) {
				return;
			} else {
				try {
					long id = Long.parseLong(input);
					Emergency emergency = getEmergencyMapper().getEmergencyFromId(id);
					if (emergency != null) {
						if (emergency.getStatus().equals(EmergencyStatus.RECORDED_BUT_UNHANDLED)) {
							this.writeOutput(this.getLongInformationString(emergency));
						} else {
							this.writeOutput("ERROR: can't choose an emergency that isn't unhandled.");
						}

					} else {
						this.writeOutput("ERROR: can't find the asked emergency, please try again.");
					}
				} catch (Exception e) {
					this.writeOutput("ERROR: Unknown command, try again.");
				}
			}
		} while (viewEmergencyDetail);

		List<Emergency> emergenciesToAssign = new ArrayList<Emergency>(0);
		Emergency emergencyToAssign;
		String expression;
		try {
			do {
				this.writeOutput("Type in a emergency id, or type \"stop\" to finish the list.");
				expression = this.parseInputToType(new StringParser(), "id");
				if (!expression.equals("stop")) {
					try {
						int id = Integer.parseInt(expression);
						emergencyToAssign = getEmergencyMapper().getEmergencyFromId(id);
						emergenciesToAssign.add(emergencyToAssign);
						this.writeOutput("Emergency added.");
					} catch (Exception ex) {
						this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
					}
				}
			} while (!expression.equals("stop"));
		} catch (ParsingAbortedException parsingAbortedException) {
		}

		try {
			getController().createDisaster(emergenciesToAssign, description);
		} catch (InvalidEmergencyException ex) {
			this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
		} catch (InvalidConstraintListException ex) {
			Logger.getLogger(CreateDisasterUserInterface.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
