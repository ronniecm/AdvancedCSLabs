package FirstTerm;

public class RationalNum {
	private int numerator, denominator;
	
	public RationalNum(int numer, int denom)
	{
		numerator = numer;
		denominator = denom;
	}
	
	public RationalNum(RationalNum copy)
	{
		numerator = copy.numerator;
		denominator = copy.denominator;
	}
	
	public void setDenom(int denom)
	{
		if(denom == 0)
			System.out.println("Cannot set denominator to 0");;
		
			this.denominator = denom;
	}
	
	public static RationalNum simplify(RationalNum param)
	{
		int gcd = findGCD(param.numerator, param.denominator);
		return new RationalNum(param.numerator/gcd, param.denominator/gcd);
	}
	
	public RationalNum add(RationalNum other)
	{
		int lcm = findLCM(this.denominator, other.denominator);
		int newNumerator = (this.numerator * (lcm / this.denominator)) + (other.numerator * (lcm / other.denominator));
	
		return simplify(new RationalNum(newNumerator, lcm));
	}
	
	public RationalNum subtract(RationalNum other)
	{
		int lcm = findLCM(this.denominator, other.denominator);
		int newNumerator = (this.numerator * (lcm / this.denominator)) - (other.numerator * (lcm / other.denominator));
	
		return simplify(new RationalNum(newNumerator, lcm));
	}
	
	public RationalNum multiply(RationalNum other)
	{
		return simplify(new RationalNum(this.numerator * other.numerator, this.denominator * other.denominator));
	}
	
	public RationalNum divide(RationalNum other)
	{
		return simplify(new RationalNum(this.numerator * other.denominator, this.denominator * other.numerator));
	}
	
	public boolean equals(RationalNum other)
	{
		RationalNum r1 = simplify(this);
		RationalNum r2 = simplify(other);
		
		if(r1.numerator == r2.numerator && r1.denominator == r2.denominator)
			return true;
		else
			return false;
	}
	
	public String toString()
	{
		return this.numerator + "/" + this.denominator;	
	}
	
	public static int findGCD(int num1, int num2)
	{
		while(num2 > 0)
		{
			int temp = num2;
			num2 = num1 % num2;
			num1 = temp;
		}
		
		return num1;
	}
	
	public static int findLCM(int num1, int num2)
	{
		return (num1 * num2) / findGCD(num1, num2);
	}
}
