package projectswop20102011.factories;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents an FireFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireFactory extends EmergencyFactory {

	/**
	 * Creates a new FireFactory.
	 *
	 * @throws InvalidEmergencyTypeNameException
	 *      If the type name of the new FireFactory is invalid.
	 */
	public FireFactory() throws InvalidEmergencyTypeNameException {
		super("fire");
	}

	/**
	 * Creates a new Fire with the given parameters.
	 * @param parameters
	 *		The parameters of the new fire.
	 * @return The created fire.
	 * @throws InvalidParameterException
	 *		Thrown when the number of parameters doesn't match the desired number of parameters.
	 */
	@Override
	public Emergency createEmergency(Object[] parameters) throws InvalidParameterException {
		if (parameters.length != 7) {
			throw new InvalidParameterException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			//TODO hieronder nog zinvolle errors geven
			try {
				return new Fire((GPSCoordinate) parameters[0], (EmergencySeverity) parameters[1], (String) parameters[2], (FireSize) parameters[3], (Boolean) parameters[4], (Long) parameters[5], (Long) parameters[6]);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidEmergencySeverityException ex) {
				Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidFireSizeException ex) {
				Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NumberOutOfBoundsException ex) {
				Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
			return null;
		}
	}

	/**
     * Returns a list of the types of parameters to construct a fire emergency.
     * @return A list of the types of parameters to construct a fire emergency.
     */
	@Override
	//TODO mag dit niet static?
	public Class[] getParameterClasses() {
		return new Class[] {GPSCoordinate.class, EmergencySeverity.class, String.class, FireSize.class, Boolean.class, Long.class, Long.class};
	}
}
