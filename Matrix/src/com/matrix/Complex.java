package com.matrix;

public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex plus(Complex secondComplex) {
        return new Complex(this.real + secondComplex.real, this.imaginary + secondComplex.imaginary);
    }

    public Complex minus(Complex secondComplex) {
        return new Complex(this.real - secondComplex.real, this.imaginary - secondComplex.imaginary);
    }

    public Complex multiply(Complex secondComplex) {
        return new Complex(this.real * secondComplex.real - this.imaginary * secondComplex.imaginary,
                this.real * secondComplex.imaginary + this.imaginary * secondComplex.real);
    }

    public Complex multiply(Integer number) {
        return new Complex(this.real * number,
                this.imaginary * number);
    }

    public Complex divide(Complex secondComplex) {
        return new Complex((this.real * secondComplex.real + this.imaginary * secondComplex.imaginary) / (secondComplex.imaginary * secondComplex.imaginary + secondComplex.real * secondComplex.real),
                (secondComplex.real * this.imaginary - this.real * secondComplex.imaginary) / (secondComplex.imaginary * secondComplex.imaginary + secondComplex.real * secondComplex.real));

    }

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

    void algebraic2trigonometric() {
        double r = Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imaginary, 2));
        double phi = Math.atan(this.imaginary / this.real);
        System.out.println(r + " * (cos(" + phi + ") * i * sin(" + phi + "))");
    }
}
