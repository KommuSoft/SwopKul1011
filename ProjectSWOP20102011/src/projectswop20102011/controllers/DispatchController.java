package projectswop20102011.controllers;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import projectswop20102011.World;
import projectswop20102011.domain.EventHandler;
import projectswop20102011.domain.Sendable;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.validators.AvailableUnitsMapItemValidator;
import projectswop20102011.domain.validators.MapItemValidator;
import projectswop20102011.domain.validators.OrValidator;
import projectswop20102011.domain.validators.TypeUnitValidator;
import projectswop20102011.domain.validators.UnitToTargetableDistanceComparator;
import projectswop20102011.domain.validators.Validator;
import projectswop20102011.exceptions.InvalidTargetableException;
import projectswop20102011.exceptions.InvalidWorldException;

public abstract class DispatchController extends Controller{
	
	/**
     * An eventHandler where the notifications should be sent to.
     */
    private EventHandler eventHandler;

    /**
     * Creates a new instance of a DispatchUnitsController with a given word to operate on.
     * @param world The world where the controller will operate on.
     * @throws InvalidWorldException If the given world is invalid.
     */
    public DispatchController(World world, EventHandler eventHandler) throws InvalidWorldException {
        super(world);
        this.eventHandler = eventHandler;
    }
	
	public List<Unit> getAvailableUnitsNeededSorted(Sendable sendable) throws InvalidClassException, InvalidTargetableException {
        //TODO: dit kan waarschijnlijk een heel klein beetje mooier misschien wellicht? [Willem: beter?]
        MapItemValidator criterium = new AvailableUnitsMapItemValidator();
        Set<Unit> units = getUnitsByPolicyAllSeverities(sendable);
        Set<Class<? extends Unit>> usedUnitTypes = new HashSet<Class<? extends Unit>>();
        for (Unit u : units) {
            usedUnitTypes.add(u.getClass());
        }
        Collection<Validator<? super Unit>> tuvs = new ArrayList<Validator<? super Unit>>();
        for (Class<? extends Unit> type : usedUnitTypes) {
            tuvs.add(new TypeUnitValidator(type));
        }
        Validator<Unit> validator = new OrValidator<Unit>(tuvs);
        List<Unit> allUnits = this.getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getSortedCopy(new UnitToTargetableDistanceComparator(sendable));
        List<Unit> result = new ArrayList<Unit>(0);
        for (Unit u : allUnits) {
            if (validator.isValid(u)) {
                result.add(u);
            }
        }
        return result;
    }
	
	public Set<Unit> getUnitsByPolicyAllSeverities(Sendable sendable) {
		MapItemValidator<Unit> criterium = new AvailableUnitsMapItemValidator();
        HashSet<Unit> mapItems = getWorld().getMapItemList().getSubMapItemListByValidator(criterium).getMapItems();
        return sendable.getPolicyProposal(mapItems);
	}
	
	public Set<Unit> getUnitsByPolicy(Sendable sendable) {
		return getUnitsByPolicyAllSeverities(sendable);
	}
	
	protected EventHandler getEventHandler(){
		return eventHandler;
	}
	
}
