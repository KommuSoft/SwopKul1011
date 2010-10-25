package projectswop20102011;

/**
 * A class that represents a fire.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Fire extends Emergency {

    /**
     * A variable registering the size of this fire
     */
    private FireSize size;
    /**
     * A variable registering whether chemicals are involved or not
     */
    private boolean chemical;
    /**
     * A variable registering whether there are trapped people or not
     */
    private boolean trappedPeople;
    /**
     * A variable registering the number of injured people of this fire
     */
    private long numberOfInjured;

    /**
     * Make a new fire emergency with the given parameters and put this new
     * fire emergency in the given emergencylist if the given id is valid.
     * @param emergencies
     *		The list of emergencies where this fire emergency must be put in.
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
     */
    public Fire(EmergencyList emergencies, GPSCoordinate location, EmergencySeverity severity,
            FireSize size, boolean chemical, boolean trappedPeople, long numberOfInjured) {
        super(emergencies, location, severity);
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
     */
    private void setNumberOfInjured(long numberOfInjured) {
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
     */
    private void setSize(FireSize size) {
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
     */
    private void setTrappedPeople(boolean trappedPeople) {
        this.trappedPeople = trappedPeople;
    }
}