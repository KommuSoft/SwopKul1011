package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

public abstract class Sendable implements Targetable {

	private String description;

	/**
	 * Returns the location of this emergency.
	 * @return The location of this emergency.
	 */
	public abstract GPSCoordinate getLocation();

        /**
         * Return the location of the Sendable.
         * @return the location of the Sendable.
         */
        public GPSCoordinate getTargetLocation () {
            return this.getLocation();
        }

	/**
	 * Returns the severity of this emergency.
	 * @return The severity of this emergency.
	 */
	public abstract EmergencySeverity getSeverity();

	/**
	 * Returns the status of this emergency.
	 * @return The status of this emergency.
	 */
	public abstract EmergencyStatus getStatus();

	/**
	 * Returns the description of this emergency.
	 * @return The description of this emergency.
	 */
	public String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Assigning units to this emergency.
	 * @param units
	 *      A list of units to assign.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of this emergency does not allow this action.
	 * @throws InvalidEmergencyException
	 *		If the emergency is invalid.
	 */
	public abstract void assignUnits(Set<Unit> units) throws InvalidEmergencyStatusException, InvalidEmergencyException;

	/**
	 * Returns a hashtable that contains the information of this emergency.
	 * This hashtable contains the id, location, severity, status and the working units.
	 * @return A hashtable that contains the information of this emergency.
	 */
	public Hashtable<String, String> getShortInformation() {
		return getInformation();
	}

	protected Hashtable<String, String> getInformation() {
		Hashtable<String, String> information = new Hashtable<String, String>();
		information.put("type", this.getClass().getSimpleName());
		information.put("location", getLocation().toString());
		information.put("severity", getSeverity().toString());
		information.put("status", getStatus().toString());
		information.put("description", getDescription().toString());
		ArrayList<Unit> units = this.getWorkingUnits();
		int number = units.size();
		StringBuilder sbWorkingUnits = new StringBuilder("[ ");
		if (number > 0) {
			for (int i = 0; i < number - 1; i++) {
				sbWorkingUnits.append(units.get(i).getName() + ", ");
			}
			sbWorkingUnits.append(units.get(number - 1).getName());
		}
		sbWorkingUnits.append(" ]");
		information.put("working units", sbWorkingUnits.toString());

		return information;
	}

	/**
	 * Returns a hashtable that contains the information of this emergency.
	 * This hashtable contains the id, type, location, severity, status, the working units and specific elements of the child of this emergency.
	 * @return A hashtable that contains the information of this emergency.
	 */
	public abstract Hashtable<String, String> getLongInformation();

	/**
	 * Checks if the given unit can assign the given list of units to the emergency.
	 * @param units
	 *      A list of units to check.
	 * @return True if the given list of units can be assigned, otherwise false.
	 */
	public abstract boolean canAssignUnits(Set<Unit> units);

	/**
	 * Returns a proposal generated by the policy of this constraint.
	 * @param availableUnits
	 *      A list of units that are available for the proposal.
	 * @return A list of units proposed by the policy of this constraint.
	 */
	public abstract Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits);

	/**
	 * Checks if this emergency can be resolved with a given collection of all available units.
	 * @param availableUnits
	 *		All the available units in the world.
	 * @return True if the given emergency can be resolved, otherwise false.
	 */
	public abstract boolean canBeResolved(Collection<Unit> availableUnits);

	/**
	 * Tests if this emergency is partially assigned.
	 * @return True if this emergency is partially assigned, otherwise false.
	 */
	public boolean isPartiallyAssigned(){
		return (this.getWorkingUnits().size() > 0 && !this.canBeResolved(new ArrayList<Unit>()));
	}

	/**
	 * Returns a list of units currently working at this emergency.
	 * @return a list of units currently working at this emergency.
	 */
	public abstract ArrayList<Unit> getWorkingUnits();

	/**
	 * Enable a unit to finish his job for this emergency.
	 * @param unitToFinish
	 *      The unit that wants to finish this emergency.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of this emergency does not allow this action.
	 * @note This method has a package visibility: Units need to finish on their own and call this method to register this to the emergency.
	 */
	abstract void finishUnit(Unit unitToFinish) throws InvalidEmergencyStatusException;

	/**
	 * Withdraws a unit from this emergency.
	 * @param unitToWithdraw
	 *      The unit that wants to withdraw from this emergency.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of this emergency does not allow this action.
	 * @note This method has a package visibility: Units need to call withdraw and call this method to register this to the emergency.
	 */
	abstract void withdrawUnit(Unit unitToWithdraw) throws InvalidEmergencyStatusException;
}
