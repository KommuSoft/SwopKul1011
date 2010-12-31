package projectswop20102011.domain.lists;

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
	 * A variable registering the time of the world.
	 */
	private long time;

        /**
         * A list of EmergencyFactory objects
         */
        private EmergencyFactoryList emergencyFactoryList;
        /**
         * A list that contains a collection of parsers used to parse data.
         */
        private ParserList parserList;

	/**
	 * Creates a new world.
	 *
	 * @effect Initializes the emergencyList.
	 *			|setEmergencyList(new EmergencyList())
	 * @effect Initializes mapItemList.
	 *			|setmapItemList(new MapItemList())
	 * @effect Initializes the timeSensitiveList.
	 *			|setTimeSensitiveList(new TimeSensitiveList())
	 * @effect Initializes the time.
	 *			|setTime(0)
	 */
	public World() {
		setEmergencyList(new EmergencyList());
		setMapItemList(new MapItemList());
		setTimeSensitiveList(new TimeSensitiveList());
                setEmergencyFactoryList(new EmergencyFactoryList());
		setTime(0);
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
	 * @param mapItemList
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

	/**
	 * Returns the time of the world.
	 * @return The time of the world.
	 */
	public long getTime() {
		return time;
	}

	/**
	 * Sets the time of the world to the given value.
	 * @param time
	 *		The new time of the world.
	 */
	public final void setTime(long time) {
		this.time = time;
	}

        /**
         * Gets the EmergencyFactoryList
         * @return The EmergencyFactoryList.
         */
        public EmergencyFactoryList getEmergencyFactoryList () {
            return this.emergencyFactoryList;
        }

        /**
         * Sets the emergencyFactoryList to the given emergencyFactoryList
         * @param emergencyFactoryList
         *          the given emergencyFactoryList.
         */
        private void setEmergencyFactoryList (EmergencyFactoryList emergencyFactoryList) {
            this.emergencyFactoryList = emergencyFactoryList;
        }

        /**
         * Sets the ParserList of this world to the given ParserList.
         * @param parserList The given ParserList.
         */
        private void setParserList (ParserList parserList) {
            this.parserList = parserList;
        }

        /**
         * Gets the list of Parsers in the World.
         * @return the list of parsers in the World.
         */
        public ParserList getParserList () {
            return this.parserList;
        }
}
