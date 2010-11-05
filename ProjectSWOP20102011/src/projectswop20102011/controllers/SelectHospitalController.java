package projectswop20102011.controllers;

import projectswop20102011.Ambulance;
import projectswop20102011.Hospital;
import projectswop20102011.TypeUnitBuildingEvaluationCriterium;
import projectswop20102011.UnitBuildingEvaluationCriterium;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class SelectHospitalController extends Controller {

    public SelectHospitalController (World world) throws InvalidWorldException {
        super(world);
    }

    public Ambulance findAmbulance (String name) {
        UnitBuildingEvaluationCriterium criterium = new TypeUnitBuildingEvaluationCriterium(Ambulance.class);
        return (Ambulance) this.getWorld().getUnitBuildingList().getUnitBuildingsByCriterium(criterium).getUnitBuildingFromName(name);
    }

    public Hospital[] getHospitalList () {
        return null;
    }

}
