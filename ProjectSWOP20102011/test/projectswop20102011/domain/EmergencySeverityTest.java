package projectswop20102011.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.exceptions.InvalidSendableSeverityException;

public class EmergencySeverityTest {

    @Test
    public void testMatches() {
        assertTrue(SendableSeverity.BENIGN.matches("BENIGN"));
        assertTrue(SendableSeverity.BENIGN.matches("BeNiGn"));
        assertTrue(SendableSeverity.BENIGN.matches("benign"));
        assertTrue(SendableSeverity.BENIGN.matches(SendableSeverity.BENIGN.toString()));
        assertFalse(SendableSeverity.BENIGN.matches("Begin"));
        assertTrue(SendableSeverity.NORMAL.matches("NORMAL"));
        assertTrue(SendableSeverity.NORMAL.matches("NoRmAl"));
        assertTrue(SendableSeverity.NORMAL.matches("normal"));
        assertTrue(SendableSeverity.NORMAL.matches(SendableSeverity.NORMAL.toString()));
        assertFalse(SendableSeverity.BENIGN.matches("normaal"));
        assertTrue(SendableSeverity.SERIOUS.matches("SERIOUS"));
        assertTrue(SendableSeverity.SERIOUS.matches("SeRiOuS"));
        assertTrue(SendableSeverity.SERIOUS.matches("serious"));
        assertTrue(SendableSeverity.SERIOUS.matches(SendableSeverity.SERIOUS.toString()));
        assertFalse(SendableSeverity.SERIOUS.matches("serieus"));
        assertTrue(SendableSeverity.URGENT.matches("URGENT"));
        assertTrue(SendableSeverity.URGENT.matches("UrGeNt"));
        assertTrue(SendableSeverity.URGENT.matches("urgent"));
        assertTrue(SendableSeverity.URGENT.matches(SendableSeverity.URGENT.toString()));
        assertFalse(SendableSeverity.URGENT.matches("UGent"));
    }

    @Test
    public void testParse() throws InvalidSendableSeverityException {
        assertEquals(SendableSeverity.BENIGN, SendableSeverity.parse(SendableSeverity.BENIGN.toString()));
        assertEquals(SendableSeverity.NORMAL, SendableSeverity.parse(SendableSeverity.NORMAL.toString()));
        assertEquals(SendableSeverity.SERIOUS, SendableSeverity.parse(SendableSeverity.SERIOUS.toString()));
        assertEquals(SendableSeverity.URGENT, SendableSeverity.parse(SendableSeverity.URGENT.toString()));
        assertEquals(SendableSeverity.BENIGN, SendableSeverity.parse(SendableSeverity.BENIGN.toString().toUpperCase()));
        assertEquals(SendableSeverity.NORMAL, SendableSeverity.parse(SendableSeverity.NORMAL.toString().toUpperCase()));
        assertEquals(SendableSeverity.SERIOUS, SendableSeverity.parse(SendableSeverity.SERIOUS.toString().toUpperCase()));
        assertEquals(SendableSeverity.URGENT, SendableSeverity.parse(SendableSeverity.URGENT.toString().toUpperCase()));
    }

    @Test(expected = InvalidSendableSeverityException.class)
    public void testParseException1() throws InvalidSendableSeverityException {
        SendableSeverity.parse("begin");
    }

    @Test(expected = InvalidSendableSeverityException.class)
    public void testParseException2() throws InvalidSendableSeverityException {
        SendableSeverity.parse("normaal");
    }

    @Test(expected = InvalidSendableSeverityException.class)
    public void testParseException3() throws InvalidSendableSeverityException {
        SendableSeverity.parse("serieus");
    }

    @Test(expected = InvalidSendableSeverityException.class)
    public void testParseException4() throws InvalidSendableSeverityException {
        SendableSeverity.parse("UGent");
    }

    @Test
    public void testToString() {
        assertEquals("benign", SendableSeverity.BENIGN.toString());
        assertEquals("normal", SendableSeverity.NORMAL.toString());
        assertEquals("serious", SendableSeverity.SERIOUS.toString());
        assertEquals("urgent", SendableSeverity.URGENT.toString());
    }

    @Test
    public void testGetMaximum () {
        assertEquals(SendableSeverity.BENIGN,SendableSeverity.BENIGN.getMaximum(SendableSeverity.BENIGN));
        assertEquals(SendableSeverity.NORMAL,SendableSeverity.BENIGN.getMaximum(SendableSeverity.NORMAL));
        assertEquals(SendableSeverity.SERIOUS,SendableSeverity.BENIGN.getMaximum(SendableSeverity.SERIOUS));
        assertEquals(SendableSeverity.URGENT,SendableSeverity.BENIGN.getMaximum(SendableSeverity.URGENT));

        assertEquals(SendableSeverity.NORMAL,SendableSeverity.NORMAL.getMaximum(SendableSeverity.BENIGN));
        assertEquals(SendableSeverity.NORMAL,SendableSeverity.NORMAL.getMaximum(SendableSeverity.NORMAL));
        assertEquals(SendableSeverity.SERIOUS,SendableSeverity.NORMAL.getMaximum(SendableSeverity.SERIOUS));
        assertEquals(SendableSeverity.URGENT,SendableSeverity.NORMAL.getMaximum(SendableSeverity.URGENT));

        assertEquals(SendableSeverity.SERIOUS,SendableSeverity.SERIOUS.getMaximum(SendableSeverity.BENIGN));
        assertEquals(SendableSeverity.SERIOUS,SendableSeverity.SERIOUS.getMaximum(SendableSeverity.NORMAL));
        assertEquals(SendableSeverity.SERIOUS,SendableSeverity.SERIOUS.getMaximum(SendableSeverity.SERIOUS));
        assertEquals(SendableSeverity.URGENT,SendableSeverity.SERIOUS.getMaximum(SendableSeverity.URGENT));

        assertEquals(SendableSeverity.URGENT,SendableSeverity.URGENT.getMaximum(SendableSeverity.BENIGN));
        assertEquals(SendableSeverity.URGENT,SendableSeverity.URGENT.getMaximum(SendableSeverity.NORMAL));
        assertEquals(SendableSeverity.URGENT,SendableSeverity.URGENT.getMaximum(SendableSeverity.SERIOUS));
        assertEquals(SendableSeverity.URGENT,SendableSeverity.URGENT.getMaximum(SendableSeverity.URGENT));
    }
}
