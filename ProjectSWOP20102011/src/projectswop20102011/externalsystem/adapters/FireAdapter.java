package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IFireView;
import projectswop20102011.domain.Fire;

public class FireAdapter extends EmergencyAdapter implements IFireView {

	public FireAdapter(Fire fire){
		super(fire);
	}

	@Override
	public String getSize() {
		return ((Fire)getEmergency()).getSize().toString();
	}

	@Override
	public boolean isChemical() {
		return ((Fire)getEmergency()).isChemical();
	}

	@Override
	public boolean hasTrappedPeople() {
		return ((Fire)getEmergency()).getTrappedPeople() > 0;
	}

	@Override
	public int getNumberOfInjured() {
		return (int) ((Fire)getEmergency()).getNumberOfInjured();
	}


}
