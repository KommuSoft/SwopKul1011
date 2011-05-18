package projectswop20102011.domain;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidLocationException;

/**
 * A class that represents an emergency.
 * @invar The location of an emergency is always valid.
 *		| isValidLocation(getLocation())
 * @invar The severity of an emergency is always valid.
 *		| isValidSeverity(getSeverity())
 * @invar The status of an emergency is always valid.
 *		| isValidStatus(getStatus())
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class Emergency extends Sendable {

    /**
     * A variable registering the location of this emergency.
     */
    private GPSCoordinate location;
    /**
     * A variable registering the severity of this emergency.
     */
    private SendableSeverity severity;
    /**
     * A variable registering the status of this emergency.
     */
    private SendableStatus status;
    /**
     * A variable registering the units needed to handle the emergency
     * and does the management of dispatching units and setting the status of this emergency.
     */
    private ConcreteUnitsNeeded unitsNeeded;
    /**
     * A variable registering whether this emergency is part of a Disaster
     */
    private boolean partOfDisaster;

    /**
     * Make a new emergency with the given location, severity and description.
     *
     * @param location
     *		The location of this emergency.
     * @param severity
     *		The severity of this emergency.
     * @param description
     *		The description of this emergency.
     * @effect This location is equal to the parameter location.
     *		|location.equals(this.getLocation())
     * @effect This severity is equal to the parameter severity.
     *		|severity.equals(this.getSeverity())
     * @effect This status is equal to the EmergencyStatus RECORDED_BUT_UNHANDLED.
     *		|getStatus().equals(EmergencyStatus.RECORDED_BUT_UNHANDLED)
     * @post This description is equal to the given description.
     *		|new.getDescription().equals(description)
     * @throws InvalidLocationException
     *		If the given location is an invalid location for an emergency.
     * @throws InvalidEmergencySeverityException
     *		If the given severity is an invalid severity for an emergency.
     */
    protected Emergency(GPSCoordinate location, SendableSeverity severity, String description) throws InvalidLocationException, InvalidSendableSeverityException {
        setLocation(location);
        setSeverity(severity);
        setDescription(description);
        setIsPartOfADisaster(false);
        try {
            setStatus(SendableStatus.RECORDED_BUT_UNHANDLED);
        } catch (InvalidSendableStatusException ex) {
            //Can't be thrown: We ensure that the status is valid.
            Logger.getLogger(Emergency.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sets the location of this emergency.
     * @param location
     *		The location of this emergency.
     * @post This location is equal to the given location.
     *		| location.equals(getLocation())
     * @throws InvalidLocationException
     *		If the given location isn't valid for an emergency.
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
     * @post This severity level is equal to the given severity level.
     *		| severity.equals(getSeverity())
     * @throws InvalidEmergencySeverityException
     *		If the given severity level is invalid for an emergency.
     */
    private void setSeverity(SendableSeverity severity) throws InvalidSendableSeverityException {
        if (!isValidSeverity(severity)) {
            throw new InvalidSendableSeverityException(String.format("\"%s\" is an invalid location for an emergency.", severity));
        }
        this.severity = severity;
    }

    /**
     * Sets the status of this emergency.
     * @param status
     *		The status of this emergency.
     * @post This status is equal to the given status.
     *		| status.equals(getStatus())
     * @throws InvalidEmergencyStatusException
     *		If the given status is invalid for an emergency.
     */
    void setStatus(SendableStatus status) throws InvalidSendableStatusException {
        if (!isValidStatus(status)) {
            throw new InvalidSendableStatusException(String.format("\"%s\" is an invalid status for an emergency.", status));
        }
        this.status = status;
    }

    /**
     * Sets whether this emergency is part of a disaster.
     * @param partOfADisaster
     *		The new value of the partOfDisaster indicator
     * @post This partOfDisaster indicator is equal to the given partOfADisaster indicator.
     *		| partOfDisaster.equals(partOfADisaster())
     */
    void setIsPartOfADisaster(boolean partOfADisaster) {
        this.partOfDisaster = partOfADisaster;
    }

    /**
     * Returns the part of a disaster indicator of this emergency
     * @return The part of a disaster indicator of this emergency
     */
    public boolean isPartOfADisaster() {
        return partOfDisaster;
    }

    /**
     * Returns the location of this emergency.
     * @return The location of this emergency.
     */
    @Override
    public GPSCoordinate getLocation() {
        return location;
    }

    /**
     * Returns the severity of this emergency.
     * @return The severity of this emergency.
     */
    @Override
    public SendableSeverity getSeverity() {
        return severity;
    }

    /**
     * Returns the status of this emergency.
     * @return The status of this emergency.
     */
    @Override
    public SendableStatus getStatus() {
        return status;
    }

    /**
     * Checks if the given severity is valid as severity level of an emergency.
     * @param severity
     *		The severity level to check.
     * @return True if the severity level is effective, otherwise false.
     */
    public static boolean isValidSeverity(SendableSeverity severity) {
        return (severity != null);
    }

    /**
     * Checks if the given status is a valid status of an emergency.
     * @param status
     *		The status to check.
     * @return True if the status is effective, otherwise false.
     */
    public static boolean isValidStatus(SendableStatus status) {
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
    protected abstract ConcreteUnitsNeeded calculateUnitsNeeded();

    /**
     * Returns a ConcreteUnitsNeeded structure that contains the units needed for this emergency.
     * @return A ConcreteUnitsNeeded structure that contains the units needed for this emergency.
     * @note Handling dispatching and updating the status of the emergency is also done by this object.
     * @note The visibility of this method is package. No classes outside the domain have access to the ConcreteUnitsNeeded object.
     */
    @Override
    synchronized ConcreteUnitsNeeded getUnitsNeeded() {
        if (this.unitsNeeded == null) {
            this.unitsNeeded = calculateUnitsNeeded();
        }
        return this.unitsNeeded;
    }

    /**
     * Returns a proposal generated by the policy of this constraint.
     * @param availableUnits
     *      A list of units that are available for the proposal.
     * @return A list of units proposed by the policy of this constraint.
     */
    @Override
    public Set<Unit> getPolicyProposal(Set<Unit> availableUnits) {
        return this.getStatus().getPolicyProposal(this.getUnitsNeeded(), availableUnits);
    }

    /**
     * Returns the policy for allocation used by this emergency.
     * @return The policy for allocation used by this emergency.
     */
    public DispatchPolicy getDispatchPolicy() {
        return this.getUnitsNeeded().getPolicy();
    }

    /**
     * Returns the constraint representing what conditions need to be met before the emergency can be finished.
     * @return A DispatchUnitsConstraint representing the constraints to finish an emergency.
     */
    public DispatchUnitsConstraint getDispatchConstraint() {
        return this.getUnitsNeeded().getConstraint();
    }
}
