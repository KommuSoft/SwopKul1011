package projectswop20102011.factories;

import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
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
	 * @throws InvalidLocationException
	 *		If the given location is invalid.
	 * @throws InvalidEmergencySeverityException
	 *		If the given emergency severity is invalid.
	 * @throws InvalidFireSizeException
	 *		If the given fire size is invalid.
	 * @throws NumberOutOfBoundsException
	 *		If the given number of trapped people or the given number of injured people is invalid.
	 * @throws InvalidAmountOfParametersException
	 *		If the amount of given parameters is invalid.
	 */
	@Override
	public Emergency createEmergency(Object[] parameters) throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidAmountOfParametersException {
		if (parameters.length != 7) {
			throw new InvalidAmountOfParametersException("The number of parameters doesn't match the desired number of parameters.");
		} else {
			return new Fire((GPSCoordinate) parameters[0], (EmergencySeverity) parameters[1], (String) parameters[2], (FireSize) parameters[3], (Boolean) parameters[4], (Long) parameters[5], (Long) parameters[6]);
		}
	}

	/**
	 * Returns a list of the types of parameters to construct a fire emergency.
	 * @return A list of the types of parameters to construct a fire emergency.
	 */
	public static Class[] getParameterClasses() {
		return new Class[]{GPSCoordinate.class, EmergencySeverity.class, String.class, FireSize.class, Boolean.class, Long.class, Long.class};
	}
}
