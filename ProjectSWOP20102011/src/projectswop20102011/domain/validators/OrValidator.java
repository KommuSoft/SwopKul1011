package projectswop20102011.domain.validators;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A Validator that represents an OR operation on other Validators.
 * @param <T> The type of object that is validated by this validator.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class OrValidator<T> implements Validator<T> {

    /**
     * A list of validators that are validated by this OrValidator.
     */
    public final ArrayList<Validator<? super T>> subValidators;

    /**
     * Creates a new instance of an OrValidator with a given collection of validators who will be evaluated under this validator.
     * @param subValidators
     */
    public OrValidator (Collection<Validator<? super T>> subValidators) {
        this.subValidators = new ArrayList<Validator<? super T>>(subValidators);
    }
    
    /**
     * Returns the list of validators under this validator.
     * @return The list of validators under this validator.
     */
    public ArrayList<Validator<? super T>> getSubValidators () {
        return (ArrayList<Validator<? super T>>) this.subValidators.clone();
    }

    /**
     * Validates the given object.
     * @param object
     *          The object to validate.
     * @return True if at least one of the validators is true, otherwise false.
     */
    @Override
    public boolean isValid(T object) {
        for(Validator<? super T> v : this.getSubValidators()) {
            if(v.isValid(object)) {
                return true;
            }
        }
        return false;
    }

}
