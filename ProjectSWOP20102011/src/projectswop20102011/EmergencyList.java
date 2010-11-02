package projectswop20102011;

import java.util.Iterator;
import java.util.HashSet;

/**
 * A list of emergencies where every emergency is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 * @invar Every Emergency in this EmergencyList is unique.
 */
public class EmergencyList implements Iterable<Emergency> {

    /**
     * The inner list to manage the emergencies.
     */
    private final HashSet<Emergency> emergencies;

    /**
     * Creating a new instance of an EmergencyList. At this moment this list
     * doesn't contain any emergency.
     */
    public EmergencyList() {
        this.emergencies = new HashSet<Emergency>();
    }

    /**
     * Returns the list of emergencies.
     * @return The list of emergencies.
     */
    public HashSet<Emergency> getEmergencies() {
        return (HashSet<Emergency>) emergencies.clone();
    }

    /**
     * Adds the given emergency to this list of emergencies if the given emergency
     * is not already in this list of emergencies.
     * @param e
     *		Emergency to be appended to this list of emergencies.
     * @post This EmergencyList contains the given Emergency.
     */
    public void addEmergency(Emergency e){
        if(!this.emergencies.contains(e)) {
            emergencies.add(e);
        }
    }

    /**
     * Returns all the emergencies in this EmergencyList that are valid to a certain EmergencyCriterium.
     * @param criterium the createrium to validate potential solution on.
     * @return a list with all the emergencies in this EmergencyList who are validated by the EmergencyCriterium.
     */
    public EmergencyList getEmergenciesByCriterium(EmergencyEvaluationCriterium criterium) {
        EmergencyList list = new EmergencyList();
        for(Emergency e : this) {
            if(criterium.isValidEmergency(e)) {
                list.addEmergency(e);
            }
        }
        return list;
    }

    /**
     * Generates an iterator to iterate over the list of emergencies.
     * @return An iterator to iterate of the list of emergencies.
     */
    @Override
    public Iterator<Emergency> iterator() {
        return this.emergencies.iterator();
    }

    /**
     * Returns an array of all the emergencies in this EmergencyList.
     * @return An array of all the emergencies in this EmergencyList.
     */
    public Emergency[] toArray () {
        return this.emergencies.toArray(new Emergency[0]);
    }
    
}
