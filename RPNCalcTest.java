import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RPNCalcTest extends TestCase {
	
	private TestData [] testData;
	private int numTests = 0;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		
		testData = new TestData[100];
		
		//The following are basic tests ..
		testData[numTests++] = new TestData("1 2 +",3);
		testData[numTests++] = new TestData("1 2 -",-1);
		
		testData[numTests++] = new TestData("1 2 *",2);
		testData[numTests++] = new TestData("1 2 /",0.5);
		
		testData[numTests++] = new TestData("100 %",1);
		testData[numTests++] = new TestData("2 !",2);
		
		//Following are UAT ..
		testData[numTests++] = new TestData("1 2 3 + -",-4);
		testData[numTests++] = new TestData("6 2 * 3 /",4);
		
		testData[numTests++] = new TestData("2 3 ^ 4 5 + +",17);
		testData[numTests++] = new TestData("50 % 2 *",1);
		
		testData[numTests++] = new TestData("3 ! 4 5 * +",26);
		testData[numTests++] = new TestData("12 3 / !",24);
		
		testData[numTests++] = new TestData("5 1 2 + 4 * + 3 -",14);
		
		//Error cases .. 
		testData[numTests++] = new TestData("% % + + *",0);
		testData[numTests++] = new TestData("2 4 + +",0);
		
		testData[numTests++] = new TestData("abc%%",0);
		testData[numTests++] = new TestData("2 ^ * 3",0);
		
		testData[numTests++] = new TestData("! 1 2 +",0);
		testData[numTests++] = new TestData("1 2",2);
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		for(int n = 0; n < numTests; n++)
		{
			if(RPNCalc.ParseLine(testData[n].getInput()) != testData[n].getResult())
			{
				fail("Wrong Result "
					+testData[n].getInput()
					+" is not "
					+testData[n].getResult());
			}
		}
	}

}
