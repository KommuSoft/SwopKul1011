package projectswop20102011;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OperatorListTest{
	private OperatorList operatorList1,operatorList2;
	private Operator operator1, operator2, operator3;

	@Before
	public void setUp() throws InvalidNameException{
		operator1 = new Operator("Jan De Helper");
		operator2 = new Operator("Piet De Helpdesk");
		operator3 = new Operator("Sonja Allo");
	}

	@Test
	public void testConstructor(){
		operatorList1 = new OperatorList();
		assertNotNull(operatorList1);
	}

	@Test
	public void testAddOperator(){
		operatorList1 = new OperatorList();
		operatorList1.addOperator(operator1);
		assertEquals(operator1, operatorList1.getOperators().get(0));

		operatorList2 = new OperatorList();
		operatorList2.addOperator(operator1);
		operatorList2.addOperator(operator2);
		operatorList2.addOperator(operator1);
		assertEquals(operator1, operatorList2.getOperators().get(0));
		assertEquals(operator2, operatorList2.getOperators().get(1));
		assertEquals(2, operatorList2.getOperators().size());
	}
}