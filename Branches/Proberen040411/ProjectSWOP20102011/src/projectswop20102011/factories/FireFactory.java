package projectswop20102011.factories;

import java.io.InvalidClassException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.exceptions.InvalidDescriptionException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidNameException;
import projectswop20102011.exceptions.InvalidParametersException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents an FireFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class FireFactory extends EmergencyFactory {

    /**
     * Creates a new FireFactory.
     * @effect The new FireFactory is a new EmergencyFactory with a given type isValidName.
     *		|super("fire")
     * @throws InvalidEmergencyTypeNameException
     *      If the type isValidName of the new FireFactory is invalid.
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
     * @throws InvalidParametersException
     *          If the given list of parameters has'nt the proper length or types of parameters.
     */
    @Override
    public Emergency createInstance(Object... parameters) throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException, InvalidParametersException {
        if (!this.areValidParameters(parameters)) {
            throw new InvalidParametersException("The given parameters can' t instantiate the constructor.");
        } else {
            return new Fire((GPSCoordinate) parameters[0], (EmergencySeverity) parameters[1], (String) parameters[2], (FireSize) parameters[3], (Boolean) parameters[4], (Long) parameters[5], (Long) parameters[6]);
        }
    }

    /**
     * Generates an information object for the Factory.
     * @return an information object for the Factory.
     */
    @Override
    protected FactoryInformation generateInformation() {
        try {
            return new FactoryInformation(
                    new FactoryInformationParameter("location", GPSCoordinate.class, "The location of the emergency."),
                    new FactoryInformationParameter("severity", EmergencySeverity.class, "The severity level of the emergency."),
                    new FactoryInformationParameter("description", String.class, "The description of the emergency."),
                    new FactoryInformationParameter("size", FireSize.class, "The size of the fire."),
                    new FactoryInformationParameter("chemical", Boolean.class, "Indicates if the fire is chemical."),
                    new FactoryInformationParameter("trappedPeople", Long.class, "The number of trapped people in the fire."),
                    new FactoryInformationParameter("numberOfInjured", Long.class, "The number of injured people by the fire."));
        } catch (InvalidNameException ex) {
            //we assume this can never happen.
            Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidClassException ex) {
            //we assume this can never happen.
            Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDescriptionException ex) {
            //we assume this can never happen.
            Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidParametersException ex) {
            //we assume this can never happen.
            Logger.getLogger(FireFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        //will never be returned.
        return null;
    }
}
