package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencyException;

/**
 * A class that represents a robbery.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class Robbery extends Emergency{
	/**
	 * Indicates if this robbery is an armed robbery.
	 */
	private boolean armed;
	/**
	 * Indicates if this robbery is still in progress.
	 */
	private boolean inProgress;

	/**
	 * Makes a new robbery emergency with the given parameters and put this new
	 * robbery emergency in the given emergencylist. A valid id is automatically assigned
	 * to this new robbery emergency.
	 * @param emergencies
	 *		The list of emergencies where this robbery emergency must be put in.
	 * @param location
	 *		The location of this robbery emergency.
	 * @param severity
	 *		The severity of this robbery emergency.
	 * @param armed
	 *		An indicator that indicates if this robbery is an armed robbery.
	 * @param inProgress
	 *		An indicator that indicates if this robbery is still in progress.
	 * @throws InvalidEmergencyException
	 *		If the given emergency is invalid. I.e. there already exists an emergency
	 *		with the same id.
	 */
	public Robbery(EmergencyList emergencies, GPSCoordinate location, EmergencySeverity severity,
			boolean armed, boolean inProgress) throws InvalidEmergencyException{
		this(emergencies, location, severity, emergencies.calculateValidId(), armed, inProgress);
	}

	/**
	 * Makes a new robbery emergency with the given parameters and put this new
	 * robbery emergency in the given emergencylist if the given id is valid.
	 * @param emergencies
	 *		The list of emergencies where this robbery emergency must be put in.
	 * @param location
	 *		The location of this robbery emergency.
	 * @param severity
	 *		The severity of this robbery emergency.
	 * @param id
	 *		The id of this robbery emergency.
	 * @param armed
	 *		An indicator that indicates if this robbery is an armed robbery.
	 * @param inProgress
	 *		An indicator that indicates if this robbery is still in progress.
	 * @throws InvalidEmergencyException
	 *		If the given emergency is invalid. I.e. there already exists an emergency
	 *		with the same id.
	 */
	public Robbery(EmergencyList emergencies, GPSCoordinate location, EmergencySeverity severity,
			long id, boolean armed, boolean inProgress) throws InvalidEmergencyException{
		super(emergencies, location, severity, id);
		setArmed(armed);
		setInProgress(inProgress);
	}

	/**
	 * Indicates whether this robbery is an armed robbery.
	 * @return True if this robbery is an armed robbery; false otherwise.
	 */
	public boolean isArmed() {
		return armed;
	}

	/**
	 * Sets the armed indicator to the given value.
	 * @param armed
	 *		The new value of the armed indicator.
	 */
	private void setArmed(boolean armed) {
		this.armed = armed;
	}

	/**
	 * Indicates whether this robbery is still in progress.
	 * @return True if this robbery is still in progress; false otherwise.
	 */
	public boolean isInProgress() {
		return inProgress;
	}

	/**
	 * Sets the in progress indicator to the given value.
	 * @param inProgress
	 *		The new value of the progress indicator.
	 */
	private void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}
}