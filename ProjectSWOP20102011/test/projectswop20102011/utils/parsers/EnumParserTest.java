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

        textualRepresentation = "recorded but unhandled";
        emergencyStatusParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED,fireSizeHolder.getObject());
        textualRepresentation = "response in progress";
        emergencyStatusParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS,fireSizeHolder.getObject());
        textualRepresentation = "completed";
        emergencyStatusParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(EmergencyStatus.COMPLETED,fireSizeHolder.getObject());

        textualRepresentation = "42recorded but unhandled1425";
        emergencyStatusParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(EmergencyStatus.RECORDED_BUT_UNHANDLED,fireSizeHolder.getObject());
        textualRepresentation = "1302response in progress1817";
        emergencyStatusParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(EmergencyStatus.RESPONSE_IN_PROGRESS,fireSizeHolder.getObject());
        textualRepresentation = "2010completed2015";
        emergencyStatusParser.parse(textualRepresentation, fireSizeHolder);
        assertEquals(EmergencyStatus.COMPLETED,fireSizeHolder.getObject());
    }

    /**
     * Test of canParse method, of class EnumParser.
     */
    @Test
    public void testCanParse() throws Exception {
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnumerationDictionary method, of class EnumParser.
     */
    @Test
    public void testGetEnumerationDictionary() {
        System.out.println("getEnumerationDictionary");
        EnumParser instance = null;
        Map expResult = null;
        Map result = instance.getEnumerationDictionary();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSearchPattern method, of class EnumParser.
     */
    @Test
    public void testGetSearchPattern() {
        System.out.println("getSearchPattern");
        EnumParser instance = null;
        Pattern expResult = null;
        Pattern result = instance.getSearchPattern();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    

}