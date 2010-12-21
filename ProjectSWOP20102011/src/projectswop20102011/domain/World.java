package projectswop20102011.domain;

import projectswop20102011.domain.lists.EmergencyList;
import projectswop20102011.domain.lists.MapItemList;
import projectswop20102011.domain.lists.TimeSensitiveList;

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
	 * A variable that registers the mapItemList of this world.
	 */
	private MapItemList mapItemList;
	/**
	 * A variable that registers the timeSensitiveList of this world.
	 */
	private TimeSensitiveList timeSensitiveList;

	/**
	 * Creates a new world.
	 *
	 * @effect Initializes the emergencyList.
	 *			|setEmergencyList(new EmergencyList())
	 * @effect Initializes mapItemList.
	 *			|setmapItemList(new MapItemList())
	 * @effect Initializes the timeSensitiveList.
	 *			|setTimeSensitiveList(new TimeSensitiveList())
	 */
	public World() {
		setEmergencyList(new EmergencyList());
		setMapItemList(new MapItemList());
		setTimeSensitiveList(new TimeSensitiveList());
	}

	/**
	 * Sets the emergencyList of this world.
	 *
	 * @param emergencyList
	 *		The emergencyList of this World.
	 * @post The emergencyList of this World is set according to the given emergencyList.
	 *		|new.getEmergencyList() == emergencyList
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
	 * @post The mapItemList of this World is set according to the given mapItemList.
	 *		|new.getMapItemList() == mapItemList
	 */
	private void setMapItemList(MapItemList mapItemList) {
		this.mapItemList = mapItemList;
	}

	/**
	 * Returns the mapItemList of this world.
	 * @return The mapItemList of this world.
	 */
	public MapItemList getMapItemList() {
		return this.mapItemList;
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
