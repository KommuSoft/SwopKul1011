package projectswop20102011.controllers;

import projectswop20102011.domain.validators.TypeMapItemValidator;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import projectswop20102011.exceptions.InvalidFinishJobException;
import projectswop20102011.exceptions.InvalidUnitException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * TODO: controller documentie-eren.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EndOfTaskController extends Controller {

    public EndOfTaskController(World world) throws InvalidWorldException {
        super(world);
    }

    public Unit findUnit(String unitName) {
        MapItemValidator criterium = new TypeMapItemValidator(Unit.class);
        return (Unit) this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItemFromName(unitName);
    }

    public void indicateEndOfTask(Unit unit) throws InvalidUnitException, InvalidEmergencyStatusException, InvalidFinishJobException {
        if (unit == null) {
            throw new InvalidUnitException("The given unit must be effective.");
        }
        unit.finishedJob();
    }
}
