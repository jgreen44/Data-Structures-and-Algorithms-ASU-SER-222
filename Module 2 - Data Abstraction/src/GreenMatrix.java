import org.jetbrains.annotations.NotNull;

/**
 * An implementation of the Matrix ADT. Provides four basic operations over an
 * immutable type.
 *
 * @author Jason Green, jgreen44@asu.edu
 * @version 1.0
 */
public class GreenMatrix implements Matrix {

    private int[][] matrix;
    private final int rows;
    private final int cols;

    // constructor
    public GreenMatrix(int[] @NotNull [] data) {
        if (data.length == 0) {
            this.rows = 0;
            this.cols = 0;
//            this.matrix = new int[this.rows][this.cols];
        } else {
            this.rows = data.length;
            this.cols = data[0].length;
        }
        this.matrix = new int[this.rows][this.cols];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                assert false;
                this.matrix[i][j] = data[i][j];
            }

        }
    }

    //TODO: implement interface.
    public int getElement(int y, int x) {
        return this.matrix[y][x];
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.cols;
    }

    public Matrix scale(int scalar){
        int matrixScale = 0;

        int[][] newMatrixScalar = new int[this.rows][this.cols];
        for (int i = 0; i < this.rows ; i++) {
            for (int j = 0; j < this.cols ; j++) {
                matrixScale = this.getElement(i, j) * scalar;
                newMatrixScalar[i][j] += matrixScale;
            }
        }
        return new GreenMatrix(newMatrixScalar);
    }

    public Matrix plus(Matrix other) {
        int matrixSum = 0;
        int[][] newMatrixSum = new int[this.rows][this.cols];
        if(this.getRows() == other.getRows() && this.getColumns() == other.getColumns()) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.cols; j++) {
                    matrixSum = this.getElement(i, j) + other.getElement(i, j);
                    newMatrixSum[i][j] += matrixSum;
                }
            }
        }else{
            System.out.println("Cannot add matrices! Dimensions are not valid.");
        }
        return new GreenMatrix(newMatrixSum);
    }

    public Matrix minus(Matrix other) {
        int matrixMinus = 0;
        int[][] newMatrixMinus = new int[this.rows][this.cols];
        if(this.getRows() == other.getRows() && this.getColumns() == other.getColumns()){

            for (int i = 0; i < this.rows ; i++) {
                for (int j = 0; j < this.cols ; j++) {
                    matrixMinus = this.getElement(i, j) - other.getElement(i, j);
                    newMatrixMinus[i][j] += matrixMinus;
                }
            }
        }else{
            System.out.println("Cannot subtract matrices! Dimensions are not valid.");
        }
        return new GreenMatrix(newMatrixMinus);

    }

    public Matrix multiply(Matrix other) {
        int matrixMultiply = 0;

        int[][] newMatrixMultiply = new int[this.rows][this.cols];
        for (int i = 0; i < this.rows ; i++) {
            for (int j = 0; j < this.cols ; j++) {
                matrixMultiply = this.getElement(i, j) * other.getElement(i, j);
                newMatrixMultiply[i][j] += matrixMultiply;
            }
        }
        return new GreenMatrix(newMatrixMultiply);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this.matrix) return true;
        if (!(other instanceof Matrix)) return false;
        if (other.getClass() != this.getClass()) return false;
        for (int i = 0; i < ((Matrix) other).getRows(); i++) {
            for (int j = 0; j < ((Matrix) other).getColumns(); j++) {
                if (this.getElement(i, j) != ((Matrix)other).getElement(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < this.rows ; i++) {
            for (int j = 0; j < this.cols ; j++) {
                text.append(matrix[i][j]);
            }

        }
        return text.toString();
    }


    /**
     * Entry point for matrix testing.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[][] data1 = new int[0][0];
        int[][] data2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] data3 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[][] data4 = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};

        Matrix m1 = new GreenMatrix(data1);
        Matrix m2 = new GreenMatrix(data2);
        Matrix m3 = new GreenMatrix(data3);
        Matrix m4 = new GreenMatrix(data4);

        System.out.println("m1 --> Rows: " + m1.getRows() + " Columns: " + m1.getColumns());
        System.out.println("m2 --> Rows: " + m2.getRows() + " Columns: " + m2.getColumns());
        System.out.println("m3 --> Rows: " + m3.getRows() + " Columns: " + m3.getColumns());

        //check for reference issues
        System.out.println("m2 -->\n" + m2);
        data2[1][1] = 101;
        System.out.println("m2 -->\n" + m2);

        //test equals
        System.out.println("m2==null: " + m2.equals(null));             //false
        System.out.println("m3==\"MATRIX\": " + m2.equals("MATRIX"));   //false
        System.out.println("m2==m1: " + m2.equals(m1));                 //false
        System.out.println("m2==m2: " + m2.equals(m2));                 //true

        System.out.println("m2==m3: " + m2.equals(m3));                 //false
        System.out.println("m3==m4: " + m3.equals(m4));                 //true

        //test operations (valid)
        System.out.println("2 * m2:\n" + m2.scale(2));
        System.out.println("m2 + m3:\n" + m2.plus(m3));
        System.out.println("m2 - m3:\n" + m2.minus(m3));

        //not tested... multiply(). you know what to do.
        System.out.println("m2 * m3: \n" + m2.multiply(m3));
        System.out.println("m3 * m4: \n" + m3.multiply(m4));

        //test operations (invalid)
        System.out.println("m1 + m2" + m1.plus(m2));
        System.out.println("m1 - m2" + m1.minus(m2));
    }
}