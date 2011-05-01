package projectswop20102011.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidDispatchPolicyException;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidUnitsNeededException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class DefaultDispatchPolicyTest {

	private Emergency e1, e2;
	private long x1, y1, x2, y2, x3, y3;
	private GPSCoordinate location1, location2, location3;
	private EmergencySeverity severity1;
	private long numberOfPeople1;
	private String name1, name2;
	private long speed1, speed2;
	private Unit u1, u2;

	@Before
	public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, NumberOutOfBoundsException, InvalidMapItemNameException, InvalidSpeedException {
		x1 = 0;
		y1 = 0;
		x2 = 10;
		y2 = 10;
		x3 = 20;
		y3 = 20;

		location1 = new GPSCoordinate(x1, y1);
		location2 = new GPSCoordinate(x2, y2);
		location3 = new GPSCoordinate(x3, y3);

		severity1 = EmergencySeverity.BENIGN;
		numberOfPeople1 = 10;

		name1 = "PC1";
		name2 = "PC2";

		speed1 = 5;
		speed2 = 10;

		e1 = new PublicDisturbance(location1, severity1, "", numberOfPeople1);
                e2 = new PublicDisturbance(location2, severity1, "", numberOfPeople1);
		u1 = new Policecar(name1, location2, speed1);
		u2 = new Policecar(name2, location3, speed2);
	}

	@Test
	public void testCompare() {
		assertTrue(e1.getDispatchPolicy().compare(u1, u2) < 0);
		assertTrue(e1.getDispatchPolicy().compare(u1, u1) == 0);
		assertTrue(e1.getDispatchPolicy().compare(u2, u1) > 0);
	}

        @Test
        public void testIsValidSuccessor () {
            assertTrue(e1.getDispatchPolicy().isValidSuccessor(null));
            assertFalse(e1.getDispatchPolicy().isValidSuccessor(e1.getDispatchPolicy()));
            assertFalse(e1.getDispatchPolicy().isValidSuccessor(e2.getDispatchPolicy()));
        }
        @Test(expected=InvalidUnitsNeededException.class)
        public void testConstructor () throws InvalidUnitsNeededException {
            new DefaultDispatchPolicy(null);
        }
        @Test(expected=InvalidDispatchPolicyException.class)
        public void testSetSuccessor1 () throws InvalidDispatchPolicyException {
            e1.getDispatchPolicy().setSuccessor(e1.getDispatchPolicy());
        }
        @Test(expected=InvalidDispatchPolicyException.class)
        public void testSetSuccessor2 () throws InvalidDispatchPolicyException {
            e1.getDispatchPolicy().setSuccessor(e2.getDispatchPolicy());
        }

}
