package projectswop20102011.externalsystem;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.externalsystem.api.Time;

public class TimeTest {
	private int hours1, hours2, hours3, hours4;
	private int minutes1, minutes2, minutes3, minutes4;
	private Time time1, time2;


	@Before
	public void setUp(){
		hours1 = 10;
		minutes1 = 55;

		hours2 = 24;
		minutes2 = 60;

		hours3 = -1;
		minutes3 = -3;

		hours4 = 11;
		minutes4 = 1;
	}

	@Test
	public void testConstructor(){
		time1 = new Time(hours1, minutes1);
		assertEquals(hours1, time1.getHours());
		assertEquals(minutes1, time1.getMinutes());

		time2 = new Time(hours4, minutes4);
		assertEquals(hours4, time2.getHours());
		assertEquals(minutes4, time2.getMinutes());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOverflowConstructor(){
		time1 = new Time(hours2, minutes2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUnderflowConstructor(){
		time1 = new Time(hours3, minutes3);
	}

	@Test
	public void testCompareTo(){
		time1 = new Time(hours1, minutes1);
		time2 = new Time(hours4, minutes4);

		assertTrue(time1.compareTo(time2) < 0);
		assertTrue(time1.compareTo(time1) == 0);
		assertTrue(time2.compareTo(time1) > 0);
	}

}
