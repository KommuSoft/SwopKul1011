package projectswop20102011;

/**
 * A class that represents a public disturbance.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class PublicDisturbance extends Emergency {

    /**
     * An estimation of the number of people that are involved in this public disturbance.
     */
    private long numberOfPeople;

    /**
     * Makes a new public disturbance emergency with the given parameters and put this new
     * public disturbance emergency in the given emergencylist if the given id is valid.
     * @param emergencies
     *		The list of emergencies where this public disturbance emergency must be put in.
     * @param location
     *		The location of this public disturbance emergency.
     * @param severity
     *		The severity of this public disturbance emergency.
     * @param numberOfPeople
     *		The number of people that are involved in this public disturbance.
     */
    public PublicDisturbance(EmergencyList emergencies, GPSCoordinate location,
            EmergencySeverity severity, long numberOfPeople) {
        super(emergencies, location, severity);
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
     */
    private void setNumberOfPeople(long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
