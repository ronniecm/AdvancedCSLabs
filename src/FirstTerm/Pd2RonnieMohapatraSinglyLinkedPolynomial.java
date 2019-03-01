package FirstTerm;

/**
Name: Ronnie Mohapatra
Due:  September 30, 2018
Date Submitted: 
What I learned: a) I learned a more practical use of singly linked list.
				b) I learned how to manipulate singly linked lists of user defined objects.
How I feel about this lab: I feel like I have acquired a very important skill as a programmer. Learning to represent polynomials
						   with singly linked lists
What I wonder: I wonder how to work with doubly linked and circularly linked lists.
Credits (person/websites):  
Students I helped:
**/

import java.lang.Math;

public class Pd2RonnieMohapatraSinglyLinkedPolynomial implements Pd2RonnieMohapatraPolynomial
{      
   private class ListNode<Term>
   {  
      private Term value;
      private ListNode<Term> next;
   
      public ListNode(Term t, ListNode<Term> n)
      {
         value = t;
         next = n;
      }
      
      public Term getValue()
      {
         return value;
      }
      
      public ListNode<Term> getNext()
      {
         return next;
      }
      
      public void setValue(Term newTerm)
      {
         value = newTerm;
      }
      
      public void setNext(ListNode<Term> n)
      {
         next = n;
      }
   }  //end of ListNode<Term>  
   
   private static class Term
   {
      private int exponent;
      private double coeff;
      
      public Term(double c, int e)
      {
         exponent = e;
         coeff = c;
      }
   
      public int getExponent()
      {
         return exponent;
      }
     
      public double getCoefficient()
      {
         return coeff;
      }
     
      public void setExponent(int x)
      {
         exponent = x;
      }
     
      public void setCoeff(double x)
      {
         coeff = x;
      }
   
      public boolean equals(Term other)
      {
         return this.coeff == other.getCoefficient() && this.exponent == other.getExponent();
      }
   
      public String toString()
      {
         return coeff + "x^" + exponent;
      }
   }  // Term 

   
   private ListNode<Term> head;
   
   public Pd2RonnieMohapatraSinglyLinkedPolynomial(double[] terms)
   {
	   ListNode<Term> currentTerm = null;
	   
	   for(int i = 0; i < terms.length; i+=2)
	   {
		   double coeff = terms[i];
		   int exp = (int)terms[i+1];
		   
		   ListNode<Term> term = new ListNode<Term>(new Term(coeff, exp), null);
		   if(i == 0)
		   {
			   head = term;
			   currentTerm = head;
		   }
		   else
		   {
			   currentTerm.setNext(term);
			   currentTerm = currentTerm.getNext();
		   }
	   }
   }
   
   public Pd2RonnieMohapatraSinglyLinkedPolynomial(ListNode<Term> x)
   {
	   head = x;
   }
   
   //copy constructor
   public Pd2RonnieMohapatraSinglyLinkedPolynomial(Pd2RonnieMohapatraPolynomial p)
   {     
	   ListNode<Term> currentTerm = null;
	   
	   for(int i = p.getDegree(); i >= 0; i--)
	   {
		   double coeff = p.getCoefficient(i);
		   int exp = i;
		   
		   ListNode<Term> term = new ListNode<Term>(new Term(coeff, exp), null);
		   
		   if(i == p.getDegree())
		   {
			   head = term;
			   currentTerm = head;
		   }
		   else
		   {
			   currentTerm.setNext(term);
			   currentTerm = currentTerm.getNext();
		   }
	   }
   }
   
   public ListNode<Term> getHead()
   {
      return head;
   }
   
   public void setHead(ListNode <Term> node)
   {
	   head = node;
   }
   
   public int getDegree()
   {
	   for(ListNode<Term> temp = head; temp != null; temp = temp.getNext())
	   {
		   if(temp.getValue().getCoefficient() != 0.0)
			   return temp.getValue().getExponent();
	   }
	   
	   return 0;
   }
   
   public double getCoefficient(int exponent)
   {
	   for(ListNode<Term> temp = head; temp != null; temp = temp.getNext())
	   {
		   if(temp.getValue().getExponent() == exponent)
			   return temp.getValue().getCoefficient();
	   }
	   
	   return 0.0;
   }
   
