package FirstTerm;

public interface Polynomial {
	int getDegree();
	double getCoefficient(int exponent);
	double evaluate(int x);
	Polynomial add(Polynomial other);
	Polynomial subtract(Polynomial other);
	Polynomial findDerivative();
}
