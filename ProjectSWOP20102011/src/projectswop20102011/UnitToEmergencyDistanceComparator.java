package projectswop20102011;

import java.util.Comparator;
import projectswop20102011.exceptions.InvalidEmergencyException;

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
	 * @throws InvalidEmergencyException
	 *		If the given emergency is an invalid Emergency.
	 * @post This emergency is equal to the given emergency.
	 *		|new.getEmergency()==emergency
	 */
	public UnitToEmergencyDistanceComparator(Emergency emergency) throws InvalidEmergencyException{
		if(!isValidEmergency(emergency)){
			throw new InvalidEmergencyException(String.format("\"%s\" is an invalid emergency for an UnitToEmergencyDistanceComparator.", emergency));
		}else{
			this.emergency = emergency;
		}
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

	/**
	 * Checks if the given Emergency is a valid Emergency for this UnitToEmergencyDistanceComparator.
	 * @param emergency
	 *		The emergency to test.
	 * @return
	 *		True if the emergency is valid; false otherwise.
	 */
	public static boolean isValidEmergency(Emergency emergency){
		return (emergency != null);
	}
}
