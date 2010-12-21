package projectswop20102011.utils.parsers;

import projectswop20102011.domain.EmergencySeverity;
import java.util.Map;
import java.util.regex.Pattern;
import projectswop20102011.domain.FireSize;
import org.junit.Test;
import projectswop20102011.domain.EmergencyStatus;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.utils.ObjectHolder;
import static org.junit.Assert.*;

public class EnumParserTest {

    private EnumParser fireSizeParser = new EnumParser<FireSize>(FireSize.class);
    private EnumParser emergencyStatusParser = new EnumParser<EmergencyStatus>(EmergencyStatus.class);
    private EnumParser emergencySeverityParser = new EnumParser<EmergencySeverity>(EmergencySeverity.class);


    @Test
    public void testConstructor() {
        //TODO: test post-condities
        new EnumParser(FireSize.class);
        new EnumParser(EmergencyStatus.class);
        new EnumParser(EmergencySeverity.class);
    }

    /**
     * Test of parse method, of class EnumParser.
     */
    @Test
    public void testParse() throws ParsingException {
        ObjectHolder<FireSize> fireSizeHolder = new ObjectHolder<FireSize>();
        String textualRepresentation = "local";
        fireSizeParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(FireSize.LOCAL,fireSizeHolder.getObject());
        textualRepresentation = "house";
        fireSizeParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(FireSize.HOUSE,fireSizeHolder.getObject());
        textualRepresentation = "facility";
        fireSizeParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(FireSize.FACILITY,fireSizeHolder.getObject());

        textualRepresentation = "42local1425";
        fireSizeParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(FireSize.LOCAL,fireSizeHolder.getObject());
        textualRepresentation = "1302house1817";
        fireSizeParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(FireSize.HOUSE,fireSizeHolder.getObject());
        textualRepresentation = "2010facility2015";
        fireSizeParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(FireSize.FACILITY,fireSizeHolder.getObject());

        ObjectHolder<EmergencyStatus> emergencyStatusHolder = new ObjectHolder<EmergencyStatus>();
        textualRepresentation = "recorded but unhandled";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED,emergencyStatusHolder.getObject());
        textualRepresentation = "response in progress";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS,emergencyStatusHolder.getObject());
        textualRepresentation = "completed";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(EmergencyStatus.COMPLETED,emergencyStatusHolder.getObject());

        textualRepresentation = "42recorded but unhandled1425";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED,emergencyStatusHolder.getObject());
        textualRepresentation = "1302response in progress1817";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS,emergencyStatusHolder.getObject());
        textualRepresentation = "2010completed2015";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(EmergencyStatus.COMPLETED,emergencyStatusHolder.getObject());

        ObjectHolder<EmergencySeverity> emergencySeverityHolder = new ObjectHolder<EmergencySeverity>();
        textualRepresentation = "benign";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(EmergencySeverity.BENIGN,emergencySeverityHolder.getObject());
        textualRepresentation = "normal";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(EmergencySeverity.NORMAL,emergencySeverityHolder.getObject());
        textualRepresentation = "serious";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(EmergencySeverity.SERIOUS,emergencySeverityHolder.getObject());
        textualRepresentation = "urgent";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(EmergencySeverity.URGENT,emergencySeverityHolder.getObject());

        textualRepresentation = "42benign1425";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(EmergencySeverity.BENIGN,emergencySeverityHolder.getObject());
        textualRepresentation = "1302normal1817";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(EmergencySeverity.NORMAL,emergencySeverityHolder.getObject());
        textualRepresentation = "2010serious2015";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(EmergencySeverity.SERIOUS,emergencySeverityHolder.getObject());
        textualRepresentation = "1789urgent1879";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(EmergencySeverity.URGENT,emergencySeverityHolder.getObject());
    }

    /**
     * Test of canParse method, of class EnumParser.
     */
    @Test
    public void testCanParse() throws Exception {
        assertTrue(fireSizeParser.canParse("local"));
        assertTrue(fireSizeParser.canParse("_facility"));
        assertTrue(fireSizeParser.canParse("house_"));
        assertTrue(fireSizeParser.canParse("+->local<-+"));
        assertFalse(fireSizeParser.canParse("h1o2u3s4e5"));
        assertFalse(fireSizeParser.canParse("loc al"));
        assertFalse(fireSizeParser.canParse("fa-cil-ity"));
        assertFalse(fireSizeParser.canParse("@jkofsdnfjdsfk"));

        assertTrue(emergencySeverityParser.canParse("benign"));
        assertTrue(emergencySeverityParser.canParse("Beninormal"));
        assertFalse(emergencySeverityParser.canParse("Serieus"));
        assertFalse(emergencySeverityParser.canParse("Kritiek"));
    }
    

}