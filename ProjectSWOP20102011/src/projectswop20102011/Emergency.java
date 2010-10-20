package projectswop20102011;

/**
 * A class that represents an emergency.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class Emergency {

    private GPSCoordinate location;
    private Severity severity;

    /**
     * Make a new emergency with the given location and severity.
     * @param location
     *		The location of this emergency.
     * @param severity
     *		The severity of this emergency.
     */
    public Emergency(GPSCoordinate location, Severity severity) {
        setLocation(location);
        setSeverity(severity);
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
    private void setSeverity(Severity severity) {
        this.severity = severity;
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
    public Severity getSeverity() {
        return severity;
    }
}
