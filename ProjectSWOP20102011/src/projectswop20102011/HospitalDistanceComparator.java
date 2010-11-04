package projectswop20102011;

import java.util.Comparator;

/**
 * A class that represents a comparator that compares the distance of two hospitals.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class HospitalDistanceComparator implements Comparator<Hospital>{
	/**
	 * The emergency where distances must be calculated from.
	 */
	private final Emergency emergency;

	/**
	 * Creates a new HospitalDistanceComparator with the given emergency.
	 * @param emergency
	 *		The emergency of the new HospitalDistanceComparator.
	 */
	public HospitalDistanceComparator(Emergency emergency){
		this.emergency = emergency;
	}

	/**
	 * Returns the emergency of this HospitalDistanceComparator.
	 * @return the emergency of this HospitalDistanceComparator.
	 */
	public Emergency getEmergency(){
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
		Double distance1 = hospital1.getHomeLocation().getDistanceTo(emergency.getLocation());
		Double distance2 = hospital2.getHomeLocation().getDistanceTo(emergency.getLocation());

		return distance1.compareTo(distance2);
	}

}