package FirstTerm;

/**
* Name: Ronnie Mohapatra                      
* Period: 2
* Name of the Lab: ListNode Linked List Lab
* Purpose of the Program: Processing singly-linked lists
* Due Date: September 20, 2018
* Date Submitted: S
* What I learned: a) I learned the proper way of traversing a linked list using a for loop
* 				  b) I learned valuable methods such as rotating, adding, deleting and reversing
* 					 linked lists.
* 				  c) I learned that you should test each individual method before applying them to other ones
* 					 or starting a new method.
* How I feel about this lab: I really liked this lab because it was the first assignment that I struggled with. It
* 						     took me some to get used to the structure of linked lists and the processing of them.
* 							 Overall, I feel this lab has made me into a more skilled programmer. 
* What I wonder: I wonder how to create doubly-linked lists 
* Credits:  
* Students whom I helped (to what extent):
*/
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Pd2RonnieMohapatraListNodeLinkedListLab
{

   private static class ListNode <E> 
   {    
      private E value;    
      private ListNode <E>  next; 
      public ListNode  (E  initValue, ListNode <E> initNext)    
      { 
         value = initValue; 
         next = initNext; 
      }  
      public E getValue()  
      { 
         return value; 
      }    
   
      public ListNode <E> getNext() 
      { 
         return next;  
      } 
   
      public void setValue(E theNewValue)
      { 
         value = theNewValue;
      }
   
      public void setNext(ListNode  <E> theNewNext)
      { 
         next = theNewNext; 
      }
   
   }  // end of ListNode

   public static void main(String [] args)
   {
      Scanner input = new Scanner (System.in);
      ListNode <Integer> h = new ListNode<Integer>(5, null);
      
      h= new ListNode(4, h);
      h= new ListNode(3, h);
      h= new ListNode(2, h);
      h= new ListNode(1, h); 
        
      char option ;
      
      
      do
      {
    	  option = menu();
    	  try {
    		  if( option  == 'a')
    		  {
    			  System.out.print("list: ");
    			  printLinkedList(h);
    		  }	
    		  else if(option == 'b')
    		  {	
    			  System.out.println("The List has atleast two element?");
    			  System.out.println(hasTwo(h));
    		  }	
    		  else if( option  =='c')
    		  {	
    			  System.out.print("The size of the array is: ");
    			  System.out.println(size(h));
    		  }	
    		  else if( option  == 'd')
    		  {
    			  h = removeFirst(h);
    			  System.out.print("New list is: ");
    			  printLinkedList(h);    			  
    		  }
    		  else  if( option  == 'e')
    		  {
    			  System.out.println("Enter number: ");
    			  int num = input.nextInt();
    			  h = add(h, new Integer(num));
    			  System.out.println("New list is: ");
    			  printLinkedList(h);
    		  }
    		  else if( option  == 'f')
    		  {
    			  h = reverseList(h);
    			  System.out.println("Reverse is: ");
    			  printLinkedList(h);
    		  }
    		  else if( option  == 'g')
    		  {
    			  h = rotate(h);
    			  System.out.println("rotated array is: ");
    			  printLinkedList(h);
    		  }
    		  else if( option  == 'h')
    		  {
    			  printLinkedList(h);
    			  System.out.println("\nmiddle node is: "+middleNode(h).getValue());
    		  }
         
    		  else if( option  == 'i')
    		  {
    			  h = removeLast(h);
    			  System.out.print("New list is: ");
    			  printLinkedList(h);
    		  }
         
    		  else if( option == 'j')
    		  {
    			  System.out.println("Enter the value you would like to remove:");
    			  int removed = input.nextInt();
    			  h = remove(h, removed);
    			  System.out.print("New list is: ");
    			  printLinkedList(h);
    		  }
    		  else if(option == 'k')
    		  {
    			  h = splitList(h);
    			  System.out.print("New list is: ");
    			  printLinkedList(h);
    		  }
    	  } catch(NullPointerException e) {
    		  System.out.println("Empty");
    	  }
    	}
      	while (option != 'z');
   }  // end of main
   
   /*
    * pre-condition: head != null
    * post-condition: prints list referring to head
    */
   public static void printLinkedList(ListNode <Integer> head) 
   {
	   System.out.print(head.getValue() + " ");
		  
	   if(head.getNext() != null)
		   printLinkedList(head.getNext());  
   }
   
   /*
    * pre-condition: head != null
    * post-condition: return true or false for whether head has at least two elements
    */
   public static boolean hasTwo(ListNode  <Integer> head) 
   {
      return size(head) >= 2;
   
   }
   /*
    * pre-condition: none
    * post-condition: returns number of elements in the linked list, returns 0 for null list
    */
   public static int size(ListNode <Integer> head) 
   {
	  if(head != null)
      {
    	  return 1 + size(head.getNext()); //adds one to what the next call of size() returns if the node is not null
      }
      
      return 0; //for last node
   }
   /*
    * pre-condition: head != null
    * post-condition: returns head with first element removed
    */
   public static ListNode <Integer> removeFirst(ListNode <Integer> head) 
   {	
	   if(head != null)
		{
		   head = head.getNext();
		   return head; //head.getNext() is head without the first node
		}
	   else
		   throw new NullPointerException();
   }	
   /*
    * pre-condition: head != null
    * post-condition: returns head with last node removed
    */
   public static ListNode <Integer> removeLast(ListNode <Integer>  head) 
   {
	   if(head.getNext() != null)
	   {
		  head.setNext(removeLast(head.getNext())); //for every node except the last, setNext to its usual next node
		  return head;
	   }
	   
	   return null; //for last node, setNext to null, ends list
   }
   /*
    * pre-condition: head != null
    * post-condition: 
    */
   public static ListNode <Integer> remove(ListNode<Integer> head, Integer value) 
   {
	   try { //try-catch for a null-pointer exception if value is not found
		   if(head.getValue() != value)
		   {
			   head.setNext(remove(head.getNext(), value));   
			   return head;
		   }
	   
		   return head.getNext(); 
	   	   
	   } catch (NullPointerException e) {
	   		   System.out.println("Value not found");
	   }
	   return head;
   }
   /*
    * pre-condition: head != null
    * post-condition: add ListNode<Integer> with data = value to the end of the linked list
    */
   public static ListNode<Integer> add(ListNode <Integer> head, Integer value) 
   {
	   if(size(head) == 0) //if list is empty
	   {
		   head = new ListNode<Integer>(value, null);
		   return head;
	   }
	   
	   if(head == null) //base case for reaching the last node
	   {
		   return new ListNode<Integer>(value, null);
	   }
	   
	   head.setNext(add(head.getNext(), value)); //keeping list unchanged until the end
	   return head;
   }
   /*
    * pre-condition: head != null
    * post-condition: returns head in reverse order
    */
   public static ListNode <Integer> reverseList(ListNode <Integer> head) 
   {
	   if(head.getNext() == null)
		   return head; //starts the reversed list with the last node
	   
		ListNode<Integer> ret = reverseList(head.getNext()); 
		head.getNext().setNext(head); //takes current node and links it to the end
		head.setNext(null); //makes the node the last one officially
		return ret;
   }
   /*
    * pre-condition: head != null
    * post-condition: returns head with first node at the end
    */
   public static ListNode <Integer> rotate(ListNode <Integer> head) 
   {
      ListNode<Integer> newHead = head.getNext(); //saves second element as a new head
     
      for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
      {
    	  if(temp.getNext() == null)
    	  {
    		 temp.setNext(head); //puts list at end
    		 head.setNext(null); //ends list after first element
    	  }
      }
      return newHead;
   }
   /*
    * pre-condition: head != null
    * post-condition: returns middle node or one of the middle nodes of head
    */
   public static ListNode <Integer> middleNode(ListNode <Integer>head) 
   {
	   ListNode<Integer> mid = head; //middle of a list with size 1
	   int count = 0; //tracks progress through list
	   
	   for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
	   {
		   count++;
		   if(count % 2 == 0)
			   mid = mid.getNext(); //sets mid to its next node if count reaches another even number
	   }
	   
	   return mid;
   }
   /*
    * pre-condition: head != null
    * post-condition: splits head into two lists, odds and evens, head is empty after
    */
   public static ListNode<Integer> splitList(ListNode<Integer> head) 
   {
	   ListNode<Integer> odds = null;
	   ListNode<Integer> evens = null;
	   ListNode<Integer> currentOdd = null;
	   ListNode<Integer> currentEven = null;
	   int count = 0;
	   
	   for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext(), head = head.getNext())
	   {
		   count++;
		  
		   if(count == 1)
		   {
			 odds = temp;
			 currentOdd = temp;
		   } 
		   
		   else if(count % 2 == 1)
		   {
			   currentOdd.setNext(temp);
			   currentOdd = temp;
		   }
		   else if(count == 2)
		   {
			   evens = temp;
			   currentEven = temp;
		   }
		   else if(count % 2 == 0)
		   {
			   currentEven.setNext(temp);
			   currentEven = temp;
		   }
	   }
	   
	   return null;
   }

   public static char menu()
   {
      Scanner input = new Scanner (System.in);
      System.out.println("\n====> What would you like to do?");
      System.out.println("a) Print list");
      System.out.println("b) Check if list has at least two nodes");
      System.out.println("c) Get size of the list");
      System.out.println("d) Remove first node");
      System.out.println("e) Add a node");
      System.out.println("f) Reverse");
      System.out.println("g) Rotate");
      System.out.println("h) Get middle node");
      System.out.println("i) Remove last node");
      System.out.println("j) Remove any node");
      System.out.println("k) Split the list");
      System.out.println("z) Quit?");
      String choice = input.next();
      return choice.charAt(0);   
   }  // end of menu
}

/*
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
a
list: 1 2 3 4 5 
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
b
The List has atleast two element?
true

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
c
The size of the array is: 5

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
d
New list is: 2 3 4 5 
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
e
Enter number: 
6
New list is: 
2 3 4 5 6 
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
f
Reverse is: 
6 5 4 3 2 
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
g
rotated array is: 
5 4 3 2 6 
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
h
5 4 3 2 6 
middle node is: 3

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
i
New list is: 5 4 3 2 
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
j
Enter the value you would like to remove:
4
New list is: 5 3 2 
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
k
New list is: Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
a
list: Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
b
The List has atleast two element?
false

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
c
The size of the array is: 0

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
d
Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
f
Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
g
Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
h
Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
i
Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
j
Enter the value you would like to remove:
5
Value not found
New list is: Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
k
New list is: Empty

====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
e
Enter number: 
1
New list is: 
1 
====> What would you like to do?
a) Print list
b) Check if list has at least two nodes
c) Get size of the list
d) Remove first node
e) Add a node
f) Reverse
g) Rotate
h) Get middle node
i) Remove last node
j) Remove any node
k) Split the list
z) Quit?
z
*/