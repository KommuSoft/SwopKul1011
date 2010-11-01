package projectswop20102011;

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
        CommandUserInterface createEmergencyUserInterface = new CreateEmergencyUserInterface();
        CommandUserInterface timeAheadUserInterface = new TimeAheadUserInterface();
        ActorUserInterface operatorUserInterface = new ActorUserInterface("Operator",createEmergencyUserInterface);
        ActorUserInterface dispatcherUserInterface = new ActorUserInterface("Dispatcher");
        ActorUserInterface demonstratorUserInterface = new ActorUserInterface("Demonstrator",timeAheadUserInterface);
        ActorUserInterface unitCommanderUserInterface = new ActorUserInterface("Unit commander");
        mainUserInterface.addActorUserInterfaces(operatorUserInterface,dispatcherUserInterface,demonstratorUserInterface,unitCommanderUserInterface);
        mainUserInterface.HandleUserInterface();
    }
}