package projectswop20102011.domain.validators;

/**
 * An interface that generates a certain number out of a given object.
 * @param <T> The type of objects to generate a number from.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface Numberator<T> {

    /**
     * A method that translate the given object intro a certain number.
     * @param object The object to generate a number from.
     * @return A number out of the object.
     */
    public abstract long getNumber (T object);

}
