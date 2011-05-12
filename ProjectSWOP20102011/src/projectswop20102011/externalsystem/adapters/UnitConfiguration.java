package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IAmbulanceView;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IFireTruckView;
import be.kuleuven.cs.swop.api.IPoliceCarView;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.IUnitConfiguration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectswop20102011.World;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.ConcreteUnitsNeeded;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.Policecar;
import projectswop20102011.domain.Unit;
import projectswop20102011.domain.lists.MapItemList;
import projectswop20102011.domain.validators.TypeMapItemValidator;
import projectswop20102011.exceptions.InvalidDispatchUnitsConstraintException;
import projectswop20102011.exceptions.InvalidEmergencyException;

public class UnitConfiguration implements IUnitConfiguration {

	private final IEmergency emergency;
	private final World world;
	private final List<IFireTruckView> fireTrucks;
	private final List<IAmbulanceView> ambulances;
	private final List<IPoliceCarView> policeCars;
	private final List<IUnit> units;

	public UnitConfiguration(IEmergency emergency, World world) {
		this.emergency = emergency;
		this.world = world;

		fireTrucks = takeListOfFireTrucks();
		ambulances = takeListOfAmbulances();
		policeCars = takeListOfPoliceCars();
		units = takeAllUnits();
	}

	private IEmergency takeEmergency() {
		return emergency;
	}

	@Override
	public IEmergency getEmergency() {
		return emergency; //TODO: dit moet nog safe gemaakt worden (clone)
	}

	private ConcreteUnitsNeeded getConcreteUnitsNeeded() {
		EmergencyAdapter ea = (EmergencyAdapter) takeEmergency();
		ConcreteUnitsNeeded cun = null;
		try {
			cun = new ConcreteUnitsNeeded(ea.getEmergency(), ea.getEmergency().getDispatchConstraint());
		} catch (InvalidEmergencyException ex) {
			Logger.getLogger(UnitConfiguration.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvalidDispatchUnitsConstraintException ex) {
			Logger.getLogger(UnitConfiguration.class.getName()).log(Level.SEVERE, null, ex);
		}

		return cun;
	}
	
	private <T extends Unit> Set<Unit> getProposal(Class<T> t){
		TypeMapItemValidator<T> miv = new TypeMapItemValidator<T>(t);
		MapItemList<T> mil = world.getMapItemList().getSubMapItemListByValidator(miv);
		return getConcreteUnitsNeeded().getPolicyProposal(new HashSet<Unit>(mil.getMapItems()));
	}

	private List<IFireTruckView> takeListOfFireTrucks() {
		Set<Unit> units = getProposal(Firetruck.class);

		List<IFireTruckView> fireTruckViews = new ArrayList<IFireTruckView>();
		for (Unit u : units) {
			fireTruckViews.add(new FireTruckAdapter((Firetruck) u));
		}
		return fireTruckViews;
	}

	private List<IAmbulanceView> takeListOfAmbulances() {
		Set<Unit> units = getProposal(Ambulance.class);

		List<IAmbulanceView> ambulanceViews = new ArrayList<IAmbulanceView>();
		for (Unit u : units) {
			ambulanceViews.add(new AmbulanceAdapter((Ambulance) u));
		}
		return ambulanceViews;
	}

	private List<IPoliceCarView> takeListOfPoliceCars() {
		Set<Unit> units = getProposal(Policecar.class);

		List<IPoliceCarView> policeCarViews = new ArrayList<IPoliceCarView>();
		for (Unit u : units) {
			policeCarViews.add(new PoliceCarAdapter((Policecar) u));
		}
		return policeCarViews;
	}

	private List<IUnit> takeAllUnits() {
		List<IUnit> units = new ArrayList<IUnit>();
		units.addAll(ambulances);
		units.addAll(policeCars);
		units.addAll(fireTrucks);

		return units;
	}

	@Override
	public List<IFireTruckView> getListOfFireTrucks() {
		return fireTrucks;
	}

	@Override
	public List<IAmbulanceView> getListOfAmbulances() {
		return ambulances;
	}

	@Override
	public List<IPoliceCarView> getListOfPoliceCars() {
		return policeCars;
	}

	@Override
	public List<IUnit> getAllUnits() {
		return units;
	}
}