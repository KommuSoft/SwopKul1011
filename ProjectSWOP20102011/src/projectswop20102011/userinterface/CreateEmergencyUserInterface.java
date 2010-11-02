package projectswop20102011.userinterface;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.FireSize;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.controllers.Controller;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.exceptions.ParsingAbortedException;

/**
 * A user interface specialized in creating a new emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class CreateEmergencyUserInterface extends CommandUserInterface {

    private final CreateEmergencyController controller;

    public CreateEmergencyUserInterface(CreateEmergencyController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("create emergency");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    @Override
    public void HandleUserInterface() {
        boolean retry;
        do {
            retry = false;
            try {
                GPSCoordinate location = UserInterfaceParsers.parseGPSCoordinate(this, "location of the emergency");
                EmergencySeverity severity = UserInterfaceParsers.parseSeverity(this, "severity level of the emergency");
                String emergencyType = UserInterfaceParsers.parseOptionsString(this, "type of the emergency", "fire", "robbery", "public disturbance", "traffic accident");
                if (emergencyType.equals("fire")) {
                    FireSize fireSize = UserInterfaceParsers.parseFireSize(this, "size of the fire");
                    boolean chemical = UserInterfaceParsers.parseBoolean(this, "is the fire chemical");
                    boolean trappedPeople = UserInterfaceParsers.parseBoolean(this, "are there trapped people");
                    long numberOfInjured = UserInterfaceParsers.parseLong(this, "number of injured people");
                    try {
                        this.controller.createFireEmergency(location, severity, fireSize, chemical, trappedPeople, numberOfInjured);
                    } catch (Exception ex) {
                        this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                        this.writeOutput("write \"retry\" to retry to input the emergency, otherwise write anything else");
                        retry = this.readInput().toLowerCase().equals("retry");
                    }
                } else if (emergencyType.equals("robbery")) {
                    boolean armed = UserInterfaceParsers.parseBoolean(this, "is the robbery armed");
                    boolean inprogress = UserInterfaceParsers.parseBoolean(this, "is the robbery still in progress");
                    try {
                        this.controller.createRobberyEmergency(location, severity, armed, inprogress);
                    } catch (Exception ex) {
                        this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                        this.writeOutput("write \"retry\" to retry to input the emergency, otherwise write anything else");
                        retry = this.readInput().toLowerCase().equals("retry");
                    }
                } else if (emergencyType.equals("public disturbance")) {
                    long numberOfPeople = UserInterfaceParsers.parseLong(this, "number of people involved in the public disturbance");
                    try {
                        this.controller.createPublicDisturbanceEmergency(location, severity, numberOfPeople);
                    } catch (Exception ex) {
                        this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                        this.writeOutput("write \"retry\" to retry to input the emergency, otherwise write anything else");
                        retry = this.readInput().toLowerCase().equals("retry");
                    }
                } else if (emergencyType.equals("traffic accident")) {
                    long numberOfCars = UserInterfaceParsers.parseLong(this, "number of cars involved in the traffic accident");
                    long numberOfPeople = UserInterfaceParsers.parseLong(this, "number of people involved in the traffic accident");
                    try {
                        this.controller.createTrafficAccidentEmergency(location, severity, numberOfCars, numberOfPeople);
                    } catch (Exception ex) {
                        this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                        this.writeOutput("write \"retry\" to retry to input the emergency, otherwise write anything else");
                        retry = this.readInput().toLowerCase().equals("retry");
                    }
                } else {
                    this.writeOutput("ERROR: unknown emergency type.");
                    this.writeOutput("write \"retry\" to retry to input the emergency, otherwise write anything else");
                        retry = this.readInput().toLowerCase().equals("retry");
                }

            } catch (ParsingAbortedException ex) {
                this.writeOutput("command aborted.");
            }
        } while (retry);
    }

    @Override
    public Controller getController() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
