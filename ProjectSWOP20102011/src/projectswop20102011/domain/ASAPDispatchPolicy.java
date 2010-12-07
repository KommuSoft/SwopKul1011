package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class representing the ASAP (As Soon As Possible) dispatch policy.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ASAPDispatchPolicy extends DispatchPolicy {

    /**
     * Creates a new insatance with a given UnitsNeeded object of the emergency that will be handled.
     * @param unitsNeeded The unitsNeeded object of the emergency this
     * @throws InvalidUnitsNeededException If the given UnitsNeeded object is not effective.
     * @throws InvalidDispatchPolicyException If the given UnitsNeeded object has already a Policy.
     */
    public ASAPDispatchPolicy (UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException, InvalidDispatchPolicyException {
        super(unitsNeeded);
    }

    /**
     * Tests if the given UnitsNeeded object is a valid UnitsNeeded parameter.
     * @param unitsNeeded The UnitsNeeded object to test.
     * @return True if the UnitsNeeded object is effective, otherwise false.
     */
    @Override
    public Unit[] filterAvailableUnits(Unit[] availableUnits) {
        //TODO: Implement (wait for the Constraint classes)
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
