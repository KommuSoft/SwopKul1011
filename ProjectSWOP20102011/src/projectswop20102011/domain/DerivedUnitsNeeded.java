package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.validators.AndDispatchUnitsConstraint;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.domain.validators.EmergencyComparator;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.utils.SortedListSet;

/**
 * A class that records which units are working on an disaster and does
 *		the accounting for the units that are working on the disaster.
 * @invar The disaster is valid.
 *		| isValidDisaster(getDisaster())
 * @invar The constraint is valid.
 *		| isValidConstraint(getConstraint())
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class DerivedUnitsNeeded extends UnitsNeeded {

	/**
	 * The disaster that is handled by this DerivedUnitsNeeded.
	 */
	private final Disaster disaster;
	/**
	 * An AndDispatchUnitsConstraint object specifying which units can be allocated to the disaster.
	 */
	private final AndDispatchUnitsConstraint constraint;

	/**
	 * Creates a new object that calculates the units needed for a disaster.
	 * @param disaster
	 *		The disaster that will be handled by this DerivedUnitsNeeded.
	 * @post This disaster is set to the given disaster.
	 *		| new.getdisaster() == disaster
	 * @post The constraint is set to the constraints of the emergencies of the disaster.
	 *		| for(every Emergency e in Disaster)
	 *		|	add the constraint of e to this constraint
	 * @throws InvalidEmergencyException
	 *		If the given emergency is not effective.
	 * @throws InvalidDispatchUnitsConstraintException
	 *		If the given constraint is invalid.
	 * @note This constructor has a package visibility, only instances in the domain layer (Emergencies) can create ConcreteUnitsNeeded.
	 */
	DerivedUnitsNeeded(Disaster disaster) throws InvalidConstraintListException {
		this.disaster = disaster;
		DispatchUnitsConstraint[] constraints = new DispatchUnitsConstraint[disaster.getEmergencies().size()];

		for (int i = 0; i < disaster.getEmergencies().size(); ++i) {
			constraints[i] = disaster.getEmergencies().get(i).getDispatchConstraint();
		}
		constraint = new AndDispatchUnitsConstraint(constraints);
	}

	/**
	 * Checks if the units needed for the disaster are all finished.
	 * @return True if all units needed for the disaster are finished; otherwise false.
	 * @note If the units needed for the disaster are all finished, then the disaster of this units needed can be completed.
	 */
	//TODO: mag deze methode weg? [Jonas: van mij wel, ConcreteUnitsNeeded bevat ze ook niet meer]
	private boolean canCompleteDisaster() {
		throw new RuntimeException("Not yet implemented");
		//TODO: moet dit niet geïmplementeerd worden?
		//return getConstraint().areValidDispatchUnits(takeFinishedUnits());
	}

	/**
	 * Returns the disaster of this DerivedUnitsNeeded.
	 * @return The disaster of this DerivedUnitsNeeded.
	 */
	private Disaster getDisaster() {
		return disaster;
	}

	/**
	 * Returns the constraint of this DerivedUnitsNeeded.
	 * @return The constraint used by this DerivedUnitsNeeded.
	 */
	@Override
	DispatchUnitsConstraint getConstraint() {
		return constraint;
	}

	/**
	 * Checks if the given disaster is a valid disaster.
	 * @param disaster
	 *		The disaster to check if it is effective.
	 * @return True if the given disaster is effective; false otherwise.
	 */
	public static boolean isValidDisaster(Disaster disaster) {
		return (disaster != null);
	}

	/**
	 * Checks if the given units can be assigned to the disaster.
	 * @param units
	 *		The units to be assigned.
	 * @return True if all the given units are effective, unique and
	 *		can be assigned and the constraint for allocation is passed;
	 *		otherwise false.
	 */
	@Override
	public boolean canAssignUnitsToEmergency(Set<Unit> units) {
		SortedSet<Unit> options = new SortedListSet<Unit>(units);
		for (Emergency e : getDisaster().getEmergencies()) {
			ConcreteUnitsNeeded CUN = e.getUnitsNeeded();
			Set<Unit> unitsForEmergency = CUN.generateProposal(options);

			if (!unitsForEmergency.isEmpty() && e.canAssignUnits(unitsForEmergency)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Assign the given set of units to the disaster.
	 * @param units
	 *		The given set of units.
	 * @param eventhandler 
	 *		The eventHandler where the notifications should be sent to.
	 * @effect All the units in the given list are assigned
	 *		| forall (u in units)
	 *		|	u.isAssigned()
	 * @effect All the units in the given list are handling an emergency of the disaster of this UnitNeeded.
	 *		| forall (u in units)
	 *		|	u.getManagingSendable().equals(this.getDisaster())
	 * @throws InvalidEmergencyException
	 *		If the units can't be assigned to the emergency (when canAssignUnitsToEmergency fails)
	 */
	@Override
	public synchronized void assignUnitsToSendable(Set<Unit> units, EventHandler eventhandler) throws InvalidEmergencyException {
		SortedSet<Unit> options = new SortedListSet<Unit>();
		options.addAll(units);
		if (!canAssignUnitsToEmergency(units)) {
			throw new InvalidEmergencyException("Units can't be assigned to the emergency, harm to assignment constraints.");
		}

		List<Emergency> emergencies = getDisaster().getEmergencies();
		Collections.sort(emergencies, new EmergencyComparator());
		Collections.reverse(emergencies);

		for (Emergency e : emergencies) {
			ConcreteUnitsNeeded CUN = e.getUnitsNeeded();
			Set<Unit> unitsForEmergency = CUN.generateProposal(options);
			CUN.assignUnitsToSendable(unitsForEmergency, eventhandler);
			options.removeAll(unitsForEmergency);
		}
	}

	/**
	 * A method called when a unit finishes his job to manage the disaster.
	 * @param unit
	 *		The unit that finishes his job.
	 * @parem eventHandler
	 *		The evenhandler where the notifications should be sent to.
	 * @effect The unit finishes its job in the emergency.
	 *		| unit.getEmergency().getUnitsNeeded().unitFinishedJob(unit)
	 * @effect The unit is removed from the workingUnits list.
	 *		|takeWorkingUnit().remove(unit)
	 * @effect The unit is added to the finished units.
	 *		|addFinishedUnits(unit)
	 */
	@Override
	void unitFinishedJob(Unit unit, EventHandler eventHandler) {
		unit.getEmergency().getUnitsNeeded().unitFinishedJob(unit, eventHandler);
	}

	/**
	 * Tests if the given DispatchUnitConstraint can be a valid constraint, i.e.
	 *		the constraint is not null.
	 * @param constraint
	 *		The constraint to check.
	 * @return True if the given constraint is effective; otherwise false.
	 */
	public static boolean isValidConstraint(AndDispatchUnitsConstraint constraint) {
		return (constraint != null);
	}

	/**
	 * Withdraw a unit from its disaster.
	 * @param unit
	 *		The unit that wants to withdraw.
	 * @param eventHandler 
	 *		The eventHandler where the notifications should be sent to.
	 * @effect The unit is withdrawn from every emergency in the disaster.
	 *		| for(every emergency e in disaster)
	 *		|	e.getUnitsNeeded().withdrawUnit(unit, eventHandler)
	 * @effect The unit is removed from the workingUnits list.
	 *		| removeFromWorkingUnits(unit)
	 */
	@Override
	void withdrawUnit(Unit unit, EventHandler eventHandler) {
		for (Emergency e : getDisaster().getEmergencies()) {
			e.getUnitsNeeded().withdrawUnit(unit, eventHandler);
		}
	}

	/**
	 * Generates a proposal based on a set of available units to allocate to the disaster.
	 * @param options
	 *		A set of units where the proposal must be created from.
	 * @return A subset of the given list containing units proposed for allocation.
	 */
	//TODO: vroeger stond hier @note The first items in the list will first be added to the proposal (This is usefull for Policies that sort the list of units before they generate a proposal).
	//gaat dit nu geen problemen meer geven?
	@Override
	Set<Unit> generateProposal(SortedSet<Unit> options) {
		HashSet<Unit> proposal = new HashSet<Unit>();
		if (getConstraint().generateProposal(this.getAlreadyAssignedUnits(), options, proposal)) {
			return proposal;
		} else {
			return null;
		}
	}

	/**
	 * Generates a proposal for unit allocation based on the policy of the disaster.
	 * @return A set of units proposed by the policy of this disaster.
	 */
	@Override
	public Set<Unit> getPolicyProposal(Set<Unit> availableUnits) {
		Set<Unit> units = null;
		if (getDisaster().getEmergencies().size() > 0) {
			units = getDisaster().getEmergencies().get(0).calculateUnitsNeeded().getPolicyProposal(availableUnits);
			for (int i = 1; i < getDisaster().getEmergencies().size(); ++i) {
				units.addAll(getDisaster().getEmergencies().get(i).calculateUnitsNeeded().getPolicyProposal(availableUnits));
			}
		}

		return units;
	}

	/**
	 * Decides whether the disaster can be handled by the given units.
	 * @param availableUnits
	 *		The units to check if they can handle the disaster.
	 * @return True if the given units can handle the disaster; false otherwise.
	 */
	@Override
	public boolean canBeResolved(Set<Unit> availableUnits) {
		return this.getConstraint().canBeResolved(this.getAlreadyAssignedUnits(), availableUnits);
	}

	/**
	 * Sets the status of the disaster.
	 * @param disasterStatus
	 *		The new status of the disaster.
	 * @throws InvalidSendableStatusException 
	 *		If the status is wrong.
	 */
	@Override
	void setStatus(SendableStatus disasterStatus) throws InvalidSendableStatusException {
		for (Emergency e : getDisaster().getEmergencies()) {
			e.setStatus(disasterStatus);
		}
	}

	/**
	 * Returns the finished units of this disaster.
	 * @return The finished units of this disaster.
	 */
	@Override
	ArrayList<Unit> takeFinishedUnits() {
		ArrayList<Unit> result = new ArrayList<Unit>();
		for (Emergency e : getDisaster().getEmergencies()) {
			result.addAll(e.getUnitsNeeded().getFinishedUnits());
		}
		return result;
	}

	/**
	 * Returns the working units of the disaster.
	 * @return The working units of the disaster.
	 */
	@Override
	ArrayList<Unit> takeWorkingUnits() {
		ArrayList<Unit> result = new ArrayList<Unit>();
		for (Emergency e : getDisaster().getEmergencies()) {
			result.addAll(e.getUnitsNeeded().getWorkingUnits());
		}
		return result;
	}
}
