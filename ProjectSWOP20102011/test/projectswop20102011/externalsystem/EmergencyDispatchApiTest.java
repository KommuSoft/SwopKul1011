package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.EmergencyState;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IFireView;
import be.kuleuven.cs.swop.api.IPublicDisturbanceView;
import be.kuleuven.cs.swop.api.IRobberyView;
import be.kuleuven.cs.swop.api.ITrafficAccidentView;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.IUnitConfiguration;
import be.kuleuven.cs.swop.api.UnitState;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.lists.EmergencyFactoryList;
import projectswop20102011.domain.lists.ParserList;
import projectswop20102011.World;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.MapItem;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.domain.Robbery;
import projectswop20102011.domain.TrafficAccident;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.UnitStatus;
import projectswop20102011.exceptions.CancelEmergencyNotSupportedException;
import projectswop20102011.exceptions.IndicateProblemNotSupportedException;
import projectswop20102011.exceptions.IndicateRepairNotSupportedException;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.externalsystem.adapters.FireAdapter;
import projectswop20102011.externalsystem.adapters.PublicDisturbanceAdapter;
import projectswop20102011.externalsystem.adapters.RobberyAdapter;
import projectswop20102011.externalsystem.adapters.TimeAdapter;
import projectswop20102011.externalsystem.adapters.TrafficAccidentAdapter;
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

public class EmergencyDispatchApiTest {

	private World world;
	private EmergencyDispatchApi api;
	private IFireView fire1, fire2, fire3, fire4;
	private IRobberyView robbery1, robbery2, robbery3, robbery4;
	private IPublicDisturbanceView publicDisturbance1, publicDisturbance2, publicDisturbance3, publicDisturbance4;
	private ITrafficAccidentView trafficAccident1, trafficAccident2, trafficAccident3, trafficAccident4;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, EmergencyDispatchException {
		world = new World();
		api = new EmergencyDispatchApi(world);

		EmergencyFactoryList efl = world.getEmergencyFactoryList();
		try {
			efl.addEmergencyFactory(new FireFactory());
			efl.addEmergencyFactory(new TrafficAccidentFactory());
			efl.addEmergencyFactory(new RobberyFactory());
			efl.addEmergencyFactory(new PublicDisturbanceFactory());

		} catch (InvalidEmergencyTypeNameException ex) {
			java.util.logging.Logger.getLogger(EmergencyDispatchApiTest.class.getName()).log(Level.SEVERE, null, ex);
		}

		ParserList pl = world.getParserList();
		pl.addParser(new BooleanParser());
		pl.addParser(new EmergencySeverityParser());
		pl.addParser(new FireSizeParser());
		pl.addParser(new GPSCoordinateParser());
		pl.addParser(new IntegerParser());
		pl.addParser(new LongParser());
		pl.addParser(new StringParser());

		Fire f1, f2, f3, f4;
		Robbery r1, r2, r3, r4;
		PublicDisturbance pd1, pd2, pd3, pd4;
		TrafficAccident ta1, ta2, ta3, ta4;

		f1 = new Fire(new GPSCoordinate(10, 20), EmergencySeverity.BENIGN, "brandje", FireSize.LOCAL, false, 0, 1);
		f2 = new Fire(new GPSCoordinate(1, 20), EmergencySeverity.SERIOUS, "Brandje", FireSize.HOUSE, true, 2, 2);
		f3 = new Fire(new GPSCoordinate(10, 2), EmergencySeverity.URGENT, "brandje", FireSize.LOCAL, false, 2, 0);
		f4 = new Fire(new GPSCoordinate(-10, 20), EmergencySeverity.NORMAL, "brandje", FireSize.FACILITY, true, 0, 2);

		fire1 = new FireAdapter(f1);
		fire2 = new FireAdapter(f2, new TimeAdapter(0, 10));
		fire3 = new FireAdapter(f3, new TimeAdapter(2, 10));
		fire4 = new FireAdapter(f4, new TimeAdapter(1, 0));

		r1 = new Robbery(new GPSCoordinate(0, 10), EmergencySeverity.NORMAL, "overvalletje", true, false);
		r2 = new Robbery(new GPSCoordinate(1, 10), EmergencySeverity.SERIOUS, "overvalletje", false, true);
		r3 = new Robbery(new GPSCoordinate(3, 0), EmergencySeverity.URGENT, "overvalletje", true, true);
		r4 = new Robbery(new GPSCoordinate(-5, 10), EmergencySeverity.BENIGN, "overvalletje", false, true);

		robbery1 = new RobberyAdapter(r1);
		robbery2 = new RobberyAdapter(r2);
		robbery3 = new RobberyAdapter(r3);
		robbery4 = new RobberyAdapter(r4);

		pd1 = new PublicDisturbance(new GPSCoordinate(0, 5), EmergencySeverity.SERIOUS, "", 10);
		pd2 = new PublicDisturbance(new GPSCoordinate(0, 5), EmergencySeverity.URGENT, "", 10);
		pd3 = new PublicDisturbance(new GPSCoordinate(0, 5), EmergencySeverity.NORMAL, "", 10);
		pd4 = new PublicDisturbance(new GPSCoordinate(0, 5), EmergencySeverity.BENIGN, "", 10);

		publicDisturbance1 = new PublicDisturbanceAdapter(pd1);
		publicDisturbance2 = new PublicDisturbanceAdapter(pd2);
		publicDisturbance3 = new PublicDisturbanceAdapter(pd3);
		publicDisturbance4 = new PublicDisturbanceAdapter(pd4);

		ta1 = new TrafficAccident(new GPSCoordinate(0, -5), EmergencySeverity.BENIGN, "", 1L, 3L);
		ta2 = new TrafficAccident(new GPSCoordinate(0, -5), EmergencySeverity.NORMAL, "", 1L, 3L);
		ta3 = new TrafficAccident(new GPSCoordinate(0, -5), EmergencySeverity.SERIOUS, "", 1L, 3L);
		ta4 = new TrafficAccident(new GPSCoordinate(0, -5), EmergencySeverity.URGENT, "", 1L, 3L);

		trafficAccident1 = new TrafficAccidentAdapter(ta1);
		trafficAccident2 = new TrafficAccidentAdapter(ta2);
		trafficAccident3 = new TrafficAccidentAdapter(ta3);
		trafficAccident4 = new TrafficAccidentAdapter(ta4);

		api.loadEnvironmentWithoutClear(new File("thirditeration.dat"));
	}

