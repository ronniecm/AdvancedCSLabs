package Stack;
import java.util.Stack;
import java.util.Scanner;

public class TextEditorLab {
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		String runAgain;
		
		//do {
			System.out.print("Enter a line of text: ");
			String input = s.nextLine();
			
			Stack<String> text = new Stack<String>();
			
			for(int i = input.length() - 1; i >= 0; i--)
				text.push(input.charAt(i) + "");
			
			String result = "";
			while(!text.isEmpty())
			{
				result += text.peek();
				
				if(text.peek().equals("$"))
				{
					result = "";
				} else if(text.peek().equals("-") && result.equals("-")) {
					result = "";
				} else if(text.peek().equals("-")) {
					result = result.substring(0, result.indexOf(text.peek()) - 1);
				}
				
				text.pop();
				
				System.out.println(result);
			}
			
			System.out.println(result);
			
			System.out.print("Run again? y/n");
			runAgain = s.next();
	//} while(runAgain.equals("y"));
	}
}
