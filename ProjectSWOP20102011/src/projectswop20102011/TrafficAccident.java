package projectswop20102011;

/**
 * A class that represents a traffic accident.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class TrafficAccident extends Emergency{

	/**
	 * A variable representing the number of cars involved in this traffic accident.
	 */
	private long numberOfCars;
	/**
	 * A variable representing the number of injured people of this traffic accident.
	 */
	private long numberOfInjured;

	/**
	 * Makes a new traffic accident emergency with the given parameters and put this new
	 * traffic accident emergency in the given emergencylist. A valid id is automatically assigned
	 * to this new traffic accident emergency.
	 * @param emergencies
	 *		The list of emergencies where this traffic accident emergency must be put in.
	 * @param location
	 *		The location of this traffic accident emergency.
	 * @param severity
	 *		The severity of this traffic accident emergency.
	 * @param numberOfCars
	 *		The number of cars involved in this traffic accident.
	 * @param numberOfInjured
	 *		The number of injured people of this traffic accident.
	 * @throws InvalidEmergencyException
	 *		If the given emergency is invalid. I.e. there already exists an emergency
	 *		with the same id.
	 */
	public TrafficAccident(EmergencyList emergencies, GPSCoordinate location, EmergencySeverity severity,
			long numberOfCars, long numberOfInjured) throws InvalidEmergencyException{
		this(emergencies, location, severity, emergencies.calculateValidId(), numberOfCars, numberOfInjured);
	}

	/**
	 * Makes a new traffic accident emergency with the given parameters and put this new
	 * traffic accident emergency in the given emergencylist if the given id is valid.
	 * @param emergencies
	 *		The list of emergencies where this traffic accident emergency must be put in.
	 * @param location
	 *		The location of this traffic accident emergency.
	 * @param severity
	 *		The severity of this traffic accident emergency.
	 * @param id
	 *		The id of this traffic accident emergency.
	 * @param numberOfCars
	 *		The number of cars involved in this traffic accident.
	 * @param numberOfInjured
	 *		The number of injured people of this traffic accident.
	 * @throws InvalidEmergencyException
	 *		If the given emergency is invalid. I.e. there already exists an emergency
	 *		with the same id.
	 */
	public TrafficAccident(EmergencyList emergencies, GPSCoordinate location, EmergencySeverity severity,
			long id, long numberOfCars, long numberOfInjured) throws InvalidEmergencyException{
		super(emergencies, location, severity, id);
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