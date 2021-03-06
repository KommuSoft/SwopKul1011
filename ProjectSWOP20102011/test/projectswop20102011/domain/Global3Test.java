package projectswop20102011.domain;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import projectswop20102011.World;
import projectswop20102011.controllers.CreateDisasterController;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.DispatchUnitsToDisasterController;
import projectswop20102011.controllers.DispatchUnitsToEmergencyController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectDisastersController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.controllers.RemoveUnitAssignmentFromDisasterController;
import projectswop20102011.controllers.RemoveUnitAssignmentFromEmergencyController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.exceptions.InvalidAddedDisasterException;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidFinishJobException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.externalsystem.NullObjectExternalSystem;

public class Global3Test {

	World world;
	CreateDisasterController cdc;
	InspectEmergenciesController iec;
	InspectDisastersController idc;
	CreateEmergencyController cec;
	DispatchUnitsToEmergencyController duc;
	DispatchUnitsToDisasterController dudc;
	SelectHospitalController shc;
	EndOfTaskController eotc;
	RemoveUnitAssignmentFromEmergencyController ruafe;
	RemoveUnitAssignmentFromDisasterController ruafd;
	TimeAheadController tac;
	ReadEnvironmentDataController rec;
	Firetruck ft1, ft2, ft3, ft4, ft5;
	Ambulance am1, am2, am3, am4, am5, am6;
	Policecar pc1, pc2, pc3, pc4, pc5;
	Hospital h1, h2, h3;
	Fire f1, f2;
	//Fire f3;
	Robbery r1, r2, r3, r4;
	TrafficAccident ta1, ta2, ta3;
	PublicDisturbance pd1, pd2, pd3;
	DispatchUnitsToEmergencyController duec;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidWorldException, InvalidSpeedException, InvalidCapacityException, NumberOutOfBoundsException, InvalidFireSizeException, InvalidSendableSeverityException {
		world = new World();
		iec = new InspectEmergenciesController(world);
		cec = new CreateEmergencyController(world, new NullObjectExternalSystem());
		duc = new DispatchUnitsToEmergencyController(world);
		shc = new SelectHospitalController(world);
		eotc = new EndOfTaskController(world);
		ruafe = new RemoveUnitAssignmentFromEmergencyController(world);
		ruafd = new RemoveUnitAssignmentFromDisasterController(world);
		cdc = new CreateDisasterController(world);
		dudc = new DispatchUnitsToDisasterController(world);
		idc = new InspectDisastersController(world);
		tac = new TimeAheadController(world, new NullObjectExternalSystem());
		rec = new ReadEnvironmentDataController(world);
		duec = new DispatchUnitsToEmergencyController(world);

		//Fires aanmaken
		f1 = new Fire(new GPSCoordinate(-100, -100), SendableSeverity.SERIOUS, "brand1", FireSize.LOCAL, false, 5, 1);
		f2 = new Fire(new GPSCoordinate(-200, -200), SendableSeverity.URGENT, "brand2", FireSize.HOUSE, true, 6, 6);
		//f3 = new Fire(new GPSCoordinate(-300, -300), SendableSeverity.BENIGN, "brand3", FireSize.FACILITY, false, 1, 0);

		//Robberies aanmaken
		r1 = new Robbery(new GPSCoordinate(-400, -400), SendableSeverity.SERIOUS, "beroving1", false, false);
		r2 = new Robbery(new GPSCoordinate(-500, -500), SendableSeverity.URGENT, "beroving2", true, false);
		r3 = new Robbery(new GPSCoordinate(-600, -600), SendableSeverity.NORMAL, "beroving3", false, true);
		r4 = new Robbery(new GPSCoordinate(-700, -700), SendableSeverity.BENIGN, "beroving4", true, true);

		//TrafficAccidents aanmaken
		ta1 = new TrafficAccident(new GPSCoordinate(-800, -800), SendableSeverity.BENIGN, "ongeluk1", 2, 3);
		ta2 = new TrafficAccident(new GPSCoordinate(-900, -900), SendableSeverity.NORMAL, "ongeluk2", 7, 5);
		ta3 = new TrafficAccident(new GPSCoordinate(-1000, -1000), SendableSeverity.URGENT, "ongeluk3", 4, 8);

		//Public Disturbances aanmaken
		pd1 = new PublicDisturbance(new GPSCoordinate(-1100, -1100), SendableSeverity.URGENT, "boel1", 4);
		pd2 = new PublicDisturbance(new GPSCoordinate(-1200, -1200), SendableSeverity.NORMAL, "boel2", 4);
		pd3 = new PublicDisturbance(new GPSCoordinate(-1300, -1300), SendableSeverity.BENIGN, "boel3", 4);

	}

