package be.kuleuven.cs.swop.api;

/**
 * This interface offers access to the data belonging to a TrafficAccident.
 * 
 * @author philippe
 */
public interface ITrafficAccidentView extends IEvent {
	
	/**
	 * Returns the number of involved cars. Only positive integers
	 * 	are considered to be valid.
	 * @return The number of cars involved
	 */
	public int getNumberOfCars();
	
	/**
	 * Returns the number of injured people in the traffic accident.
	 * 	 Only positive integers are considered to be valid.
	 * @return The number of injured people
	 */
	public int getNumberOfInjured();
	
}
