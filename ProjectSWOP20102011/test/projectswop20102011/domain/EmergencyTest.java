package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmergencyTest{
	private GPSCoordinate gp1, gp2;
	private long x1, y1;
	private EmergencyStatus es1, es2;
	
	@Before
    public void setUp(){
        x1 = 3141592;
        y1 = 2718281;

        gp1 = new GPSCoordinate(x1, y1);
		gp2 = null;

		es1 = EmergencyStatus.COMPLETED;
		es2 = null;
    }

	@Test
	public void testIsValidLocation(){
		assertTrue(Emergency.isValidLocation(gp1));
		assertFalse(Emergency.isValidLocation(gp2));
	}

	@Test
	public void testIsValidStatus(){
		assertTrue(Emergency.isValidStatus(es1));
		assertFalse(Emergency.isValidStatus(es2));
	}

}