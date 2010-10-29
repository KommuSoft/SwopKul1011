package projectswop20102011.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.Emergency;
import projectswop20102011.EmergencyList;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.Fire;
import projectswop20102011.FireSize;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.PublicDisturbance;
import projectswop20102011.Robbery;
import projectswop20102011.TrafficAccident;
import projectswop20102011.exceptions.InvalidCommandException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidExpressionFormatException;
import projectswop20102011.exceptions.InvalidEmergencyTypeException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.exceptions.ParsingException;

/**
 * A class that handles all actions from the operator user.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
class OperatorController implements Controller {

    private final MainController mainController;
    private final EmergencyList emergencyList;
    private static final Pattern CREATE_EMERGENCY_REGEX = Pattern.compile("^create emergency ([A-Za-z ]+),\\(([0-9]+),([0-9]+)\\),([A-Za-z]+)(,(.+))?$");
    private static final Pattern FIRE_EXTRA_PARAMETERS_REGEX = Pattern.compile("^([A-Za-z ]+),([A-Za-z]+),([A-Za-z]+),([0-9]+)$");
    private static final Pattern ROBBERY_EXTRA_PARAMETERS_REGEX = Pattern.compile("^([A-Za-z]+),([A-Za-z]+)$");
    private static final Pattern TRAFFIC_ACCIDENT_EXTRA_PARAMETERS_REGEX = Pattern.compile("^([0-9]+),([0-9]+)$");
    private static final Pattern PUBLIC_DISTURBANCE_EXTRA_PARAMETERS_REGEX = Pattern.compile("^([0-9]+)$");

    /**
     * Creates a new instance of the OperatorController class.
     */
    public OperatorController(MainController mainController, EmergencyList emergencyList) {
        this.mainController = mainController;
        this.emergencyList = emergencyList;
    }

    /**
     * Executes a new "create emergency" command
     * @param expression
     */
    private void readCreateEmergencyCommand(String expression) throws InvalidExpressionFormatException, InvalidEmergencySeverityException, Exception {
        Matcher mCreateEmergency = CREATE_EMERGENCY_REGEX.matcher(expression);
        if (!mCreateEmergency.find()) {
            throw new InvalidExpressionFormatException(String.format("Invalid create emergency expression: \"%s\".", expression));
        }
        String emergencyType = mCreateEmergency.group(1);
        long x = Long.parseLong(mCreateEmergency.group(2));
        long y = Long.parseLong(mCreateEmergency.group(3));
        GPSCoordinate gpsCoordinate = new GPSCoordinate(x, y);
        EmergencySeverity severity = EmergencySeverity.parse(mCreateEmergency.group(4));
        String extraArguments = mCreateEmergency.group(6);
        Emergency createdEmergency;
        if (emergencyType.equals("fire")) {
            createdEmergency = readCreateFireCommand(gpsCoordinate, severity, extraArguments);
        } else if (emergencyType.equals("robbery")) {
            createdEmergency = readCreateRobberyCommand(gpsCoordinate, severity, extraArguments);
        } else if (emergencyType.equals("traffic accident")) {
            createdEmergency = readCreateTrafficAccidentCommand(gpsCoordinate, severity, extraArguments);
        } else if (emergencyType.equals("public disturbance")) {
            createdEmergency = readCreatePublicDisturbanceCommand(gpsCoordinate, severity, extraArguments);
        } else {
            throw new InvalidEmergencyTypeException(String.format("There is no emergency type called %s.", emergencyType));
        }
        this.writeOutput(String.format("new emergency has been created: %s", createdEmergency));
    }

    /**
     * Executes a new "create emergency fire" command
     * @param location the location where the emergency happens.
     * @param severity the severity level of the emergency.
     * @param extraArguments A string of additional arguments in the expression.
     * @return An emergency that represents the new created fire emergency.
     * @throws InvalidExpressionFormatException When the format of the extraArguments is invalid.
     * @throws InvalidFireSizeException when there is no known fire size that is represented by it's textual argument.
     * @throws ParsingException When some arguments can not be parsed to primitive types.
     */
    private Emergency readCreateFireCommand(GPSCoordinate location, EmergencySeverity severity, String extraArguments) throws InvalidExpressionFormatException, InvalidFireSizeException, ParsingException, InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        Matcher mFireParameters = FIRE_EXTRA_PARAMETERS_REGEX.matcher(extraArguments);
        if (extraArguments == null || !mFireParameters.find()) {
            throw new InvalidExpressionFormatException("Emergency type \"fire\" requires different parameters.");
        }
        FireSize fireSize = FireSize.parse(mFireParameters.group(1));
        boolean chemical = ControllerUtilities.parseToBoolean(mFireParameters.group(2));
        boolean trappedPeople = ControllerUtilities.parseToBoolean(mFireParameters.group(3));
        long numberOfInjured = Long.parseLong(mFireParameters.group(4));
        return new Fire(location, severity, fireSize, chemical, trappedPeople, numberOfInjured);
    }

    /**
     * Executes a new "create emergency robbery" command
     * @param location the location where the emergency happens.
     * @param severity the severity level of the emergency.
     * @param extraArguments A string of additional arguments in the expression.
     * @return An emergency that represents the new created robbery emergency.
     * @throws InvalidExpressionFormatException When the format of the extraArguments is invald.
     * @throws ParsingException when some arguments can not be parsed in primitive types.
     */
    private Emergency readCreateRobberyCommand(GPSCoordinate location, EmergencySeverity severity, String extraArguments) throws InvalidExpressionFormatException, ParsingException, InvalidLocationException, InvalidEmergencySeverityException {
        Matcher mRobberyParameters = ROBBERY_EXTRA_PARAMETERS_REGEX.matcher(extraArguments);
        if (extraArguments == null || !mRobberyParameters.find()) {
            throw new InvalidExpressionFormatException("Emergency type \"robbery\" requires different parameters.");
        }
        boolean armed = ControllerUtilities.parseToBoolean(mRobberyParameters.group(1));
        boolean inProgress = ControllerUtilities.parseToBoolean(mRobberyParameters.group(2));
        return new Robbery(location, severity, armed, inProgress);
    }

    /**
     * Executes a new "create emergency traffic accident" command
     * @param location the location where the emergency happens.
     * @param severity the severity level of the emergency.
     * @param extraArguments A string of additional arguments in the expression.
     * @return An emergency that represents the new created traffic accident emergency.
     * @throws InvalidExpressionFormatException When the format of the extraArguments is invald.
     */
    private Emergency readCreateTrafficAccidentCommand(GPSCoordinate location, EmergencySeverity severity, String extraArguments) throws InvalidExpressionFormatException, ParsingException, InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        Matcher mTrafficAccidentParameters = TRAFFIC_ACCIDENT_EXTRA_PARAMETERS_REGEX.matcher(extraArguments);
        if (extraArguments == null || !mTrafficAccidentParameters.find()) {
            throw new InvalidExpressionFormatException("Emergency type \"traffic accident\" requires different parameters.");
        }
        long numberOfCars = Long.parseLong(mTrafficAccidentParameters.group(1));
        long numberOfInjured = Long.parseLong(mTrafficAccidentParameters.group(2));
        return new TrafficAccident(location, severity, numberOfCars, numberOfInjured);
    }

    /**
     * Executes a new "create emergency public disturbance" command
     * @param location the location where the emergency happens.
     * @param severity the severity level of the emergency.
     * @param extraArguments A string of additional arguments in the expression.
     * @return An emergency that represents the new created public disturbance emergency.
     * @throws InvalidExpressionFormatException When the format of the extraArguments is invald.
     */
    private Emergency readCreatePublicDisturbanceCommand(GPSCoordinate location, EmergencySeverity severity, String extraArguments) throws InvalidExpressionFormatException, ParsingException, InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        Matcher mPublicDisturbanceParameters = PUBLIC_DISTURBANCE_EXTRA_PARAMETERS_REGEX.matcher(extraArguments);
        if (extraArguments == null || !mPublicDisturbanceParameters.find()) {
            throw new InvalidExpressionFormatException("Emergency type \"public disturbance\" requires different parameters.");
        }
        long numberOfPeople = Long.parseLong(mPublicDisturbanceParameters.group(1));
        return new PublicDisturbance(location, severity, numberOfPeople);
    }

    /**
     * Reads input from the text based user interface that has been posted by an operator.
     * @param expression the expression that has been inserted by the operator in the user interface.
     * @throws InvalidCommandException if a command is expressed that is unknown by the OperatorControl.
     */
    @Override
    public void readInput(String expression) throws InvalidCommandException, InvalidExpressionFormatException, Exception {
        if (expression.startsWith("create emergency")) {
            this.readCreateEmergencyCommand(expression);
        } else {
            throw new InvalidCommandException(String.format("Operator controller doesn't know a command \"%s\"", expression));
        }
    }

    /**
     * A method writing output to the OPERATOR actor.
     * @param message The message that needs to be written to the OPERATOR actor.
     */
    private void writeOutput(String message) {
        this.mainController.writeOutput(message, "operator");
    }
}
