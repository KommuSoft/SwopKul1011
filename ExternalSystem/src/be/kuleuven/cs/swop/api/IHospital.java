package be.kuleuven.cs.swop.api;
/**
 * This interface offers operations to inspect the properties of a hospital.
 * 
 * @author philippe
 */
public interface IHospital {

	/**
	 * Retrieves the name of the hospital
	 * @return The name of the hospital
	 */
	public String getName();
	
	/**
	 * Retrieves the location of the hospital
	 * @return The location of the hospital
	 */
	public ILocation getLocation();
	
}
