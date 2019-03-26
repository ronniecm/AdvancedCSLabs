/*
 * Name: 
 * Date: 
 * 
 * What I learned:  
   a. 
   b. 
   c. 
   ...
 * How I feel about this lab:  
 * What I wonder: 
 * 
 * Credits: 
 */

 /*****************************************************************************  
 Assignment:  A simple hashing program is given below. Compile 
 and run.  Notice that collisions occur.  You are to implement 
 three different collision schemes, namely, linear probing,quadratic 
 probing and chaining (use the LinkedList class).
 *****************************************************************************/
package Hash;
import java.util.*;

public class Pd2RonnieMohapatraHashingLab
{
	
   public static void main(String[] args){
      
      Scanner keyboard = new Scanner(System.in);  
      System.out.print("Enter the size of the hash table (enter 20):  ");
      int arrayLength = keyboard.nextInt();       // Use 20
      keyboard.nextLine();
      
      System.out.println("Choose a structure. 1 for linear probing, 2 for quadratic probing and 3 for chaining:");
      String collisionDealing = keyboard.nextLine();
      int useProbing = Integer.parseInt(collisionDealing);
      
      // Create a table of Objects or a table of raw linked list using ListNode
      HashTable table = new HashTable(arrayLength, useProbing);
      System.out.println("The hash table: " + table);
      
      System.out.print("\nEnter the number of items(enter 15):  ");
      int numItems = keyboard.nextInt();        
      keyboard.nextLine();
      
      System.out.print("\nThe Load Factor is " + (double)numItems/arrayLength);
      System.out.println();
      
      if(table.LoadFactor((double)numItems/arrayLength))
      {
         System.out.println("The load factor for a linear probe or quadratic probe cannot be greater than 1. Try again.");
         System.exit(0);
      }
      
      //
      System.out.println ("Start entering info into the hash table\n");
      System.out.println ("Enter " + numItems + " values.");
      
      // NOTE: In this lab, we don't have key-value pair. We just 
      // store an int to illustrate how hashing works
      
      // Insert objects in the hash table
      for(int i = 0; i < numItems; i++)
         table.add(keyboard.nextLine());
         
      System.out.println();
      System.out.print("Start searching ==> search for? ");
      String key = keyboard.nextLine();
      
      if(table.contains(key))
         System.out.println(key + " found");
      else
         System.out.println(key + " NOT found");
      
      
      System.out.println(table);
   }  // main
   
}  // HashCode
   
   
class HashTable
{
	
   private int size;             // size of the hash table
   private int useProbing; 
   private Object[] table;
   
   private static int LINEAR_PROBE = 1;
   private static int QUADRATIC_PROBE = 2;
   private static int CHAINING = 3;	
   
   // pre-condition:
   // post-condition:
   public HashTable(int numSlots, int useProbing)
   {
        size = numSlots;
        this.useProbing = useProbing;
   }//constructor
	
   
   // pre-condition:
   // post-condition:
   public boolean LoadFactor(double loadfactor)
   {
      if((useProbing==LINEAR_PROBE || useProbing == QUADRATIC_PROBE)&&(loadfactor>1))
         return true;
      return false;
   }
   
   // pre-condition:
   // post-condition:
   public void add(Object obj)
   {
      int code = Math.abs(obj.hashCode());
      System.out.println("Hash Code: " + code);
      int index = code % size;
      // has an empty slot
      if(table[index] == null)
      {
            table[index] = obj;
      }
      // start probing sequence
      else
      {
         if(useProbing==LINEAR_PROBE)
         {   /*Linear Probing*/
         
            // call linearProbe() to find index of the next available space
            // put the object in the table using the index
            index = linearProbe(index) % size;
            table[index] = obj;
            // add the object at location "index"
         }   
         
         
         else if(useProbing== QUADRATIC_PROBE)
         {
            // call quadraticProbe() to find index of the next available space
            // store the object in the table using the index         
            index = quadraticProbe(index, 1) % size;
            table[index] = obj;
         }
         else
         {    /*Chaining*/
              // call the chaining method
            
         }
      }
      
   }//add
   
   
   // returns true if obj can be found in the table
   public boolean contains(Object obj)
   {
   	
      int code = Math.abs(obj.hashCode());  // Question: Which hashCode function are we using here?
                                            //           The one in the Integer class? How does it work?
      int index = code % size;
   	
      if(useProbing==LINEAR_PROBE)
      {

      }
      else if(useProbing== QUADRATIC_PROBE)
      {
      
      }
      else  // chaining
      {
      
      }
      return false;
   }//contains
   
 /*  implement collision resolution methods one at a time */
 /*  implement this method recursively */
   public int linearProbe(int index)
   {
      if(table[index] == null)
            return index;

      return linearProbe((index + 1) % size);
   }//linearProbe
   
   /*  implement this method recursively */
   public int quadraticProbe(int index, int n)
   {
      if(table[index] != null)
            return index;

      return quadraticProbe((index + n * n) % size, n+1);
   } // quadraticProbe

   // Note: each table element is a singly linked list of Objects
   public Object chaining(int index, Object obj)
   {
      if(table[index] == null)
      {
            table[index] = new LinkedList<Object>();
      }
    
      LinkedList<Object> ll = (LinkedList<Object>)table[index];
      ll.addLast(obj);
      return ll;
   }//chaining
     
   public String toString()
   {
      String result = "{ ";
      if(useProbing== LINEAR_PROBE||useProbing== QUADRATIC_PROBE)
      {
          for(int i = 0; i < table.length; i++)
            result += table[i].toString() + ", ";
      }
      else  // table of linked lists
      {
          for(int i = 0; i < table.length; i++)
          {
              result += table[i].toString() + "\n";
          }
      }
      result +="}";
      return result;
   } //toString

}  // HashTable
