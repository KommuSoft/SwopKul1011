package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidEmergencyStatusException;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmergencyStatusTest {

    @Test
    public void testMatches() {
        assertTrue(EmergencyStatus.RECORDED_BUT_UNHANDLED.matches("RECORDED BUT UNHANDLED"));
        assertTrue(EmergencyStatus.RECORDED_BUT_UNHANDLED.matches("ReCoRdEd bUt uNhAnDlEd"));
        assertTrue(EmergencyStatus.RECORDED_BUT_UNHANDLED.matches("recorded but unhandled"));
        assertTrue(EmergencyStatus.RECORDED_BUT_UNHANDLED.matches(EmergencyStatus.RECORDED_BUT_UNHANDLED.getTextual()));
        assertFalse(EmergencyStatus.RECORDED_BUT_UNHANDLED.matches("recorded but unmanageable"));
        assertTrue(EmergencyStatus.RESPONSE_IN_PROGRESS.matches("RESPONSE IN PROGRESS"));
        assertTrue(EmergencyStatus.RESPONSE_IN_PROGRESS.matches("ReSpOnSe iN PrOgReSs"));
        assertTrue(EmergencyStatus.RESPONSE_IN_PROGRESS.matches("response in progress"));
        assertTrue(EmergencyStatus.RESPONSE_IN_PROGRESS.matches(EmergencyStatus.RESPONSE_IN_PROGRESS.getTextual()));
        assertFalse(EmergencyStatus.RESPONSE_IN_PROGRESS.matches("response out of progress"));
        assertTrue(EmergencyStatus.COMPLETED.matches("COMPLETED"));
        assertTrue(EmergencyStatus.COMPLETED.matches("CoMpLeTeD"));
        assertTrue(EmergencyStatus.COMPLETED.matches("completed"));
        assertTrue(EmergencyStatus.COMPLETED.matches(EmergencyStatus.COMPLETED.getTextual()));
        assertFalse(EmergencyStatus.COMPLETED.matches("finem"));
    }

    @Test
    public void testParse() throws InvalidEmergencyStatusException {
        assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, EmergencyStatus.parse(EmergencyStatus.RECORDED_BUT_UNHANDLED.getTextual()));
        assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS, EmergencyStatus.parse(EmergencyStatus.RESPONSE_IN_PROGRESS.getTextual()));
        assertEquals(EmergencyStatus.COMPLETED, EmergencyStatus.parse(EmergencyStatus.COMPLETED.getTextual()));
        assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED, EmergencyStatus.parse(EmergencyStatus.RECORDED_BUT_UNHANDLED.getTextual().toUpperCase()));
        assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS, EmergencyStatus.parse(EmergencyStatus.RESPONSE_IN_PROGRESS.getTextual().toUpperCase()));
        assertEquals(EmergencyStatus.COMPLETED, EmergencyStatus.parse(EmergencyStatus.COMPLETED.getTextual().toUpperCase()));
    }

    @Test(expected = InvalidEmergencyStatusException.class)
    public void testParseException1() throws InvalidEmergencyStatusException {
        EmergencyStatus.parse("recorded but unmanageable");
    }

    @Test(expected = InvalidEmergencyStatusException.class)
    public void testParseException2() throws InvalidEmergencyStatusException {
        EmergencyStatus.parse("response out of progress");
    }

    @Test(expected = InvalidEmergencyStatusException.class)
    public void testParseException3() throws InvalidEmergencyStatusException {
        EmergencyStatus.parse("finem");
    }

	@Test
	public void testToString(){
		assertEquals("completed", EmergencyStatus.COMPLETED.toString());
		assertEquals("recorded but unhandled", EmergencyStatus.RECORDED_BUT_UNHANDLED.toString());
		assertEquals("response in progress", EmergencyStatus.RESPONSE_IN_PROGRESS.toString());
	}
}