	@Test
	public void testFire() throws InvalidEmergencyException, InvalidConstraintListException, InvalidClassException, InvalidSendableStatusException, InvalidAddedDisasterException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidDurationException, InvalidWithdrawalException, InvalidMapItemException {

		//Firetrucks aanmaken
		ft1 = new Firetruck("brandweerwagen1", new GPSCoordinate(100, 100), 10 * 3600, 1001);
		ft2 = new Firetruck("brandweerwagen2", new GPSCoordinate(200, 200), 10 * 3600, 500001);
		ft3 = new Firetruck("brandweerwagen3", new GPSCoordinate(300, 300), 10 * 3600, 95000);
		ft4 = new Firetruck("brandweerwagen4", new GPSCoordinate(400, 400), 10 * 3600, 6000);
		ft5 = new Firetruck("brandweerwagen5", new GPSCoordinate(500, 500), 10 * 3600, 42000);

		//Ambulances aanmaken
		am1 = new Ambulance("ziekenwagen1", new GPSCoordinate(600, 600), 10 * 3600);
		am2 = new Ambulance("ziekenwagen2", new GPSCoordinate(700, 700), 10 * 3600);
		am3 = new Ambulance("ziekenwagen3", new GPSCoordinate(800, 800), 10 * 3600);

		//Policecars aanmaken
		pc1 = new Policecar("politiewagen1", new GPSCoordinate(1200, 1200), 10 * 3600);
		pc2 = new Policecar("politiewagen2", new GPSCoordinate(1300, 1300), 10 * 3600);
		pc3 = new Policecar("politiewagen3", new GPSCoordinate(1400, 1400), 10 * 3600);
		pc4 = new Policecar("politiewagen4", new GPSCoordinate(1500, 1500), 10 * 3600);
		pc5 = new Policecar("politiewagen5", new GPSCoordinate(1600, 1600), 10 * 3600);

		//Hospitals aanmaken
		rec.addHospital("UZ1", new GPSCoordinate(-5000, -5000));
		rec.addHospital("UZ2", new GPSCoordinate(0, 0));
		rec.addHospital("UZ3", new GPSCoordinate(5000, 5000));

		//Firetrucks toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(ft1);
		world.getMapItemList().addMapItem(ft2);
		world.getMapItemList().addMapItem(ft3);
		world.getMapItemList().addMapItem(ft4);
		world.getMapItemList().addMapItem(ft5);

		//Ambulances toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(am1);
		world.getMapItemList().addMapItem(am2);
		world.getMapItemList().addMapItem(am3);

		//Policars toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(pc1);
		world.getMapItemList().addMapItem(pc2);
		world.getMapItemList().addMapItem(pc3);
		world.getMapItemList().addMapItem(pc4);
		////world.getMapItemList().addMapItem(pc5);

		//Hospitals toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(h1);
		world.getMapItemList().addMapItem(h2);
		world.getMapItemList().addMapItem(h3);

		//Firetrucks toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(ft1);
		world.getTimeSensitiveList().addTimeSensitive(ft2);
		world.getTimeSensitiveList().addTimeSensitive(ft3);
		world.getTimeSensitiveList().addTimeSensitive(ft4);
		world.getTimeSensitiveList().addTimeSensitive(ft5);

		//Ambulances toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(am1);
		world.getTimeSensitiveList().addTimeSensitive(am2);
		world.getTimeSensitiveList().addTimeSensitive(am3);

		//Policars toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(pc1);
		world.getTimeSensitiveList().addTimeSensitive(pc2);
		world.getTimeSensitiveList().addTimeSensitive(pc3);
		world.getTimeSensitiveList().addTimeSensitive(pc4);

		//Alle Emergencies toevoegen aan de World
		cec.addCreatedEmergencyToTheWorld(f1);
		cec.addCreatedEmergencyToTheWorld(f2);

		List<Emergency> emergenciesForDisaster = new ArrayList<Emergency>(0);

		emergenciesForDisaster.add(f1);
		emergenciesForDisaster.add(f2);

		String description = "Tis de moeite";
		cdc.createDisaster(emergenciesForDisaster, description);

		Disaster[] disasters = idc.inspectDisastersOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
		Disaster disaster = disasters[0];

		Set<Unit> unitsFromPolicy = dudc.getUnitsByPolicy(disaster);
		//Set<Unit> unitsFromPolicy = duec.getUnitsByPolicy(f2);

		int counter;

		counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsFromPolicy);
		assertEquals(3, counter);
		counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsFromPolicy);
		assertEquals(3, counter);
		counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsFromPolicy);
		assertEquals(1, counter);
		List<Policecar> policecars = getPolicecars(unitsFromPolicy);
		assertEquals(pc1.getName(), policecars.get(0).getName());

		dudc.dispatchToDisaster(disaster, unitsFromPolicy);

		tac.doTimeAheadAction(1);

		ruafd.withdrawUnit(am1);
		ruafd.withdrawUnit(am2);
		ruafd.withdrawUnit(am3);

		ruafd.withdrawUnit(pc1);
		try {
			ruafd.withdrawUnit(pc2);
			fail("This unit can't be withdrawn");
		} catch (InvalidWithdrawalException e) {
		}
		try {
			ruafd.withdrawUnit(pc3);
			fail("This unit can't be withdrawn");

		} catch (InvalidWithdrawalException e) {
		}
		try {
			ruafd.withdrawUnit(pc4);
			fail("This unit can't be withdrawn");
		} catch (InvalidWithdrawalException e) {
		}
		try {
			ruafd.withdrawUnit(ft1);
			fail("This unit can't be withdrawn.");
		} catch (InvalidWithdrawalException e) {
		}
		try {
			ruafd.withdrawUnit(ft2);
			fail("This unit can't be withdrawn.");
		} catch (InvalidWithdrawalException e) {
		}
		try {
			ruafd.withdrawUnit(ft3);
			fail("This unit can't be withdrawn.");
		} catch (InvalidWithdrawalException e) {
		}
		try {
			ruafd.withdrawUnit(ft4);
			fail("This unit can't be withdrawn.");
		} catch (InvalidWithdrawalException e) {
		}
		try {
			ruafd.withdrawUnit(ft5);
			fail("This unit can't be withdrawn.");
		} catch (InvalidWithdrawalException e) {
		}

		unitsFromPolicy = dudc.getUnitsByPolicy(disaster);

		counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsFromPolicy);
		assertEquals(3, counter);
		counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsFromPolicy);
		assertEquals(1, counter);
		counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsFromPolicy);
		assertEquals(0, counter);//hier faalt de test
	}

	@Test
	public void testFinishedJob() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidEmergencyException, InvalidAddedDisasterException, InvalidConstraintListException, InvalidClassException, InvalidSendableStatusException, InvalidWithdrawalException, InvalidMapItemException, InvalidDurationException, InvalidUnitException, InvalidFinishJobException {
		//Firetrucks aanmaken
		ft1 = new Firetruck("brandweerwagen1", new GPSCoordinate(100, 100), 10 * 3600, 1001);
		ft2 = new Firetruck("brandweerwagen2", new GPSCoordinate(200, 200), 10 * 3600, 500001);
		ft3 = new Firetruck("brandweerwagen3", new GPSCoordinate(300, 300), 10 * 3600, 95000);
		ft4 = new Firetruck("brandweerwagen4", new GPSCoordinate(400, 400), 10 * 3600, 6000);
		ft5 = new Firetruck("brandweerwagen5", new GPSCoordinate(500, 500), 10 * 3600, 42000);

		//Ambulances aanmaken
		am1 = new Ambulance("ziekenwagen1", new GPSCoordinate(600, 600), 10 * 3600);
		am2 = new Ambulance("ziekenwagen2", new GPSCoordinate(700, 700), 10 * 3600);
		am3 = new Ambulance("ziekenwagen3", new GPSCoordinate(800, 800), 10 * 3600);

		//Policecars aanmaken
		pc1 = new Policecar("politiewagen1", new GPSCoordinate(1200, 1200), 10 * 3600);
		pc2 = new Policecar("politiewagen2", new GPSCoordinate(1300, 1300), 10 * 3600);
		pc3 = new Policecar("politiewagen3", new GPSCoordinate(1400, 1400), 10 * 3600);
		pc4 = new Policecar("politiewagen4", new GPSCoordinate(1500, 1500), 10 * 3600);
		pc5 = new Policecar("politiewagen5", new GPSCoordinate(1600, 1600), 10 * 3600);

		//Hospitals aanmaken
		h1 = new Hospital("UZ1", new GPSCoordinate(-5000, -5000));

		rec.addHospital("UZ1", new GPSCoordinate(-5000, -5000));
		rec.addHospital("UZ2", new GPSCoordinate(0, 0));
		rec.addHospital("UZ3", new GPSCoordinate(5000, 5000));

		//Firetrucks toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(ft1);
		world.getMapItemList().addMapItem(ft2);
		world.getMapItemList().addMapItem(ft3);
		world.getMapItemList().addMapItem(ft4);
		world.getMapItemList().addMapItem(ft5);

		//Ambulances toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(am1);
		world.getMapItemList().addMapItem(am2);
		world.getMapItemList().addMapItem(am3);

		//Policars toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(pc1);
		world.getMapItemList().addMapItem(pc2);
		world.getMapItemList().addMapItem(pc3);
		world.getMapItemList().addMapItem(pc4);
		////world.getMapItemList().addMapItem(pc5);

		//Hospitals toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(h1);
		world.getMapItemList().addMapItem(h2);
		world.getMapItemList().addMapItem(h3);

		//Firetrucks toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(ft1);
		world.getTimeSensitiveList().addTimeSensitive(ft2);
		world.getTimeSensitiveList().addTimeSensitive(ft3);
		world.getTimeSensitiveList().addTimeSensitive(ft4);
		world.getTimeSensitiveList().addTimeSensitive(ft5);

		//Ambulances toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(am1);
		world.getTimeSensitiveList().addTimeSensitive(am2);
		world.getTimeSensitiveList().addTimeSensitive(am3);

		//Policars toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(pc1);
		world.getTimeSensitiveList().addTimeSensitive(pc2);
		world.getTimeSensitiveList().addTimeSensitive(pc3);
		world.getTimeSensitiveList().addTimeSensitive(pc4);

		//Alle Emergencies toevoegen aan de World
		cec.addCreatedEmergencyToTheWorld(f1);
		cec.addCreatedEmergencyToTheWorld(f2);

		List<Emergency> emergenciesForDisaster = new ArrayList<Emergency>(0);

		emergenciesForDisaster.add(f1);
		emergenciesForDisaster.add(f2);

		String description = "Tis de moeite";
		cdc.createDisaster(emergenciesForDisaster, description);

		Disaster[] disasters = idc.inspectDisastersOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
		Disaster disaster = disasters[0];

		Set<Unit> unitsFromPolicy = dudc.getUnitsByPolicy(disaster);
		//Set<Unit> unitsFromPolicy = duec.getUnitsByPolicy(f2);

		int counter;

		counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsFromPolicy);
		assertEquals(3, counter);
		counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsFromPolicy);
		assertEquals(3, counter);
		counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsFromPolicy);
		assertEquals(1, counter);
		List<Policecar> policecars = getPolicecars(unitsFromPolicy);
		assertEquals(pc1.getName(), policecars.get(0).getName());

		dudc.dispatchToDisaster(disaster, unitsFromPolicy);

		tac.doTimeAheadAction(10000000);

		for (Unit u : disaster.getWorkingUnits()) {
			if (u instanceof Ambulance) {
				shc.selectHospital((Ambulance) u, h1);
			}
		}

		tac.doTimeAheadAction(10000000);

		for (Unit u : disaster.getWorkingUnits()) {
			eotc.indicateEndOfTask(u);
		}

	}

	@Test
	public void testFinishedLittleJob() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidEmergencyException, InvalidAddedDisasterException, InvalidConstraintListException, InvalidClassException, InvalidSendableStatusException, InvalidWithdrawalException, InvalidMapItemException, InvalidDurationException, InvalidUnitException, InvalidFinishJobException {
		//Firetrucks aanmaken
		ft1 = new Firetruck("brandweerwagen1", new GPSCoordinate(100, 100), 10 * 3600, 1001);
		ft2 = new Firetruck("brandweerwagen2", new GPSCoordinate(200, 200), 10 * 3600, 500001);
		ft3 = new Firetruck("brandweerwagen3", new GPSCoordinate(300, 300), 10 * 3600, 95000);
		ft4 = new Firetruck("brandweerwagen4", new GPSCoordinate(400, 400), 10 * 3600, 6000);
		ft5 = new Firetruck("brandweerwagen5", new GPSCoordinate(500, 500), 10 * 3600, 42000);

		//Ambulances aanmaken
		am1 = new Ambulance("ziekenwagen1", new GPSCoordinate(600, 600), 10 * 3600);
		am2 = new Ambulance("ziekenwagen2", new GPSCoordinate(700, 700), 10 * 3600);
		am3 = new Ambulance("ziekenwagen3", new GPSCoordinate(800, 800), 10 * 3600);

		//Policecars aanmaken
		pc1 = new Policecar("politiewagen1", new GPSCoordinate(1200, 1200), 10 * 3600);
		pc2 = new Policecar("politiewagen2", new GPSCoordinate(1300, 1300), 10 * 3600);
		pc3 = new Policecar("politiewagen3", new GPSCoordinate(1400, 1400), 10 * 3600);
		pc4 = new Policecar("politiewagen4", new GPSCoordinate(1500, 1500), 10 * 3600);
		pc5 = new Policecar("politiewagen5", new GPSCoordinate(1600, 1600), 10 * 3600);

		//Hospitals aanmaken
		h1 = new Hospital("UZ1", new GPSCoordinate(-5000, -5000));

		rec.addHospital("UZ1", new GPSCoordinate(-5000, -5000));
		rec.addHospital("UZ2", new GPSCoordinate(0, 0));
		rec.addHospital("UZ3", new GPSCoordinate(5000, 5000));

		//Firetrucks toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(ft1);
		world.getMapItemList().addMapItem(ft2);
		world.getMapItemList().addMapItem(ft3);
		world.getMapItemList().addMapItem(ft4);
		world.getMapItemList().addMapItem(ft5);

		//Ambulances toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(am1);
		world.getMapItemList().addMapItem(am2);
		world.getMapItemList().addMapItem(am3);

		//Policars toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(pc1);
		world.getMapItemList().addMapItem(pc2);
		world.getMapItemList().addMapItem(pc3);
		world.getMapItemList().addMapItem(pc4);
		////world.getMapItemList().addMapItem(pc5);

		//Hospitals toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(h1);
		world.getMapItemList().addMapItem(h2);
		world.getMapItemList().addMapItem(h3);

		//Firetrucks toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(ft1);
		world.getTimeSensitiveList().addTimeSensitive(ft2);
		world.getTimeSensitiveList().addTimeSensitive(ft3);
		world.getTimeSensitiveList().addTimeSensitive(ft4);
		world.getTimeSensitiveList().addTimeSensitive(ft5);

		//Ambulances toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(am1);
		world.getTimeSensitiveList().addTimeSensitive(am2);
		world.getTimeSensitiveList().addTimeSensitive(am3);

		//Policars toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(pc1);
		world.getTimeSensitiveList().addTimeSensitive(pc2);
		world.getTimeSensitiveList().addTimeSensitive(pc3);
		world.getTimeSensitiveList().addTimeSensitive(pc4);

		//Alle Emergencies toevoegen aan de World
		cec.addCreatedEmergencyToTheWorld(f1);
		cec.addCreatedEmergencyToTheWorld(f2);

		List<Emergency> emergenciesForDisaster = new ArrayList<Emergency>(0);

		emergenciesForDisaster.add(f1);

		String description = "Tis de moeite";
		cdc.createDisaster(emergenciesForDisaster, description);

		Disaster[] disasters = idc.inspectDisastersOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
		Disaster disaster = disasters[0];

		Set<Unit> unitsFromPolicy = dudc.getUnitsByPolicy(disaster);

		dudc.dispatchToDisaster(disaster, unitsFromPolicy);

		tac.doTimeAheadAction(10000000);

		for (Unit u : disaster.getWorkingUnits()) {
			if (u instanceof Ambulance) {
				shc.selectHospital((Ambulance) u, h1);
			}
		}

		tac.doTimeAheadAction(10000000);

		for (Unit u : disaster.getWorkingUnits()) {
			eotc.indicateEndOfTask(u);
		}

	}

	@Test
	public void testFinishedJobSmallEmergency() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidEmergencyException, InvalidAddedDisasterException, InvalidConstraintListException, InvalidClassException, InvalidSendableStatusException, InvalidWithdrawalException, InvalidMapItemException, InvalidDurationException, InvalidUnitException, InvalidFinishJobException {
		//Firetrucks aanmaken
		ft1 = new Firetruck("brandweerwagen1", new GPSCoordinate(100, 100), 10 * 3600, 1001);
		ft2 = new Firetruck("brandweerwagen2", new GPSCoordinate(200, 200), 10 * 3600, 500001);
		ft3 = new Firetruck("brandweerwagen3", new GPSCoordinate(300, 300), 10 * 3600, 95000);
		ft4 = new Firetruck("brandweerwagen4", new GPSCoordinate(400, 400), 10 * 3600, 6000);
		ft5 = new Firetruck("brandweerwagen5", new GPSCoordinate(500, 500), 10 * 3600, 42000);

		//Ambulances aanmaken
		am1 = new Ambulance("ziekenwagen1", new GPSCoordinate(600, 600), 10 * 3600);
		am2 = new Ambulance("ziekenwagen2", new GPSCoordinate(700, 700), 10 * 3600);
		am3 = new Ambulance("ziekenwagen3", new GPSCoordinate(800, 800), 10 * 3600);

		//Policecars aanmaken
		pc1 = new Policecar("politiewagen1", new GPSCoordinate(1200, 1200), 10 * 3600);
		pc2 = new Policecar("politiewagen2", new GPSCoordinate(1300, 1300), 10 * 3600);
		pc3 = new Policecar("politiewagen3", new GPSCoordinate(1400, 1400), 10 * 3600);
		pc4 = new Policecar("politiewagen4", new GPSCoordinate(1500, 1500), 10 * 3600);
		pc5 = new Policecar("politiewagen5", new GPSCoordinate(1600, 1600), 10 * 3600);

		//Hospitals aanmaken
		h1 = new Hospital("UZ1", new GPSCoordinate(-5000, -5000));

		rec.addHospital("UZ1", new GPSCoordinate(-5000, -5000));
		rec.addHospital("UZ2", new GPSCoordinate(0, 0));
		rec.addHospital("UZ3", new GPSCoordinate(5000, 5000));

		//Firetrucks toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(ft1);
		world.getMapItemList().addMapItem(ft2);
		world.getMapItemList().addMapItem(ft3);
		world.getMapItemList().addMapItem(ft4);
		world.getMapItemList().addMapItem(ft5);

		//Ambulances toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(am1);
		world.getMapItemList().addMapItem(am2);
		world.getMapItemList().addMapItem(am3);

		//Policars toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(pc1);
		world.getMapItemList().addMapItem(pc2);
		world.getMapItemList().addMapItem(pc3);
		world.getMapItemList().addMapItem(pc4);
		////world.getMapItemList().addMapItem(pc5);

		//Hospitals toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(h1);
		world.getMapItemList().addMapItem(h2);
		world.getMapItemList().addMapItem(h3);

		//Firetrucks toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(ft1);
		world.getTimeSensitiveList().addTimeSensitive(ft2);
		world.getTimeSensitiveList().addTimeSensitive(ft3);
		world.getTimeSensitiveList().addTimeSensitive(ft4);
		world.getTimeSensitiveList().addTimeSensitive(ft5);

		//Ambulances toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(am1);
		world.getTimeSensitiveList().addTimeSensitive(am2);
		world.getTimeSensitiveList().addTimeSensitive(am3);

		//Policars toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(pc1);
		world.getTimeSensitiveList().addTimeSensitive(pc2);
		world.getTimeSensitiveList().addTimeSensitive(pc3);
		world.getTimeSensitiveList().addTimeSensitive(pc4);

		//Alle Emergencies toevoegen aan de World
		cec.addCreatedEmergencyToTheWorld(f1);
		cec.addCreatedEmergencyToTheWorld(f2);

		duec.dispatchToEmergency(f1, duec.getUnitsByPolicy(f1));

		tac.doTimeAheadAction(10000000);

		for (Unit u : f1.getWorkingUnits()) {
			if (u instanceof Ambulance) {
				shc.selectHospital((Ambulance) u, h1);
			}
		}

		tac.doTimeAheadAction(10000000);

		for (Unit u : f1.getWorkingUnits()) {
			eotc.indicateEndOfTask(u);
		}

	}

	@Test
	public void testFinishedJobEmergency() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidEmergencyException, InvalidAddedDisasterException, InvalidConstraintListException, InvalidClassException, InvalidSendableStatusException, InvalidWithdrawalException, InvalidMapItemException, InvalidDurationException, InvalidUnitException, InvalidFinishJobException {
		//Firetrucks aanmaken
		ft1 = new Firetruck("brandweerwagen1", new GPSCoordinate(100, 100), 10 * 3600, 1001);
		ft2 = new Firetruck("brandweerwagen2", new GPSCoordinate(200, 200), 10 * 3600, 500001);
		ft3 = new Firetruck("brandweerwagen3", new GPSCoordinate(300, 300), 10 * 3600, 95000);
		ft4 = new Firetruck("brandweerwagen4", new GPSCoordinate(400, 400), 10 * 3600, 6000);
		ft5 = new Firetruck("brandweerwagen5", new GPSCoordinate(500, 500), 10 * 3600, 42000);

		//Ambulances aanmaken
		am1 = new Ambulance("ziekenwagen1", new GPSCoordinate(600, 600), 10 * 3600);
		am2 = new Ambulance("ziekenwagen2", new GPSCoordinate(700, 700), 10 * 3600);
		am3 = new Ambulance("ziekenwagen3", new GPSCoordinate(800, 800), 10 * 3600);

		//Policecars aanmaken
		pc1 = new Policecar("politiewagen1", new GPSCoordinate(1200, 1200), 10 * 3600);
		pc2 = new Policecar("politiewagen2", new GPSCoordinate(1300, 1300), 10 * 3600);
		pc3 = new Policecar("politiewagen3", new GPSCoordinate(1400, 1400), 10 * 3600);
		pc4 = new Policecar("politiewagen4", new GPSCoordinate(1500, 1500), 10 * 3600);
		pc5 = new Policecar("politiewagen5", new GPSCoordinate(1600, 1600), 10 * 3600);

		//Hospitals aanmaken
		h1 = new Hospital("UZ1", new GPSCoordinate(-5000, -5000));

		rec.addHospital("UZ1", new GPSCoordinate(-5000, -5000));
		rec.addHospital("UZ2", new GPSCoordinate(0, 0));
		rec.addHospital("UZ3", new GPSCoordinate(5000, 5000));

		//Firetrucks toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(ft1);
		world.getMapItemList().addMapItem(ft2);
		world.getMapItemList().addMapItem(ft3);
		world.getMapItemList().addMapItem(ft4);
		world.getMapItemList().addMapItem(ft5);

		//Ambulances toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(am1);
		world.getMapItemList().addMapItem(am2);
		world.getMapItemList().addMapItem(am3);

		//Policars toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(pc1);
		world.getMapItemList().addMapItem(pc2);
		world.getMapItemList().addMapItem(pc3);
		world.getMapItemList().addMapItem(pc4);
		////world.getMapItemList().addMapItem(pc5);

		//Hospitals toevoegen aan de MapItemList
		world.getMapItemList().addMapItem(h1);
		world.getMapItemList().addMapItem(h2);
		world.getMapItemList().addMapItem(h3);

		//Firetrucks toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(ft1);
		world.getTimeSensitiveList().addTimeSensitive(ft2);
		world.getTimeSensitiveList().addTimeSensitive(ft3);
		world.getTimeSensitiveList().addTimeSensitive(ft4);
		world.getTimeSensitiveList().addTimeSensitive(ft5);

		//Ambulances toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(am1);
		world.getTimeSensitiveList().addTimeSensitive(am2);
		world.getTimeSensitiveList().addTimeSensitive(am3);

		//Policars toevoegen aan de TimeSensitiveList
		world.getTimeSensitiveList().addTimeSensitive(pc1);
		world.getTimeSensitiveList().addTimeSensitive(pc2);
		world.getTimeSensitiveList().addTimeSensitive(pc3);
		world.getTimeSensitiveList().addTimeSensitive(pc4);

		//Alle Emergencies toevoegen aan de World
		cec.addCreatedEmergencyToTheWorld(f1);
		cec.addCreatedEmergencyToTheWorld(f2);

		duec.dispatchToEmergency(f1, duec.getUnitsByPolicy(f1));
		duec.dispatchToEmergency(f2, duec.getUnitsByPolicy(f2));

		tac.doTimeAheadAction(10000000);

		for (Unit u : f1.getWorkingUnits()) {
			if (u instanceof Ambulance) {
				shc.selectHospital((Ambulance) u, h1);
			}
		}
		for (Unit u : f2.getWorkingUnits()) {
			if (u instanceof Ambulance) {
				shc.selectHospital((Ambulance) u, h1);
			}
		}

		tac.doTimeAheadAction(10000000);

		for (Unit u : f1.getWorkingUnits()) {
			eotc.indicateEndOfTask(u);
		}
		for (Unit u : f2.getWorkingUnits()) {
			eotc.indicateEndOfTask(u);
		}

	}

	private int checkAantalUnits(TypeUnitValidator tuv, Set<Unit> units) {
		int counter = 0;
		for (Unit u : units) {
			if (tuv.isValid(u)) {
				counter++;
			}
		}
		return counter;
	}

	private List<Policecar> getPolicecars(Set<Unit> units) throws InvalidClassException {
		TypeUnitValidator tuv = new TypeUnitValidator(Policecar.class);
		List<Policecar> policecars = new ArrayList<Policecar>();
		for (Unit u : units) {
			if (tuv.isValid(u)) {
				policecars.add((Policecar) u);
			}
		}
		return policecars;
	}

	private List<Firetruck> getFiretrucks(Set<Unit> units) throws InvalidClassException {
		TypeUnitValidator tuv = new TypeUnitValidator(Firetruck.class);
		List<Firetruck> firetrucks = new ArrayList<Firetruck>();
		for (Unit u : units) {
			if (tuv.isValid(u)) {
				firetrucks.add((Firetruck) u);
			}
		}
		return firetrucks;
	}

	private List<Firetruck> getFiretrucks(List<Unit> units) throws InvalidClassException {
		TypeUnitValidator tuv = new TypeUnitValidator(Firetruck.class);
		List<Firetruck> firetrucks = new ArrayList<Firetruck>();
		for (Unit u : units) {
			if (tuv.isValid(u)) {
				firetrucks.add((Firetruck) u);
			}
		}
		return firetrucks;
	}
}