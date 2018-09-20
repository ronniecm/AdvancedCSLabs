/*
 * Name: Ronnie Mohapatra
 * Lab: Polynomial Lab
 * Date: September 8th, 2018
 * What I Learned: a.) I gained a whole new perspective on how interfaces can be used. I now realize
 * 						that they can be used kind of as an overlap between several different classes.
 * 				   b.) You must test your program several times before finishing. Otherwise, you may not have
 * 						the proper results.
 */
package FirstTerm;

public class Pd2RonnieMohapatraArrayBasedPoly implements Pd2RonnieMohapatraPolynomial {
	private double[] c;
	
	/*
	 * pre-condition: none
	 * post-condition: coefficients is copied to c
	 */
	public Pd2RonnieMohapatraArrayBasedPoly(double[] coefficients)
	{
		c = coefficients;
	}
	
	/*
	 * pre-condition: exponent > 0
	 * post-condition: c is filled with zeros excpet for the index-based degree
	 */
	public Pd2RonnieMohapatraArrayBasedPoly(double coefficient, int exponent)
	{
		c = new double[exponent + 1];
		for(int i = 0; i < c.length; i++) {
			if(i == exponent)
				c[i] = coefficient;
			else
				c[i] = 0;
		}
	}
	 
	/*
	 * pre-condition: copy is of type Polynomial
	 * post-condition: attributes of copy are also attributes of this
	 */
	public Pd2RonnieMohapatraArrayBasedPoly(Pd2RonnieMohapatraPolynomial copy)
	{
		c = new double[copy.getDegree() + 1];
		for(int i = 0; i < c.length; i++)
		{
			c[i] = copy.getCoefficient(i);
		}
	}
	
	/*
	 * pre-condition: 0 < exponent < c.length
	 * post-condition: c[exponent] is returned
	 */
	public double getCoefficient(int exponent)
	{
		return c[exponent];
	}
	
	public int getDegree()
	{
		return c.length - 1;
	}
	
	/*
	 * pre-condition: none
	 * post-condition: p(x) is returned
	 */
	public double evaluate(int x)
	{
		double answer = 0;
		
		for(int i = 0; i < c.length; i++)
		{
			answer += this.getCoefficient(i) * Math.pow(x, i);
		}
		
		return answer;
	}
	
	/*
	 * pre-condition: other is of type Polynomial
	 * post-condition: new Polynomial is returned which represents the sum of this and other
	 */
	public Pd2RonnieMohapatraPolynomial add(Pd2RonnieMohapatraPolynomial other)
	{
		double[] c2;
		if(this.getDegree() + 1 > other.getDegree() + 1)
		{
			c2 = new double[this.getDegree() + 1];
			int index = 0;
			while(index <= this.getDegree()) {
				if(index > other.getDegree()) {
					c2[index] = this.getCoefficient(index);
				} else {
					c2[index] = this.getCoefficient(index) + other.getCoefficient(index);
				}
				
				index++;
			}
		} else if(this.getDegree() + 1 < other.getDegree()) {
			c2 = new double[other.getDegree() + 1];
			int index = 0;
			while(index <= other.getDegree()) {
				if(index > this.getDegree())
					c2[index] = other.getCoefficient(index);
				else
					c2[index] = this.getCoefficient(index) + other.getCoefficient(index);
			}
		} else {
			c2 = new double[this.getDegree() + 1];
			for(int i = 0; i <= this.getDegree(); i++)
			{
				c2[i] = this.getCoefficient(i) + other.getCoefficient(i);
			}
		}
		
		return new Pd2RonnieMohapatraArrayBasedPoly(c2);
	}
	
	/*
	 * pre-condition: other is of type Polynomial
	 * post-condition: new Polynomial is returned which represents the difference between this and other
	 */
	public Pd2RonnieMohapatraPolynomial subtract(Pd2RonnieMohapatraPolynomial other)
	{
		double[] c2;
		int index = 0;
		
		if(this.getDegree() + 1 > other.getDegree() + 1) {
			c2 = new double[this.getDegree() + 1];
			while(index <= this.getDegree()) {
				if(index > other.getDegree())
					c2[index] = this.getCoefficient(index);
				else
					c2[index] = this.getCoefficient(index) - other.getCoefficient(index);
				
				index++;
			}
		} else if(this.getDegree() + 1 < other.getDegree() + 1) {
			c2 = new double[other.getDegree() + 1];
			while(index <= other.getDegree()) {
				if(index > this.getDegree())
					c2[index] = -1 * other.getCoefficient(index);
				else
					c2[index] = this.getCoefficient(index) - other.getCoefficient(index);
				index++;
			}
		} else {
			c2 = new double[this.getDegree() + 1];
			for(int i = 0; i <= this.getDegree(); i++) {
				c2[i] = this.getCoefficient(i) - other.getCoefficient(i);
			}
		}
		
		return new Pd2RonnieMohapatraArrayBasedPoly(c2);
	}
	
