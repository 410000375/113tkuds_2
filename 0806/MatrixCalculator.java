public class MatrixCalculator {

    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("矩陣 A：");
        printMatrix(matrixA);

        System.out.println("矩陣 B：");
        printMatrix(matrixB);

        System.out.println("矩陣 A + B：");
        printMatrix(addMatrices(matrixA, matrixB));

        System.out.println("矩陣 A × C：");
        printMatrix(multiplyMatrices(matrixA, matrixC));

        System.out.println("矩陣 A 的轉置：");
        printMatrix(transposeMatrix(matrixA));

        int[] maxMin = findMaxMin(matrixA);
        System.out.println("矩陣 A 的最大值：" + maxMin[0]);
        System.out.println("矩陣 A 的最小值：" + maxMin[1]);
    }

    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = a[i][j] + b[i][j];

        return result;
    }

    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int colsB = b[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++)
            for (int j = 0; j < colsB; j++)
                for (int k = 0; k < colsA; k++)
                    result[i][j] += a[i][k] * b[k][j];

        return result;
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transposed = new int[cols][rows];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                transposed[j][i] = matrix[i][j];

        return transposed;
    }

    public static int[] findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];

        for (int[] row : matrix)
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }

        return new int[]{max, min};
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row)
                System.out.print(val + "\t");
            System.out.println();
        }
        System.out.println();
    }
}
