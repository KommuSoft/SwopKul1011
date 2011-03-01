package projectswop20102011.userinterface;

import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.controllers.EmergencyController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.utils.parsers.EmergencyStatusParser;

/**
 * A command user interface to inspect the emergencies (use case #3)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectEmergenciesUserInterface extends CommandUserInterface {

	private final InspectEmergenciesController inspectEmergenciesController;
	private final EmergencyController emergencyController;

	public InspectEmergenciesUserInterface(InspectEmergenciesController inspectEmergenciesController, EmergencyController emergencyController) throws InvalidControllerException, InvalidCommandNameException {
		super("inspect emergencies");
		//TODO duplicated code
		if (inspectEmergenciesController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.inspectEmergenciesController = inspectEmergenciesController;
		if (emergencyController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.emergencyController = emergencyController;
	}

	//TODO rare naam
	/**
	 * Returns the InspectEmergenciesController used by this command user interface.
	 * @return The inspectEmergenciesController used by this command user interface.
	 */
	@Override
	public InspectEmergenciesController getController() {
		return this.inspectEmergenciesController;
	}

	private EmergencyController getEmergencyController() {
		return emergencyController;
	}

	/**
	 * Build a short textual representation of the given emergency.
	 * @param emergency The given emergency to convert.
	 * @return A textual representation of the given Emergency.
	 */
	private String getShortInformationString(Emergency emergency) {
		String result = String.format("[type=%s", emergency.getClass().getSimpleName());
		result += String.format("; assignable=%s", this.getController().canBeAssigned(emergency));
		Set<Entry<String, String>> set = this.getController().getEmergencyShortInformation(emergency);
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
		String result = String.format("\ttype=%s", emergency.getClass().getSimpleName());
		result += String.format("\n\tassignable=%s", this.getController().canBeAssigned(emergency));
		Set<Entry<String, String>> set = this.getController().getEmergencyLongInformation(emergency);
		for (Entry<String, String> e : set) {
			result += String.format("\n\t%s=%s", e.getKey(), e.getValue());
		}
		return result;
	}

	@Override
	public void handleUserInterface() {
		boolean viewOtherList, viewEmergencyDetail;
		do {
			viewOtherList = false;
			try {
				EmergencyStatus status = this.parseInputToType(new EmergencyStatusParser(), "status of the emergency");
				Emergency[] emergencies = this.getController().inspectEmergenciesOnStatus(status);
				this.writeOutput(String.format("Founded emergencies (%s):", emergencies.length));
				for (Emergency em : emergencies) {
					this.writeOutput(String.format("\t%s", this.getShortInformationString(em)) + " id: " + getEmergencyController().getEmergencyId(em).toString());
				}
				do {
					viewEmergencyDetail = true;
					this.writeOutput("Type in an id to view any details of an emergency,");
					this.writeOutput("otherwise type \"other list\" to view another list,");
					this.writeOutput("finally type \"quit\" to end inspecting emergencies.");
					String input = this.readInput().toLowerCase();
					if (input.equals("quit")) {
						return;
					} else if (input.equals("other list")) {
						viewEmergencyDetail = false;
						viewOtherList = true;
					} else {
						try {
							long id = Long.parseLong(input);
							Emergency emergency = getEmergencyController().getEmergencyFromId(id);
							if (emergency != null) {
								this.writeOutput(this.getLongInformationString(emergency));
							} else {
								this.writeOutput("ERROR: can't find the asked emergency, please try again.");
							}
						} catch (Exception e) {
							this.writeOutput("ERROR: Unknown command, try again.");
						}
					}
				} while (viewEmergencyDetail);
			} catch (ParsingAbortedException ex) {
				this.writeOutput("command aborted.");
			}
		} while (viewOtherList);
	}
}
