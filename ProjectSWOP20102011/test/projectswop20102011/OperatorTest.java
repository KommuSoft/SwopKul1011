package projectswop20102011;

import org.junit.*;
import static org.junit.Assert.*;


/**
 *
 * @author Willem Van Onsem, Jonas Vanthornhout and Pieter-Jan Vuylsteke
 */
public class OperatorTest{

        private Operator operator1,operator2;
        private String name1,name2;

        @Before
        public void setUp(){
            name1 = "Jan de Helper";
			name2 = "";
        }

        @Test
        public void testConstructor() throws InvalidNameException{
            operator1 = new Operator(name1);
            assertEquals(name1,operator1.getName());
        }

		@Test(expected = InvalidNameException.class)
		public void testInvalidNameConstructor() throws InvalidNameException, InvalidPhoneNumberException{
				operator2 = new Operator(name2);
		}
}