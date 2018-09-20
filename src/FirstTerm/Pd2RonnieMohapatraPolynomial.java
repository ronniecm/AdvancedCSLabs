/*
 * Name: Ronnie Mohapatra
 * Lab: Polynomial Lab
 */
package FirstTerm;

public interface Pd2RonnieMohapatraPolynomial {
	int getDegree();
	double getCoefficient(int exponent);
	double evaluate(int x);
	Pd2RonnieMohapatraPolynomial add(Pd2RonnieMohapatraPolynomial other);
	Pd2RonnieMohapatraPolynomial subtract(Pd2RonnieMohapatraPolynomial other);
	Pd2RonnieMohapatraPolynomial findDerivative();
	Pd2RonnieMohapatraPolynomial times(double factor);	
}
