package FirstTerm;

public class ArrayBasedPolyDriver
{
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
}

