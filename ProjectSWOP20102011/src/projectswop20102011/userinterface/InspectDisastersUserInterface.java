package projectswop20102011.userinterface;

import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.Controller;
import projectswop20102011.controllers.DisasterMapper;
import projectswop20102011.controllers.InspectDisastersController;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.exceptions.InvalidAddedDisasterException;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.utils.parsers.EmergencyStatusParser;

/**
 * A command user interface to inspect the disasters
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectDisastersUserInterface extends CommandUserInterface{

	private final InspectDisastersController inspectDisastersController;
	private final DisasterMapper disasterController;

	public InspectDisastersUserInterface(InspectDisastersController inspectDisastersController, DisasterMapper disasterController) throws InvalidCommandNameException, InvalidControllerException {
		super("inspect disasters");

		if (inspectDisastersController == null || disasterController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.inspectDisastersController = inspectDisastersController;
		this.disasterController = disasterController;
	}

	@Override
	public InspectDisastersController getController() {
		return this.inspectDisastersController;
	}

	private DisasterMapper getDisasterController() {
		return disasterController;
	}

	/**
	 * Build a short textual representation of the given disaster.
	 * @param disaster The given disaster to convert.
	 * @return A textual representation of the given Disaster.
	 */
	private String getShortInformationString(Disaster disaster) {
		//String result = String.format("[type=%s", emergency.getClass().getSimpleName());
		String result = "";
		Set<Entry<String, String>> set = this.getController().getDisasterShortInformation(disaster);
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
	private String getLongInformationString(Disaster disaster) {
		//String result = String.format("\ttype=%s", emergency.getClass().getSimpleName());
		String result = "";
		Set<Entry<String, String>> set = this.getController().getDisasterLongInformation(disaster);
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
				EmergencyStatus status = this.parseInputToType(new EmergencyStatusParser(), "status of the disaster");
				Disaster[] disasters = this.getController().inspectDisastersOnStatus(status);
				this.writeOutput(String.format("Founded disasters (%s):", disasters.length));
				for (Disaster em : disasters) {
					this.writeOutput(String.format("\t%s", this.getShortInformationString(em)) + " id: " + getDisasterController().getDisasterId(em).toString());
				}
				do {
					viewEmergencyDetail = true;
					this.writeOutput("Type in an id to view any details of a disaster,");
					this.writeOutput("otherwise type \"other list\" to view another list,");
					this.writeOutput("finally type \"quit\" to end inspecting disasters.");
					String input = this.readInput().toLowerCase();
					if (input.equals("quit")) {
						return;
					} else if (input.equals("other list")) {
						viewEmergencyDetail = false;
						viewOtherList = true;
					} else {
						try {
							long id = Long.parseLong(input);
							Disaster disaster = getDisasterController().getDisasterFromId(id);
							if (disaster != null) {
								this.writeOutput(this.getLongInformationString(disaster));
							} else {
								this.writeOutput("ERROR: can't find the asked disaster, please try again.");
							}
						} catch (Exception e) {
							this.writeOutput("ERROR: Unknown command, try again.");
						}
					}
				} while (viewEmergencyDetail);
			} catch (InvalidAddedDisasterException ex) {
				Logger.getLogger(InspectDisastersUserInterface.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ParsingAbortedException ex) {
				this.writeOutput("command aborted.");
			}
		} while (viewOtherList);
	}
}
