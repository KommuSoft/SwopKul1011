package projectswoptest20102011;

import projectswop20102011.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class CallerTest extends TestCase{

        Caller caller;
        String name;
        String phoneNumber;

        @Override
        @Before
        public void setUp(){
            name = "Elio De Wever";
            phoneNumber = "010/130210";
        }

        @Test
        public void testConstructor(){
            caller = new Caller(name,phoneNumber);
            assertEquals(name,caller.getName());
            assertEquals(phoneNumber,caller.getPhoneNumber());

        }

}
