package projectswop20102011.controllers;

import java.util.ArrayList;
import projectswop20102011.domain.Emergency;
import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidWorldException;

/**
 * A controller used for the mapping between an id and an emergency.
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class EmergencyMapper extends Controller {

	private static ArrayList<Long> ids = new ArrayList<Long>();
	private static ArrayList<Emergency> emergencies = new ArrayList<Emergency>();
	private static long id = 0;

	public EmergencyMapper(World world) throws InvalidWorldException {
		super(world);
	}

	private long getId() {
		return id;
	}

	private void setId(long id) {
		EmergencyMapper.id = id;
	}

	private ArrayList<Long> getIds() {
		return ids;
	}

	private ArrayList<Emergency> getEmergencies() {
		return emergencies;
	}

	private void updateEmergencies() {
		for (Emergency e : getWorld().getEmergencyList()) {
			if (!getEmergencies().contains(e)) {
				getEmergencies().add(e);
				getIds().add(getId());
				setId(getId()+1);
			}
		}
	}

	public Emergency getEmergencyFromId(long id) {
		updateEmergencies();
		if (!getIds().contains(id)) {
			return null;
		}
		return getEmergencies().get(getIds().indexOf(id));
	}

	public Long getEmergencyId(Emergency em) {
		updateEmergencies();
		if (!getEmergencies().contains(em)) {
			return null;
		}
		return getIds().get(getEmergencies().indexOf(em));
	}
}
