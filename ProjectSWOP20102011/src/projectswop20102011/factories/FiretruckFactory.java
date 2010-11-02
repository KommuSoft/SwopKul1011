package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.Firetruck;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;
import projectswop20102011.exceptions.InvalidUnitBuildingTypeNameException;

/**
 * A class that represents a FiretruckFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FiretruckFactory extends UnitBuildingFactory{

	/**
	 *
	 * @throws InvalidUnitBuildingTypeNameException
	 */
	public FiretruckFactory () throws InvalidUnitBuildingTypeNameException {
		super("firetruck");
	}

	/**
	 * Creates a new Firetruck.
	 *
	 * @param parameters
	 *		The parameters to construct the Firetruck.
	 * @return The new Firetruck.
	 * @throws InvalidParameterException
	 *		If the parameters are invalid for this Firetruck.
	 */
	@Override
	public Firetruck createUnitBuilding(Object[] parameters) throws InvalidParameterException {
		if(areValidParameters(parameters)){
			String name = (String) parameters[0];
			GPSCoordinate homeLocation = (GPSCoordinate) parameters[1];
			Long speed = (Long) parameters[2];
			GPSCoordinate currentLocation = (GPSCoordinate) parameters[3];
			GPSCoordinate destination = (GPSCoordinate) parameters[4];
			Boolean assigned = (Boolean) parameters[5];
			try {
				return new Firetruck(name, homeLocation, speed, currentLocation, destination, assigned);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(FiretruckFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidUnitBuildingNameException ex) {
				Logger.getLogger(FiretruckFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidSpeedException ex) {
				Logger.getLogger(FiretruckFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			throw new InvalidParameterException("Invalid parameters for this fire truck.");
		}
		return null;
	}

	/**
	 * Returns true if the given parameters are valid parameters for a Firetruck.
	 * @param parameters
	 *		The desired parameters of the Firetruck.
	 * @return
	 *		True if the given parameters are valid for a Firetruck; false otherwise.
	 */
	public boolean areValidParameters(Object[] parameters){
		if(parameters.length != 6){
			return false;
		}
		if(parameters[0].getClass() != String.class ||
			parameters[1].getClass() != GPSCoordinate.class ||
			parameters[2].getClass() != Long.class ||
			parameters[3].getClass() != GPSCoordinate.class ||
			parameters[4].getClass() != GPSCoordinate.class ||
			parameters[5].getClass() != Boolean.class) {
			return false;
		}
		return true;
	}

}