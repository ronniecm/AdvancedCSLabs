package FirstTerm;

public class ArrayBasedPoly implements Polynomial {
	private double[] c;
	
	public ArrayBasedPoly(double[] coefficients)
	{
		c = coefficients;
	}
	
	public ArrayBasedPoly(double coefficient, int exponent)
	{
		c = new double[exponent + 1];
		for(int i = 0; i < c.length; i++) {
			if(i == exponent)
				c[i] = coefficient;
			else
				c[i] = 0;
		}
	}
	 
	public ArrayBasedPoly(Polynomial copy)
	{
		c = new double[copy.getDegree() + 1];
		for(int i = 0; i < c.length; i++)
		{
			c[i] = copy.getCoefficient(i);
		}
	}
	
	public double getCoefficient(int exponent)
	{
		return c[exponent];
	}
	
	public int getDegree()
	{
		return c.length - 1;
	}
	
	public double evaluate(int x)
	{
		double answer = 0;
		
		for(int i = 0; i < c.length; i++)
		{
			if(i == 0)
				answer += c[0];
			else
				answer += this.getCoefficient(i) * Math.pow(x, i);
		}
		
		return answer;
	}
	
	public Polynomial add(Polynomial other)
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
		
		return new ArrayBasedPoly(c2);
	}
	
	public Polynomial subtract(Polynomial other)
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
		
		return new ArrayBasedPoly(c2);
	}
	
	public Polynomial findDerivative()
	{
		double[] c2 = new double[c.length - 1];
		
		for(int i = 0; i < c2.length; i++) {
			c2[i] = (i+1) * c[i+1];
		}
		
		return new ArrayBasedPoly(c2);
	}
	
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
	
	
	
	public static void main(String[] args)
	{
		double [] c = {1, 0, 3, 4};
		double[] c2 = {3, 1, 2};
		Polynomial p = new ArrayBasedPoly(c);
		Polynomial p2 = new ArrayBasedPoly(c2);
		Polynomial p3 = p.add(p2);
		Polynomial p4 = p.subtract(p2);
		Polynomial p5 = p.findDerivative();
		
		System.out.println("p(x) = " + p);
		System.out.println("p2(x) = " + p2);
		System.out.println("p3(x) = " + p3);
		System.out.println("p4(x) = " + p4);
		System.out.println("p5(x) = " + p5);
	}	
}
