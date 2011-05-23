package projectswop20102011.domain.validators;

import java.util.Comparator;
import projectswop20102011.domain.Emergency;

/**
 * A class that compares two Emergencies by their severity level.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencyComparator implements Comparator<Emergency> {

    /**
     * Compares two emergencies with each other based on their severity level.
     * @param o1
     *          The first emergency to compare.
     * @param o2
     *          The second emergency to compare.
     * @return Zero if both emergencies have the same severity level.
     * -1 if the first emergency has a more important severity level.
     * 1 if the second emergency has a more important severity level.
     */
    @Override
    public int compare(Emergency o1, Emergency o2) {
        return o1.getSeverity().compareTo(o2.getSeverity());
    }
}
