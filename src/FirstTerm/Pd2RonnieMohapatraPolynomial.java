/*
 * Name: Ronnie Mohapatra
 * Lab: Polynomial Lab
 */
package FirstTerm;

public interface Pd2RonnieMohapatraPolynomial {
	int getDegree();
	double getCoefficient(int exponent);
	double evaluate(double x);
	Pd2RonnieMohapatraPolynomial add(Pd2RonnieMohapatraPolynomial other);
	Pd2RonnieMohapatraPolynomial subtract(Pd2RonnieMohapatraPolynomial other);
	Pd2RonnieMohapatraPolynomial multiply(Pd2RonnieMohapatraPolynomial other);
	Pd2RonnieMohapatraPolynomial findDerivative();
}
