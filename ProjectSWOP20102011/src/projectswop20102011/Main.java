package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.userinterface.ActorUserInterface;
import projectswop20102011.userinterface.CommandUserInterface;
import projectswop20102011.userinterface.CreateEmergencyUserInterface;
import projectswop20102011.userinterface.MainUserInterface;
import projectswop20102011.userinterface.TimeAheadUserInterface;

/**
 * @author Willem Van Onsem, Pieter-Jan Vuylsteke and Jonas Vanthornhout
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World world = new World();
        //MainController mainController = new MainController(world);
        MainUserInterface mainUserInterface = new MainUserInterface();
        try {
            CommandUserInterface createEmergencyUserInterface = new CreateEmergencyUserInterface(new CreateEmergencyController(world));
            CommandUserInterface timeAheadUserInterface = new TimeAheadUserInterface(new TimeAheadController(world));
            ActorUserInterface operatorUserInterface = new ActorUserInterface("Operator", createEmergencyUserInterface);
            ActorUserInterface dispatcherUserInterface = new ActorUserInterface("Dispatcher");
            ActorUserInterface demonstratorUserInterface = new ActorUserInterface("Demonstrator", timeAheadUserInterface);
            ActorUserInterface unitCommanderUserInterface = new ActorUserInterface("Unit commander");
            mainUserInterface.addActorUserInterfaces(operatorUserInterface, dispatcherUserInterface, demonstratorUserInterface, unitCommanderUserInterface);
            mainUserInterface.HandleUserInterface();
        } catch (InvalidCommandNameException ex) {
            //can't be thrown, ensured by the our implementation
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidControllerException ex) {
            //can't be thrown, ensured by the our implementation
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidWorldException ex) {
            //can't be thrown, ensured by the our implementation
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
