package projectswop20102011;

/**
 * A class that handles all actions from the operator user.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class OperatorController {

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

}
