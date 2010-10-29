package projectswop20102011.userinterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void HandleUserInterface() {
        //writing project header
        writeOutput("Project SWOP v. 1.618034\n");
        String actor;
        while (true) {
            writeOutput("Who is the actor?");
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
                this.actorUserInterfaces.get(actor.toLowerCase()).HandleUserInterface();
            } else {
                writeOutput(String.format("I can't find actor \"%s\", please try again.",actor));
            }
        }
        writeOutput("\nEnd of the demonstration of project SWOP v. 1.618034");
    }
}
