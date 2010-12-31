package projectswop20102011.factories;

import java.io.InvalidClassException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.exceptions.InvalidDescriptionException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidNameException;
import projectswop20102011.exceptions.InvalidParametersException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents an PublicDisturbanceFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PublicDisturbanceFactory extends EmergencyFactory {

    /**
     * Creates a new PublicDisturbance.
     * @effect The new PublicDisturbanceFactory is a new EmergencyFactory with a given type isValidName.
     *		|super("public disturbance")
     * @throws InvalidEmergencyTypeNameException
     *      If the type isValidName of the new PublicDisturbance is invalid.
     */
    public PublicDisturbanceFactory() throws InvalidEmergencyTypeNameException {
        super("public disturbance");
    }

    /**
     * Creates a new PublicDisturbance with the given parameters.
     * @param parameters
     *		The parameters of the new public disturbance.
     * @return The created public disturbance.
     * @throws InvalidLocationException
     *		If the given location is invalid.
     * @throws InvalidEmergencySeverityException
     *		If the given emergency severity is invalid.
     * @throws InvalidParametersException
     *          If the given list of parameters has'nt the proper length or types of parameters.
     * @throws NumberOutOfBoundsException If the number of people involved in the public disturbance is invalid.
     */
    @Override
    public Emergency createInstance(Object... parameters) throws InvalidLocationException, InvalidEmergencySeverityException, InvalidParametersException, NumberOutOfBoundsException {
        if (!this.areValidParameters(parameters)) {
            throw new InvalidParametersException("The given parameters can' t instantiate the constructor.");
        } else {
            return new PublicDisturbance((GPSCoordinate) parameters[0], (EmergencySeverity) parameters[1], (String) parameters[2], (Long) parameters[3]);
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
                    new FactoryInformationParameter("numberOfPeople", Long.class, "The number of people involved by the public disturbance."));
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
