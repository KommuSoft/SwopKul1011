package projectswop20102011;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

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
     * Returns a hashtable that represents all the information of this robbery.
	 * This hashtable contains the id, location, severity, status, working units,
	 * a boolean representing if the robbery is armed and one if the robbery is in progress.
     * @return A hashtable that represents all the information of this robbery.
     */
    public Hashtable<String, String>  toLongInformationString() {
		Hashtable<String, String> information = toInformationString();

		information.put("armed", ""+isArmed());
		information.put("in progress", ""+isInProgress());

		return information;
    }

    /**
     * Calculates the units needed for this robbery.
     * @return The units needed for this robbery.
     */
    @Override
    protected UnitsNeeded calculateUnitsNeeded() {
        try {
            if (!isArmed() || !isInProgress()) {
                return new UnitsNeeded(this, new Class[]{Policecar.class, Ambulance.class, Firetruck.class}, new long[]{1, 0, 0});
            } else {
                return new UnitsNeeded(this, new Class[]{Policecar.class, Ambulance.class, Firetruck.class}, new long[]{3, 0, 0});
            }
        } catch (InvalidEmergencyException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidUnitsNeededException ex) {
            //we assume this can't happen
            Logger.getLogger(Robbery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
