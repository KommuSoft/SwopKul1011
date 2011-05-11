package be.kuleuven.cs.swop.api;

/**
 * This interface offers access to the data belonging to a Robbery.
 * 
 * @author philippe
 */
public interface IRobberyView extends IEvent {

	/**
	 * Returns whether it concerns an armed robbery or not
	 * @return True if it concerns an armed robbery, false otherwise
	 */
	public boolean isArmed();
	
	/**
	 * Indicates whether the robbery is in progress
	 * @return True if the robbery is in progress, false otherwise
	 */
	public boolean isInProgress();
	
}
