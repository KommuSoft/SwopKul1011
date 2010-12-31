package projectswop20102011.factories;

import java.io.InvalidClassException;
import projectswop20102011.exceptions.InvalidDescriptionException;
import projectswop20102011.exceptions.InvalidNameException;

/**
 * A class containing information about an parameter of a GenericFactory. Used by the FactoryInformation.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The name of this class is always valid.
 *          |isValidName(getName())
 * @invar The parameter type of this class is always valid.
 *          |isValidParameterType(getParameterType())
 * @invar The description of this class is always valid.
 *          |isValidDescription(getDescription())
 */
public class FactoryInformationParameter {

    private final String name;
    private final Class parameterType;
    private final String description;

    /**
     * Creating a new FactoryInformationParameter with a given name, type and description.
     * @param name The name of the parameter.
     * @param parameterType The type of the parameter.
     * @param description A description about the parameter.
     * @throws InvalidNameException If the given name is invalid.
     * @throws InvalidClassException If the given parameter type is invalid.
     * @throws InvalidDescriptionException If the given description is invalid.
     * @post The name of this object is equal to the given name.
     *          |new.getName().equals(name)
     * @post The parameter type of this object is equal to the given parameter type.
     *          |new.getParameterType().equals(parameterType)
     * @post The description of this object is equal to the given description.
     *          |new.getDescription().equals(description)
     */
    public FactoryInformationParameter (String name, Class parameterType, String description) throws InvalidNameException, InvalidClassException, InvalidDescriptionException {
        if(!isValidName(name))
            throw new InvalidNameException("The name of the parameter must be effective.");
        if(!isValidParameterType(parameterType))
            throw new InvalidClassException("The type of the parameter must be effective.");
        if(!isValidDescription(description))
            throw new InvalidDescriptionException("The description of the parameter must be effective.");
        this.name = name;
        this.parameterType = parameterType;
        this.description = description;
    }

    /**
     * Gets the name of the parameter.
     * @return the name of the parameter.
     */
    public String getName () {
        return this.name;
    }
    /**
     * Gets the description of the parameter.
     * @return the description of the parameter.
     */
    public String getDescription () {
        return this.description;
    }
    /**
     * Gets the type of the parameter.
     * @return The type of the parameter.
     */
    public Class getParameterType () {
        return this.parameterType;
    }

    /**
     * Tests if the given name is a valid name for a FactoryInformationParameter.
     * @param name The name to test.
     * @return True if the name is effective, otherwise false.
     */
    public static boolean isValidName (String name) {
        return (name != null);
    }
    /**
     * Tests if the given parameter type is a valid parameter type for a FactoryInformationParameter.
     * @param parameterType The parameter type to test.
     * @return True if the given parameter is effective, otherwise false.
     */
    public static boolean isValidParameterType (Class parameterType) {
        return (parameterType != null);
    }

    /**
     * Tests if the given description is valid for a FactoryInformationParameter.
     * @param description The description to test.
     * @return True if the given description is effective, otherwise false.
     */
    public static boolean isValidDescription (String description) {
        return (description != null);
    }

    /**
     * Checks if the given parameter instance can be a parameter for the parameter of the factory this class describes.
     * @param obj The object to test.
     * @return True if the given object is an instance of the parameter type, otherwise false.
     */
    public boolean canBeAParameter (Object obj) {
        return this.getParameterType().isInstance(obj);
    }

}
