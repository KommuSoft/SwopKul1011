package projectswop20102011.domain.validators;

import java.util.Comparator;
import projectswop20102011.domain.Emergency;

/**
 * A comparator to sort Emergencies based on being partially allocated.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class PartiallyAssignedEmergencyComparator implements Comparator<Emergency> {

    /**
     * Compares the two given emergencies.
     * @param emergency1
     *		The first emergency to compare.
     * @param emergency2
     *		The second emergency to compare.
     * @return An integer where:    If smaller than zero the first emergency is partially allocated and the second not
     *                              If larger than zero the second emergency is partially allocated and the second not
     *                              Otherwise zero.
     */
    @Override
    public int compare(Emergency emergency1, Emergency emergency2) {
        boolean partial1 = emergency1.isPartiallyAssigned();
        boolean partial2 = emergency2.isPartiallyAssigned();
        return -((Boolean) partial1).compareTo(partial2);
    }
}
