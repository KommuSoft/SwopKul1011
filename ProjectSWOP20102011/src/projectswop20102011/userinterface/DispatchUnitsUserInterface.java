package projectswop20102011.userinterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.Unit;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.BooleanParser;
import projectswop20102011.userinterface.parsers.LongParser;
import projectswop20102011.userinterface.parsers.StringParser;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsUserInterface extends CommandUserInterface {

    private final DispatchUnitsController controller;

    public DispatchUnitsUserInterface(DispatchUnitsController controller) throws InvalidControllerException, InvalidCommandNameException {
        super("dispatch units");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public DispatchUnitsController getController() {
        return this.controller;
    }

	@Override
	public void HandleUserInterface() {
		try {
			long emergencyId = this.parseInputToType(new LongParser(), "The id of the emergency");
			Emergency selectedEmergency = this.getController().getEmergencyFromId(emergencyId);
			if (selectedEmergency == null) {
				this.writeOutput("Emergency not found.");
			} else {
				Hashtable<String, String> information = selectedEmergency.getLongInformation();
				this.writeOutput("EMERGENCY DETAILS:");
				this.writeOutput(String.format("\ttype: %s", selectedEmergency.getClass().getSimpleName()));
				for (Entry<String, String> entry : information.entrySet()) {
					this.writeOutput(String.format("\t%s: %s", entry.getKey(), entry.getValue()));
				}
				//TODO: alternatief voor constraint zoeken
				//UnitsNeeded unitsNeeded = this.getController().getUnitsNeeded(selectedEmergency);
				this.writeOutput("SUGGESTED UNITS:");
				ArrayList<Unit> suggestedUnits;
				try {
					suggestedUnits = (ArrayList<Unit>) this.getController().getUnitsByPolicy(selectedEmergency);
					for (int i = 0; i < suggestedUnits.size(); i++) {
						Unit u = suggestedUnits.get(i);
						double distance = u.getCurrentLocation().getDistanceTo(selectedEmergency.getLocation());
						long eta = u.getETA(selectedEmergency.getLocation());
						this.writeOutput(String.format("\t%s\t%s\t%s\t%.4f\t%s", i, u.getClass().getSimpleName(), u.getName(), distance, eta));
					}
					boolean acceptSuggestion = this.parseInputToType(new BooleanParser(), "Do you accept the suggestion");
					HashSet<Unit> assignedUnits = new HashSet<Unit>();
					boolean retry = true;
					if (!acceptSuggestion) {
						while(retry){
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
								this.getController().dispatchToEmergency(selectedEmergency, new ArrayList<Unit>(assignedUnits));
							} catch (Exception ex) {
								this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
								this.writeOutput("Please give a new selection of units.");
								retry = true;
							}
						}
						this.writeOutput("The chosen units are assigned");
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
