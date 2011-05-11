package be.kuleuven.cs.swop.api;

/**
 * This interface offers access to the data of an ambulance
 * 
 * @author philippe
 */
public interface IAmbulanceView extends IUnit {

	/**
	 * Indicates whether the ambulance is currently carrying a victim or not.
	 * @return True if the ambulance is carrying a victim, false otherwise
	 */
	public boolean isOccupied();
	
}
