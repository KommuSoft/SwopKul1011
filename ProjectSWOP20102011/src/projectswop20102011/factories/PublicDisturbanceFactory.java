package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.PublicDisturbance;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a PublicDisturbanceFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PublicDisturbanceFactory extends EmergencyFactory{

	public PublicDisturbanceFactory () throws InvalidEmergencyTypeNameException {
		super("public disturbance");
	}

	/**
	 * Creates a new PublicDisturbance with the given parameters.
	 *
	 * @param parameters
	 *		The parameters to construct the PublicDisturbance.
	 * @return The created PublicDisturbance.
	 */
	@Override
	public PublicDisturbance createEmergency(Object[] parameters) throws InvalidParameterException{
		if(areValidParameters(parameters)){
			GPSCoordinate location = (GPSCoordinate) parameters[0];
			EmergencySeverity eq = (EmergencySeverity) parameters[1];
			Long numberOfPeople = (Long) parameters[2];
			try {
				return new PublicDisturbance(location, eq, numberOfPeople);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(PublicDisturbanceFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidEmergencySeverityException ex) {
				Logger.getLogger(PublicDisturbanceFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NumberOutOfBoundsException ex) {
				Logger.getLogger(PublicDisturbanceFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			throw new InvalidParameterException("Invalid parameters for this public disturbance.");
		}
		return null;
	}

	/**
	 * Returns true if the given parameters are valid parameters for a PublicDisturbance.
	 * @param parameters
	 *		The desired parameters of the PublicDisturbance.
	 * @return
	 *		True if the given parameters are valid for a PublicDisturbance; false otherwise.
	 */
	public boolean areValidParameters(Object[] parameters){
		if(parameters.length != 3){
			return false;
		}
		if(parameters[0].getClass() != GPSCoordinate.class ||
				parameters[1].getClass() != EmergencySeverity.class ||
				parameters[2].getClass() != Long.class){
			return false;
		}

		return true;
	}
}