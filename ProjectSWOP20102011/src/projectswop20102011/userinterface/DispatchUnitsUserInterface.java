package projectswop20102011.userinterface;

import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Emergency;
import projectswop20102011.Unit;
import projectswop20102011.UnitsNeeded;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.LongParser;

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
                Hashtable<String, String> information = selectedEmergency.toLongInformationString();
                this.writeOutput("EMERGENCY DETAILS:");
                this.writeOutput(String.format("\ttype: %s", selectedEmergency.getClass().getSimpleName()));
                for (Entry<String, String> entry : information.entrySet()) {
                    this.writeOutput(String.format("\t%s: %s", entry.getKey(), entry.getValue()));
                }
                this.writeOutput("NEEDED UNITS:");
                UnitsNeeded unitsNeeded = this.controller.getNeededUnits(selectedEmergency);
                Class[] types = unitsNeeded.getUnits();
                long[] needed = unitsNeeded.getNumbersNeeded();
                for (int i = 0; i < types.length; i++) {
                    this.writeOutput(String.format("\t%s: %s", types[i].getSimpleName(), needed[i]));
                }
                this.writeOutput("AVAILABLE UNITS:");
                Unit[] availableUnits;
                try {
                    availableUnits = this.controller.getAvailableUnitsSorted(selectedEmergency);
                    for (int i = 0; i < availableUnits.length; i++) {
                        Unit u = availableUnits[i];
                        double distance = u.getCurrentLocation().getDistanceTo(selectedEmergency.getLocation());
                        this.writeOutput(String.format("\t%s\t%s\t%s\t%s", i, u.getClass().getSimpleName(), u.getName(), distance));
                    }
                } catch (InvalidEmergencyException ex) {
                    this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                }
            }
        } catch (ParsingAbortedException ex) {
            this.writeOutput("Command aborted.");
        }

    }
}
