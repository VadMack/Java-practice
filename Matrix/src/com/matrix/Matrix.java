package com.matrix;

public class Matrix {
    private Complex[][] complexField;
    private Integer[][] intField;
    private int sizeN;
    private int sizeM;

    public Matrix(Complex[][] complexField) {
        this.complexField = complexField;
    }

    public Matrix(Integer[][] intField) {
        this.intField = intField;
    }

    public Matrix plus(Matrix secondMatrix) throws MatrixError {
        if (complexField != null && secondMatrix.complexField != null) { // Блок для комплексных
            Matrix result = new Matrix(new Complex[this.complexField.length][this.complexField[0].length]);
            for (int i = 0; i < this.complexField.length; i++) {
                for (int j = 0; j < this.complexField[i].length; j++) {
                    result.complexField[i][j] = this.complexField[i][j].plus(secondMatrix.complexField[i][j]);
                }
            }
            return result;
        } else if (intField != null && secondMatrix.intField != null) { // Блок для действительных
            Matrix result = new Matrix(new Integer[this.intField.length][this.intField[0].length]);
            for (int i = 0; i < this.intField.length; i++) {
                for (int j = 0; j < this.intField[i].length; j++) {
                    result.intField[i][j] = this.intField[i][j] + secondMatrix.intField[i][j];
                }

            }
            return result;
        } else {
            throw new MatrixError("Incompatible types");
        }
    }

    public Matrix minus(Matrix secondMatrix) throws MatrixError {
        if (complexField != null && secondMatrix.complexField != null) { // Блок для комплексных
            Matrix result = new Matrix(new Complex[this.complexField.length][this.complexField[0].length]);
            for (int i = 0; i < result.complexField[0].length; i++) {
                for (int j = 0; j < this.complexField.length; j++) {
                    result.complexField[i][j] = this.complexField[i][j].minus(secondMatrix.complexField[i][j]);
                }
            }
            return result;
        } else if (intField != null && secondMatrix.intField != null) { // Блок для действительных
            Matrix result = new Matrix(new Integer[this.intField.length][this.intField[0].length]);
            for (int i = 0; i < this.intField.length; i++) {
                for (int j = 0; j < this.intField[i].length; j++) {
                    result.intField[i][j] = this.intField[i][j] - secondMatrix.intField[i][j];
                }

            }
            return result;
        } else {
            throw new MatrixError("Incompatible types");
        }
    }

    public Matrix multiply(Matrix secondMatrix) throws MatrixError {

        if (complexField != null && secondMatrix.complexField != null) { // Блок для комплексных
            if (this.complexField[0].length != secondMatrix.complexField.length ||
                    this.complexField.length != secondMatrix.complexField[0].length) {
                throw new MatrixError("Incompatible matrix sizes");
            }
            Matrix result = new Matrix(new Complex[this.complexField.length][this.complexField[0].length]);
            for (int i = 0; i < result.complexField[0].length; i++) {
                for (int j = 0; j < this.complexField.length; j++) {
                    Complex sum = new Complex(0, 0);
                    for (int k = 0; k < secondMatrix.complexField[0].length; k++) {
                        sum = sum.plus(this.complexField[i][k].multiply(secondMatrix.complexField[k][j]));
                    }
                    result.complexField[i][j] = sum;
                }
            }
            return result;

        } else if (intField != null && secondMatrix.intField != null) { // Блок для действительных
            if (this.intField[0].length != secondMatrix.intField.length ||
                    this.intField.length != secondMatrix.intField[0].length) {
                throw new MatrixError("Incompatible matrix sizes");
            }
            Matrix result = new Matrix(new Integer[this.intField.length][this.intField[0].length]);
            for (int i = 0; i < result.intField[0].length; i++) {
                for (int j = 0; j < this.intField.length; j++) {
                    int sum = 0;
                    for (int k = 0; k < secondMatrix.intField[0].length; k++) {
                        sum += this.intField[i][k] * secondMatrix.intField[k][j];
                    }
                    result.intField[i][j] = sum;
                }
            }
            return result;
        } else {
            System.out.println("Error");
            return null;
        }
    }

    public void print() {
        if (complexField != null) { // Блок для комплексных
            for (int i = 0; i < this.complexField.length; i++) {
                for (int j = 0; j < this.complexField[i].length; j++) {
                    this.complexField[i][j].print();
                    System.out.print(" ");
                }
                System.out.println();
            }
        } else if (intField != null) { // Блок для действительных
            for (int i = 0; i < this.intField.length; i++) {
                for (int j = 0; j < this.intField[i].length; j++) {
                    System.out.print(this.intField[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("Error");
        }
    }

    static class MatrixError extends Exception {
        MatrixError(String message) {
            super(message);
        }
    }

}
