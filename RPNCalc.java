package someProject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RPNCalc{
	public static void evalRPN(String expr){
		String cleanExpr = sanitize(expr);
		LinkedList<Double> stack = new LinkedList<Double>();
		System.out.println("Input\tOperation\tStack after");
		for(String token:cleanExpr.split("\\s")){
			System.out.print(token+"\t");
			Double tokenNum = null;
			try{
				tokenNum = Double.parseDouble(token);				
			}
			catch(NumberFormatException e)
			{
				//System.out.println("Error");
			}
			
			ParseLineWithSwitch(stack, token, tokenNum);
			
			System.out.println(stack);
		}
		
		try
		{
			System.out.println("Final answer: " + stack.pop());
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Error");
		}
	}

	@SuppressWarnings("unused")
	private static void ParseLine(LinkedList<Double> stack, String token,
			Double tokenNum) {
		if(tokenNum != null){
			System.out.print("Push\t\t");
			stack.push(Double.parseDouble(token+""));
		}else if(token.equals("*")){
			System.out.print("Operate\t\t");
			double secondOperand = stack.pop();
			double firstOperand = stack.pop();
			stack.push(firstOperand * secondOperand);
		}else if(token.equals("/")){
			System.out.print("Operate\t\t");
			double secondOperand = stack.pop();
			double firstOperand = stack.pop();
			stack.push(firstOperand / secondOperand);
		}else if(token.equals("-")){
			System.out.print("Operate\t\t");
			double secondOperand = stack.pop();
			double firstOperand = stack.pop();
			stack.push(firstOperand - secondOperand);
		}else if(token.equals("+")){
			System.out.print("Operate\t\t");
			double secondOperand = stack.pop();
			double firstOperand = stack.pop();
			stack.push(firstOperand + secondOperand);
		}else if(token.equals("^")){
			System.out.print("Operate\t\t");
			double secondOperand = stack.pop();
			double firstOperand = stack.pop();
			stack.push(Math.pow(firstOperand, secondOperand));
		}
	}
	
	private static void ParseLineWithSwitch(LinkedList<Double> stack, String token,
			Double tokenNum) {
		if(tokenNum != null){
			stack.push(Double.parseDouble(token+""));
		}else
		{
			double secondOperand = stack.pop();
			double firstOperand = stack.pop();
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
			}
		}
	}
 
	private static String sanitize(String expr){
		return expr.replaceAll("[^\\^\\*\\+\\-\\d/\\s]", "");
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
			//Print Error
		}
		evalRPN(s.toString());
	}
}
