package projectswop20102011.domain.validators;

import java.util.Collection;

/**
 * A constraint that succeeds on a certain object T depending on a collection of objects of the U type.
 * @param <TCollection> The type of objects in the collection.
 * @param <TItem> The type of objects validated by the QuadraticValidator.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public interface QuadraticValidator<TCollection,TItem> {

    /**
     * A method that returns true if the item is valid depending on the content of the collection.
     * @param collection The collection of objects.
     * @param item The item to validate.
     * @return True if the object is valid, otherwise false.
     */
    public abstract boolean isValid (Collection<TCollection> collection, TItem item);

}
