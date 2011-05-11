package be.kuleuven.cs.swop.scenarios;

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

/**
 * This event generator produces a simple example scenario producing the following events:<ul>
 * <li>00h 10m	(30,50)		serious 	fire				size=local,chemical=false,trappedPeople=0,numberOfInjured=1</li>
 * <li>Move time to 00h15 (+0h15m)</li>				
 * <li>00h 20m	(65,13)		urgent		robbery				armed=true,inProgress=true</li>
 * <li>Move time to 00h25 (+0h10m)</li>
 * <li>01h 45m	(80,25)		benign		publicDisturbance	numberOfPeople=2</li>
 * <li>Move time to 2h38 (+2h13m)</li>
 * <li>03h 12m	(63,32)		normal		trafficAccident		numberOfCars=1,numberOfInjured=1</li>
 * <li>Move time to 3h12 (+0h34m)</li>
 * <li>03h 22m	(-16,-78)	serious		trafficAccident		numberOfCars=2,numberOfInjured=3</li>
 * </ul>
 * 
 * @author philippe
 *
 */
public class SimpleScenario extends AbstractScenario {
	
	/**
	 * Creates a new instance of the simple scenario
	 * @param api The implementation of the API to be used for interaction
	 * 	with the emergency system
	 * @param logger The logger used for output
	 */
	public SimpleScenario(IEmergencyDispatchApi api, Logger logger) {
		super("Simple Test", api, logger);
	}
	
	@Override
	public void notifyTimeChanged(ITime time) throws ExternalSystemException,IllegalArgumentException {
		if(this.getApi() == null) throw new ExternalSystemException("No API registered");
		
		getLogger().info("Registering time change: " + time.toString());
	}
	
	@Override
	public void notifyAssignment(IUnit unit, IEmergency event)throws ExternalSystemException, IllegalArgumentException {
		if(this.getApi() == null) throw new ExternalSystemException("No API registered");
		
		getLogger().info("Registering unit assignment: " + unit.toString() + " to " + event);
	}

	@Override
	public void notifyRelease(IUnit unit, IEmergency event)throws ExternalSystemException, IllegalArgumentException {
		if(this.getApi() == null) throw new ExternalSystemException("No API registered");
		
		getLogger().info("Registering unit release: " + unit.toString() + " from " + event);
	}

	@Override
	protected void runScenario() {
		addEvent(new Fire(
				new Time(0,10),
				new Location(30,50),
				Severity.SERIOUS,
				"local",
				false,
				0,
				1				
		));
		
		advanceTime(new Time(0, 15));
		
		addEvent(new Robbery(
				new Time(0,20),
				new Location(65,13),
				Severity.URGENT,
				true,
				true				
		));
		
		advanceTime(new Time(0, 10));
		
		addEvent(new PublicDisturbance(
				new Time(1,45),
				new Location(80,25),
				Severity.BENIGN,
				2				
		));
		
		advanceTime(new Time(2, 13));
		
		addEvent(new TrafficAccident(
				new Time(3,12),
				new Location(63,32),
				Severity.NORMAL,
				1,
				1			
		));
		
		advanceTime(new Time(0, 34));
		
		addEvent(new TrafficAccident(
				new Time(3,22),
				new Location(-16,-78),
				Severity.SERIOUS,
				2,
				3			
		));
	}
	
}
