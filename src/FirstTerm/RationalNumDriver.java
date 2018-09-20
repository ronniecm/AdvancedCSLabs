package FirstTerm;

public class RationalNumDriver {
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
}
