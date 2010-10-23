package projectswop20102011;

import java.util.ArrayList;
import java.util.Random;
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
	 * @throws InvalidEmergencyException
	 *		If the given emergency is invalid. I.e. there already exists an emergency
	 *		with the same id.
	 */
	public void addEmergency(Emergency e) throws InvalidEmergencyException{
		for(int i=0; i<getEmergencies().size(); ++i){
			if(getEmergencies().get(i).getId() == e.getId()){
				throw new InvalidEmergencyException();
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

	public long calculateValidId(){
		ArrayList<Long> ids = new ArrayList<Long>(getEmergencies().size());
		for(int i=0; i<getEmergencies().size(); ++i){
			ids.add(getEmergencies().get(i).getId());
		}
		Random r = new Random();
		long id = r.nextLong();
		while(ids.contains(id)){
			id = r.nextLong();
		}

		return id;
	}
}
