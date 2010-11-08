package projectswop20102011;

import java.util.Comparator;

/**
 * A class that represents a comparator that compares the distance of two units to a given emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UnitToEmergencyDistanceComparator implements Comparator<Unit> {

    /**
	 * The emergency where distances must be calculated from.
	 */
	private final Emergency emergency;

	/**
	 * Creates a new UnitToEmergencyDistanceComparator with the given emergency.
	 * @param emergency
	 *		The emergency of the new UnitToEmergencyDistanceComparator.
	 */
	public UnitToEmergencyDistanceComparator(Emergency emergency){
		this.emergency = emergency;
	}

	/**
	 * Returns the emergency of this UnitToEmergencyDistanceComparator.
	 * @return the emergency of this UnitToEmergencyDistanceComparator.
	 */
	private Emergency getEmergency(){
		return emergency;
	}

	/**
	 * Compares the distance of the two units to the emergency.
	 * @param unit1
	 *		Unit number one.
	 * @param unit2
	 *		Unit number two.
	 * @return
	 *		A negative value if the distance from unit1 to the emergency is
	 *		lower than the distance from unit2 to the emergency;
	 *		zero if the distances are equal and
	 *		positive if the distance from unit1 to the emergency is greater
	 *		than the distance from unit2 to the emergency.
	 */
	@Override
	public int compare(Unit unit1, Unit unit2) {
		Double distance1 = unit1.getCurrentLocation().getDistanceTo(getEmergency().getLocation());
		Double distance2 = unit2.getCurrentLocation().getDistanceTo(getEmergency().getLocation());
		return distance1.compareTo(distance2);
	}

}
