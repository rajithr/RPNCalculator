import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Stack;

public class RPNCalc{
	
	public static double ParseLine(String expr){
		String cleanExpr = sanitize(expr);
		Stack<Double> stack = new Stack<Double>();
		double result = 0;
		
		for(String token:cleanExpr.split("\\s")){
			Double tokenNum = null;
			try{
				tokenNum = Double.parseDouble(token);				
			}
			catch(NumberFormatException e)
			{
				//This is OK when parsing operator tokens
			}
			
			try
			{
				ParseTokens(stack, token, tokenNum);
			}
			catch(NoSuchElementException e)
			{
				return result;
			}
			catch(EmptyStackException e)
			{
				return result;
			}
		}
		
		try
		{
			result = stack.pop();
		}
		catch(NoSuchElementException e)
		{
			//Do Nothing
		}
		catch(EmptyStackException e)
		{
			//Do Nothing
		}
		
		return result;
	}

	private static void ParseTokens(Stack<Double> stack, String token, Double tokenNum) {
		if(tokenNum != null){
			stack.push(Double.parseDouble(token+""));
		}else
		{
			double secondOperand = stack.pop();
			double firstOperand = 0;
			if((token.charAt(0) != '%')
					&& (token.charAt(0) != '!'))
			{
				firstOperand = stack.pop();
			}
			
			switch(token.charAt(0))
			{
			case '+':
				stack.push(firstOperand + secondOperand);
				break;
			case '-':
				stack.push(firstOperand - secondOperand);
				break;
			case '*':
				stack.push(firstOperand * secondOperand);
				break;
			case '/':
				stack.push(firstOperand / secondOperand);
				break;
			case '^':
				stack.push(Math.pow(firstOperand,secondOperand));
				break;
			case '%':
				stack.push(secondOperand/100);
				break;
			case '!':
				stack.push(factorial(secondOperand));
				break;
			}
		}
	}
 
	private static double factorial(double number) {
		double factorial = 1;
		
		if(number <= 1)
		{
			return factorial;
		}
		
		for(int n=1; n<=number; n++)
		{
			factorial *= n;
		}
		
		return factorial;
	}

	private static String sanitize(String expr){
		return expr.replaceAll("[^\\^\\*\\+\\-\\d/\\s\\%\\!]", "");
	}
 
}
