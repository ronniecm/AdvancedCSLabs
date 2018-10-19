/*****************************************************************************************************************
* Name: Ronnie Mohapatra                     
* Period: 2
* Name of the Lab: Recursion Lab
* Purpose of the Program: Recursion Practice
* Due Date: October 12, 2018
* Date Submitted: 
* What I learned: a) I learned how to order the statements in a recursive method
* 				  b) You can type cast from int to char and vice versa
* How I feel about this lab: I feel that recursion is very easy now.
* What I wonder:  What are some popular algorithms that use recursion?
* Credits:  
* Students whom I helped (to what extent):
*****************************************************************************************************************/

package FirstTerm;
import java.util.Scanner;   

public class Pd2RonnieMohapatraRecursionLab
{
   //Pre: c is a lower case letter - Post: all lower case letters a-char c are printed 
   public static void letters(char c)
   {
	   if(c == 'a')
	   {
		   System.out.print(c);
		   return;
	   }
	   
	   
	   letters((char)(c-1));
	   System.out.print(c);
   }
	//Pre: none - Post: returns number of times two can go into x
   public static int twos(int x)
   {
      if(x % 2 != 0)
    	  return 0;
      
      return 1 + twos(x / 2);
   }
	//Pre: none - Post: returns if x is a power of 3
   public static boolean powerof3(int x)
   {
	   if(x % 3 != 0 && x > 1)
		   return false;
	   if(x == 1)
		   return true;
	   
	   return powerof3(x / 3);
   }
	//Pre: none - Post: returns String of x reversed 
   public static String reverse(long x)
   {
      if(x < 10)
    	  return Long.toString(x);
      
      return Long.toString(x % 10) + reverse(x / 10);
   }
	//Pre: x > 0 - Post: Prints x in base 5
   public static void base5(int x)
   {
	   if(x < 5)
	   {
		   System.out.print(x);
		   return;
	   }
	   
	   base5(x / 5);
	   System.out.print(x % 5);
   }
	// Pre: x > 0 - Post: Prints x with commas
   public static void printWithCommas(long x)
   {
	   if(x / 1000 == 0)
	   {
		   System.out.print(x);
		   return;
	   }
	   
	   
	   printWithCommas(x / 1000);
	   System.out.print("," + x % 1000);
   }
	
	
   public static void main(String []args)
   {
      Scanner scan = new Scanner (System.in);
      int choice;
      do
      {
         System.out.println("\n\n1)Letters"+
                           "\n2)Twos"+
                           "\n3)Power Of 3"+
                           "\n4)Reverse"+
                           "\n5)Base 5"+
                           "\n6)Print With Commas"+
                           "\n7)Exit");
         choice = scan.nextInt();
         if (choice == 1)
         {
            System.out.println("Enter a letter");
            char charA = scan.next().charAt(0);
            if (charA < 'a' || charA > 'z')
               System.out.println("That letter not valid");
            else
               letters(charA);
         }
         else if (choice == 2)
         {
            System.out.println("Enter a number");
            System.out.println(twos(scan.nextInt()));
         }
         else if (choice == 3)
         {
            System.out.println("Enter a number");
            System.out.println(powerof3(scan.nextInt()));
         }
         else if (choice == 4)
         {
            System.out.println("Enter a number");
            System.out.println(reverse(scan.nextLong()));
         }
         else if (choice == 5)
         {
            System.out.println("Enter a number");
            int number = scan.nextInt();
            if (number > 0)
               base5(number);
            else
               System.out.println("That number is not valid");
         }
         else if (choice == 6)
         {
            System.out.println("Enter a number");
            long number = scan.nextLong();
            if (number > 0)
               printWithCommas(number);
            else
               System.out.println("That number is not valid");
         }
      }while(choice != 7);
   }
}

/* Output
1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
1
Enter a letter
z
abcdefghijklmnopqrstuvwxyz

1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
2
Enter a number
6
1


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
2
Enter a number
12
2


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
2
Enter a number
7
0


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
3
Enter a number
9
true


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
3
Enter a number
10
false


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
4
Enter a number
236245
542632


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
4
Enter a number
5
5


1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
5
Enter a number
14
24

1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
6
Enter a number
234154326243
234,154,326,243

1)Letters
2)Twos
3)Power Of 3
4)Reverse
5)Base 5
6)Print With Commas
7)Exit
7
*/