package projectswop20102011;

/**
 * A class representing an evaluation criterium on an emergency.
 * 
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class EmergencyEvaluationCriterium {

	/**
	 * The validation method for a certain Emergency on the criterium.
	 * @param emergency
	 *		The emergency to validate.
	 * @return True if the Emergency is valid according to the criterium, otherwise false.
	 */
	public abstract boolean isValidEmergency(Emergency emergency);
}
