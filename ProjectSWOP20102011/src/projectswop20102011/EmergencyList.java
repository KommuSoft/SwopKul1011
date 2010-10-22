package projectswop20102011;

import java.util.ArrayList;
/**
 * A list of Emergency's where every Emergency is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
class EmergencyList {

    /**
     * The inner list to manage the emergencies.
     */
    private final ArrayList<Emergency> emergencies;

    /**
     * Creating a new instance of an EmergencyList.
     */
    public EmergencyList () {
        this.emergencies = new ArrayList<Emergency>();
    }

    /**
     * Returns all the emergencies in this EmergencyList that are valid to a certain EmergencyCriterium.
     * @param criterium the createrium to validate potential solution on.
     * @return a list with all the emergencies in this EmergencyList who are validated by the EmergencyCriterium.
     */
    public Emergency[] getEmergenciesByCriterium (EmergencyEvaluationCriterium criterium) {
        return null;
    }

}
