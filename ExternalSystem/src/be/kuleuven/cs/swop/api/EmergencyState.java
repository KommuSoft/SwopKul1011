package be.kuleuven.cs.swop.api;

/**
 * This enum represents the different states an emergency can be in. These
 *  states are used by the IEmergencyDispatchAPI and should be understood
 *  by the Emergency Dispatch System.
 * 
 * @author philippe
 */
public enum EmergencyState {

	/**
	 * Represents any possible emergency state.
	 */
	ANY,
	
	/**
	 * Represents the state where an emergency is recorded but unhandled OR
	 * 	the state where a disaster is created but unhandled.
	 */
	UNHANDLED,
	
	/**
	 * Represents the state where an operator has assigned units to the 
	 *  emergency/disaster.
	 */
	RESPONDED,
	
	/**
	 * Represents the state where an emergency/disaster is finished successfully.
	 */
	COMPLETED,
	
	/**
	 * Represents the state where an emergency is cancelled. This state can not
	 *  occur for disasters.
	 */
	CANCELLED
	
}
