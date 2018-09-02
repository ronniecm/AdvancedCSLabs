package FirstTerm;

public class RationalNum {
	private int numerator, denominator;
	
	public RationalNum(int numer, int denom)
	{
		numerator = numer;
		denominator = denom;
		simplify();
	}
	
	public RationalNum(RationalNum copy)
	{
		numerator = copy.numerator;
		denominator = copy.denominator;
	}
	
	public void setDenom(int denom)
	{
		if(denom == 0)
			System.out.println("Cannot set denominator to 0");
		else
			this.denominator = denom;
		
		simplify();
	}
	
	private void simplify()
	{
		int gcd = findGCD(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
	}
	
	public RationalNum add(RationalNum other)
	{
		int lcm = findLCM(this.denominator, other.denominator);
		int newNumerator = (this.numerator * (lcm / this.denominator)) + (other.numerator * (lcm / other.denominator));
		return new RationalNum(newNumerator, lcm);	
	}
	
	public RationalNum subtract(RationalNum other)
	{
		int lcm = findLCM(this.denominator, other.denominator);
		int newNumerator = (this.numerator * (lcm / this.denominator)) - (other.numerator * (lcm / other.denominator));
	
		return new RationalNum(newNumerator, lcm);
	}
	
	public RationalNum multiply(RationalNum other)
	{
		return new RationalNum(this.numerator * other.numerator, this.denominator * other.denominator);
	}
	
	public RationalNum divide(RationalNum other)
	{
		return new RationalNum(this.numerator * other.denominator, this.denominator * other.numerator);
	}
	
	public boolean equals(RationalNum other)
	{
		if(this.numerator == other.numerator && this.denominator == other.denominator)
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		return this.numerator + "/" + this.denominator;	
	}
	
	private int findGCD(int num1, int num2)
	{
		while(num2 > 0)
		{
			int temp = num2;
			num2 = num1 % num2;
			num1 = temp;
		}
		
		return num1;
	}
	
	private int findLCM(int num1, int num2)
	{
		return (num1 * num2) / findGCD(num1, num2);
	}
}
