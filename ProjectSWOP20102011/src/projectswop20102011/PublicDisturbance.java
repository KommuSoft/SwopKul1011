package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a public disturbance.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar The number of people involved must always be valid | isValidNumberOfPeople(getNumberOfPeople())
 */
public class PublicDisturbance extends Emergency {

    /**
     * An estimation of the number of people that are involved in this public disturbance.
     */
    private long numberOfPeople;

    /**
     * Makes a new public disturbance emergency with the given parameters.
     * @param location
     *		The location of this public disturbance emergency.
     * @param severity
     *		The severity of this public disturbance emergency.
     * @param numberOfPeople
     *		The number of people that are involved in this public disturbance.
     * @effect super(location,severity)
     * @throws InvalidLocationException If the given location is an invalid location for an emergency.
     * @throws InvalidEmergencySeverityException If the given severity is an invalid severity for an emergency.
     * @throws NumberOutOfBoundsException If the given number of people invlolved is invalid.
     * @post the number of involved people of this public disturbance is equal to the given number of people.
     */
    public PublicDisturbance(GPSCoordinate location,
            EmergencySeverity severity, long numberOfPeople) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        super(location, severity);
        setNumberOfPeople(numberOfPeople);
    }

    /**
     * Returns an estimation of the number of people that are involved in this public disturbance.
     * @return An estimation of the number of people that are involved in this public disturbance.
     */
    public long getNumberOfPeople() {
        return numberOfPeople;
    }

    /**
     * Sets the number of people that are involved in this public disturbance to the given value.
     * @param numberOfPeople
     *		The new number of people that are involved in this public disturbance.
     * @throws NumberOutOfBoundsException If the given number of people invlolved is invalid.
     * @post the number of involved people of this public disturbance is equal to the given number of people.
     */
    private void setNumberOfPeople(long numberOfPeople) throws NumberOutOfBoundsException {
        if (!isValidNumberOfPeople(numberOfPeople)) {
            throw new NumberOutOfBoundsException(String.format("The number of people involved in a public disturbance must be strict larger than zero and not \"%s\".", numberOfPeople));
        }
        this.numberOfPeople = numberOfPeople;
    }

    /**
     * Checks if the number of people is a valid number for a public disturbance emergency.
     * @param numberOfPeople
     *		The number of people to check.
     * @return True if the number of people is strict larger than zero.
     */
    public static boolean isValidNumberOfPeople(long numberOfPeople) {
        return (numberOfPeople > 0);
    }

    /**
     * Returns a string that represents the basic information of the public disturbance (type,location,severity)
     * @return A string representing basic information of the public disturbance.
     * @see PublicDisturbance.toLongInformationString
     */
    //TODO deze code moet nog verplaatst worden
    public String toShortInformationString() {
        return String.format("[Public Disturbance id=%s; location=%s; severity=%s; status=%s]", this.getId(), this.getLocation(), this.getSeverity(), this.getStatus());
    }

    /**
     * Returns a string that represents all the information of the public disturbance.
     * @return A string that represents all the information of the public disturbance.
     * @see PublicDisturbance.toShortInformationString
     */
    //TODO deze code moet nog verplaatst worden
    public String toLongInformationString() {
        return String.format("[Public Disturbance id=%s; location=%s; severity=%s; status=%s; number_of_people=%s]", this.getId(), this.getLocation(), this.getSeverity(), this.getStatus(), this.getNumberOfPeople());
    }

    /**
     * Calculates the units needed for this public disturbance.
     * @return The units needed for this public disturbance.
     */
    @Override
    protected UnitsNeeded calculateUnitsNeeded() {
        try {
            return new UnitsNeeded(this, new Class[]{Policecar.class}, new long[]{(this.getNumberOfPeople() + 4) / 5});
        } catch (InvalidEmergencyException ex) {
            //we assume this can't happen
            Logger.getLogger(PublicDisturbance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidUnitsNeededException ex) {
            //we assume this can't happen
            Logger.getLogger(PublicDisturbance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
