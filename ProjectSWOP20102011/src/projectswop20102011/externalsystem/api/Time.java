package projectswop20102011.externalsystem.api;

import be.kuleuven.cs.swop.api.ITime;

public class Time implements ITime {

	private final int hours;
	private final int minutes;

	public Time(int hours, int minutes) {
		if ((hours < 0) || (hours > 23) || (minutes < 0) || (minutes > 59)) {
			throw new IllegalArgumentException("Invalid time specification");
		}

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
		if (o == null) {
			throw new IllegalArgumentException("Time to compare to cannot be null");
		}

		Integer time1 = Integer.valueOf(getHours() * 60 + getMinutes());
		Integer time2 = Integer.valueOf(o.getHours() * 60 + o.getMinutes());
		return time1.compareTo(time2);
	}
}
