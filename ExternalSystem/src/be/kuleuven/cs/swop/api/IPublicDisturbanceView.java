package be.kuleuven.cs.swop.api;

/**
 * This interface offers access to the data belonging to a PublicDisturbance.
 * 
 * @author philippe
 */
public interface IPublicDisturbanceView extends IEvent {

	/**
	 * Returns the number of people involved in the public disturbance. Only
	 * 	positive integers are considered to be valid.
	 * @return The number of involved people
	 */
	public int getNumberOfPeople();
	
}
