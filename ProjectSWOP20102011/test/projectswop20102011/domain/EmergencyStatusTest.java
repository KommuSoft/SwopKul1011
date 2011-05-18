package projectswop20102011.domain;

import projectswop20102011.exceptions.InvalidSendableStatusException;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmergencyStatusTest {

    @Test
    public void testMatches() {
        assertTrue(SendableStatus.RECORDED_BUT_UNHANDLED.matches("RECORDED BUT UNHANDLED"));
        assertTrue(SendableStatus.RECORDED_BUT_UNHANDLED.matches("ReCoRdEd bUt uNhAnDlEd"));
        assertTrue(SendableStatus.RECORDED_BUT_UNHANDLED.matches("recorded but unhandled"));
        assertTrue(SendableStatus.RECORDED_BUT_UNHANDLED.matches(SendableStatus.RECORDED_BUT_UNHANDLED.toString()));
        assertFalse(SendableStatus.RECORDED_BUT_UNHANDLED.matches("recorded but unmanageable"));
        assertTrue(SendableStatus.RESPONSE_IN_PROGRESS.matches("RESPONSE IN PROGRESS"));
        assertTrue(SendableStatus.RESPONSE_IN_PROGRESS.matches("ReSpOnSe iN PrOgReSs"));
        assertTrue(SendableStatus.RESPONSE_IN_PROGRESS.matches("response in progress"));
        assertTrue(SendableStatus.RESPONSE_IN_PROGRESS.matches(SendableStatus.RESPONSE_IN_PROGRESS.toString()));
        assertFalse(SendableStatus.RESPONSE_IN_PROGRESS.matches("response out of progress"));
        assertTrue(SendableStatus.COMPLETED.matches("COMPLETED"));
        assertTrue(SendableStatus.COMPLETED.matches("CoMpLeTeD"));
        assertTrue(SendableStatus.COMPLETED.matches("completed"));
        assertTrue(SendableStatus.COMPLETED.matches(SendableStatus.COMPLETED.toString()));
        assertFalse(SendableStatus.COMPLETED.matches("finem"));
    }

    @Test
    public void testParse() throws InvalidSendableStatusException {
        assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED, SendableStatus.parse(SendableStatus.RECORDED_BUT_UNHANDLED.toString()));
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, SendableStatus.parse(SendableStatus.RESPONSE_IN_PROGRESS.toString()));
        assertEquals(SendableStatus.COMPLETED, SendableStatus.parse(SendableStatus.COMPLETED.toString()));
        assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED, SendableStatus.parse(SendableStatus.RECORDED_BUT_UNHANDLED.toString().toUpperCase()));
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS, SendableStatus.parse(SendableStatus.RESPONSE_IN_PROGRESS.toString().toUpperCase()));
        assertEquals(SendableStatus.COMPLETED, SendableStatus.parse(SendableStatus.COMPLETED.toString().toUpperCase()));
    }

    @Test(expected = InvalidSendableStatusException.class)
    public void testParseException1() throws InvalidSendableStatusException {
        SendableStatus.parse("recorded but unmanageable");
    }

    @Test(expected = InvalidSendableStatusException.class)
    public void testParseException2() throws InvalidSendableStatusException {
        SendableStatus.parse("response out of progress");
    }

    @Test(expected = InvalidSendableStatusException.class)
    public void testParseException3() throws InvalidSendableStatusException {
        SendableStatus.parse("finem");
    }

    @Test
    public void testToString() {
        assertEquals("completed", SendableStatus.COMPLETED.toString());
        assertEquals("recorded but unhandled", SendableStatus.RECORDED_BUT_UNHANDLED.toString());
        assertEquals("response in progress", SendableStatus.RESPONSE_IN_PROGRESS.toString());
    }

    @Test
    public void testCombine () {
        assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED,SendableStatus.RECORDED_BUT_UNHANDLED.combine(SendableStatus.RECORDED_BUT_UNHANDLED));
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,SendableStatus.RECORDED_BUT_UNHANDLED.combine(SendableStatus.RESPONSE_IN_PROGRESS));
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,SendableStatus.RECORDED_BUT_UNHANDLED.combine(SendableStatus.COMPLETED));

        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,SendableStatus.RESPONSE_IN_PROGRESS.combine(SendableStatus.RECORDED_BUT_UNHANDLED));
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,SendableStatus.RESPONSE_IN_PROGRESS.combine(SendableStatus.RESPONSE_IN_PROGRESS));
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,SendableStatus.RESPONSE_IN_PROGRESS.combine(SendableStatus.COMPLETED));

        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,SendableStatus.COMPLETED.combine(SendableStatus.RECORDED_BUT_UNHANDLED));
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,SendableStatus.COMPLETED.combine(SendableStatus.RESPONSE_IN_PROGRESS));
        assertEquals(SendableStatus.COMPLETED,SendableStatus.COMPLETED.combine(SendableStatus.COMPLETED));
    }

}
