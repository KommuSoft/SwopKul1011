package projectswop20102011;
/**
 * A class that represents a world
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class World {

    /**
     * A variable that registers the emergencylist of this world.
     */
    private EmergencyList emergencyList;

	/**
	 * Creates a new world
	 */
    public World () {
        setEmergencyList(new EmergencyList());
    }

	/**
     * Sets the emergencylist of this world.
     * @param emergencyList
     *		The emergencylist of this world.
	 */
	private void setEmergencyList(EmergencyList emergencyList){
		this.emergencyList = emergencyList;
	}

	/**
     * Returns the emergencylist of this world.
	 * 
     * @return The emergencylist of this world.
	 */
    public EmergencyList getEmergencyList () {
        return this.emergencyList;
    }

}