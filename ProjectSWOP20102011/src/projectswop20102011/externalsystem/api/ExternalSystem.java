package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.ITime;
import be.kuleuven.cs.swop.external.ExternalSystemException;
import be.kuleuven.cs.swop.external.IExternalSystem;

public class ExternalSystem implements IExternalSystem {

	@Override
	public void notifyTimeChanged(ITime time) throws ExternalSystemException, IllegalArgumentException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
