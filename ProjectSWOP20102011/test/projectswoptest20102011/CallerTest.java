package projectswoptest20102011;

import projectswop20102011.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class CallerTest{

        private Caller caller1,caller2,caller3;
        private String name1,name2,name3;
        private String phoneNumber1,phoneNumber2,phoneNumber3;

        @Before
        public void setUp(){
            name1 = "Elio De Wever";
            phoneNumber1 = "010130210";
			name2 = "*/*";
			phoneNumber2 = "063125678";
			name3 = "Ivan Knuth";
			phoneNumber3 = "0abc0";
        }

        @Test
        public void testConstructor() throws InvalidNameException, InvalidPhoneNumberException{
            caller1 = new Caller(name1,phoneNumber1);
            assertEquals(name1,caller1.getName());
            assertEquals(phoneNumber1,caller1.getPhoneNumber());
        }

		@Test(expected = InvalidNameException.class)
		public void testInvalidNameConstructor() throws InvalidNameException, InvalidPhoneNumberException{
			caller2 = new Caller(name2,phoneNumber2);
		}

		@Test(expected = InvalidPhoneNumberException.class)
		public void testInvalidPhoneNumberConstructor() throws InvalidNameException, InvalidPhoneNumberException{
			caller3 = new Caller(name3,phoneNumber3);
		}

}
