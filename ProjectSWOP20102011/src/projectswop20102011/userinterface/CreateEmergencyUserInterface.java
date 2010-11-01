package projectswop20102011.userinterface;

import projectswop20102011.EmergencySeverity;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.controllers.Controller;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A user interface specialized in creating a new emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class CreateEmergencyUserInterface extends CommandUserInterface {


    private final CreateEmergencyController controller;

    public CreateEmergencyUserInterface (CreateEmergencyController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("create emergency");
        if(controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public void HandleUserInterface() {
        try {
            GPSCoordinate location = UserInterfaceParsers.parseGPSCoordinate(this, "location of the emergency");
            EmergencySeverity severity = UserInterfaceParsers.parseGPSSeverity(this, "severity level of the emergency");
            String emergencyType = UserInterfaceParsers.parseOptionsString(this, "type of the emergency", "fire","robbery","public disturbance","traffic accident");
            
        } catch (ParsingAbortedException ex) {
            this.writeOutput("command aborted.");
        }
    }

    @Override
    public Controller getController() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
