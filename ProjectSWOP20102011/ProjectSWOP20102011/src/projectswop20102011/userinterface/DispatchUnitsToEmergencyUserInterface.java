package projectswop20102011.userinterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.controllers.DispatchUnitsToEmergencyController;
import projectswop20102011.controllers.EmergencyMapper;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.domain.SendableStatus;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.utils.parsers.BooleanParser;
import projectswop20102011.utils.parsers.LongParser;
import projectswop20102011.utils.parsers.StringParser;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsToEmergencyUserInterface extends CommandUserInterface {

	private final DispatchUnitsToEmergencyController dispatchUnitsController;
	private final EmergencyMapper emergencyMapper;
	private final InspectEmergenciesController inspectEmergenciesController;

	public DispatchUnitsToEmergencyUserInterface(DispatchUnitsToEmergencyController dispatchUnitsController, InspectEmergenciesController inspectEmergenciesController, EmergencyMapper emergencyController) throws InvalidControllerException, InvalidCommandNameException {
		super("dispatch units to emergency");

		if (dispatchUnitsController == null || emergencyController == null || inspectEmergenciesController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.dispatchUnitsController = dispatchUnitsController;
		this.emergencyMapper = emergencyController;
		this.inspectEmergenciesController = inspectEmergenciesController;
	}

	@Override
	public DispatchUnitsToEmergencyController getController() {
		return this.dispatchUnitsController;
	}

	private EmergencyMapper getEmergencyMapper() {
		return emergencyMapper;
	}

	private InspectEmergenciesController getInspectEmergenciesController() {
		return inspectEmergenciesController;
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

	@Override
	public void handleUserInterface() {
		Emergency[] emergenciesUnhandled = this.getInspectEmergenciesController().inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
		Emergency[] emergenciesResponded = getInspectEmergenciesController().inspectEmergenciesOnStatus(SendableStatus.RESPONSE_IN_PROGRESS);
		this.writeOutput(String.format("Founded emergencies (%s):", (emergenciesUnhandled.length + emergenciesResponded.length)));
		for (Emergency em : emergenciesResponded) {
			if (!em.isPartOfADisaster()) {
				this.writeOutput(String.format("\t%s", this.getShortInformationString(em)) + " id: " + getEmergencyMapper().getEmergencyId(em).toString());
			}
		}
		for (Emergency em : emergenciesUnhandled) {
			if (!em.isPartOfADisaster()) {
				this.writeOutput(String.format("\t%s", this.getShortInformationString(em)) + " id: " + getEmergencyMapper().getEmergencyId(em).toString());
			}
		}

		try {
			long emergencyId = this.parseInputToType(new LongParser(), "The id of the emergency");
			Emergency selectedEmergency = getEmergencyMapper().getEmergencyFromId(emergencyId);
			if (selectedEmergency == null) {
				this.writeOutput("Emergency not found.");
			} else if (selectedEmergency.getStatus().matches("completed")) {
				this.writeOutput("An emergency that is already completed, can't be chosen.");
			} else {
				Hashtable<String, String> information = selectedEmergency.getLongInformation();
				this.writeOutput("EMERGENCY DETAILS:");
				this.writeOutput(String.format("\ttype: %s", selectedEmergency.getClass().getSimpleName()));
				for (Entry<String, String> entry : information.entrySet()) {
					this.writeOutput(String.format("\t%s: %s", entry.getKey(), entry.getValue()));
				}
				this.writeOutput("SUGGESTED UNITS:");
				try {
					Set<Unit> suggestedUnits = this.getController().getUnitsByPolicy(selectedEmergency);
					ArrayList<Unit> suggestedUnitsL = new ArrayList<Unit>(suggestedUnits);
					//print information for the suggested units
					for (int i = 0; i < suggestedUnitsL.size(); i++) {
						Unit u = suggestedUnitsL.get(i);
						double distance = u.getCurrentLocation().getDistanceTo(selectedEmergency.getLocation());
						long eta = u.getETA(selectedEmergency.getLocation());
						this.writeOutput(String.format("\t%s\t%s\t%s\t%.4f\t%s", i, u.getClass().getSimpleName(), u.getName(), distance, eta));
					}
					boolean acceptSuggestion = this.parseInputToType(new BooleanParser(), "Do you accept the suggestion");
					if (!acceptSuggestion) {
						HashSet<Unit> assignedUnits;
						boolean retry = true;
						while (retry) {
							assignedUnits = new HashSet<Unit>();
							retry = false;
							this.writeOutput("REQUIRED UNITS:");
							this.writeOutput(getController().getRequiredUnits(selectedEmergency));
							this.writeOutput("AVAILABLE UNITS:");
							List<Unit> availableUnits = this.getController().getAvailableUnitsNeededSorted(selectedEmergency);
							for (int i = 0; i < availableUnits.size(); i++) {
								Unit u = availableUnits.get(i);
								double distance = u.getCurrentLocation().getDistanceTo(selectedEmergency.getLocation());
								long eta = u.getETA(selectedEmergency.getLocation());
								this.writeOutput(String.format("\t%s\t%s\t%s\t%.4f\t%s", i, u.getClass().getSimpleName(), u.getName(), distance, eta));
							}
							String expression;
							do {
								this.writeOutput("Type in a unit id, or type \"stop\" to finish the list.");
								expression = this.parseInputToType(new StringParser(), "id");
								if (!expression.equals("stop")) {
									try {
										int id = Integer.parseInt(expression);
										assignedUnits.add(availableUnits.get(id));
										this.writeOutput("Unit added.");
									} catch (Exception ex) {
										this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
									}
								}
							} while (!expression.equals("stop"));
							try {
								this.getController().dispatchToEmergency(selectedEmergency, assignedUnits);
								this.writeOutput("The chosen units are assigned");
							} catch (Exception ex) {
								this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
								this.writeOutput("Please give a new selection of units.");
								retry = true;
							}
						}
					} else {
						this.getController().dispatchToEmergency(selectedEmergency, suggestedUnits);
						this.writeOutput("The suggested units are assigned");
					}
				} catch (Exception ex) {
					this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
				}
			}
		} catch (ParsingAbortedException ex) {
			this.writeOutput("Command aborted.");
		}

	}
}
