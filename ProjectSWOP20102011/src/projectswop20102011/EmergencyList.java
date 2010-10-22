package projectswop20102011;

import java.util.ArrayList;
/**
 * A list of emergencies where every emergency is unique.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class EmergencyList {

    /**
     * The inner list to manage the emergencies.
     */
    private final ArrayList<Emergency> emergencies;

    /**
     * Creating a new instance of an EmergencyList. At this moment this list
	 * doesn't contain any emergency.
     */
    public EmergencyList () {
        this.emergencies = new ArrayList<Emergency>();
    }

	/**
	 * Returns the list of emergencies.
	 * @return The list of emergencies.
	 */
	public ArrayList<Emergency> getEmergencies(){
		return (ArrayList<Emergency>) emergencies.clone();
	}
	
	/**
	 * Adds the given emergency to this list of emergencies if the given emergency
	 * is not already in this list of emergencies.
	 * @param e
	 *		Emergency to be appended to this list of emergencies.
	 */
	public void addEmergency(Emergency e){
		for(int i=0; i<getEmergencies().size(); ++i){
			if(getEmergencies().get(i).getId() == e.getId()){
				throw new RuntimeException();
			}
		}
		emergencies.add(e);
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
