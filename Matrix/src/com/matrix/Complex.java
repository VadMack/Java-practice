package com.matrix;

/**
 * @author Vadim Makarov
 */
public class Complex {
    private double real;
    private double imaginary;

    /**
     * Empty signature constructor
     */
    public Complex() {
        this(0, 0);
    }

    /**
     * Constructor
     * @param real the real part of the complex number
     * @param imaginary the imaginary part of the complex number
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Addition operation
     * @param secondComplex the second argument for binary operation
     * @return Summation result
     */
    public Complex plus(Complex secondComplex) {
        return new Complex(this.real + secondComplex.real, this.imaginary + secondComplex.imaginary);
    }

    /**
     * Addition operation
     * @param number the second argument for binary operation
     * @return Summation result
     */
    public Complex plus(Integer number) {
        return new Complex(this.real + number, this.imaginary);
    }

    /**
     * Subtraction operation
     * @param secondComplex the second argument for binary operation
     * @return Subtraction result
     */
    public Complex minus(Complex secondComplex) {
        return new Complex(this.real - secondComplex.real, this.imaginary - secondComplex.imaginary);
    }

    /**
     * Subtraction operation
     * @param number the second argument for binary operation
     * @return Subtraction result
     */
    public Complex minus(Integer number) {
        return new Complex(this.real - number, this.imaginary);
    }

    /**
     * Multiplication operation
     * @param secondComplex the second argument for binary operation
     * @return Multiplication result
     */
    public Complex multiply(Complex secondComplex) {
        return new Complex(this.real * secondComplex.real - this.imaginary * secondComplex.imaginary,
                this.real * secondComplex.imaginary + this.imaginary * secondComplex.real);
    }

    /**
     * Multiplication operation
     * @param number the second argument for binary operation
     * @return Multiplication result
     */
    public Complex multiply(Integer number) {
        return new Complex(this.real * number,
                this.imaginary * number);
    }

    /**
     * Division operation
     * @param secondComplex the second argument for binary operation
     * @return Division result
     */
    public Complex divide(Complex secondComplex) {
        return new Complex((this.real * secondComplex.real + this.imaginary * secondComplex.imaginary) / (secondComplex.imaginary * secondComplex.imaginary + secondComplex.real * secondComplex.real),
                (secondComplex.real * this.imaginary - this.real * secondComplex.imaginary) / (secondComplex.imaginary * secondComplex.imaginary + secondComplex.real * secondComplex.real));

    }

    /**
     * prints the complex number in algebraic form
     */
    void print() {
        System.out.print(this.real);
        if (this.imaginary > 0) {
            System.out.print("+" + this.imaginary + "i");
        } else if (this.imaginary < 0) {
            System.out.print(+this.imaginary + "i");
        } else {
            System.out.print("");
        }
    }

    /**
     * prints the complex number in trigonometric form
     */
    void algebraic2trigonometric() {
        double r = Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
        double phi = Math.atan(this.imaginary / this.real);
        System.out.println(r + " * (cos(" + phi + ") * i * sin(" + phi + "))");
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }
}
