package projectswop20102011.domain.validators;

import projectswop20102011.domain.MapItem;

/**
 * An implementation for a MapItemValidator that checks if the object is an instance of a given type.
 * @param <T> The type of MapItems this validator will validate.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class TypeMapItemValidator<T extends MapItem> implements MapItemValidator<T> {

    /**
     * The class of mapitems that will be accepted
     */
    private final Class type;

    /**
     * Creates a new instance of a TypeMapItemValidator with a given type to check on.
     * @param type
     *		The type that must be equal to the validating MapItem.
     * @post This type is equal to the parameter type
     *		| type == getType()
     */
    public TypeMapItemValidator(Class<T> type) {
        this.type = type;
    }

    /**
     * Validates a given MapItem on type.
     * @param mapItem
     *		The MapItem to check if this is a valid MapItem.
     * @return True if the status of the MapItem equals this type, otherwise false.
     */
    @Override
    public boolean isValid(MapItem mapItem) {
        return getType().isAssignableFrom(mapItem.getClass());
    }

    /**
     * Returns the type to check a MapItem on.
     * @return The type that must be equal to the type of the MapItem to validate that MapItem.
     */
    public Class getType() {
        return type;
    }
}
