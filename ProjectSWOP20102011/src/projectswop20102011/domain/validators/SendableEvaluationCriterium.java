package projectswop20102011.domain.validators;

import projectswop20102011.domain.Sendable;

/**
 * A class representing an evaluation criterium on a sendable.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
//TODO: wordt normaal nergens gebruikt, zouden we deze generische implementatie nog integreren in onze implementatie?
public abstract class SendableEvaluationCriterium<T extends Sendable> {

	/**
	 * The validation method for a certain sendable on the criterium.
	 * @param sendable
	 *		The sendable to validate.
	 * @return True if the sendable is valid according to the criterium, otherwise false.
	 */
	public abstract boolean isValidSendable(T sendable);
}
