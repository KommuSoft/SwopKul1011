package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

public class Disaster implements Sendable {

	private final String description;
	private final List<Emergency> emergencies;

	public Disaster(List<Emergency> emergencies, String description) {
		this.emergencies = emergencies;
		this.description = description;
	}

	private List<Emergency> getEmergencies() {
		return emergencies;
	}

	/**
	 * Returns the location of this emergency.
	 * @return The location of this emergency.
	 */
	public GPSCoordinate getLocation() {
		long x = 0;
		long y = 0;

		for (Emergency e : getEmergencies()) {
			x += e.getLocation().getX();
			y += e.getLocation().getY();
		}

		x /= getEmergencies().size(); //todo heeft afronding hier fouten?
		y /= getEmergencies().size(); //todo heeft afronding hier fouten?

		return new GPSCoordinate(x, y);
	}

	/**
	 * Returns the severity of this emergency.
	 * @return The severity of this emergency.
	 */
	public EmergencySeverity getSeverity() {
		EmergencySeverity max = EmergencySeverity.BENIGN;

		for (Emergency e : getEmergencies()) {
			if (e.getSeverity().ordinal() > max.ordinal()) {
				max = e.getSeverity();
			}
		}

		return max;
	}

	/**
	 * Returns the status of this emergency.
	 * @return The status of this emergency.
	 */
	public EmergencyStatus getStatus() {
		boolean completed = true;
		for (int i = 0; i < getEmergencies().size() - 1; ++i) {
			if (!(getEmergencies().get(i).getStatus().equals(getEmergencies().get(i + 1).getStatus()) && getEmergencies().get(i).getStatus().equals(EmergencyStatus.COMPLETED))) {
				completed = false;
			}
		}
		if (completed) {
			return EmergencyStatus.COMPLETED;
		} else {
			return EmergencyStatus.RESPONSE_IN_PROGRESS;
		}
	}

	/**
	 * Returns the description of this emergency.
	 * @return The description of this emergency.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns a UnitsNeeded structure that contains the units needed for this emergency.
	 * @return A UnitsNeeded structure that contains the units needed for this emergency.
	 * @note Handling dispatching and updating the status of the emergency is also done by this object.
	 * @note The visibility of this method is package. No classes outside the domain have access to the UnitsNeeded object.
	 */
	private synchronized List<UnitsNeeded> getUnitsNeeded() {
		ArrayList<UnitsNeeded> unitsNeeded = new ArrayList<UnitsNeeded>();

		for(Emergency e:getEmergencies()){
			unitsNeeded.add(e.calculateUnitsNeeded());
		}

		return (List<UnitsNeeded>) unitsNeeded.clone();
	}

	/**
	 * Assigning units to this emergency.
	 * @param units
	 *      A list of units to assign.
	 * @throws InvalidEmergencyStatusException
	 *      If the status of this emergency does not allow this action.
	 * @throws Exception
	 *      If another exception is thrown by performing this operation (for instance those units are already assigned).
	 */
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
	void withdrawUnit(Unit unitToWithdraw) throws InvalidEmergencyStatusException {
		this.getStatus().withdrawUnit(this.getUnitsNeeded(), unitToWithdraw);
	}

	/**
	 * Returns a hashtable that contains the information of this emergency.
	 * This hashtable contains the id, location, severity, status and the working units.
	 * @return A hashtable that contains the information of this emergency.
	 */
	public Hashtable<String, String> getShortInformation() {
		return getInformation();
	}

	/**
	 * Returns a hashtable that contains the information of this emergency.
	 * This hashtable contains the id, type, location, severity, status, the working units and specific elements of the child of this emergency.
	 * @return A hashtable that contains the information of this emergency.
	 */
	public abstract Hashtable<String, String> getLongInformation();

	/**
	 * Returns a hashtable that contains the information of this emergency.
	 * This hashtable contains the id, location, severity, status and the working units.
	 * @return A hashtable that contains the information of this emergency.
	 */
	protected Hashtable<String, String> getInformation() {
		Hashtable<String, String> information = new Hashtable<String, String>();
		//todo moeten hier nog alle emergencies in staan?
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
	 * Returns a list of units currently working at this emergency.
	 * @return a list of units currently working at this emergency.
	 */
	public ArrayList<Unit> getWorkingUnits() {
		return this.getUnitsNeeded().getWorkingUnits();
	}

	/**
	 * Checks if the given unit can assign the given list of units to the emergency.
	 * @param units
	 *      A list of units to check.
	 * @return True if the given list of units can be assigned, otherwise false.
	 */
	public boolean canAssignUnits(Set<Unit> units) {
		return this.getStatus().canAssignUnits(this.getUnitsNeeded(), units);
	}

	/**
	 * Returns a proposal generated by the policy of this constraint.
	 * @param availableUnits
	 *      A list of units that are available for the proposal.
	 * @return A list of units proposed by the policy of this constraint.
	 */
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
	public boolean canBeResolved(Collection<Unit> availableUnits) {
		return this.getStatus().canBeResolved(this.getUnitsNeeded(), availableUnits);
	}

	/**
	 * Tests if this emergency is partially assigned.
	 * @return True if this emergency is partially assigned, otherwise false.
	 */
	public boolean isPartiallyAssigned() {
		return (this.getWorkingUnits().size() > 0 && !this.canBeResolved(new ArrayList<Unit>()));
	}
}
