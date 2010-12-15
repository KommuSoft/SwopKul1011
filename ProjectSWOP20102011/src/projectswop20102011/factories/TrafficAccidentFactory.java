package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.TrafficAccident;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a TrafficAccidentFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TrafficAccidentFactory extends EmergencyFactory {

	/**
	 * Creates a new TrafficAccidentFactory.
	 *
	 * @throws InvalidEmergencyTypeNameException
	 *      If the type name of the new TrafficAccident is invalid.
	 */
	public TrafficAccidentFactory() throws InvalidEmergencyTypeNameException {
		super("traffic accident");
	}

	/**
	 * Creates a new TrafficAccident with the given parameters.
	 * @param parameters
	 *		The parameters of the new traffic accident.
	 * @return The created traffic accident.
	 * @throws InvalidParameterException
	 *		Thrown when the number of parameters doesn't match the desired number of parameters.
	 */
	@Override
	public Emergency createEmergency(Object[] parameters) throws InvalidParameterException {

		if (parameters.length != 5) {
			throw new InvalidParameterException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			try {
				//TODO hieronder nog zinvolle errors geven
				return new TrafficAccident((GPSCoordinate) parameters[0], (EmergencySeverity) parameters[1], (String) parameters[2], (Long) parameters[3], (Long) parameters[4]);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidEmergencySeverityException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NumberOutOfBoundsException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
			return null;
		}
	}

	/**
     * Returns a list of the types of parameters to construct a traffic accident emergency.
     * @return A list of the types of parameters to construct a traffic accident emergency.
     */
	@Override
	public Class[] getParameterClasses() {
		return new Class[] {GPSCoordinate.class, EmergencySeverity.class, String.class, Long.class, Long.class};
	}
}
