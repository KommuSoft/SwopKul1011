/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectswop20102011;

/**
 *
 * @author jonas
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
	public Emergency(GPSCoordinate location, Severity severity){
		setLocation(location);
		setSeverity(severity);
	}

	/**
	 * Set the location of this emergency.
	 * @param location
	 */
	private void setLocation(GPSCoordinate location) {
		this.location = location.clone();
	}

	private void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public GPSCoordinate getLocation() {
		return location;
	}

	public Severity getSeverity() {
		return severity;
	}
}
