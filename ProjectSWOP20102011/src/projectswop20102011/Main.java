package projectswop20102011;

import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.external.ExternalSystem;
import be.kuleuven.cs.swop.external.IExternalSystem;
import java.io.FileInputStream;
import projectswop20102011.controllers.CreateDisasterController;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.DisasterMapper;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.DispatchUnitsToDisasterController;
import projectswop20102011.controllers.EmergencyMapper;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectDisastersController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.controllers.RemoveUnitAssignmentFromDisasterController;
import projectswop20102011.controllers.RemoveUnitAssignmentFromEmergencyController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.lists.EmergencyFactoryList;
import projectswop20102011.domain.lists.ParserList;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.externalsystem.EmergencyDispatchApi;
import projectswop20102011.factories.FireFactory;
import projectswop20102011.factories.PublicDisturbanceFactory;
import projectswop20102011.factories.RobberyFactory;
import projectswop20102011.factories.TrafficAccidentFactory;
import projectswop20102011.userinterface.ActorUserInterface;
import projectswop20102011.userinterface.CommandUserInterface;
import projectswop20102011.userinterface.CreateDisasterUserInterface;
import projectswop20102011.userinterface.CreateEmergencyUserInterface;
import projectswop20102011.userinterface.DispatchUnitsToDisasterUserInterface;
import projectswop20102011.userinterface.DispatchUnitsToEmergencyUserInterface;
import projectswop20102011.userinterface.EndOfTaskUserInterface;
import projectswop20102011.userinterface.EnvironmentReader;
import projectswop20102011.userinterface.InspectDisastersUserInterface;
import projectswop20102011.userinterface.InspectEmergenciesUserInterface;
import projectswop20102011.userinterface.MainUserInterface;
import projectswop20102011.userinterface.RemoveUnitAssignmentFromDisasterUserInterface;
import projectswop20102011.userinterface.RemoveUnitAssignmentFromEmergencyUserInterface;
import projectswop20102011.userinterface.SelectHospitalUserInterface;
import projectswop20102011.userinterface.TimeAheadUserInterface;
import projectswop20102011.utils.parsers.BooleanParser;
import projectswop20102011.utils.parsers.EmergencySeverityParser;
import projectswop20102011.utils.parsers.FireSizeParser;
import projectswop20102011.utils.parsers.GPSCoordinateParser;
import projectswop20102011.utils.parsers.IntegerParser;
import projectswop20102011.utils.parsers.LongParser;
import projectswop20102011.utils.parsers.StringParser;

public class Main {

	public static void main(String[] args) throws Exception {
		World world = initWorld();
		IExternalSystem es = initExternalSystem(world);
		if (args.length == 0 || readEnvironment(world, args)) {
			initUserInterface(world, es).handleUserInterface();
		}
	}

	//TODO: deze methode heb ik (Jonas Vanthornhout) eventjus public gezet
	public static World initWorld() throws InvalidEmergencyTypeNameException {
		World world = new World();

		EmergencyFactoryList efl = world.getEmergencyFactoryList();
		efl.addEmergencyFactory(new FireFactory());
		efl.addEmergencyFactory(new TrafficAccidentFactory());
		efl.addEmergencyFactory(new RobberyFactory());
		efl.addEmergencyFactory(new PublicDisturbanceFactory());

		ParserList pl = world.getParserList();
		pl.addParser(new BooleanParser());
		pl.addParser(new EmergencySeverityParser());
		pl.addParser(new FireSizeParser());
		pl.addParser(new GPSCoordinateParser());
		pl.addParser(new IntegerParser());
		pl.addParser(new LongParser());
		pl.addParser(new StringParser());

		return world;
	}

	private static IExternalSystem initExternalSystem(World world) {
		IEmergencyDispatchApi api = new EmergencyDispatchApi(world);
		world.setIEmergencyDispatchApi(api);
		return ExternalSystem.bootstrap(api);
	}

	private static boolean readEnvironment(World world, String[] args) throws Exception {
		EnvironmentReader er = new EnvironmentReader(new ReadEnvironmentDataController(world));
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
		CommandUserInterface inspectEmergenciesUserInterface = new InspectEmergenciesUserInterface(new InspectEmergenciesController(world), new EmergencyMapper(world));
		CommandUserInterface dispatchUnitsUserInterface = new DispatchUnitsToEmergencyUserInterface(new DispatchUnitsController(world), new EmergencyMapper(world));
		CommandUserInterface selectHospitalUserInterface = new SelectHospitalUserInterface(new SelectHospitalController(world));
		CommandUserInterface endOfEmergencyUserInterface = new EndOfTaskUserInterface(new EndOfTaskController(world));
		CommandUserInterface timeAheadUserInterface = new TimeAheadUserInterface(new TimeAheadController(world, es));
		CommandUserInterface removeUnitAssignmentFromEmergencyInterface = new RemoveUnitAssignmentFromEmergencyUserInterface(new RemoveUnitAssignmentFromEmergencyController(world), new EmergencyMapper(world));
		CommandUserInterface removeUnitAssignmentFromDisasterInterface = new RemoveUnitAssignmentFromDisasterUserInterface(new RemoveUnitAssignmentFromDisasterController(world), new DisasterMapper(world));
		CommandUserInterface dispatchUnitsToDisasterInterface = new DispatchUnitsToDisasterUserInterface(new DispatchUnitsToDisasterController(world), new DisasterMapper(world));
		CommandUserInterface createDisasterInterface = new CreateDisasterUserInterface(new CreateDisasterController(world), new InspectEmergenciesController(world), new EmergencyMapper(world)); //TODO: ik (jonas) heb hier de twee laatste parameters bijgezet om te kunnen compilen
		CommandUserInterface inspectDisastersInterface = new InspectDisastersUserInterface(new InspectDisastersController(world), new DisasterMapper(world));
		ActorUserInterface operatorUserInterface = new ActorUserInterface("Operator", createEmergencyUserInterface, createDisasterInterface);
		ActorUserInterface dispatcherUserInterface = new ActorUserInterface("Dispatcher", dispatchUnitsUserInterface, inspectEmergenciesUserInterface, removeUnitAssignmentFromEmergencyInterface, removeUnitAssignmentFromDisasterInterface, dispatchUnitsToDisasterInterface,inspectDisastersInterface);
		ActorUserInterface demonstratorUserInterface = new ActorUserInterface("Demonstrator", timeAheadUserInterface);
		ActorUserInterface unitCommanderUserInterface = new ActorUserInterface("Unit commander", selectHospitalUserInterface, endOfEmergencyUserInterface);
		mainUserInterface.addActorUserInterfaces(operatorUserInterface, dispatcherUserInterface, demonstratorUserInterface, unitCommanderUserInterface);
		return mainUserInterface;
	}
}
