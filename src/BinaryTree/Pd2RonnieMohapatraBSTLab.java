package BinaryTree;

/*****************************************************************************************************************
NAME: Ronnie Mohapatra  
PERIOD: 2	
DUE DATE: January 30, 2019

PURPOSE: Working with Binary Search Trees

WHAT I LEARNED: a). I learned how to build a binary tree with an input string. 
  				b). I learned that the shape of the tree depends on the first character in the input string.
  				c). I learned that the minimum value of the tree is the leftmost value.
  				d). I learned that the maximum value of the tree is the rightmost value.

HOW I FEEL ABOUT THIS LAB: I feel that I learned a lot from this lab. Learning about these concepts is really
						   fun to visualize and apply. This lab was much easier than the Binary Tree Lab because
						   of the structure of the BST, where lesser values go left and greater values go right.
						   This structure makes building and searching much easier.
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): https://www.cs.usfca.edu/~galles/visualization/BST.html

****************************************************************************************************************/
import java.util.Scanner;
	/****************************************************************
	Practice with a Binary Search Tree. Uses TreeNode.
    Prompt the user for an input string.  Build a Binary Search Tree 
	using Comparables.  Display it as a sideways tree (take the code 
	from the Tree Lab).  Prompt the user for a target and search the tree 
	for it.  Display the tree's minimum and maximum values.  Print 
	the data in order from smallest to largest.
	*****************************************************************/
public class Pd2RonnieMohapatraBSTLab
{
   public static void main(String[] args)
   {
          // your code goes here
	   String input;
       Scanner s = new Scanner(System.in);
       do {
	       System.out.print("Enter input string: ");
	       input = s.nextLine();
	       if(input.equals("0"))
	    	   break;
	       TreeNode t = null;
	       for(int i = 0; i < input.length(); i++)
	    	   t = insert(t, "" + input.charAt(i));
	       
	       display(t, 0);
	       System.out.println();
	       int randInd = (int)(Math.random() * input.length());
	       System.out.println("Input target: " + input.charAt(randInd));
	       System.out.println(input.charAt(randInd) + " " + (find(t, "" + input.charAt(randInd)) ? "is found" : "not found."));
	       System.out.println("Input target: " + 'x');
	       System.out.println("x " + (find(t, "x") ? "is found." : "not found."));
	       System.out.println("Min: " + min(t));
	       System.out.println("Max: " + max(t));
	       System.out.print("In order: ");
	       smallToLarge(t);
	       System.out.println("\n");
       } while(true);
   }
      
   	/****************************************************************
   	Recursive algorithm to build a BST:  if the node is null, insert the 
   	new node.  Else, if the item is less, set the left node and recur to 
   	the left.  Else, if the item is greater, set the right node and recur 
   	to the right.   
   	*****************************************************************/
   public static TreeNode insert(TreeNode t, String s)
   {  	
	   if(t == null)
		   return new TreeNode(s, null, null);
	   else if(s.compareTo((String)t.getValue()) <= 0)
		   t.setLeft(insert(t.getLeft(), s));
	   else
		   t.setRight(insert(t.getRight(), s));
	   
	   return t;
   }
   
   public static void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      
      display(t.getRight(), level + 1); //recurse right
      
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
      
      display(t.getLeft(), level + 1); //recurse left
   }
   	
     /***************************************************************
      Iterative algorithm:  create a temporary pointer p at the root.  
   	While p is not null, if the p's value equals the target, return true.  
   	If the target is less than the p's value, go left, otherwise go right.   
   	If the target is not found, return false. 
      
   	Find the target. Recursive algorithm:  If the tree is empty, 
   	return false.  If the target is less than the current node 
   	value, return the left subtree.  If the target is greater, return 
   	the right subtree.  Otherwise, return true.   
   . ****************************************************************/    
   public static boolean find(TreeNode t, Comparable x)
   {
      if(t == null)
    	  return false;
      else if(x.compareTo(t.getValue()) < 0)
    	  return find(t.getLeft(), x);
      else if(x.compareTo(t.getValue()) > 0)
    	  return find(t.getRight(), x);
      else
    	  return true;
   }
   
     /***************************************************************
   	starting at the root, return the min value in the BST.   
   	Use iteration.   Hint:  look at several BSTs. Where are 
   	the min values always located?  
   	***************************************************************/
   public static Object min(TreeNode t)
   {
	   Object min = null;
	   for(TreeNode n = t; n != null; n = n.getLeft())
		   min = n.getValue();
	   
	   return min;
   }
   /*****************************************************************
   	starting at the root, return the max value in the BST.  
   	Use recursion!
    *****************************************************************/
   public static Object max(TreeNode t)
   {
      if(t.getRight() == null)
    	  return t.getValue();
      return max(t.getRight());
   }
   
   public static void smallToLarge(TreeNode t)
   {
	   if(t != null)
	   {
		   smallToLarge(t.getLeft());
		   System.out.print(t.getValue() + " ");
		   smallToLarge(t.getRight());
	   }
   }
}  //end of class

/* Output
 * Enter input string: AMERICAN
		R
			N
	M
			I
		E
			C
A
	A

Input target: A
A is found
Input target: x
x not found.
Min: A
Max: R
In order: A A C E I M N R 

Enter input string: AACEIMNR
						R
					N
				M
			I
		E
	C
A
	A

Input target: N
N is found
Input target: x
x not found.
Min: A
Max: R
In order: A A C E I M N R 

Enter input string: MAENIRAC
		R
	N
M
			I
		E
			C
	A
		A

Input target: R
R is found
Input target: x
x not found.
Min: A
Max: R
In order: A A C E I M N R 

Enter input string: 0
*/
