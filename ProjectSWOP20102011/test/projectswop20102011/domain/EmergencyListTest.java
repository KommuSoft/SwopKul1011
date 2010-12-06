package projectswop20102011.domain;

import projectswop20102011.domain.validators.EmergencyEvaluationCriterium;
import projectswop20102011.domain.validators.StatusEqualityEmergencyEvaluationCriterium;
import projectswop20102011.exceptions.InvalidEmergencyException;
import projectswop20102011.exceptions.InvalidCoordinateException;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class EmergencyListTest {

    private EmergencyList el1, el2, el3;
    private Emergency e1, e2;
    private GPSCoordinate l1, l2;
    private long x1, y1, x2, y2;

    @Before
    public void setUp() throws InvalidCoordinateException, InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        x1 = 12;
        y1 = 789;
        x2 = 95;
        y2 = 65;
        l1 = new GPSCoordinate(x1, y1);
        l2 = new GPSCoordinate(x2, y2);

        el1 = new EmergencyList();
        e1 = new Fire(l1, EmergencySeverity.URGENT, "", FireSize.LOCAL, false, 0, 1337);
        el2 = new EmergencyList();
        e2 = new PublicDisturbance(l1, EmergencySeverity.URGENT, "", 1302);
        el3 = new EmergencyList();
    }

    @Test
    public void testConstructor() {
        assertNotNull(el1);
        assertEquals(0, el1.getEmergencies().size());
    }

    @Test
    public void testAdd() throws InvalidEmergencyException {
        el1.addEmergency(e1);
        assertEquals(1, el1.getEmergencies().size());
        assertTrue(el1.getEmergencies().contains(e1));
        el1.addEmergency(e2);
        assertEquals(2, el1.getEmergencies().size());
        assertTrue(el1.getEmergencies().contains(e2));
    }

    @Test
    public void testGetEmergencyFromId () {
        assertEquals(null,el3.getEmergencyFromId(e1.getId()));
        assertEquals(null,el3.getEmergencyFromId(e2.getId()));
        assertEquals(null,el3.getEmergencyFromId(-1302));
        assertEquals(null,el3.getEmergencyFromId(-1425));
        el3.addEmergency(e1);
        assertEquals(e1,el3.getEmergencyFromId(e1.getId()));
        assertEquals(null,el3.getEmergencyFromId(e2.getId()));
        assertEquals(null,el3.getEmergencyFromId(-1302));
        assertEquals(null,el3.getEmergencyFromId(-1425));
        el3.addEmergency(e2);
        assertEquals(e1,el3.getEmergencyFromId(e1.getId()));
        assertEquals(e2,el3.getEmergencyFromId(e2.getId()));
        assertEquals(null,el3.getEmergencyFromId(-1302));
        assertEquals(null,el3.getEmergencyFromId(-1425));
    }

	@Test
	public void testGetEmergenciesByCriterium(){
		EmergencyEvaluationCriterium evc = new StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus.RECORDED_BUT_UNHANDLED);
		el1.addEmergency(e1);
        el1.addEmergency(e2);
		EmergencyList emergencies = el1.getEmergenciesByCriterium(evc);

		assertEquals(2, emergencies.getEmergencies().size());
	}

	@Test
	public void testToArray(){
		el1.addEmergency(e1);
        el1.addEmergency(e2);
		Emergency[] emergencies = el1.toArray();

		assertEquals(2, emergencies.length);

		boolean emergency1 = false;
		boolean emergency2 = false;
		for(int i=0; i<emergencies.length; ++i){
			if(emergencies[i].equals(e1)){
				emergency1 = true;
			}
			if(emergencies[i].equals(e2)){
				emergency2 = true;
			}
		}
		assertTrue(emergency1);
		assertTrue(emergency2);
	}
}
