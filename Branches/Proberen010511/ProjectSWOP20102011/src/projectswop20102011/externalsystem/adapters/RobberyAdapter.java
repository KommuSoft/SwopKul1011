package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IRobberyView;
import projectswop20102011.domain.Robbery;

public class RobberyAdapter extends EmergencyAdapter implements IRobberyView{

	public RobberyAdapter(Robbery robbery){
		super(robbery);
	}

	@Override
	public boolean isArmed() {
		return ((Robbery)getEmergency()).isArmed();
	}

	@Override
	public boolean isInProgress() {
		return ((Robbery)getEmergency()).isInProgress();
	}

}
