package projectswop20102011.domain;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.World;
import projectswop20102011.controllers.CreateDisasterController;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.DispatchUnitsToDisasterController;
import projectswop20102011.controllers.DispatchUnitsToEmergencyController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectDisastersController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.RemoveUnitAssignmentFromDisasterController;
import projectswop20102011.controllers.RemoveUnitAssignmentFromEmergencyController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.controllers.TimeAheadController;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.eventhandlers.NullEventHandler;
import projectswop20102011.exceptions.InvalidAddedDisasterException;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidFinishJobException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitException;
import projectswop20102011.exceptions.InvalidWithdrawalException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class Global2Test {

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
    Firetruck ft1, ft2, ft3, ft4, ft5;
    Ambulance am1, am2, am3, am4, am5, am6;
    Policecar pc1, pc2, pc3, pc4, pc5;
    Hospital h1, h2, h3;
    Fire f1, f2, f3;
    Robbery r1, r2, r3, r4;
    TrafficAccident ta1, ta2, ta3;
    PublicDisturbance pd1, pd2, pd3;

    @Before
    public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidWorldException, InvalidSpeedException, InvalidCapacityException, NumberOutOfBoundsException, InvalidFireSizeException, InvalidSendableSeverityException {
        world = new World();
        iec = new InspectEmergenciesController(world);
        cec = new CreateEmergencyController(world);
        duc = new DispatchUnitsToEmergencyController(world, new NullEventHandler());
        shc = new SelectHospitalController(world);
        eotc = new EndOfTaskController(world, new NullEventHandler());
        ruafe = new RemoveUnitAssignmentFromEmergencyController(world, new NullEventHandler());
        ruafd = new RemoveUnitAssignmentFromDisasterController(world, new NullEventHandler());
        cdc = new CreateDisasterController(world);
        dudc = new DispatchUnitsToDisasterController(world, new NullEventHandler());
        idc = new InspectDisastersController(world);
        tac = new TimeAheadController(world, null, new NullEventHandler());

        //Firetrucks aanmaken
        ft1 = new Firetruck("brandweerwagen1", new GPSCoordinate(100, 100), 10 * 3600, 1001);
        ft2 = new Firetruck("brandweerwagen2", new GPSCoordinate(200, 200), 10 * 3600, 500001);
        ft3 = new Firetruck("brandweerwagen3", new GPSCoordinate(300, 300), 10 * 3600, 95000);
        ft4 = new Firetruck("brandweerwagen4", new GPSCoordinate(400, 400), 10 * 3600, 6000);
        ft5 = new Firetruck("brandweerwagen5", new GPSCoordinate(500, 500), 10 * 3600, 42000);

        //Ambulances aanmaken
        am1 = new Ambulance("ziekenwagen1", new GPSCoordinate(600, 600), 10 * 3600);
        am2 = new Ambulance("ziekenwagen1", new GPSCoordinate(700, 700), 10 * 3600);
        am3 = new Ambulance("ziekenwagen1", new GPSCoordinate(800, 800), 10 * 3600);
        am4 = new Ambulance("ziekenwagen1", new GPSCoordinate(900, 900), 10 * 3600);
        am5 = new Ambulance("ziekenwagen1", new GPSCoordinate(1000, 1000), 10 * 3600);
        am6 = new Ambulance("ziekenwagen1", new GPSCoordinate(1100, 1100), 10 * 3600);

        //Policecars aanmaken
        pc1 = new Policecar("politiewagen1", new GPSCoordinate(1200, 1200), 10 * 3600);
        pc2 = new Policecar("politiewagen1", new GPSCoordinate(1300, 1300), 10 * 3600);
        pc3 = new Policecar("politiewagen1", new GPSCoordinate(1400, 1400), 10 * 3600);
        pc4 = new Policecar("politiewagen1", new GPSCoordinate(1500, 1500), 10 * 3600);
        pc5 = new Policecar("politiewagen1", new GPSCoordinate(1600, 1600), 10 * 3600);

        //Hospitals aanmaken
        h1 = new Hospital("UZ1", new GPSCoordinate(-5000, -5000));
        h2 = new Hospital("UZ2", new GPSCoordinate(0, 0));
        h3 = new Hospital("UZ3", new GPSCoordinate(5000, 5000));

        //Fires aanmaken
        f1 = new Fire(new GPSCoordinate(-100, -100), SendableSeverity.SERIOUS, "brand1", FireSize.LOCAL, false, 5, 1);
        f2 = new Fire(new GPSCoordinate(-200, -200), SendableSeverity.URGENT, "brand2", FireSize.HOUSE, true, 6, 6);
        f3 = new Fire(new GPSCoordinate(-300, -300), SendableSeverity.BENIGN, "brand3", FireSize.FACILITY, false, 1, 0);

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
    public void testFinishedJob() throws InvalidLocationException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyException, InvalidDurationException, InvalidUnitException, InvalidFinishJobException, InvalidAmbulanceException, InvalidHospitalException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidClassException, InvalidSendableSeverityException, InvalidSendableStatusException {
        Firetruck ft = new Firetruck("vuurwagen", new GPSCoordinate(98, 9), 100, 1001);
        Ambulance am1 = new Ambulance("ambulance1", new GPSCoordinate(9, 98), 100);
        Ambulance am2 = new Ambulance("ambulance2", new GPSCoordinate(9, 98), 100);
        Ambulance am3 = new Ambulance("ambulance3", new GPSCoordinate(9, 98), 100);
        Hospital ho = new Hospital("hospital", new GPSCoordinate(57, 68));

        world.getMapItemList().addMapItem(ft);
        world.getMapItemList().addMapItem(am1);
        world.getMapItemList().addMapItem(am2);
        world.getMapItemList().addMapItem(am3);
        world.getMapItemList().addMapItem(ho);

        world.getTimeSensitiveList().addTimeSensitive(ft);
        world.getTimeSensitiveList().addTimeSensitive(am1);
        world.getTimeSensitiveList().addTimeSensitive(am2);
        world.getTimeSensitiveList().addTimeSensitive(am3);
        // Register a new event
        Fire f = new Fire(
                new GPSCoordinate(30, 50),
                SendableSeverity.SERIOUS,
                "local",
                FireSize.LOCAL,
                false,
                0,
                3);

        cec.addCreatedEmergencyToTheWorld(f);

        // Assign the unit suggestion to the emergency
        duc.dispatchToEmergency(f, duc.getUnitsByPolicy(f));
        assertEquals(3, f.getWorkingUnits().size());

        // Advance time with 15 minutes
        world.getTimeSensitiveList().timeAhead(90000);
        world.setTime(world.getTime() + 90000, new NullEventHandler());

        // Check the unit state of all involved units
        ArrayList<Unit> units = f.getWorkingUnits();
        ArrayList<Ambulance> ambulances = new ArrayList<Ambulance>();
        for (Unit u : units) {
            if (u.getClass().getSimpleName().equalsIgnoreCase("ambulance")) {
                ambulances.add((Ambulance) u);
            }
        }

        Hospital h = shc.getHospitalList(ambulances.get(0)).get(0);

        for (Ambulance a : ambulances) {
            shc.selectHospital(a, h);
        }

        // Advance time with 32 hours
        world.getTimeSensitiveList().timeAhead(115200);
        world.setTime(world.getTime() + 115200, new NullEventHandler());

        //We checken op het verwachte aantal units
        int counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), f.getWorkingUnits());
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), f.getWorkingUnits());
        assertEquals(2, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), f.getWorkingUnits());
        assertEquals(1, counter);

        // Indicate end of task for all units
        for (Unit u : f.getWorkingUnits()) {
            eotc.indicateEndOfTask(u);
        }

        assertEquals(0, f.getWorkingUnits().size());
        assertEquals(SendableStatus.COMPLETED, f.getStatus());

    }

    @Test
    public void testExtendedFinishedJobEmergency() throws InvalidClassException, InvalidWithdrawalException, InvalidMapItemException, InvalidEmergencyException, InvalidDurationException, InvalidUnitException, InvalidFinishJobException, InvalidSendableStatusException {
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
        world.getMapItemList().addMapItem(am4);
        world.getMapItemList().addMapItem(am5);
        world.getMapItemList().addMapItem(am6);

        //Policars toevoegen aan de MapItemList
        world.getMapItemList().addMapItem(pc1);
        world.getMapItemList().addMapItem(pc2);
        world.getMapItemList().addMapItem(pc3);
        world.getMapItemList().addMapItem(pc4);
        world.getMapItemList().addMapItem(pc5);

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
        world.getTimeSensitiveList().addTimeSensitive(am4);
        world.getTimeSensitiveList().addTimeSensitive(am5);
        world.getTimeSensitiveList().addTimeSensitive(am6);

        //Policars toevoegen aan de TimeSensitiveList
        world.getTimeSensitiveList().addTimeSensitive(pc1);
        world.getTimeSensitiveList().addTimeSensitive(pc2);
        world.getTimeSensitiveList().addTimeSensitive(pc3);
        world.getTimeSensitiveList().addTimeSensitive(pc4);
        world.getTimeSensitiveList().addTimeSensitive(pc5);

        //Alle Emergencies toevoegen aan de World
        cec.addCreatedEmergencyToTheWorld(f1);
        cec.addCreatedEmergencyToTheWorld(f2);
        cec.addCreatedEmergencyToTheWorld(f3);
        cec.addCreatedEmergencyToTheWorld(r1);
        cec.addCreatedEmergencyToTheWorld(r2);
        cec.addCreatedEmergencyToTheWorld(r3);
        cec.addCreatedEmergencyToTheWorld(r4);
        cec.addCreatedEmergencyToTheWorld(ta1);
        cec.addCreatedEmergencyToTheWorld(ta2);
        cec.addCreatedEmergencyToTheWorld(ta3);
        cec.addCreatedEmergencyToTheWorld(pd1);
        cec.addCreatedEmergencyToTheWorld(pd2);
        cec.addCreatedEmergencyToTheWorld(pd3);

        //Testen of alles aan de World is toevoegd
        assertEquals(13, world.getEmergencyList().getEmergencies().size());
        assertEquals(19, world.getMapItemList().getMapItems().size());
        assertEquals(16, world.getTimeSensitiveList().getTimeSensitives().size());

        int counter;

        //Een proposal aanvragen voor robbery 4
        Set<Unit> unitsForRobbery4 = duc.getUnitsByPolicy(r4);

        //We checken op het verwachte aantal units
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsForRobbery4);
        assertEquals(3, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsForRobbery4);
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsForRobbery4);
        assertEquals(0, counter);

        //We dispatchten de units naar robbery 4
        duc.dispatchToEmergency(r4, unitsForRobbery4);
        assertEquals(3, r4.getWorkingUnits().size());
        //We withdrawen ze eens, no problems
        for (Unit u : unitsForRobbery4) {
            ruafe.withdrawUnit(u);
        }
        assertEquals(0, r4.getWorkingUnits().size());
        duc.dispatchToEmergency(r4, unitsForRobbery4);
        assertEquals(3, r4.getWorkingUnits().size());
        world.getTimeSensitiveList().timeAhead(600);
        world.setTime(world.getTime() + 600, new NullEventHandler());

        //We verwachten dat ze op de locatie van de emergency zijn toegekomen
        for (Unit u : r4.getWorkingUnits()) {
            assertTrue(u.isAtDestination());
        }

        //we proberen ze nog eens te withdrawen
        try {
            for (Unit u : unitsForRobbery4) {
                ruafe.withdrawUnit(u);
            }
            fail("De units zijn al op de plaats van de emergency");
        } catch (InvalidWithdrawalException e) {
        }

        //We beëindigen de taak van de units
        for (Unit u : r4.getWorkingUnits()) {
            eotc.indicateEndOfTask(u);
        }

        assertEquals(0, r4.getWorkingUnits().size());
        assertEquals(SendableStatus.COMPLETED, r4.getStatus());
        try {
            duc.dispatchToEmergency(r4, duc.getUnitsByPolicy(r4));
            fail("Er kon niet gedispatched worden aan een emergency die al completed is!");
        } catch (InvalidSendableStatusException e) {
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //We vragen een proposal voor de units van fire 1
        Set<Unit> unitsForFire1 = duc.getUnitsByPolicy(f1);

        //We checken op het verwachte aantal units
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsForFire1);
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsForFire1);
        assertEquals(3, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsForFire1);
        assertEquals(1, counter);

        //We dispatchen de units naar fire 1
        duc.dispatchToEmergency(f1, unitsForFire1);
        ArrayList<Unit> unitsForFire1List = this.convertSetToList(unitsForFire1);
        Set<Unit> firetrucksForFire1List = this.filterUnits(new TypeUnitValidator(Firetruck.class), unitsForFire1List);
        try {
            for (Unit u : firetrucksForFire1List) {
                ruafe.withdrawUnit(u);
            }
            fail("De unit is een firetruck, dus kan niet gewithdrawed worden");
        } catch (InvalidWithdrawalException e) {
        }
        assertEquals(4, f1.getWorkingUnits().size());
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //We vragen een proposal voor de units van fire 2
        Set<Unit> unitsForFire2 = duc.getUnitsByPolicy(f2);

        //We checken op het verwachte aantal units
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsForFire2);
        assertEquals(1, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsForFire2);
        assertEquals(3, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsForFire2);
        assertEquals(1, counter);
        //We dispatchen de units naar fire 2
        duc.dispatchToEmergency(f2, duc.getUnitsByPolicy(f2));
        assertEquals(5, f2.getWorkingUnits().size());
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //We vragen een proposal voor de units van fire 3
        Set<Unit> unitsForFire3 = duc.getUnitsByPolicy(f3);

        //We checken op het verwachte aantal units
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsForFire3);
        assertEquals(3, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsForFire3);
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsForFire3);
        assertEquals(3, counter);
        //We dispatchen de units naar fire 3
        duc.dispatchToEmergency(f3, unitsForFire3);
        assertEquals(6, f3.getWorkingUnits().size());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (Unit u : f1.getWorkingUnits()) {
            try {
                eotc.indicateEndOfTask(u);
                fail("Er kon niet ge-end-of-tasked worden als de units nog niet op de plaats van emergency zijn");
            } catch (InvalidFinishJobException e) {
            }
        }

        for (Unit u : f2.getWorkingUnits()) {
            try {
                eotc.indicateEndOfTask(u);
                fail("Er kon niet ge-end-of-tasked worden als de units nog niet op de plaats van emergency zijn");
            } catch (InvalidFinishJobException e) {
            }
        }

        for (Unit u : f3.getWorkingUnits()) {
            try {
                eotc.indicateEndOfTask(u);
                fail("Er kon niet ge-end-of-tasked worden als de units nog niet op de plaats van emergency zijn");
            } catch (InvalidFinishJobException e) {
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //We spoelen de tijd door
        world.getTimeSensitiveList().timeAhead(600);
        world.setTime(world.getTime() + 600, new NullEventHandler());

        //We verwachten dat alle units op de plaats van de emergency zijn
        for (Unit u : f1.getWorkingUnits()) {
            assertTrue(u.isAtDestination());
        }
        for (Unit u : f2.getWorkingUnits()) {
            assertTrue(u.isAtDestination());
        }
        for (Unit u : f3.getWorkingUnits()) {
            assertTrue(u.isAtDestination());
        }

        //We filteren de verschillende soorten units
        Set<Unit> ambOfFire1 = filterUnits(new TypeUnitValidator(Ambulance.class), f1.getWorkingUnits());
        ArrayList<Ambulance> ambulancesOfFire1 = convertToAmbulances(ambOfFire1);
        Set<Unit> firetrucksOfFire1 = filterUnits(new TypeUnitValidator(Firetruck.class), f1.getWorkingUnits());
        Set<Unit> policecarsOfFire1 = filterUnits(new TypeUnitValidator(Policecar.class), f1.getWorkingUnits());

        //We beeindigen de taak van de firetrucks en policecars
        endTaskOfGivenUnits(firetrucksOfFire1);
        endTaskOfGivenUnits(policecarsOfFire1);

        //We verwachten dat er enkel nog ambulances zijn en dat de emergency nog bezig is
        assertEquals(3, f1.getWorkingUnits().size());
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, f1.getStatus());

        //De ambulances kunnen nog niet ge end of tasked worden
        try {
            endTaskOfGivenUnits(ambOfFire1);
            fail("Er kon niet ge-end-of-tasked worden als de ambulances nog niet op de plaats van het hospital zijn");
        } catch (InvalidFinishJobException e) {
        }

        //Selecteer een hospitaal voor de ambulances
        for (Ambulance a : ambulancesOfFire1) {
            shc.selectHospital(a, h1);
        }

        //De ambulances kunnen niet meer gewithdrawed worden, want ze zijn al op de plaats van emergency geweest.
        try {
            for (Unit u : ambOfFire1) {
                ruafe.withdrawUnit(u);
            }
            fail("De units zijn op de plaats van de emergency");
        } catch (InvalidWithdrawalException e) {
        }

        //De ambulances kunnen nog niet ge-end-of-tasked worden omdat ze nog niet op de locatie van het hospitaal zijn
        try {
            endTaskOfGivenUnits(ambOfFire1);
            fail("Er kon niet ge-end-of-tasked worden als de ambulances nog niet op de plaats van het hospital zijn");
        } catch (InvalidFinishJobException e) {
        }

        //We spoelen de tijd door
        world.getTimeSensitiveList().timeAhead(1000);
        world.setTime(world.getTime() + 1000, new NullEventHandler());

        try {
            for (Unit u : ambOfFire1) {
                ruafe.withdrawUnit(u);
            }
            fail("De units zijn al op de plaats van de emergency geweest");
        } catch (InvalidWithdrawalException e) {
        }
        //Nu kunnen we de taak van de ambulances wel beeindigen
        endTaskOfGivenUnits(ambOfFire1);
        try {
            for (Unit u : ambOfFire1) {
                ruafe.withdrawUnit(u);
            }
            fail("De units zijn niet toegewezen aan een emergency");
        } catch (InvalidWithdrawalException e) {
        }

        //Er werken nog 0 units en de emergency is completed
        assertEquals(0, f1.getWorkingUnits().size());
        assertEquals(SendableStatus.COMPLETED, f1.getStatus());

        //Aangezien de emergency completed is, kunnen er geen units meer gestuurd worden
        try {
            duc.dispatchToEmergency(f1, duc.getUnitsByPolicy(f1));
            fail("Er kon niet gedispatched worden aan een emergency die al completed is!");
        } catch (InvalidSendableStatusException e) {
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Een proposal voor de units van fire2
        Set<Unit> unitsForFire3Assignment2 = duc.getUnitsByPolicy(f3);

        //controleren of het aantal voorgestelde units kloppen
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsForFire3Assignment2);
        assertEquals(1, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsForFire3Assignment2);
        assertEquals(1, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsForFire3Assignment2);
        assertEquals(0, counter);

        //units dispatchen naar de emergency
        duc.dispatchToEmergency(f3, unitsForFire3Assignment2);
        assertEquals(8, f3.getWorkingUnits().size());
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, f3.getStatus());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Een proposal voor de units van fire2
        Set<Unit> unitsForFire2Assignment2 = duc.getUnitsByPolicy(f2);

        //controleren of het aantal voorgestelde units kloppen
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsForFire2Assignment2);
        assertEquals(2, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsForFire2Assignment2);
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsForFire2Assignment2);
        assertEquals(0, counter);

        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, f3.getStatus());

        //Filter de working units op type
        Set<Unit> ambOfFire2 = filterUnits(new TypeUnitValidator(Ambulance.class), f2.getWorkingUnits());
        ArrayList<Ambulance> ambulancesOfFire2 = convertToAmbulances(ambOfFire2);
        Set<Unit> firetrucksOfFire2 = filterUnits(new TypeUnitValidator(Firetruck.class), f2.getWorkingUnits());
        Set<Unit> policecarsOfFire2 = filterUnits(new TypeUnitValidator(Policecar.class), f2.getWorkingUnits());

        //Laat de firetrucks en policecars stoppen met werken
        endTaskOfGivenUnits(firetrucksOfFire2);
        assertEquals(4, f2.getWorkingUnits().size());
        endTaskOfGivenUnits(policecarsOfFire2);
        assertEquals(3, f2.getWorkingUnits().size());

        //De ambulances kunnen niet stoppen
        try {
            endTaskOfGivenUnits(ambOfFire2);
            fail("Er kon niet ge-end-of-tasked worden als de ambulances nog niet op de plaats van het hospital zijn");
        } catch (InvalidFinishJobException e) {
        }

        //Selecteer een hospitaal voor de ambulances
        for (Ambulance a : ambulancesOfFire2) {
            shc.selectHospital(a, h2);
        }

        //Spoel de tijd door
        world.getTimeSensitiveList().timeAhead(1000);
        world.setTime(world.getTime() + 1000, new NullEventHandler());

        //Laat de ambulances stoppen met werken (ze worden opnieuw toegewezen omdat er nog nodig zijn)
        endTaskOfGivenUnits(ambOfFire2);
        assertEquals(3, f2.getWorkingUnits().size());
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, f2.getStatus());

        //Ze kunnen nog niet stoppen omdat ze opnieuw naar de plaats van emergency moeten
        try {
            endTaskOfGivenUnits(ambOfFire2);
            fail("Er kon niet ge-end-of-tasked worden als de ambulances nog niet op de plaats van het hospital zijn");
        } catch (InvalidFinishJobException e) {
        }

        //Spoel de tijd door
        world.getTimeSensitiveList().timeAhead(1500);
        world.setTime(world.getTime() + 1500, new NullEventHandler());

        //Selecteer een hospitaal
        for (Ambulance a : ambulancesOfFire2) {
            shc.selectHospital(a, h3);
        }

        //Ze kunnen nog niet stoppen, want ze moeten naar het hospitaal
        try {
            endTaskOfGivenUnits(ambOfFire2);
            fail("Er kon niet ge-end-of-tasked worden als de ambulances nog niet op de plaats van het hospital zijn");
        } catch (InvalidFinishJobException e) {
        }

        //Spoel de tijd door
        world.getTimeSensitiveList().timeAhead(1500);
        world.setTime(world.getTime() + 1500, new NullEventHandler());

        //Ze kunnen stoppen
        endTaskOfGivenUnits(ambOfFire2);
        assertEquals(0, f2.getWorkingUnits().size());
        assertEquals(SendableStatus.COMPLETED, f2.getStatus());

        //We verwachten dat de proposal er geen meer voorstelt
        Set<Unit> unitsForFire2Assignment3 = duc.getUnitsByPolicy(f2);
        assertEquals(0, unitsForFire2Assignment3.size());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Set<Unit> unitsForFire3Assignment3 = duc.getUnitsByPolicy(f3);
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsForFire3Assignment3);
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsForFire3Assignment3);
        assertEquals(1, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsForFire3Assignment3);
        assertEquals(0, counter);

        //Filter de working units op type
        Set<Unit> ambOfFire3 = filterUnits(new TypeUnitValidator(Ambulance.class), f3.getWorkingUnits());
        ArrayList<Ambulance> ambulancesOfFire3 = convertToAmbulances(ambOfFire3);
        Set<Unit> fOfFire3 = filterUnits(new TypeUnitValidator(Firetruck.class), f3.getWorkingUnits());
        ArrayList<Unit> firetrucksOfFire3 = convertSetToList(fOfFire3);
        Set<Unit> policecarsOfFire3 = filterUnits(new TypeUnitValidator(Policecar.class), f3.getWorkingUnits());

        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, f3.getStatus());
        endTaskOfGivenUnits(policecarsOfFire3);
        assertEquals(5, f3.getWorkingUnits().size());
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, f3.getStatus());

        //Kies een hospitaal
        for (Ambulance a : ambulancesOfFire3) {
            shc.selectHospital(a, h2);
        }

        //Spoel de tijd door
        world.getTimeSensitiveList().timeAhead(1500);
        world.setTime(world.getTime() + 1500, new NullEventHandler());

        endTaskOfGivenUnits(ambOfFire3);
        assertEquals(4, f3.getWorkingUnits().size());
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, f3.getStatus());


        try {
            eotc.indicateEndOfTask(firetrucksOfFire3.get(0));
            fail("Dit kon niet gelukt zijn omdat niet alle benodigde units van hetzelfde type toegekend zijn");
        } catch (InvalidFinishJobException e) {
        }

        try {
            eotc.indicateEndOfTask(firetrucksOfFire3.get(1));
            fail("Dit kon niet gelukt zijn omdat niet alle benodigde units van hetzelfde type toegekend zijn");
        } catch (InvalidFinishJobException e) {
        }

        try {
            eotc.indicateEndOfTask(firetrucksOfFire3.get(2));
            fail("Dit kon niet gelukt zijn omdat niet alle benodigde units van hetzelfde type toegekend zijn");

        } catch (InvalidFinishJobException e) {
        }

        try {
            eotc.indicateEndOfTask(firetrucksOfFire3.get(3));
            fail("Dit kon niet gelukt zijn omdat niet alle benodigde units van hetzelfde type toegekend zijn");
        } catch (InvalidFinishJobException e) {
        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        duc.dispatchToEmergency(f3, unitsForFire3Assignment3);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        assertEquals(5, f3.getWorkingUnits().size());
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, f3.getStatus());
        Set<Unit> unitsForFire3Assignment4 = duc.getUnitsByPolicy(f3);
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsForFire3Assignment4);
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsForFire3Assignment4);
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsForFire3Assignment4);
        assertEquals(0, counter);

        try {
            for (Unit u : unitsForFire3Assignment3) {
                eotc.indicateEndOfTask(u);
            }
            fail("Dit kon niet omdat de firetruck nog niet op de plaats van de emergency is.");
        } catch (InvalidFinishJobException e) {
        }
        try {
            eotc.indicateEndOfTask(firetrucksOfFire3.get(0));
            fail("Dit kon niet gelukt zijn omdat niet alle benodigde units van hetzelfde type op de plaats van de emergency zijn");
        } catch (InvalidFinishJobException e) {
        }

        try {
            eotc.indicateEndOfTask(firetrucksOfFire3.get(1));
            fail("Dit kon niet gelukt zijn omdat niet alle benodigde units van hetzelfde type op de plaats van de emergency zijn");
        } catch (InvalidFinishJobException e) {
        }

        try {
            eotc.indicateEndOfTask(firetrucksOfFire3.get(2));
            fail("Dit kon niet gelukt zijn omdat niet alle benodigde units van hetzelfde type op de plaats van de emergency zijn");

        } catch (InvalidFinishJobException e) {
        }

        try {
            eotc.indicateEndOfTask(firetrucksOfFire3.get(3));
            fail("Dit kon niet gelukt zijn omdat niet alle benodigde units van hetzelfde type op de plaats van de emergency zijn");
        } catch (InvalidFinishJobException e) {
        }

        world.getTimeSensitiveList().timeAhead(1500);
        world.setTime(world.getTime() + 1500, new NullEventHandler());

        endTaskOfGivenUnits(f3.getWorkingUnits());
        assertEquals(0, f3.getWorkingUnits().size());
        assertEquals(SendableStatus.COMPLETED, f3.getStatus());
    }

    @Test
    public void testCreateDisaster() throws InvalidLocationException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyException, InvalidConstraintListException, InvalidSendableSeverityException {

        //Fires aanmaken
        f1 = new Fire(new GPSCoordinate(-100, -100), SendableSeverity.SERIOUS, "brand1", FireSize.LOCAL, false, 5, 1);
        f2 = new Fire(new GPSCoordinate(-200, -200), SendableSeverity.URGENT, "brand2", FireSize.HOUSE, true, 6, 6);
        f3 = new Fire(new GPSCoordinate(-300, -300), SendableSeverity.BENIGN, "brand3", FireSize.FACILITY, false, 1, 0);

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

        //Alle Emergencies toevoegen aan de World
        cec.addCreatedEmergencyToTheWorld(f1);
        cec.addCreatedEmergencyToTheWorld(f2);
        cec.addCreatedEmergencyToTheWorld(f3);
        cec.addCreatedEmergencyToTheWorld(r1);
        cec.addCreatedEmergencyToTheWorld(r2);
        cec.addCreatedEmergencyToTheWorld(r3);
        cec.addCreatedEmergencyToTheWorld(r4);
        cec.addCreatedEmergencyToTheWorld(ta1);
        cec.addCreatedEmergencyToTheWorld(ta2);
        cec.addCreatedEmergencyToTheWorld(ta3);
        cec.addCreatedEmergencyToTheWorld(pd1);
        cec.addCreatedEmergencyToTheWorld(pd2);
        cec.addCreatedEmergencyToTheWorld(pd3);

        ArrayList<Emergency> emergenciesOfDisaster1 = new ArrayList<Emergency>(0);
        emergenciesOfDisaster1.add(r1);
        emergenciesOfDisaster1.add(r2);
        emergenciesOfDisaster1.add(r3);
        emergenciesOfDisaster1.add(r4);
        String description1 = "Disaster 1";

        ArrayList<Emergency> emergenciesOfDisaster2 = new ArrayList<Emergency>(0);
        emergenciesOfDisaster2.add(ta1);
        emergenciesOfDisaster2.add(ta2);
        emergenciesOfDisaster2.add(ta3);
        emergenciesOfDisaster2.add(r4);
        String description2 = "Disaster 2";

        ArrayList<Emergency> emergenciesOfDisaster3 = new ArrayList<Emergency>(0);
        emergenciesOfDisaster3.add(ta1);
        emergenciesOfDisaster3.add(ta2);
        emergenciesOfDisaster3.add(ta3);
        String description3 = "Disaster 3";

        ArrayList<Emergency> emergenciesOfDisaster4 = new ArrayList<Emergency>(0);
        emergenciesOfDisaster4.add(f1);
        emergenciesOfDisaster4.add(f2);
        emergenciesOfDisaster4.add(f3);
        emergenciesOfDisaster4.add(pd1);
        emergenciesOfDisaster4.add(pd2);
        emergenciesOfDisaster4.add(pd3);
        String description4 = "Disaster 4";

        cdc.createDisaster(emergenciesOfDisaster1, description1);

        try {
            cdc.createDisaster(emergenciesOfDisaster1, description1);
            fail("An emergency that is already part of a disaster can't be chosen for a disaster");
        } catch (InvalidEmergencyException e) {
        }
        try {
            cdc.createDisaster(emergenciesOfDisaster2, description2);
            fail("Some emergencies are already part of another disaster");
        } catch (InvalidEmergencyException e) {
        }
        cdc.createDisaster(emergenciesOfDisaster3, description3);
        cdc.createDisaster(emergenciesOfDisaster4, description4);

    }

    @Test
    public void testWithdrawDisaster() throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, NumberOutOfBoundsException, InvalidFireSizeException, InvalidEmergencyException, InvalidConstraintListException, InvalidWithdrawalException, InvalidMapItemException, InvalidSendableSeverityException, InvalidSendableStatusException {

        //Firetrucks aanmaken
        ft1 = new Firetruck("brandweerwagen1", new GPSCoordinate(100, 100), 10 * 3600, 1001);
        ft2 = new Firetruck("brandweerwagen2", new GPSCoordinate(200, 200), 10 * 3600, 500001);
        ft3 = new Firetruck("brandweerwagen3", new GPSCoordinate(300, 300), 10 * 3600, 95000);
        ft4 = new Firetruck("brandweerwagen4", new GPSCoordinate(400, 400), 10 * 3600, 6000);
        ft5 = new Firetruck("brandweerwagen5", new GPSCoordinate(500, 500), 10 * 3600, 42000);

        //Ambulances aanmaken
        am1 = new Ambulance("ziekenwagen1", new GPSCoordinate(600, 600), 10 * 3600);
        am2 = new Ambulance("ziekenwagen1", new GPSCoordinate(700, 700), 10 * 3600);
        am3 = new Ambulance("ziekenwagen1", new GPSCoordinate(800, 800), 10 * 3600);
        am4 = new Ambulance("ziekenwagen1", new GPSCoordinate(900, 900), 10 * 3600);
        am5 = new Ambulance("ziekenwagen1", new GPSCoordinate(1000, 1000), 10 * 3600);
        am6 = new Ambulance("ziekenwagen1", new GPSCoordinate(1100, 1100), 10 * 3600);

        //Policecars aanmaken
        pc1 = new Policecar("politiewagen1", new GPSCoordinate(1200, 1200), 10 * 3600);
        pc2 = new Policecar("politiewagen1", new GPSCoordinate(1300, 1300), 10 * 3600);
        pc3 = new Policecar("politiewagen1", new GPSCoordinate(1400, 1400), 10 * 3600);
        pc4 = new Policecar("politiewagen1", new GPSCoordinate(1500, 1500), 10 * 3600);
        pc5 = new Policecar("politiewagen1", new GPSCoordinate(1600, 1600), 10 * 3600);

        //Hospitals aanmaken
        h1 = new Hospital("UZ1", new GPSCoordinate(-5000, -5000));
        h2 = new Hospital("UZ2", new GPSCoordinate(0, 0));
        h3 = new Hospital("UZ3", new GPSCoordinate(5000, 5000));

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
        world.getMapItemList().addMapItem(am4);
        world.getMapItemList().addMapItem(am5);
        world.getMapItemList().addMapItem(am6);

        //Policars toevoegen aan de MapItemList
        world.getMapItemList().addMapItem(pc1);
        world.getMapItemList().addMapItem(pc2);
        world.getMapItemList().addMapItem(pc3);
        world.getMapItemList().addMapItem(pc4);
        world.getMapItemList().addMapItem(pc5);

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
        world.getTimeSensitiveList().addTimeSensitive(am4);
        world.getTimeSensitiveList().addTimeSensitive(am5);
        world.getTimeSensitiveList().addTimeSensitive(am6);

        //Policars toevoegen aan de TimeSensitiveList
        world.getTimeSensitiveList().addTimeSensitive(pc1);
        world.getTimeSensitiveList().addTimeSensitive(pc2);
        world.getTimeSensitiveList().addTimeSensitive(pc3);
        world.getTimeSensitiveList().addTimeSensitive(pc4);
        world.getTimeSensitiveList().addTimeSensitive(pc5);

        //Fires aanmaken
        f1 = new Fire(new GPSCoordinate(-100, -100), SendableSeverity.SERIOUS, "brand1", FireSize.LOCAL, false, 5, 1);
        f2 = new Fire(new GPSCoordinate(-200, -200), SendableSeverity.URGENT, "brand2", FireSize.HOUSE, true, 6, 6);
        f3 = new Fire(new GPSCoordinate(-300, -300), SendableSeverity.BENIGN, "brand3", FireSize.FACILITY, false, 1, 0);

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

        //Alle Emergencies toevoegen aan de World
        cec.addCreatedEmergencyToTheWorld(f1);
        cec.addCreatedEmergencyToTheWorld(f2);
        cec.addCreatedEmergencyToTheWorld(f3);
        cec.addCreatedEmergencyToTheWorld(r1);
        cec.addCreatedEmergencyToTheWorld(r2);
        cec.addCreatedEmergencyToTheWorld(r3);
        cec.addCreatedEmergencyToTheWorld(r4);
        cec.addCreatedEmergencyToTheWorld(ta1);
        cec.addCreatedEmergencyToTheWorld(ta2);
        cec.addCreatedEmergencyToTheWorld(ta3);
        cec.addCreatedEmergencyToTheWorld(pd1);
        cec.addCreatedEmergencyToTheWorld(pd2);
        cec.addCreatedEmergencyToTheWorld(pd3);


        ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
        emergencies.add(r1);
        emergencies.add(r2);
        Disaster disaster = new Disaster(emergencies, "Veel brand");

        Set<Unit> units = dudc.getUnitsByPolicy(disaster);
        cdc.addCreatedDisasterToTheWorld(disaster);
        dudc.dispatchToDisaster(disaster, units);

        ruafd.withdrawUnit(units.iterator().next());
    }

    @Test
    public void testExtendedFinishedJobForDisaster() throws InvalidEmergencyException, InvalidConstraintListException, InvalidAddedDisasterException, InvalidClassException, InvalidDurationException, InvalidWithdrawalException, InvalidSendableStatusException, InvalidMapItemException {

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
        world.getMapItemList().addMapItem(am4);
        world.getMapItemList().addMapItem(am5);
        world.getMapItemList().addMapItem(am6);

        //Policars toevoegen aan de MapItemList
        world.getMapItemList().addMapItem(pc1);
        world.getMapItemList().addMapItem(pc2);
        world.getMapItemList().addMapItem(pc3);
        world.getMapItemList().addMapItem(pc4);
        world.getMapItemList().addMapItem(pc5);

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
        world.getTimeSensitiveList().addTimeSensitive(am4);
        world.getTimeSensitiveList().addTimeSensitive(am5);
        world.getTimeSensitiveList().addTimeSensitive(am6);

        //Policars toevoegen aan de TimeSensitiveList
        world.getTimeSensitiveList().addTimeSensitive(pc1);
        world.getTimeSensitiveList().addTimeSensitive(pc2);
        world.getTimeSensitiveList().addTimeSensitive(pc3);
        world.getTimeSensitiveList().addTimeSensitive(pc4);
        world.getTimeSensitiveList().addTimeSensitive(pc5);

        //Alle Emergencies toevoegen aan de World
        cec.addCreatedEmergencyToTheWorld(f1);
        cec.addCreatedEmergencyToTheWorld(f2);
        cec.addCreatedEmergencyToTheWorld(f3);
        cec.addCreatedEmergencyToTheWorld(r1);
        cec.addCreatedEmergencyToTheWorld(r2);
        cec.addCreatedEmergencyToTheWorld(r3);
        cec.addCreatedEmergencyToTheWorld(r4);
        cec.addCreatedEmergencyToTheWorld(ta1);
        cec.addCreatedEmergencyToTheWorld(ta2);
        cec.addCreatedEmergencyToTheWorld(ta3);
        cec.addCreatedEmergencyToTheWorld(pd1);
        cec.addCreatedEmergencyToTheWorld(pd2);
        cec.addCreatedEmergencyToTheWorld(pd3);

        List<Emergency> emergenciesForDisaster = new ArrayList<Emergency>(0);

        emergenciesForDisaster.add(f1);
        emergenciesForDisaster.add(f2);
        emergenciesForDisaster.add(f3);
        emergenciesForDisaster.add(r1);
        emergenciesForDisaster.add(r2);
        emergenciesForDisaster.add(r3);
        emergenciesForDisaster.add(r4);
        emergenciesForDisaster.add(ta1);
        emergenciesForDisaster.add(ta2);
        emergenciesForDisaster.add(ta3);
        emergenciesForDisaster.add(pd1);
        emergenciesForDisaster.add(pd2);
        emergenciesForDisaster.add(pd3);

        String description = "Tis de moeite";
        cdc.createDisaster(emergenciesForDisaster, description);

        Disaster[] disasters = idc.inspectDisastersOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
        assertEquals(1, disasters.length);
        Disaster disaster = disasters[0];

        Emergency[] emergencies = iec.inspectEmergenciesOnStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
        assertEquals(13, emergencies.length);
        for(int i = 0; i < emergencies.length; i++) {
            assertTrue(emergenciesForDisaster.contains(emergencies[i]));
        }
        for(Emergency e : emergenciesForDisaster) {
            boolean contains = false;
            for(int i = 0; i < emergencies.length; i++) {
                if(emergencies[i] == e) {
                    contains = true;
                    break;
                }
            }
            if(!contains) {
                fail("Bevat niet alle emergencies.");
            }
        }

        Set<Unit> unitsFromPolicy = dudc.getUnitsByPolicy(disaster);
        System.out.println("=====================================================");
        for (Unit u : unitsFromPolicy) {
            System.out.println(u);
        }
        System.out.println("=====================================================");//*/
        int counter;

        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsFromPolicy);
        assertEquals(6, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsFromPolicy);
        assertEquals(3, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsFromPolicy);
        assertEquals(5, counter);

        dudc.dispatchToDisaster(disaster, unitsFromPolicy);

        tac.doTimeAheadAction(10);
        for (Unit u : disaster.getWorkingUnits()) {
            System.out.println(u);
            assertFalse(u.isAtDestination());
        }
        System.out.println("=====================================================");//*/

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

        ruafd.withdrawUnit(am1);
        ruafd.withdrawUnit(am2);
        ruafd.withdrawUnit(am3);
        ruafd.withdrawUnit(am4);
        ruafd.withdrawUnit(am5);
        ruafd.withdrawUnit(am6);

        ruafd.withdrawUnit(pc1);
        ruafd.withdrawUnit(pc2);
        ruafd.withdrawUnit(pc3);
        ruafd.withdrawUnit(pc4);
        ruafd.withdrawUnit(pc5);

        List<Unit> workingUnits = disaster.getWorkingUnits();

        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), workingUnits);
        assertEquals(0, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), workingUnits);
        assertEquals(3, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), workingUnits);
        assertEquals(0, counter);

        unitsFromPolicy = dudc.getUnitsByPolicy(disaster);
        System.out.println(unitsFromPolicy.getClass());
        counter = checkAantalUnits(new TypeUnitValidator(Ambulance.class), unitsFromPolicy);
        assertEquals(6, counter);
        counter = checkAantalUnits(new TypeUnitValidator(Firetruck.class), unitsFromPolicy);
        assertEquals(0, counter);//Hier faalt de test
        counter = checkAantalUnits(new TypeUnitValidator(Policecar.class), unitsFromPolicy);
        assertEquals(5, counter);

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

    private int checkAantalUnits(TypeUnitValidator tuv, List<Unit> units) {
        int counter = 0;
        for (Unit u : units) {
            if (tuv.isValid(u)) {
                counter++;
            }
        }
        return counter;
    }

    private void endTaskOfGivenUnits(ArrayList<Unit> units) throws InvalidUnitException, InvalidFinishJobException, InvalidEmergencyException, InvalidSendableStatusException {
        for (Unit u : units) {
            eotc.indicateEndOfTask(u);
        }
    }

    private void endTaskOfGivenUnits(Set<Unit> units) throws InvalidUnitException, InvalidFinishJobException, InvalidEmergencyException, InvalidSendableStatusException {
        for (Unit u : units) {
            eotc.indicateEndOfTask(u);
        }
    }

    private Set<Unit> filterUnits(TypeUnitValidator tuv, ArrayList<Unit> units) {
        Set<Unit> filteredUnits = new HashSet<Unit>(0);
        for (Unit u : units) {
            if (tuv.isValid(u)) {
                filteredUnits.add(u);
            }
        }
        return filteredUnits;
    }

    private ArrayList<Ambulance> convertToAmbulances(Set<Unit> units) {
        ArrayList<Ambulance> ambulances = new ArrayList<Ambulance>(0);
        for (Unit u : units) {
            ambulances.add((Ambulance) u);
        }
        return ambulances;
    }

    private ArrayList<Unit> convertSetToList(Set<Unit> units) {
        ArrayList<Unit> convertedUnits = new ArrayList<Unit>(0);
        for (Unit u : units) {
            convertedUnits.add(u);
        }
        return convertedUnits;
    }
}
