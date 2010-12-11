package projectswop20102011.userinterface;

import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.controllers.Controller;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.ParsingAbortedException;
import projectswop20102011.userinterface.parsers.BooleanParser;
import projectswop20102011.userinterface.parsers.EmergencySeverityParser;
import projectswop20102011.userinterface.parsers.FireSizeParser;
import projectswop20102011.userinterface.parsers.GPSCoordinateParser;
import projectswop20102011.userinterface.parsers.LongParser;
import projectswop20102011.userinterface.parsers.StringParser;

/**
 * A user interface specialized in creating a new emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class CreateEmergencyUserInterface extends CommandUserInterface {

    private final CreateEmergencyController controller;

    /**
     * Creates a new CreateEmergencyUserInterface with a given controller to modify the world of the controller.
     * @param controller The controller that modifies the world.
     * @throws InvalidCommandNameException If the name of the command is invalid.
     * @throws InvalidControllerException If the given controller is invalid.
     */
    public CreateEmergencyUserInterface(CreateEmergencyController controller) throws InvalidCommandNameException, InvalidControllerException {
        super("create emergency");
        if (controller == null) {
            throw new InvalidControllerException("Controller must be effective.");
        }
        this.controller = controller;
    }

    /**
     * Handels the user interface to create an emergency.
     */
    @Override
    public void HandleUserInterface() {
        boolean retry;
        do {
            retry = false;
            try {
                GPSCoordinate location = this.parseInputToType(new GPSCoordinateParser(), "location of the emergency");
                EmergencySeverity severity = this.parseInputToType(new EmergencySeverityParser(), "severity level of the emergency");
                String description = this.parseInputToType(new StringParser(), "description of the emergency");
                String emergencyType = this.parseInputToType(new StringParser(), "type of the emergency");//, "fire", "robbery", "public disturbance", "traffic accident"
                if (emergencyType.equals("fire")) {
                    FireSize fireSize = this.parseInputToType(new FireSizeParser(), "size of the fire");
                    boolean chemical = this.parseInputToType(new BooleanParser(), "is the fire chemical");
                    long trappedPeople = this.parseInputToType(new LongParser(), "are there trapped people");
                    long numberOfInjured = this.parseInputToType(new LongParser(), "number of injured people");
                    try {
                        this.controller.createFireEmergency(location, severity, description, fireSize, chemical, trappedPeople, numberOfInjured);
                    } catch (Exception ex) {
                        this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                        this.writeOutput("write \"retry\" to retry to input the emergency, otherwise write anything else");
                        retry = this.readInput().toLowerCase().equals("retry");
                    }
                } else if (emergencyType.equals("robbery")) {
                    boolean armed = this.parseInputToType(new BooleanParser(), "is the robbery armed");
                    boolean inprogress = this.parseInputToType(new BooleanParser(), "is the robbery still in progress");
                    try {
                        this.controller.createRobberyEmergency(location, severity, description, armed, inprogress);
                    } catch (Exception ex) {
                        this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                        this.writeOutput("write \"retry\" to retry to input the emergency, otherwise write anything else");
                        retry = this.readInput().toLowerCase().equals("retry");
                    }
                } else if (emergencyType.equals("public disturbance")) {
                    long numberOfPeople = this.parseInputToType(new LongParser(), "number of people involved in the public disturbance");
                    try {
                        this.controller.createPublicDisturbanceEmergency(location, severity, description, numberOfPeople);
                    } catch (Exception ex) {
                        this.writeOutput(String.format("ERROR: %s", ex.getMessage()));
                        this.writeOutput("write \"retry\" to retry to input the emergency, otherwise write anything else");
                        retry = this.readInput().toLowerCase().equals("retry");
                    }
                } else if (emergencyType.equals("traffic accident")) {
                    long numberOfCars = this.parseInputToType(new LongParser(), "number of cars involved in the traffic accident");
                    long numberOfPeople = this.parseInputToType(new LongParser(), "number of people involved in the traffic accident");
                    try {
                        this.controller.createTrafficAccidentEmergency(location, severity, description, numberOfCars, numberOfPeople);
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
        return this.controller;
    }
}
