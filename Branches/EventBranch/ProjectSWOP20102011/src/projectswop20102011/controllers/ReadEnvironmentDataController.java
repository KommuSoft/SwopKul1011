package projectswop20102011.controllers;

import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.MapItem;
import projectswop20102011.World;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.TimeSensitive;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller that reads in the environmental data, and constructs the units and buildings (i.e. a hospital) described in it.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ReadEnvironmentDataController extends Controller {

    /**
     * Creates a new ReadEnvironmentDataController controller with a given world it will modify.
     * @param world The world the controller will modify.
     * @throws InvalidWorldException If the given world is invalid.
     */
    public ReadEnvironmentDataController(World world) throws InvalidWorldException {
        super(world);
    }

    /**
     * Add a MapItem to the world.
     * @param mi The MapItem object to add to the world.
     */
    private void addMapItemToMapItemList(MapItem mi) {
        this.getWorld().getMapItemList().addMapItem(mi);
    }

    /**
     * Adds a TimeSensitive object to the TimeSensitiveList of the World.
     * @param timeSensitive the object to add to the TimeSensitiveList of the world.
     */
    private void addToTimeSensitiveList(TimeSensitive timeSensitive) {
        this.getWorld().getTimeSensitiveList().addTimeSensitive(timeSensitive);
    }

    /**
     * Adds a hospital to the world with a given name and coordinate.
     * @param name The name of the hospital.
     * @param coordinate The location of the hospital.
     * @throws InvalidMapItemNameException If the given name is invalid.
     * @throws InvalidLocationException If the given location is invalid.
     */
    public void addHospital(String name, GPSCoordinate coordinate) throws InvalidMapItemNameException, InvalidLocationException {
        this.addMapItemToMapItemList(new Hospital(name, coordinate));
    }

    /**
     * Adds a Firetruck to the world.
     * @param name The name of the Firetruck.
     * @param coordinate The homelocation of the Firetruck.
     * @param speed The speed of the firetruck.
     * @param capacity The capacity (in liters) of the Firetruck.
     * @throws InvalidLocationException If the given homelocation is invalid.
     * @throws InvalidMapItemNameException If the given name is invalid.
     * @throws InvalidSpeedException If the given speed is invalid.
     * @throws InvalidCapacityException If the given capacity is invalid.
     */
    public void addFiretruck(String name, GPSCoordinate coordinate, long speed, long capacity) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException {
        Unit u = new Firetruck(name, coordinate, speed, capacity);
        this.addMapItemToMapItemList(u);
        this.addToTimeSensitiveList(u);
    }

    /**
     * Adds a new Policecar to the world.
     * @param name The name of the policecar.
     * @param coordinate The homelocation of the policecar.
     * @param speed The speed of the policecar.
     * @throws InvalidLocationException If the given homelocation is invalid.
     * @throws InvalidMapItemNameException If the given name of the policecar is invalid.
     * @throws InvalidSpeedException If the given speed is invalid.
     */
    public void addPolicecar(String name, GPSCoordinate coordinate, long speed) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
        Unit u = new Policecar(name, coordinate, speed);
        this.addMapItemToMapItemList(u);
        this.addToTimeSensitiveList(u);
    }

    /**
     * Adds a new Ambulance to the world.
     * @param name The name of the ambulance.
     * @param coordinate The homelocation of the ambulance.
     * @param speed The speed of the ambulance.
     * @throws InvalidLocationException If the given homelocation is invalid.
     * @throws InvalidMapItemNameException If the given name of the ambulance is invalid.
     * @throws InvalidSpeedException If the given speed is invalid.
     */
    public void addAmbulance(String name, GPSCoordinate coordinate, long speed) throws InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException {
        Unit u = new Ambulance(name, coordinate, speed);
        this.addMapItemToMapItemList(u);
        this.addToTimeSensitiveList(u);
    }
}
