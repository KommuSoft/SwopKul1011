package projectswop20102011.userinterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.EmergencyController;
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
public class DispatchUnitsUserInterface extends CommandUserInterface {

	private final DispatchUnitsController dispatchUnitsController;
	private final EmergencyController emergencyController;

	public DispatchUnitsUserInterface(DispatchUnitsController dispatchUnitsController, EmergencyController emergencyController) throws InvalidControllerException, InvalidCommandNameException {
		super("dispatch units");

		//TODO: bad smell duplicated code, opgelost?
		if (dispatchUnitsController == null || emergencyController == null) {
			throw new InvalidControllerException("Controller must be effective.");
		}
		this.dispatchUnitsController = dispatchUnitsController;
		this.emergencyController = emergencyController;
	}

	//TODO: rare naam :s
	@Override
	public DispatchUnitsController getController() {
		return this.dispatchUnitsController;
	}

	private EmergencyController getEmergencyController(){
		return emergencyController;
	}

	@Override
	public void handleUserInterface() {
		try {
			long emergencyId = this.parseInputToType(new LongParser(), "The id of the emergency");
			Emergency selectedEmergency = getEmergencyController().getEmergencyFromId(emergencyId);
			if (selectedEmergency == null) {
				this.writeOutput("Emergency not found.");
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
							ArrayList<Unit> availableUnits = (ArrayList<Unit>) this.getController().getAvailableUnitsSorted(selectedEmergency);
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
