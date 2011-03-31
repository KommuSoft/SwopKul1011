package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.validators.AndDispatchUnitsConstraint;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidConstraintListException;

public class DerivedUnitsNeeded extends UnitsNeeded {

	private final Disaster disaster;
	private final AndDispatchUnitsConstraint constraint;
	private ArrayList<Unit> workingUnits;
	private ArrayList<Unit> finishedUnits;

	DerivedUnitsNeeded(Disaster disaster) throws InvalidConstraintListException {
		this.disaster = disaster;
		DispatchUnitsConstraint[] constraints = new DispatchUnitsConstraint[disaster.getEmergencies().size()];

		for (int i = 0; i < disaster.getEmergencies().size(); ++i) {
			constraints[i] = disaster.getEmergencies().get(i).getDispatchConstraint();
		}

		constraint = new AndDispatchUnitsConstraint(constraints);
		initUnits();
	}

	private void initUnits() {
		workingUnits = new ArrayList<Unit>();
		finishedUnits = new ArrayList<Unit>();
	}

	/**
	 * Checks if the units needed for the emergency are all finished.
	 * @return True if all units needed for the emergency are finished; otherwise false.
	 * @note If the units needed for the emergency are all finished, then the emergency of this units needed can be completed.
	 */
	public boolean canCompleteDisaster() {
		throw new RuntimeException("Not yet implemented");
		//return getConstraint().areValidDispatchUnits(takeFinishedUnits());
	}

	private void addWorkingUnits(Unit unit) {
		takeWorkingUnits().add(unit);
	}

	private void addFinishedUnits(Unit unit) {
		takeFinishedUnits().add(unit);
	}

	private Disaster getDisaster() {
		return disaster;
	}

	@Override
	public ArrayList<Unit> getWorkingUnits() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		for (Emergency e : getDisaster().getEmergencies()) {
			units.addAll(e.calculateUnitsNeeded().getWorkingUnits());
		}

		return units;
	}

	/**
	 * Returns the working units of the emergency.
	 * @return The working units of the emergency.
	 * @note The difference between takeWorkingUnits and getWorkingUnits is that
	 *		getWorkingUnits first clones the list, takeWorkingUnits is only
	 *		visible at private level and returns the real list.
	 * @see #getWorkingUnits()
	 */
	private ArrayList<Unit> takeWorkingUnits() {
		return workingUnits;
	}

	private ArrayList<Unit> takeFinishedUnits() {
		return finishedUnits;
	}

	@Override
	public ArrayList<Unit> getFinishedUnits() {
		ArrayList<Unit> units = new ArrayList<Unit>();
		for (Emergency e : getDisaster().getEmergencies()) {
			units.addAll(e.getUnitsNeeded().getFinishedUnits());
		}

		return units;
	}

	private AndDispatchUnitsConstraint getConstraint() {
		return constraint;
	}

	public static boolean isValidDisaster(Disaster disaster) {
		return (disaster != null);
	}

	/**
	 * Checks if the given units can be assigned to the emergency.
	 * @param units
	 *		The units to be assigned.
	 * @return True if all the given units are effective, unique and
	 *          can be assigned and the constraint for allocation is passed;
	 *          otherwise false.
	 */
	public boolean canAssignUnitsToEmergency(Set<Unit> units) {
		throw new RuntimeException("Not yet implemented");
	}

	/**
	 * Assign the given list of units to the emergency.
	 * @param units
	 *		The given list of units.
	 * @post All the units in the given list are assigned
	 *		| forall (u in units)
	 *		|	u.isAssigned()
	 * @post All the units in the given list are handling the emergency of this UnitNeeded
	 *		| forall (u in units)
	 *		|	u.getEmergency().equals(this.getEmergency())
	 * @throws InvalidEmergencyException
	 *		If the units can't be assigned to the emergency (when canAssignUnitsToEmergency fails)
	 * @see #canAssignUnitsToEmergency(Set)
	 */
	public synchronized void assignUnitsToEmergency(Set<Unit> units) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	void unitFinishedJob(Unit unit) {
		for (Emergency e : getDisaster().getEmergencies()) {
			e.calculateUnitsNeeded().unitFinishedJob(unit);
		}
		removeFromWorkingUnits(unit);
		addFinishedUnits(unit);
	}

	public static boolean isValidConstraint(AndDispatchUnitsConstraint constraint) {
		return (constraint != null);
	}

	private void removeFromWorkingUnits(Unit unit) {
		takeWorkingUnits().remove(unit);
	}

	void withdrawUnit(Unit unit) {
		for (Emergency e : getDisaster().getEmergencies()) {
			e.calculateUnitsNeeded().withdrawUnit(unit);
		}
		removeFromWorkingUnits(unit);
	}

	private List<Unit> calculateFixedPart() {
		final List<Unit> fixedPart = getWorkingUnits();
		fixedPart.addAll(takeFinishedUnits());
		return fixedPart;
	}

	@Override
	Set<Unit> generateProposal(List<Unit> options) {
		return getConstraint().generateProposal(calculateFixedPart(), options);
	}


	@Override
	public Set<Unit> getPolicyProposal(List<? extends Unit> availableUnits) {
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
	 * Decides whether the emergency can be handled by the given units.
	 * @param availableUnits
	 *		The units to check if they can handle the emergency.
	 * @return True if the given units can handle the emrgency; false otherwise.
	 */
	@Override
	public boolean canBeResolved(Collection<? extends Unit> availableUnits) {
		final Collection<Unit> completeCollection = this.getFinishedUnits();
		completeCollection.addAll(takeWorkingUnits());
		completeCollection.addAll(availableUnits);
		return this.getConstraint().areValidDispatchUnits(completeCollection);
	}
}
