package projectswop20102011.externalsystem.adapters;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class TimeAdapterTest {

	private TimeAdapter time1, time2;
	private int hours0, hours1, hours2;
	private int minutes0, minutes1, minutes2;

	@Before
	public void setUp() {
		hours0 = 0;
		hours1 = 1;
		hours2 = -1;

		minutes0 = 0;
		minutes1 = 1;
		minutes2 = -1;
	}

	@Test
	public void testDefaultConstructor() {
		time1 = new TimeAdapter();
		assertEquals(0, time1.getHours());
		assertEquals(0, time1.getMinutes());
	}

	@Test
	public void testConstructor() throws NumberOutOfBoundsException {
		time1 = new TimeAdapter(hours1, minutes1);
		assertEquals(hours1, time1.getHours());
		assertEquals(minutes1, time1.getMinutes());

		time2 = new TimeAdapter(hours0, minutes0);
		assertEquals(hours0, time2.getHours());
		assertEquals(minutes0, time2.getMinutes());
	}

	@Test(expected = NumberOutOfBoundsException.class)
	public void testConstructorInvalidHours() throws NumberOutOfBoundsException {
		time1 = new TimeAdapter(hours2, minutes0);
	}

	@Test(expected = NumberOutOfBoundsException.class)
	public void testConstructorInvalidMinutes() throws NumberOutOfBoundsException {
		time1 = new TimeAdapter(hours0, minutes2);

	}
}
