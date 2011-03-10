package projectswop20102011.scenarios;

import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.external.ExternalSystem;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.domain.lists.World;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.ReadEnvironmentDataController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.externalsystem.EmergencyDispatchApi;

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

    @Before
    public void setUp() throws InvalidWorldException {
        World world = new World();
        IEmergencyDispatchApi api = new EmergencyDispatchApi(world);
	world.setIEmergencyDispatchApi(api);
        cec = new CreateEmergencyController(world);
        iec = new InspectEmergenciesController(world);
        redc = new ReadEnvironmentDataController(world);
        duc = new DispatchUnitsController(world);
        eotc = new EndOfTaskController(world);
        tac = new TimeAheadController(world,ExternalSystem.bootstrap(api));
        System.out.println();
    }

    
    @Test
    public void testScenario1 () throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidMapItemTypeNameException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException, InvalidEmergencyException, InvalidEmergencyStatusException, Exception {
        //initialize world
        redc.addFiretruck("engine1", new GPSCoordinate(0,20), 5, FireSize.HOUSE);
        //inspecting emergencies
        assertEquals(0,iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED).length);
        assertEquals(0,iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(0,iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
        //adding fire
        cec.createFireEmergency(new GPSCoordinate(0,0), EmergencySeverity.BENIGN,"geïmplodeerde tv", FireSize.HOUSE, false, 0, 2);
        //inspecting emergencies
        Emergency[] rbu_em = iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED);
        assertEquals(1,rbu_em.length);
        assertEquals(0,iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(0,iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
        //assign engine1 to the fire
        List<Unit> units = duc.getAvailableUnitsSorted(rbu_em[0]);
        Firetruck engine1 = (Firetruck) units.get(0);
        assertEquals("engine1",engine1.getName());
        Set<Unit> assign_units = new HashSet<Unit>();
        assign_units.add(engine1);
        duc.dispatchToEmergency(rbu_em[0], assign_units);
        //inspecting emergencies
        assertEquals(0,iec.inspectEmergenciesOnStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED).length);
        assertEquals(1,iec.inspectEmergenciesOnStatus(EmergencyStatus.RESPONSE_IN_PROGRESS).length);
        assertEquals(0,iec.inspectEmergenciesOnStatus(EmergencyStatus.COMPLETED).length);
        try {
            eotc.indicateEndOfTask(engine1);
            fail("engine1 cant end of task");
        }
        catch (Exception e) {}
        tac.doTimeAheadAction(18);
        try {
            eotc.indicateEndOfTask(engine1);
            fail("engine1 cant end of task");
        }
        catch (Exception e) {}
        tac.doTimeAheadAction(14400);
        eotc.indicateEndOfTask(engine1);
    }

}