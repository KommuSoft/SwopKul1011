package projectswop20102011.controllers;

import projectswop20102011.Ambulance;
import projectswop20102011.Hospital;
import projectswop20102011.HospitalDistanceComparator;
import projectswop20102011.TypeUnitBuildingEvaluationCriterium;
import projectswop20102011.UnitBuildingEvaluationCriterium;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidAmbulanceException;
import projectswop20102011.exceptions.InvalidHospitalException;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class SelectHospitalController extends Controller {

    public SelectHospitalController (World world) throws InvalidWorldException {
        super(world);
    }

    /**
     * Searches the ambulance that need to log in intro the system.
     * @param name The name of the ambulance to log in.
     * @return An ambulance from the world with a name equal to the given name, or null if no ambulance is found.
     */
    public Ambulance findAmbulance (String name) {
        UnitBuildingEvaluationCriterium criterium = new TypeUnitBuildingEvaluationCriterium(Ambulance.class);
        return (Ambulance) this.getWorld().getUnitBuildingList().getUnitBuildingsByCriterium(criterium).getUnitBuildingFromName(name);
    }

    /**
     * Generate a list of the nearest hospitals near the emergency assigned to the given ambulance.
     * @param ambulance The ambulance to generate the list from.
     * @return A list of the hospitals sorted on the distance to the location of the assigned emergency of the ambulance.
     */
    public Hospital[] getHospitalList (Ambulance ambulance) {
        HospitalDistanceComparator comparator = new HospitalDistanceComparator(ambulance.getEmergency());
        return this.getWorld().getUnitBuildingList().sort(comparator).toArray(new Hospital[0]);
    }

    /**
     * Assign the given hospital to the given ambulance.
     * @param ambulance The given ambulance.
     * @param hospital The given hospital.
     * @throws InvalidAmbulanceException If the given ambulance is not assigned to an emergency or is not at it's destination.
     * @throws InvalidHospitalException If the given hospital is not effective.
     */
    public void SelectHospital (Ambulance ambulance, Hospital hospital) throws InvalidAmbulanceException, InvalidHospitalException {
        ambulance.selectHospital(hospital);
    }

}