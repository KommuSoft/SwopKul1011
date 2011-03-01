package projectswop20102011.userinterface;

import java.util.Hashtable;

/**
 * The main textual user interface, that forms the root of all use cases.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class MainUserInterface extends UserInterface {

    private final Hashtable<String, ActorUserInterface> actorUserInterfaces;

    public MainUserInterface() {
        this.actorUserInterfaces = new Hashtable<String, ActorUserInterface>();
    }

    public void addActorUserInterfaces(ActorUserInterface... actorUserInterfaces) {
        for (ActorUserInterface aui : actorUserInterfaces) {
            this.actorUserInterfaces.put(aui.getActorName().toLowerCase(), aui);
        }
    }

    @Override
    public void handleUserInterface() {
		//TODO: Commentaar
        //writing project header
        writeOutput("Project SWOP v. 2.718281\n");
        String actor;
        while (true) {
            writeOutput("Type in the actor's name?");
            actor = readInput();
            if (actor.toLowerCase().equals("quit")) {
                break;
            }
            else if(actor.toLowerCase().equals("help")) {
                writeOutput("You can choose one of the following actors:");
                for(String s : this.actorUserInterfaces.keySet()) {
                    writeOutput(String.format("\t%s",s));
                }
            }
            else if(this.actorUserInterfaces.containsKey(actor.toLowerCase())) {
                UserInterface ui = this.actorUserInterfaces.get(actor.toLowerCase());
                ui.setIndentation(this.getIndentation()+1);
                ui.handleUserInterface();
            } else {
                writeOutput(String.format("I can't find actor \"%s\", please try again.",actor));
            }
        }
        writeOutput("\nEnd of the demonstration of project SWOP v. 2.718281");
    }
}
