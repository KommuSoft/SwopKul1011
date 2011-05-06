package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.IAmbulanceView;
import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.IFireTruckView;
import be.kuleuven.cs.swop.api.IPoliceCarView;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.api.IUnitConfiguration;
import java.util.ArrayList;
import java.util.List;

public class UnitConfiguration implements IUnitConfiguration{
	private final IEmergency emergency;

	public UnitConfiguration(IEmergency emergency){
		this.emergency = emergency;
	}

	private IEmergency takeEmergency(){
		return emergency;
	}

	@Override
	public IEmergency getEmergency() {
		return emergency; //TODO: dit moet nog safe gemaakt worden (clone)
	}

	@Override
	public List<IFireTruckView> getListOfFireTrucks() {
		List<IUnit> units = takeEmergency().getAssignedUnits();
		List<IFireTruckView> fireTrucks = new ArrayList<IFireTruckView>();

		for(IUnit u : units) {
			if(u.getClass().getSimpleName().equals("IFireTruckView")){
				fireTrucks.add((IFireTruckView) u);
			}
		}

		return fireTrucks;
	}

	@Override
	public List<IAmbulanceView> getListOfAmbulances() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<IPoliceCarView> getListOfPoliceCars() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public List<IUnit> getAllUnits() {
		return takeEmergency().getAssignedUnits();
	}

}
