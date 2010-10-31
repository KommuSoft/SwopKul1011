package projectswop20102011.userinterface;

import projectswop20102011.EmergencySeverity;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A user interface specialized in creating a new emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class CreateEmergencyUserInterface extends CommandUserInterface {

    @Override
    public String getCommandName() {
        return "create emergency";
    }

    @Override
    public void HandleUserInterface() {
        try {
            GPSCoordinate location = UserInterfaceParsers.ParseGPSCoordinate(this, "location of the emergency");
            EmergencySeverity severity = UserInterfaceParsers.ParseGPSSeverity(this, "severity level of the emergency");
        } catch (ParsingAbortedException ex) {
            this.writeOutput("command aborted.");
        }
    }

}
