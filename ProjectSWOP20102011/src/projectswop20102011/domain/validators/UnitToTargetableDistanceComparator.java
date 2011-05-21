package projectswop20102011.domain.validators;

import java.util.Comparator;
import projectswop20102011.domain.Targetable;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidTargetableException;

/**
 * A comparator class that compares two units based on the distance to a specified Targetable.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UnitToTargetableDistanceComparator implements Comparator<Unit> {

    /**
     * The Targetable to calculate the distance to.
     */
    private final Targetable targetable;

    /**
     * Creates a new UnitToTargetableDistanceComparator with a given Targetable to calculated distances to.
     * @param targetable
     *          The Targetable to calculate distance to.
     * @throws InvalidTargetableException
     *          If the given Targetable is Invalid.
     */
    public UnitToTargetableDistanceComparator(Targetable targetable) throws InvalidTargetableException {
        if (!isValidTargetable(targetable)) {
            throw new InvalidTargetableException(String.format("\"%s\" is an invalid disaster for an UnitToDisasterDistanceComparator.", targetable));
        } else {
            this.targetable = targetable;
        }
    }

    /**
     * Returns the Targetable where distances will be calculated to.
     * @return The Targetable where distances will be calculated to.
     */
    private Targetable getTargetable() {
        return targetable;
    }

    /**
     * Compares two Units based on their distance to the Targetable of this Comparator.
     * @param unit1
     *          The first unit to compare.
     * @param unit2
     *          The second unit to compare.
     * @return  Zero if both distances are equal.
     *          Negative if the distance of the first unit is smaller than the second.
     *          Positive if the distance of the second unit is smaller than the first.
     */
    @Override
    public int compare(Unit unit1, Unit unit2) {
        Double distance1 = unit1.getCurrentLocation().getDistanceTo(getTargetable().getTargetLocation());
        Double distance2 = unit2.getCurrentLocation().getDistanceTo(getTargetable().getTargetLocation());
        return distance1.compareTo(distance2);
    }

    /**
     * Checks if the given targetable is a valid targetable for a UnitToTargetableDistanceComparator.
     * @param targetable
     *          The targetable to validate.
     * @return True if the given Targetable is effective, otherwise false.
     */
    public static boolean isValidTargetable(Targetable targetable) {
        return (targetable != null);
    }
}
