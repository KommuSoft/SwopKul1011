package projectswop20102011.domain;

import java.io.InvalidClassException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.AndDispatchUnitsConstraint;
import projectswop20102011.domain.validators.DifferentQuadraticValidator;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.domain.validators.FiretruckWaterUnitValidator;
import projectswop20102011.domain.validators.MinMaxNumberDispatchUnitsConstraint;
import projectswop20102011.domain.validators.NumberDispatchUnitsConstraint;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSendableException;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a fire.
 * @invar The fire size is always valid.
 *		| isValidFireSize(getSize()) == true
 * @invar The number of injured people is always valid.
 *		| isValidNumberOfInjured(getNumberOfInjured()) == true
 * @invar The number of trapped people is always validL
 *		| isValidTrappedPeople(getNumberOfTrappedPeople()) == true
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Fire extends Emergency {

	/**
	 * A variable registering the size of this fire.
	 */
	private FireSize size;
	/**
	 * A variable registering whether chemicals are involved or not.
	 */
	private boolean chemical;
	/**
	 * A variable registering how many trapped people there are
	 */
	private long trappedPeople;
	/**
	 * A variable registering the number of injured people of this fire.
	 */
	private long numberOfInjured;

	/**
	 * Makes a new fire emergency with the given parameters.
	 *
	 * @param location
	 *		The location of this fire emergency.
	 * @param severity
	 *		The severity of this fire emergency.
	 * @param description
	 *		The description of this fire emergency.
	 * @param size
	 *		The size of this fire emergency.
	 * @param chemical
	 *		Indicates whether this fire emergency is a chemical fire or not.
	 * @param trappedPeople
	 *		The number of trapped people in the fire.
	 * @param numberOfInjured
	 *		The number of injured people of this fire emergency.
	 * @effect The new fire is an emergency with the given location, severity and description.
	 *		| super(location,severity,description)
	 * @post The number of injured people of the fire emergency is equal to the given number.
	 *		| numberOfInjured.equals(getNumberOfInjured())
	 * @post The fire size of this fire emergency is equal to the given fire size.
	 *		| size.equals(getSize())
	 * @post The chemical property of the fire emergency is equal to the given chemical property.
	 *		| chemical.equals(isChemical())
	 * @post The trapped people property of the fire emergency is equal to the given trapped people property.
	 *		| trappedPeople.equals(hasTrappedPeople())
	 * @throws InvalidLocationException
	 *		If the given location is an invalid location for an emergency.
	 * @throws InvalidSendableSeverityException
	 *		If the given severity is an invalid severity for a sendable.
	 * @throws InvalidFireSizeException
	 *		If the given fire size isn't a valid fire size for a fire emergency.
	 * @throws NumberOutOfBoundsException
	 *		If the given number of injured people is smaller than zero or if the number of trapped people is smaller than zero.
	 */
	public Fire(GPSCoordinate location, SendableSeverity severity, String description,
			FireSize size, boolean chemical, long trappedPeople, long numberOfInjured) throws InvalidLocationException, InvalidSendableSeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		super(location, severity, description);
		setSize(size);
		setChemical(chemical);
		setTrappedPeople(trappedPeople);
		setNumberOfInjured(numberOfInjured);
	}

	/**
	 * Indicates whether this fire emergency is a chemical fire or not.
	 * @return True if this fire emergency is a chemical fire; false otherwise.
	 */
	public boolean isChemical() {
		return chemical;
	}

	/**
	 * Sets the chemical indicator to the given value.
	 * @param chemical
	 *		The new value of the chemical indicator.
	 * @post The chemical property of the fire emergency is equal to the given chemical property.
	 *		| chemical.equals(isChemical())
	 */
	private void setChemical(boolean chemical) {
		this.chemical = chemical;
	}

	/**
	 * Returns the number of injured people of this fire emergency.
	 * @return The number of injured people of this fire emergency.
	 */
	public long getNumberOfInjured() {
		return numberOfInjured;
	}

	/**
	 * Sets the number of injured people of this fire emergency to the given value.
	 * @param numberOfInjured
	 *		The new number of injured people of this fire emergency.
	 * @post The number of injured people of the fire emergency is equal to the given number.
	 *		| numberOfInjured.equals(getNumberOfInjured())
	 * @throws NumberOutOfBoundsException
	 *		If the given number of injured people is smaller than zero.
	 */
	private void setNumberOfInjured(long numberOfInjured) throws NumberOutOfBoundsException {
		if (!isValidNumberOfInjured(numberOfInjured)) {
			throw new NumberOutOfBoundsException(String.format("Number of Injured people must be larger or equal to zero and not \"%s\"", numberOfInjured));
		}
		this.numberOfInjured = numberOfInjured;
	}

	/**
	 * Returns the size of this fire emergency.
	 * @return The size of this fire emergency.
	 */
	public FireSize getSize() {
		return size;
	}

	/**
	 * Sets the size of this fire emergency to the given value.
	 * @param size
	 *		The new size of this fire emergency.
	 * @post The fire size of this fire emergency is equal to the given fire size.
	 *		| size.equals(getSize())
	 * @throws InvalidFireSizeException
	 *		If the given fire size isn't a valid fire size for a fire emergency.
	 */
	private void setSize(FireSize size) throws InvalidFireSizeException {
		if (!isValidFireSize(size)) {
			throw new InvalidFireSizeException(String.format("\"%s\" isn't a valid fire size for a fire.", size));
		}
		this.size = size;
	}

	/**
	 * Indicates the number of trapped people in the fire.
	 * @return The number of trapped people in the fire.
	 */
	public long getTrappedPeople() {
		return trappedPeople;
	}

	/**
	 * Sets the number of trapped people indicator to the given value.
	 * @param trappedPeople
	 *		The new value of the number of trapped people indicator.
	 * @post The trapped people property of the fire emergency is equal to the given trapped people property.
	 *		| trappedPeople.equals(hasTrappedPeople())
	 * @throws NumberOutOfBoundsException
	 *		If the given number of trapped people is smaller than zero.
	 */
	private void setTrappedPeople(long trappedPeople) throws NumberOutOfBoundsException {
		if (!isValidTrappedPeople(trappedPeople)) {
			throw new NumberOutOfBoundsException(String.format("Number of trapped people must be larger or equal to zero and not \"%s\"", trappedPeople));
		}
		this.trappedPeople = trappedPeople;
	}

	/**
	 * Checks if the given fire size is a valid fire size for a fire emergency.
	 * @param fireSize
	 *		The fire size to validate.
	 * @return True if fireSize is effective, otherwise false.
	 */
	public static boolean isValidFireSize(FireSize fireSize) {
		return (fireSize != null);
	}

	/**
	 * Returns the liters that are required for this fire.
	 * @return The liters that are required for this fire.
	 */
	public long getNumberOfLitersRequired() {
		return getSize().getNeededCapacity();
	}

	/**
	 * Checks if the given number of injured people is a valid number for a fire emergency.
	 * @param numberOfInjured
	 *		The number of injured people to validate.
	 * @return True if the number injured people is larger or equal to zero, otherwise false.
	 */
	public static boolean isValidNumberOfInjured(long numberOfInjured) {
		return (numberOfInjured >= 0);
	}

	/**
	 * Checks if the given number of trapped people is a valid number for a fire emergency.
	 * @param trappedPeople
	 *		The number of trapped people to validate.
	 * @return True if the number of trapped people is larger or equal to zero, otherwise false.
	 */
	public static boolean isValidTrappedPeople(long trappedPeople) {
		return (trappedPeople >= 0);
	}

	/**
	 * Returns a hashtable that represents all the information of the Fire.
	 * This hashtable contains the the size of the fire, a boolean representing if the fire is chemical,
	 * the number of trapped people and the number of injured.
	 * @return A hashtable that represents all the information of the Fire.
	 */
	@Override
	public Hashtable<String, String> getLongInformation() {
		Hashtable<String, String> information = getInformation();

		information.put("size", getSize().getTextual());
		information.put("chemical", "" + isChemical());
		information.put("trapped people", "" + getTrappedPeople());
		information.put("number of injured", "" + getNumberOfInjured());

		return information;
	}

	/**
	 * Calculates the number of patients of this emergency.
	 * @return The number of patients of this emergency.
	 */
	private long calculateNumberOfPatients() {
		return getTrappedPeople() + getNumberOfInjured();
	}

	/**
	 * Calculates the units needed for a fire.
	 * @return The units needed for a fire.
	 */
	@Override
	protected ConcreteUnitsNeeded calculateUnitsNeeded() {
		try {
			long numberOfLitersRequired = FireUnitsNeededCalculator.calculateLiters(size);
			long policecars = FireUnitsNeededCalculator.calculatePoliceCars(size);
			long minimum = Ambulance.getMinimumNumberOfAmbulances(this.calculateNumberOfPatients());
			long maximum = Ambulance.getMaximumNumberOfAmbulances(this.calculateNumberOfPatients());

			DispatchUnitsConstraint fir = new MinMaxNumberDispatchUnitsConstraint(new FiretruckWaterUnitValidator(), numberOfLitersRequired, Long.MAX_VALUE);
			DispatchUnitsConstraint amb = new MinMaxNumberDispatchUnitsConstraint(new TypeUnitValidator(Ambulance.class), minimum, maximum);
			DispatchUnitsConstraint pol = new NumberDispatchUnitsConstraint(new TypeUnitValidator(Policecar.class), policecars, new DifferentQuadraticValidator<Unit, Unit>());
			ConcreteUnitsNeeded un = new ConcreteUnitsNeeded(this, new AndDispatchUnitsConstraint(fir, amb, pol));
			un.pushPolicy(new ASAPDispatchPolicy(un, new FireSizeDispatchPolicy(un)));
			return un;
		} catch (InvalidSendableException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidDispatchUnitsConstraintException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidDispatchPolicyException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidUnitsNeededException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidConstraintListException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidClassException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NumberOutOfBoundsException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidValidatorException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		}

		//this should never happen
		return null;
	}
}
