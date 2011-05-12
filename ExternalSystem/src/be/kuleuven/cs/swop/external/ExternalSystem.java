package be.kuleuven.cs.swop.external;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.external.logging.Logger;
import be.kuleuven.cs.swop.scenarios.DisasterScenario;
import be.kuleuven.cs.swop.scenarios.SimpleScenario;
import be.kuleuven.cs.swop.scenarios.SuperScenario;
import be.kuleuven.cs.swop.scenarios.TestScenario;
import be.kuleuven.cs.swop.scenarios.ThirdIterationScenario;
import be.kuleuven.cs.swop.scenarios.WithdrawScenario;

/**
 * A concrete implementation of an external system conforming the IExternalSystem interface
 * 
 * @author philippe
 *
 */
public class ExternalSystem implements IExternalSystem {

	/**
	 * A list of scenarios to run
	 */
	private List<AbstractScenario> scenarios = null;
	
	/**
	 * The scenario currently running
	 */
	private AbstractScenario current = null;
	
	/**
	 * A logger used for output
	 */
	private Logger logger = null;
	
	/**
	 * Creates a new external system with a reference to the given API. External entities should
	 * 	use the bootstrap method instead of the constructor.
	 * @param api An implementation of an API that can be used for system interaction
	 */
	private ExternalSystem(IEmergencyDispatchApi api) {
		setLogger(new Logger());
		
		setScenarios(new ArrayList<AbstractScenario>());
		getScenarios().add(new SimpleScenario(api, logger));			//werkt (12/05/11 15:31)
		getScenarios().add(new ThirdIterationScenario(api, logger));	//werkt (12/05/11 15:32)
		getScenarios().add(new TestScenario(api, logger));				//werkt (12/05/11 15:32)
		getScenarios().add(new WithdrawScenario(api, logger));
		getScenarios().add(new SuperScenario(api, logger));
		getScenarios().add(new DisasterScenario(api, logger));			//werkt (12/05/11 15:33)
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				
				getLogger().info("Starting external command loop");
				
				for(AbstractScenario s : getScenarios()) {
					setCurrent(s);
					s.run();
				}
			}}).start();
	}
	
	@Override
	public void notifyTimeChanged(ITime time) throws ExternalSystemException,
			IllegalArgumentException {
		if(getCurrent() != null) {
			getCurrent().notifyTimeChanged(time);
		}
	}
	
	@Override
	public void notifyAssignment(IUnit unit, IEmergency event)
			throws ExternalSystemException, IllegalArgumentException {
		if(getCurrent() != null) {
			getCurrent().notifyAssignment(unit, event);
		}
	}

	@Override
	public void notifyRelease(IUnit unit, IEmergency event)
			throws ExternalSystemException, IllegalArgumentException {
		if(getCurrent() != null) {
			getCurrent().notifyRelease(unit, event);
		}
	}
	

	/*
	 * Bootstrap
	 */
	
	/**
	 * Bootstraps the external system and returns an object that can be used
	 * 	to interact with the external system
	 * @param api An implementation of an API that can be used for system interaction
	 * @return an object implementing the IExternalSystem interface that can be used to interact
	 * 	with the external system
	 */
	public static IExternalSystem bootstrap(IEmergencyDispatchApi api) {
		return new ExternalSystem(api);
	}
	
	/*
	 * Getters and Setters
	 */
	
	/**
	 * Returns the list of scenarios to run
	 * @return The list of scenarios to run
	 */
	private List<AbstractScenario> getScenarios() {
		return scenarios;
	}

	/**
	 * Sets the list of scenarios to the given list
	 * @param scenarios The given list of scenarios
	 */
	private void setScenarios(List<AbstractScenario> scenarios) {
		this.scenarios = scenarios;
	}

	/**
	 * Returns the currently running scenario
	 * @return The currently running scenario
	 */
	private AbstractScenario getCurrent() {
		return current;
	}
	
	/**
	 * Sets the currently running scenario to the given scenario
	 * @param current The given currently running scenario
	 */
	private void setCurrent(AbstractScenario current) {
		this.current = current;
	}

	/**
	 * Returns the logger used for output
	 * @return The logger used for output
	 */
	private Logger getLogger() {
		return logger;
	}

	/**
	 * Sets the logger used for output
	 * @param logger The logger to be used for output
	 */
	private void setLogger(Logger logger) {
		this.logger = logger;
	}
	

}
