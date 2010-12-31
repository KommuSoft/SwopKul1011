package projectswop20102011.userinterface;

import java.util.Hashtable;

/**
 * An class that represents a user interface used by a specific user
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ActorUserInterface extends UserInterface {

    private final Hashtable<String, CommandUserInterface> commandUserInterfaces;
    private final String actorName;

    /**
     * Creates a new ActorUserInterface with given the name of the actor and a list of commands of that actor.
     * @param actorName The name of the actor.
     * @param commands A list of commands used by the actor.
     */
    public ActorUserInterface(String actorName, CommandUserInterface... commands) {
        this.actorName = actorName;
        this.commandUserInterfaces = new Hashtable<String, CommandUserInterface>();
        this.AddCommands(commands);
    }

    /**
     * Add the given commands to the ActorUserInterface.
     * @param commands The given commands to addd.
     */
    public void AddCommands(CommandUserInterface... commands) {
        for (CommandUserInterface command : commands) {
            this.commandUserInterfaces.put(command.getCommandName(), command);
        }
    }

    /**
     * Returns the name of the actor.
     * @return the name of the actor.
     */
    public String getActorName() {
        return this.actorName;
    }

    /**
     * Handles the actor user interface.
     */
    @Override
    public void handleUserInterface() {
        boolean nextRun;
        do {
            nextRun = false;
            this.writeOutput("type in a command?");
            String command = this.readInput();
            if (command.toLowerCase().equals("help")) {
                nextRun = true;
                this.writeOutput("The command options are:");
                for (String commandOption : this.commandUserInterfaces.keySet()) {
                    this.writeOutput(String.format("\t%s", commandOption));
                }
            } else if (this.commandUserInterfaces.containsKey(command.toLowerCase())) {
                UserInterface ui = this.commandUserInterfaces.get(command.toLowerCase());
                ui.setIndentation(this.getIndentation()+1);
                ui.handleUserInterface();
            }
        } while (nextRun);
    }
}
