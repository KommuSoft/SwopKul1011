package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents an PublicDisturbanceFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PublicDisturbanceFactory extends EmergencyFactory {

	/**
	 * Creates a new PublicDisturbance.
	 *
	 * @throws InvalidEmergencyTypeNameException
	 *      If the type name of the new PublicDisturbance is invalid.
	 */
	public PublicDisturbanceFactory() throws InvalidEmergencyTypeNameException {
		super("public disturbance");
	}

	/**
	 * Creates a new PublicDisturbance with the given parameters.
	 * @param parameters
	 *		The parameters of the new public disturbance.
	 * @return The created public disturbance.
	 * @throws InvalidParameterException
	 *		Thrown when the number of parameters doesn't match the desired number of parameters.
	 */
	@Override
	public Emergency createEmergency(Object[] parameters) throws InvalidParameterException {
		if (parameters.length != 4) {
			throw new InvalidParameterException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			try {
				//TODO hieronder nog zinvolle errors geven
				return new PublicDisturbance((GPSCoordinate) parameters[0], (EmergencySeverity) parameters[1], (String) parameters[2], (Long) parameters[3]);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(PublicDisturbanceFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidEmergencySeverityException ex) {
				Logger.getLogger(PublicDisturbanceFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NumberOutOfBoundsException ex) {
				Logger.getLogger(PublicDisturbanceFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
			return null;
		}
	}

	/**
     * Returns a list of the types of parameters to construct a public disturbance emergency.
     * @return A list of the types of parameters to construct a public disturbance emergency.
     */
	@Override
	//TODO mag dit niet static?
	public Class[] getParameterClasses() {
		return new Class[] {GPSCoordinate.class, EmergencySeverity.class, String.class, Long.class};
	}
}
