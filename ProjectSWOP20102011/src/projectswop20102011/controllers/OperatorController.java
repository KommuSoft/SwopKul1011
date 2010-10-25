package projectswop20102011.controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.EmergencyList;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.exceptions.InvalidCommandException;
import projectswop20102011.exceptions.InvalidCoordinateException;
import projectswop20102011.exceptions.InvalidExpressionFormatException;

/**
 * A class that handles all actions from the operator user.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
class OperatorController implements Controller {

    private final EmergencyList emergencyList;
    private static final Pattern CREATE_EMERGENCY_REGEX = Pattern.compile("^create emergency ([a-z]+) (\\([0-9]+,[0-9]+\\)) ([a-z]+) (.+)$");
    private static final Pattern EMERGENCY_TYPE_REGEX = Pattern.compile("^(fire|public disturbance|robbery|traffic accident)$");

    /**
     * Creates a new instance of the OperatorController class.
     */
    public OperatorController(EmergencyList emergencyList) {
        this.emergencyList = emergencyList;
    }

    /**
     * Executes a new "create emergency" command
     * @param expression
     */
    private void readCreateEmergencyCommand(String expression) throws InvalidExpressionFormatException, InvalidCoordinateException {
        Matcher mCreateEmergency = CREATE_EMERGENCY_REGEX.matcher(expression);
        if(!mCreateEmergency.find()) {
            throw new InvalidExpressionFormatException(String.format("Invalid create emergency expression: \"%s\".",expression));
        }
        String emergencyType = mCreateEmergency.group(1);
        long x = Long.parseLong(mCreateEmergency.group(2));
        long y = Long.parseLong(mCreateEmergency.group(3));
        String severity = mCreateEmergency.group(4);
        GPSCoordinate gpsCoordinate = new GPSCoordinate(x,y);

    }

    /**
     * Reads input from the text based user interface that has been posted by an operator.
     * @param expression the expression that has been inserted by the operator in the user interface.
     * @throws InvalidCommandException if a command is expressed that is unknown by the OperatorControl.
     */
    @Override
    public void readInput(String expression) throws InvalidCommandException, InvalidExpressionFormatException, InvalidCoordinateException {
        if(expression.startsWith("create emergency")) {
            this.readCreateEmergencyCommand(expression);
        }
        else {
            throw new InvalidCommandException(String.format("Operator controller doesn't know a command \"%s\"", expression));
        }
    }
}