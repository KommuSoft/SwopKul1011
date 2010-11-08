package projectswop20102011.controllers;

import projectswop20102011.TypeUnitBuildingEvaluationCriterium;
import projectswop20102011.Unit;
import projectswop20102011.UnitBuildingEvaluationCriterium;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EndOfEmergencyController extends Controller {

    public EndOfEmergencyController (World world) throws InvalidWorldException {
        super(world);
    }

    public Unit findUnit (String unitName) {
        UnitBuildingEvaluationCriterium criterium = new TypeUnitBuildingEvaluationCriterium(Unit.class);
        return (Unit) this.getWorld().getUnitBuildingList().getUnitBuildingsByCriterium(criterium).getUnitBuildingFromName(unitName);
    }

}
