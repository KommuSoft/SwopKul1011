package projectswop20102011.externalsystem;

import be.kuleuven.cs.swop.api.IEmergency;
import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.api.IUnit;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;

public class NullObjectExternalSystem implements IExternalSystem {

	@Override
	public void notifyTimeChanged(ITime itime) throws ExternalSystemException, IllegalArgumentException {
	}

	@Override
	public void notifyAssignment(IUnit iunit, IEmergency ie) throws ExternalSystemException, IllegalArgumentException {
	}

	@Override
	public void notifyRelease(IUnit iunit, IEmergency ie) throws ExternalSystemException, IllegalArgumentException {
	}
}
