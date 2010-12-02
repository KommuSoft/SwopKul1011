package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidUnitsNeededException;

/**
 * A class that represents the default policy used to handle an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DefaultDispatchPolicy extends DispatchPolicy {

    /**
     * Creates a new instance of a DefaultDispatchPolicy with a given UnitsNeeded object of the emergency to handle.
     * @param unitsNeeded The UnitsNeeded object of the emergency this policy will handle.
     * @throws InvalidUnitsNeededException If the given UnitsNeeded policy is ineffective.
     */
    public DefaultDispatchPolicy (UnitsNeeded unitsNeeded) throws InvalidUnitsNeededException {
        super(unitsNeeded);
    }

    /**
     * Fiters the selected units (by the policy) out of a number of available units
     * @param availableUnits A list of units that are available to handle an emergency (not necessarily this emergency in particular).
     * @return A list of units that would be allocated to the emergency if the policy would be applied.
     * @pre The list of available units is effective and contains unique (no duplicates) units that are available for an emergency.
     */
    @Override
    public Unit[] filterAvailableUnits(Unit[] availableUnits) {
        //TODO: Implement (wait for the Constraint classes)
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
