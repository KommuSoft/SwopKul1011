package projectswop20102011.domain.validators;

import java.util.Collection;

/**
 * A QuadraticValidator implementation that succeeeds when the item is different to all objects in the collection.
 * @param <TCollection> The type of objects in the collection.
 * @param <TItem> The type of items to validate.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DifferentQuadraticValidator<TCollection,TItem extends TCollection> implements QuadraticValidator<TCollection,TItem> {

    /**
     * A method that succeeds if the given item is not an element of the collection.
     * @param collection
     *          The collection to test against the item.
     * @param item
     *          The item to validate.
     * @return True if the item is not an element of the collection, otherwise false.
     */
    @Override
    public boolean isValid(Collection<TCollection> collection, TItem item) {
        return !collection.contains(item);
    }

}
