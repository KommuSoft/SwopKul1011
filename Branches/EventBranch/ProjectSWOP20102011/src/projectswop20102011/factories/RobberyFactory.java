package projectswop20102011.factories;

import java.io.InvalidClassException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Robbery;
import projectswop20102011.exceptions.InvalidDescriptionException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidNameException;
import projectswop20102011.exceptions.InvalidParametersException;

/**
 * A class that represents an RobberyFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class RobberyFactory extends EmergencyFactory {

    /**
     * Creates a new RobberyFactory.
     * @effect The new RobberyFactory is a new EmergencyFactory with a given type isValidName.
     *		|super("robbery")
     * @throws InvalidEmergencyTypeNameException
     *      If the type isValidName of the new Robbery is invalid.
     */
    public RobberyFactory() throws InvalidEmergencyTypeNameException {
        super("robbery");
    }

    /**
     * Creates a new Robbery with the given parameters.
     * @param parameters
     *		The parameters of the new robbery.
     * @return The created robbery.
     * @throws InvalidLocationException
     *		If the given location is invalid.
     * @throws InvalidEmergencySeverityException
     *		If the given emergency severity is invalid.
     * @throws InvalidParametersException
     *          If the given parameters are invalid.
     */
    @Override
    public Emergency createInstance(Object... parameters) throws InvalidParametersException {
        if (!this.areValidParameters(parameters)) {
            throw new InvalidParametersException("The parameters are invalid.");
        } else {
			try {
				return new Robbery((GPSCoordinate) parameters[0], (SendableSeverity) parameters[1], (String) parameters[2], (Boolean) parameters[3], (Boolean) parameters[4]);
			} catch (InvalidLocationException ex) {
				throw new InvalidParametersException("The given parameters can' t instantiate the constructor. " + ex.getMessage());
			} catch (InvalidSendableSeverityException ex) {
				throw new InvalidParametersException("The given parameters can' t instantiate the constructor. " + ex.getMessage());
			}
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
                    new FactoryInformationParameter("severity", SendableSeverity.class, "The severity level of the emergency."),
                    new FactoryInformationParameter("description", String.class, "The description of the emergency."),
                    new FactoryInformationParameter("armed", Boolean.class, "Indicates if the robber is armed."),
                    new FactoryInformationParameter("inProgress", Boolean.class, "Indicates if the robbery is still in progress."));
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
