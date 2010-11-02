package projectswop20102011.factories;

import java.lang.Class;
import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.Fire;
import projectswop20102011.FireSize;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a FireFactory
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireFactory extends EmergencyFactory{

	public FireFactory () throws InvalidEmergencyTypeNameException {
		super("fire");
	}

	/**
	 * Creates a new Fire with the given parameters.
	 * 
	 * @param parameters
	 *		The parameters to construct the fire.
	 * @return The created fire.
	 */
	@Override
	public Fire createEmergency(Object[] parameters) throws InvalidParameterException {
		if(areValidParameters(parameters)){
			GPSCoordinate location = (GPSCoordinate) parameters[0];
			EmergencySeverity eq = (EmergencySeverity) parameters[1];
			FireSize fs = (FireSize) parameters[2];
			Boolean chemical = (Boolean) parameters[3];
			Boolean trappedPeople = (Boolean) parameters[4];
			Long numberOfInjured = (Long) parameters[5];
			try {
				return new Fire(location, eq, fs, chemical, trappedPeople, numberOfInjured);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidEmergencySeverityException ex) {
				Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidFireSizeException ex) {
				Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NumberOutOfBoundsException ex) {
				Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			throw new InvalidParameterException("Invalid parameters for this fire.");
		}
		return null;
	}

	/**
	 * Returns true if the given parameters are valid parameters for a Fire.
	 * @param parameters
	 *		The desired parameters of the Fire.
	 * @return
	 *		True if the given parameters are valid for a Fire; false otherwise.
	 */
	public boolean areValidParameters(Object[] parameters){
		if(parameters.length != 6){
			return false;
		}
		if(parameters[0].getClass() != GPSCoordinate.class ||
				parameters[1].getClass() != EmergencySeverity.class ||
				parameters[2].getClass() !=  FireSize.class ||
				parameters[3].getClass() != Boolean.class ||
				parameters[4].getClass() != Boolean.class ||
				parameters[5].getClass() != Long.class){
				return false;
		}

		return true;
	}
}