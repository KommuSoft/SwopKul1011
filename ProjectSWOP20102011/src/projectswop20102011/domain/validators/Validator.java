package projectswop20102011.domain.validators;

/**
 * A generic interface that represents a validator that validates a certain type of object
 * @param <T> The type of object that will be validated.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface Validator<T> {

    /**
     * A method that validates the given object.
     * @param object The object to validate.
     * @return True if the object is valid, false otherwise.
     */
    public abstract boolean isValid (T object);

}
