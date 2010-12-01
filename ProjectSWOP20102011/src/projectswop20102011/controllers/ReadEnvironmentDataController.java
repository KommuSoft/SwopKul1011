package projectswop20102011.controllers;

import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.MapItem;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitBuildingNameException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller that reads in the environmental data, and constructs the units and buildings described in it.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ReadEnvironmentDataController extends Controller {

    public ReadEnvironmentDataController(World world) throws InvalidWorldException {
        super(world);
    }

    private void addUnitBuildingToUnitBuildingList(MapItem ub) {
        this.getWorld().getUnitBuildingList().addUnitBuilding(ub);
    }
    private void addUnitToTimeSensitiveList (Unit unit) {
        this.getWorld().getTimeSensitiveList().addTimeSensitive(unit);
    }

    public void addHospital(String name, GPSCoordinate coordinate) throws InvalidUnitBuildingNameException, InvalidLocationException {
        this.addUnitBuildingToUnitBuildingList(new Hospital(name, coordinate));
    }

    public void addFiretruck(String name, GPSCoordinate coordinate, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        Unit u = new Firetruck(name, coordinate, speed);
        this.addUnitBuildingToUnitBuildingList(u);
        this.addUnitToTimeSensitiveList(u);
    }

    public void addPolicecar(String name, GPSCoordinate coordinate, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        Unit u = new Policecar(name, coordinate, speed);
        this.addUnitBuildingToUnitBuildingList(u);
        this.addUnitToTimeSensitiveList(u);
    }

    public void addAmbulance(String name, GPSCoordinate coordinate, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        Unit u = new Ambulance(name, coordinate, speed);
        this.addUnitBuildingToUnitBuildingList(u);
        this.addUnitToTimeSensitiveList(u);
    }
}
