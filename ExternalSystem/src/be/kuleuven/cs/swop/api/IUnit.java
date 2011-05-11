package be.kuleuven.cs.swop.api;

/**
 * This interface offers operations to inspect a specific unit.
 * 
 * @author philippe
 */
public interface IUnit {
	
	/**
	 * Retrieves the current location of the unit
	 * @return The current location of the unit
	 */
	public ILocation getLocation();
	
	/**
	 * Retrieves the home location of the unit.
	 * @return The home location of the unit
	 */
	public ILocation getHomeLocation();
	
	/**
	 * Retrieves the name of the unit.
	 * @return The name of the unit.
	 */
	public String getName();
	
	/**
	 * Retrieves the current state of the unit within the system.
	 * @return The state of the unit.
	 */
	public UnitState getState();
	
}
