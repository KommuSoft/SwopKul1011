package projectswop20102011.domain.validators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.Unit;
import projectswop20102011.utils.SortedListSet;

/**
 * An abstract class representing a constraint to dispatch units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class DispatchUnitsConstraint {

    /**
     * Generates a proposal for units based on the set of available units, and adds them to the proposal.
     * @param finishedOrAssignedUnits
     *          The list of units that are already finished or assigned.
     * @param availableUnits
     *          The set of available units to assign.
     * @param proposal
     *          A set where the units that will be proposed will be added to.
     * @return True if this methods can generate a proposal without violating constraints (for example firetrucks if units could only be assigned at once, a valid proposal could not be possible).
     */
    public abstract boolean generateProposal(List<Unit> finishedOrAssignedUnits, SortedSet<Unit> availableUnits, Set<Unit> proposal);

    /**
     * Checks if the given set of units to assign to an emergency can be assigned.
     * @param finishedOrAssignedUnits The list of Units that were already
     * @param toAssignUnits
     *          The list of units to check if they can be assigned.
     * @param relevantUnits
     *          The set of units that are relevant to the assignation. In the end all the units to Assign must be relevant to the composed constraint.
     * @return True if the given set of units can be assigned, otherwise false.
     */
    protected abstract boolean canAssign(List<Unit> finishedOrAssignedUnits, Set<Unit> toAssignUnits, Set<Unit> relevantUnits);

    /**
     * Checks if the given set of units can be assigned to the emergency with this DispatchUnitsConstraint.
     * @param finishedOrAssignedUnits
     *          A list of units that were already assigned.
     * @param toAssignUnits
     *          A set of units proposed to be assigned.
     * @return True if all the constraints still hold, and all the units to assign are relevant for the emergency.
     */
    public boolean canAssign(List<Unit> finishedOrAssignedUnits, Set<Unit> toAssignUnits) {
        HashSet<Unit> relevant = new HashSet<Unit>(toAssignUnits.size());
        return (canAssign(finishedOrAssignedUnits, toAssignUnits, relevant) && relevant.size() == toAssignUnits.size());
    }

    /**
     * Checks if the emergency can be finished if the given units have all done their job in the emergency.
     * @param finishedUnits
     *          A list of finished Units.
     * @return True if the given emergency can finish, otherwise false.
     */
    public abstract boolean canFinish(List<Unit> finishedUnits);

    /**
     * A method that checks if the Emergency could be resolved with a Set of available Units.
     * @param finishedOrAssigned
     *          The units that are already assigned or even finished with the emergency.
     * @param availableUnits
     *          A set of available units for allocation.
     * @return True if the given Emergency can be resolved with the avialable units, otherwise false.
     */
    public boolean canBeResolved(List<Unit> finishedOrAssigned, Set<Unit> availableUnits) {
        SortedListSet<Unit> available = new SortedListSet<Unit>(availableUnits);
        HashSet<Unit> proposal = new HashSet<Unit>();
        if (generateProposal(finishedOrAssigned, available, proposal)) {
            List<Unit> assumedFinished = new ArrayList<Unit>(finishedOrAssigned);
            assumedFinished.addAll(proposal);
            return canFinish(assumedFinished);
        } else {
            return false;
        }
    }
}
