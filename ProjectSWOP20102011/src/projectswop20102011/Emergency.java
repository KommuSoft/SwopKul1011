package projectswop20102011;

import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidLocationException;

/**
 * A class that represents an emergency.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 * @invar The location of an emergency is always valid.
 *		| isValidLocation(getLocation())
 * @invar The severity of an emergency is always valid.
 *		| isValidSeverity(getSeverity())
 * @invar The status of an emergency is always valid.
 *		| isValidStatus(getStatus())
 * @invar Every emergency has a unique id.
 */
public abstract class Emergency {

    /**
     * A static field that generates id's for the constructed emergencies,
     * and ensures that every emergency has a different id.
     */
    private static long idDispatcher = 0;
    /**
     * A variable registering the id of this emergency.
     */
    private final long id;
    /**
     * A variable registering the location of this emergency.
     */
    private GPSCoordinate location;
    /**
     * A variable registering the severity of this emergency.
     */
    private EmergencySeverity severity;
    /**
     * A variable registering the status of this emergency.
     */
    private EmergencyStatus status;
    /**
     * A variable registering the amount of units needed to handle the emergency
     * and does the management of dispatching units and setting the status of this emergency.
     */
    private UnitsNeeded unitsNeeded;
	/**
	 * A variable registering the number of units needed for this emergency.
	 */
	private UnitsNeeded unitsNeeded;

    /**
     * Make a new emergency with the given location, severity.
     *
     * @param location
     *		The location of this emergency.
     * @param severity
     *		The severity of this emergency.
     * @throws InvalidLocationException If the given location is an invalid location for an emergency.
     * @throws InvalidEmergencySeverityException If the given severity is an invalid severity for an emergency.
     * @post This location is equal to the parameter location.
     *		|location.equals(this.getLocation())
     * @post This severity is equal to the parameter severity.
     *		|severity.equals(this.getSeverity())
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
     * @post This location is equal to the given location.
     *		| location.equals(getLocation())
     */
    private void setLocation(GPSCoordinate location) throws InvalidLocationException {
        if (!isValidLocation(location)) {
            throw new InvalidLocationException(String.format("\"%s\" is an invalid location for an emergency.", location));
        }
        this.location = location;
    }

    /**
     * Sets the severity of this emergency.
     * @param severity
     *		The severity of this emergency.
     * @throws InvalidEmergencySeverityException If the given severity level is invalid for an emergency.
     * @post This severity level is equal to the given severity level.
     *		| severity.equals(getSeverity())
     */
    private void setSeverity(EmergencySeverity severity) throws InvalidEmergencySeverityException {
        if (!isValidSeverity(severity)) {
            throw new InvalidEmergencySeverityException(String.format("\"%s\" is an invalid location for an emergency.", severity));
        }
        this.severity = severity;
    }

    /**
     * Sets the status of this emergency.
     * @param status
     *		The status of this emergency.
     * @throws InvalidEmergencyStatusException If the given staus is invalid for an emergency.
     * @post This status is equal to the given status.
     *		| status.equals(getStatus())
     */
    void setStatus(EmergencyStatus status) throws InvalidEmergencyStatusException {
        if (!isValidStatus(status)) {
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
	 * Returns the units needed for this emergency.
	 * @return The units needed for this emergency.
	 */
	public UnitsNeeded getUnitsNeeded(){
		return this.unitsNeeded;
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
     * Checks if the given status is a valid status of an emergency.
     * @param status
     *		The status to check.
     * @return true if the status is effective, otherwise false.
     */
    public static boolean isValidStatus(EmergencyStatus status) {
        return (status != null);
    }

    /**
     * Checks if the given location is a valid location for an emergency.
     * @param location
     *		The location to test.
     * @return True if the location is effective, otherwise false.
     */
    public static boolean isValidLocation(GPSCoordinate location) {
        return (location != null);
    }

    /**
     * Calculates the units needed for this Emergency.
     * @return The units needed for this Emergency.
     */
    protected abstract UnitsNeeded calculateUnitsNeeded();

    /**
     * Returns a UnitsNeeded structure that contains the amount and types of units needed for this emergency.
     * @return A UnitsNeeded structure that contains the amount and types of units needed for this emergency.
     * @note Handling dispatching and updating the status of the emergency is also done by this object.
     */
    public UnitsNeeded getUnitsNeeded () {
        if(this.unitsNeeded == null) {
            this.unitsNeeded = this.calculateUnitsNeeded();
        }
        return this.unitsNeeded;
    }

    //TODO deze code moet nog verwijderd worden. Dit is een dummy zodat de userinterface geen errors geeft.
    public String toShortInformationString() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //TODO deze code moet nog verwijderd worden. Dit is een dummy zodat de userinterface geen errors geeft.
    public String toLongInformationString() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
