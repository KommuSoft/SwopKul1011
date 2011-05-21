package projectswop20102011.domain.validators;

import projectswop20102011.domain.Disaster;

/**
 * A class representing an evaluation criterium on an disaster.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public abstract class DisasterEvaluationCriterium implements Validator<Disaster> {

    /**
     * The validation method for a certain Disaster on the criterium.
     * @param disaster
     *		The disaster to validate.
     * @return True if the Disaster is valid according to the criterium, otherwise false.
     */
    @Override
    public abstract boolean isValid(Disaster disaster);
}
