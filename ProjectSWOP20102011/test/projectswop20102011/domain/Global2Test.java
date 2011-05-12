package projectswop20102011.domain;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import projectswop20102011.World;
import projectswop20102011.controllers.CreateEmergencyController;
import projectswop20102011.controllers.DispatchUnitsController;
import projectswop20102011.controllers.EndOfTaskController;
import projectswop20102011.controllers.InspectEmergenciesController;
import projectswop20102011.controllers.SelectHospitalController;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidDurationException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFinishJobException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class Global2Test {

	World world;
	InspectEmergenciesController iec;
	CreateEmergencyController cec;
	DispatchUnitsController duc;
	SelectHospitalController shc;
	EndOfTaskController eotc;

	@Before
	public void setUp() throws InvalidLocationException, InvalidMapItemNameException, InvalidWorldException {
		world = new World();
		iec = new InspectEmergenciesController(world);
		cec = new CreateEmergencyController(world);
		duc = new DispatchUnitsController(world);
		shc = new SelectHospitalController(world);
		eotc = new EndOfTaskController(world);
	}

	@Test
	public void testFinishedJob() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidEmergencyStatusException, InvalidEmergencyException, InvalidDurationException, InvalidUnitException, InvalidFinishJobException, InvalidAmbulanceException, InvalidHospitalException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException {
		Firetruck ft = new Firetruck("tettn", new GPSCoordinate(98,9), 100, 1001);
		Ambulance am1 = new Ambulance("gn tettn", new GPSCoordinate(9,98), 100);
		Ambulance am2 = new Ambulance("gn tettn", new GPSCoordinate(9,98), 100);
		Ambulance am3 = new Ambulance("gn tettn", new GPSCoordinate(9,98), 100);
		Hospital ho = new Hospital("water",new GPSCoordinate(57,68));

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
				EmergencySeverity.SERIOUS,
				"local",
				FireSize.LOCAL,
				false,
				0,
				3);

		cec.addCreatedEmergencyToTheWorld(f);

		// Assign the unit suggestion to the emergency
		duc.dispatchToEmergency(f, duc.getUnitsByPolicy(f));

		System.out.println("working units after assignation " + f.getWorkingUnits().size());

		// Advance time with 15 minutes
		world.getTimeSensitiveList().timeAhead(90000);
		world.setTime(world.getTime() + 90000);

		// Check the unit state of all involved units
		ArrayList<Unit> units = f.getWorkingUnits();
		ArrayList<Ambulance> ambulances = new ArrayList<Ambulance>();
		for(Unit u:units){
			if(u.getClass().getSimpleName().equalsIgnoreCase("ambulance")){
				ambulances.add((Ambulance) u);
			}
		}

		Hospital h = shc.getHospitalList(ambulances.get(0)).get(0);

		for(Ambulance a:ambulances){
			shc.selectHospital(a,h);
		}

		// Advance time with 32 hours
		world.getTimeSensitiveList().timeAhead(115200);
		world.setTime(world.getTime() + 115200);

		// Indicate end of task for all units
		for(Unit u:f.getWorkingUnits()){
			System.out.println(u.getClass());
			eotc.indicateEndOfTask(u);
		}

		System.out.println(f.getWorkingUnits().size());
		System.out.println(f.getStatus());

	}

}





