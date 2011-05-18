package projectswop20102011.utils.parsers;

import projectswop20102011.domain.SendableSeverity;
import java.util.Map;
import java.util.regex.Pattern;
import projectswop20102011.domain.FireSize;
import org.junit.Test;
import projectswop20102011.domain.SendableStatus;
import projectswop20102011.exceptions.ParsingException;
import projectswop20102011.utils.ObjectHolder;
import static org.junit.Assert.*;

public class EnumParserTest {

    private EnumParser fireSizeParser = new EnumParser<FireSize>(FireSize.class);
    private EnumParser emergencyStatusParser = new EnumParser<SendableStatus>(SendableStatus.class);
    private EnumParser emergencySeverityParser = new EnumParser<SendableSeverity>(SendableSeverity.class);


    @Test
    public void testConstructor() {
        new EnumParser(FireSize.class);
        new EnumParser(SendableStatus.class);
        new EnumParser(SendableSeverity.class);
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

        ObjectHolder<SendableStatus> emergencyStatusHolder = new ObjectHolder<SendableStatus>();
        textualRepresentation = "recorded but unhandled";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED,emergencyStatusHolder.getObject());
        textualRepresentation = "response in progress";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,emergencyStatusHolder.getObject());
        textualRepresentation = "completed";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.COMPLETED,emergencyStatusHolder.getObject());

		textualRepresentation = "UNHANDLED";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED,emergencyStatusHolder.getObject());
        textualRepresentation = "RESPONDED";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,emergencyStatusHolder.getObject());
		 textualRepresentation = "COMPLETED";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.COMPLETED,emergencyStatusHolder.getObject());

        textualRepresentation = "42recorded but unhandled1425";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED,emergencyStatusHolder.getObject());
        textualRepresentation = "1302response in progress1817";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,emergencyStatusHolder.getObject());
        textualRepresentation = "2010completed2015";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.COMPLETED,emergencyStatusHolder.getObject());

        ObjectHolder<SendableSeverity> emergencySeverityHolder = new ObjectHolder<SendableSeverity>();
        textualRepresentation = "benign";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.BENIGN,emergencySeverityHolder.getObject());
        textualRepresentation = "normal";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.NORMAL,emergencySeverityHolder.getObject());
        textualRepresentation = "serious";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.SERIOUS,emergencySeverityHolder.getObject());
        textualRepresentation = "urgent";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.URGENT,emergencySeverityHolder.getObject());

        textualRepresentation = "BENIGN";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.BENIGN,emergencySeverityHolder.getObject());
        textualRepresentation = "NORMAL";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.NORMAL,emergencySeverityHolder.getObject());
        textualRepresentation = "SERIOUS";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.SERIOUS,emergencySeverityHolder.getObject());
        textualRepresentation = "URGENT";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.URGENT,emergencySeverityHolder.getObject());

        textualRepresentation = "42benign1425";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.BENIGN,emergencySeverityHolder.getObject());
        textualRepresentation = "1302normal1817";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.NORMAL,emergencySeverityHolder.getObject());
        textualRepresentation = "2010serious2015";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.SERIOUS,emergencySeverityHolder.getObject());
        textualRepresentation = "1789urgent1879";
        emergencySeverityParser.parse(textualRepresentation, emergencySeverityHolder);
        assertEquals(SendableSeverity.URGENT,emergencySeverityHolder.getObject());
    }

	@Test
	public void testExternalSystemEnum() throws ParsingException{
		ObjectHolder<SendableStatus> emergencyStatusHolder = new ObjectHolder<SendableStatus>();
		String textualRepresentation = "unhandled";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.RECORDED_BUT_UNHANDLED,emergencyStatusHolder.getObject());
        textualRepresentation = "responded";
        emergencyStatusParser.parse(textualRepresentation, emergencyStatusHolder);
        assertEquals(SendableStatus.RESPONSE_IN_PROGRESS,emergencyStatusHolder.getObject());
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