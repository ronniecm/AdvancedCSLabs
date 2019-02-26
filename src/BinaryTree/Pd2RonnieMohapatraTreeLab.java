package BinaryTree;

/**
* Name: Ronnie Mohapatra                
* Period: 2
* 
* Purpose of the Program: Binary Tree Methods
* Date Submitted: January 21 2019
* 
* What I learned: a). I learned the threee different traversal methods for a binary tree, preorder, inorder, and postorder.
* 				  b). I learned how to find the maximum and minimum node of a binary tree.
* 				  c). I learned how to find the height of a binary tree.
* 				  d). I learned how to count the number leaves and single-children nodes in a binary tree.
* 
* How I feel about this lab: I felt that it was a big challenge doing this lab cause it is more complex than other data
* 							 structures that we have learned in the past two years. It also fun learning how to write these
* 							 methods.
* 	
* What I wonder: What are some valuable applications of a binary tree.
* Credits:
* Students whom I helped (to what extent): 
*/


import java.util.*; //for the queue interface

public class Pd2RonnieMohapatraTreeLab     
{
   public static void main(String[] args)
   {
      String s = "XCOMPUTERSCIENCE";
   		
      TreeNode root = new TreeNode("" + s.charAt(1), null, null);
         
       // The root node has index 1
       // The second level nodes' indices: 2, 3
       // The third level nodes' indices: 4,5,6,7
       // Idea: based on the index of the node, log (pos) of base 2 calculates the level of the
       //          node: root (index 1) on level 1. Node with index 2 or 3 is on level 2
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, (int)(1 + Math.log(pos) / Math.log(2)));
      
      // NOTE: The following 3 lines are supposed to further show you how insert works. You
      //             uncomment them and see how the tree looks like with these 3 additional nodes
      	insert(root, "A", 17, 5); 
      	insert(root, "B", 18, 5); 
    	insert(root, "C", 37, 6); //B's right child
      
      // display the tree sideway; see the sample output at the end of this file
      display(root, 0);
      
      System.out.print("\nPreorder: ");
      preorderTraverse(root);
      System.out.print("\nInorder: ");
      inorderTraverse(root);
      System.out.print("\nPostorder: ");
      postorderTraverse(root);
      
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Grandparents = " + countGrandParentNodes(root));  // count the number grandparent nodes
      System.out.println("Only childs = " + countSingleChildNodes(root));	   // count the number of nodes that has only 1 child
         
      // System.out.println("\nDepth = " + numOfLevels(root));                    
      System.out.println("Height = " + height(root));

      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
      
