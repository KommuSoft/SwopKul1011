package projectswop20102011.domain;

import projectswop20102011.domain.validators.NumberDispatchUnitsConstraint;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.domain.validators.AndDispatchUnitsConstraint;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import java.io.InvalidClassException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitValidatorException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
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
	public TrafficAccident(GPSCoordinate location, EmergencySeverity severity, String description,
			long numberOfCars, long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException {
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
		information.put("description", "" + getDescription());

		return information;
	}

	/**
	 * Calculates the units needed for this traffic accident.
	 * @return The units needed for this traffic accident.
	 */
	@Override
	protected UnitsNeeded calculateUnitsNeeded() { //TODO hier gebeurt iets nutteloos (zie hint)
		try {
			DispatchUnitsConstraint fir = new NumberDispatchUnitsConstraint(new TypeUnitValidator(Policecar.class), 1);
			DispatchUnitsConstraint amb = new NumberDispatchUnitsConstraint(new TypeUnitValidator(Policecar.class), this.getNumberOfInjured());
			DispatchUnitsConstraint pol = new NumberDispatchUnitsConstraint(new TypeUnitValidator(Policecar.class), (this.getNumberOfCars() + 1) / 2);
			UnitsNeeded un = new UnitsNeeded(this, new AndDispatchUnitsConstraint(fir, amb, pol));
			new DefaultDispatchPolicy(un); //TODO kan dit niet mooier
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
		} catch (InvalidUnitValidatorException ex) {
			//we assume this can't happen
			Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidClassException ex) {
			//we assume this can't happen
			Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidConstraintListException ex) {
			//we assume this can't happen
			Logger.getLogger(TrafficAccident.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidUnitsNeededException ex) {
			//we asume this can't happen
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidDispatchPolicyException ex) {
			//we asume this can't happen
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
