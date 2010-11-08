package projectswop20102011;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidEmergencySeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class StatusEqualityEmergencyEvaluationCriteriumTest {

    Emergency e1r, e2r, e3r, e4r;//recorded but unhandled

    public StatusEqualityEmergencyEvaluationCriteriumTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws InvalidLocationException, InvalidEmergencySeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
        GPSCoordinate gp1  = new GPSCoordinate(1425,1302);
        e1r = new Fire(gp1,EmergencySeverity.BENIGN,FireSize.HOUSE,false,1,42);
        e2r = new PublicDisturbance(gp1,EmergencySeverity.NORMAL,16);
        e3r = new Robbery(gp1,EmergencySeverity.SERIOUS,false,false);
        e4r = new TrafficAccident(gp1,EmergencySeverity.URGENT,128,256);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isValidEmergency method, of class StatusEqualityEmergencyEvaluationCriterium.
     */
    @Test
    public void testIsValidEmergency() {
        StatusEqualityEmergencyEvaluationCriterium seeec1 = new StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus.RECORDED_BUT_UNHANDLED);
        assertTrue(seeec1.isValidEmergency(e1r));
        assertTrue(seeec1.isValidEmergency(e2r));
        assertTrue(seeec1.isValidEmergency(e3r));
        assertTrue(seeec1.isValidEmergency(e4r));
    }

    /**
     * Test of constructor, of class StatusEqualityEmergencyEvaluationCriterium.
     */
    @Test
    public void testConstructor () {
        StatusEqualityEmergencyEvaluationCriterium seeec1 = new StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus.RECORDED_BUT_UNHANDLED);
        assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, seeec1.getStatus());
        StatusEqualityEmergencyEvaluationCriterium seeec2 = new StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus.RESPONSE_IN_PROGRESS);
        assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS, seeec2.getStatus());
        StatusEqualityEmergencyEvaluationCriterium seeec3 = new StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus.FINISHED);
        assertEquals(EmergencyStatus.FINISHED, seeec3.getStatus());
        StatusEqualityEmergencyEvaluationCriterium seeec4 = new StatusEqualityEmergencyEvaluationCriterium(null);
        assertEquals(null, seeec4.getStatus());
    }

}