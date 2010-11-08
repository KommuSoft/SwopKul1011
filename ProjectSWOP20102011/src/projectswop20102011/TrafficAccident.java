package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a traffic accident.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar the number of cars is always valid | isValidNumberOfCars(getNumberOfCars())
 * @invar the number of injured is always valid | isValidNumberOfInjured(getNumberOfInjured())
 */
public class TrafficAccident extends Emergency {

    /**
     * A variable representing the number of cars involved in this traffic accident.
     */
    private long numberOfCars;
    /**
     * A variable representing the number of injured people of this traffic accident.
     */
    private long numberOfInjured;

    /**
     * Makes a new traffic accident emergency with the given parameters.
     * @param location
     *		The location of this traffic accident emergency.
     * @param severity
     *		The severity of this traffic accident emergency.
     * @param numberOfCars
     *		The number of cars involved in this traffic accident.
     * @param numberOfInjured
     *		The number of injured people of this traffic accident.
     * @effect super(location,severity)
     * @throws InvalidLocationException If the given location is an invalid location for an emergency.
     * @throws InvalidEmergencySeverityException If the given severity is an invalid severity for an emergency.
     * @throws NumberOutOfBoundsException If the number of involved cars or injured people is invalid.
     * @post The number of involved cars in this traffic accident is equal to the given number of cars.
     *		| numberOfCars.equals(getNumberOfCars())
     * @post The number of injured people in this traffic accident is equal to the given number of people.
     *		| numberOfInjured.equals(getNumberOfInjured())
     */
    public TrafficAccident(GPSCoordinate location, EmergencySeverity severity,
            long numberOfCars, long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
        super(location, severity);
        setNumberOfCars(numberOfCars);
        setNumberOfInjured(numberOfInjured);
    }

    /**
     * Returns the number of cars involved in this traffic accident.
     * @return The number of cars involved in this traffic accident.
     */
    public long getNumberOfCars() {
        return numberOfCars;
    }

    /**
     * Sets then number of cars involved in this traffic accident to the given value.
     * @param numberOfCars
     *		The new value of the number of cars involved in this traffic accident.
     * @throws NumberOutOfBoundsException If the number of cars is invalid.
     * @post The number of involved cars in this traffic accident is equal to the given number of cars.
     *		| numberOfCars.equals(getNumberOfCars())
     */
    private void setNumberOfCars(long numberOfCars) throws NumberOutOfBoundsException {
        if (!isValidNumberOfCars(numberOfCars)) {
            throw new NumberOutOfBoundsException(String.format("The number of cars must be larger or equal to zero in a traffic accident, and not \"%s\".", numberOfCars));
        }
        this.numberOfCars = numberOfCars;
    }

    /**
     * Returns the number of injured people in this traffic accident.
     * @return the number of injured people in this traffic accident.
     */
    public long getNumberOfInjured() {
        return numberOfInjured;
    }

    /**
     * Sets the number of injured people in this traffic accident to the given value.
     * @param numberOfInjured
     *		The new number of injured people involved in this traffic accident.
     * @throws NumberOutOfBoundsException If the number of cars is invalid.
     * @post The number of injured people in this traffic accident is equal to the given number of people.
     *		| numberOfInjured.equals(getNumberOfInjured())
     */
    private void setNumberOfInjured(long numberOfInjured) throws NumberOutOfBoundsException {
        if (!isValidNumberOfInjured(numberOfInjured)) {
            throw new NumberOutOfBoundsException(String.format("The number of injured must be larger or equal to zero in a traffic accident, and not \"%s\".", numberOfCars));
        }
        this.numberOfInjured = numberOfInjured;
    }

    /**
     * Checks if the given number of cars is a valid number for a traffic accident.
     * @param numberOfCars
     *		The number of cars to validate.
     * @return True if numberOfCars is larger or equal to zero, otherwise false.
     */
    public static boolean isValidNumberOfCars(long numberOfCars) {
        return (numberOfCars >= 0);
    }

    /**
     * Checks if the given number of injured people is a valid number for a traffic accident.
     * @param numberOfInjured
     *		The number of injured people to validate.
     * @return True if numberOfInjured is larger or equal to zero, otherwise false.
     */
    public static boolean isValidNumberOfInjured(long numberOfInjured) {
        return (numberOfInjured >= 0);
    }

    /**
     * Returns a string that represents the basic information of the traffic accident.
     * @return A string representing basic information of the traffic accident.
     * @see TrafficAccident.toLongInformationString
     */
    //TODO deze code moet nog verplaatst worden
    public String toShortInformationString() {
        return String.format("[Traffic Accident id=%s; location=%s; severity=%s; status=%s]", this.getId(), this.getLocation(), this.getSeverity(), this.getStatus());
    }

    /**
     * Returns a string that represents all the information of the traffic accident.
     * @return A string representing all the information of the traffic accident.
     * @see TrafficAccident.toShortInformationString
     */
    //TODO deze code moet nog verplaatst worden
    public String toLongInformationString() {
        return String.format("[Traffic Accident id=%s; location=%s; severity=%s; status=%s; number_of_cars=%s; number_of_injured=%s]", this.getId(), this.getLocation(), this.getSeverity(), this.getStatus(), this.getNumberOfCars(), this.getNumberOfInjured());
    }

    /**
     * Calculates the units needed for this traffic accident.
     * @return The units needed for this traffic accident.
     */
    @Override
    protected UnitsNeeded calculateUnitsNeeded() {
		if(getNumberOfInjured() > 0){
			if(getNumberOfCars() > 1){
				try {
					return new UnitsNeeded(this, new Class[]{Firetruck.class, Ambulance.class, Policecar.class}, new long[]{1, getNumberOfInjured(), getNumberOfCars() / 2});
				} catch (InvalidEmergencyException ex) {
					Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
				} catch (InvalidUnitsNeededException ex) {
					Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				try {
					return new UnitsNeeded(this, new Class[]{Firetruck.class, Ambulance.class}, new long[]{1, getNumberOfInjured()});
				} catch (InvalidEmergencyException ex) {
					Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
				} catch (InvalidUnitsNeededException ex) {
					Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		} else {
			if(getNumberOfCars() > 1){
				try {
					return new UnitsNeeded(this, new Class[]{Firetruck.class, Policecar.class}, new long[]{1, getNumberOfCars() / 2});
				} catch (InvalidEmergencyException ex) {
					Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
				} catch (InvalidUnitsNeededException ex) {
					Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				try {
					return new UnitsNeeded(this, new Class[]{Firetruck.class}, new long[]{1});
				} catch (InvalidEmergencyException ex) {
					Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
				} catch (InvalidUnitsNeededException ex) {
					Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		//should never be returned.
		return null;
    }
}
