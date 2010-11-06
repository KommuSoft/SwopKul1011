package projectswop20102011;

import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;

/**
 * A class that represents a robbery.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class Robbery extends Emergency {

    /**
     * Indicates if this robbery is an armed robbery.
     */
    private boolean armed;
    /**
     * Indicates if this robbery is still in progress.
     */
    private boolean inProgress;

    /**
     * Makes a new robbery emergency with the given parameters.
     * @param location
     *		The location of this robbery emergency.
     * @param severity
     *		The severity of this robbery emergency.
     * @param armed
     *		An indicator that indicates if this robbery is an armed robbery.
     * @param inProgress
     *		An indicator that indicates if this robbery is still in progress.
     * @effect super(location,severity)
     * @throws InvalidLocationException If the given location is an invalid location for an emergency.
     * @throws InvalidEmergencySeverityException If the given severity is an invalid severity for an emergency.
     * @post The is armed parameter of the robery is equal to the given parameter. | armed.equals(isArmed())
	 * @post the in progress parameter of this robbery is equal to the given parameter. | inProgress.equals(isInProgress())
     */
    public Robbery(GPSCoordinate location, EmergencySeverity severity,
            boolean armed, boolean inProgress) throws InvalidLocationException, InvalidEmergencySeverityException {
        super(location, severity);
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
     * @post The is armed parameter of this robbery is equal to the given parameter. | armed.equals(isArmed())
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
     * @post the in progress parameter of this robbery is equal to the given parameter. | inProgress.equals(isInProgress())
     */
    private void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

	/**
     * Returns a string that represents the basic information of this robbery.
     * @return A string representing basic information of this robbery.
     * @see PublicDisturbance.toLongInformationString
	 */
    //TODO deze code moet nog verplaatst worden
    public String toShortInformationString() {
        return String.format("[Robbery id=%s; location=%s; severity=%s; status=%s]",this.getId(),this.getLocation(),this.getSeverity(),this.getStatus());
    }

	/**
	 * Returns a string that represents all the information of this robbery.
	 * @return A string that represents all the information of this robbery.
	 * @see PublicDisturbance.toShortInformationString
	 */
    //TODO deze code moet nog verplaatst worden
    public String toLongInformationString() {
        return String.format("[Robbery id=%s; location=%s; severity=%s; status=%s; armed=%s; in_progress=%s]",this.getId(),this.getLocation(),this.getSeverity(),this.getStatus(),this.isArmed(),this.isInProgress());
    }


	/**
	 * Calculates the units needed for this robbery.
	 * @return The units needed for this robbery.
	 */
	@Override
	public UnitsNeeded calculateUnitsNeeded() {
		int size = 1;
		Class units[] = new Class[size];
		long numbersNeeded[] = new long[size];
		units[0] = Policecar.class;

		if(!isArmed() || !isInProgress()){
			numbersNeeded[0] = 1;
		} else {
			numbersNeeded[0] = 3;
		}

		return new UnitsNeeded(numbersNeeded, units);
	}
}
