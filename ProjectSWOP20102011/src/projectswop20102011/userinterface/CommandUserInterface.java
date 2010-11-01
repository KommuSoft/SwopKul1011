package projectswop20102011.userinterface;

import projectswop20102011.controllers.Controller;
import projectswop20102011.exceptions.InvalidCommandNameException;

/**
 * An abstract class where all User Interfaces that handle a specific command are a subclass off.
 * @author Willem Van Onsem, Jonas Vanthornhout
 */
public abstract class CommandUserInterface extends UserInterface {

    private final String commandName;

    protected CommandUserInterface (String commandName) throws InvalidCommandNameException {
        if(!isValidCommandName(commandName)) {
            throw new InvalidCommandNameException(String.format("Command name must be effective and not empty and not \"%s\"" ,commandName));
        }
        this.commandName = commandName;
    }

    /**
     * Gets the name of the command.
     * @return The name of the command.
     */
    public final String getCommandName () {
        return this.commandName;
    }

    /**
     * Gets the controller used by the command user interface.
     * @return The controller used by the command user interface.
     */
    public abstract Controller getController ();

    /**
     * Tests if the given command name is a valid name for a command user interface.
     * @param commandName The given command name to test.
     * @return True if the given command name is effective and not an empty String.
     */
    public static boolean isValidCommandName(String commandName) {
        return (commandName != null && commandName.length() > 0);
    }

}
