/*****************************************************************************************************************
NAME: Ronnie Mohapatra
PERIOD: 2
DUE DATE: December 10, 2018 
ASSIGNMENT: Text Editor Lab

PURPOSE: To edit text using the Stack data structure   

LEARNED: a). I learned a new way of saving data using Stack
  		 b). I got a good refresher on how to process Strings with substring(), indexOf(), and charAt()
            
CREDITS: 

****************************************************************************************************************/
package Stack;
import java.util.Stack;
import java.util.Scanner;

public class Pd2RonnieMohapatraTextEditorLab {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		String again;
		
		do {
			System.out.print("Enter a line of text: ");
			String input = s.nextLine();
			
			System.out.println("Here is the line you entered: " + translate(input));
			
			System.out.print("Run again y/n: ");
			again = s.nextLine();
		} while(again.equals("y"));
	}
	
	/*
	 * pre-condition: s is not empty
	 * post-condition: returns translated String
	 */
	public static String translate(String s)
	{
		Stack<String> stack = new Stack<String>();
		
		for(int i = 0; i < s.length(); i++) //read String into Stack
		{
			if(s.charAt(i) == '$' || (s.charAt(i) == '-' && stack.isEmpty())) //clear stack if '$' or stack is empty if '-'
				stack.clear();
			else if(s.charAt(i) == '-' && !stack.isEmpty()) //delete last character if '-' and stack is not empty
				stack.pop();
			else
				stack.push(s.charAt(i) + ""); //else push char into stack
			
			//System.out.println(stack);
		}
			
		String result = "";
			
		for(int i = 0; i < stack.size(); i++)
			result += stack.get(i); //build String
		
		return result;
	}
	
	
}

/* Output
 Enter a line of text: Ca-noe$Ra3-fx-t
Here is the line you entered: Raft
Run again y/n: y
Enter a line of text: AP$$-Compp-utee-r Sic--cei--ience
Here is the line you entered: Computer Science
Run again y/n: n
*/

