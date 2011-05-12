package be.kuleuven.cs.swop.scenarios;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.EmergencyState;
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
import be.kuleuven.cs.swop.events.Time;
import be.kuleuven.cs.swop.external.AbstractScenario;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.logging.Logger;
import java.util.List;

public class WithdrawScenario extends AbstractScenario {

	private int counter = 0;

	public WithdrawScenario(IEmergencyDispatchApi api, Logger logger) {
		super("WS", api, logger);
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

		IEmergency em1 = ems1.get(0);
		IUnitConfiguration cfg1 = getApi().getUnitConfiguration(em1);
		getApi().assignUnits(cfg1);
		printDebugStatements();

		// Advance time with 15 minutes
		advanceTime(new Time(0, 15));
		printDebugStatements();

		// Print info
		List<IEmergency> emergencies = getApi().getListOfEmergencies(EmergencyState.ANY);
		getLogger().info("List of emergencies (" + emergencies.size() + ")");
		for (int i = 0; i < emergencies.size(); ++i) {
			getLogger().info("\t" + i + ": " + emergencies.get(i));
		}

		IEmergency emergency = emergencies.get(0);
		List<IUnit> units = emergency.getAssignedUnits();
		getLogger().info("List of units (" + units.size() + ")");
		for (int i = 0; i < units.size(); ++i) {
			getLogger().info("\t" + i + ": " + units.get(i));
		}

		// Withraw unit
		IUnit unit = units.get(0);
		getApi().withdrawUnit(unit, emergency);

		// Print info
		getLogger().info("List of emergencies (" + emergencies.size() + ")");
		for (int i = 0; i < emergencies.size(); ++i) {
			getLogger().info("\t" + i + ": " + emergencies.get(i));
		}

		getLogger().info("List of units (" + units.size() + ")");
		for (int i = 0; i < units.size(); ++i) {
			getLogger().info("\t" + i + ": " + units.get(i));
		}
	}

	private void printDebugStatements() throws EmergencyDispatchException {
//		List<IUnit> units = getApi().getListOfUnits(UnitState.ANY);
//		getLogger().info("DEBUG " + (++counter) + " number of units=" + units.size());
//		for (int i = 0; i < units.size(); ++i) {
//			getLogger().info("\tDEBUG " + units.get(i) + "{naam=" + units.get(i).getName() + ", state=" + units.get(i).getState() + ", location=" + units.get(i).getLocation() + ", homeLocation=" + units.get(i).getHomeLocation() + "}");
//		}
//
//		List<IHospital> hospitals = getApi().getListOfHospitals();
//		getLogger().info("DEBUG " + (++counter) + " number of hospitals=" + hospitals.size());
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
