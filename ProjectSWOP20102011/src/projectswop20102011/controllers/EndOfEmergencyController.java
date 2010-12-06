package projectswop20102011.controllers;

import projectswop20102011.domain.validators.TypeMapItemEvaluationCriterium;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemEvaluationCriterium;
import projectswop20102011.domain.World;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidUnitException;
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
        MapItemEvaluationCriterium criterium = new TypeMapItemEvaluationCriterium(Unit.class);
        return (Unit) this.getWorld().getMapItemList().getMapItemsByCriterium(criterium).getMapItemFromName(unitName);
    }

    public void indicateEndOfEmergency (Unit unit) throws InvalidEmergencyException, InvalidLocationException, InvalidUnitException {
        if(unit == null) {
            throw new InvalidUnitException("The given unit must be effective.");
        }
        unit.finishedJob();
    }

}
