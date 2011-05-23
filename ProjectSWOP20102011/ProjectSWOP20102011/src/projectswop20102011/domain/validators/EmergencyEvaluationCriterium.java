package projectswop20102011.domain.validators;

import projectswop20102011.domain.Emergency;

/**
 * A class representing an evaluation criterium on an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class EmergencyEvaluationCriterium implements Validator<Emergency> {

    /**
     * The validation method for a certain Emergency on the criterium.
     * @param emergency
     *		The emergency to validate.
     * @return True if the Emergency is valid according to the criterium, otherwise false.
     */
    @Override
    public abstract boolean isValid(Emergency emergency);
}
