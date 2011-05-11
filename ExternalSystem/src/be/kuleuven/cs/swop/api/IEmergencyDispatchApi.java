package be.kuleuven.cs.swop.api;

import java.io.File;
import java.util.List;

/**
 * This interface defines an API that should be provided by the
 * Emergency Dispatch System. All operations need to be implemented
 * according to the documentation of this interface.
 * 
 * @author philippe
 *
 */
public interface IEmergencyDispatchApi {

	/**
	 * Registers a new event with the Emergency Dispatch System, which corresponds
	 * 	to the execution of the use case "Record New Emergency". The given event
	 * 	contains all required data about the emergency.
	 * @param event The event to register with the Emergency Dispatch System
	 * @throws EmergencyDispatchException When an error occurs during the event registration
	 */
	public void registerNewEvent(IEvent event) throws EmergencyDispatchException;
	
	/**
	 * Retrieves a list of emergencies which conform to the given state. The returned emergency 
	 *  objects conform to the IEmergency interface. The objects in the list are not expected to
	 *  be in any particular order.
	 * @param state The state of the emergencies that are requested.
	 * @return The list of requested emergencies
	 * @throws EmergencyDispatchException When an error occurs during retrieval (e.g. an incorrect state value)
	 */
	public List<IEmergency> getListOfEmergencies(EmergencyState state) throws EmergencyDispatchException;
	
	/**
	 * Retrieves a list of disasters which conform to the given state. The returned disaster 
	 *  objects conform to the EmergencyState interface. The objects in the list are not expected to
	 *  be in any particular order.
	 * @param state The state of the disasters that are requested.
	 * @return The list of requested disasters
	 * @throws EmergencyDispatchException When an error occurs during retrieval (e.g. an incorrect state value)
	 */
	public List<IEmergency> getListOfDisasters(EmergencyState state) throws EmergencyDispatchException;

	/**
	 * Retrieves a list of units which conform to the given state. The returned units
	 * 	conform to the IUnit interface. The objects in the list are not expected to be in any
	 * 	particular order. 
	 * @param state The state of the units that are requested
	 * @return The list of requested units
	 * @throws EmergencyDispatchException When an error occurs during retrieval (e.g. an incorrect state value)
	 */
	public List<IUnit> getListOfUnits(UnitState state) throws EmergencyDispatchException;
	
	/**
	 * Retrieves a list of hospitals that exist within the world. The returned hospitals
	 * 	conform to the IHospital interface.
	 * @return The list of requested hospitals.
	 */
	public List<IHospital> getListOfHospitals();
	
	/**
	 * Returns a suggested unit configuration for the given emergency. The unit configuration is compiled
	 * 	according to the emergency and disaster policies.
	 * @param emergency The emergency/disaster data to compile the configuration for.
	 * @return The suggested unit configuration
	 * @throws EmergencyDispatchException When an error occurs during the compilation of the unit configuration
	 */
	public IUnitConfiguration getUnitConfiguration(IEmergency emergency) throws EmergencyDispatchException;
	
	/**
	 * Assigns the units in the unit configuration to the emergency associated with the given
	 * 	unit configuration.
	 * @param configuration The configuration of units to be assigned to the associated emergency
	 * @throws EmergencyDispatchException When the assignment of one or more units fails (e.g. wrong type, already assigned, ...)
	 */
	public void assignUnits(IUnitConfiguration configuration) throws EmergencyDispatchException;
	
	/**
	 * Assigns the given emergencies to a new disaster with the given description.
	 * @param description The description of the disaster that needs to be created.
	 * @param emergencies The emergencies that are part of the disaster.
	 * @throws EmergencyDispatchException When the creation of the disaster fails (e.g. wrong emergency state, ...)
	 */
	public void createDisaster(String description, List<IEmergency> emergencies) throws EmergencyDispatchException;
	
	/**
	 * Selects the given hospital as the destination for the given unit.
	 * @param unit The unit for which the hospital needs to be selected
	 * @param hospital The selected hospital, which will become the destination of the unit
	 * @throws EmergencyDispatchException In case the selection of the hospital fails (e.g. wrong unit type)
	 */
	public void selectHospital(IUnit unit, IHospital hospital) throws EmergencyDispatchException;
	
	/**
	 * Indicates that the given unit has completed the given task.
	 * @param unit The unit that has finished the task.
	 * @param emergency The emergency that has been handled by the unit.
	 * @throws EmergencyDispatchException When the indication fails (e.g. wrong emergency)
	 */
	public void indicateEndOfTask(IUnit unit, IEmergency emergency) throws EmergencyDispatchException;
	
	/**
	 * Indicates that the given unit has encountered a problem and should be marked as broken.
	 * @param unit The unit that has encountered the problem
	 * @throws NotSupportedException If your implementation does not support this operation (only for groups with a limited assignment!)
	 * @throws EmergencyDispatchException When the indication fails (e.g. already broken)
	 */
	public void indicateProblem(IUnit unit) throws NotSupportedException, EmergencyDispatchException;
	
	/**
	 * Indicates that the given unit has been repaired and should be marked as available.
	 * @param unit The unit that has been repaired
	 * @throws NotSupportedException If your implementation does not support this operation (only for groups with a limited assignment!)
	 * @throws EmergencyDispatchException When the indication fails (e.g. not broken)
	 */
	public void indicateRepair(IUnit unit) throws NotSupportedException, EmergencyDispatchException;
	
	/**
	 * Advances the system time by the given amount of time.
	 * @param time The amount of time to advance
	 * @throws EmergencyDispatchException When advancing time fails
	 */
	public void advanceTime(ITime time) throws EmergencyDispatchException;
	
	/**
	 * Withdraws the given unit from the given emergency.
	 * @param unit The unit to withdraw
	 * @param emergency The emergency to withdraw the unit from
	 * @throws EmergencyDispatchException When withdrawing the unit fails (e.g. not assigned). This
	 * 	also includes the cases where the unit has already arrived or is a fire truck.
	 */
	public void withdrawUnit(IUnit unit, IEmergency emergency) throws EmergencyDispatchException;
	
	/**
	 * Cancels the given emergency.
	 * @param emergency The emergency to cancel
	 * @throws NotSupportedException If your implementation does not support this operation (only for groups with a limited assignment!)
	 * @throws EmergencyDispatchException When canceling the emergency fails (e.g. already completed). This
	 * 	does not include the cases where the system waits for unit confirmation.
	 */
	public void cancelEmergency(IEmergency emergency) throws NotSupportedException, EmergencyDispatchException;
	
	/**
	 * Clears all existing data in the system.
	 */
	public void clearSystem();
	
	/**
	 * Clears all existing data in the system and then initializes the system with the 
	 * 	data present in the environment file, which is specified according to the format in the assignment.
	 *	In case an error occurs during parsing, the system should be left in an empty state
	 *	and an appropriate error message should be thrown.
	 * @param file A File object representing the environment file that needs to be read.
	 * @throws EmergencyDispatchException When the environment file can not be loaded (e.g. invalid format,
	 * 	invalid input, ...)
	 */
	public void loadEnvironment(File file) throws EmergencyDispatchException;
	
	
}
