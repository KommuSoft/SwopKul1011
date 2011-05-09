package projectswop20102011.domain.validators;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.domain.Unit;
import projectswop20102011.utils.OrderedSet;

/**
 * An abstract class representing a constraint to dispatch units.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class DispatchUnitsConstraint {
    
    /**
     * Generates a proposal for units based on the set of available units, and adds them to the proposal.
     * @param finishedOrAssignedUnits The list of units that are already finished or assigned.
     * @param availableUnits The set of available units to assign.
     * @param proposal A set where the units that will be proposed will be added to.
     * @return True if this methods can generate a proposal without violating constraints (for example firetrucks can only added once, sometimes we can't generate a proposal).
     */
    public abstract boolean generateProposal (List<Unit> finishedOrAssignedUnits, OrderedSet<Unit> availableUnits, Set<Unit> proposal);

    /**
     * Checks if the given set of units to assign to an emergency can be assigned.
     * @param finishedOrAssignedUnits The list of Units that were already 
     * @param toAssignUnits The list of units to check if they can be assigned.
     * @param relevantUnits A set of units containing after this method all the units from toAssignUnits that are relevant.
     * @return True if the given set of units can be assigned, otherwise false.
     * @note For a valid assignment, all the units from toAssign needs to be in the relevantUnits at the end of this method.
     */
    protected abstract boolean canAssign (List<Unit> finishedOrAssignedUnits, OrderedSet<Unit> toAssignUnits, Set<Unit> relevantUnits);

    /**
     * Checks if the given set of units can be assigned to the emergency with this DispatchUnitsConstraint.
     * @param finishedOrAssignedUnits A list of units that were already assigned.
     * @param toAssignUnits A set of units proposed to be assigned.
     * @return True if all the constraints still hold, and all the units to assign are relevant for the emergency.
     */
    public boolean canAssign(List<Unit> finishedOrAssignedUnits, OrderedSet<Unit> toAssignUnits) {
        HashSet<Unit> relevant = new HashSet<Unit>();
        return (canAssign(finishedOrAssignedUnits,toAssignUnits,relevant) && relevant.size() == toAssignUnits.size());
    }

    /**
     * Checks if the emergency can be finished if the given units have all done their job in the emergency.
     * @param finishedUnits A list of finished Units.
     * @return True if the given emergency can finish, otherwise false.
     */
    public abstract boolean canFinish (List<Unit> finishedUnits);

    public boolean canBeResolved (List<Unit> finishedOrAssignedUnits, Collection<? extends Unit> availableUnits) {
        return true;//TODO: implement
    }

}