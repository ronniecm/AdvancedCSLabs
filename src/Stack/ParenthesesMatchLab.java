package Stack;
import java.util.Stack;

public class ParenthesesMatchLab {
	public static final String LEFT = "([{<"; public static final String RIGHT = ")]}>";
	
	public static void main(String[] args) {
		check("[(5+7)*3])");
	}
	
	public static boolean check(String s)
	{
		Stack<String> stack = new Stack<String>();
		for(int i = 0; i < s.length(); i++)
		{
			//check if character is a left grouping symbol. push to stack if yes
			if(s.charAt(i) == LEFT.charAt(0) || s.charAt(i) == LEFT.charAt(1) || s.charAt(i) == LEFT.charAt(2) || s.charAt(i) == LEFT.charAt(3))
				stack.push(s.substring(i, i+1));	
			//check if character is a right grouping symbol
			else if(s.charAt(i) == RIGHT.charAt(0) || s.charAt(i) == RIGHT.charAt(1) || s.charAt(i) == RIGHT.charAt(1) || s.charAt(i) == RIGHT.charAt(3))
			{
				String right = stack.pop();
				if(LEFT.indexOf(s.substring(i, i+1)) != RIGHT.indexOf(right))
					return false;
			}
		}
		
		while(!stack.isEmpty())
			System.out.println(stack.pop());
		
		return true;
	}
}
