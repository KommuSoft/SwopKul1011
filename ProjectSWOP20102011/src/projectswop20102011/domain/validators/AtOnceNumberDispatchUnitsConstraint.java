package projectswop20102011.domain.validators;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 * A special case of a NumberDispatchUnitsConstraint but where units needs to be assigned at once.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AtOnceNumberDispatchUnitsConstraint extends NumberDispatchUnitsConstraint {

    /**
     * Creates a new AtOnceNumberDispatchConstraint object.
     * @param validatornumberator
     *          The ValidatorNumberator that validates units, and gives a number to represent them.
     * @param number
     *          The number needed.
     * @throws NumberOutOfBoundsException
     *          If the given number is not valid.
     * @throws InvalidValidatorException
     *          If the given validator is not valid.
     */
    public AtOnceNumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatornumberator, long number) throws NumberOutOfBoundsException, InvalidValidatorException {
        super(validatornumberator, number);
    }

    /**
     * Creates a new AtOnceNumberDispatchConstraint object.
     * @param validatornumberator
     *          The ValidatorNumberator that validates units, and gives a number to represent them.
     * @param number
     *          The number needed.
     * @param quadraticValidator
     *          The quadratic validator to validate units.
     * @throws NumberOutOfBoundsException
     *          If the given number is not valid.
     * @throws InvalidValidatorException
     *          If the given validators are not valid.
     */
    public AtOnceNumberDispatchUnitsConstraint(ValidatorNumberator<? super Unit> validatornumberator, long number, QuadraticValidator<Unit, Unit> quadraticValidator) throws NumberOutOfBoundsException, InvalidValidatorException {
        super(validatornumberator, number, quadraticValidator);
    }

    @Override
    public boolean generateProposal(List<Unit> finishedOrAssignedUnits, SortedSet<Unit> availableUnits, Set<Unit> proposal) {
        HashSet<Unit> subProposal = new HashSet<Unit>();
        if (super.generateProposal(finishedOrAssignedUnits, availableUnits, subProposal)) {
            ArrayList<Unit> proposalList = new ArrayList<Unit>(subProposal);
            if(canFinish(proposalList)) {
                proposal.addAll(subProposal);
                return true;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    protected boolean canAssign(List<Unit> finishedOrAssignedUnits, Set<Unit> toAssignUnits, Set<Unit> relevantUnits) {
        ArrayList<Unit> collection = new ArrayList<Unit>();
        this.collectValidUnits(toAssignUnits, collection);
        if (this.canFinish(finishedOrAssignedUnits)) {//already allocated
            return (collection.size() <= 0);
        } else {//not located, or all allocated at the same time
            if (collection.size() <= 0) {//no units are to be allocated
                return true;
            } else if (this.canFinish(collection)) {
                relevantUnits.addAll(collection);
                return true;
            } else {
                return false;
            }
        }
    }
}
