package projectswop20102011;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that handles all text based user interface. And redirects these expressions
 * to the right actor controller. Redirection is done on a reflection based way.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class MainController {

    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("^([A-Za-z]+) (.*)$");

    //this class is static no instances can be constructed
    private MainController() {
    }

    /**
     * Reads input from the text based user interface.
     * @param expression the expression that has been inserted in the user interface.
     * @throws InvalidExpressionFormatException If the expression has no propper format.
     * @throws InvalidActorException If the actor in the expression is unknown.
     */
    public static void readInput(String expression) throws InvalidExpressionFormatException, InvalidActorException, InvalidNameException, InvalidCommandException {
        String actor = readActor(expression);
        String message = readMessage(expression);
        if (actor.equals("operator")) {
            OperatorController.readInput(message);
        } else {
            throw new InvalidActorException(String.format("Actor %s doesn't exists!", actor));
        }
    }

    /**
     * Extracts the name of the actor out of the expression.
     * @param expression The expression readed from the user interface.
     * @return The name of the actor of the expression.
     * @throws InvalidExpressionFormatException If the expression has no propper format.
     */
    public static String readActor(String expression) throws InvalidExpressionFormatException {
        try {
            Matcher m = EXPRESSION_PATTERN.matcher(expression);
            m.find();
            return m.group(1).toLowerCase();
        } catch (Exception e) {
            throw new InvalidExpressionFormatException("Expression has a wrong format!");
        }
    }

    /**
     * Extracts the message out of the expression.
     * @param expression The expression readed from the user interface.
     * @return The message of the expression.
     * @throws InvalidExpressionFormatException If the expression has no propper format.
     */
    public static String readMessage(String expression) throws InvalidExpressionFormatException {
        try {
            Matcher m = EXPRESSION_PATTERN.matcher(expression);
            m.find();
            return m.group(2);
        } catch (Exception e) {
            throw new InvalidExpressionFormatException("Expression has a wrong format!");
        }
    }
}
