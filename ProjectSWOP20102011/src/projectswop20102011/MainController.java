package projectswop20102011;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that handles all text based user interface. And redirects these expressions
 * to the right actor controller. Redirection is done on a reflection based way.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class MainController {

    private final static Pattern EXPRESSION_PATTERN = Pattern.compile("^([A-Za-z]+) ([a-zA-Z ]*)$");

	//this class is static no instances can be constructed
    private MainController () {}

    /**
     * Reads input from the text based user interface.
     * @param expression the expression that has been inserted in the user interface.
     * @pre the actor controllers must be loaded. | areActorControllersLoaded()
     */
    public static void readInput (String expression) {
        Matcher m = EXPRESSION_PATTERN.matcher(expression);
		String actor = m.group(0);
		String message = m.group(1);
		if(actor.equals("Operator")) {
			OperatorController.readInput(expression);
		}
    }

	public static String readActor (String expression) {
		System.out.print(EXPRESSION_PATTERN.matcher(expression).matches());
		Matcher m = EXPRESSION_PATTERN.matcher(expression);
		return m.group(0);
	}

}
