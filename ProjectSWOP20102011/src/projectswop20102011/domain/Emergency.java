package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
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
	private EmergencySeverity severity;
	/**
	 * A variable registering the status of this emergency.
	 */
	private EmergencyStatus status;
	/**
	 * A variable registering the units needed to handle the emergency
	 * and does the management of dispatching units and setting the status of this emergency.
	 */
	private ConcreteUnitsNeeded unitsNeeded;

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
	protected Emergency(GPSCoordinate location, EmergencySeverity severity, String description) throws InvalidLocationException, InvalidEmergencySeverityException {
		setLocation(location);
		setSeverity(severity);
		setDescription(description);
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
	 * @post This status is equal to the given status.
	 *		| status.equals(getStatus())
	 * @throws InvalidEmergencyStatusException
	 *		If the given status is invalid for an emergency.
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
	@Override
	public GPSCoordinate getLocation() {
		return location;
	}

	/**
	 * Returns the severity of this emergency.
	 * @return The severity of this emergency.
	 */
	@Override
	public EmergencySeverity getSeverity() {
		return severity;
	}

	/**
	 * Returns the status of this emergency.
	 * @return The status of this emergency.
	 */
	@Override
	public EmergencyStatus getStatus() {
		return status;
	}

	/**
	 * Checks if the given severity is valid as severity level of an emergency.
	 * @param severity
	 *		The severity level to check.
	 * @return True if the severity level is effective, otherwise false.
	 */
	public static boolean isValidSeverity(EmergencySeverity severity) {
		return (severity != null);
	}

	/**
	 * Checks if the given status is a valid status of an emergency.
	 * @param status
	 *		The status to check.
	 * @return True if the status is effective, otherwise false.
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
	protected abstract ConcreteUnitsNeeded calculateUnitsNeeded();

	/**
	 * Returns a ConcreteUnitsNeeded structure that contains the units needed for this emergency.
	 * @return A ConcreteUnitsNeeded structure that contains the units needed for this emergency.
	 * @note Handling dispatching and updating the status of the emergency is also done by this object.
	 * @note The visibility of this method is package. No classes outside the domain have access to the ConcreteUnitsNeeded object.
	 */
	synchronized ConcreteUnitsNeeded getUnitsNeeded() {
		//TODO deze methode staat nu package maar vroeger stond ze private, is dit een probleem?
		if (this.unitsNeeded == null) {
			this.unitsNeeded = calculateUnitsNeeded();
		}
		return this.unitsNeeded;
	}

	/**
	 * Assigning units to this emergency.
	 * @param units
	 *      A list of units to assign.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of this emergency does not allow this action.
	 * @throws  InvalidEmergencyException
	 *		If the emergency is invalid.
	 */
	@Override
	public void assignUnits(Set<Unit> units) throws InvalidEmergencyStatusException, InvalidEmergencyException {
		this.getStatus().assignUnits(this.getUnitsNeeded(), units);
	}

	/**
	 * Enable a unit to finish his job for this emergency.
	 * @param unitToFinish
	 *      The unit that wants to finish this emergency.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of this emergency does not allow this action.
	 * @note This method has a package visibility: Units need to finish on their own and call this method to register this to the emergency.
	 */
	@Override
	void finishUnit(Unit unitToFinish) throws InvalidEmergencyStatusException {
		this.getStatus().finishUnit(this.getUnitsNeeded(), unitToFinish);
	}

	/**
	 * Withdraws a unit from this emergency.
	 * @param unitToWithdraw
	 *      The unit that wants to withdraw from this emergency.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of this emergency does not allow this action.
	 * @note This method has a package visibility: Units need to call withdraw and call this method to register this to the emergency.
	 */
	@Override
	void withdrawUnit(Unit unitToWithdraw) throws InvalidEmergencyStatusException {
		this.getStatus().withdrawUnit(this.getUnitsNeeded(), unitToWithdraw);
	}

	/**
	 * Checks if the given unit can assign the given list of units to the emergency.
	 * @param units
	 *      A list of units to check.
	 * @return True if the given list of units can be assigned, otherwise false.
	 */
	@Override
	public boolean canAssignUnits(Set<Unit> units) {
		return this.getStatus().canAssignUnits(this.getUnitsNeeded(), units);
	}

	/**
	 * Returns a proposal generated by the policy of this constraint.
	 * @param availableUnits
	 *      A list of units that are available for the proposal.
	 * @return A list of units proposed by the policy of this constraint.
	 */
	@Override
	public Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits) {
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

	/**
	 * Checks if this emergency can be resolved with a given collection of all available units.
	 * @param availableUnits
	 *		All the available units in the world.
	 * @return True if the given emergency can be resolved, otherwise false.
	 */
	@Override
	public boolean canBeResolved(Collection<Unit> availableUnits) {
		return this.getStatus().canBeResolved(this.getUnitsNeeded(), availableUnits);
	}

	/**
	 * Returns a list of units currently working at this emergency.
	 * @return a list of units currently working at this emergency.
	 */
	@Override
	public ArrayList<Unit> getWorkingUnits(){
		return this.getUnitsNeeded().getWorkingUnits();
	}
}
