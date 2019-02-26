/*****************************************************************************************************************
NAME: Ronnie Mohapatra
PERIOD: 2
DUE DATE: December 12, 2018 
ASSIGNMENT: Parentheses Matching Lab

PURPOSE: To check whether the parentheses in an expression match

LEARNED: a). I realized that you cannot pop or peek when the Stack is empty, you will get an EmptyStackException
  		 b). Easiest way to see if a String has a character is using the boolean expression str.indexOf(ch) != -1
            
CREDITS: 

****************************************************************************************************************/
package Stack;
import java.util.*;

public class Pd2RonnieMohapatraParenthesesMatchLab {
	public static final String LEFT = "([{<", RIGHT = ")]}>"; 
	
	public static void main(String[] args) {
		String[] testSubjects = {"5+7", "(5+7)", ")5+7(", "((5+7)*3)", "[(5+7)*]3", "<{5+7}*3>", "(5+7)*3", "5+(7*3)", 
								 "((5+7)*3", "[(5+7]*3)", "[(5+7)*3])", "([(5+7)*3]"};
		
		String[] results = new String[testSubjects.length];
		
		for(int i = 0; i < results.length; i++)
			results[i] = check(testSubjects[i]) ? "true" : "false";
		
		for(int i = 0; i < testSubjects.length; i++)
			System.out.println(testSubjects[i] + " " + results[i]);
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
5+7 true
(5+7) true
)5+7( false
((5+7)*3) true
[(5+7)*]3 true
<{5+7}*3> true
(5+7)*3 true
5+(7*3) true
((5+7)*3 false
[(5+7]*3) false
[(5+7)*3]) false
([(5+7)*3] false
*/

