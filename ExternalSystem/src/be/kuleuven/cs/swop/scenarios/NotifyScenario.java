package be.kuleuven.cs.swop.scenarios;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.Severity;
import be.kuleuven.cs.swop.events.Fire;
import be.kuleuven.cs.swop.events.Location;
import be.kuleuven.cs.swop.events.Time;
import be.kuleuven.cs.swop.external.AbstractScenario;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.logging.Logger;

public class NotifyScenario extends AbstractScenario {

	public NotifyScenario(IEmergencyDispatchApi api, Logger logger) {
		super("N3", api, logger);
	}

	@Override
	protected void runScenario() throws EmergencyDispatchException {
		addEvent(new Fire(
				new Time(0,10),
				new Location(30,50),
				Severity.SERIOUS,
				"local",
				false,
				0,
				1				
		));
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
