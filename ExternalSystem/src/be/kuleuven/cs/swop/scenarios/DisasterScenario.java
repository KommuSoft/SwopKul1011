package be.kuleuven.cs.swop.scenarios;

import java.util.List;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.EmergencyState;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.Severity;
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
public class DisasterScenario extends AbstractScenario {

	/**
	 * Creates a new instance of the simple scenario for IT3
	 * @param api The implementation of the API to be used for interaction
	 * 	with the emergency system
	 * @param logger The logger used for output
	 */
	public DisasterScenario(IEmergencyDispatchApi api, Logger logger) {
		super("DS", api, logger);
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

		// Register a new event
		addEvent(new Robbery(
				new Time(0, 20),
				new Location(65, 13),
				Severity.URGENT,
				true,
				true));

		List<IEmergency> iEmergencies = getApi().getListOfEmergencies(EmergencyState.ANY);
		getApi().createDisaster("Roger Vangheluwe", iEmergencies);

		List<IEmergency> emergencies = getApi().getListOfDisasters(EmergencyState.ANY);
		if(emergencies.size() != 2){
			getLogger().error("The list of emergencies in the disasters is wrong! " +  emergencies.size() + " expected 2");
		}
	}
}
