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

/**
 * This class contains a unit configuration for a specific emergency. A unit configuration consists of a number of suggested units for each required unit type.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class UnitConfiguration implements IUnitConfiguration {

	/**
	 * The Emergency of this UnitConfiguration.
	 */
	private final IEmergency emergency;
	/**
	 * The World of this UnitConfiguration.
	 */
	private final World world;
	/**
	 * The List of IFireTruckViews of the UnitConfiguration.
	 */
	private final List<IFireTruckView> fireTrucks;
	/**
	 * The List of IAmbulanceViews of the UnitConfiguration.
	 */
	private final List<IAmbulanceView> ambulances;
	/**
	 * The List of IPoliceCarViews of the UnitConfiguration.
	 */
	private final List<IPoliceCarView> policeCars;
	/**
	 * The List of IUnits of the UnitConfiguration.
	 */
	private final List<IUnit> units;

	/**
	 * Creates UnitConfiguration from the given Emergency and World.
	 * @param emergency
	 *		The Emergency of this UnitConfiguration.
	 * @param world
	 *		The world of this UnitConfiguration.
	 */
	public UnitConfiguration(IEmergency emergency, World world) {
		this.emergency = emergency;
		this.world = world;
		fireTrucks = takeListOfFireTrucks();
		ambulances = takeListOfAmbulances();
		policeCars = takeListOfPoliceCars();
		units = takeAllUnits();
	}

	/**
	 * Retrieves the emergency for which the unit configuration is created.
	 * @return The associated emergency.
	 */
	private IEmergency takeEmergency() {
		return emergency;
	}

	/**
	 * Retrieves the emergency for which the unit configuration is created.
	 * @return The associated emergency.
	 */
	@Override
	public IEmergency getEmergency() {
		return emergency; //TODO: dit moet nog safe gemaakt worden (clone)
	}

	/**
	 * Returns the ConcreteUnitsNeeded.
	 * @return The ConcreteUnitsNeeded.
	 */
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

	/**
	 * Returns the proposal of units for this UnitConfiguration.
	 * @param <T>
	 *		The type of the desired Units.
	 * @param t
	 *		The class of the desired Units.
	 * @return The proposal.
	 */
	private <T extends Unit> Set<Unit> getProposal(Class<T> t){
		TypeMapItemValidator<T> miv = new TypeMapItemValidator<T>(t);
		MapItemList<T> mil = world.getMapItemList().getSubMapItemListByValidator(miv);
		return getConcreteUnitsNeeded().getPolicyProposal(new HashSet<Unit>(mil.getMapItems()));
	}

	/**
	 * Calculates and returns the list of fire trucks that are needed.
	 * @return The list of fire trucks that are needed.
	 */
	private List<IFireTruckView> takeListOfFireTrucks() {
		Set<Unit> units = getProposal(Firetruck.class);

		List<IFireTruckView> fireTruckViews = new ArrayList<IFireTruckView>();
		for (Unit u : units) {
			fireTruckViews.add(new FireTruckAdapter((Firetruck) u));
		}
		return fireTruckViews;
	}

	/**
	 * Calculates and returns the list of ambulances that are needed.
	 * @return The list of ambulances that are needed.
	 */
	private List<IAmbulanceView> takeListOfAmbulances() {
		Set<Unit> units = getProposal(Ambulance.class);

		List<IAmbulanceView> ambulanceViews = new ArrayList<IAmbulanceView>();
		for (Unit u : units) {
			ambulanceViews.add(new AmbulanceAdapter((Ambulance) u));
		}
		return ambulanceViews;
	}

	/**
	 * Calculates and returns the list of police cars that are needed.
	 * @return The list of police cars that are needed.
	 */
	private List<IPoliceCarView> takeListOfPoliceCars() {
		Set<Unit> units = getProposal(Policecar.class);

		List<IPoliceCarView> policeCarViews = new ArrayList<IPoliceCarView>();
		for (Unit u : units) {
			policeCarViews.add(new PoliceCarAdapter((Policecar) u));
		}
		return policeCarViews;
	}

	/**
	 * Calculates and returns the list of units that are needed.
	 * @return The list of units that are needed.
	 */
	private List<IUnit> takeAllUnits() {
		List<IUnit> units = new ArrayList<IUnit>();
		units.addAll(ambulances);
		units.addAll(policeCars);
		units.addAll(fireTrucks);

		return units;
	}

	/**
	 * Retrieves the list of fire trucks present in the unit configuration.
	 * @return The list of fire trucks.
	 * @note No particular order is expected within the list.
	 */
	@Override
	public List<IFireTruckView> getListOfFireTrucks() {
		return fireTrucks;
	}

	/**
	 * Retrieves the list of ambulances present in the unit configuration.
	 * @return The list of ambulances.
	 * @note No particular order is expected within the list.
	 */
	@Override
	public List<IAmbulanceView> getListOfAmbulances() {
		return ambulances;
	}

	/**
	 * Retrieves the list of police cars present in the unit configuration.
	 * @return The list of police cars.
	 * @note No particular order is expected within the list.
	 */
	@Override
	public List<IPoliceCarView> getListOfPoliceCars() {
		return policeCars;
	}

	/**
	 * Retrieves the list of all units present in the unit configuration.
	 * @return The list of all units.
	 * @note No particular order is expected within the list.
	 */
	@Override
	public List<IUnit> getAllUnits() {
		return units;
	}
}