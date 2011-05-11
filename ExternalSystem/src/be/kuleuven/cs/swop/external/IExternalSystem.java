package be.kuleuven.cs.swop.external;

import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;

/**
 * This interface defines operations provided by the external system
 * and describes how the external system expects them to be used. All
 * of this expected functionality should be provided!
 * 
 * @author philippe
 *
 */
public interface IExternalSystem {
	
	/**
	 * Notifies the external system of a time change in the Emergency Dispatch
	 * 	System. The new timestamp is provided. This operation can cause the external
	 * 	system to start execution certain actions, using the previously registered
	 * 	IEmergencyDispatchApi.
	 * @param time The new time of the Emergency Dispatch System
	 * @throws ExternalSystemException When no IEmergencyDispatchApi has been registered
	 * 	within the system
	 * @throws IllegalArgumentException When an incorrect timestamp is provided
	 */
	public void notifyTimeChanged(ITime time) throws ExternalSystemException, IllegalArgumentException;
	
	/**
	 * Notifies the external system of a unit assignment to an emergency or disaster. The
	 * 	assigned unit and targeted emergency/disaster are provided.
	 * @param unit The unit that is assigned
	 * @param event The emergency/disaster the unit is assigned to
	 * @throws ExternalSystemException When no IEmergencyDispatchApi has been registered
	 * 	within the system
	 * @throws IllegalArgumentException When an incorrect unit or event is provided
	 */
	public void notifyAssignment(IUnit unit, IEmergency event) throws ExternalSystemException, IllegalArgumentException;
	
	/**
	 * Notifies the external system of a unit release fromo an emergency or disaster. This occurs
	 * 	when the unit completes a task or is withdrawn from a task. The assigned unit and targeted 
	 *  emergency/disaster are provided.
	 * @param unit The unit that is released
	 * @param event The emergency/disaster the unit is released from
	 * @throws ExternalSystemException When no IEmergencyDispatchApi has been registered
	 * 	within the system
	 * @throws IllegalArgumentException When an incorrect unit or event is provided
	 */
	public void notifyRelease(IUnit unit, IEmergency event) throws ExternalSystemException, IllegalArgumentException;
	
}
