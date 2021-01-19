package com.complex;

public class Main {

    public static void main(String[] args) {
        Complex complex1 = new Complex(-2, 1);
        Complex complex2 = new Complex(1, -1);
        Complex complex3 = complex1.divide(complex2);
        Complex complex4 = complex1.plus(complex2);
        Complex complex5 = complex1.minus(complex2);
        Complex complex6 = complex1.multiply(complex2);
        complex3.print();
        complex4.print();
        complex5.print();
        complex6.print();
        complex1.algebraic2trigonometric();
    }
}
