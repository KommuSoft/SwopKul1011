package projectswop20102011.controllers;

import projectswop20102011.Ambulance;
import projectswop20102011.Firetruck;
import projectswop20102011.GPSCoordinate;
import projectswop20102011.Hospital;
import projectswop20102011.Policecar;
import projectswop20102011.UnitBuilding;
import projectswop20102011.World;
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

    private void addUnitBuildingToUnitBuildingList(UnitBuilding ub) {
        this.getWorld().getUnitBuildingList().addUnitBuilding(ub);
    }

    public void addHospital(String name, GPSCoordinate coordinate) throws InvalidUnitBuildingNameException, InvalidLocationException {
        this.addUnitBuildingToUnitBuildingList(new Hospital(name, coordinate));
    }

    public void addFiretruck(String name, GPSCoordinate coordinate, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        this.addUnitBuildingToUnitBuildingList(new Firetruck(name, coordinate, speed));
    }

    public void addPolicecar(String name, GPSCoordinate coordinate, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        this.addUnitBuildingToUnitBuildingList(new Policecar(name, coordinate, speed));
    }

    public void addAmbulance(String name, GPSCoordinate coordinate, long speed) throws InvalidLocationException, InvalidUnitBuildingNameException, InvalidSpeedException {
        this.addUnitBuildingToUnitBuildingList(new Ambulance(name, coordinate, speed));
    }
}
