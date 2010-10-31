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
                ui.HandleUserInterface();
            }
        } while (nextRun);
    }
}
