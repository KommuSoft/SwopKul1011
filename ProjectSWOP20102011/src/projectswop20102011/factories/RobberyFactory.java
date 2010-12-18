package projectswop20102011.factories;

import java.security.InvalidParameterException;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Robbery;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;

/**
 * A class that represents an RobberyFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class RobberyFactory extends EmergencyFactory {

	/**
	 * Creates a new RobberyFactory.
	 *
	 * @throws InvalidEmergencyTypeNameException
	 *      If the type name of the new Robbery is invalid.
	 */
	public RobberyFactory() throws InvalidEmergencyTypeNameException {
		super("robbery");
	}

	/**
	 * Creates a new Robbery with the given parameters.
	 * @param parameters
	 *		The parameters of the new robbery.
	 * @return The created robbery.
	 * @throws InvalidParameterException
	 *		Thrown when the number of parameters doesn't match the desired number of parameters.
	 */


	/**
	 * Creates a new Robbery with the given parameters.
	 * @param parameters
	 *		The parameters of the new robbery.
	 * @return The created robbery.
	 * @throws InvalidLocationException
	 *		If the given location is invalid.
	 * @throws InvalidEmergencySeverityException
	 *		If the given emergency severity is invalid.
	 * @throws InvalidAmountOfParametersException
	 *		If the amount of given parameters is invalid.
	 */
	@Override
	public Emergency createEmergency(Object[] parameters) throws InvalidLocationException, InvalidEmergencySeverityException, InvalidAmountOfParametersException {
		if (parameters.length != 5) {
			throw new InvalidAmountOfParametersException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			return new Robbery((GPSCoordinate) parameters[0], (EmergencySeverity) parameters[1], (String) parameters[2], (Boolean) parameters[3], (Boolean) parameters[4]);

		}
	}

	/**
	 * Returns a list of the types of parameters to construct a robbery emergency.
	 * @return A list of the types of parameters to construct a robbery emergency.
	 */
	@Override
	public Class[] getParameterClasses() {
		return new Class[]{GPSCoordinate.class, EmergencySeverity.class, String.class, Boolean.class, Boolean.class};
	}
}