	@Test
	public void testTimeAhead() throws EmergencyDispatchException {
		assertEquals(0, world.getTime());
		api.advanceTime(new TimeAdapter(0, 10));
		assertEquals(600, world.getTime());
		api.advanceTime(new TimeAdapter(1, 10));
		assertEquals(3600 + 600 + 600, world.getTime());
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testTimeAheadInvalidMinutes() throws EmergencyDispatchException {
		assertEquals(0, world.getTime());
		api.advanceTime(new TimeAdapter(0, -10));
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testTimeAheadInvalidHours() throws EmergencyDispatchException {
		assertEquals(0, world.getTime());
		api.advanceTime(new TimeAdapter(-1, 0));
	}

	@Test
	public void testClearSystem() throws EmergencyDispatchException {
		assertEquals(0, world.getTime());
		api.advanceTime(new TimeAdapter(0, 10));
		assertEquals(600, world.getTime());

		api.registerNewEvent(fire1);
		assertEquals(1, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(5, api.getListOfUnits(UnitState.ANY).size());
		assertEquals(1, api.getListOfHospitals().size());
		assertEquals(6, world.getMapItemList().toArrayList().size());
		assertEquals(1, world.getEmergencyList().toArray().length);

		api.clearSystem();
		assertEquals(0, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(0, api.getListOfUnits(UnitState.ANY).size());
		assertEquals(0, api.getListOfHospitals().size());
		assertEquals(600, world.getTime());
		assertEquals(6, world.getMapItemList().toArrayList().size());
		assertEquals(1, world.getEmergencyList().toArray().length);
	}

	@Test
	public void testRegisterNewEvent() throws EmergencyDispatchException {
		assertEquals(0, api.getListOfEmergencies(EmergencyState.ANY).size());
		api.registerNewEvent(fire1);
		api.registerNewEvent(fire2);
		api.registerNewEvent(fire3);
		api.registerNewEvent(fire4);

		api.registerNewEvent(robbery1);
		api.registerNewEvent(robbery2);
		api.registerNewEvent(robbery3);
		api.registerNewEvent(robbery4);

		api.registerNewEvent(publicDisturbance1);
		api.registerNewEvent(publicDisturbance2);
		api.registerNewEvent(publicDisturbance3);
		api.registerNewEvent(publicDisturbance4);

		api.registerNewEvent(trafficAccident1);
		api.registerNewEvent(trafficAccident2);
		api.registerNewEvent(trafficAccident3);
		api.registerNewEvent(trafficAccident4);

		//TODO: aanpassen als de timestamp toch anders werkt
//		assertEquals(13, api.getListOfEmergencies(EmergencyState.ANY));
//		api.advanceTime(new TimeAdapter(0, 10));
//		assertEquals(14, api.getListOfEmergencies(EmergencyState.ANY));
//		api.advanceTime(new TimeAdapter(3, 0));
		assertEquals(16, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(16, api.getListOfEmergencies(EmergencyState.UNHANDLED).size());
		assertEquals(16, world.getEmergencyList().toArray().length);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testRegisterNewEventInvalidWorld() throws EmergencyDispatchException {
		world = null;
		api = new EmergencyDispatchApi(world);
		api.registerNewEvent(fire1);
	}

	@Test
	public void testGetListOfEmergenciesSingleEmergency() throws EmergencyDispatchException {
		assertEquals(0, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(0, world.getEmergencyList().toArray().length);

		api.registerNewEvent(robbery1);

		assertEquals(1, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfEmergencies(EmergencyState.UNHANDLED).size());
		assertEquals(1, world.getEmergencyList().toArray().length);

		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		assertEquals(1, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfEmergencies(EmergencyState.RESPONDED).size());
		assertEquals(1, world.getEmergencyList().toArray().length);

		api.advanceTime(new TimeAdapter(10, 0));

		for (IUnit u : cfg1.getAllUnits()) {
			api.indicateEndOfTask(u, em1);
		}

		assertEquals(1, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfEmergencies(EmergencyState.COMPLETED).size());
		assertEquals(1, world.getEmergencyList().toArray().length);
	}

	@Test
	public void testGetListOfEmergenciesMultipleEmergencies() throws EmergencyDispatchException {
		assertEquals(0, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(0, world.getEmergencyList().toArray().length);

		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);

		assertEquals(1, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfEmergencies(EmergencyState.UNHANDLED).size());
		assertEquals(1, world.getEmergencyList().toArray().length);

		api.registerNewEvent(fire1);

		assertEquals(2, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(2, api.getListOfEmergencies(EmergencyState.UNHANDLED).size());
		assertEquals(2, world.getEmergencyList().toArray().length);

		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		assertEquals(2, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfEmergencies(EmergencyState.RESPONDED).size());
		assertEquals(1, api.getListOfEmergencies(EmergencyState.UNHANDLED).size());
		assertEquals(2, world.getEmergencyList().toArray().length);

		api.advanceTime(new TimeAdapter(10, 0));

		for (IUnit u : cfg1.getAllUnits()) {
			api.indicateEndOfTask(u, em1);
		}

		assertEquals(2, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfEmergencies(EmergencyState.COMPLETED).size());
		assertEquals(1, api.getListOfEmergencies(EmergencyState.UNHANDLED).size());
		assertEquals(2, world.getEmergencyList().toArray().length);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testGetListOfEmergenciesInvalidWorld() throws EmergencyDispatchException {
		world = null;
		api = new EmergencyDispatchApi(world);
		api.getListOfEmergencies(EmergencyState.ANY);
	}

	@Test
	public void testGetListOfDisasters() throws EmergencyDispatchException {
		assertEquals(0, api.getListOfDisasters(EmergencyState.ANY).size());
		assertEquals(0, api.getListOfDisasters(EmergencyState.UNHANDLED).size());
		assertEquals(0, world.getDisasterList().toArray().length);

		api.registerNewEvent(robbery1);
		api.registerNewEvent(robbery2);

		api.createDisaster("disaster", api.getListOfEmergencies(EmergencyState.ANY));

		assertEquals(2, api.getListOfDisasters(EmergencyState.ANY).size());
		assertEquals(2, api.getListOfDisasters(EmergencyState.UNHANDLED).size());
		assertEquals(1, world.getDisasterList().toArray().length);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testGetListOfDisastersInvalidWorld() throws EmergencyDispatchException {
		world = null;
		api = new EmergencyDispatchApi(world);
		api.getListOfDisasters(EmergencyState.ANY);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testAssignToDisaster() throws EmergencyDispatchException {
		api.registerNewEvent(robbery1);
		api.registerNewEvent(robbery2);

		api.createDisaster("disaster", api.getListOfEmergencies(EmergencyState.ANY));

		assertEquals(2, api.getListOfDisasters(EmergencyState.ANY).size());
		assertEquals(2, api.getListOfDisasters(EmergencyState.UNHANDLED).size());

		List<IEmergency> ems = api.getListOfEmergencies(EmergencyState.UNHANDLED);
		for (IEmergency e : ems) {
			IUnitConfiguration cfg1 = api.getUnitConfiguration(e);
			api.assignUnits(cfg1);
		}
	}

	@Test
	public void testGetListOfUnits() throws EmergencyDispatchException {
		assertEquals(5, api.getListOfUnits(UnitState.ANY).size());
		assertEquals(5, api.getListOfUnits(UnitState.IDLE).size());

		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		assertEquals(5, api.getListOfUnits(UnitState.ANY).size());
		assertEquals(4, api.getListOfUnits(UnitState.IDLE).size());
		assertEquals(1, api.getListOfUnits(UnitState.ASSIGNED).size());
	}

	@Test
	public void testGetListOfHospitals() throws EmergencyDispatchException {
		assertEquals(1, api.getListOfHospitals().size());
	}

	@Test
	public void testUnitConfiguration() throws EmergencyDispatchException {
		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		assertEquals(1, api.getUnitConfiguration(em1).getAllUnits().size());
		assertEquals(1, api.getUnitConfiguration(em1).getListOfPoliceCars().size());
	}

	@Test
	public void testSelectHospital() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);
		api.advanceTime(new TimeAdapter(10, 0));

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());

		for (IUnit u : cfg1.getListOfAmbulances()) {
			api.selectHospital(u, api.getListOfHospitals().get(0));
		}

		assertEquals(1, api.getListOfUnits(UnitState.ASSIGNED).size());
		assertEquals(1, api.getListOfUnits(UnitState.OCCUPIED).size());
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testSelectHospitalInvalidWorld() throws EmergencyDispatchException {
		world = null;
		api = new EmergencyDispatchApi(world);

		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);
		api.advanceTime(new TimeAdapter(10, 0));

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());

		for (IUnit u : cfg1.getListOfAmbulances()) {
			api.selectHospital(u, api.getListOfHospitals().get(0));
		}
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testSelectInvalidHospital() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);
		api.advanceTime(new TimeAdapter(10, 0));

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());

		for (IUnit u : cfg1.getListOfAmbulances()) {
			api.selectHospital(u, null);
		}
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testSelectHospitalInvalidUnit() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);
		api.advanceTime(new TimeAdapter(10, 0));

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());

		api.selectHospital(null, api.getListOfHospitals().get(0));
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testSelectHospitalNotAtEmergency() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());
		for (IUnit u : cfg1.getListOfAmbulances()) {
			api.selectHospital(u, api.getListOfHospitals().get(0));
		}
	}

	@Test
	public void testEndOfTask() throws EmergencyDispatchException {
		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		api.advanceTime(new TimeAdapter(10, 0));
		assertEquals(1, api.getListOfEmergencies(EmergencyState.RESPONDED).size());
		for (IUnit u : cfg1.getAllUnits()) {
			api.indicateEndOfTask(u, em1);
		}
		assertEquals(1, api.getListOfEmergencies(EmergencyState.COMPLETED).size());
		assertEquals(EmergencyState.COMPLETED, em1.getState());

		for (IUnit u : cfg1.getAllUnits()) {
			assertEquals(UnitState.IDLE, u.getState());
		}
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testEndOfTaskInvalidWorld() throws EmergencyDispatchException {
		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		api.advanceTime(new TimeAdapter(10, 0));

		world = null;
		api = new EmergencyDispatchApi(world);
		for (IUnit u : cfg1.getAllUnits()) {
			api.indicateEndOfTask(u, em1);
		}
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testEndOfTaskInvalidUnit() throws EmergencyDispatchException {
		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		api.advanceTime(new TimeAdapter(10, 0));
		api.indicateEndOfTask(null, em1);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testEndOfTaskCantFinish() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		api.advanceTime(new TimeAdapter(10, 0));
		for (IUnit u : cfg1.getAllUnits()) {
			api.indicateEndOfTask(u, em1);
		}
	}

	@Test
	public void testWithdrawUnit() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());

		api.withdrawUnit(cfg1.getListOfAmbulances().get(0), em1);

		assertEquals(1, api.getListOfUnits(UnitState.ASSIGNED).size());
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testWithdrawUnitThatWasAlreadyAtSite() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		api.advanceTime(new TimeAdapter(10, 0));
		api.withdrawUnit(cfg1.getListOfAmbulances().get(0), em1);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testWithdrawFiretruck() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());

		api.withdrawUnit(cfg1.getListOfFireTrucks().get(0), em1);

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());
	}

