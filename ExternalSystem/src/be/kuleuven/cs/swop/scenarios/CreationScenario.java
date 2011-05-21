package be.kuleuven.cs.swop.scenarios;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.EmergencyState;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.Severity;
import be.kuleuven.cs.swop.events.Fire;
import be.kuleuven.cs.swop.events.Location;
import be.kuleuven.cs.swop.events.PublicDisturbance;
import be.kuleuven.cs.swop.events.Robbery;
import be.kuleuven.cs.swop.events.Time;
import be.kuleuven.cs.swop.events.TrafficAccident;
import be.kuleuven.cs.swop.external.AbstractScenario;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.logging.Logger;
import java.util.List;

public class CreationScenario extends AbstractScenario {

	/**
	 * Creates a new instance of the simple scenario
	 * @param api The implementation of the API to be used for interaction
	 * 	with the emergency system
	 * @param logger The logger used for output
	 */
	public CreationScenario(IEmergencyDispatchApi api, Logger logger) {
		super("Creation Test", api, logger);
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
		loadEnvironment("environment.dat");
		
		addEvent(new Fire(
				new Time(0, 10),
				new Location(30, 50),
				Severity.BENIGN,
				"local",
				false,
				0,
				1));

		addEvent(new Fire(
				new Time(0, 10),
				new Location(30, 50),
				Severity.URGENT,
				"house",
				false,
				0,
				1));

		addEvent(new Fire(
				new Time(0, 10),
				new Location(30, 50),
				Severity.NORMAL,
				"facility",
				false,
				0,
				1));

		addEvent(new Fire(
				new Time(0, 10),
				new Location(30, 50),
				Severity.SERIOUS,
				"facility",
				false,
				9,
				1));

		addEvent(new Robbery(
				new Time(0, 20),
				new Location(65, 13),
				Severity.URGENT,
				true,
				true));

		addEvent(new Robbery(
				new Time(0, 20),
				new Location(65, 13),
				Severity.BENIGN,
				false,
				true));

		addEvent(new PublicDisturbance(
				new Time(1, 45),
				new Location(80, 25),
				Severity.BENIGN,
				2));


		addEvent(new PublicDisturbance(
				new Time(1, 45),
				new Location(80, 25),
				Severity.BENIGN,
				20));

		addEvent(new TrafficAccident(
				new Time(3, 12),
				new Location(63, 32),
				Severity.NORMAL,
				1,
				1));

		addEvent(new TrafficAccident(
				new Time(3, 22),
				new Location(-16, -78),
				Severity.SERIOUS,
				2,
				3));
		
		List<IEmergency> em = getApi().getListOfEmergencies(EmergencyState.ANY).subList(2, 5);
		getApi().createDisaster("Disaster", em);
	}
}
