package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.ITime;

public class Time implements ITime{

	@Override
	public int getHours() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int getMinutes() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public int compareTo(ITime o) {
		return getHours()*60+getMinutes()-o.getHours()*60+o.getMinutes();
	}

}