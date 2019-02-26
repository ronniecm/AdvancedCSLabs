package BinaryTree;
/*****************************************************************************************************************
NAME: Ronnie Mohapatra
PERIOD: 3
DUE DATE: February 6, 2019
PURPOSE: Deleting a node from a Binary Search Tree

WHAT I LEARNED: a). I learned that to recur throughout the whole tree, you have to recur in both directions
  				b). I learned the algorithm for deleting a node in a BST.
HOW I FEEL ABOUT THIS LAB:
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITES && what kind of 
         help did you get from the source?): 

****************************************************************************************************************/
import java.util.Scanner;
/****************************************************************
Practice with a Binary Search Tree. Uses TreeNode.
Step 1: Prompt the user for an input string.  
Step 2: Build a Binary Search Tree using Comparables.  
Step 3: Display it as a sideways tree (take the code from TreeLab).  
Step 4: Prompt the user for a target and delete that node, if it exists. 
*****************************************************************/
public class Pd2RonnieMohapatraBSTDeleteLab
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();   // Use	ECSBPWANR
   	         				   							    
      TreeNode t = null;
      for(int x = 0; x<s.length(); x++)
         t = insert(t, s.substring(x, x+1));
      display(t, 0);
   
      while (true)
      {
         System.out.print("Delete? ");
         String target = sc.next();
         if( contains( t, target ) )
         {
            t = delete( t, target );
            System.out.println("\n" + target+" deleted.");
         }
         else
            System.out.println("\n" + target+" not found");
         display(t, 0);   
      }     
   }
   
	/**************************
	Recursive algorithm to build a BST:  if the node is null, insert the 
	new node.  Else, if the item is less, set the left node and recur to 
	the left.  Else, if the item is greater, set the right node and recur 
	to the right.   
	*****************************/
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s, null, null);
      if(s.compareTo(t.getValue()+"")<0)
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
   
   public static boolean contains( TreeNode current, String target )
   {
      while(current !=null)
      {
         int compare = target.compareTo((String)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   public static TreeNode delete(TreeNode current, String target)
   {
	   if(current == null)
		   return null;
	   else if(target.compareTo((String)current.getValue()) < 0)
		   current.setLeft(delete(current.getLeft(), target)); //recur left if target is less than current
	   else if(target.compareTo((String)current.getValue()) > 0)
		   current.setRight(delete(current.getRight(), target)); //recur right if target is greater than current
	   else { //current equals target
		   if(current.getLeft() == null && current.getRight() == null) //get rid of node if target is a leaf node
			   return null;
		   else if(current.getLeft() != null && current.getRight() == null) 
			   return current.getLeft(); //return left child if current has only a left child
		   else if(current.getLeft() == null && current.getRight() != null) 
			   return current.getRight(); //return right child if current has only a right child
		   else {
			   current.setValue(findMax(current.getLeft())); //set current node value to maximum value of current's left subtree
			   current.setLeft(delete(current.getLeft(), (String)current.getValue())); //recur left with target value as the just-found max value
			   current.setRight(delete(current.getRight(), (String)current.getValue())); //recur right with target value as the just-found max value
		   }
	   }
	   return current; //return the tree that was given
   }
   
   public static Object findMax(TreeNode t)
   {
	   if(t.getRight() == null)
		   return t.getValue();
	   
	   return findMax(t.getRight());
   }
}  // end of class

/* Output
Input string: ECSBPWANR
W
S
	R
P
	N
E
C
B
	A
Delete? A

A deleted.
W
S
	R
P
	N
E
C
B
Delete? E

E deleted.
W
S
	R
P
	N
C
B
Delete? S

S deleted.
W
R
P
	N
C
B
Delete? N

N deleted.
W
R
P
C
B
Delete? 
*/