   /**
   pre-condition: this is not null
   post-condition: returns a double that is the answer if parameter x is plugged in as x
                   in the polynomial
   **/
   public double evaluate(double x)
   {    
      double answer = 0;
      
      for(ListNode<Term> temp = head; temp != null; temp = temp.getNext())
    	  answer += temp.getValue().getCoefficient() * Math.pow(x, temp.getValue().getExponent());
      
      return answer;
   }
   
   /**
   pre-condition: this and other are not null
   post-condition: returns a SinglyLinkedPolynomial that is the sum of this and other and
                   is in highest to lowest exponent order
   **/   
   public Pd2RonnieMohapatraPolynomial add(Pd2RonnieMohapatraPolynomial other)
   {
	  ListNode<Term> sum = null;
	  
	  int endExp = this.getDegree() > other.getDegree() ? this.getDegree() : other.getDegree();
	  	  
	  for(int i = 0; i <= endExp; i++)
	  {
		  double coeff = this.getCoefficient(i) + other.getCoefficient(i);		  
		  sum = new ListNode<Term>(new Term(coeff, i), sum);
	  }
	  
	  return new Pd2RonnieMohapatraSinglyLinkedPolynomial(sum);
   }
   
   /**
   pre-condition: this and other are not null
   post-condition: returns a SinglyLinkedPolynomial that is the difference of this and other and is
                   in highest to lowest exponent order
   **/   
   public Pd2RonnieMohapatraPolynomial subtract(Pd2RonnieMohapatraPolynomial other)
   {
      ListNode<Term> diff = null;
	  int endExp = this.getDegree() > other.getDegree() ? this.getDegree() : other.getDegree();    
      for(int i = 0; i <= endExp; i++)
      {
    	  double coeff = this.getCoefficient(i) - other.getCoefficient(i);
    	  diff = new ListNode<Term>(new Term(coeff, i), diff);
      }
      return new Pd2RonnieMohapatraSinglyLinkedPolynomial(diff);
   }
   
   /**	
   pre-condition: this is not null
   post-condition: returns a SinglyLinkedPolynomial that is the derivative of this and is
                   in highest to lowest exponent order
   **/
   public Pd2RonnieMohapatraPolynomial findDerivative()
   {
	   ListNode<Term> deriv = null;
	   
	   for(int i = 0; i <= this.getDegree(); i++)
	   {
		   Term term = new Term(this.getCoefficient(i) * i, i - 1);
		   
		   if(term.getExponent() != -1)
			   deriv = new ListNode<Term>(term, deriv);
	   }
	   
	   return new Pd2RonnieMohapatraSinglyLinkedPolynomial(deriv);
   }

   /**
   pre-condition: this and other are not null
   post-condition: return a new SinglyLinkedPolynomial in highest to lowest degree order
                   that is the product of this and other
   **/   
   public Pd2RonnieMohapatraPolynomial multiply(Pd2RonnieMohapatraPolynomial other)
   {
	  Pd2RonnieMohapatraPolynomial result = null;
	  ListNode<Term> prod = null;
	  
	  for(ListNode<Term> temp = head; temp != null; temp = temp.getNext())
	  {
		  prod = null;
		  for(int i = 0; i <= other.getDegree(); i++)
		  {
			 double coeff = temp.getValue().getCoefficient() * other.getCoefficient(i);
			 int exp = temp.getValue().getExponent() + i;
			 
			 prod = new ListNode<Term>(new Term(coeff, exp), prod);
		  }
		  
		  result = temp == head ? new Pd2RonnieMohapatraSinglyLinkedPolynomial(prod) :  result.add(new Pd2RonnieMohapatraSinglyLinkedPolynomial(prod));
	  }
	  
	  return result;
   }
   
   /**
   pre-condition: head and node aren't null
   post-condition: node is removed from the list passed
   **/
   public void remove(ListNode<Term> head, ListNode<Term> node)
   {
	   if(head == node)
		   head.setNext(head.getNext().getNext());
	   
	   remove(head.getNext(), node);
   }
   
