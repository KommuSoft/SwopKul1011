package be.kuleuven.cs.swop.api;

/**
 * This interface provides access to the data of an event.
 * 
 * @author philippe
 *
 */
public interface IEvent {

	/**
	 * Returns the timestamp associated with the event. The time
	 * 	should not lie in the future compared to the current system
	 * 	time.
	 * @return The timestamp
	 */
	public ITime getTime();
	
	/**
	 * Retrieves the location of the event
	 * @return The location of the event
	 */
	public ILocation getLocation();
	
	/**
	 * Retrieves the severity of the event
	 * @return The severity of the event
	 */
	public Severity getSeverity();

}