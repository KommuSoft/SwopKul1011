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
        System.out.println("Project SWOP v. 1.618034\n");
        String actor = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(isr);
        while (true) {
            System.out.println("Who is the actor?");
            System.out.print("> ");
            try {
                actor = in.readLine();
            } catch (IOException ex) {
                Logger.getLogger(MainUserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (actor.toLowerCase().equals("quit")) {
                System.out.println("\nEnd of the demonstration of project SWOP v. 1.618034");
                break;
            }
            if (this.actorUserInterfaces.containsKey(actor.toLowerCase())) {
                this.actorUserInterfaces.get(actor.toLowerCase()).HandleUserInterface();
            } else {
                System.out.println(String.format("I can't find actor \"%s\", please try again.",actor));
            }
        }
    }
}
