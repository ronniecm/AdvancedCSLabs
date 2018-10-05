package FirstTerm;
/*****************************************************************************************************************
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
*****************************************************************************************************************/
import java.util.Scanner;

public class Pd2RonnieMohapatraCLL <anyType>
{
   private ListNode <anyType> head;			//refers to the first element

   public Pd2RonnieMohapatraCLL()						
   {
      head = null;
   }


   //post: adds x to the front of the list 
   public void addFirst(anyType x)				
   {
	  if(head == null)
	  {
		  head = new ListNode<anyType>(x, null);
		  head.setNext(head);
	  }
	  else
	  {
		  ListNode<anyType> current = head;
		  while(current.getNext() != head)
			  current = current.getNext();
		  
		  current.setNext(new ListNode<anyType>(x, head));
		  head = current.getNext();
	  }
   }    // addFirst

   //post:  adds x to the end of the list
   public void addLast(anyType x)
   {
	  if(head == null)
	  {
		  head = new ListNode<anyType>(x, null);
		  head.setNext(head);
	  }
	  else
	  {
		  ListNode<anyType> current = head;
		  while(current.getNext() != head)
			  current = current.getNext();
		  
		  current.setNext(new ListNode<anyType>(x, head));
	  }
      //if list is empty
      //head will also be the last node who's next points to itself
      //else
      //make current go to the last element
      //make the current's next become a new ending node who's next points back to the first
   } 

   //pre:  the head is not null
   //post: returns the head's value
   public anyType getFirst() throws Exception
   {
      if (head==null)							//if list is empty
         throw new Exception("You can't get the head of an empty list");
      return head.getValue();
   }

   //pre:  the lastNode is not null
   //post: returns the lastNode's value
   public anyType getLast() throws Exception
   {
      if (head==null)						//if list is empty
         throw new Exception("You can't get the end of an empty list");
   
      ListNode<anyType> current = head;
      while(current.getNext()!= head) //make current go to the last element
         current = current.getNext();
      return current.getValue();
   }

  //pre:  the head is not null
  //post: removes the first element from the list and returns its value
   public anyType removeFirst() throws Exception
   {
      if(head == null)
    	  throw new Exception("No elements in the list");
      else if(head.getNext() == head)
      {
    	  anyType ret = head.getValue();
    	  head = null;
    	  return ret;
      }
      else
      {
    	  ListNode<anyType> current = head;
    	  anyType ret = head.getValue();
    	  while(current.getNext() != head)
    		  current = current.getNext();
    	  current.setNext(head.getNext());
    	  head = head.getNext();
    	  
    	  return ret;
      }
   }    

   //pre:  the head is not null
   //post: removes the last element from the list and returns its value
   public anyType removeLast() throws Exception
   {
      if(head == null)
    	  throw new Exception("No elements in list");
      else if(head.getNext() == head)
      {
    	  anyType ret = head.getValue();
    	  head = null;
    	  return ret;
      }
      else
      {
    	  ListNode<anyType> current = head;
    	  while(current.getNext().getNext() != head)
    		  current = current.getNext();
    	  
    	  anyType ret = current.getValue();
    	  current.setNext(head);
    	  
    	  return ret;
      }
   }    

   // ***************************************  WRITE THIS METHOD  ***********************************************
   //post: returns the number of elements
   public int size()
   {
	   ListNode<anyType> current = head;
	   if(current == null)
		   return 0;
	   else if(current.getNext() == head)
		   return 1;
	   else
	   {
		   int size = 0;
		   while(current.getNext() != head)
		   {
			   current = current.getNext();
			   size++;
		   }
		   
		   return size;
	   }
   }    

   //pre: index >=0 and index < size()
   //post: returns the object at a specific index (first element is index 0)
   public anyType get(int index)		
   {
	   if(index == 0)
		   return head.getValue();
	   else
	   {
		   int i = 0;
		   ListNode<anyType> current = head;
		   while(current.getNext() != head && i < index)
		   {
			   current = current.getNext();
			   i++;
		   }
		   return current.getValue();
	   }
	   
   }  



