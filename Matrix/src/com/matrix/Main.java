package com.matrix;

public class Main {

    public static void main(String[] args) throws Matrix.MatrixError {

        Complex[][] complexField = new Complex[2][2];
        complexField[0][0] = new Complex(1, 1);
        complexField[0][1] = new Complex(2, 2);
        complexField[1][0] = new Complex(3, 3);
        complexField[1][1] = new Complex(4, 4);
        Matrix complexMatrix1 = new Matrix(complexField);
        complexMatrix1.print();

        int[][] intField1 = {{1, 2}, {3, 4}};
        int[][] intField2 = {{1, 2, 3}, {4, 5, 6}};
        int[][] intField3 = {{1, 2}, {3, 4}, {5, 6}};
        int[][] intField4 = {{1, 2, 1}, {4, 5, 6}, {7, 7, 2}};

        Matrix intMatrix1 = new Matrix(intField1);
        intMatrix1.print();

        Matrix complexMatrix2 = complexMatrix1.plus(intMatrix1);
        complexMatrix2.print();

        complexMatrix2 = complexMatrix2.minus(complexMatrix1);
        complexMatrix2.print();

        Matrix intMatrix2 = intMatrix1.multiply(intMatrix1);
        intMatrix2.print();

        Matrix intMatrix3 = new Matrix(intField2);
        Matrix intMatrix4 = new Matrix(intField3);
        Matrix intMatrix5 = intMatrix4.multiply(intMatrix3);
        intMatrix5.print();

        intMatrix5.print();
        intMatrix5.findDeterminant().print();
        System.out.println("\n");

        intMatrix1.print();
        Complex determinant = intMatrix1.findDeterminant();
        determinant.print();
        System.out.println("\n");

        Matrix intMatrix6 = new Matrix(intField4);
        intMatrix6.print();
        determinant = intMatrix6.findDeterminant();
        determinant.print();
        System.out.println("\n");
    }
}
