package projectswop20102011;

import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.external.ExternalSystem;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.World;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.lists.EmergencyFactoryList;
import projectswop20102011.domain.lists.ParserList;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.externalsystem.EmergencyDispatchApi;
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
	private DispatchUnitsController duc;
	private EndOfTaskController eotc;
	private TimeAheadController tac;
    private SelectHospitalController shc;

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
        IEmergencyDispatchApi api = new EmergencyDispatchApi(world);
        world.setIEmergencyDispatchApi(api);
        cec = new CreateEmergencyController(world);
        iec = new InspectEmergenciesController(world);
        redc = new ReadEnvironmentDataController(world);
        duc = new DispatchUnitsController(world);
        eotc = new EndOfTaskController(world);
        tac = new TimeAheadController(world, ExternalSystem.bootstrap(api));
        shc = new SelectHospitalController(world);
    }

    @Test
    public void testScenario1() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemTypeNameException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException, InvalidEmergencyException, InvalidEmergencyStatusException, Exception {
        //initialize world
        redc.addFiretruck("engine1", new GPSCoordinate(0, 20), 5, FireSize.HOUSE);
        redc.addFiretruck("engine2", new GPSCoordinate(0, 21), 5, FireSize.HOUSE);
        redc.addFiretruck("engine3", new GPSCoordinate(0, 22), 5, FireSize.LOCAL);
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
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED).length);
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
        //adding fire
        cec.createFireEmergency(new GPSCoordinate(0, 0), EmergencySeverity.BENIGN, "geïmplodeerde tv", FireSize.LOCAL, false, 0, 0);
        //inspecting emergencies
        Emergency[] rbu_em = iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED);
        assertEquals(1, rbu_em.length);
        Emergency emergency = rbu_em[0];
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
        assertFalse(emergency.isPartiallyAssigned());
        //assign engine1 to the fire
        List<Unit> units = duc.getAvailableUnitsSorted(rbu_em[0]);
        Firetruck engine1 = (Firetruck) units.get(0);
        Firetruck engine2 = (Firetruck) units.get(1);
        Firetruck engine3 = (Firetruck) units.get(2);
        Policecar unit1 = (Policecar) units.get(3);
        Ambulance ambulance1 = (Ambulance) units.get(4);
        Ambulance ambulance2 = (Ambulance) units.get(5);
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
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED).length);
        assertEquals(1, iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
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
        tac.doTimeAheadAction(14400);
        eotc.indicateEndOfTask(engine1);
        //inspect emergencies
        assertFalse(emergency.isPartiallyAssigned());
        assertEquals(5, iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED).length);
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(1, iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
        //adding fire
        cec.createFireEmergency(new GPSCoordinate(0, 0), EmergencySeverity.BENIGN, "terrorist maakt METH", FireSize.HOUSE, false, 0, 1);
        //inspect emergencies
        rbu_em = iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED);
        assertEquals(6, rbu_em.length);
        Fire fire = null;
        for (int i = 0; i < rbu_em.length; i++) {
            if (rbu_em[i].getDescription().equals("terrorist maakt METH")) {
                fire = (Fire) rbu_em[i];
                break;
            }
        }
        assertFalse(fire.isPartiallyAssigned());
        try {
            assign_units.clear();
            assign_units.add(engine3);
            duc.dispatchToEmergency(fire, assign_units);
            fail("can not assign a firetruck who can't handle the fire");
        } catch (Exception e) {
        }
        try {
            assign_units.clear();
            assign_units.add(ambulance1);
            assign_units.add(engine1);
            assign_units.add(ambulance2);
            duc.dispatchToEmergency(fire, assign_units);
            fail("can not assign too much units to an emergency");
        } catch (Exception e) {
        }
        assign_units.clear();
        assign_units.add(engine2);
        assign_units.add(ambulance1);
        assign_units.add(unit1);
        duc.dispatchToEmergency(fire, assign_units);
        assertTrue(fire.isPartiallyAssigned());
        Hospital hospital1 = shc.getHospitalList(ambulance1).get(0);
        try {
            shc.selectHospital(ambulance2, hospital1);
            fail("ambulances can't select a hospital when they aren't assigned.");
        }
        catch(Exception e) {}
        try {
            shc.selectHospital(ambulance2, null);
            fail("ambulances can't select a hospital when they aren't assigned.");
        }
        catch(Exception e) {}
        try {
            shc.selectHospital(ambulance1, hospital1);
            fail("ambulances can't select a hospital when they aren't at the location of the emergency.");
        }
        catch(Exception e) {}
        try {
            eotc.indicateEndOfTask(ambulance2);
            fail("units who don't have jobs can't finish work.");
        }
        catch(Exception e) {}
        try {
            eotc.indicateEndOfTask(ambulance1);
            fail("units who aren't at the emergency can't finish their job.");
        }
        catch(Exception e) {}
        tac.doTimeAheadAction(25000);
        eotc.indicateEndOfTask(engine2);
        eotc.indicateEndOfTask(unit1);
        assertTrue(fire.isPartiallyAssigned());
        //inspect emergencies
        assertEquals(5, iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED).length);
        assertEquals(1, iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(1, iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
        shc.selectHospital(ambulance1, hospital1);
        Set<Unit> policy_units = duc.getUnitsByPolicy(fire);
        assertEquals(1, policy_units.size());
        assertEquals(engine2,policy_units.toArray()[0]);
        duc.dispatchToEmergency(fire, policy_units);
        assertFalse(fire.isPartiallyAssigned());
        try {
            eotc.indicateEndOfTask(ambulance1);
            fail("ambulance must be at hospital.");
        }
        catch(Exception e) {}
        tac.doTimeAheadAction(18000);
        eotc.indicateEndOfTask(engine2);
        eotc.indicateEndOfTask(ambulance1);
        //inspect emergencies
        assertFalse(fire.isPartiallyAssigned());
        assertEquals(5, iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED).length);
        assertEquals(0, iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(2, iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
    }
}