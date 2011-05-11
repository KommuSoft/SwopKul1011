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

public class SuperScenario extends AbstractScenario {

	private int counter = 0;

	public SuperScenario(IEmergencyDispatchApi api, Logger logger) {
		super("SS", api, logger);
	}

	@Override
	protected void runScenario() throws EmergencyDispatchException {
		// Load the environment
		loadEnvironment("environment.dat");

		// Register a new event
		addEvent(new Fire(
				new Time(0, 10),
				new Location(30, 50),
				Severity.SERIOUS,
				"local",
				false,
				0,
				1));

		// Check registration
		List<IEmergency> ems1 = getApi().getListOfEmergencies(EmergencyState.UNHANDLED);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of unhandled emergencies (" + ems1.size() + ", expected 1)");
		}
		ems1 = getApi().getListOfEmergencies(EmergencyState.ANY);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of emergencies (" + ems1.size() + ", expected 1)");
		}

		// Assign the unit suggestion to the emergency
		IEmergency em1 = ems1.get(0);
		IUnitConfiguration cfg1 = getApi().getUnitConfiguration(em1);
		getApi().assignUnits(cfg1);

		// Check the unit state of all involved units
		ems1 = getApi().getListOfEmergencies(EmergencyState.RESPONDED);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of responded emergencies (" + ems1.size() + ", expected 1)");
		}
		ems1 = getApi().getListOfEmergencies(EmergencyState.ANY);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of emergencies (" + ems1.size() + ", expected 1)");
		}
		for (IUnit u : ems1.get(0).getAssignedUnits()) {
			if (u.getState() != UnitState.ASSIGNED) {
				getLogger().error("Unexpected unit state (" + u.getName() + "): " + u.getState() + " instead of ASSIGNED");
			} else {
				// Check if unit is an ambulance
				int counter = 0;
				if (u instanceof IAmbulanceView) {
					++counter;
					List<IHospital> h = getApi().getListOfHospitals();
					if (h.size() != 1) {
						getLogger().fatal("Unexpected number of hospitals (" + h.size() + ", expected 1)");
					}

					// Select hospital
					getApi().selectHospital(u, h.get(0));
				}
				getLogger().info("Number of ambulances = " + counter);
			}
		}

		// Advance time with 15 minutes
		advanceTime(new Time(0, 15));

		// Register a new event
		addEvent(new Robbery(
				new Time(0, 20),
				new Location(65, 13),
				Severity.URGENT,
				true,
				true));

		// Check registration
		ems1 = getApi().getListOfEmergencies(EmergencyState.UNHANDLED);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of unhandled emergencies (" + ems1.size() + ", expected 1)");
		}
		ems1 = getApi().getListOfEmergencies(EmergencyState.ANY);
		if (ems1.size() != 2) {
			getLogger().fatal("Unexpected number of emergencies (" + ems1.size() + ", expected 2)");
		}

		// Assign a custom unit configuration
		IEmergency em2 = getApi().getListOfEmergencies(EmergencyState.UNHANDLED).get(0);
		IUnitConfiguration cfg2 = getApi().getUnitConfiguration(em2);
		//TODO custom
		getApi().assignUnits(cfg2);


		// Check the unit state of all involved units
		ems1 = getApi().getListOfEmergencies(EmergencyState.RESPONDED);
		if (ems1.size() != 2) {
			getLogger().fatal("Unexpected number of responded emergencies (" + ems1.size() + ", expected 2)");
		}
		ems1 = getApi().getListOfEmergencies(EmergencyState.ANY);
		if (ems1.size() != 2) {
			getLogger().fatal("Unexpected number of emergencies (" + ems1.size() + ", expected 2)");
		}
		for (IUnit u : ems1.get(1).getAssignedUnits()) {
			if (u.getState() != UnitState.ASSIGNED) {
				getLogger().error("Unexpected unit state (" + u.getName() + "): " + u.getState() + " instead of ASSIGNED");
			} else {
				// Check if unit is an ambulance
				int counter = 0;
				if (u instanceof IAmbulanceView) {
					++counter;
					List<IHospital> h = getApi().getListOfHospitals();
					if (h.size() != 1) {
						getLogger().fatal("Unexpected number of hospitals (" + h.size() + ", expected 1)");
					}

					// Select hospital
					getApi().selectHospital(u, h.get(0));
				}
				getLogger().info("Number of ambulances = " + counter);
			}
		}

		// Advance time with 32 hours
		advanceTime(new Time(32, 0));

		// Indicate end of task for all units
		List<IEmergency> ems = getApi().getListOfEmergencies(EmergencyState.RESPONDED);
		for (IEmergency e : ems) {
			for (IUnit u : e.getAssignedUnits()) {
				getApi().indicateEndOfTask(u, e);
			}
		}

		// Check state units
		List<IUnit> units = getApi().getListOfUnits(UnitState.ANY);
		for (IUnit unit : units) {
			if (!unit.getState().equals(UnitState.IDLE)) {
				getLogger().error(unit + " has state " + unit.getState() + " this should be " + UnitState.IDLE);
			}
		}

		// Check number of unhandled emergencies
		int count = getApi().getListOfEmergencies(EmergencyState.UNHANDLED).size();
		if (count > 0) {
			getLogger().error("Unexpected number of unhandled emergencies: " + count + ", expected 0");
		}
		// Check number of responded emergencies
		count = getApi().getListOfEmergencies(EmergencyState.RESPONDED).size();
		if (count > 0) {
			getLogger().error("Unexpected number of responded emergencies: " + count + ", expected 0");
		}
		// Check number of completed emergencies
		count = getApi().getListOfEmergencies(EmergencyState.COMPLETED).size();
		if (count > 0) {
			getLogger().error("Unexpected number of responded emergencies: " + count + ", expected 2");
		}
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
