package com.matrix;

public class Main {

    public static void main(String[] args) throws Matrix.MatrixError {
        Complex[][] field = new Complex[2][2];
        field[0][0] = new Complex(1, 1);
        field[0][1] = new Complex(2, 2);
        field[1][0] = new Complex(3, 3);
        field[1][1] = new Complex(4, 4);

        Matrix matrix = new Matrix(field);
        matrix.print();

        System.out.println();
        Matrix matrix2 = matrix.plus(matrix);
        matrix2.print();
        System.out.println();

        matrix2 = matrix2.minus(matrix);
        matrix2.print();
        System.out.println();

        matrix2 = matrix2.multiply(matrix);
        matrix2.print();
        System.out.println();

        // Блок для целочисленных

        Integer[][] field2 = {{1, 2}, {3, 4}};
        Matrix matrix3 = new Matrix(field2);
        matrix3.print();
        System.out.println();

        matrix3 = matrix3.multiply(matrix3);
        matrix3.print();
        System.out.println();

        matrix3 = matrix3.plus(matrix3);
        matrix3.print();
        System.out.println();

        matrix3 = matrix3.minus(matrix3);
        matrix3.print();
        System.out.println();


    }
}
