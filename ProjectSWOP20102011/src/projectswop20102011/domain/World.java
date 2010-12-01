package projectswop20102011.domain;

/**
 * A class that represents a world.
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke.
 */
public class World {

	/**
	 * A variable that registers the emergencyList of this world.
	 */
	private EmergencyList emergencyList;
	/**
	 * A variable that registers the unitBuildingList of this world.
	 */
	private MapItemList unitBuildingList;
	/**
	 * A variable that registers the timeSensitiveList of this world.
	 */
	private TimeSensitiveList timeSensitiveList;

	/**
	 * Creates a new world.
	 *
	 * @effect Initializes the emergencyList.
	 *			|setEmergencyList(new EmergencyList())
	 * @effect Initializes unitBuildingList.
	 *			|setUnitBuildingList(new MapItemList())
	 * @effect Initializes the timeSensitiveList.
	 *			|setTimeSensitiveList(new TimeSensitiveList())
	 */
	public World() {
		setEmergencyList(new EmergencyList());
		setUnitBuildingList(new MapItemList());
		setTimeSensitiveList(new TimeSensitiveList());
	}

	/**
	 * Sets the emergencylist of this world.
	 *
	 * @param emergencyList
	 *		The emergencyList of this World.
	 * @post The emergencyList of this World is set according to the given emergencyList.
	 *		|new.getEmergencyList()==emergencyList
	 */
	private void setEmergencyList(EmergencyList emergencyList) {
		this.emergencyList = emergencyList;
	}

	/**
	 * Returns the emergencyList of this world.
	 * @return The emergencyList of this world.
	 */
	public EmergencyList getEmergencyList() {
		return this.emergencyList;
	}

	/**
	 * Sets the MapItemList of this world.
	 * @param MapItemList
	 *		The new MapItemList of this world.
	 * @post The unitBuildingList of this World is set according to the given unitBuildingList.
	 *		|new.getUnitBuildingList()==unitBuildingList
	 */
	private void setUnitBuildingList(MapItemList unitBuildingList) {
		this.unitBuildingList = unitBuildingList;
	}

	/**
	 * Returns the unitBuildingList of this world.
	 * @return The unitBuildingList of this world.
	 */
	public MapItemList getUnitBuildingList() {
		return this.unitBuildingList;
	}

	/**
	 * Sets the timeSensitiveList of this world.
	 * @param timeSensitiveList
	 *		The timeSensitiveList of this world.
	 * @post The timeSensitiveList of this World is set according to the given timeSensitiveList.
	 *		|new.getTimeSensitiveList()==timeSensitiveList
	 */
	private void setTimeSensitiveList(TimeSensitiveList timeSensitiveList) {
		this.timeSensitiveList = timeSensitiveList;
	}

	/**
	 * Returns the timeSensitiveList of this world.
	 * @return The timeSensitiveList of this world.
	 */
	public TimeSensitiveList getTimeSensitiveList() {
		return this.timeSensitiveList;
	}
}