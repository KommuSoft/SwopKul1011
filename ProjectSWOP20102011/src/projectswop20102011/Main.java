package projectswop20102011;

import projectswop20102011.controllers.MainController;
import projectswop20102011.userinterface.MainUserInterface;
import projectswop20102011.userinterface.OperatorUserInterface;

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
        mainUserInterface.addActorUserInterfaces(new OperatorUserInterface());
        mainUserInterface.HandleUserInterface();
    }
}