package be.kuleuven.cs.swop.scenarios;

import java.util.List;

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

/**
 * This event generator produces a simple example scenario for the third iteration containing the following events:<ul>
 * <li>00h 10m	(30,50)		serious 	fire				size=local,chemical=false,trappedPeople=0,numberOfInjured=1</li>
 * <li>Request a suggestion to deal with the fire and use the suggestion</li>
 * <li>Advance time with 15 minutes</li>		
 * <li>Select a hospital for the ambulance</li>
 * <li>00h 20m	(65,13)		urgent		robbery				armed=true,inProgress=true</li>
 * <li>Propose an own solution to deal with the emergency</li>
 * <li>Advance time with 32 hours</li>
 * <li>Indicate end of task for all units</li>
 * </ul>
 * 
 * @author philippe
 *
 */
public class ThirdIterationScenario extends AbstractScenario {

	/**
	 * Creates a new instance of the simple scenario for IT3
	 * @param api The implementation of the API to be used for interaction
	 * 	with the emergency system
	 * @param logger The logger used for output
	 */
	public ThirdIterationScenario(IEmergencyDispatchApi api, Logger logger) {
		super("IT3", api, logger);
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

	@Override
	protected void runScenario() throws EmergencyDispatchException {
		// Load the environment
		loadEnvironment("thirditeration.dat");

		// Register a new event
		addEvent(new Fire(
				new Time(0, 10),
				new Location(30, 50),
				Severity.SERIOUS,
				"local",
				false,
				0,
				1));

		// Assign the unit suggestion to the emergency
		List<IEmergency> ems1 = getApi().getListOfEmergencies(EmergencyState.UNHANDLED);
		if (ems1.size() != 1) {
			getLogger().fatal("Unexpected number of emergencies (" + ems1.size() + ", expected 1)");
		}
		IEmergency em1 = ems1.get(0);
		IUnitConfiguration cfg1 = getApi().getUnitConfiguration(em1);
		getApi().assignUnits(cfg1);

		// Advance time with 15 minutes
		advanceTime(new Time(0, 15));

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

		// Register a new event
		addEvent(new Robbery(
				new Time(0, 20),
				new Location(65, 13),
				Severity.URGENT,
				true,
				true));

		// Assign a custom unit configuration
		List<IEmergency> ems2 = getApi().getListOfEmergencies(EmergencyState.UNHANDLED);
		if (ems2.size() != 1) {
			getLogger().fatal("Unexpected number of emergencies (" + ems2.size() + ", expected 1)");
		}
		IEmergency em2 = ems2.get(0);
		//TODO make this a custom selection by dispatcher
		IUnitConfiguration cfg2 = getApi().getUnitConfiguration(em2);
		getApi().assignUnits(cfg2);

		// Advance time with 32 hours
		advanceTime(new Time(32, 0));

		// Indicate end of task for all units
		List<IEmergency> ems = getApi().getListOfEmergencies(EmergencyState.RESPONDED);
		if (ems.size() != 2) {
			getLogger().fatal("Unexpected number of emergencies (" + ems.size() + ", expected 2)");
		}
		for (IEmergency e : ems) {
			for (IUnit u : e.getAssignedUnits()) {
				getApi().indicateEndOfTask(u, e);
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
	}
}
