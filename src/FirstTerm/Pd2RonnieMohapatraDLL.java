package FirstTerm;

/***********************************************************************************************************************************************
 * Name: Ronnie Mohapatra              
 * Period: 2
 * Name of the Lab: Doubly Linked List Lab
 * Purpose of the Program: Write major methods for a Doubly Linked List
  * Due Date: October 3, 2018
 * Date Submitted:
 * What I learned: 
 * 1. I learned that you have to repair the previous linkages as well as the post linkages in a doubly linked list.
 * 2. While iterating through a doubly linked list, make sure to have a boolean condition that allows the loop to skip the dummy node
 * ... 
 * How I feel about this lab: I feel like I accomplished something difficult. It takes a lot of logic to write these methods because you have to make
 * sure you fix the post and previous linkages
 * What I wonder: How hard is a circularly linked list methods?
 * Student(s) who helped me (to what extent):  
 * Student(s) whom I helped (to what extent):
 *************************************************************************************************************************************************/
public class Pd2RonnieMohapatraDLL <E>
{
   // private static nested class
   private class DLNode <E>
   {
      private E value;
      private DLNode prev;
      private DLNode next;
      public DLNode(E arg, DLNode <E> p, DLNode <E> n)
      {
         value=arg;
         prev=p;
         next=n;
      }
      public DLNode()
      {
         value=null;
         next=this;
         prev=this;
      }
      public void setValue(E arg)
      {
         value=arg;
      }
      public void setNext(DLNode <E> arg)
      {
         next=arg;
      }
      public void setPrev(DLNode <E> arg)
      {
         prev=arg;
      }
      public DLNode <E> getNext()
      {
         return next;
      }
      public DLNode <E> getPrev()
      {
         return prev;
      }
      public E getValue()
      {
         return value;
      }
   }  // end of DLNode
   
   //*********************************************************************************************  DLL class

   private int size = 0;
   private DLNode <E> head = new DLNode <E> (); //dummy node--very useful--simplifies the code
   
   public int size()
   {
	   int count = 0;
	   
	   if(head.getNext() == head)
		   return 0;
	   else
	   {
		   DLNode<E> current = head.getNext();
		   while(current != null)
		   {
			   count++;
			   current = current.getNext();
		   }
	   }
     
     return count;
   }
   /* appends obj to end of list; increases size;
     @return true  */
   public boolean add(E obj)
   {
	 if(this.size() == 0)
		 head.setNext(new DLNode<E>(obj, head, null));
	 else
	 {
		 DLNode<E> current = head.getNext();
		 while(current.getNext() != null)
			 current = current.getNext();
		 
		 current.setNext(new DLNode<E>(obj, current, null));
	 }
	 
     return true;
   }
   /* inserts obj at position index.  increments size. 
   	*/
   public void add(int index, E obj)
   {
	  DLNode<E> current = head.getNext();
	  int i = 1;
	  
	  while(i < index)
	  {
		  i++;
		  current = current.getNext();
	  }
	  
	  DLNode<E> node = new DLNode<E>(obj, current, current.getNext());
	  current.setNext(node);
   }
   /* return obj at position index.  
   	*/
   public E get(int index)
   {
	  int tracker = 0;
	  for(DLNode<E> temp = head.getNext(); temp != null; temp = temp.getNext(), tracker++)
	  {
		  if(tracker == index)
			  return temp.getValue();
	  }
	  
	  return null;
   }
   /* replaces obj at position index.  
   	*/
   public void set(int index, E obj)
   {
	   int tracker = 1;
		  for(DLNode<E> temp = head.getNext(); temp != null; temp = temp.getNext(), tracker++)
		  {
			  if(tracker == index)
			  {
				  temp.setValue(obj);
			  }
		  }  
   }
   /*  removes the node from position index.  decrements size.
     @return the object at position index.
    */
   public E remove(int index)
   {
      DLNode<E> currentNode = head.getNext();
      int current = 0;
      
      while(current < index)
      {
    	  current++;
    	  currentNode = currentNode.getNext();
      }
      
      E ret = currentNode.getValue();
      currentNode.getPrev().setNext(currentNode.getNext());
      return ret;
   }
   /* inserts obj at front of list; increases size;
     */
   public void addFirst(E obj)
   {
	   head.setNext(new DLNode<E>(obj, head, head.getNext()));
	   head.getNext().getNext().setPrev(head.getNext());
   }
   /* appends obj to end of list; increases size;
       */
   public void addLast(E obj)
   {
	   add(obj);
   }
   public E getFirst()
   {
      return head.getNext().getValue();
   }
   public E getLast()
   {
      return null;
   }
   public E removeFirst()
   {
	  E ret = head.getNext().getValue();
      head.setNext(head.getNext().getNext());
      head.getNext().setPrev(head);
      return ret;
   }
   
   public E removeLast()
   {
      return null;
   }
   public String toString()
   {
	   String result = "";
	   for(DLNode<E> temp = head; temp != null; temp = temp.getNext())
	   {
		   if(temp != head)
			   result += temp.getValue() + " ";
	   }
	   
	   return result;
   }



   public static void main(String args[])
   {
      Pd2RonnieMohapatraDLL <String> list = new Pd2RonnieMohapatraDLL <String> ();	
   
      list.addLast("Apple");
      list.addLast("Banana");
      list.addLast("Cucumber");
      list.add("Dumpling");
      list.add("Escargot");
      System.out.println(list);
      System.out.println("Size: " + list.size());
      Object obj = list.remove(3);
      System.out.println(list);
      System.out.println("Size: " +list.size());
      System.out.println("Removed "+ obj);
      System.out.print("Add at 3:   ");
      list.add(3,"Cheese");
      System.out.println(list);
      System.out.println("Get values at 1 and first: " + list.get(1)+" and " + list.getFirst());
      System.out.println("No change: " +list);
      System.out.println( list.removeFirst() + " is now removed!"); 
      System.out.println(list);
      System.out.print("Add first:  ");
      list.addFirst("Anchovie");
      System.out.println(list);
      System.out.println("Size: " + list.size());
      System.out.print("Set the second:  ");
      list.set(2, "Rread");
      System.out.println(list);
   }
}

