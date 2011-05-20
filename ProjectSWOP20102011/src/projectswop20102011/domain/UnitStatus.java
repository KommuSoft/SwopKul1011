package projectswop20102011.domain;
/*
 * An enumeration that represents the status of a unit
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */

public enum UnitStatus {

	/**
	 * Represents any unit state.
	 */
	ANY(),

	/**
	 * Represents the state where a unit is assigned to a sendable.
	 */
	ASSIGNED(),

	/**
	 * Respresents the state where a unit is broken and cannot handle any new tasks.
	 */
	BROKEN(),
	
	/**
	 * Represents the state where a unit is idle, which means it is not assigned to a sendable, but available to receive new tasks.
	 */
	IDLE(),

	/**
	 * Represents the state where an ambulance is assigned to a sendable, but is currently carrying a victim.
	 */
	OCCUPIED();
}
