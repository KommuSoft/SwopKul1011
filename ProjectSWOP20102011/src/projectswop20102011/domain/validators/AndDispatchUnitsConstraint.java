package projectswop20102011.domain.validators;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidConstraintListException;

/**
 * A constraint class checking if a collection of other constraints all pass.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AndDispatchUnitsConstraint extends DispatchUnitsConstraint {

    private final DispatchUnitsConstraint[] constraints;

    /**
     * Creates a new AndDispatchUnitsConstraint with the given constraints below that all should pass to let pass this constraint.
     * @param constraints
	 *		The list of constraints that all should pass.
     * @throws InvalidConstraintListException
	 *		If the given list of constraints is invalid.
     */
    public AndDispatchUnitsConstraint(DispatchUnitsConstraint... constraints) throws InvalidConstraintListException {
        if (!areValidConstraints(constraints)) {
            throw new InvalidConstraintListException("The list with constraints and all its elements must be effective.");
        }
        this.constraints = constraints.clone();
    }

    /**
     * Tests if the given list of constraints is a valid constraint argument in the constructor.
     * @param constraints
	 *		A list of constraints to check.
     * @return True if the list and all it's elements are effective, otherwise false.Ò
     */
    public static boolean areValidConstraints(DispatchUnitsConstraint... constraints) {
        if (constraints == null) {
            return false;
        }
        for (DispatchUnitsConstraint duc : constraints) {
            if (duc == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a list of all the DispatchUnitConstraints below this AndDispatchUnitsConstraint.
     * @return A list of all the DispatchUnitConstraints below this AndDispatchUnitsConstraint.
     */
    public DispatchUnitsConstraint[] getConstraints () {
        return constraints.clone();
    }

    /**
     * Returns a textual representation of the AndDispatchUnitsConstraint.
     * @return A textual representation of the AndDispatchUnitsConstraint.
     */
    @Override
    public String toString () {
        DispatchUnitsConstraint[] c = getConstraints();
        if(c.length == 0) {
            return "TRUE";
        }
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("(%s)",c[0].toString()));
            for(int i = 1; i < c.length; i++) {
                sb.append(String.format(" AND (%s)", c[i]));
            }
            return sb.toString();
        }
    }

    /**
     * Generates a proposal for units based on the set of available units, and adds them to the proposal.
     * @param finishedOrAssignedUnits The list of units that are already finished or assigned.
     * @param availableUnits The set of available units to assign.
     * @param proposal A set where the units that will be proposed will be added to.
     * @return True if this methods can generate a proposal without violating constraints (for example firetrucks can only added once, sometimes we can't generate a proposal).
     * @note This constraint will ask all containing constraints to generate their proposal, the result is the union of all the proposed units, and the return value the and of all the return values.
     */
    @Override
    public boolean generateProposal(List<Unit> finishedOrAssignedUnits, SortedSet<Unit> availableUnits, Set<Unit> proposal) {
        for(DispatchUnitsConstraint duc : this.constraints) {
            if(!duc.generateProposal(finishedOrAssignedUnits,availableUnits,proposal)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the given set of units to assign to an emergency can be assigned.
     * @param finishedOrAssignedUnits The list of Units that were already 
     * @param toAssignUnits The list of units to check if they can be assigned.
     * @return True if the given set of units can be assigned, otherwise false.
     * @note The result of this method is the and of the results of the containing constraints.
     */
    @Override
    public boolean canAssign(List<Unit> finishedOrAssignedUnits, Set<Unit> toAssignUnits, Set<Unit> relevantUnits) {
        for(DispatchUnitsConstraint duc : this.constraints) {
            if(!duc.canAssign(finishedOrAssignedUnits,toAssignUnits,relevantUnits)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the emergency can be finished if the given units have all done their job in the emergency.
     * @param finishedUnits A list of finished Units.
     * @return True if the given emergency can finish, otherwise false.
     * @note The result of this method is the and of the results of the containing constraints.
     */
    @Override
    public boolean canFinish(List<Unit> finishedUnits) {
        for(DispatchUnitsConstraint duc : this.constraints) {
            if(!duc.canFinish(finishedUnits)) {
                return false;
            }
        }
        return true;
    }

}