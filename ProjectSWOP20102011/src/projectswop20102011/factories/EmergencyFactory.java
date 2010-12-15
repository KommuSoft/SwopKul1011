package projectswop20102011.factories;

import java.security.InvalidParameterException;
import projectswop20102011.domain.Emergency;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;

/**
 * A class that represents an EmergencyFactory.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class EmergencyFactory {

    /**
     * The name of the emergency the factory will create.
     */
    private final String emergencyTypeName;

    /**
     * Creates a new EmergencyFactory with a given emergency type name.
     * @param emergencyTypeName
     *		The name of the type of emergency the factory will create.
     * @throws InvalidEmergencyTypeNameException
     *		If the name of the emergency type is invalid.
     */
    protected EmergencyFactory(String emergencyTypeName) throws InvalidEmergencyTypeNameException {
        if (isValidEmergencyTypeName(emergencyTypeName)) {
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
     * @throws Exception
     *          If the constructor in the factory throws an exception.
     */
    public abstract Emergency createEmergency(Object[] parameters) throws InvalidParameterException, Exception;

    
    /**
     * Returns a list of the types of parameters to construct that special type of emergency.
     * @return A list of the types of parameters to construct that special type of emergency.
     */
    public abstract Class[] getParameterClasses ();

    /**
     * Tests if the given emergency type name is valid for an EmergencyFactory object.
     * @param emergencyTypeName
     *		The given emergency type name to test.
     * @return True if the given name is effective and not empty, otherwise false.
     */
    public static boolean isValidEmergencyTypeName(String emergencyTypeName) {
        return (emergencyTypeName != null && emergencyTypeName.length() > 0);
    }

    /**
     * Checks if the given parameters can be used to construct the specific emergency.
     * @return True if the given parameters can be used to initialize the specific emergency constructor, otherwise false.
     */
    public boolean areValidParameters (Object[] parameters) {
        Class[] parameterClasses = this.getParameterClasses();
        if(parameterClasses.length != parameters.length) {
            return false;
        }
        for(int i = 0; i < parameterClasses.length; i++) {
            if(!parameterClasses[i].isInstance(parameters[i])) {
                return false;
            }
        }
        return true;
    }
}