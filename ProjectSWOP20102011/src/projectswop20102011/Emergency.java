package projectswop20102011;

/**
 * A class that represents an emergency.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Emergency {
	private final long id;
    private GPSCoordinate location;
    private EmergencySeverity severity;
	private EmergencyStatus status;

    /**
     * Make a new emergency with the given location and severity.
	 * @param emergencies
	 *		The list of emergencies where this emergency must put in.
	 * @param location
     *		The location of this emergency.
     * @param severity
     *		The severity of this emergency.
     */
    public Emergency(EmergencyList emergencies, GPSCoordinate location, EmergencySeverity severity, long id) {
		this.id = id;
		emergencies.addEmergency(this);
		setLocation(location);
        setSeverity(severity);
		setStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED);
    }

    /**
     * Sets the location of this emergency.
     * @param location
     *		The location of this emergency.
     */
    private void setLocation(GPSCoordinate location) {
        this.location = location;
    }

    /**
     * Sets the severity of this emergency.
     * @param severity
     *		The severity of this emergency.
     */
    private void setSeverity(EmergencySeverity severity) {
        this.severity = severity;
    }

	/**
	 * Sets the status of this emergency.
	 * @param status
	 *		The status of this emergency.
	 */
	private void setStatus(EmergencyStatus status){
		this.status = status;
	}

    /**
     * Returns the location of this emergency.
     * @return The location of this emergency.
     */
    public GPSCoordinate getLocation() {
        return location;
    }

    /**
     * Returns the severity of this emergency.
     * @return The severity of this emergency.
     */
    public EmergencySeverity getSeverity() {
        return severity;
    }

	/**
	 * Returns the status of this emergency.
	 * @return The status of this emergency.
	 */
	public EmergencyStatus getStatus() {
		return status;
	}
	
	/**
	 * Returns the id of this emergency.
	 * @return The is of this emergency.
	 */
	public long getId(){
		return id;
	}
}
