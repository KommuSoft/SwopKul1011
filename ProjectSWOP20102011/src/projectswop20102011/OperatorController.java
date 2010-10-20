package projectswop20102011;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that handles all actions from the operator user.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class OperatorController {

    private static final Pattern OPERATOR_CREATION_REGEX = Pattern.compile("^create operator (.+)$");
    private static final OperatorList operators = new OperatorList();

    //This class is static, no instances may be constructed.
    private OperatorController() {
    }

    /**
     * Reads input from the text based user interface.
     * @param expression the expression that has been inserted by the operator in the user interface.
     */
    public static void readInput(String expression) throws InvalidNameException, InvalidCommandException {
        Matcher m = OPERATOR_CREATION_REGEX.matcher(expression);
        m.find();
        if (m.matches()) {
            //this expression is a create operator expression
            String operatorName = m.group(1);
            Operator o = new Operator(operatorName);
            operators.addOperator(o);
        }
        else {
            throw new InvalidCommandException(String.format("Operator actor doesn't know a command \"%s\"", expression));
        }
    }
}
