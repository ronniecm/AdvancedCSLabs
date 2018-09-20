package FirstTerm;

/**
* Name:                       
* Period: 
* Name of the Lab: 
* Purpose of the Program: 
* Due Date: 
* Date Submitted: 
* What I learned: 
* How I feel about this lab: 
* What I wonder:  
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
      ListNode <Integer> h = new ListNode( 5, null);
      h= new ListNode(4, h);
      h= new ListNode(3, h);
      h= new ListNode(2, h);
      h= new ListNode(1, h);
        
      char option ;
      do
      {
         option = menu();
         if( option  == 'a')
         {
            System.out.println("list: ");
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
            add(h, new Integer(num));
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
      
      } while (option != 'z');
   
   }  // end of main
   
   public static void printLinkedList(ListNode <Integer> head)
   {
	   for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
		   System.out.print(temp.getValue() + " ");
   }
   
   public static boolean hasTwo(ListNode  <Integer> head)
   {
      return size(head) >= 2;
   
   }
   public static int size(ListNode <Integer> head)
   {
      int size = 0;
      for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
      {
    	  if(temp != null)
    		  size++;
      }
      
      return size;
   }

   public static ListNode <Integer> removeFirst(ListNode <Integer> head)
   {	
	   head = head.getNext();
	   return head;
   }

   public static ListNode <Integer> removeLast(ListNode <Integer>  head)
   {	
      for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
      {
    	  if(temp.getNext().getNext() == null)
    	  {	  
    		  temp.setNext(null);
    	  }
      }    
      
      return head;
   }
   
   public static ListNode <Integer> remove(ListNode<Integer> head, Integer value)
   {	
      //ListNode<Integer> prev = head;
      
      for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
      {
    	  if(temp.getValue() == value)
    		  temp = temp.getNext();
      }
      
      return head;
   }

   public static void add(ListNode <Integer> head, Integer value)
   {
	   ListNode<Integer> n = new ListNode<Integer>(value, null);
	   for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
	   {
		   if(temp.getNext() == null) {
			   temp.setNext(n);
			   return;
		   }
	   }
   }

   public static ListNode <Integer>  reverseList(ListNode <Integer> head)
   {
      ListNode<Integer> prev = null;
      ListNode<Integer> temp = head;
      
      while(temp != null)
      {
    	  ListNode<Integer> next = temp.getNext();
    	  temp.setNext(prev);
    	  prev = temp;
    	  temp = next;
      }
      head = prev;
      return head;
   }

   public static ListNode <Integer> rotate(ListNode <Integer> head)
   {
      ListNode<Integer> newHead = head.getNext();
     
      for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
      {
    	  if(temp.getNext() == null)
    	  {
    		 temp.setNext(head);
    		 head.setNext(null);
    	  }
      }
      return newHead;
   }

   public static ListNode <Integer> middleNode(ListNode <Integer>head)
   {
	   ListNode<Integer> mid = head;
	   int count = 0;
	   
	   for(ListNode<Integer> temp = head; temp != null; temp = temp.getNext())
	   {
		   count++;
		   if(count % 2 == 0)
			   mid = mid.getNext();
	   }
	   
	   return mid;
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
      System.out.println("z) Quit?");
      String choice = input.next();
      return choice.charAt(0);   
   }  // end of menu
}

/*
Output:
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
z) Quit?
a
list: 
1 2 3 4 5 
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
z) Quit?
z
*/