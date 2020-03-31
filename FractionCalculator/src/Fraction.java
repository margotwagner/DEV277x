/*
Code created by Margot Wagner 3/24/20
Module 2 (Building Your Own Objects) final project
Fraction Calculator
Part of edX Object Oriented Programming in Java by Microsoft
 */

public class Fraction {
    // object that holds info about fraction and does math
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator cannot be zero");
        }
        else if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int numerator) {
        this(numerator, 1);
    }

    public Fraction() {
        this(0,1);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public double toDouble() {
        return numerator/denominator;
    }

    public Fraction add(Fraction other) {
        // adds other to this; returns sum
        Fraction sum = new Fraction();
        if (this.denominator == other.denominator) {
            sum.numerator = this.numerator + other.numerator;
            sum.denominator = this.denominator;
        }
        else if (this.denominator%other.denominator == 0) {
            sum.numerator = this.numerator + (this.denominator/other.denominator)*other.numerator;
            sum.denominator = this.denominator;
        }
        else if (other.denominator%this.denominator == 0) {
            sum.numerator = other.numerator + (other.denominator/this.denominator)*this.numerator;
            sum.denominator = other.denominator;
        }
        else {
            sum.numerator = (this.numerator*other.denominator) + (other.numerator*this.denominator);
            sum.denominator = this.denominator * other.denominator;
        }
        return sum;
    }

    public Fraction subtract(Fraction other) {
        // subtracts other from this; return difference
        Fraction diff = new Fraction();
        if (this.denominator == other.denominator) {
            diff.numerator = this.numerator - other.numerator;
            diff.denominator = this.denominator;
        }
        else if (this.denominator%other.denominator == 0) {
            diff.numerator = this.numerator - (this.denominator/other.denominator)*other.numerator;
            diff.denominator = this.denominator;
        }
        else if (other.denominator%this.denominator == 0) {
            diff.numerator = (other.denominator/this.denominator)*this.numerator - other.numerator;
            diff.denominator = other.denominator;
        }
        else {
            diff.numerator = (this.numerator*other.denominator) - (other.numerator*this.denominator);
            diff.denominator = this.denominator * other.denominator;
        }
        return diff;
    }

    public Fraction multiply(Fraction other) {
        Fraction prod = new Fraction();
        prod.numerator = this.numerator*other.numerator;
        prod.denominator = this.denominator*other.denominator;
        return prod;
    }

    public Fraction divide(Fraction other) {
        // divides other fraction by this fraction
        if (other.numerator == 0) {
            throw new IllegalArgumentException("You cannot divide by zero");
        }
        else {

            Fraction div = new Fraction();
            div.numerator = this.numerator * other.denominator;
            div.denominator = this.denominator * other.numerator;
            return div;
        }
    }

    public boolean equals(Object other) {
        if (other instanceof Fraction) {
            Fraction otherFrac = (Fraction) other;
            if ((this.numerator/this.denominator) == (otherFrac.numerator/otherFrac.denominator)) {
                return true;
            }
            else { return false; }
        }
        else { return false; }
    }

    public void toLowestTerms() {
        // Converts fraction to lowest terms using gcd
        int gcdInt = gcd(this.numerator, this.denominator);
        this.numerator = this.numerator / gcdInt;
        this.denominator = this.denominator / gcdInt;

    }

    public static int gcd(int num, int den) {
        // returns greatest common divisor using Euclidean Algorithm
        while ((num != 0) && (den != 0)) {
            int rem = num % den;
            num = den;
            den = rem;
        }
        return num;
    }

}
