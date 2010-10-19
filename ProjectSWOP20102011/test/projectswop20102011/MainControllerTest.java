package projectswop20102011;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import projectswop20102011.InvalidActorException;
import projectswop20102011.InvalidExpressionFormatException;
import projectswop20102011.MainController;
import static org.junit.Assert.*;

/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class MainControllerTest {

    public MainControllerTest() {
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
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadInputUnformatted1 () throws Exception {
        MainController.readActor("O*erator hello world!");
    }
    /**
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadInputUnformatted2 () throws Exception {
        MainController.readInput("Operator");
    }
    /**
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidActorException.class)
    public void testReadInputFormatted () throws Exception {
        MainController.readInput("InvalidActor This message has no valid actor!");
    }

    /**
     * Test of readActor method, of class MainController.
     */
    @Test
    public void testReadActorFormatted() throws Exception {
        assertEquals(MainController.readActor("Operator hello world!"),"operator");
        assertEquals(MainController.readActor("OpErAtOr to be or not to be"),"operator");
        assertEquals(MainController.readActor("DISPATCHER do you like java?"),"dispatcher");
    }

    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadActorUnFormatted1() throws Exception {
        MainController.readActor("O*erator hello world!");
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadActorUnFormatted2() throws Exception {
        MainController.readActor("Operator");
    }

    /**
     * Test of readMessage method, of class MainController.
     */
    @Test
    public void testReadMessageFormatted() throws Exception {
        assertEquals(MainController.readMessage("Operator hello world!"),"hello world!");
        assertEquals(MainController.readMessage("OpErAtOr To Be Or Not To Be? That's the question."),"To Be Or Not To Be? That's the question.");
        assertEquals(MainController.readMessage("DISPATCHER do you like java?"),"do you like java?");
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadMessageUnFormatted1() throws Exception {
        MainController.readMessage("O*erator hello world!");
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadMessageUnFormatted2() throws Exception {
        MainController.readMessage("Operator");
    }

}