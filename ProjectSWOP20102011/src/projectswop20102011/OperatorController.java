package projectswop20102011;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that handles all actions from the operator user.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class OperatorController {

    private static final Pattern CREATE_OPERATOR_REGEX = Pattern.compile("^create operator (.+)$");
    private static final Pattern CREATE_CALLER_REGEX = Pattern.compile("^create caller ([A-Za-z ]+),([0-9]+)$");
    private static final Pattern CREATE_CALL_REGEX = Pattern.compile("^create call$");
    private static final Pattern CREATE_EMERGENCY_REGEX = Pattern.compile("^create emergency \\(([0-9]+),([0-9]+)\\),([A-Za-z]+)$");

    //This class is static, no instances may be constructed.
    private OperatorController() {
    }

    /**
     * Reads input from the text based user interface that has been posted by an operator.
     * @param expression the expression that has been inserted by the operator in the user interface.
     * @throws InvalidCommandException if a command is expressed that is unknown by the OperatorControl.
     */
    public static void readInput(String expression) throws InvalidCommandException {
            throw new InvalidCommandException(String.format("Operator actor doesn't know a command \"%s\"", expression));
    }
    /*private static void readCreateOperatorCommand (String expression) throws InvalidNameException {
        Matcher m = CREATE_OPERATOR_REGEX.matcher(expression);
        m.find();
        String operatorName = m.group(1);
        Operator o = new Operator(operatorName);
        OperatorList.addOperator(o);
    }
    private static void readCreateCallerCommand (String expression) throws InvalidNameException, InvalidPhoneNumberException {
        Matcher m = CREATE_CALLER_REGEX.matcher(expression);
        m.find();
        String name = m.group(1);
        String phoneNumber = m.group(2);
        //TODO: what to do with the created caller
        new Caller(name,phoneNumber);
    }
    private static void readCreateCallCommand (String expression) throws InvalidTimestampException {
        //TODO: what to do with the created call
        new Call(new Date());
    }
    private static void readCreateEmergencyCommand (String expression) throws InvalidExpressionFormatException, InvalidCommandException {
        Matcher m = CREATE_EMERGENCY_REGEX.matcher(expression);
        if(!m.find()) {
            throw new InvalidExpressionFormatException(String.format("can't parse parameters from %s.", expression));
        }
        long x = Long.parseLong(m.group(1));
        long y = Long.parseLong(m.group(2));
        String severity = m.group(3).toUpperCase();
        Severity s;
        try {
            s = Severity.valueOf(severity);
        }
        catch(IllegalArgumentException e) {
            throw new InvalidCommandException(String.format("cant find severity level \"%s\"",severity));
        }
        GPSCoordinate c = new GPSCoordinate(x,y);
        new Emergency(c,s);
    }*/

}
