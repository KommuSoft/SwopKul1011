package projectswop20102011.controllers;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.World;
import projectswop20102011.domain.Disaster;
import projectswop20102011.domain.EventHandler;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.domain.validators.OrValidator;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.domain.validators.UnitToTargetableDistanceComparator;
import projectswop20102011.domain.validators.Validator;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidSendableStatusException;
import projectswop20102011.exceptions.InvalidTargetableException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used for the use case where units are dispatched to a disaster.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class DispatchUnitsToDisasterController extends Controller {

    /**
     * An eventHandler where the notifications should be sent to.
     */
    private EventHandler eventHandler;

    public DispatchUnitsToDisasterController(World world, EventHandler eventHandler) throws InvalidWorldException {
        super(world);
        this.eventHandler = eventHandler;
    }

    public Set<Unit> getUnitsByPolicy(Disaster disaster) {
        MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
        HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
        return disaster.getPolicyProposal(mapItems);
    }

    public Set<Unit> getUnitsByPolicyAllSeverities(Disaster disaster) {
        MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
        HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
        return disaster.getPolicyProposalAllSeverities(mapItems);
    }

    public List<Unit> getAvailableUnitsSorted(Disaster disaster) throws InvalidClassException, InvalidTargetableException {
        //TODO: dit kan waarschijnlijk een heel klein beetje mooier misschien wellicht? [Willem: beter?]
        MapItemValidator criterium = new AvailableUnitsMapItemValidator();
        Set<Unit> units = getUnitsByPolicyAllSeverities(disaster);
        Set<Class<? extends Unit>> usedUnitTypes = new HashSet<Class<? extends Unit>>();
        for (Unit u : units) {
            usedUnitTypes.add(u.getClass());
        }
        Collection<Validator<? super Unit>> tuvs = new ArrayList<Validator<? super Unit>>();
        for (Class<? extends Unit> type : usedUnitTypes) {
            tuvs.add(new TypeUnitValidator(type));
        }
        Validator<Unit> validator = new OrValidator<Unit>(tuvs);
        List<Unit> allUnits = this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getSortedCopy(new UnitToTargetableDistanceComparator(disaster));
        List<Unit> result = new ArrayList<Unit>(0);
        for (Unit u : allUnits) {
            if (validator.isValid(u)) {
                result.add(u);
            }
        }
        return result;
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
        disaster.assignUnits(units, eventHandler);
    }
}
