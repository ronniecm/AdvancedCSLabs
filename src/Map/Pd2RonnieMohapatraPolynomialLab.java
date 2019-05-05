package Map;

/**
 * Name: Ronnie Mohapatra                                         
 * Period: 2
 * Name of the Lab: Polynomial Lab
 * Purpose of the Program: Model polynomials using the Map data structure
 * Due Date: 
 * Date Submitted: 
 * What I learned: a) Adding polynomials using maps
 * 				   b) Multiplying polynomials using maps
 * 				   c) Formatting polynomials into Strings
 *  
 * How I feel about this lab: It was a good application of using Maps in the real world. It was much easier
 * 						      than modeling polynomials using arrays. 
 
 * What I wonder: What other applications of Maps are there?
 *
 * Most Difficult Method: toString()
 
 * Credits: 
 */
import java.util.*;

public class Pd2RonnieMohapatraPolynomialLab {
	public static void main(String[] args) {
		Polynomial poly = new Polynomial();
		poly.makeTerm(1, -4);
		poly.makeTerm(3, 2);
		poly.makeTerm(0, 2);
		System.out.println("Polynomial 1: " + poly.toString());
		double evaluateAt = 2.0;
		System.out.println("Evaluated at " + evaluateAt + ": " + poly.evaluateAt(evaluateAt));

		Polynomial poly2 = new Polynomial();
		poly2.makeTerm(1, -5);
		poly2.makeTerm(4, 2);
		poly2.makeTerm(0, -3);
		poly2.makeTerm(2, 1);
		System.out.println("Polynomial 2: " + poly2.toString());

		System.out.println("Sum: " + poly.add(poly2));
		System.out.println("Product: " + poly.multiply(poly2));
	}
}

class Polynomial {
	private Map<Integer, Integer> polyMap = new TreeMap<Integer, Integer>();
	
	public Map<Integer, Integer> getPolyMap() {
		return polyMap;
	}
	
	public void makeTerm(int exponent, int coeff) {
		polyMap.put(exponent, coeff);
	}
	
	public int getDegree() {
		Set<Integer> exponents = polyMap.keySet();
		int degree = 0;
		for(Integer exp : exponents) {
			if(exp > degree)
				degree = exp;
		}
		
		return degree;
	}
	
	public Polynomial add(Polynomial other) {
		Polynomial sum = new Polynomial();
		int degree = this.getDegree() > other.getDegree() ? this.getDegree() : other.getDegree();
		for(int exp = 0; exp <= degree; exp++) {
			if(polyMap.get(exp) == null && other.getPolyMap().get(exp) != null)
				sum.makeTerm(exp, other.getPolyMap().get(exp));
			else if(polyMap.get(exp) != null && other.getPolyMap().get(exp) == null)
				sum.makeTerm(exp, polyMap.get(exp));
			else if(polyMap.get(exp) != null && other.getPolyMap().get(exp) != null) {
				int coeff = polyMap.get(exp) + other.getPolyMap().get(exp);
				sum.makeTerm(exp, coeff);
			}
		}
		return sum;
	}
	
	public Polynomial multiply(Polynomial other) {
		Polynomial product = new Polynomial();
		Set<Integer> exponents = polyMap.keySet();
		//nested for loop to calculate product
		for(Integer exp : exponents) {
			Polynomial temp = new Polynomial(); //use a temp polynomial
			Set<Integer> otherExponents = other.getPolyMap().keySet();
			for(Integer otherExp : otherExponents) {
				int exponent = exp + otherExp; //add exponents
				int coeff = polyMap.get(exp) * other.getPolyMap().get(otherExp); //multiply coefficients
				temp.makeTerm(exponent, coeff); //add term to temp polynomial
			}
			product = product.add(temp); //add temp to real product
		}
		return product;
	}
	
	public double evaluateAt(double x) {
		double result = 0;
		Set<Integer> exponents = polyMap.keySet();
		for(Integer exp : exponents) //traverse through polynomial and keep adding term to result
			result += polyMap.get(exp) * Math.pow(x, exp);
		return result;
	}
	
	public String toString() {
		String result = "";
		String[] terms = new String[polyMap.size()]; //create array of terms in String form
		Set<Integer> exponents = polyMap.keySet();
		int i = 0;
		for(Integer exp : exponents) { //traverse through map
			String term = "";
			if(polyMap.get(exp) < 0) //if coeff < 0 add a negative
				term += " - ";
			else
				term += (i == polyMap.size()-1 ? "" : " + "); //else add a plus if not at highest degree term
			
			//if exp == 0 add just the coeff to the String
			if(exp == 0)
				term += Math.abs(polyMap.get(exp));
			else //add just x if exp == 1 else add the coeff plus the exponent
				term += (Math.abs(polyMap.get(exp)) == 1.0 ? "" : Math.abs(polyMap.get(exp))) + (exp == 1 ? "x" : "x^" + exp);
			
			//add term to the array
			terms[i++] = term;
		}
		
		//traverse through array backwards so output goes from highest degree to lowest
		for(int j = terms.length - 1; j >= 0; j--)
			result += terms[j];
		
		return result;
	}
}
