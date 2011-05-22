package projectswop20102011.controllers;

import java.util.HashSet;
import java.util.Set;
import projectswop20102011.World;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.EventHandler;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used for the use case where units are dispatched to a disaster.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsToDisasterController extends DispatchController {

    public DispatchUnitsToDisasterController(World world, EventHandler eventHandler) throws InvalidWorldException {
        super(world, eventHandler);
    }

    public Set<Unit> getUnitsByPolicy(Disaster disaster) {
        MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
        HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
        return disaster.getPolicyProposal(mapItems);
    }

    //TODO: string hiervan maken [is waarschijnlijk OK nu]
    public String getRequiredUnits(Disaster disaster) {
        String s = "";

        for (int i = 0; i < disaster.getEmergencies().size(); ++i) {
            s += "For emergency " + (i + 1) + " in the disaster we want: ";
            s += disaster.getEmergencies().get(i).getDispatchConstraint().toString();
            s += "\n";
        }
        return s;
//		
//		MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
//		HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
//		return disaster.getPolicyProposalAllSeverities(mapItems);
    }

    public void dispatchToDisaster(Disaster disaster, Set<Unit> units) throws InvalidSendableStatusException, InvalidEmergencyException {
        disaster.assignUnits(units, getEventHandler());
    }
}
