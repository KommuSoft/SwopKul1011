package projectswop20102011;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class MainControllerTest {

    public MainControllerTest() {
    }

    @BeforeClass
    public static void setUpClass(){
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadInputUnformatted1 () throws InvalidExpressionFormatException {
        MainController.readActor("O*erator hello world!");
    }
    /**
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadInputUnformatted2 () throws InvalidExpressionFormatException, InvalidActorException, InvalidNameException, InvalidCommandException {
        MainController.readInput("Operator");
    }
    /**
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidActorException.class)
    public void testReadInputFormatted () throws InvalidExpressionFormatException, InvalidActorException, InvalidNameException, InvalidCommandException {
        MainController.readInput("InvalidActor This message has no valid actor!");
    }

    /**
     * Test of readActor method, of class MainController.
     */
    @Test
    public void testReadActorFormatted() throws InvalidExpressionFormatException {
        assertEquals("operator", MainController.readActor("Operator hello world!"));
        assertEquals("operator", MainController.readActor("OpErAtOr to be or not to be"));
        assertEquals("dispatcher", MainController.readActor("DISPATCHER do you like java?"));
    }

    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadActorUnFormatted1() throws InvalidExpressionFormatException {
        MainController.readActor("O*erator hello world!");
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadActorUnFormatted2() throws InvalidExpressionFormatException {
        MainController.readActor("Operator");
    }

    /**
     * Test of readMessage method, of class MainController.
     */
    @Test
    public void testReadMessageFormatted() throws InvalidExpressionFormatException {
        assertEquals("hello world!", MainController.readMessage("Operator hello world!"));
        assertEquals("To Be Or Not To Be? That's the question.", MainController.readMessage("OpErAtOr To Be Or Not To Be? That's the question."));
        assertEquals("do you like java?", MainController.readMessage("DISPATCHER do you like java?"));
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadMessageUnFormatted1() throws InvalidExpressionFormatException {
        MainController.readMessage("O*erator hello world!");
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadMessageUnFormatted2() throws InvalidExpressionFormatException {
        MainController.readMessage("Operator");
    }

}