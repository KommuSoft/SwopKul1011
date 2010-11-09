package projectswop20102011;

import java.util.Comparator;
import projectswop20102011.exceptions.InvalidEmergencyException;

/**
 * A class that represents a comparator that compares the distance of two hospitals to a given emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class HospitalToEmergencyDistanceComparator implements Comparator<Hospital> {

	/**
	 * The emergency where distances must be calculated from.
	 */
	private final Emergency emergency;

	/**
	 * Creates a new HospitalToEmergencyDistanceComparator with the given emergency.
	 * @param emergency
	 *		The emergency of the new HospitalToEmergencyDistanceComparator.
	 * @throws InvalidEmergencyException
	 *		If the given emergency is an invalid Emergency.
	 * @post This emergency is equal to the given emergency.
	 *		| new.getEmergency()==emergency
	 */
	public HospitalToEmergencyDistanceComparator(Emergency emergency) throws InvalidEmergencyException {
		if (!isValidEmergency(emergency)) {
			throw new InvalidEmergencyException(String.format("\"%s\" is an invalid emergency for an HospitalToEmergencyDistanceComparator.", emergency));
		} else {
			this.emergency = emergency;
		}
	}

	/**
	 * Returns the emergency of this HospitalToEmergencyDistanceComparator.
	 * @return The emergency of this HospitalToEmergencyDistanceComparator.
	 */
	private Emergency getEmergency() {
		return emergency;
	}

	/**
	 * Compares the distance of the two hospitals to the emergency.
	 * @param hospital1
	 *		Hospital number one.
	 * @param hospital2
	 *		Hospital number two.
	 * @return
	 *		A negative value if the distance from hospital1 to the emergency is
	 *		lower than the distance from hospital2 to the emergency;
	 *		zero if the distances are equal and
	 *		positive if the distance from hospital1 to the emergency is greater
	 *		than the distance from hospital2 to the emergency.
	 */
	@Override
	public int compare(Hospital hospital1, Hospital hospital2) {
		Double distance1 = hospital1.getHomeLocation().getDistanceTo(getEmergency().getLocation());
		Double distance2 = hospital2.getHomeLocation().getDistanceTo(getEmergency().getLocation());

		return distance1.compareTo(distance2);
	}

	/**
	 * Checks if the given Emergency is a valid Emergency for this HospitalToEmergencyDistanceComparator.
	 * @param emergency
	 *		The emergency to test.
	 * @return
	 *		True if the emergency is effective; false otherwise.
	 */
	public static boolean isValidEmergency(Emergency emergency) {
		return (emergency != null);
	}
}