   //pre:  index >=0 and index < size()
   //post: changes the element at a specific index to x, returning the element that was originally there
   public anyType set(int index, anyType x)
   {
	   ListNode<anyType> current = head;
	   int i = 0;
	   while(current.getNext() != head && i < index)
	   {
		   current = current.getNext();
		   i++;
	   }
	   anyType ret = current.getValue();
	   current.setValue(x);
	   return ret;
   }

   //post: adds element x to the end of the list, returns true if successful
   public boolean add(anyType x)
   {
      addLast(x);
      return true;			
   }	  


   //pre:  index >=0 and index < size()
   //post: adds element x at index i, returns true if successful
   public boolean add(int index, anyType x)
   {
	  int i = 1;
	  ListNode<anyType> current = head;
	  
	  while(current.getNext() != head && i < index)
	  {
		  i++;
		  current = current.getNext();
	  }
	  
	  current.setNext(new ListNode<anyType>(x, current.getNext()));
	  
      return true;			
   }	  
   
   //pre: index >=0 and index < size()
   //post: removes and returns the object at a specific index (first element is index 0)
   public anyType remove(int index)		
   {
	   ListNode<anyType> current = head;
	   int i = 1;
	   while(current.getNext() != head && i < index)
	   {
		   current = current.getNext();
		   i++;
	   }
	   
	   anyType ret = current.getNext().getValue();
	   current.setNext(current.getNext().getNext());
	   return ret;
   }	  
   
   //pre:  the head is not null
   //post: shows all elements of the list O(n)
   public void showList()
   {
      if (head==null)						//if list is empty
         System.out.println("List is empty");
      else
      {
         ListNode<anyType> current =  head;
         while(current.getNext() != head)
         {
            System.out.print(current.getValue() + " ");
            current = current.getNext();
         }	
         System.out.println(current.getValue());
         System.out.println("And here is the last element's next: " + current.getNext().getValue());
      
      }
   
   }

   //pre:
   //post:  returns the contents of the list as a String in the form of [a1, a2, a3,...,an] where a1 is the first element and an is the last
   public String toString()
   {
      return null;
   }    // end of toString

   public boolean isEmpty()
   {
      if (head == null)
         return true;
      return false;
   }    


/*****************************************************************/
   public static void main(String[] arg) throws Exception
   {
      Scanner input = new Scanner(System.in);
      Pd2RonnieMohapatraCLL<String> list = new Pd2RonnieMohapatraCLL();
      String opt = "1";					//for options chosen
      String in;							//for inputing into the list
      while (!opt.equals("0"))
      {
         System.out.print("Here is your list:");
         list.showList();
         if(!list.isEmpty())
         {
            System.out.println("The first element is:" + list.getFirst());
            System.out.println("The last element is:" + list.getLast());
         }
         System.out.println("(1)to add in front");
         System.out.println("(2)to add in back");
         System.out.println("(3)to remove from front");
         System.out.println("(4)to remove from back");
         System.out.println("(5)to clear the list");
         System.out.println("(0) to quit");
         opt = input.next();
         if (opt.equals("1"))
         {
            System.out.println("What do you want to add");
            in = input.next();
            list.addFirst(in);
         }
         else if (opt.equals("2"))
         {
            System.out.println("What do you want to add");
            in = input.next();
            list.addLast(in);
         }
         else if (opt.equals("3"))
            list.removeFirst();
         else if (opt.equals("4"))
            list.removeLast();
         else if (opt.equals("5"))
         {
            while(!list.isEmpty())
               list.removeFirst();
         }
         else if(!opt.equals("0"))
            System.out.println("That is not an option");	
      }   // while
   }  // main
}    // CircularLinkedList