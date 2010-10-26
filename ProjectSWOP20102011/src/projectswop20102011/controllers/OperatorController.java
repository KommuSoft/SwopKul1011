package projectswop20102011.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.Emergency;
import projectswop20102011.EmergencyList;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.Fire;
import projectswop20102011.FireSize;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.exceptions.InvalidCommandException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidExpressionFormatException;
import projectswop20102011.exceptions.InvalidEmergencyTypeException;
import projectswop20102011.exceptions.InvalidFireSizeException;
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
    private static final Pattern ROBBERY_EXTRA_PARAMETERS_REGEX = Pattern.compile("^([a-z ]+),(true|false),(true|false),([0-9]+)$");
    private static final Pattern TRAFFIC_ACCIDENT_EXTRA_PARAMETERS_REGEX = Pattern.compile("^([a-z ]+),(true|false),(true|false),([0-9]+)$");
    private static final Pattern PUBLIC_DISTURBANCE_PARAMETERS_REGEX = Pattern.compile("^([a-z ]+),(true|false),(true|false),([0-9]+)$");

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
            //} else if (emergencyType.equals("robbery")) {
            //} else if (emergencyType.equals("traffic accident")) {
            //} else if (emergencyType.equals("public disturbance")) {
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
     * @throws InvalidExpressionFormatException When the format of the parameters is invalid.
     * @throws InvalidFireSizeException when there is no known fire size that is represented by it's textual argument.
     * @throws ParsingException When some arguments can not be parsed to primitive types.
     */
    private Emergency readCreateFireCommand(GPSCoordinate location, EmergencySeverity severity, String extraArguments) throws InvalidExpressionFormatException, InvalidFireSizeException, ParsingException {
        Matcher mFireParameters = FIRE_EXTRA_PARAMETERS_REGEX.matcher(extraArguments);
        if (extraArguments == null || !mFireParameters.find()) {
            throw new InvalidExpressionFormatException("fire requires extra parameters");
        }
        FireSize fireSize = FireSize.parse(mFireParameters.group(1));
        boolean chemical = ControllerUtilities.parseToBoolean(mFireParameters.group(2));
        boolean trappedPeople = ControllerUtilities.parseToBoolean(mFireParameters.group(3));
        long numberOfInjured = Long.parseLong(mFireParameters.group(4));
        return new Fire(emergencyList, location, severity, fireSize, chemical, trappedPeople, numberOfInjured);
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