   /**
   pre-condition: head is not null
   post-condition: return head of the reversed list
   **/   
   public ListNode<Term> reverse(ListNode<Term> head)
   {
      if(head.getNext() == null)
    	  return head;
      
      ListNode<Term> ret = reverse(head.getNext());
      head.getNext().setNext(head);
      head.setNext(null);
      return ret;
   } 
   
   @Override public boolean equals(Object other)
   {
      Pd2RonnieMohapatraPolynomial p = (Pd2RonnieMohapatraSinglyLinkedPolynomial)other;
     
      if(this.getDegree() != p.getDegree())
    	  return false;
      
      for(int i = 0; i <= this.getDegree(); i++)
      {
    	  if(this.getCoefficient(i) != p.getCoefficient(i))
    		  return false;
      }
      
      return true;
   }
   
   public String toString()
   {
      String result = head.getValue().toString();
      
      for(ListNode<Term> temp = head.getNext(); temp != null; temp = temp.getNext())
      {
    	  if(temp.getValue().getCoefficient() == 0.0)
    		  temp = temp.getNext();
    	  
    	  if(temp.getValue().getCoefficient() > 0 && !result.equals(""))
    		  result += " + ";
    	  else
    		  result += " - ";
    	  
    	  if(temp.getValue().getExponent() == 0)
    		  result += Math.abs(temp.getValue().getCoefficient());
    	  else if(temp.getValue().getCoefficient() < 0)
    		  result += Math.abs(temp.getValue().getCoefficient()) + "x^" + temp.getValue().getExponent();
    	  else
    		  result += temp.getValue();
      }
      
      return result;
   }
   public static void main(String[] args)  
   {
      double[] arr = {4,3,3,2,1,0};
      Pd2RonnieMohapatraPolynomial p1 = new Pd2RonnieMohapatraSinglyLinkedPolynomial(arr);  // 4x^3 + 3x^2 + 1
      System.out.println("p1(x) =     " + p1);

      double[] arr2 = {-5,1,-2,0};
      Pd2RonnieMohapatraPolynomial p2 = new Pd2RonnieMohapatraSinglyLinkedPolynomial(arr2);   // - 5x – 2
      System.out.println("p2(x) =     " + p2);                 // p2(x) = - 5x^1 – 2
      System.out.println("p1 and p2 are the same: " + p1.equals(p2));
      
      double[] arr3 = {-4,1};
      Pd2RonnieMohapatraPolynomial p3   = new Pd2RonnieMohapatraSinglyLinkedPolynomial(arr3);  // coeff, exp = -4x
      System.out.println("p3(x) =     " + p3);
   
      //Pd2RonnieMohapatraPolynomial p    = p1.add(p2);   // 4x^3 + 3x^2 - 10x – 3
      Pd2RonnieMohapatraPolynomial p    = p1.add(p2).add(p2);   // 4x^3 + 3x^2 - 10x – 3
      System.out.println("p(x)  =     " + p);       // p(x) = 4x^3 + 3x^2 - 10x^1 – 3
   
      Pd2RonnieMohapatraPolynomial p4   = p.subtract(p3);   // 4x^3 + 3x^2 - 6x^1 – 3   <====
      System.out.println("p4(x) =     " + p4);
   
      Pd2RonnieMohapatraPolynomial p5   = p4.findDerivative();   // 12x^2 + 6x^1 - 6   <====
      System.out.println("p5(x) =     " + p5);
            
      Pd2RonnieMohapatraPolynomial clone = new Pd2RonnieMohapatraSinglyLinkedPolynomial(p5);
      System.out.println("clone(x) =     " + clone);
      System.out.println("p5 and clone are the same: " + p5.equals(clone));
      
      Pd2RonnieMohapatraPolynomial clone2 = p5;
      System.out.println("clone2(x) =    " + clone2);
      System.out.println("p5 and clone 2 are the same: " + p5.equals(clone2));
      
      Pd2RonnieMohapatraPolynomial product = p1.multiply(p2);
      System.out.println("product of p1(x) and p2(x) is     " + product);
      
      System.out.println ("p5(0) = " + p5.evaluate(0));
      System.out.println ("p5(1) = " + p5.evaluate(1));
   }
}  //end of SinglyLinkedPolynomial