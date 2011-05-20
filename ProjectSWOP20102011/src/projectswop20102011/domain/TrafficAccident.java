package projectswop20102011.domain;

import projectswop20102011.domain.validators.NumberDispatchUnitsConstraint;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.domain.validators.AndDispatchUnitsConstraint;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import java.io.InvalidClassException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.FiretruckWaterUnitValidator;
import projectswop20102011.domain.validators.MinMaxNumberDispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a traffic accident.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar The number of cars is always valid.
 *		| isValidNumberOfCars(getNumberOfCars())
 * @invar The number of injured is always valid.
 *		| isValidNumberOfInjured(getNumberOfInjured())
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
	 * @param description
	 *		The description of this traffic accident emergency.
	 * @param numberOfCars
	 *		The number of cars involved in this traffic accident.
	 * @param numberOfInjured
	 *		The number of injured people of this traffic accident.
	 * @effect The new traffic accident is a new emergency with the given location and severity.
	 *		| super(location,severity)
	 * @effect The number of involved cars in this traffic accident is equal to the given number of cars.
	 *		| setNumberOfCars(numberOfCars)
	 * @effect The number of injured people in this traffic accident is equal to the given number of people.
	 *		| setNumberOfInjured(numberOfInjured)
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for an emergency.
	 * @throws InvalidEmergencySeverityException
	 *		If the given severity is an invalid severity for an emergency.
	 * @throws NumberOutOfBoundsException
	 *		If the number of involved cars or injured people is invalid.
	 *
	 */
	public TrafficAccident(GPSCoordinate location, SendableSeverity severity, String description,
			long numberOfCars, long numberOfInjured) throws InvalidLocationException, InvalidSendableSeverityException, NumberOutOfBoundsException {
		super(location, severity, description);
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
	 * @throws NumberOutOfBoundsException
	 *		If the number of cars is invalid.
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
	 * @throws NumberOutOfBoundsException
	 *		If the number of cars is invalid.
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
	 * Returns a hashtable that represents all the information of the traffic accident.
	 * This hashtable contains the id, location, severity, status, the working units, the number of cars and the number of injured.
	 * @return A hashtable representing all the information of the traffic accident.
	 */
	@Override
	public Hashtable<String, String> getLongInformation() {
		Hashtable<String, String> information = getInformation();

		information.put("number of cars", "" + getNumberOfCars());
		information.put("number of injured", "" + getNumberOfInjured());

		return information;
	}

	/**
         * Caclulates the number of patients of this emergency.
         * @return The number of patients of this emergency.
         */
        private long calculateNumberOfPatients() {
		return getNumberOfInjured();
	}

	/**
	 * Calculates the concrete units needed for this traffic accident.
	 * @return The concrete units needed for this traffic accident.
	 */
	@Override
	protected ConcreteUnitsNeeded calculateUnitsNeeded() {
		try {
			long minimum = Ambulance.getMinimumNumberOfAmbulances(this.calculateNumberOfPatients());
			long maximum = Ambulance.getMaximumNumberOfAmbulances(this.calculateNumberOfPatients());
			DispatchUnitsConstraint fir = new MinMaxNumberDispatchUnitsConstraint(new FiretruckWaterUnitValidator(), 1000, Long.MAX_VALUE);
			DispatchUnitsConstraint amb = new MinMaxNumberDispatchUnitsConstraint(new TypeUnitValidator(Ambulance.class), minimum, maximum);
			DispatchUnitsConstraint pol = new NumberDispatchUnitsConstraint(new TypeUnitValidator(Policecar.class), (this.getNumberOfCars() + 1) / 2);
			ConcreteUnitsNeeded un = new ConcreteUnitsNeeded(this, new AndDispatchUnitsConstraint(fir, amb, pol));
			return un;
		} catch (InvalidEmergencyException ex) {
			//we assume this can't happen
			Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidDispatchUnitsConstraintException ex) {
			//we assume this can't happen
			Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NumberOutOfBoundsException ex) {
			//we assume this can't happen
			Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidValidatorException ex) {
			//we assume this can't happen
			Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidClassException ex) {
			//we assume this can't happen
			Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidConstraintListException ex) {
			//we assume this can't happen
			Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
