package be.kuleuven.cs.swop.scenarios;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.EmergencyState;
import be.kuleuven.cs.swop.api.IAmbulanceView;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IHospital;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.IUnitConfiguration;
import be.kuleuven.cs.swop.api.Severity;
import be.kuleuven.cs.swop.api.UnitState;
import be.kuleuven.cs.swop.events.Fire;
import be.kuleuven.cs.swop.events.Location;
import be.kuleuven.cs.swop.events.Robbery;
import be.kuleuven.cs.swop.events.Time;
import be.kuleuven.cs.swop.external.AbstractScenario;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.logging.Logger;
import java.util.List;

public class TestScenario extends AbstractScenario {

	private int counter = 0;

	public TestScenario(IEmergencyDispatchApi api, Logger logger) {
		super("TS", api, logger);
	}

	@Override
	protected void runScenario() throws EmergencyDispatchException {
		// Load the environment
		loadEnvironment("thirditeration.dat");
		printDebugStatements();

		// Register a new event
		addEvent(new Fire(
				new Time(0, 10),
				new Location(30, 50),
				Severity.SERIOUS,
				"local",
				false,
				0,
				1));
		printDebugStatements();

		// Assign the unit suggestion to the emergency
		List<IEmergency> ems1 = getApi().getListOfEmergencies(EmergencyState.UNHANDLED);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of emergencies (" + ems1.size() + ", expected 1)");
		}
		ems1 = getApi().getListOfEmergencies(EmergencyState.ANY);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of emergencies (" + ems1.size() + ", expected 1)");
		}

		IEmergency em1 = ems1.get(0);
		IUnitConfiguration cfg1 = getApi().getUnitConfiguration(em1);
		getApi().assignUnits(cfg1);
		printDebugStatements();

		// Advance time with 15 minutes
		advanceTime(new Time(0, 15));
		printDebugStatements();

		// Check the unit state of all involved units
		ems1 = getApi().getListOfEmergencies(EmergencyState.RESPONDED);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of emergencies (" + ems1.size() + ", expected 1)");
		}
		for (IUnit u : ems1.get(0).getAssignedUnits()) {
			if (u.getState() != UnitState.ASSIGNED) {
				getLogger().error("Unexpected unit state (" + u.getName() + "): " + u.getState() + " instead of ASSIGNED");
			} else {
				// Check if unit is an ambulance
				if (u instanceof IAmbulanceView) {
					List<IHospital> h = getApi().getListOfHospitals();
					if (h.size() != 1) {
						getLogger().fatal("Unexpected number of hospitals (" + h.size() + ", expected 1)");
					}

					// Select hospital
					getApi().selectHospital(u, h.get(0));
				}
			}
		}
		printDebugStatements();

		// Register a new event
		addEvent(new Robbery(
				new Time(0, 20),
				new Location(65, 13),
				Severity.URGENT,
				true,
				true));
		printDebugStatements();

		// Assign a custom unit configuration
		List<IEmergency> ems2 = getApi().getListOfEmergencies(EmergencyState.UNHANDLED);
		if (ems2.size() != 1) {
			getLogger().fatal("Unexpected number of emergencies (" + ems2.size() + ", expected 1)");
		}
		IEmergency em2 = ems2.get(0);
		printDebugStatements();

		//TODO make this a custom selection by dispatcher
		IUnitConfiguration cfg2 = getApi().getUnitConfiguration(em2);
		getApi().assignUnits(cfg2);
		printDebugStatements();

		// Advance time with 32 hours
		advanceTime(new Time(32, 0));
		printDebugStatements();

		// Indicate end of task for all units
		List<IEmergency> ems = getApi().getListOfEmergencies(EmergencyState.RESPONDED);
		if (ems.size() != 2) {
			getLogger().fatal("Unexpected number of emergencies (" + ems.size() + ", expected 2)");
		}

		getLogger().info("Number of emergencies " + ems.size());
		for (IEmergency e : ems) {
			getLogger().info("DEBUG " + e + " " + e.getState());
			getLogger().info("DEBUG assigned units" + e.getAssignedUnits().size());
			for (IUnit u : e.getAssignedUnits()) {
				getApi().indicateEndOfTask(u, e);
			}
		}
		printDebugStatements();

		// Check number of unhandled emergencies
		int count = getApi().getListOfEmergencies(EmergencyState.UNHANDLED).size();
		if (count > 0) {
			getLogger().error("Unexpected number of unhandled emergencies: " + count + ", expected 0");
		}
		printDebugStatements();
		// Check number of responded emergencies
		count = getApi().getListOfEmergencies(EmergencyState.RESPONDED).size();
		if (count > 0) {
			getLogger().error("Unexpected number of responded emergencies: " + count + ", expected 0");
		}
		printDebugStatements();
	}

	private void printDebugStatements() throws EmergencyDispatchException {
//		List<IUnit> units = getApi().getListOfUnits(UnitState.ANY);
//		getLogger().info("DEBUG " + (++counter) + " number of units=" + units.size());
//		for (int i = 0; i < units.size(); ++i) {
//			getLogger().info("\tDEBUG " + units.get(i) + "{naam=" + units.get(i).getName() + ", state=" + units.get(i).getState() + ", location=" + units.get(i).getLocation() + ", homeLocation=" + units.get(i).getHomeLocation() + "}");
//		}
//
//		List<IHospital> hospitals = getApi().getListOfHospitals();
//		getLogger().info("DEBUG " + counter + " number of hospitals=" + hospitals.size());
//		for (int i = 0; i < hospitals.size(); ++i) {
//			getLogger().info("\tDEBUG " + hospitals.get(i) + "{naam=" + hospitals.get(i).getName() + ", location=" + hospitals.get(i).getLocation() + "}");
//		}
	}

	@Override
	public void notifyTimeChanged(ITime time) throws ExternalSystemException, IllegalArgumentException {
		if (this.getApi() == null) {
			throw new ExternalSystemException("No API registered");
		}

		getLogger().info("Registering time change: " + time.toString());
	}

	@Override
	public void notifyAssignment(IUnit unit, IEmergency event) throws ExternalSystemException, IllegalArgumentException {
		if (this.getApi() == null) {
			throw new ExternalSystemException("No API registered");
		}

		getLogger().info("Registering unit assignment: " + unit.toString() + " to " + event);
	}

	@Override
	public void notifyRelease(IUnit unit, IEmergency event) throws ExternalSystemException, IllegalArgumentException {
		if (this.getApi() == null) {
			throw new ExternalSystemException("No API registered");
		}

		getLogger().info("Registering unit release: " + unit.toString() + " from " + event);
	}
}
