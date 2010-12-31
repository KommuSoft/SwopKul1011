package projectswop20102011.externalsystem.main;

import be.kuleuven.cs.swop.external.ExternalSystem;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.controllers.RemoveUnitAssignmentController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.lists.EmergencyFactoryList;
import projectswop20102011.domain.lists.World;
import projectswop20102011.externalsystem.EmergencyDispatchApi;
import projectswop20102011.factories.FireFactory;
import projectswop20102011.factories.PublicDisturbanceFactory;
import projectswop20102011.factories.RobberyFactory;
import projectswop20102011.factories.TrafficAccidentFactory;
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

public class Main {

    public static void main(String[] args) throws Exception {
        World world = initWorld();
        IExternalSystem es = initExternalSystem(world);
        if (readEnvironment(world, args)) {
            initUserInterface(world,es).handleUserInterface();
        }
    }

    private static World initWorld() throws Exception {
        World world = new World();
        EmergencyFactoryList efl = world.getEmergencyFactoryList();
        efl.addEmergencyFactory(new FireFactory());
        efl.addEmergencyFactory(new TrafficAccidentFactory());
        efl.addEmergencyFactory(new RobberyFactory());
        efl.addEmergencyFactory(new PublicDisturbanceFactory());
        return world;
    }

    private static IExternalSystem initExternalSystem(World world) {
        EmergencyDispatchApi api = new EmergencyDispatchApi(world);
        return ExternalSystem.bootstrap(api);
    }

    private static boolean readEnvironment(World world, String[] args) throws Exception {
        EnvironmentReader er = new EnvironmentReader(new ReadEnvironmentDataController(world));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            er.readEnvironmentData(fis);
            fis.close();
        } catch (Exception ex) {
            System.out.println(String.format("ERROR: %s", ex.getMessage()));
            System.out.println("program will now stop.");
            return false;
        }
        return true;
    }

    private static MainUserInterface initUserInterface(World world, IExternalSystem es) throws Exception {
        MainUserInterface mainUserInterface = new MainUserInterface();
        CommandUserInterface createEmergencyUserInterface = new CreateEmergencyUserInterface(new CreateEmergencyController(world));
        CommandUserInterface inspectEmergenciesUserInterface = new InspectEmergenciesUserInterface(new InspectEmergenciesController(world));
        CommandUserInterface dispatchUnitsUserInterface = new DispatchUnitsUserInterface(new DispatchUnitsController(world));
        CommandUserInterface selectHospitalUserInterface = new SelectHospitalUserInterface(new SelectHospitalController(world));
        CommandUserInterface endOfEmergencyUserInterface = new EndOfTaskUserInterface(new EndOfTaskController(world));
        CommandUserInterface timeAheadUserInterface = new TimeAheadUserInterface(new TimeAheadController(world, es));
        CommandUserInterface removeUnitAssignmentInterface = new RemoveUnitAssignmentInterface(new RemoveUnitAssignmentController(world));
        ActorUserInterface operatorUserInterface = new ActorUserInterface("Operator", createEmergencyUserInterface);
        ActorUserInterface dispatcherUserInterface = new ActorUserInterface("Dispatcher", dispatchUnitsUserInterface, inspectEmergenciesUserInterface, removeUnitAssignmentInterface);
        ActorUserInterface demonstratorUserInterface = new ActorUserInterface("Demonstrator", timeAheadUserInterface);
        ActorUserInterface unitCommanderUserInterface = new ActorUserInterface("Unit commander", selectHospitalUserInterface, endOfEmergencyUserInterface);
        mainUserInterface.addActorUserInterfaces(operatorUserInterface, dispatcherUserInterface, demonstratorUserInterface, unitCommanderUserInterface);
        return mainUserInterface;
    }
}
