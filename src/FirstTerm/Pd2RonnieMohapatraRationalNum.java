/*
 * Name: Ronnie Mohapatra
 * Lab: RationalNum
 * Date: September 8, 2018
 * What I Learned: a.) I learned a new and improved way to find the greatest common factor between
 * 						two numbers using a recursive method and the mod operator.
 * 				   b.) I also found a new way to compute the least common multiple between two numbers using
 * 						the greatest common factor and the product of the two numbers. It is num1 * num2 / GCD(num1, num2).
 */
package FirstTerm;

public class Pd2RonnieMohapatraRationalNum {
	private int numerator, denominator;
	
	/*
	 * pre-condition: denom > 0
	 * post-condition: a simplified rational number has constructed
	 * 					numerator = numer and denominator = denom
	 */
	public Pd2RonnieMohapatraRationalNum(int numer, int denom)
	{
		numerator = numer;
		denominator = denom;
		simplify(); //simplifies the fraction completely
	}
	
	public int getNumerator()
	{
		return numerator;
	}
	
	public int getDenominator()
	{
		return denominator;
	}
	/*
	 * pre-condition: none
	 * post-condition: instance variables of this RationalNum equal the instance variables of other
	 */
	public Pd2RonnieMohapatraRationalNum(Pd2RonnieMohapatraRationalNum copy)
	{
		numerator = copy.numerator;
		denominator = copy.denominator;
	}
	
	/*
	 * pre-condition: none
	 * post-condition: denominator = denom
	 */
	public void setDenom(int denom)
	{
		if(denom == 0)
			System.out.println("Cannot set denominator to 0");
		else
			this.denominator = denom;
		
		simplify();
	}
	
	/*
	 * pre-condition: numerator and denominator have a value
	 * post-condition: numerator and denominator are divded by their greatest common factor
	 */
	private void simplify()
	{
		int gcd = GCD(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
	}
	
	/*
	 * pre-condition: none
	 * post-condition: new RationalNum of the sum between this and other returned
	 */
	public Pd2RonnieMohapatraRationalNum add(Pd2RonnieMohapatraRationalNum other)
	{
		int lcm = findLCM(this.denominator, other.denominator); 
		int newNumerator = (this.numerator * (lcm / this.denominator)) + (other.numerator * (lcm / other.denominator));
		return new Pd2RonnieMohapatraRationalNum(newNumerator, lcm);	
	}
	
	/*
	 * pre-condition: none
	 * post-condition: new RationalNum of the difference between this and other returned
	 */
	
	public Pd2RonnieMohapatraRationalNum subtract(Pd2RonnieMohapatraRationalNum other)
	{
		int lcm = findLCM(this.denominator, other.denominator);
		int newNumerator = (this.numerator * (lcm / this.denominator)) - (other.numerator * (lcm / other.denominator));
	
		return new Pd2RonnieMohapatraRationalNum(newNumerator, lcm);
	}
	
	/*
	 * pre-condition: none
	 * post-condition: new RationalNum of the product between this and other returned
	 */
	public Pd2RonnieMohapatraRationalNum multiply(Pd2RonnieMohapatraRationalNum other)
	{
		return new Pd2RonnieMohapatraRationalNum(this.numerator * other.numerator, this.denominator * other.denominator);
	}
	
	/*
	 * pre-condition: none
	 * post-condition: new RationalNum of the quotient between this and other returned
	 */
	public Pd2RonnieMohapatraRationalNum divide(Pd2RonnieMohapatraRationalNum other)
	{
		return new Pd2RonnieMohapatraRationalNum(this.numerator * other.denominator, this.denominator * other.numerator);
	}
	
	/*
	 * pre-condition: other is of type RationalNum
	 * post-condition: returns true if numerator and denominator of this is equal to the numerator and denominator of other
	 */
	public boolean equals(Object other)
	{
		Pd2RonnieMohapatraRationalNum n = (Pd2RonnieMohapatraRationalNum)other;
		if(this.numerator == n.getNumerator() && this.denominator == n.getDenominator()) //compares numerator and denominator
			return true;
		else
			return false;
	}
	
	/*
	 * pre-condition: none
	 * post-condition: RationalNum printed out in "numerator / denominator" format
	 */
	public String toString()
	{
		return this.numerator + "/" + this.denominator;	
	}
	
	/*
	 * pre-condition: num >= 0 and denom > 0
	 * post-condition: greatest common factor between num and denom is returned
	 */
	private int GCD(int num, int denom)
	{
		if(denom == 0)
			return num;
		return GCD(denom, num % denom); //if denom != 0, then keep rerunning method with denom and num % denom as parameters
	}
	
	/*
	 * pre-condition: num1 >= 0 and num2 > 0
	 * post-condition: least common multiple between num1 and num2 is found
	 */
	private int findLCM(int num1, int num2)
	{
		return num1 * num2 / GCD(num1, num2); //formula for LCM: LCM = num1 * num2 / GCD(num1, num2)
	} 
	
	public static void main(String [] args) 
	{
		Pd2RonnieMohapatraRationalNum r1 = new Pd2RonnieMohapatraRationalNum(52, 36); 
		System.out.println("r1 = " + r1); 
		Pd2RonnieMohapatraRationalNum r2 = new Pd2RonnieMohapatraRationalNum(7, 39); 
		System.out.println("r2 = " + r2 + "\n");
		r2.setDenom(0);
		System.out.println("r2 = " + r2 + "\n"); 
		r2.setDenom(14);
		System.out.println("r2 = " + r2 + "\n"); 
		System.out.println("r1 + r2: " + r1.add(r2)); 
		System.out.println("r1 - r2: " + r1.subtract(r2));
		System.out.println("r1 * r2: " + r1.multiply(r2)); 
		System.out.println("r1 / r2: " + r1.divide(r2)); 
		System.out.println("r1 equals r2: " + r1.equals(r2));
	}
	
	/* Output
	 * r1 = 13/9
	   r2 = 7/39

	  	Cannot set denominator to 0
		r2 = 7/39
	
		r2 = 1/2

		r1 + r2: 35/18
		r1 - r2: 17/18
		r1 * r2: 13/18
		r1 / r2: 26/9
		r1 equals r2: false

	 */
}
