package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.Robbery;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;

/**
 * A class that represents a RobberyFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class RobberyFactory extends EmergencyFactory{

	public RobberyFactory () throws InvalidEmergencyTypeNameException {
		super("robbery");
	}

	/**
	 * Creates a new Robbery with the given parameters.
	 *
	 * @param parameters
	 *		The parameters to construct the Robbery.
	 * @return The created Robbery.
	 */
	@Override
	public Robbery createEmergency(Object[] parameters) throws InvalidParameterException {
		if(areValidParameters(parameters)){
			GPSCoordinate location = (GPSCoordinate) parameters[0];
			EmergencySeverity eq = (EmergencySeverity) parameters[1];
			Boolean armed = (Boolean) parameters[2];
			Boolean inProgress = (Boolean) parameters[3];
			try {
				return new Robbery(location, eq, armed, inProgress);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(RobberyFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidEmergencySeverityException ex) {
				Logger.getLogger(RobberyFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			throw new InvalidParameterException("Invalid parameters for this robbery.");
		}
		return null;
	}

	/**
	 * Returns true if the given parameters are valid parameters for a Robbery.
	 * @param parameters
	 *		The desired parameters of the Robbery.
	 * @return
	 *		True if the given parameters are valid for a Robbery; false otherwise.
	 */
	public boolean areValidParameters(Object[] parameters){
		if(parameters.length != 4){
			return false;
		}
		if(parameters[0].getClass() != GPSCoordinate.class ||
				parameters[1].getClass() != EmergencySeverity.class ||
				parameters[2].getClass() != Boolean.class ||
				parameters[3].getClass() != Boolean.class){
			return false;
		}

		return true;
	}


}