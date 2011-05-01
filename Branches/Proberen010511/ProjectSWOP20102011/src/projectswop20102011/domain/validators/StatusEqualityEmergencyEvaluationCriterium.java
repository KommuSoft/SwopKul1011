package projectswop20102011.domain.validators;

import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.EmergencyStatus;

/**
 * An implementation for an EmergencyEvaluationCriterium that checks if the status is equal to a given status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class StatusEqualityEmergencyEvaluationCriterium extends EmergencyEvaluationCriterium {

	/**
	 * A variable registering the status of this StatusEqualityEmergencyEvaluationCriterium.
	 */
	private final EmergencyStatus status;

	/**
	 * Creates a new instance of a StatusEqualityEmergencyEvaluationCriterium with a given EmergencyStatus to check on.
	 * @param status
	 *		The EmergencyStatus that must be equal to the validating Emergency.
	 * @post This status is equal to the parameter status
	 *		| status == getStatus()
	 */
	public StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus status) {
		this.status = status;
	}

	/**
	 * Validates a given Emergency on equality on the status of the Emergency, and this status.
	 * @param emergency
	 *		The emergency to validate.
	 * @return True if the status of the emergency equals this status, otherwise false.
	 */
	@Override
	public boolean isValidEmergency(Emergency emergency) {
		return (emergency.getStatus() == getStatus());
	}

	/**
	 * Returns the status to check an emergency on.
	 * @return The status that must be equal to the status of the emergency to validate that emergency.
	 */
	public EmergencyStatus getStatus() {
		return status;
	}
}
