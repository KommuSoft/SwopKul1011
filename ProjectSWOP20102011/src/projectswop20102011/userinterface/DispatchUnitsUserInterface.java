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
                this.writeOutput("NEEDED UNITS:");
		//TODO: alternatief voor constraint zoeken
                //UnitsNeeded unitsNeeded = this.getController().getUnitsNeeded(selectedEmergency);
                this.writeOutput("AVAILABLE UNITS:");
                ArrayList<Unit> availableUnits;
                try {
                    availableUnits = (ArrayList<Unit>) this.getController().getUnitsByPolicy(selectedEmergency);
                    for (int i = 0; i < availableUnits.size(); i++) {
                        Unit u = availableUnits.get(i);
                        double distance = u.getCurrentLocation().getDistanceTo(selectedEmergency.getLocation());
                        this.writeOutput(String.format("\t%s\t%s\t%s\t%s", i, u.getClass().getSimpleName(), u.getName(), distance));
                    }
                    String expression;
                    HashSet<Unit> assignedUnits = new HashSet<Unit>();
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
                    this.getController().dispatchToEmergency(selectedEmergency, new ArrayList<Unit>(assignedUnits));
                    this.writeOutput("Units are assigned");
                } catch (Exception ex) {
                    this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                }
            }
        } catch (ParsingAbortedException ex) {
            this.writeOutput("Command aborted.");
        }

    }
}
