package projectswop20102011.controllers;

import java.util.ArrayList;
import projectswop20102011.World;
import projectswop20102011.domain.Disaster;
import projectswop20102011.exceptions.InvalidWorldException;

public class DisasterMapper extends Controller {

	private static ArrayList<Long> ids = new ArrayList<Long>();
	private static ArrayList<Disaster> disasters = new ArrayList<Disaster>();
	private static long id = 0;

	public DisasterMapper(World world) throws InvalidWorldException {
		super(world);
	}

	private long getId() {
		return id;
	}

	private void setId(long id) {
		DisasterMapper.id = id;
	}

	private ArrayList<Long> getIds() {
		return ids;
	}

	private ArrayList<Disaster> getDisasters() {
		return disasters;
	}

	private void updateDisasters() {
		for (Disaster e : getWorld().getDisasterList()) {
			if (!getDisasters().contains(e)) {
				getDisasters().add(e);
				getIds().add(getId());
				setId(getId() + 1);
			}
		}
	}

	public Disaster getDisasterFromId(long id) {
		updateDisasters();
		if (!getIds().contains(id)) {
			return null;
		}
		return getDisasters().get(getIds().indexOf(id));
	}

	public Long getDisasterId(Disaster em) {
		updateDisasters();
		if (!getDisasters().contains(em)) {
			return null;
		}
		return getIds().get(getDisasters().indexOf(em));
	}
}
