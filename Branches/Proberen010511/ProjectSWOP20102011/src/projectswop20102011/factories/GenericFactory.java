package projectswop20102011.factories;

import projectswop20102011.exceptions.InvalidParametersException;

/**
 * An interface providing method signatures for a factory (including naming, creation and parameter validation).
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface GenericFactory {

    /**
     * Returns the name of the factory (a directive used by parsers).
     * @return The name of the factory.
     */
    public abstract String getName ();
    /**
     * Creates an instance of the object provided by the factory.
     * @param parameters The parameters used for the creation of the object.
     * @return The object created by the factory.
     * @throws InvalidParametersException If the number or types of the parameters is not valid.
     * @throws Exception If another exception occurs while creating an instance of of the object.
     */
    public abstract Object createInstance (Object... parameters) throws InvalidParametersException, Exception;
    /**
     * Tests if the parameters are valid to create an instance.
     * @param parameters The lists of parameters to check on.
     * @return True if the parameters are valid, false otherwise.
     * @note The parameters are valid if the number of parameters correspont the the number of parameters of the factory and if their types match.
     *          However this does not mean that no exception can occur by creating an instance (The interpretation of the parameters is not checked).
     */
    public abstract boolean areValidParameters (Object... parameters);

    /**
     * Gets information about the factory (the type and name of the parameters to create an instance).
     * @return information about the factory.
     */
    public FactoryInformation getInformation ();

}
