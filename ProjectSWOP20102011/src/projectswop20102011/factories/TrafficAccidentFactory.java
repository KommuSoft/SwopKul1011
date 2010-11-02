package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.EmergencySeverity;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.TrafficAccident;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;


/**
 * A class that represents a TrafficAccidentFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TrafficAccidentFactory extends EmergencyFactory{

	public TrafficAccidentFactory () throws InvalidEmergencyTypeNameException {
		super("traffic accident");
	}


	/**
	 * Creates a new TrafficAccident with the given parameters.
	 *
	 * @param parameters
	 *		The parameters to construct the TrafficAccident.
	 * @return The created TrafficAccident.
	 */
	@Override
	public TrafficAccident createEmergency(Object[] parameters) throws InvalidParameterException {
		if(areValidParameters(parameters)){
			GPSCoordinate location = (GPSCoordinate) parameters[0];
			EmergencySeverity eq = (EmergencySeverity) parameters[1];
			Long numberOfCars =  (Long) parameters[2];
			Long numberOfInjured =  (Long) parameters[3];
			try {
				return new TrafficAccident(location, eq, numberOfCars, numberOfInjured);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidEmergencySeverityException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NumberOutOfBoundsException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
		} else {
			throw new InvalidParameterException("Invalid parameters for this traffic accident.");
		}
		return null;
	}

	/**
	 * Returns true if the given parameters are valid parameters for a TrafficAccident.
	 * @param parameters
	 *		The desired parameters of the TrafficAccident.
	 * @return
	 *		True if the given parameters are valid for a TrafficAccident; false otherwise.
	 */
	public boolean areValidParameters(Object[] parameters){
		if(parameters.length != 4){
			return false;
		}
		if(parameters[0].getClass() != GPSCoordinate.class ||
				parameters[1].getClass() != EmergencySeverity.class ||
				parameters[2].getClass() != Long.class ||
				parameters[3].getClass() != Long.class){
			return false;
		}

		return true;
	}
}