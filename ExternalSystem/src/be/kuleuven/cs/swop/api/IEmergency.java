package be.kuleuven.cs.swop.api;

import java.util.List;

/**
 * This interface offers operations to inspect the state of an emergency.
 * 
 * @author philippe
 */
public interface IEmergency {

	/**
	 * Retrieves the current state of the emergency
	 * @return The state of the emergency
	 */
	public EmergencyState getState();
	
	/**
	 * Retrieves the list of assigned units. The list is not
	 * 	expected in a specific order.
	 * @return The list of assigned units.
	 */
	public List<IUnit> getAssignedUnits();
	
	/**
	 * Indicates whether this emergency is part of a disaster
	 * 	or not.
	 * @return True if the emergency is part of a disaster, false othwerise
	 */
	public boolean isPartOfDisaster();
	
}
