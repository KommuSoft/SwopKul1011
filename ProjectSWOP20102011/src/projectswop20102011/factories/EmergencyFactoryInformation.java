package projectswop20102011.factories;

import projectswop20102011.exceptions.InvalidParametersException;

/**
 * A class that contains information about how the parameters a Factory expects.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The list of parameters is always valid.
 *          |areValidParameters(getParameters())
 */
public class EmergencyFactoryInformation {

    private final EmergencyFactoryInformationParameter[] parameters;

    /**
     * Creates a new EmergencyFactoryInformation object with a given list of parameters of the factory.
     * @param parameters The parameters of this EmergecyFactory it describes.
     * @throws InvalidParametersException If the given list of parameters is invalid.
     */
    public EmergencyFactoryInformation (EmergencyFactoryInformationParameter... parameters) throws InvalidParametersException {
        if(!areValidParameters(parameters)) {
            throw new InvalidParametersException("The list of parameters must be effective and it's elements.");
        }
        this.parameters = parameters;
    }

    /**
     * Gets the parameters of this EmergencyFactoryInformation.
     * @return the parameters of this EmergencyFactoryInformation.
     */
    public EmergencyFactoryInformationParameter[] getParameters () {
        return (EmergencyFactoryInformationParameter[]) this.parameters.clone();
    }

    /**
     * Checks if the given list of parameters is valid.
     * @param parameters The list of parameters to check.
     * @return True if the list is valid and all its objects, otherwise false.
     */
    public static boolean areValidParameters (EmergencyFactoryInformationParameter[] parameters) {
        if(parameters != null) {
            for(EmergencyFactoryInformationParameter efip : parameters) {
                if(efip == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    /**
     * Checks if the Factory it describes can be called with the given set of parameters.
     * @param objects The instances of parameters to check.
     * @return True if the length of the given parameters is equal to the number of parameters and every parameter is a valid instance, otherwise false.
     */
    public boolean areValidParameterInstances (Object... objects) {
        EmergencyFactoryInformationParameter[] parameters = this.getParameters();
        if(objects.length == parameters.length) {
            for(int i = 0; i < parameters.length; i++) {
                if(!parameters[i].canBeAParameter(objects[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
