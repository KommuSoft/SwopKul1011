package projectswop20102011.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * An abstract implementation of a DispatchPolicy that uses a sorting
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class MinimalSortingDispatchPolicy extends DispatchPolicy {

    private Comparator<? super Unit> comparator;

    protected MinimalSortingDispatchPolicy(UnitsNeeded unitsNeeded, Comparator<? super Unit> comparator) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
        super(unitsNeeded);
    }

    @Override
    public Unit[] filterAvailableUnits(Unit[] availableUnits) {
        Unit[] sortedUnits = availableUnits.clone();
        Arrays.sort(sortedUnits, this.getComparator());
        int n = sortedUnits.length;
        Unit[] maxProposal = null;
        boolean constraintCheck = false;
        for (int i = 1; i <= n && !constraintCheck; i++) {
            //add i-th element to the proposal
            maxProposal = new Unit[i];
            System.arraycopy(sortedUnits, 0, maxProposal, 0, i);
            constraintCheck = this.getUnitsNeeded().canAssignUnitsToEmergency(maxProposal);
        }
        Unit[] proposal = maxProposal;
        if (maxProposal != null) {
            int nMaxProposal = maxProposal.length;
            for (int i = nMaxProposal - 2; i >= 0; i--) {
                //remove i-th element from the proposal (beginning from n-2 because we know for sure the last unit is required)
                proposal = new Unit[nMaxProposal-1];
                System.arraycopy(maxProposal, 0, proposal, 0, i);
                System.arraycopy(maxProposal, i+1, proposal, i, nMaxProposal-i-1);
                if(this.getUnitsNeeded().canAssignUnitsToEmergency(proposal)) {
                    maxProposal = proposal;
                    nMaxProposal = maxProposal.length;
                }
            }
        }
        return proposal;
    }

    public Comparator<? super Unit> getComparator() {
        return this.comparator;
    }

    /**
     * Tests if the given comparator is a valid comparator parameter
     * @param comparator The comparator to test.
     * @return True if the given comparator is effective, otherwise false.
     */
    public static boolean isValidComparator(Comparator<? super Unit> comparator) {
        return (comparator != null);
    }
}
