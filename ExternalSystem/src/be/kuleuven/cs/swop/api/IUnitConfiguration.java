package be.kuleuven.cs.swop.api;

import java.util.List;

/**
 * This interface contains a unit configuration for a specific emergency. 
 *  A unit configuration consists of a number of suggested units for each
 *  required unit type.
 *  
 * @author philippe
 */
public interface IUnitConfiguration {

	/**
	 * Retrieves the emergency for which the unit configuration is
	 * 	created.
	 * @return The associated emergency
	 */
	public IEmergency getEmergency();
	
	/**
	 * Retrieves the list of fire trucks present in the unit configuration.
	 * 	No particular order is expected within the list.
	 * @return The list of fire trucks
	 */
	public List<IFireTruckView> getListOfFireTrucks();
	
	/**
	 * Retrieves the list of ambulances present in the unit configuration.
	 * 	No particular order is expected within the list.
	 * @return The list of ambulances
	 */
	public List<IAmbulanceView> getListOfAmbulances();
	
	/**
	 * Retrieves the list of police cars present in the unit configuration.
	 * 	No particular order is expected within the list.
	 * @return The list of police cars
	 */
	public List<IPoliceCarView> getListOfPoliceCars();
	
	/**
	 * Returns a list of all units present in this configuration.
	 *  No particular order is expected within the list.
	 * @return The list of all units
	 */
	public List<IUnit> getAllUnits();
	
}
