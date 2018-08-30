package FirstTerm;

public class ArrayBasedPolyDriver
{
   public static void main(String[] args) { 
      double [] c = {1, 0, 3, 4};
      double [] c1 = {-2, -5};
   
      Polynomial p1 = new ArrayBasedPoly(c);  // 4x^3 + 3x^2 + 1
      System.out.println("p1(x) = " + p1);
   
      Polynomial p2 = new ArrayBasedPoly(c1);   // - 5x – 2
      System.out.println("p2(x) = " + p2);                 // p2(x) = - 5x^1 – 2
   
      Polynomial p3 = new ArrayBasedPoly(-4, 1);  // coeff = -4, exp = 1
      System.out.println("p3(x) = " + p3);
   
      Polynomial p = p1.add(p2).add(p2);   // 4x^3 + 3x^2 - 10x – 3
      System.out.println("p(x) = " + p);       // p(x) = 4x^3 + 3x^2 - 10x^1 – 3
      
      Polynomial p4   = p.subtract(p3);   // 4x^3 + 3x^2 - 6x^1 – 3   <====
      System.out.println("p4(x) = " + p4);
   
   
      Polynomial p5   = p4.findDerivative();   // 12x^2 + 6x^1 - 6   <====
      System.out.println("p5(x) = " + p5);
      
      Polynomial clone = new ArrayBasedPoly(p5);
      System.out.println("clone(x) = " + clone);
   
      System.out.println ("p(5) = " + p5.evaluate(0));
      System.out.println ("p(1) = " + p5.evaluate(1));
   }
}

