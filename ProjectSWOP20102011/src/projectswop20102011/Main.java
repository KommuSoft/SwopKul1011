package projectswop20102011;

import projectswop20102011.controllers.MainController;
import projectswop20102011.userinterface.ActorUserInterface;
import projectswop20102011.userinterface.CommandUserInterface;
import projectswop20102011.userinterface.CreateEmergencyUserInterface;
import projectswop20102011.userinterface.MainUserInterface;

/**
 * @author Willem Van Onsem, Pieter-Jan Vuylsteke and Jonas Vanthornhout
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World world = new World();
        MainController mainController = new MainController(world);
        MainUserInterface mainUserInterface = new MainUserInterface();
        CommandUserInterface createEmergencyUserInterface = new CreateEmergencyUserInterface();
        ActorUserInterface operatorUserInterface = new ActorUserInterface("Operator",createEmergencyUserInterface);
        ActorUserInterface dispatcherUserInterface = new ActorUserInterface("Dispatcher");
        ActorUserInterface demonstratorUserInterface = new ActorUserInterface("Demonstrator");
        ActorUserInterface unitCommanderUserInterface = new ActorUserInterface("Unit commander");
        mainUserInterface.addActorUserInterfaces(operatorUserInterface,dispatcherUserInterface,demonstratorUserInterface,unitCommanderUserInterface);
        mainUserInterface.HandleUserInterface();
    }
}