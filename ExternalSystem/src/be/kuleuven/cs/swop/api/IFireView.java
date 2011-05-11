package be.kuleuven.cs.swop.api;

/**
 * This interface offers access to the data belonging to a Fire.
 * 
 * @author philippe
 */
public interface IFireView extends IEvent {

	/**
	 * Returns the size of the fire. Possible values are "local", "house"
	 * 	and "facility".
	 * @return The size of the fire
	 */
	public String getSize();
	
	/**
	 * Returns whether the fire is chemical or not
	 * @return True if it concerns a chemical fire, false otherwise
	 */
	public boolean isChemical();
	
	/**
	 * Returns the number of trapped people in the fire. Only positive
	 *  integers are considered to be valid values.
	 * @return the number of trapped people
	 */
	public int getNumberOfTrappedPeople();
	
	/**
	 * Returns the number of injured people in the fire. Only positive
	 * 	integers are considered to be valid values.
	 * @return The number of injured people
	 */
	public int getNumberOfInjured();
	
}
