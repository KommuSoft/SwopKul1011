package projectswop20102011.domain.validators;

/**
 * A class that generates a numberator out of a given validator.
 * @param <T> The type of objects that will be validated and numberated.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class BooleanValidatorNumberator<T> implements ValidatorNumberator<T> {

    /**
     * Returns a number out of the object.
     * @param object The object to generate a number from.
     * @return One if the given object is true, otherwise zero.
     */
    @Override
    public final long getNumber(T object) {
        if (this.isValid(object)) {
            return 1;
        } else {
            return 0;
        }
    }

}