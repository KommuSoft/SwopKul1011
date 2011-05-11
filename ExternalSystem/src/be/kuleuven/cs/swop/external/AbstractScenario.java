package be.kuleuven.cs.swop.external;

import java.io.File;

import be.kuleuven.cs.swop.api.EmergencyDispatchException;
import be.kuleuven.cs.swop.api.IEmergencyDispatchApi;
import be.kuleuven.cs.swop.api.IEvent;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.external.logging.Logger;

/**
 * An abstract scenario offers easy to use methods to create scenarios interacting
 * with the emergency dispatch system
 * 
 * @author philippe
 */
public abstract class AbstractScenario implements IExternalSystem {

	/**
	 * The name of the scenario
	 */
	private String name = "";
	/**
	 * The logger used for sending output messages
	 */
	private Logger logger = null;
	/**
	 * The implementation of the API
	 */
	private IEmergencyDispatchApi api = null;

	/**
	 * Create a new abstract scenario with the given arguments
	 * @param name The name of the scenario
	 * @param api The implementation of the API to use
	 * @param logger The logger to send output to
	 */
	public AbstractScenario(String name, IEmergencyDispatchApi api, Logger logger) {
		this.setName(name);
		this.setLogger(logger);
		this.setApi(api);
	}

	/**
	 * Run the scenario. This method uses the Template Method runScenario
	 */
	public void run() {
		getLogger().info("Running Scenario: " + getName());
		try {
			runScenario();
		} catch (Exception e) {
			getLogger().error("Failed to complete scenario: " + getName() + " (" + e.getMessage() + ")");
		}
		getLogger().info("Finished Scenario: " + getName());
	}

	/**
	 * The concrete implementation of the actual scenario
	 * @throws EmergencyDispatchException If an error occurs during the execution of the scenario
	 */
	protected abstract void runScenario() throws EmergencyDispatchException;

	/**
	 * Registers a new event with the API of the emergency dispatch system
	 * @param e The event to register
	 */
	protected void addEvent(IEvent e) {
		try {
			getLogger().info("Registering new event: " + e.toString());
			getApi().registerNewEvent(e);
		} catch (EmergencyDispatchException e1) {
			getLogger().error("Failed to register event (" + e.toString() + "): " + e1.getMessage());
		}
	}

	/**
	 * Uses the API of the emergency dispatch system to advance time
	 * @param t The amount of time to advance
	 */
	protected void advanceTime(ITime t) {
		try {
			getLogger().info("Advancing time: " + t.toString());
			getApi().advanceTime(t);
		} catch (EmergencyDispatchException e1) {
			getLogger().error("Failed to advance time (" + t.toString() + "): " + e1.getMessage());
		}
	}

	/**
	 * Uses the API of the emergency dispatch system to load a new environment
	 * @param filename The filename of the environment file
	 */
	protected void loadEnvironment(String filename) {
		try {
			getLogger().info("Loading environment file: " + filename);
			getApi().loadEnvironment(new File(filename));
		} catch (EmergencyDispatchException e) {
			getLogger().error("Failed to load environment file (" + filename + "): " + e.getMessage());
		}
	}

	/*
	 * Getters and Setters
	 */
	/**
	 * Returns the name of the scenario
	 * @return The name of the scenario
	 */
	private String getName() {
		return name;
	}

	/**
	 * Sets the name of the scenario
	 * @param name The new name of the scenario
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the logger associated with this scenario
	 * @return The logger for sending output to
	 */
	protected Logger getLogger() {
		return logger;
	}

	/**
	 * Set a new logger for output messages 
	 * @param logger The new logger
	 */
	private void setLogger(Logger logger) {
		this.logger = logger;
	}

	/**
	 * Returns the API implementation to use in the scenario
	 * @return The API implementation
	 */
	protected IEmergencyDispatchApi getApi() {
		return api;
	}

	/**
	 * Sets a new API implementation
	 * @param api The new API implementation
	 */
	private void setApi(IEmergencyDispatchApi api) {
		this.api = api;
	}
}
