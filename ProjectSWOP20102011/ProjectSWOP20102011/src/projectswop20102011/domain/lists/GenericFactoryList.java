package projectswop20102011.domain.lists;

import java.util.HashMap;
import projectswop20102011.factories.GenericFactory;

/**
 * A utility class providing utility methods and method signatures for a list of GenericFactory objects.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class GenericFactoryList {

    /**
     * The inner list.
     */
    private HashMap<String,GenericFactory> factoryList = new HashMap<String,GenericFactory>();

    /**
     * Search for a GenericFactory in the list with the same name as the given name.
     * @param name
	 *		The name to search for.
     * @return A factory with the same name if this list contains one, otherwise null.
     */
    public GenericFactory getGenericFactoryFromName (String name) {
        return this.getFactoryList().get(name.toLowerCase());
    }

    /**
     * Gets the list of factories.
     * @return the list of factories.
     */
    protected HashMap<String,GenericFactory> getFactoryList () {
        return this.factoryList;
    }

    /**
     * Adds a GenericFactory to the list.
     * @param factory 
	 *		The factory to add to the list.
	 * @effect The given factory is added to this list
	 *		|this.getFactoryList().put(factory.getName().toLowerCase(),factory)
     */
    protected void addGenericFactory (GenericFactory factory) {
        this.getFactoryList().put(factory.getName().toLowerCase(),factory);
    }
    /**
     * Removes a factory from the list.
     * @param factory 
	 *		The factory to remove from the list.
	 * @effect The given factory is removed from this list
	 *		|this.getFactoryList().remove(factory.getName().toLowerCase())
     */
    protected void removeGenericFactory (GenericFactory factory) {
        this.getFactoryList().remove(factory.getName().toLowerCase());
    }

}