      System.out.println("\nBy Level: ");
      displayLevelOrder(root);     // level by level display of the nodes (starts from left to right for nodes on the same level)
      
   } // end of main
   
   
   // insert a new node in the tree based on the node's level
   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
         //  then 1 << 4 will insert four 0-bits at the right of 1 (binary representation of 1 is 1. 
         // 1 << 4 results in 10000 (in binary)
         if((pos & (1 << k)) == 0) 
            p = p.getLeft();      // What does this do? Answer this question first.  What does '&' do? Google it!!!!
         else                        //  We did not learn this in AP CS A!  :
            p = p.getRight();    // What does this do? Answer this question first.
            
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   } // end of insert
      
      
   /*****************************************************************************************************   
     postcondition: display the tree sideway   
   *****************************************************************************************************/   
   public static void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      display(t.getRight(), level + 1); //recurse right
         
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
         
      display(t.getLeft(), level + 1); //recurse left
   }  // end of display
      
      
   public static void preorderTraverse(TreeNode t)
   {
	   if(t != null)
	   {
		   System.out.print(t.getValue() + " "); //root
		   preorderTraverse(t.getLeft()); //left
		   preorderTraverse(t.getRight()); //right
	   }
   }
   
   
   
   public static void inorderTraverse(TreeNode t)
   {
        if(t != null)
        {
        	inorderTraverse(t.getLeft()); //left
        	System.out.print(t.getValue() + " "); //root
        	inorderTraverse(t.getRight()); //right
        }
   }
   
   
   public static void postorderTraverse(TreeNode t)
   {
        if(t != null)
        {
        	postorderTraverse(t.getLeft()); //left
        	postorderTraverse(t.getRight()); //right
        	System.out.print(t.getValue() + " "); //root
        }
   }
   
   
   public static int countNodes(TreeNode t)
   {
      if(t != null)
      {
    	  return 1 + countNodes(t.getLeft()) + countNodes(t.getRight()); //add one after each node visited
      }
      
      return 0; //add zero is node is null
   }
   
   
   public static int countLeaves(TreeNode t)
   {
      if(t != null)
      {
    	  if(t.getLeft() == null && t.getRight() == null)
    		  return 1 + countLeaves(t.getLeft()) + countLeaves(t.getRight()); //add 1 if node has no children
    	  else
    		  return countLeaves(t.getLeft()) + countLeaves(t.getRight()); //add zero if node has a child
      }
      
      return 0; //add zero if node is null
   }
   
   
   public static int countGrandParentNodes(TreeNode t)
   {
      if(t != null)
      {
    	  System.out.println(t.getValue());
    	  if(t.getLeft() != null) //must have a child to count, causes NullPointerException if no children
    	  {
    		  if(t.getLeft().getLeft() != null || t.getLeft().getRight() != null) //add one if it has a grandchild
    			  return 1 + countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight());
    		  else
    			  return countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight()); //add zero if no grandchild
    	  }
    	  else if(t.getRight() != null)
    	  {
    		  if(t.getRight().getLeft() != null || t.getRight().getRight() != null) //same thing as left side
    			  return 1 + countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight());
    		  else
    			  return countGrandParentNodes(t.getLeft()) + countGrandParentNodes(t.getRight());
    	  }
    	  else
    		  return 0; //add zero if no children
      }
      else {
    	  return 0;
      }
   }
   
   
   public static int countSingleChildNodes(TreeNode t)
   {
      if(t != null)
      {
    	  if(t.getLeft() != null && t.getRight() == null || t.getLeft() == null && t.getRight() != null) //add one if node has only one child
    		  return 1 + countSingleChildNodes(t.getLeft()) + countSingleChildNodes(t.getRight());
    	  else
    		  return countSingleChildNodes(t.getLeft()) + countSingleChildNodes(t.getRight()); //add zero if else
      }
      
      return 0; //add zero if null
   }
   public static int depth(TreeNode t)
   {
	   return -1; //method not used and output not correct in shell
   }
      
   public static int height(TreeNode t)
   {
      if(t != null)
      {
    	  if(t.getLeft() != null) //go left if left is not empty
    		  return 1 + height(t.getLeft()); //add one
    	  else if(t.getRight() != null) //go right if right is not empty
    		  return 1 + height(t.getRight()); //add one
    	  else 
    		  return 0; //add zero if there is no where to go
      }
      
      return 0; //add zero if node is null
   }
      
   public static Object min(TreeNode t)
   {
	   if(t == null)
		   return "ZZ"; //return most maximum String if node is null
	   //get node value, left and right values
	   String min = (String)t.getValue();
	   String left = (String)min(t.getLeft());
	   String right = (String)min(t.getRight());
	   
	   if(left.compareTo(min) < 0 && right.compareTo(min) < 0) //return left or right if they are both less than current minimum
		   return left.compareTo(right) < 0 ? left : right;
	   else if(left.compareTo(min) < 0) //return left if it is less than minimum
		   return left;
	   else if(right.compareTo(min) < 0) //return right if it is less than minimum
		   return right;
	   else
		   return min; //return current minimum
   }
    
   public static Object max(TreeNode t)
   {
      if(t == null)
    	  return ""; //return least possible String if node is null
      //same documentation for min method
      String max = (String)t.getValue();
      String left = (String)max(t.getLeft());
      String right = (String)max(t.getRight());
      
      if(left.compareTo(max) > 0 && right.compareTo(max) > 0)
    	  return left.compareTo(right) > 0 ? left : right;
      else if(left.compareTo(max) > 0)
    	  return left;
      else if(right.compareTo(max) > 0)
    	  return right;
      else
    	  return max;
   }
   
   /*****************************************************************************************************
        This method is not recursive.  
        Hint: Use a local queue to store the children of the current node.
   *****************************************************************************************************/
   public static void displayLevelOrder(TreeNode t)
   {
	 Queue<Object> q = new LinkedList<Object>();
	 q.add(t); //add to queue
	 while(!q.isEmpty())
	 {
		 TreeNode c = (TreeNode)q.peek();
		 //add children to node
		 if(c.getLeft() != null)
			 q.add(c.getLeft());
		 if(c.getRight() != null)
			 q.add(c.getRight());
		 
		 //print q.peek()
		 System.out.print(c.getValue());
		 q.remove();
	 }
   }
}  // end of TreeLab_shell

class TreeNode {
	private Object val;
	private TreeNode left, right;
	
	public TreeNode(Object val, TreeNode left, TreeNode right)
	{
		this.val = val;
		this.left = null;
		this.right = null;
	}
	
	public Object getValue()
	{
		return this.val;
	}
	
	public TreeNode getLeft()
	{
		return this.left;
	}
	
	public TreeNode getRight()
	{
		return this.right;
	}
	
	public void setValue(Object obj)
	{
		this.val = obj;
	}
	public void setLeft(TreeNode t)
	{
		this.left = t;
	}
	
	public void setRight(TreeNode t)
	{
		this.right = t;
	}
}
/* Output
E
E
C
M
N
T
E
C
I
U
C
O
S
		C
	B
P
	A
R

Preorder: C O P R A S B C U C I M T E N E C E 
Inorder: R A P B C S O C U I C E T N M C E E 
Postorder: A R C B S P C I U O E N T C E E M C 

Nodes = 18
Leaves = 8
C
O
P
R
A
S
B
C
U
C
I
M
T
E
N
E
C
E
Grandparents = 5
Only childs = 3
Height = 4
Min = A
Max = U

By Level: 
COMPUTERSCIENCEABC
*/