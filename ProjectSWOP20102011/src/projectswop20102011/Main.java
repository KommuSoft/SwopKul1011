package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.EndOfEmergencyController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.userinterface.ActorUserInterface;
import projectswop20102011.userinterface.CommandUserInterface;
import projectswop20102011.userinterface.CreateEmergencyUserInterface;
import projectswop20102011.userinterface.DispatchUnitsUserInterface;
import projectswop20102011.userinterface.EndOfEmergencyUserInterface;
import projectswop20102011.userinterface.InspectEmergenciesUserInterface;
import projectswop20102011.userinterface.MainUserInterface;
import projectswop20102011.userinterface.SelectHospitalUserInterface;
import projectswop20102011.userinterface.TimeAheadUserInterface;

/**
 * @author Willem Van Onsem, Pieter-Jan Vuylsteke and Jonas Vanthornhout
 */
public class Main {
//TODO Use Case 2 (stap 2): controleren of er genoeg units zijn om de emergency af te handelen en tonen bij inspect emergencies
//TODO Use Case 2 (step 4): wanneer gedetailleerde info het aantal aanwezige units tonen.
//TODO Use Case 2 (step 5,6,7): wanneer dispatch units geselecteerd wordt crasht het programma :(
//TODO Use Case 4
	
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        World world = new World();
        //MainController mainController = new MainController(world);
        MainUserInterface mainUserInterface = new MainUserInterface();
        try {
            CommandUserInterface createEmergencyUserInterface = new CreateEmergencyUserInterface(new CreateEmergencyController(world));
            CommandUserInterface inspectEmergenciesUserInterface = new InspectEmergenciesUserInterface(new InspectEmergenciesController(world));
            CommandUserInterface dispatchUnitsUserInterface = new DispatchUnitsUserInterface(new DispatchUnitsController(world));
            CommandUserInterface selectHospitalUserInterface = new SelectHospitalUserInterface(new SelectHospitalController(world));
            CommandUserInterface endOfEmergencyUserInterface = new EndOfEmergencyUserInterface(new EndOfEmergencyController(world));
            CommandUserInterface timeAheadUserInterface = new TimeAheadUserInterface(new TimeAheadController(world));

            ActorUserInterface operatorUserInterface = new ActorUserInterface("Operator", createEmergencyUserInterface);
            ActorUserInterface dispatcherUserInterface = new ActorUserInterface("Dispatcher",dispatchUnitsUserInterface,inspectEmergenciesUserInterface);
            ActorUserInterface demonstratorUserInterface = new ActorUserInterface("Demonstrator", timeAheadUserInterface);
            ActorUserInterface unitCommanderUserInterface = new ActorUserInterface("Unit commander",selectHospitalUserInterface,endOfEmergencyUserInterface);
            
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
