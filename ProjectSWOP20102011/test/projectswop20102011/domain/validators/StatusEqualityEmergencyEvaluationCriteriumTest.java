package projectswop20102011.domain.validators;

import projectswop20102011.domain.validators.StatusEqualityEmergencyEvaluationCriterium;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import projectswop20102011.domain.Emergency;
import projectswop20102011.domain.SendableSeverity;
import projectswop20102011.domain.SendableStatus;
import projectswop20102011.domain.Fire;
import projectswop20102011.domain.FireSize;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.PublicDisturbance;
import projectswop20102011.domain.Robbery;
import projectswop20102011.domain.TrafficAccident;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidSendableSeverityException;
import projectswop20102011.exceptions.InvalidFireSizeException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;

public class StatusEqualityEmergencyEvaluationCriteriumTest {

	private Emergency e1r, e2r, e3r, e4r;//recorded but unhandled

	public StatusEqualityEmergencyEvaluationCriteriumTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() throws InvalidLocationException, InvalidSendableSeverityException, InvalidFireSizeException, NumberOutOfBoundsException {
		GPSCoordinate gp1 = new GPSCoordinate(1425, 1302);
		e1r = new Fire(gp1, SendableSeverity.BENIGN, "", FireSize.HOUSE, false, 1, 42);
		e2r = new PublicDisturbance(gp1, SendableSeverity.NORMAL, "", 16);
		e3r = new Robbery(gp1, SendableSeverity.SERIOUS, "", false, false);
		e4r = new TrafficAccident(gp1, SendableSeverity.URGENT, "", 128, 256);
	}

	@Test
	public void testIsValidEmergency() {
		StatusEqualityEmergencyEvaluationCriterium seeec1 = new StatusEqualityEmergencyEvaluationCriterium(SendableStatus.RECORDED_BUT_UNHANDLED);
		assertTrue(seeec1.isValidEmergency(e1r));
		assertTrue(seeec1.isValidEmergency(e2r));
		assertTrue(seeec1.isValidEmergency(e3r));
		assertTrue(seeec1.isValidEmergency(e4r));
	}
	
	@Test
	public void testConstructor() {
		StatusEqualityEmergencyEvaluationCriterium seeec1 = new StatusEqualityEmergencyEvaluationCriterium(SendableStatus.RECORDED_BUT_UNHANDLED);
		assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED, seeec1.getStatus());
		StatusEqualityEmergencyEvaluationCriterium seeec2 = new StatusEqualityEmergencyEvaluationCriterium(SendableStatus.RESPONSE_IN_PROGRESS);
		assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, seeec2.getStatus());
		StatusEqualityEmergencyEvaluationCriterium seeec3 = new StatusEqualityEmergencyEvaluationCriterium(SendableStatus.COMPLETED);
		assertEquals(SendableStatus.COMPLETED, seeec3.getStatus());
		StatusEqualityEmergencyEvaluationCriterium seeec4 = new StatusEqualityEmergencyEvaluationCriterium(null);
		assertEquals(null, seeec4.getStatus());
	}
}
