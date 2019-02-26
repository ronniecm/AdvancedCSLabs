/*****************************************************************************************************************
NAME: Ronnie Mohapatra
PERIOD: 2
DUE DATE: December 15, 2018 
ASSIGNMENT: Postfix Lab

PURPOSE: To convert from infix to postfix and evaluate the postfix expression

LEARNED: a). I learned the algorithm for converting from infix to postfix 
  		 b). I kept making the mistake that to actually traverse a stack, you must pop the elements, not peek at them
  		 c). The form of a postfix expression operand1operand2operator which makes it so you have to save the two
  		     operands first and when you reach the operator you evaluate
CREDITS: 

****************************************************************************************************************/
package Stack;
import java.util.*;

public class Pd2RonnieMohapatraPostfixLab 
{
	public static final String OPERATORS = "+-*/"; //used when seeing whether a character is a mathematical operator
	public static final String LEFT = "([{<", RIGHT = ")]}>"; 
	
	public static void main(String[] args)
	{
		String[] testExpressions = {"3+4*5", "3*4+5", "(4+5)-5*3", "(3+4)*(5+6)", "(3*(4+5)-2)/5", "8+1*2-9/3"};
		
		for(String expression : testExpressions)
		{
			System.out.println("Infix expression: " + expression);
			System.out.println("Do the parentheses match? " + check(expression));
			if(check(expression))
			{
				String postfix = convert(expression);
				System.out.println("Postfix expression: " + postfix);
				System.out.println("Answer: " + eval(postfix));
			} else {
				System.out.println("Parentheses do not match. Calculation terminated.");
			}
			System.out.println();
		}
	}
	
	/*
	 * pre-condition: str is a non-empty, continuous expression (no spaces), and is in infix format
	 * post-condition: postfix version of expression is returned
	 */
	public static String convert(String str) 
	{
		String postfix = ""; //initialize postfix string
		Stack<Character> stack = new Stack<Character>(); //initialize stack for operators and parentheses
		for(int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i) == '(')
				stack.push(str.charAt(i)); //push to stack if char is left parenthesis
			else if(str.charAt(i) == ')')
			{
				while(stack.peek() != '(') //keep popping and appending to postfix expression until left parenthesis is popped if char is right parenthesis
				{
					postfix += stack.pop();
				}
				
				stack.pop(); //pop the left parenthesis
			} else if(OPERATORS.indexOf(str.charAt(i)) != -1) {
				//keep popping and appending until stack is empty or character on top is a left parenthesis or operator on top is of lower precedence of current operator
				while(!stack.isEmpty() && stack.peek() != '(' && !isLower(stack.peek(), str.charAt(i)))
				{
					postfix += stack.pop(); 
				}
				stack.push(str.charAt(i)); //push to stack if one condition is met
			} else  {
				postfix += str.charAt(i); //append to postfix expression if char is operand
			}
		}
		
		while(!stack.isEmpty()) //keep popping and appending until stack is empty
		{
			postfix += stack.pop();
		}
		
		return postfix;
	}
	
	/*
	 * pre-condition: op1 and op2 equal a character
	 * post-condition: return true if op1 has a lower precedence than op2, otherwise returns false
	 */
	public static boolean isLower(char op1, char op2)
	{
		//returns true if op1 is + or - and op2 is * or /
		return (op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/') ?  true :  false;
	}
	
	/*
	 * pre-condition: str is not empty and is a postfix mathematical expression
	 * post-condition: returns integer answer of the postfix mathematical expression
	 */
	public static int eval(String str)
	{
		Stack<Integer> stack = new Stack<Integer>(); //initialize Stack
		for(int i = 0; i < str.length(); i++)
		{
			if(isOperator(str.charAt(i)))
			{
				int a = stack.pop(), b = stack.pop(); //pop top two operands
				
				if(str.charAt(i) == '-' || str.charAt(i) == '/' || str.charAt(i) == '%' || str.charAt(i) == '^') //evaluate the bottom one between a and b if operator is division or subtraction
					stack.push(eval(b, a, str.charAt(i)));
				else
					stack.push(eval(a, b, str.charAt(i))); //result to top of stack
			} else {
				stack.push(Integer.parseInt(str.charAt(i) + ""));
			}
		}
		
		return stack.pop(); //final answer will be at the top of the stack
	}
	
	/*
	 * pre-condition:  b > 0 if ch != '/', ch is a mathematical operator
	 * post-condition: returns a operator b
	 */
	public static int eval(int a, int b, char ch)
	{
		//checks what ch is and does the respective calculation
		if(ch == '+')
			return a+b;
		else if(ch == '-')
			return a-b;
		else if(ch == '*')
			return a*b;
		else if(ch == '/')
			return a /b;
		else
			return 0;
	}
	
	/*
	 * pre-condition: none
	 * post-condition: returns true if ch is a mathematical operator
	 */
	public static boolean isOperator(char ch)
	{
		return OPERATORS.indexOf(ch) != -1;
	}
	
	/*
	 * pre-condition: s is a non-empty string
	 * post-condition: return true if grouping symbols match with each other, false otherwise
	 */
	public static boolean check(String s)
	{
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == LEFT.charAt(0) || s.charAt(i) == LEFT.charAt(1) || s.charAt(i) == LEFT.charAt(2) || s.charAt(i) == LEFT.charAt(3))
				stack.push(s.charAt(i)); //check if character is a left grouping symbol. push to stack if yes	
			else if(RIGHT.indexOf(s.charAt(i)) != -1) //check if character is a right grouping symbol
			{
				if(stack.isEmpty()) //happens when there is no left grouping symbol available to compare with the right grouping symbol
					return false;
				
				char left = stack.peek();
				char right = s.charAt(i);
				
				if(LEFT.indexOf(left) != RIGHT.indexOf(right)) //if the index of the left grouping symbol in LEFT and the index of the right grouping symbol in RIGHT do not match return false
					return false;
				
				stack.pop(); //remove left grouping symbol from stack if symbols match
			} 
		}
		
		return stack.isEmpty(); //if there is a lingering left grouping symbol return false, otherwise return true
	}
}


/* Output
Infix expression: 3+4*5
Postfix expression: 345*+
Answer: 23

Infix expression: 3*4+5
Postfix expression: 34*5+
Answer: 17

Infix expression: (4+5)-5*3
Postfix expression: 45+53*-
Answer: -6

Infix expression: (3+4)*(5+6)
Postfix expression: 34+56+*
Answer: 77

Infix expression: (3*(4+5)-2)/5
Postfix expression: 345+*2-5/
Answer: 5

Infix expression: 8+1*2-9/3
Postfix expression: 812*+93/-
Answer: 7
*/
