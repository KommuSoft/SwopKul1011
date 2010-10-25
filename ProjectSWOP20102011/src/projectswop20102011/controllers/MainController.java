package projectswop20102011.controllers;

import projectswop20102011.exceptions.InvalidCommandException;
import projectswop20102011.exceptions.InvalidActorException;
import projectswop20102011.exceptions.InvalidExpressionFormatException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import projectswop20102011.World;

/**
 * A class that handles all text based user interface. And redirects these expressions
 * to the right actor controller.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class MainController implements Controller {

    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("^([A-Za-z]+) (.*)$");
    private final OperatorController operatorController;
    private final DispatcherController dispatcherController;

    public MainController(World world) {
        this.operatorController = new OperatorController(world.getEmergencyList());
        this.dispatcherController = new DispatcherController(world.getEmergencyList());
    }

    /**
     * Reads input from the text based user interface.
     * @param expression the expression that has been inserted in the user interface.
     * @throws InvalidExpressionFormatException If the expression has no propper format.
     * @throws InvalidActorException If the actor in the expression is unknown.
     */
    @Override
    public void readInput(String expression) throws InvalidExpressionFormatException, InvalidActorException, InvalidCommandException {
        String actor = readActor(expression);
        String message = readMessage(expression);
        if (actor.equals("operator")) {
            this.operatorController.readInput(message);
        }
        else if(actor.equals("dispatcher")) {
            this.dispatcherController.readInput(message);
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
    public String readActor(String expression) throws InvalidExpressionFormatException {
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
    public String readMessage(String expression) throws InvalidExpressionFormatException {
        try {
            Matcher m = EXPRESSION_PATTERN.matcher(expression);
            m.find();
            return m.group(2);
        } catch (Exception e) {
            throw new InvalidExpressionFormatException("Expression has a wrong format!");
        }
    }
}