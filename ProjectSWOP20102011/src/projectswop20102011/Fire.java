package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A class that represents a fire.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The fire size is always valid. | isValidFireSize(getSize())
 * @invar The number of injured people is always valid. | isValidNumberOfInjured(getNumberOfInjured())
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
     * A variable registering whether there are trapped people or not.
     */
    private boolean trappedPeople;
    /**
     * A variable registering the number of injured people of this fire.
     */
    private long numberOfInjured;

    /**
     * Make a new fire emergency with the given parameters.
	 *
     * @param location
     *		The location of this fire emergency.
     * @param severity
     *		The severity of this fire emergency.
     * @param size
     *		The size of this fire emergency.
     * @param chemical
     *		Indicates whether this fire emergency is a chemical fire or not.
     * @param trappedPeople
     *		Indicates whether this fire emergency has trapped people or not.
     * @param numberOfInjured
     *		The number of injured people of this fire emergency.
     * @effect super(location,severity)
     * @throws InvalidLocationException If the given location is an invalid location for an emergency.
     * @throws InvalidEmergencySeverityException If the given severity is an invalid severity for an emergency.
     * @throws InvalidFireSizeException If the given fire size isn't a valid fire size for a fire emergency.
     * @throws NumberOutOfBoundsException If the given number of injured people is smaller than zero.
     * @post The number of injured people of the fire emergency is equal to the given number. | numberOfInjured.equals(getNumberOfInjured())
     * @post The fire size of this fire emergency is equal to the given fire size. | size.equals(getSize())
     * @post The chemical property of the fire emergency is equal to the given chemical property. | chemical.equals(isChemical())
     * @post The trapped people property of the fire emergency is equal to the given trapped people property. | trappedPeople.equals(hasTrappedPeople())
     */
    public Fire(GPSCoordinate location, EmergencySeverity severity,
            FireSize size, boolean chemical, boolean trappedPeople, long numberOfInjured) throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        super(location, severity);
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
     * @post The chemical property of the fire emergency is equal to the given chemical property. | chemical.equals(isChemical())
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
     * @throws NumberOutOfBoundsException If the given number of injured people is smaller than zero.
     * @post The number of injured people of the fire emergency is equal to the given number. | numberOfInjured.equals(getNumberOfInjured())
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
     * @throws InvalidFireSizeException If the given fire size isn't a valid fire size for a fire emergency.
     * @post The fire size of this fire emergency is equal to the given fire size. | size.equals(getSize())
     */
    private void setSize(FireSize size) throws InvalidFireSizeException {
        if (!isValidFireSize(size)) {
            throw new InvalidFireSizeException(String.format("\"%s\" isn't a valid fire size for a fire emergency.", size));
        }
        this.size = size;
    }

    /**
     * Indicates whether there are trapped people in this fire emergency.
     * @return True if this fire emergency has trapped people; false otherwise.
     */
    public boolean hasTrappedPeople() {
        return trappedPeople;
    }

    /**
     * Sets the trapped people indicator to the given value.
     * @param trappedPeople
     *		The new value of the trapped people indicator.
     * @post The trapped people property of the fire emergency is equal to the given trapped people property. | trappedPeople.equals(hasTrappedPeople())
     */
    private void setTrappedPeople(boolean trappedPeople) {
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
     * Checks if the given number of injured people is a valid number for a fire emergency.
     * @param numberOfInjured
	 *		The number of injured people to validate.
     * @return True if injured people is larger or equal to zero, otherwise false.
     */
    public static boolean isValidNumberOfInjured(long numberOfInjured) {
        return (numberOfInjured >= 0);
    }

    /**
     * Returns a string that represents the basic information of the Fire (type,location,severity).
     * @return A string representing basic information of the Fire.
     * @see Fire.toLongInformationString
     */
    //TODO deze code moet nog verplaatst worden
    public String toShortInformationString() {
        return String.format("[Fire id=%s; location=%s; severity=%s; status=%s]",this.getId(),this.getLocation(),this.getSeverity(),this.getStatus());
    }

	/**
	 * Returns a string that represents all the information of the Fire.
	 * @return A string that represents all the information of the Fire.
	 * @see Fire.toShortInformationString
	 */
    //TODO deze code moet nog verplaatst worden
    public String toLongInformationString() {
        return String.format("[Fire id=%s; location=%s; severity=%s; status=%s; size=%s; chemical=%s; trapped_people=%s numberOfInjured=%s]",this.getId(),this.getLocation(),this.getSeverity(),this.getStatus(),this.getSize(),this.isChemical(),this.hasTrappedPeople(),this.getNumberOfInjured());
    }

	/**
	 * Calculates the units needed for this fire.
	 * @return The units needed for this fire.
	 */
	@Override
	protected UnitsNeeded calculateUnitsNeeded() {
		int fireSize;
		long ambulances = getNumberOfInjured();
		if(hasTrappedPeople()){
			++ambulances;
		}

		Class units[] = null;
		long numbersNeeded[] = null;

		if(ambulances > 0){
			if(getSize() == FireSize.LOCAL){
				fireSize = 2;
				units = new Class[fireSize];
				numbersNeeded = new long[fireSize];
				units[1] = Firetruck.class;
				numbersNeeded[1] = 1;
			} else if(getSize() == FireSize.HOUSE){
				fireSize = 3;
				units = new Class[fireSize];
				numbersNeeded = new long[fireSize];
				units[1] = Firetruck.class;
				units[2] = Policecar.class;
				numbersNeeded[1] = 2;
				numbersNeeded[2] = 1;
			} else if(getSize() == FireSize.FACILITY){
				fireSize = 3;
				units = new Class[fireSize];
				numbersNeeded = new long[fireSize];
				units[1] = Firetruck.class;
				units[2] = Policecar.class;
				numbersNeeded[1] = 4;
				numbersNeeded[2] = 3;
			}
			units[0] = Ambulance.class;
			numbersNeeded[0] = ambulances;
		} else {
			if(getSize() == FireSize.LOCAL){
				fireSize = 1;
				units = new Class[fireSize];
				numbersNeeded = new long[fireSize];
				units[0] = Firetruck.class;
				numbersNeeded[0] = 1;
			} else if(getSize() == FireSize.HOUSE){
				fireSize = 2;
				units = new Class[fireSize];
				numbersNeeded = new long[fireSize];
				units[0] = Firetruck.class;
				units[1] = Policecar.class;
				numbersNeeded[0] = 2;
				numbersNeeded[1] = 1;
			} else if(getSize() == FireSize.FACILITY){
				fireSize = 2;
				units = new Class[fireSize];
				numbersNeeded = new long[fireSize];
				units[0] = Firetruck.class;
				units[1] = Policecar.class;
				numbersNeeded[0] = 4;
				numbersNeeded[1] = 3;
			}
		}
		try {
			return new UnitsNeeded(this, units, numbersNeeded);
		} catch (InvalidEmergencyException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidUnitsNeededException ex) {
			Logger.getLogger(Fire.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
