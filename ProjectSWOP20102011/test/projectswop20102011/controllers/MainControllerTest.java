package projectswop20102011.controllers;

import projectswop20102011.World;
import projectswop20102011.exceptions.InvalidExpressionFormatException;
import projectswop20102011.exceptions.InvalidCommandException;
import projectswop20102011.exceptions.InvalidNameException;
import projectswop20102011.exceptions.InvalidPhoneNumberException;
import projectswop20102011.exceptions.InvalidActorException;
import projectswop20102011.exceptions.InvalidTimestampException;
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

    private MainController mainController;

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
        this.mainController = new MainController(new World());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadInputUnformatted1 () throws InvalidExpressionFormatException {
        this.mainController.readActor("O*erator hello world!");
    }
    /**
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadInputUnformatted2 () throws InvalidExpressionFormatException, InvalidActorException, InvalidCommandException, Exception {
        this.mainController.readInput("Operator");
    }
    /**
     * Test of readInput method, of class MainController.
     */
    @Test(expected=InvalidActorException.class)
    public void testReadInputFormatted () throws InvalidExpressionFormatException, InvalidActorException, InvalidCommandException, Exception {
        this.mainController.readInput("InvalidActor This message has no valid actor!");
    }

    /**
     * Test of readActor method, of class MainController.
     */
    @Test
    public void testReadActorFormatted() throws InvalidExpressionFormatException {
        assertEquals("operator", this.mainController.readActor("Operator hello world!"));
        assertEquals("operator", this.mainController.readActor("OpErAtOr to be or not to be"));
        assertEquals("dispatcher", this.mainController.readActor("DISPATCHER do you like java?"));
    }

    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadActorUnFormatted1() throws InvalidExpressionFormatException {
        this.mainController.readActor("O*erator hello world!");
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadActorUnFormatted2() throws InvalidExpressionFormatException {
        this.mainController.readActor("Operator");
    }

    /**
     * Test of readMessage method, of class MainController.
     */
    @Test
    public void testReadMessageFormatted() throws InvalidExpressionFormatException {
        assertEquals("hello world!", this.mainController.readMessage("Operator hello world!"));
        assertEquals("To Be Or Not To Be? That's the question.", this.mainController.readMessage("OpErAtOr To Be Or Not To Be? That's the question."));
        assertEquals("do you like java?", this.mainController.readMessage("DISPATCHER do you like java?"));
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadMessageUnFormatted1() throws InvalidExpressionFormatException {
        this.mainController.readMessage("O*erator hello world!");
    }
    @Test(expected=InvalidExpressionFormatException.class)
    public void testReadMessageUnFormatted2() throws InvalidExpressionFormatException {
        this.mainController.readMessage("Operator");
    }

}