	@Test
	public void testAssignTo() throws EmergencyDispatchException {
		api.registerNewEvent(fire1);

		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		assertEquals(2, api.getListOfUnits(UnitState.ASSIGNED).size());
		int counter = 0;
		List<MapItem> mapItems = world.getMapItemList().toArrayList();
		for (int i = 0; i < mapItems.size(); ++i) {
			if (mapItems.get(i) instanceof Unit) {
				Unit u = (Unit) mapItems.get(i);
				if (u.getUnitStatus().equals(UnitStatus.ASSIGNED)) {
					++counter;
				}
			}
		}
		assertEquals(2, counter);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testAssignToInvalidWorld() throws EmergencyDispatchException {
		world = null;
		api = new EmergencyDispatchApi(world);

		api.registerNewEvent(fire1);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testAssignToCompletedEmergency() throws EmergencyDispatchException {
		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		api.advanceTime(new TimeAdapter(10, 0));
		for (IUnit u : cfg1.getAllUnits()) {
			api.indicateEndOfTask(u, em1);
		}

		api.assignUnits(cfg1);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testAssignToHarmsConstraint() throws EmergencyDispatchException {
		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg1 = api.getUnitConfiguration(em1);
		api.assignUnits(cfg1);

		api.assignUnits(cfg1);
	}

	@Test
	public void testCreateDisaster() throws EmergencyDispatchException {
		ArrayList<IEmergency> emergencies = new ArrayList<IEmergency>();
		emergencies.add((IEmergency) fire1);

		api.createDisaster("", emergencies);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testCreateDisasterInvalidWorld() throws EmergencyDispatchException {
		world = null;
		api = new EmergencyDispatchApi(world);
		ArrayList<IEmergency> emergencies = new ArrayList<IEmergency>();
		emergencies.add((IEmergency) fire1);

		api.createDisaster("", emergencies);
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testCreateDisasterWithNoEmergencies() throws EmergencyDispatchException {
		ArrayList<IEmergency> emergencies = new ArrayList<IEmergency>();

		api.createDisaster("", emergencies);
	}

	@Test
	public void testLoadEnvironment() throws EmergencyDispatchException {
		assertEquals(0, api.getListOfDisasters(EmergencyState.ANY).size());
		assertEquals(0, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfHospitals().size());
		assertEquals(5, api.getListOfUnits(UnitState.ANY).size());


		api.loadEnvironment(new File("thirditeration.dat"));

		assertEquals(0, api.getListOfDisasters(EmergencyState.ANY).size());
		assertEquals(0, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfHospitals().size());
		assertEquals(5, api.getListOfUnits(UnitState.ANY).size());
	}

	@Test
	public void testLoadEnvironmentWithoutClear() throws EmergencyDispatchException {
		assertEquals(0, api.getListOfDisasters(EmergencyState.ANY).size());
		assertEquals(0, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(1, api.getListOfHospitals().size());
		assertEquals(5, api.getListOfUnits(UnitState.ANY).size());


		api.loadEnvironmentWithoutClear(new File("thirditeration.dat"));

		assertEquals(0, api.getListOfDisasters(EmergencyState.ANY).size());
		assertEquals(0, api.getListOfEmergencies(EmergencyState.ANY).size());
		assertEquals(2, api.getListOfHospitals().size());
		assertEquals(10, api.getListOfUnits(UnitState.ANY).size());
	}

	@Test(expected = EmergencyDispatchException.class)
	public void testLoadEnvironmentWithoutClearInvalidWorld() throws EmergencyDispatchException {
		world = null;
		api = new EmergencyDispatchApi(world);

		api.loadEnvironmentWithoutClear(new File("thirditeration.dat"));
	}

	@Test(expected = CancelEmergencyNotSupportedException.class)
	public void testCancelEmergency() throws EmergencyDispatchException {
		api.registerNewEvent(robbery1);
		IEmergency em1 = api.getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		api.cancelEmergency(em1);
	}

	@Test(expected=IndicateProblemNotSupportedException.class)
	public void testIndicateProblem() throws EmergencyDispatchException {
		api.indicateProblem(api.getListOfUnits(UnitState.ANY).get(0));
	}

	@Test(expected=IndicateRepairNotSupportedException.class)
	public void testIndicateRepair() throws EmergencyDispatchException {
		api.indicateRepair(api.getListOfUnits(UnitState.ANY).get(0));
	}
}
