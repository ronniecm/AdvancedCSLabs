package Stack;

/*****************************************************************************************************************
NAME: Ronnie Mohapatra
PERIOD: 2
DUE DATE: December 8, 2018 
ASSIGNMENT: Iterator Lab

PURPOSE: To traverse an ArrayList using an Iterator object   

LEARNED: a). I learned a different method of iterating through lists.
  		 b). Use ListIterator to make edits to the list while you iterate throught the list
            
CREDITS: 

****************************************************************************************************************/

 // NOTE:  use for-each loops or iterators, not regular for-loops
   import java.io.*;
   import java.util.*;
    public class Pd2RonnieMohapatraIteratorLab
   {
       public static void main(String[] args)
      {
         System.out.println("Iterator Lab\n");
         int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
         for(int n : rawNumbers )
            System.out.print(n + " ");    
         ArrayList<Integer> numbers = createNumbers(rawNumbers);
         System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
         System.out.println("Count negative numbers: " + countNeg(numbers));
         System.out.println("Average: " + average(numbers));
         System.out.println("Replace negative numbers: " + replaceNeg(numbers));
         System.out.println("Delete zeros: " + deleteZero(numbers));
         String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
         ArrayList<String> movies = createMovies(rawMovies);
         System.out.println("Movies: " + movies);
         System.out.println("Movies: " +  removeDupes(movies));
      }
      // pre: an array of just int values 
   	// post: return an ArrayList containing all the values
       public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
      {
    	   ArrayList<Integer> list = new ArrayList<Integer>();
    	   for(int num : rawNumbers)
    	   {
    		   list.add(num);
    	   }
    	   
    	   return list;
      }
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
       public static ArrayList<String> createMovies(String[] rawWords) 
      {
    	   ArrayList<String> list = new ArrayList<String>();
    	   for(String word : rawWords)
    		   list.add(word);
    	   
    	   return list;
      }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
       public static int countNeg(ArrayList<Integer> a)
      {
         int count = 0;
         Iterator<Integer> iterator = a.iterator();
         while(iterator.hasNext())
         {
        	 if(iterator.next() < 0)
        		 count++;
         }
         
         return count;
      }
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
       public static double average(ArrayList<Integer> a)
      {
    	   int sum = 0;
    	   Iterator<Integer> iterator = a.iterator();
    	   
    	   while(iterator.hasNext())
    		   sum += iterator.next();
    	   
    	   return (double)sum/a.size();
      }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
       public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
      {
    	   ListIterator<Integer> listIterator = a.listIterator();
    	   
    	   while(listIterator.hasNext())
    	   {
    		   if(listIterator.next() < 0)
    			   listIterator.set(0);
    	   }
    	   
    	   return a;
      }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
       public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
      {
    	   ListIterator<Integer> listIterator = a.listIterator();
    	   while(listIterator.hasNext())
    	   {
    		   if(listIterator.next() == 0)
    			   listIterator.remove();
    	   }
    	   
    	   return a;
      }
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return ArrayList without duplicate movie titles
		// strategy: start with an empty array and add movies as needed
      public static ArrayList<String> removeDupes(ArrayList<String> a)
      {
         ArrayList<String> noDupes = new ArrayList<String>();
         Iterator<String> dupesIterator = a.iterator();
         //Iterator<String> noDupesIterator = noDupes.iterator();
         
         while(dupesIterator.hasNext())
         {
        	 String title = dupesIterator.next();
        	 
        	 boolean isDuplicate = false;
        	 
        	 for(String word : noDupes)
        		 if(word.equals(title))
        			 isDuplicate = true;
        	 
        	 if(!isDuplicate)
        		 noDupes.add(title);
         }
         
         return noDupes;
      }
   
   }
    
 /* Output

Iterator Lab

-9 4 2 5 -10 6 -4 24 20 -28 ArrayList: [-9, 4, 2, 5, -10, 6, -4, 24, 20, -28]
Count negative numbers: 4
Average: 1.0
Replace negative numbers: [0, 4, 2, 5, 0, 6, 0, 24, 20, 0]
Delete zeros: [4, 2, 5, 6, 24, 20]
Movies: [High_Noon, High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No, Dr_No, Mary_Poppins, High_Noon, Tron]
Movies: [High_Noon, Star_Wars, Tron, Mary_Poppins, Dr_No]

*/
