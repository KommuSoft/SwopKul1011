package projectswop20102011.userinterface;

import projectswop20102011.Emergency;
import projectswop20102011.EmergencyStatus;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A command user interface to inspect the emergencies (use case #3)
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class InspectEmergenciesUserInterface extends CommandUserInterface {

    private final InspectEmergenciesController controller;

    public InspectEmergenciesUserInterface(InspectEmergenciesController controller) throws InvalidControllerException, InvalidCommandNameException {
        super("inspect emergencies");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    /**
     * Returns the InspectEmergenciesController used by this command user interface.
     * @return The controller used by this command user interface.
     */
    public InspectEmergenciesController getController() {
        return this.controller;
    }

    @Override
    public void HandleUserInterface() {
        boolean viewOtherList, viewEmergencyDetail;
        do {
            viewOtherList = false;
            try {
                EmergencyStatus status = UserInterfaceParsers.parseEmergencyStatus(this, "status of the emergency");
                Emergency[] emergencies = this.getController().inspectEmergenciesOnStatus(status);
                this.writeOutput(String.format("Founded emergencies (%s):", emergencies.length));
                for (Emergency em : emergencies) {
                    this.writeOutput(String.format("\t%s", em.toShortInformationString()));
                }
                do {
                    viewEmergencyDetail = true;
                    this.writeOutput("Type in an id, to view an details of an emergency,");
                    this.writeOutput("otherwise type \"other list\", to view another list");
                    this.writeOutput("finally type \"quit\", to end this command");
                    String input = this.readInput().toLowerCase();
                    if (input.equals("quit")) {
                        return;
                    } else if (input.equals("other list")) {
                        viewEmergencyDetail = false;
                        viewOtherList = true;
                    } else {
                        try {
                            long id = Long.parseLong(input);
                            Emergency emergency = this.getController().inspectEmergencyDetailOnStatusId(status, id);
                            if (emergency != null) {
                                this.writeOutput(String.format("\t%s", emergency.toLongInformationString()));
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
