package projectswop20102011.factories;

import java.io.InvalidClassException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencySeverity;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.TrafficAccident;
import projectswop20102011.exceptions.InvalidDescriptionException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyTypeNameException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidNameException;
import projectswop20102011.exceptions.InvalidParametersException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a TrafficAccidentFactory.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TrafficAccidentFactory extends EmergencyFactory {

    /**
     * Creates a new TrafficAccidentFactory.
     * @effect The new TrafficAccidentFactory is a new EmergencyFactory with a given type isValidName.
     *		|super("traffic accident")
     * @throws InvalidEmergencyTypeNameException
     *      If the type isValidName of the new TrafficAccident is invalid.
     */
    public TrafficAccidentFactory() throws InvalidEmergencyTypeNameException {
        super("trafficAccident");
    }

    /**
     * Creates a new TrafficAccident with the given parameters.
     * @param parameters
     *		The parameters of the new traffic accident.
     * @return The created traffic accident.
     * @throws InvalidLocationException
     *		If the given location is invalid.
     * @throws InvalidEmergencySeverityException
     *		If the given emergency severity is invalid.
     * @throws NumberOutOfBoundsException
     *		If the number of involved cars or injured people is invalid.
     * @throws InvalidParametersException
     *          If the given list of parameters is invalid.
     */
    @Override
    public Emergency createInstance(Object... parameters) throws InvalidParametersException {
        if (!this.areValidParameters(parameters)) {
            throw new InvalidParametersException("The parameters are invalid.");
        } else {
			try {
				return new TrafficAccident((GPSCoordinate) parameters[0], (EmergencySeverity) parameters[1], (String) parameters[2], (Long) parameters[3], (Long) parameters[4]);
			} catch (InvalidLocationException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvalidEmergencySeverityException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NumberOutOfBoundsException ex) {
				Logger.getLogger(TrafficAccidentFactory.class.getName()).log(Level.SEVERE, null, ex);
			}
        }
		return null;
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
                    new FactoryInformationParameter("numberOfCars", Long.class, "The number of cars envolved in the traffic accident."),
                    new FactoryInformationParameter("numberOfInjured", Long.class, "The number of injured people."));
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
