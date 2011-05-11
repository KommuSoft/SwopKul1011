package be.kuleuven.cs.swop.api;

/**
 * This interface offers access to the data of a fire truck
 * 
 * @author philippe
 */
public interface IFireTruckView extends IUnit {

	/**
	 * Retrieves the capacity of a fire truck. Valid values are
	 * 	positive integers
	 * @return The capacity of the fire truck
	 */
	public int getCapacity();
	
}
