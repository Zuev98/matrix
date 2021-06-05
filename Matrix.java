package processor;

import java.util.Scanner;

class Matrix {
    private final Scanner scanner = new Scanner(System.in);
    private double[][] matrix;
    final int n, m;

    Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        matrix = new double[n][m];
    }

    void input() {
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                matrix[i][j] = scanner.nextDouble();
    }

    Matrix add(Matrix matrixB) {
        Matrix res = new Matrix(this.n, this.m);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                res.matrix[i][j] = this.matrix[i][j] + matrixB.matrix[i][j];

        return res;
    }

    void output() {
        System.out.println("The result is:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                System.out.printf("%5.2f ", this.matrix[i][j]);
            System.out.println();
        }
    }

    Matrix scalarMultiply(double scalar) {
        Matrix res = new Matrix(this.n, this.m);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                res.matrix[i][j] = this.matrix[i][j] * scalar;

        return res;
    }

    Matrix multiply(Matrix matrixB) {
        Matrix res = new Matrix(this.n, matrixB.m);
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < matrixB.m; j++)
                for (int k = 0; k < this.m; k++)
                    res.matrix[i][j] += this.matrix[i][k] * matrixB.matrix[k][j];

        return res;
    }

    Matrix transpose() {
        Matrix res = new Matrix(this.n, this.m);
        for (int i = 0; i < n - 1; i++) {
            res.matrix[i][i] = this.matrix[i][i];
            for (int j = i + 1; j < m; j++) {
                res.matrix[i][j] = this.matrix[j][i];
                res.matrix[j][i] = this.matrix[i][j];
            }
        }
        res.matrix[n - 1][m - 1] = matrix[n - 1][m - 1];

        return res;
    }

    Matrix sideTranspose() {
        Matrix res = new Matrix(this.n, this.m);
        for (int i = 0; i < n - 1; i++) {
            res.matrix[i][m - i - 1] = this.matrix[i][m - i - 1];
            for (int j = 0; j < m - i - 1; j++) {
                res.matrix[i][j] = this.matrix[n - j - 1][m - i - 1];
                res.matrix[n - j - 1][m - i - 1] = this.matrix[i][j];
            }
        }
        res.matrix[n - 1][0] = matrix[n - 1][0];

        return res;
    }

    Matrix verticalTranspose() {
        Matrix res = new Matrix(this.n, this.m);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m / 2; j++) {
                res.matrix[i][j] = this.matrix[i][m - j - 1];
                res.matrix[i][m - j - 1] = this.matrix[i][j];
            }
        if (m % 2 == 1)
            for (int i = 0; i < n; i++)
                res.matrix[i][m / 2] = this.matrix[i][m / 2];

        return res;
    }

    Matrix horizontalTranspose() {
        Matrix res = new Matrix(this.n, this.m);
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m; j++) {
                res.matrix[i][j] = this.matrix[n - i - 1][j];
                res.matrix[n - i - 1][j] = this.matrix[i][j];
            }
        }
        if (n % 2 == 1)
            for (int i = 0; i < m; i++)
                res.matrix[n / 2][i] = this.matrix[n / 2][i];

        return res;
    }

    private Matrix getMinorMatrix(int row, int column)
    {
        Matrix minorMatrix = new Matrix(this.n - 1, this.n - 1);

        for (int r = 0, i = 0; r < this.n; r++) {
            if (r == row)
                continue;
            for (int col = 0, j = 0; col < this.n; col++)
                if (col != column)
                    minorMatrix.matrix[i][j++] = this.matrix[r][col];
            i++;
        }

        return minorMatrix;
    }

    double calculateDeterminant()
    {
        if (this.n == 2)
            return this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];

        double det = 0;
        int sign = 1;
        for (int column = 0; column < this.n; column++)
        {
            Matrix minorMatrix = this.getMinorMatrix(0, column);
            det += sign * this.matrix[0][column] * minorMatrix.calculateDeterminant();
            sign = -sign;
        }

        return det;
    }

    Matrix getCofactorMatrix() {
        Matrix cofactorMatrix = new Matrix(this.n, this.m);

        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++) {
                int sign = 1;
                if ((i + j) % 2 == 1)
                    sign = -1;
                Matrix minorMatrix = this.getMinorMatrix(i, j);
                cofactorMatrix.matrix[i][j] = sign * minorMatrix.calculateDeterminant();
            }

        return cofactorMatrix;
    }
}
