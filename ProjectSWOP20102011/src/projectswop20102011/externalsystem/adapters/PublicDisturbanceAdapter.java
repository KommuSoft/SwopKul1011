package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IPublicDisturbanceView;
import projectswop20102011.domain.PublicDisturbance;
public class PublicDisturbanceAdapter extends EmergencyAdapter implements IPublicDisturbanceView{

	public PublicDisturbanceAdapter(PublicDisturbance publicDisturbance){
		super(publicDisturbance);
	}

	@Override
	public int getNumberOfPeople() {
		return (int) ((PublicDisturbance)getEmergency()).getNumberOfPeople();
	}

}
