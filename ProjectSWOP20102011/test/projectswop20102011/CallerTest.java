package projectswop20102011;



import projectswop20102011.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class CallerTest extends TestCase{

        private Caller caller;
        private String name;
        private String phoneNumber;

        @Override
        @Before
        public void setUp(){
            name = "Elio De Wever";
            phoneNumber = "010130210";
        }

        @Test
        public void testConstructor() throws InvalidNameException, InvalidPhoneNumberException{
            caller = new Caller(name,phoneNumber);
            assertEquals(name,caller.getName());
            assertEquals(phoneNumber,caller.getPhoneNumber());
        }

}
