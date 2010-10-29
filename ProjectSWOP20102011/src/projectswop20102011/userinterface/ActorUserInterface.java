package projectswop20102011.userinterface;

import java.util.Hashtable;

/**
 * An class that represents a user interface used by a specific user
 * @author willem
 */
public class ActorUserInterface extends UserInterface {

    private final Hashtable<String, CommandUserInterface> commandUserInterfaces;
    private final String actorName;

    public ActorUserInterface(String actorName, CommandUserInterface... commands) {
        this.actorName = actorName;
        this.commandUserInterfaces = new Hashtable<String, CommandUserInterface>();
        this.AddCommands(commands);
    }

    public void AddCommands(CommandUserInterface... commands) {
        for (CommandUserInterface command : commands) {
            this.commandUserInterfaces.put(command.getCommandName(), command);
        }
    }

    public String getActorName() {
        return this.actorName;
    }

    @Override
    public void HandleUserInterface() {
        
    }

}
