package projectswop20102011;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class StatusEqualityEmergencyEvaluationCriteriumTest {

    public StatusEqualityEmergencyEvaluationCriteriumTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isValidEmergency method, of class StatusEqualityEmergencyEvaluationCriterium.
     */
    @Test
    public void testIsValidEmergency() {
        Emergency emergency1 = new Fire();
        fail("how to test this??");
    }

    /**
     * Test of constructor, of class StatusEqualityEmergencyEvaluationCriterium.
     */
    @Test
    public void testConstructor () {
        StatusEqualityEmergencyEvaluationCriterium seeec1 = new StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus.RECORDED_BUT_UNHANDLED);
        assertEquals(seeec1.getStatus(),EmergencyStatus.RECORDED_BUT_UNHANDLED);
        StatusEqualityEmergencyEvaluationCriterium seeec2 = new StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus.RESPONSE_IN_PROGRESS);
        assertEquals(seeec2.getStatus(),EmergencyStatus.RESPONSE_IN_PROGRESS);
        StatusEqualityEmergencyEvaluationCriterium seeec3 = new StatusEqualityEmergencyEvaluationCriterium(EmergencyStatus.FINISHED);
        assertEquals(seeec3.getStatus(),EmergencyStatus.FINISHED);
        StatusEqualityEmergencyEvaluationCriterium seeec4 = new StatusEqualityEmergencyEvaluationCriterium(null);
        assertEquals(seeec4.getStatus(),null);
    }

}