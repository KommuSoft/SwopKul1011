package projectswop20102011.domain.lists;

import java.util.HashMap;
import projectswop20102011.factories.GenericFactory;

/**
 * A utility class providing utility methods and mothod signatures for a list of GenericFactory objects.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public abstract class GenericFactoryList {

    /**
     * The inner list.
     */
    private HashMap<String,GenericFactory> factoryList = new HashMap<String,GenericFactory>();

    /**
     * Search for a GenericFactory in the list with the same name as the given name.
     * @param name The name to search for.
     * @return A factory with the same name if this list contains one, otherwise null.
     */
    public GenericFactory getGenericFactoryFromName (String name) {
        return this.getFactoryList().get(name);
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
     * @param factory The factory to add to the list.
     */
    protected void AddGenericFactory (GenericFactory factory) {
        this.getFactoryList().put(factory.getName(),factory);
    }
    /**
     * Removes a factory from the list.
     * @param factory The factory to remove from the list.
     */
    protected void RemoveGenericFactory (GenericFactory factory) {
        this.getFactoryList().remove(factory.getName());
    }

}
