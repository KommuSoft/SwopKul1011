package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a traffic accident.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar the number of cars is always valid | hasValidNumberOfCars()
 * @invar the number of injured is always valid | hasValidNumberOfInjured()
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
     * @post The number of involved cars in this traffic accident is equal to the given number of cars. | numberOfCars.equals(getNumberOfCars())
     * @post The number of injured people in this traffic accident is equal to the given number of people. | numberOfInjured.equals(getNumberOfInjured())
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
     * @post The number of involved cars in this traffic accident is equal to the given number of cars. | numberOfCars.equals(getNumberOfCars())
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
     * @post The number of injured people in this traffic accident is equal to the given number of people. | numberOfInjured.equals(getNumberOfInjured())
     */
    private void setNumberOfInjured(long numberOfInjured) throws NumberOutOfBoundsException {
        if (!isValidNumberOfInjured(numberOfInjured)) {
            throw new NumberOutOfBoundsException(String.format("The number of injured must be larger or equal to zero in a traffic accident, and not \"%s\".", numberOfCars));
        }
        this.numberOfInjured = numberOfInjured;
    }

    /**
     * Checks if the given number of cars is a valid number for a traffic accident.
     * @param numberOfCars The number of cars to validate.
     * @return True if numberOfCars is larger or equal to zero, otherwise false.
     */
    public static boolean isValidNumberOfCars(long numberOfCars) {
        return (numberOfCars >= 0);
    }

    /**
     * Checks if the number of injured people is valid.
     * @return True if the number of cars is valid, otherwise false.
     * @note This method tests an invariant, the result must always be true.
     */
    public boolean hasValidNumberOfCars() {
        return isValidNumberOfInjured(this.getNumberOfInjured());
    }

    /**
     * Checks if the given number of injured people is a valid number for a traffic accident.
     * @param numberOfInjured The number of injured people to validate.
     * @return True if numberOfInjured is larger or equal to zero, otherwise false.
     */
    public static boolean isValidNumberOfInjured(long numberOfInjured) {
        return (numberOfInjured >= 0);
    }

    /**
     * Checks if the number of injured people is valid.
     * @return True if the number of injured people is valid, otherwise false.
     * @note This method tests an invariant, the result must always be true.
     */
    public boolean hasValidNumberOfInjured() {
        return isValidNumberOfInjured(this.getNumberOfInjured());
    }
}
