package projectswop20102011.domain.validators;

import java.util.Collection;

/**
 * A QuadraticValidator that is always valid.
 * @param <TCollection>
 *		The type of items in the collection.
 * @param <TItem>
 *		The type of items to check for.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DefaultQuadraticValidator<TCollection, TItem> implements QuadraticValidator<TCollection, TItem> {

    /**
     * A validator that always succeeds, whatever the item and collection are.
     * @param collection
     *          A collection to test against the item (not used).
     * @param item
     *          The item to validate (not used).
     * @return Always true.
     */
    @Override
    public boolean isValid(Collection<TCollection> collection, TItem item) {
        return true;
    }
}
