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
    private final UnitCommanderController unitCommanderController;
    private final DemonstratorController demonstratorController;

    public MainController(World world) {
        this.operatorController = new OperatorController(this, world.getEmergencyList());
        this.dispatcherController = new DispatcherController(this, world.getEmergencyList());
        this.unitCommanderController = new UnitCommanderController(this);
        this.demonstratorController = new DemonstratorController(this);
    }

    /**
     * Reads input from the text based user interface.
     * @param expression the expression that has been inserted in the user interface.
     * @throws InvalidExpressionFormatException If the expression has no propper format.
     * @throws InvalidActorException If the actor in the expression is unknown.
     */
    @Override
    public void readInput(String expression) throws InvalidExpressionFormatException, InvalidActorException, InvalidCommandException, Exception {
        String actor = readActor(expression);
        String message = readMessage(expression);
        if (actor.equals("operator")) {
            this.operatorController.readInput(message);
        } else if (actor.equals("dispatcher")) {
            this.dispatcherController.readInput(message);
        } else if (actor.equals("unitcommander")) {
            this.unitCommanderController.readInput(message);
        } else if (actor.equals("demonstrator")) {
            this.demonstratorController.readInput(message);
        } else {
            throw new InvalidActorException(String.format("Actor %s doesn't exists!", actor));
        }
    }

    /**
     * Writing a specified message to the specified actor (as part of the User Interface).
     * @param message the message to write out.
     * @param toActor the actor that will be the receiver of the message.
     */
    void writeOutput(String message, String toActor) {
        System.out.println(String.format("\t\t< %s %s", toActor.toUpperCase(), message));
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
