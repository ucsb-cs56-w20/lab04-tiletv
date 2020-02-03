package edu.ucsb.cs56.ratcalc.model;


/**
 * A class to represent a rational number with a numerator and denominator
 *
 * @author P. Conrad for CS56 F16
 *
 */

public class Rational {

    private int num;
    private int denom;

    /**
     * greatest common divisor of a and b
     *
     * @param a first number
     * @param b second number
     * @return gcd of a and b
     */
    public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        else if (b == 0)
            return a;
        else
            return gcd(b % a, a);
    }

    public Rational() {
        this.num = 1;
        this.denom = 1;
    }

    public Rational(int num, int denom) {
        if (denom == 0) {
            throw new IllegalArgumentException("denominator may not be zero");
        }
        this.num = num;
        this.denom = denom;
        if (num != 0) {
            int gcd = Rational.gcd(num, denom);
            this.num /= gcd;
            this.denom /= gcd;
        }
	if(this.num>=0 && this.denom<0){
	    this.num = this.num*(-1);
	    this.denom = this.denom*(-1);
	}
    }

    public String toString() {
        if (denom == 1 || num == 0)
            return "" + num;
        return num + "/" + denom;
    }

    public int getNumerator() {
        return this.num;
    }

    public int getDenominator() {
        return this.denom;
    }

    public Rational times(Rational r) {
        return new Rational(this.num * r.num, this.denom * r.denom);
    }

    public static Rational product(Rational a, Rational b) {
        return new Rational(a.num * b.num, a.denom * b.denom);
    }

    /**
     * For testing getters.
     *
     * 
     */
    public static int lcm(int a,int b){
	return (a*b)/gcd(a,b);
    }

    public Rational plus(Rational r){
	int n1;
	int n2;
	n1 = this.denom * r.getNumerator() + this.num * r.getDenominator();
	n2 = this.denom * r.getDenominator();
	//if (n1 >= 0 && n2 < 0){
	//    n1 = n1 * -1;
	//    n2 = n2 * -1;
	//}
	Rational newone = new Rational(n1,n2);
	return newone;
    }

    public static Rational sum(Rational a, Rational b){
	int n1;
	int n2;
	n1 = a.getDenominator() * b.getNumerator() + a.getNumerator() * b.getDenominator();
	n2 = a.getDenominator() * b.getDenominator();

	//if (n1 >=0 && n2 < 0){
	//    n1 = n1 * -1;
	//    n2 = n2 * -1;
	//}
	Rational newone = new Rational(n1,n2);
	return newone;
    }

    public Rational minus(Rational r){
	Rational tR = new Rational(r.getNumerator()*(-1),r.getDenominator());
	return plus(tR);
    }
    
    public static Rational difference(Rational a, Rational b){
	Rational tR = new Rational(b.getNumerator()*(-1),b.getDenominator());
	return sum(a, tR);
    }

    public Rational reciprocalOf(){
	if(num ==0){
	    throw new ArithmeticException();
	}
	Rational newone = new Rational(this.denom,this.num);
	return newone;
    }

    public Rational dividedBy(Rational r){
	Rational newone = new Rational(this.num*r.denom,this.denom*r.num);
        return newone;
    }

    public static Rational quotient(Rational a, Rational b){
	Rational newone = new Rational(a.num*b.denom,a.denom*b.num);
        return newone;
    }
    
    

    public static void main(String[] args) {
        Rational r = new Rational(5, 7);
        System.out.println("r.getNumerator()=" + r.getNumerator());
        System.out.println("r.getDenominator()=" + r.getDenominator());

    }

}