	/*
	 * pre-condition: none
	 * post-condition: new Polynomial returned which represents the derivative of this
	 */
	public Pd2RonnieMohapatraPolynomial findDerivative()
	{
		double[] c2 = new double[c.length - 1];
		
		for(int i = 0; i < c2.length; i++) {
			c2[i] = (i+1) * c[i+1];
		}
		
		return new Pd2RonnieMohapatraArrayBasedPoly(c2);
	}
	
	public Pd2RonnieMohapatraPolynomial times(double factor)
	{
		double[] c1 = new double[c.length];
		for(int i = 0; i < c1.length; i++)
			c1[i] = factor * c[i];
		
		return new Pd2RonnieMohapatraArrayBasedPoly(c1);
	}
	
	/*
	 * pre-condition: none
	 * post-condition: ArrayBasedPoly printed out with highest degree term first
	 */
	public String toString()
	{
		String result = this.getCoefficient(c.length-1) + "x^" + (c.length-1);
		
		for(int i = c.length - 2; i >= 0; i--)
		{
			if(this.getCoefficient(i) > 0) {
				if(i == 0)
					result += " + " + this.getCoefficient(i);
				else
					result += " + " + this.getCoefficient(i) + "x^" + i;
			} else if(this.getCoefficient(i) < 0) {
				if(i == 0)
					result += " - " + Math.abs(this.getCoefficient(i));
				else
					result += " - " + Math.abs(this.getCoefficient(i)) + "x^" + i;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) { 
	      double [] c = {1, 0, 3, 4};
	      double [] c1 = {-2, -5};
	   
	      Pd2RonnieMohapatraPolynomial p1 = new Pd2RonnieMohapatraArrayBasedPoly(c);  // 4x^3 + 3x^2 + 1
	      System.out.println("p1(x) = " + p1);
	   
	      Pd2RonnieMohapatraPolynomial p2 = new Pd2RonnieMohapatraArrayBasedPoly(c1);   // - 5x – 2
	      System.out.println("p2(x) = " + p2);                 // p2(x) = - 5x^1 – 2
	   
	      Pd2RonnieMohapatraPolynomial p3 = new Pd2RonnieMohapatraArrayBasedPoly(-4, 1);  // coeff = -4, exp = 1
	      System.out.println("p3(x) = " + p3);
	   
	      Pd2RonnieMohapatraPolynomial p = p1.add(p2).add(p2);   // 4x^3 + 3x^2 - 10x – 3
	      System.out.println("p(x) = " + p);       // p(x) = 4x^3 + 3x^2 - 10x^1 – 3
	      
	      Pd2RonnieMohapatraPolynomial p4   = p.subtract(p3);   // 4x^3 + 3x^2 - 6x^1 – 3   <====
	      System.out.println("p4(x) = " + p4);
	   
	   
	      Pd2RonnieMohapatraPolynomial p5   = p4.findDerivative();   // 12x^2 + 6x^1 - 6   <====
	      System.out.println("p5(x) = " + p5);
	      
	      Pd2RonnieMohapatraPolynomial clone = new Pd2RonnieMohapatraArrayBasedPoly(p5);
	      System.out.println("clone(x) = " + clone);
	   
	      System.out.println ("p(5) = " + p5.evaluate(0));
	      System.out.println ("p(1) = " + p5.evaluate(1));
	   }
	
	/* Output
		p1(x) = 4.0x^3 + 3.0x^2 + 1.0
		p2(x) = -5.0x^1 - 2.0
		p3(x) = -4.0x^1
		p(x) = 4.0x^3 + 3.0x^2 - 10.0x^1 - 3.0
		p4(x) = 4.0x^3 + 3.0x^2 - 6.0x^1 - 3.0
		p5(x) = 12.0x^2 + 6.0x^1 - 6.0
		clone(x) = 12.0x^2 + 6.0x^1 - 6.0
		p(5) = -6.0
		p(1) = 12.0
	 */
}
