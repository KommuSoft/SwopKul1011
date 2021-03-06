package projectswop20102011;

import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.external.ExternalSystem;
import be.kuleuven.cs.swop.external.logging.Logger;
import be.kuleuven.cs.swop.scenarios.SimpleScenario;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import projectswop20102011.controllers.CreateEmergencyController;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.controllers.DispatchUnitsToEmergencyController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.controllers.RemoveUnitAssignmentFromEmergencyController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.domain.SendableStatus;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.lists.EmergencyFactoryList;
import projectswop20102011.domain.lists.ParserList;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.externalsystem.EmergencyDispatchApi;
import projectswop20102011.externalsystem.NullObjectExternalSystem;
import projectswop20102011.externalsystem.adapters.HospitalAdapter;
import projectswop20102011.factories.FireFactory;
import projectswop20102011.factories.PublicDisturbanceFactory;
import projectswop20102011.factories.RobberyFactory;
import projectswop20102011.factories.TrafficAccidentFactory;
import projectswop20102011.utils.parsers.BooleanParser;
import projectswop20102011.utils.parsers.EmergencySeverityParser;
import projectswop20102011.utils.parsers.FireSizeParser;
import projectswop20102011.utils.parsers.GPSCoordinateParser;
import projectswop20102011.utils.parsers.IntegerParser;
import projectswop20102011.utils.parsers.LongParser;
import projectswop20102011.utils.parsers.StringParser;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Scenario1Test {

	private CreateEmergencyController cec;
	private InspectEmergenciesController iec;
	private ReadEnvironmentDataController redc;
	private DispatchUnitsToEmergencyController duc;
	private EndOfTaskController eotc;
	private TimeAheadController tac;
	private SelectHospitalController shc;
	private RemoveUnitAssignmentFromEmergencyController ruac;
	private IEmergencyDispatchApi api;

	@Before
	public void setUp() throws InvalidWorldException, InvalidEmergencyTypeNameException {
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
		api = new EmergencyDispatchApi(world);
		world.setIEmergencyDispatchApi(api);
		cec = new CreateEmergencyController(world, new NullObjectExternalSystem());
		iec = new InspectEmergenciesController(world);
		redc = new ReadEnvironmentDataController(world);
		duc = new DispatchUnitsToEmergencyController(world);
		eotc = new EndOfTaskController(world);
		tac = new TimeAheadController(world, ExternalSystem.bootstrap(api));
		shc = new SelectHospitalController(world);
		ruac = new RemoveUnitAssignmentFromEmergencyController(world);
	}

	@Test
	public void testScenario1() throws InvalidLocationException, InvalidSendableSeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemTypeNameException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException, InvalidEmergencyException, InvalidSendableStatusException, Exception {
		//initialize world
		redc.addFiretruck("engine1", new GPSCoordinate(0, 20), 5, 100000);
		redc.addFiretruck("engine2", new GPSCoordinate(0, 21), 5, 100000);
		redc.addFiretruck("engine3", new GPSCoordinate(0, 22), 5, 1000);
		redc.addPolicecar("unit1", new GPSCoordinate(0, 23), 5);
		redc.addAmbulance("ambulance1", new GPSCoordinate(0, 24), 5);
		redc.addAmbulance("ambulance2", new GPSCoordinate(0, 25), 5);
		redc.addHospital("hospital1", new GPSCoordinate(2, 0));
		try {
			redc.addPolicecar("unit2", null, 5);
			fail("creation of invalid policecar must fail");
		} catch (Exception e) {
		}
		try {
			redc.addPolicecar("unit2", new GPSCoordinate(0, 0), -5);
			fail("creation of invalid policecar must fail");
		} catch (Exception e) {
		}

		//inspecting emergencies
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED).length);
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.RESPONSE_IN_PROGRESS).length);
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.COMPLETED).length);

		//adding fire
		cec.createFireEmergency(new GPSCoordinate(0, 0), SendableSeverity.BENIGN, "geïmplodeerde tv", FireSize.LOCAL, false, 0, 0);

		//inspecting emergencies
		Emergency[] rbu_em = iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
		assertEquals(1, rbu_em.length);
		Emergency emergency = rbu_em[0];
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.RESPONSE_IN_PROGRESS).length);
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.COMPLETED).length);
		assertFalse(emergency.isPartiallyAssigned());

		//assign engine1 to the fire
		List<Unit> units = duc.getAvailableUnitsNeededSorted(rbu_em[0]);
		Firetruck engine1 = (Firetruck) units.get(0);

		try {
			eotc.indicateEndOfTask(engine1);
			fail("Engine can't end a task if he hasn't any!");
		} catch (Exception e) {
		}

		try {
			ruac.withdrawUnit(engine1);
			fail("Engine can't withdraw from a task if he hasn't any!");
		} catch (Exception e) {
		}

		Firetruck engine2 = (Firetruck) units.get(1);
		Firetruck engine3 = (Firetruck) units.get(2);
		assertEquals("engine1", engine1.getName());
		Set<Unit> assign_units = new HashSet<Unit>();
		assign_units.add(engine1);
		duc.dispatchToEmergency(emergency, assign_units);

		try {
			duc.dispatchToEmergency(rbu_em[0], assign_units);
			fail("can not assign a working unit");
		} catch (Exception e) {
		}

		//inspecting emergencies
		assertFalse(emergency.isPartiallyAssigned());
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED).length);
		assertEquals(1, iec.inspectEmergenciesOnStatus(SendableStatus.RESPONSE_IN_PROGRESS).length);
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.COMPLETED).length);
		try {
			eotc.indicateEndOfTask(engine1);
			fail("engine1 cant end of task");
		} catch (Exception e) {
		}

		tac.doTimeAheadAction(18);

		try {
			eotc.indicateEndOfTask(engine1);
			fail("engine1 cant end of task");
		} catch (Exception e) {
		}

		//ADDING EMERGENCIES BY EXTERNAL SYSTEM:
		//[type=Robbery; assignable=true; status=recorded but unhandled; location=(35,80); working units=[  ]; severity=urgent; type=Robbery; description=]
		//[type=TrafficAccident; assignable=true; status=recorded but unhandled; location=(-90,5); working units=[  ]; severity=serious; type=TrafficAccident; description=]
		//[type=PublicDisturbance; assignable=true; status=recorded but unhandled; location=(-10,40); working units=[  ]; severity=normal; type=PublicDisturbance; description=]
		//[type=Fire; assignable=true; status=recorded but unhandled; location=(90,90); working units=[  ]; severity=normal; type=Fire; description=]
		//[type=TrafficAccident; assignable=true; status=recorded but unhandled; location=(100,0); working units=[  ]; severity=serious; type=TrafficAccident; description=]
		//tac.doTimeAheadAction(14400);
		SimpleScenario ss = new SimpleScenario(api, new Logger());
		ss.run();

		eotc.indicateEndOfTask(engine1);

		//inspect emergencies
		assertFalse(emergency.isPartiallyAssigned());
		assertEquals(5, iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED).length);
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.RESPONSE_IN_PROGRESS).length);
		assertEquals(1, iec.inspectEmergenciesOnStatus(SendableStatus.COMPLETED).length);
		//adding fire
		cec.createFireEmergency(new GPSCoordinate(0, 0), SendableSeverity.BENIGN, "terrorist maakt METH", FireSize.HOUSE, false, 0, 1);
		//inspect emergencies
		rbu_em = iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
		assertEquals(6, rbu_em.length);
		Fire fire = null;
		for (int i = 0; i < rbu_em.length; i++) {
			if (rbu_em[i].getDescription().equals("terrorist maakt METH")) {
				fire = (Fire) rbu_em[i];
				break;
			}
		}
		assertFalse(fire.isPartiallyAssigned());

		assign_units.clear();
		assign_units.add(engine1);
		duc.dispatchToEmergency(fire, assign_units);

		assertTrue(fire.isPartiallyAssigned());

		assign_units.clear();
		assign_units.add(engine2);
		duc.dispatchToEmergency(fire, assign_units);


		assertTrue(fire.isPartiallyAssigned());
		tac.doTimeAheadAction(25000);
		eotc.indicateEndOfTask(engine2);

		assertTrue(fire.isPartiallyAssigned());

		//inspect emergencies
		assertEquals(5, iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED).length);
		assertEquals(1, iec.inspectEmergenciesOnStatus(SendableStatus.RESPONSE_IN_PROGRESS).length);
		assertEquals(1, iec.inspectEmergenciesOnStatus(SendableStatus.COMPLETED).length);
		Set<Unit> policy_units = duc.getUnitsByPolicy(fire);

		assertEquals(2, policy_units.size());
		assertTrue(fire.isPartiallyAssigned());

		tac.doTimeAheadAction(18000);
		try {
			eotc.indicateEndOfTask(engine2);
			fail("firetruck isn't assigned to an emergency, so it can't end his task");
		} catch (Exception e) {
		}

		//inspect emergencies
		assertTrue(fire.isPartiallyAssigned());
		assertEquals(5, iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED).length);
		assertEquals(1, iec.inspectEmergenciesOnStatus(SendableStatus.RESPONSE_IN_PROGRESS).length);
		assertEquals(1, iec.inspectEmergenciesOnStatus(SendableStatus.COMPLETED).length);

		duc.dispatchToEmergency(fire, policy_units);

		assertFalse(fire.isPartiallyAssigned());

		tac.doTimeAheadAction(6566485);

		Iterator<Unit> it = fire.getWorkingUnits().iterator();
		while (it.hasNext()) {
			Unit u = it.next();
			if (u.getClass().getSimpleName().equalsIgnoreCase("ambulance")) {
				Ambulance a = (Ambulance) u;
				HospitalAdapter haha = (HospitalAdapter) api.getListOfHospitals().get(0);
				a.selectHospital(haha.getHospital());
			}
		}

		tac.doTimeAheadAction(6566485);

		it = fire.getWorkingUnits().iterator();
		while (it.hasNext()) {
			it.next().finishedJob();
		}

		assertFalse(fire.isPartiallyAssigned());

		assertEquals(5, iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED).length);
		assertEquals(0, iec.inspectEmergenciesOnStatus(SendableStatus.RESPONSE_IN_PROGRESS).length);
		assertEquals(2, iec.inspectEmergenciesOnStatus(SendableStatus.COMPLETED).length);
	}
}
