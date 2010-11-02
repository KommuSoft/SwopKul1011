package projectswop20102011.factories;

import java.security.InvalidParameterException;
import projectswop20102011.Emergency;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * A class that represents an EmergencyFactory.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class EmergencyFactory{

	private final String emergencyTypeName;
	
	/**
	 * Creates a new EmergencyFactory with a given emergency type name.
	 * @param emergencyTypeName
	 *		The name of the type of emergency the factory will create.
	 * @throws InvalidEmergencyTypeNameException
	 *		If the name of the emergency type is invalid.
	 */
	protected EmergencyFactory (String emergencyTypeName) throws InvalidEmergencyTypeNameException {
		if(isValidEmergencyTypeName(emergencyTypeName)) {
			throw new InvalidEmergencyTypeNameException("emergencyTypeName must be effective and not empty");
		}
		this.emergencyTypeName = emergencyTypeName;
	}

	/**
	 * Creates a new specific emergency with the given parameters
	 * @param parameters
	 *		The parameters of the constructor.
	 * @return The emergency created by the factory.
	 * @throws InvalidParameterException
	 *		If the given parameters are invalid parameters for the Emergency.
	 */
	public abstract Emergency createEmergency (Object[] parameters) throws InvalidParameterException;

	/**
	 * Tests if the given emergency type name is valid for an EmergencyFactory object.
	 * @param emergencyTypeName
	 *		The given emergency type name to test.
	 * @return True if the given name is effective and not empty, otherwise false.
	 */
	public static boolean isValidEmergencyTypeName (String emergencyTypeName) {
		return (emergencyTypeName != null && emergencyTypeName.length() > 0);
	}

}