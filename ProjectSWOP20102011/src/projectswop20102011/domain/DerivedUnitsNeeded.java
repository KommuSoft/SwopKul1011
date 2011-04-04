package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.domain.validators.AndDispatchUnitsConstraint;
import projectswop20102011.domain.validators.DispatchUnitsConstraint;
import projectswop20102011.exceptions.InvalidConstraintListException;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;

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
		return (ArrayList<Unit>) workingUnits.clone();
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

	ArrayList<Unit> takeFinishedUnits() {
		return finishedUnits;
	}

	@Override
	public ArrayList<Unit> getFinishedUnits() {
		return (ArrayList<Unit>) finishedUnits.clone();
	}

	@Override
	DispatchUnitsConstraint getConstraint() {
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
		ArrayList<Unit> options = new ArrayList<Unit>(units.size());
		Iterator<Unit> it = units.iterator();
		while (it.hasNext()) {
			options.add(it.next());
		}
		for (Emergency e : getDisaster().getEmergencies()) {
			ConcreteUnitsNeeded CUN = e.getUnitsNeeded();
			Set<Unit> unitsForEmergency = CUN.generateProposal(options);

			if (unitsForEmergency.isEmpty() || !e.canAssignUnits(unitsForEmergency)) {
				return false;
			}
		}

		return true;
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
	@Override
	public synchronized void assignUnitsToEmergency(Set<Unit> units) throws InvalidEmergencyException {
		ArrayList<Unit> options = new ArrayList<Unit>(units.size());
		Iterator<Unit> it = units.iterator();
		while (it.hasNext()) {
			options.add(it.next());
		}

		if (!canAssignUnitsToEmergency(units)) {
			throw new InvalidEmergencyException("Units can't be assigned to the emergency, harm to assignment constraints.");
		}
		for (Emergency e : getDisaster().getEmergencies()) {
			ConcreteUnitsNeeded CUN = e.getUnitsNeeded();
			Set<Unit> unitsForEmergency = CUN.generateProposal(options);
			try {
				e.assignUnits(unitsForEmergency);
			} catch (InvalidEmergencyStatusException ex) {
				//We assume this can't happen.
				Logger.getLogger(DerivedUnitsNeeded.class.getName()).log(Level.SEVERE, null, ex);
			}
			for (Unit u : unitsForEmergency) {
				addWorkingUnits(u);
			}
			options.removeAll(unitsForEmergency);
		}
	}

	@Override
	void unitFinishedJob(Unit unit) {
		removeFromWorkingUnits(unit);
		addFinishedUnits(unit);
	}

	public static boolean isValidConstraint(AndDispatchUnitsConstraint constraint) {
		return (constraint != null);
	}

	private void removeFromWorkingUnits(Unit unit) {
		unit.setEmergency(null);
		takeWorkingUnits().remove(unit);
	}

	@Override
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

	@Override
	void setStatus(EmergencyStatus emergencyStatus) throws InvalidEmergencyStatusException {
		for (Emergency e : getDisaster().getEmergencies()) {
			e.setStatus(emergencyStatus);
		}
	}
}
