package projectswop20102011.userinterface;

import projectswop20102011.EmergencyStatus;
import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A command user interface to inspect the emergencies (use case #3)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectEmergenciesUserInterface extends CommandUserInterface {

    @Override
    public String getCommandName() {
        return "inspect emergencies";
    }

    @Override
    public void HandleUserInterface() {
        try {
            EmergencyStatus status = UserInterfaceParsers.parseEmergencyStatus(this, "status of the emergency");
        } catch (ParsingAbortedException ex) {
            this.writeOutput("command aborted.");
        }
    }

}
