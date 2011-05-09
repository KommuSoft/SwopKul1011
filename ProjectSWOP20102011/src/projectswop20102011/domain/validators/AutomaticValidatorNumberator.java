package projectswop20102011.domain.validators;

import projectswop20102011.exceptions.InvalidValidatorException;

/**
 * A class that generates a numberator out of a given validator.
 * @param <T> The type of objects that will be validated and numberated.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class AutomaticValidatorNumberator<T> implements ValidatorNumberator<T> {

    private final Validator<T> validator;

    /**
     * Creates a new AutomaticValidatorNumberator out of a given Validator.
     * @param validator The given Validator.
     * @throws InvalidValidatorException If the Validator is invalid.
     */
    public AutomaticValidatorNumberator(Validator<T> validator) throws InvalidValidatorException {
        if (!isValidValidator(validator)) {
            throw new InvalidValidatorException("Validator must be effective.");
        }
        this.validator = validator;
    }

    /**
     * Checks if the given Validator is valid.
     * @param <T> The type the validator validates.
     * @param validator The given validator to validate.
     * @return True if the Validator is effective, false otherwise.
     */
    public static<T> boolean isValidValidator (Validator<T> validator) {
        return (validator != null);
    }

    /**
     * Checks if the given object is valid.
     * @param object The object to validate.
     * @return True if the object is valid, otherwise false.
     */
    @Override
    public boolean isValid(T object) {
        return this.validator.isValid(object);
    }

    /**
     * Returns a number out of the object.
     * @param object The object to generate a number from.
     * @return One if the given object is true, otherwise zero.
     */
    @Override
    public long getNumber(T object) {
        if (this.isValid(object)) {
            return 1;
        } else {
            return 0;
        }
    }
}
