package processor;

import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("Your choice: ");
            int choice = SCANNER.nextInt();
            System.out.println();

            Matrix matrixA, matrixB;
            switch (choice) {
                case 1:
                    matrixA = fill("first ");
                    matrixB = fill("second ");
                    if (matrixA.n == matrixB.n && matrixA.m == matrixB.m)
                        matrixA.add(matrixB).output();
                    else
                        System.out.println("The operation cannot be performed.");
                    break;
                case 2:
                    matrixA = fill("");
                    System.out.print("Enter constant: ");
                    int scalar = SCANNER.nextInt();
                    matrixA.scalarMultiply(scalar).output();
                    break;
                case 3:
                    matrixA = fill("first ");
                    matrixB = fill("second ");
                    if (matrixA.m == matrixB.n)
                        matrixA.multiply(matrixB).output();
                    else
                        System.out.println("The operation cannot be performed.");
                    break;
                case 4:
                    printSubmenu();
                    System.out.print("Your choice: ");
                    choice = SCANNER.nextInt();
                    switch (choice) {
                        case 1:
                            matrixA = fill("");
                            if (matrixA.n == matrixA.m)
                                matrixA.transpose().output();
                            else
                                System.out.println("The operation cannot be performed.");
                            break;
                        case 2:
                            matrixA = fill("");
                            if (matrixA.n == matrixA.m)
                                matrixA.sideTranspose().output();
                            else
                                System.out.println("The operation cannot be performed.");
                            break;
                        case 3:
                            matrixA = fill("");
                            matrixA.verticalTranspose().output();
                            break;
                        case 4:
                            matrixA = fill("");
                            matrixA.horizontalTranspose().output();
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    matrixA = fill("");
                    if (matrixA.n == matrixA.m)
                        System.out.println("The result is:\n" + matrixA.calculateDeterminant());
                    else
                        System.out.println("The operation cannot be performed.");
                    break;
                case 6:
                    matrixA = fill("");
                    if (matrixA.n == matrixA.m) {
                        double det = matrixA.calculateDeterminant();
                        if (det != 0)
                            matrixA.getCofactorMatrix().transpose().scalarMultiply(1 / det).output();
                        else
                            System.out.println("This matrix doesn't have an inverse.");
                    }
                    else
                        System.out.println("The operation cannot be performed.");
                    break;
                case 0:
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
    }

    private static void printSubmenu() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
    }

    private static Matrix fill(String number) {
        System.out.print("Enter size of " + number + "matrix: ");
        int n = SCANNER.nextInt();
        int m = SCANNER.nextInt();
        System.out.println("\nEnter " + number + "matrix:");
        Matrix matrix = new Matrix(n, m);
        matrix.input();

        return matrix;
    }
}
