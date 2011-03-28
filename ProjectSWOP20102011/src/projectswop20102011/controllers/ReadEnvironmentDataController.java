package projectswop20102011.controllers;

import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Hospital;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.MapItem;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidAmountOfParametersException;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemTypeNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidWorldException;
import projectswop20102011.factories.AmbulanceFactory;
import projectswop20102011.factories.FiretruckFactory;
import projectswop20102011.factories.PolicecarFactory;

/**
 * A controller that reads in the environmental data, and constructs the units and buildings described in it.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class ReadEnvironmentDataController extends Controller {

    public ReadEnvironmentDataController(World world) throws InvalidWorldException {
        super(world);
    }

    private void addMapItemToMapItemList(MapItem mi) {
        this.getWorld().getMapItemList().addMapItem(mi);
    }
    private void addUnitToTimeSensitiveList (Unit unit) {
        this.getWorld().getTimeSensitiveList().addTimeSensitive(unit);
    }

    public void addHospital(String name, GPSCoordinate coordinate) throws InvalidMapItemNameException, InvalidLocationException {
        this.addMapItemToMapItemList(new Hospital(name, coordinate));
    }

    public void addFiretruck(String name, GPSCoordinate coordinate, long speed, long capacity) throws InvalidMapItemTypeNameException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException, InvalidCapacityException {
		FiretruckFactory ff = new FiretruckFactory();
        Unit u = ff.createMapItem(new Object[] {name, coordinate, speed, capacity});
        this.addMapItemToMapItemList(u);
        this.addUnitToTimeSensitiveList(u);
    }

    public void addPolicecar(String name, GPSCoordinate coordinate, long speed) throws InvalidMapItemTypeNameException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException {
		PolicecarFactory pf = new PolicecarFactory();
        Unit u = pf.createMapItem(new Object[] {name, coordinate, speed});
        this.addMapItemToMapItemList(u);
        this.addUnitToTimeSensitiveList(u);
    }

    public void addAmbulance(String name, GPSCoordinate coordinate, long speed) throws InvalidMapItemTypeNameException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidAmountOfParametersException  {
		AmbulanceFactory af = new AmbulanceFactory();
        Unit u = af.createMapItem(new Object[] {name, coordinate, speed});
        this.addMapItemToMapItemList(u);
        this.addUnitToTimeSensitiveList(u);
    }
}
