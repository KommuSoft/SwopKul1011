package projectswop20102011.externalsystem.adapters;

import be.kuleuven.cs.swop.api.ITime;

public class TimeAdapter implements ITime {

	private final int hours;
	private final int minutes;

	public TimeAdapter() {
		this.hours = 0;
		this.minutes = 0;
	}

	public TimeAdapter(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}

	@Override
	public int getHours() {
		return hours;
	}

	@Override
	public int getMinutes() {
		return minutes;
	}

	@Override
	public int compareTo(ITime o) {
		return Integer.valueOf(hours * 60 + minutes).compareTo(Integer.valueOf(o.getHours() * 60 + o.getMinutes()));
	}
}
