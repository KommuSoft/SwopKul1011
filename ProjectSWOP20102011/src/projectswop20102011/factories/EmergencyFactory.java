package projectswop20102011.factories;

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
     * An object containing information about the factory.
     */
    private EmergencyFactoryInformation information;

    /**
     * Creates a new EmergencyFactory with a given emergency type name.
     * @param emergencyTypeName
     *		The name of the type of emergency the factory will create.
     * @throws InvalidEmergencyTypeNameException
     *		If the name of the emergency type is invalid.
     */
    protected EmergencyFactory(String emergencyTypeName) throws InvalidEmergencyTypeNameException {
        if (!isValidEmergencyTypeName(emergencyTypeName)) {
            throw new InvalidEmergencyTypeNameException("emergencyTypeName must be effective and not empty");
        }
        this.emergencyTypeName = emergencyTypeName;
    }

    /**
     * Returns the name of the emergency this factory will create.
     * @return The name of the emergency this factory will create.
     */
    public String getEmergencyTypeName() {
        return emergencyTypeName;
    }

    /**
     * Creates a new specific emergency with the given parameters.
     * @param parameters
     *		The parameters of the constructor.
     * @return The emergency created by the factory.
     * @throws Exception
     *		If an error occurs.
     */
    public abstract Emergency createEmergency(Object[] parameters) throws Exception;

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
     * An abstract method to given the children a way to generate information about the specific factory.
     * @return An EmergencyFactoryInformation object containing information about the Factory.
     */
    protected abstract EmergencyFactoryInformation generateInformation();

    public synchronized EmergencyFactoryInformation getInformation() {
        if (this.information == null) {
            this.information = this.generateInformation();
        }
        return this.information;
    }

    /**
     * Checks if the given parameters can be used to construct the specific emergency.
     * @param parameters The parameters to check.
     * @return True if the given parameters can be used to initialize the specific emergency constructor, otherwise false.
     * @note Only the instance is checked. E.g. if we expect a positive Integer and we give a negative Integer as parameter then this method returns true.
     */
    public boolean areValidParameters(Object... parameters) {
        return this.getInformation().areValidParameterInstances(parameters);
    }
}
