package projectswop20102011.domain.validators;

import java.io.InvalidClassException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import projectswop20102011.domain.Ambulance;
import projectswop20102011.domain.Firetruck;
import projectswop20102011.domain.GPSCoordinate;
import projectswop20102011.domain.Unit;
import projectswop20102011.exceptions.InvalidCapacityException;
import projectswop20102011.exceptions.InvalidLocationException;
import projectswop20102011.exceptions.InvalidMapItemNameException;
import projectswop20102011.exceptions.InvalidSpeedException;
import projectswop20102011.exceptions.InvalidValidatorException;
import projectswop20102011.exceptions.NumberOutOfBoundsException;
import projectswop20102011.utils.SortedListSet;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout & Pieter-Jan Vuylsteke
 */
public class MinMaxNumberDispatchUnitsConstraintTest {

    public MinMaxNumberDispatchUnitsConstraintTest() {
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
     * Test of getValidator method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testGetValidator() throws NumberOutOfBoundsException, InvalidValidatorException, InvalidClassException {
        UnitValidator uv = new BasicUnitValidator();
        MinMaxNumberDispatchUnitsConstraint mmnduct = new MinMaxNumberDispatchUnitsConstraint(uv,1302,1425);
        assertEquals(uv,mmnduct.getValidator());
        uv = new TypeUnitValidator(Firetruck.class);
        mmnduct = new MinMaxNumberDispatchUnitsConstraint(uv,1302,1425);
        assertEquals(uv,mmnduct.getValidator());
    }

    /**
     * Test of getQuadraticValidator method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testGetQuadraticValidator() throws NumberOutOfBoundsException, InvalidValidatorException {
        UnitValidator uv = new BasicUnitValidator();
        MinMaxNumberDispatchUnitsConstraint instance = new MinMaxNumberDispatchUnitsConstraint(uv,1302,1425);
        assertNotNull(instance.getQuadraticValidator());
        Class expResultC = DefaultQuadraticValidator.class;
        Class resultC = instance.getQuadraticValidator().getClass();
        assertEquals(expResultC, resultC);
        uv = new BasicUnitValidator();
        QuadraticValidator<Unit,Unit> quv = new DefaultQuadraticValidator<Unit,Unit>();
        instance = new MinMaxNumberDispatchUnitsConstraint(uv,1302,1425,quv);
        assertNotNull(instance.getQuadraticValidator());
        QuadraticValidator<Unit,Unit> expResult = quv;
        QuadraticValidator<Unit,Unit> result = instance.getQuadraticValidator();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMinimum method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testGetMinimum() throws NumberOutOfBoundsException, InvalidValidatorException {
        UnitValidator uv = new BasicUnitValidator();
        MinMaxNumberDispatchUnitsConstraint instance = new MinMaxNumberDispatchUnitsConstraint(uv,42,1425);
        assertEquals(42,instance.getMinimum());
        instance = new MinMaxNumberDispatchUnitsConstraint(uv,1302,1425);
        assertEquals(1302,instance.getMinimum());
        instance = new MinMaxNumberDispatchUnitsConstraint(uv,1425,1425);
        assertEquals(1425,instance.getMinimum());
    }

    /**
     * Test of getMaximum method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testGetMaximum() throws NumberOutOfBoundsException, InvalidValidatorException {
        UnitValidator uv = new BasicUnitValidator();
        MinMaxNumberDispatchUnitsConstraint instance = new MinMaxNumberDispatchUnitsConstraint(uv,42,42);
        assertEquals(42,instance.getMaximum());
        instance = new MinMaxNumberDispatchUnitsConstraint(uv,42,1302);
        assertEquals(1302,instance.getMaximum());
        instance = new MinMaxNumberDispatchUnitsConstraint(uv,42,1425);
        assertEquals(1425,instance.getMaximum());
    }

    /**
     * Test of isValidValidator method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testIsValidValidator() throws InvalidClassException {
        ValidatorNumberator<? super Unit> validatornumberator = null;
        boolean expResult = false;
        boolean result = MinMaxNumberDispatchUnitsConstraint.isValidValidator(validatornumberator);
        assertEquals(expResult, result);
        validatornumberator = new BasicUnitValidator();
        expResult = true;
        result = MinMaxNumberDispatchUnitsConstraint.isValidValidator(validatornumberator);
        assertEquals(expResult, result);
        validatornumberator = new TypeUnitValidator(Firetruck.class);
        expResult = true;
        result = MinMaxNumberDispatchUnitsConstraint.isValidValidator(validatornumberator);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidNumber method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testIsValidNumber() {
        long number = 0L;
        boolean expResult = true;
        boolean result = MinMaxNumberDispatchUnitsConstraint.isValidNumber(number);
        assertEquals(expResult, result);
        number = -1L;
        expResult = false;
        result = MinMaxNumberDispatchUnitsConstraint.isValidNumber(number);
        assertEquals(expResult, result);
        number = 42L;
        expResult = true;
        result = MinMaxNumberDispatchUnitsConstraint.isValidNumber(number);
        assertEquals(expResult, result);
        number = 1302L;
        expResult = true;
        result = MinMaxNumberDispatchUnitsConstraint.isValidNumber(number);
        assertEquals(expResult, result);
        number = -1302L;
        expResult = false;
        result = MinMaxNumberDispatchUnitsConstraint.isValidNumber(number);
        assertEquals(expResult, result);
        number = 1425L;
        expResult = true;
        result = MinMaxNumberDispatchUnitsConstraint.isValidNumber(number);
        assertEquals(expResult, result);
    }

    /**
     * Test of areValidMinimumMaximum method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testAreValidMinimumMaximum() {
        long minimum = 0L;
        long maximum = 0L;
        boolean expResult = true;
        boolean result = MinMaxNumberDispatchUnitsConstraint.areValidMinimumMaximum(minimum, maximum);
        assertEquals(expResult, result);
        minimum = 0L;
        maximum = 42L;
        expResult = true;
        result = MinMaxNumberDispatchUnitsConstraint.areValidMinimumMaximum(minimum, maximum);
        assertEquals(expResult, result);
        minimum = -42L;
        maximum = 42L;
        expResult = false;
        result = MinMaxNumberDispatchUnitsConstraint.areValidMinimumMaximum(minimum, maximum);
        assertEquals(expResult, result);
        minimum = 1703L;
        maximum = 42L;
        expResult = false;
        result = MinMaxNumberDispatchUnitsConstraint.areValidMinimumMaximum(minimum, maximum);
        assertEquals(expResult, result);
    }

    /**
     * Test of generateProposal method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testGenerateProposal() throws NumberOutOfBoundsException, InvalidValidatorException, InvalidLocationException, InvalidMapItemNameException, InvalidSpeedException, InvalidCapacityException, InvalidClassException {
        List<Unit> finishedOrAssignedUnits = new ArrayList<Unit>();
        SortedSet<Unit> availableUnits = new SortedListSet<Unit>();
        Ambulance a01 = new Ambulance("a01",new GPSCoordinate(0,0),50);
        Ambulance a02 = new Ambulance("a02",new GPSCoordinate(0,0),50);
        Ambulance a03 = new Ambulance("a03",new GPSCoordinate(0,0),50);
        Ambulance a04 = new Ambulance("a04",new GPSCoordinate(0,0),50);
        Firetruck f01 = new Firetruck("f01",new GPSCoordinate(0,0),50,50);
        Firetruck f02 = new Firetruck("f02",new GPSCoordinate(0,0),50,50);
        Firetruck f03 = new Firetruck("f03",new GPSCoordinate(0,0),50,50);
        availableUnits.add(a01);
        availableUnits.add(f01);
        availableUnits.add(a02);
        availableUnits.add(f02);
        Set<Unit> proposal = new HashSet<Unit>();
        MinMaxNumberDispatchUnitsConstraint instance = new MinMaxNumberDispatchUnitsConstraint(new BasicUnitValidator(),5,10);
        boolean expResult = true;
        boolean result = instance.generateProposal(finishedOrAssignedUnits, availableUnits, proposal);
        assertEquals(expResult, result);
        assertEquals(4,proposal.size());
        assertTrue(proposal.contains(a01));
        assertTrue(proposal.contains(f01));
        assertTrue(proposal.contains(a02));
        assertTrue(proposal.contains(f02));

        availableUnits.add(a03);
        availableUnits.add(a04);

        proposal = new HashSet<Unit>();
        expResult = true;
        result = instance.generateProposal(finishedOrAssignedUnits, availableUnits, proposal);
        assertEquals(expResult, result);
        assertEquals(5,proposal.size());
        assertTrue(proposal.contains(a01));
        assertTrue(proposal.contains(f01));
        assertTrue(proposal.contains(a02));
        assertTrue(proposal.contains(f02));
        assertTrue(proposal.contains(a03));

        instance = new MinMaxNumberDispatchUnitsConstraint(new TypeUnitValidator(Firetruck.class),14,25);
        proposal = new HashSet<Unit>();
        result = instance.generateProposal(finishedOrAssignedUnits, availableUnits, proposal);
        assertEquals(expResult, result);
        assertEquals(2,proposal.size());
        assertTrue(proposal.contains(f01));
        assertTrue(proposal.contains(f02));
    }

    /**
     * Test of canAssign method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testCanAssign() throws InvalidClassException, NumberOutOfBoundsException, InvalidValidatorException {
        List<Unit> finishedOrAssignedUnits = new ArrayList<Unit>();
        Set<Unit> toAssignUnits = new HashSet<Unit>();
        Set<Unit> relevantUnits = new HashSet<Unit>();
        MinMaxNumberDispatchUnitsConstraint instance = new MinMaxNumberDispatchUnitsConstraint(new TypeUnitValidator(Firetruck.class),14,25);
        boolean expResult = true;
        boolean result = instance.canAssign(finishedOrAssignedUnits, toAssignUnits, relevantUnits);
        assertEquals(expResult, result);
        assertEquals(0,relevantUnits.size());
        //TODO: complete test [zie pc op kot Willem]
    }

    /**
     * Test of canFinish method, of class MinMaxNumberDispatchUnitsConstraint.
     */
    @Test
    public void testCanFinish() {
        /*System.out.println("canFinish");
        List<Unit> finishedUnits = null;
        MinMaxNumberDispatchUnitsConstraint instance = null;
        boolean expResult = false;
        boolean result = instance.canFinish(finishedUnits);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail. [zie pc op kot Willem]
        fail("The test case is a prototype.");*/
    }

    private class BasicUnitValidator extends UnitValidator<Unit> {

        @Override
        public boolean isValid(Unit object) {
            return true;
        }

    }

}