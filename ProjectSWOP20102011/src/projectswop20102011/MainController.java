package projectswop20102011;

/**
 * A class that handles all text based user interface. And redirects these expressions
 * to the right actor controller. Redirection is done on a reflection based way.
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class MainController {

    private static boolean actorControllersLoaded = false;

    //this class is static no instances can be constructed
    private MainController () {}

    /**
     * Tests if the actor controllers are already loaded
     * @return true if the actor controllers are loaded, otherwise false.
     */
    public static boolean areActorControllersLoaded () {
        return actorControllersLoaded;
    }
    /**
     * A static method that browses the program in search for actor controllers, and
     * add them to the list.
     * This method should be called before the ReadInput method can be called.
     */
    public static void loadActorControllers () {

    }

    /**
     * Reads input from the text based user interface.
     * @param expression the expression that has been inserted in the user interface.
     * @pre the actor controllers must be loaded. | areActorControllersLoaded()
     */
    public static void readInput (String expression) {
        
    }

}
