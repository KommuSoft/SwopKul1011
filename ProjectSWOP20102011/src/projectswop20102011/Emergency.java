package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidLocationException;

/**
 * A class that represents an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The location of an emergency is always valid. | hasValidLocation()
 * @invar The severity of an emergency is always valid. | hasValidSeverity()
 * @invar The status of an emergency is always valid. | hasValidEmergency()
 * @invar Every emergency has a unique id.
 */
public abstract class Emergency {

    /**
     * A static field that generates id's for the constructed emergencies,
     * and ensures that every emergency has a different id.
     */
    private static long idDispatcher = 0;
    /**
     * A variable registering the id of this emergency
     */
    private final long id;
    /**
     * A variable registering the location of this emergency
     */
    private GPSCoordinate location;
    /**
     * A variable registering the severity of this emergency
     */
    private EmergencySeverity severity;
    /**
     * A variable registering the status of this emergency
     */
    private EmergencyStatus status;

    /**
     * Make a new emergency with the given location, severity
     * @param location
     *		The location of this emergency.
     * @param severity
     *		The severity of this emergency.
     * @throws InvalidLocationException If the given location is an invalid location for an emergency.
     * @throws InvalidEmergencySeverityException If the given severity is an invalid severity for an emergency.
     * @post This location is equal to the parameter location. |location.equals(this.getLocation())
     * @post This severity is equal to the parameter severity. |severity.equals(this.getSeverity())
     */
    protected Emergency(GPSCoordinate location, EmergencySeverity severity) throws InvalidLocationException, InvalidEmergencySeverityException {
        this.id = idDispatcher++;
        setLocation(location);
        setSeverity(severity);
        try {
            setStatus(EmergencyStatus.RECORDED_BUT_UNHANDLED);
        } catch (InvalidEmergencyStatusException ex) {
            //Can't be thrown: We ensure that the status is valid.
            Logger.getLogger(Emergency.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the location of this emergency.
     * @param location
     *		The location of this emergency.
     * @throws InvalidLocationException If the given location isn't valid for an emergency.
     * @post This location is equal to the given location. | location.equals(getLocation())
     */
    private void setLocation(GPSCoordinate location) throws InvalidLocationException {
        if(!isValidLocation(location)) {
            throw new InvalidLocationException(String.format("\"%s\" is an invalid location for an emergency.", location));
        }
        this.location = location;
    }

    /**
     * Sets the severity of this emergency.
     * @param severity
     *		The severity of this emergency.
     * @throws InvalidEmergencySeverityException If the given severity level is invalid for an emergency.
     * @post This severity level is equal to the given severity level. | severity.equals(getSeverity())
     */
    private void setSeverity(EmergencySeverity severity) throws InvalidEmergencySeverityException {
        if(!isValidSeverity(severity)) {
            throw new InvalidEmergencySeverityException(String.format("\"%s\" is an invalid location for an emergency.", severity));
        }
        this.severity = severity;
    }

    /**
     * Sets the status of this emergency.
     * @param status
     *		The status of this emergency.
     * @throws InvalidEmergencyStatusException If the given staus is invalid for an emergency.
     * @post This status is equal to the given status. | status.equals(getStatus())
     */
    private void setStatus(EmergencyStatus status) throws InvalidEmergencyStatusException {
        if(!isValidStatus(status)) {
            throw new InvalidEmergencyStatusException(String.format("\"%s\" is an invalid status for an emergency.", status));
        }
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
    public long getId() {
        return id;
    }

    /**
     * Checks if the given severity is valid as severity level of an emergency.
     * @param severity The severity level to check.
     * @return true if the severity level is effective, otherwise false.
     */
    public static boolean isValidSeverity(EmergencySeverity severity) {
        return (severity != null);
    }

    /**
     * Checks if the current severity level is valid.
     * @return True if the severity level is valid, otherwise false.
     * @note This method tests an invariant and so must be always true.
     */
    public boolean hasValidSeverity() {
        return isValidSeverity(this.getSeverity());
    }

    /**
     * Cehcs if the given status is a valid status of an emergency.
     * @param status The status to check.
     * @return true if the status is effective, otherwise false.
     */
    public static boolean isValidStatus(EmergencyStatus status) {
        return (status != null);
    }

    /**
     * Checs if the current status is valid.
     * @return True if the current status is valid, otherwise false.
     * @note This method tests an invariant and so must be always true.
     */
    public boolean hasValidStatus() {
        return isValidStatus(this.getStatus());
    }

    /**
     * Checks if the given location is a valid location for an emergency.
     * @param location The location to test.
     * @return True if the location is effective, otherwise false.
     */
    public static boolean isValidLocation(GPSCoordinate location) {
        return (location != null);
    }

    /**
     * Checks if the current location is a valid location.
     * @return True if this location is a valid location, otherwise false.
     * @note This method tests an invariant and so must be always true.
     */
    public boolean hasValidLocation() {
        return isValidLocation(this.getLocation());
    }
}
