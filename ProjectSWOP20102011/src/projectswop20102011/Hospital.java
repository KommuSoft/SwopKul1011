package projectswop20102011;

import projectswop20102011.exceptions.InvalidHospitalNameException;
import projectswop20102011.exceptions.InvalidLocationException;

/**
 * A class that represents a hospital.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar The location of a hospital is always valid.
 *		|isValidLocation(getLocation())
 * @invar The name of a hospital is always valid.
 *		|isValidName(getName())
 */
public class Hospital {

	/**
	 * A variable registering the location of this hospital.
	 */
	private GPSCoordinate location;

	/**
	 * A variable registering the name of this hospital.
	 */
	private String name;

	/**
	 * Initialize a new hospital with given parameters.
	 *
	 * @param location
	 *		The location of the new hospital.
	 * @param name
	 *		The name of the new hospital.
	 * @effect The new hospital has the given location.
	 *		|setLocation(location)
	 * @effect The new hospital has the given name.
	 *		|setName(name)
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for a hospital.
	 * @throws InvalidHospitalNameException
	 *		If the given name is an invalid name for a hospital.
	 */
	public Hospital(GPSCoordinate location, String name) throws InvalidLocationException, InvalidHospitalNameException{
		setLocation(location);
		setName(name);
	}

	/**
	 * Returns the location of this hospital.
	 * 
	 * @return The location of this hospital.
	 */
	public GPSCoordinate getLocation(){
		return location;
	}

	/**
	 * Returns the name of this hospital.
	 *
	 * @return The name of this hospital.
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the location of this hospital to the given value.
	 *
	 * @param location
	 *		The new location of this hospital.
     * @throws InvalidLocationException
	 *		If the given location isn't a valid name for a hospital.
     * @post The location of this hospital is set according to the given location.
	 *		|new.getLocation() == location
	 */
	private void setLocation(GPSCoordinate location) throws InvalidLocationException{
		if(!isValidLocation(location)){
			throw new InvalidLocationException(String.format("\"%s\" is an invalid location for a hospital.", location));
		}else{
			this.location = location;			
		}

	}

	/**
	 * Sets the name of this hospital to the given value.
	 *
	 * @param name
	 *		The new name of this hospital.
     * @throws InvalidHospitalNameException
	 *		If the given name isn't a valid name for a hospital.
     * @post The name of this hospital is set according to the given name.
	 *		|new.getName() == name
	 */
	private void setName(String name) throws InvalidHospitalNameException{
		if(!isValidName(name)){
			throw new InvalidHospitalNameException(String.format("\"%s\" is an invalid name for a hospital.", location));
		}
		this.name = name;
	}

	/**
	 * Checks if the given location is a valid location for a hospital.
	 *
	 * @param location
	 *		The location of a hospital to test.
	 * @return True if the location is valid; false otherwise.
	 */
	public static boolean isValidLocation(GPSCoordinate location){
		return location != null;
	}

	/**
	 * Checks if the given name is a valid name for a hospital.
	 *
	 * @param name
	 *		The name of a hospital to test.
	 * @return True if the name is valid; false otherwise.
	 */
	public static boolean isValidName(String name){
		return ((!name.equals(""))&&(name != null));
	}

}
