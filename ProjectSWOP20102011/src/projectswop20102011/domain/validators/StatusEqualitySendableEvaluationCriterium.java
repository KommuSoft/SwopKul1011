package projectswop20102011.domain.validators;

import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.domain.Sendable;

public class StatusEqualitySendableEvaluationCriterium<T extends Sendable> extends SendableEvaluationCriterium<T>{

	/**
	 * A variable registering the status of this StatusEqualitySendableEvaluationCriterium.
	 */
	private final EmergencyStatus status;

	/**
	 * Creates a new instance of a StatusEqualitySendableEvaluationCriterium with a given EmergencyStatus to check on.
	 * @param status
	 *		The EmergencyStatus that must be equal to the validating Emergency.
	 * @post This status is equal to the parameter status
	 *		| status == getStatus()
	 */
	public StatusEqualitySendableEvaluationCriterium(EmergencyStatus status) {
		this.status = status;
	}

	/**
	 * Validates a given Emergency on equality on the status of the Emergency, and this status.
	 * @param sendable
	 *		The sendable to validate.
	 * @return True if the status of the emergency equals this status, otherwise false.
	 */
	@Override
	public boolean isValidSendable(T sendable) {
		return ((Sendable) sendable).getStatus() == getStatus();
	}

	/**
	 * Returns the status to check an emergency on.
	 * @return The status that must be equal to the status of the emergency to validate that emergency.
	 */
	public EmergencyStatus getStatus() {
		return status;
	}
}
