package projectswop20102011.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.controllers.RemoveUnitAssignmentController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidCommandNameException;
import projectswop20102011.exceptions.InvalidControllerException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.userinterface.ActorUserInterface;
import projectswop20102011.userinterface.CommandUserInterface;
import projectswop20102011.userinterface.CreateEmergencyUserInterface;
import projectswop20102011.userinterface.DispatchUnitsUserInterface;
import projectswop20102011.userinterface.EndOfTaskUserInterface;
import projectswop20102011.userinterface.EnvironmentReader;
import projectswop20102011.userinterface.InspectEmergenciesUserInterface;
import projectswop20102011.userinterface.MainUserInterface;
import projectswop20102011.userinterface.RemoveUnitAssignmentInterface;
import projectswop20102011.userinterface.SelectHospitalUserInterface;
import projectswop20102011.userinterface.TimeAheadUserInterface;

/**
 * @author Willem Van Onsem, Pieter-Jan Vuylsteke and Jonas Vanthornhout
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        World world = new World();

        try {
            EnvironmentReader er = new EnvironmentReader(new ReadEnvironmentDataController(world));
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                FileInputStream fis = new FileInputStream(args[0]);
                er.readEnvironmentData(fis);
                fis.close();
            } catch (Exception ex) {
                System.out.println(String.format("ERROR: %s", ex.getMessage()));
                System.out.println("program will now stop.");
                return;
            }

            //MainController mainController = new MainController(world);
            MainUserInterface mainUserInterface = new MainUserInterface();


            CommandUserInterface createEmergencyUserInterface = new CreateEmergencyUserInterface(new CreateEmergencyController(world));
            CommandUserInterface inspectEmergenciesUserInterface = new InspectEmergenciesUserInterface(new InspectEmergenciesController(world));
            CommandUserInterface dispatchUnitsUserInterface = new DispatchUnitsUserInterface(new DispatchUnitsController(world));
            CommandUserInterface selectHospitalUserInterface = new SelectHospitalUserInterface(new SelectHospitalController(world));
            CommandUserInterface endOfEmergencyUserInterface = new EndOfTaskUserInterface(new EndOfTaskController(world));
            CommandUserInterface timeAheadUserInterface = new TimeAheadUserInterface(new TimeAheadController(world));
            CommandUserInterface removeUnitAssignmentInterface = new RemoveUnitAssignmentInterface(new RemoveUnitAssignmentController(world));

            ActorUserInterface operatorUserInterface = new ActorUserInterface("Operator", createEmergencyUserInterface);
            ActorUserInterface dispatcherUserInterface = new ActorUserInterface("Dispatcher", dispatchUnitsUserInterface, inspectEmergenciesUserInterface, removeUnitAssignmentInterface);
            ActorUserInterface demonstratorUserInterface = new ActorUserInterface("Demonstrator", timeAheadUserInterface);
            ActorUserInterface unitCommanderUserInterface = new ActorUserInterface("Unit commander", selectHospitalUserInterface, endOfEmergencyUserInterface);

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
