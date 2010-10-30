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
     * A variable that registers the notTimeSensitiveUnitBuildingList of this world.
     */
    private NotTimeSensitiveUnitBuildingList notTimeSensitiveUnitBuildingList;

	/**
     * A variable that registers the timeSensitiveUnitBuildingList of this world.
     */
    private TimeSensitiveUnitBuildingList timeSensitiveUnitBuildingList;

	/**
	 * Creates a new world
	 */
    public World () {
        setEmergencyList(new EmergencyList());
		setNotTimeSensitiveUnitBuildingList(new NotTimeSensitiveUnitBuildingList());
		setTimeSensitiveUnitBuildingList(new TimeSensitiveUnitBuildingList());
    }

	/**
     * Sets the emergencylist of this world.
     * @param emergencyList
     *		The emergencylist of this world.
	 * @post The is emergencyList attribute of this world is equal to the given parameter.
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

	/**
     * Sets the notTimeSensitiveUnitBuildingList of this world.
     * @param notTimeSensitiveUnitBuildingList
     *		The new NotTimeSensitiveUnitBuildingList of this world.
	 * @post The is notTimeSensitiveUnitBuildingLiest attribute of this world is
	 *		equal to the given parameter.
	 */
	private void setNotTimeSensitiveUnitBuildingList(NotTimeSensitiveUnitBuildingList notTimeSensitiveUnitBuildingList){
		this.notTimeSensitiveUnitBuildingList = notTimeSensitiveUnitBuildingList;
	}

	/**
     * Returns the notTimeSensitiveUnitBuildingList of this world.
	 *
     * @return The notTimeSensitiveUnitBuildingList of this world.
	 */
    public NotTimeSensitiveUnitBuildingList getNotTimeSensitiveUnitBuildingList () {
        return this.notTimeSensitiveUnitBuildingList;
    }

	/**
     * Sets the timeSensitiveUnitBuildingList of this world.
     * @param timeSensitiveUnitBuildingList
     *		The timeSensitiveUnitBuildingList of this world.
	 * @post The is timeSensitiveUnitBuildingLiest attribute of this world is
	 *		equal to the given parameter.
	 */
	private void setTimeSensitiveUnitBuildingList(TimeSensitiveUnitBuildingList timeSensitiveUnitBuildingList){
		this.timeSensitiveUnitBuildingList = timeSensitiveUnitBuildingList;
	}

	/**
     * Returns the timeSensitiveUnitBuildingList of this world.
     * @return The timeSensitiveUnitBuildingList of this world.
	 */
    public TimeSensitiveUnitBuildingList getTimeSensitiveUnitBuildingList () {
        return this.timeSensitiveUnitBuildingList;
    }

}