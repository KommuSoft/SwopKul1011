package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;

/**
 * A class that represents a traffic accident.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
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
     */
    public TrafficAccident(GPSCoordinate location, EmergencySeverity severity,
            long numberOfCars, long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException {
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
     */
    private void setNumberOfCars(long numberOfCars) {
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
     */
    private void setNumberOfInjured(long numberOfInjured) {
        this.numberOfInjured = numberOfInjured;
    }
}
