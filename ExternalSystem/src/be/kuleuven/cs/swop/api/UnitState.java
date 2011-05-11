package be.kuleuven.cs.swop.api;

/**
 * This enum represents the different states a unit can be in. These
 *  states are used by the IEmergencyDispatchAPI and should be understood
 *  by the Emergency Dispatch System.
 * 
 * @author philippe
 */
public enum UnitState {
	
	/**
	 * Represents any unit state.
	 */
	ANY,
	
	/**
	 * Represents the state where a unit is idle, which means it is not
	 * 	assigned to an emergency, but available to receive new tasks.
	 */
	IDLE,
	
	/**
	 * Represents the state where a unit is assigned to an emergency.
	 */
	ASSIGNED,
	
	/**
	 * Represents the state where an ambulance is assigned to an emergency,
	 * 	but is currently carrying a victim.
	 */
	OCCUPIED,
	
	/**
	 * Respresents the state where a unit is broken and cannot handle
	 * 	any new tasks.
	 */
	BROKEN
	
}
