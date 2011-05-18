package projectswop20102011.domain.validators;

import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.SendableStatus;

/**
 * An implementation for an DisasterEvaluationCriterium that checks if the status is equal to a given status.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class StatusEqualityDisasterEvaluationCriterium extends DisasterEvaluationCriterium{

	/**
	 * A variable registering the status of this StatusEqualityDisasterEvaluationCriterium.
	 */
	private final SendableStatus status;

	/**
	 * Creates a new instance of a StatusEqualityDisasterEvaluationCriterium with a given EmergencyStatus to check on.
	 * @param status
	 *		The EmergencyStatus that must be equal to the validating Disaster.
	 * @post This status is equal to the parameter status
	 *		| status == getStatus()
	 */
	public StatusEqualityDisasterEvaluationCriterium(SendableStatus status) {
		this.status = status;
	}

	/**
	 * Validates a given Disaster on equality on the status of the Disaster, and this status.
	 * @param disaster
	 *		The disaster to validate.
	 * @return True if the status of the disaster equals this status, otherwise false.
	 */
	@Override
	public boolean isValidDisaster(Disaster disaster) {
		return (disaster.getStatus() == getStatus());
	}

	/**
	 * Returns the status to check a disaster on.
	 * @return The status that must be equal to the status of the disaster to validate that disaster.
	 */
	public SendableStatus getStatus() {
		return status;
	}
}
