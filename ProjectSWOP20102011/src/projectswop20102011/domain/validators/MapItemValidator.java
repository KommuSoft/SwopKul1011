package projectswop20102011.domain.validators;

import projectswop20102011.domain.MapItem;

/**
 * A class representing an evaluation criterium on an MapItem.
 * @param <T> An optional type parameter, this parameter is used for conversions of type of for exampe a MapItemList.
 * @note If <T> has another type than MapItem, the MapItemValidator should fail on every instance that has another type than <T>.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public interface MapItemValidator<T extends MapItem> extends Validator<MapItem> {

    /**
     * The validation method for a certain MapItem on the criterium.
     * @param mapItem
     *		The MapItem to validate.
     * @return True if the MapItem is valid according to the criterium, otherwise false.
     */
    @Override
    public abstract boolean isValid(MapItem mapItem);
}
