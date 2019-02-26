package Stack;

import java.io.*;
import java.util.*;

public class Pd2RonnieMohapatraPrintQueueLab
{
   private static Scanner in;
   private static Queue<String> q;
   private static int jobNumber =100;
   
   public static void main(String[] args) throws Exception
   {
      q = new LinkedList<String>();
      in = new Scanner(System.in);
      String prompt = "Add, Print, Delete, eXit:  ";
      System.out.print(prompt);
      String str = in.next();
      while(!str.equals("X"))
      {
         if(str.equals("A"))
            add();
         else if(str.equals("P"))
            printJob();  
         else if(str.equals("D"))
            delete();
         else
            System.out.println("   invalid command");
         printQueue();
         System.out.print(prompt);
         str = in.next();
      }
      in.close();
   }
   
   public static void add()
   {
	   q.add(jobNumber++ + "");
   }
   public static void printJob()
   {
   }
			
   public static void delete()
   {
	   System.out.print("Enter job number: ");
	   int num = in.nextInt();
	                    
   }
   public static void printQueue()
   {
   }
}