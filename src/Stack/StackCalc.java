package Stack;
import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;
import java.awt.*;

public class StackCalc extends JPanel {
	private JPanel numsPanel;
	private JTextField calcText;
	private JButton[][] nums = new JButton[4][3];
	private JPanel west;
	private JButton[][] opButtons = new JButton[5][1];
	public static final String OPS = "C+-÷*";
	public static final String NUMBERS = "1234567890";
	public static final String OPERATORS = "+-*÷";
	private boolean isPressed = false;
	private JPanel east;
	private JPanel parenthesesPanel;

	public StackCalc()
	{
		setLayout(new BorderLayout());
		
		calcText = new JTextField("0", 10);
		calcText.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		calcText.setHorizontalAlignment(SwingConstants.RIGHT);
		add(calcText, BorderLayout.NORTH);
		
		numsPanel = new JPanel();
		numsPanel.setLayout(new GridLayout(4, 3));
		add(numsPanel, BorderLayout.CENTER);
		
		int tracker = 1;
		
		for(int r = 0; r < 4; r++)
		{
			for(int c = 0; c < 3; c++)
			{
				JButton num ;
				
				if(tracker == 10)
					num = new JButton(".");
				else if(tracker == 11)
					num = new JButton("0");
				else if(tracker == 12)
					num = new JButton("delete");
				else
					num = new JButton(tracker + "");
				
				if(tracker != 12)
					num.addActionListener(new Insert(num.getText()));
				else
					num.addActionListener(new Delete());
				nums[r][c] = num;
				tracker++;
				numsPanel.add(nums[r][c]);
			}
		}
		
		west = new JPanel();
		west.setLayout(new GridLayout(5, 1));
		add(west, BorderLayout.WEST);
		
		for(int i = 0; i < 5; i++)
		{
			opButtons[i][0] = new JButton(OPS.charAt(i) + "");
			opButtons[i][0].addActionListener(new OpListener(i));
			west.add(opButtons[i][0]);
		}
		
		east = new JPanel();
		east.setLayout(new GridLayout(2, 1));
		add(east, BorderLayout.EAST);
		
		parenthesesPanel = new JPanel();
		parenthesesPanel.setLayout(new GridLayout(1, 2));
		east.add(parenthesesPanel);
		JButton leftP = new JButton("(");
		JButton rightP = new JButton(")");
		leftP.addActionListener(new Insert("("));
		rightP.addActionListener(new Insert(")"));
		parenthesesPanel.add(leftP);
		parenthesesPanel.add(rightP);
		JButton equalButton = new JButton("=");
		equalButton.addActionListener(new Equals());
		east.add(equalButton);
		
	}
	
	private class OpListener implements ActionListener
	{
		private int r;
		
		public OpListener(int r)
		{
			this.r = r;
		}
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(r);
			if(r == 0)
				calcText.setText("0");
			else if(calcText.getText() != "0")
				calcText.setText(calcText.getText() + opButtons[r][0].getText());
		}
	}
	
	private class Delete implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			calcText.setText(calcText.getText().substring(0, calcText.getText().length() - 1));
		}
	}
	
	private class Insert implements ActionListener
	{
		private String s;
		public Insert(String s)
		{
			this.s = s;
		}
		public void actionPerformed(ActionEvent e)
		{
			if(calcText.getText().equals("0") || isPressed)
				calcText.setText(s);
			else
				calcText.setText(calcText.getText() + s);
	
			isPressed = false;
		}
	}
	
	private class Equals implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String postfix = convert(calcText.getText());
			System.out.println(postfix);
			calcText.setText(eval(postfix) + "");
			isPressed = true;
		}
		
		public String convert(String str) 
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
				} else if(i <= str.length() - 2 && NUMBERS.indexOf(str.charAt(i)) != -1 && NUMBERS.indexOf(str.charAt(i+1)) != -1) {
					postfix += str.substring(i, i+1) + "|";
					//System.out.println("hi");
				//	i++;
				} else {
					postfix += str.charAt(i); //append to postfix expression if char is operand
				}
				System.out.println(postfix);
			}
			
			while(!stack.isEmpty()) //keep popping and appending until stack is empty
			{
				postfix += stack.pop();
			}
			
			return postfix;
		}
		
		public boolean isLower(char op1, char op2)
		{
			//returns true if op1 is + or - and op2 is * or /
			return (op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '÷') ?  true :  false;
		}
		
		public double eval(String str)
		{
			Stack<Double> stack = new Stack<Double>(); //initialize Stack
			for(int i = 0; i < str.length(); i++)
			{
				if(isOperator(str.charAt(i)))
				{
					double a = stack.pop(), b = stack.pop(); //pop top two operands
					
					if(str.charAt(i) == '-' || str.charAt(i) == '÷' || str.charAt(i) == '%' || str.charAt(i) == '^') //evaluate the bottom one between a and b if operator is division or subtraction
						stack.push(eval(b, a, str.charAt(i)));
					else
						stack.push(eval(a, b, str.charAt(i))); //result to top of stack
				} else if(str.charAt(i) == '.') {
					int tracker = i; 
					String num = str.substring(tracker - 1, tracker+2);
					while(tracker < str.length() - 1 && str.charAt(tracker+2) == '|')
					{
						tracker+=2;
						num += str.substring(tracker+1, tracker+2);
					}
					stack.push(Double.parseDouble(num));
					i = tracker;
					i++;
					//stack.push(Double.parseDouble(str.substring(i-1, i+2)));
					//i++;
				} else if(NUMBERS.indexOf(str.charAt(i)) != -1 && str.charAt(i+1) != '.' && str.charAt(i+1) != '|') {
					stack.push(Double.parseDouble(str.charAt(i) + ""));
				} else if(str.charAt(i) == '|') {
					int tracker = i;
					String num = str.substring(tracker - 1, tracker) + str.substring(tracker+1, tracker+2);
					while(tracker < str.length() - 1 && (str.charAt(tracker+2) == '|' || str.charAt(tracker+2) == '.'))
					{
						tracker += 2;
						if(str.charAt(tracker) == '|')
							num += str.substring(tracker+1, tracker+2);
						else
							num += str.substring(tracker, tracker+2);
					}
					stack.push(Double.parseDouble(num));
					i = tracker;
					i++;
					//i++;
				}
				
				System.out.println(stack);
			}
			
			return stack.pop(); //final answer will be at the top of the stack
		}
		
		public double eval(double a, double b, char ch)
		{
			//checks what ch is and does the respective calculation
			if(ch == '+')
				return a+b;
			else if(ch == '-')
				return a-b;
			else if(ch == '*')
				return a*b;
			else if(ch == '÷')
				return a /b;
			else
				return 0;
		}
		
		public boolean isOperator(char ch)
		{
			return OPERATORS.indexOf(ch) != -1;
		}	
	}
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setLocation(0, 0);
		frame.setSize(810, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new StackCalc());
		frame.setVisible(true);
	}
}
