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
     * A variable that registers the UnitBuildingList of this world.
     */
    private UnitBuildingList unitBuildingList;

	/**
     * A variable that registers the TimeSensitiveList of this world.
     */
    private TimeSensitiveList timeSensitiveList;

	/**
	 * Creates a new world
	 */
    public World () {
        setEmergencyList(new EmergencyList());
		setUnitBuildingList(new UnitBuildingList());
		setTimeSensitiveList(new TimeSensitiveList());
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
     * Sets the UnitBuildingList of this world.
     * @param UnitBuildingList
     *		The new UnitBuildingList of this world.
	 * @post The is UnitBuildingList attribute of this world is
	 *		equal to the given parameter.
	 */
	private void setUnitBuildingList(UnitBuildingList unitBuildingList){
		this.unitBuildingList = unitBuildingList;
	}

	/**
     * Returns the UnitBuildingList of this world.
	 *
     * @return The UnitBuildingList of this world.
	 */
    public UnitBuildingList getUnitBuildingList () {
        return this.unitBuildingList;
    }

	/**
     * Sets the timeSensitiveList of this world.
     * @param timeSensitiveList
     *		The timeSensitiveList of this world.
	 * @post The is timeSensitiveList attribute of this world is
	 *		equal to the given parameter.
	 */
	private void setTimeSensitiveList(TimeSensitiveList timeSensitiveList){
		this.timeSensitiveList = timeSensitiveList;
	}

	/**
     * Returns the timeSensitiveList of this world.
     * @return The timeSensitiveList of this world.
	 */
    public TimeSensitiveList getTimeSensitiveList () {
        return this.timeSensitiveList;
    }

}