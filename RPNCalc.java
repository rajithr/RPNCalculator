import java.io.IOException;
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
				//System.out.println("E-NumberFormatException");
			}
			
			try
			{
				ParseTokens(stack, token, tokenNum);
			}
			catch(NoSuchElementException e)
			{
				//System.out.println("E-NoSuchElementException");
			}
			catch(EmptyStackException e)
			{
				//System.out.println("E-EmptyStackException");
			}
		}
		
		try
		{
			result = stack.pop();
			//System.out.println(result);
		}
		catch(NoSuchElementException e)
		{
			//System.out.println("E-NoSuchElementException on final pop");
		}
		catch(EmptyStackException e)
		{
			//System.out.println("E-EmptyStackException on final pop");
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
				firstOperand = stack.pop();
			
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
 
	private static Double factorial(double number) {
		Double d = new Double(1);
		
		if(number <= 1)
		{
			return d;
		}
		
		for(int n=1; n<=number; n++)
		{
			d *= n;
		}
		
		return d;
	}

	private static String sanitize(String expr){
		return expr.replaceAll("[^\\^\\*\\+\\-\\d/\\s\\%\\!]", "");
	}
 
	public static void main(String[] args){
		int a = 0;
		StringBuilder s = new StringBuilder();
		try
		{
			while((char)a != '\n')
			{
				a = System.in.read();
				s.append((char)a);
			}
		}
		catch(IOException e)
		{
			//System.out.println("E-IOException");
		}
		double result = ParseLine(s.toString());
		System.out.println(result);
	}
}

