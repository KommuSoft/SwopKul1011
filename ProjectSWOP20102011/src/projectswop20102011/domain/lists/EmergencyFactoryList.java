package projectswop20102011.domain.lists;

import projectswop20102011.factories.EmergencyFactory;

/**
 * A list of EmergencyFactory object.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencyFactoryList extends GenericFactoryList {

    /**
     * Searches a Factory with the same name as the given name.
     * @param name The name to search for.
     * @return A factory with the same name as the given name in the List if the list contains such a factory, otherwise null.
     */
    @Override
    public EmergencyFactory getGenericFactoryFromName(String name) {
        return (EmergencyFactory) this.getGenericFactoryFromName(name);
    }

    /**
     * Adds a factory to the list.
     * @param factory The factory to add.
     */
    public void addEmergencyFactory(EmergencyFactory factory) {
        this.AddGenericFactory(factory);
    }

    /**
     * Removes a factory from the list.
     * @param factory The factory to remove.
     */
    public void removeEmergencyFactory(EmergencyFactory factory) {
        this.removeEmergencyFactory(factory);
    }
}
