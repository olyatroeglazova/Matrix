package matrix;

import exceptions.MatrixOutOfBoundException;
import interfaces.IMatrix;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Matrix implements IMatrix, Serializable {

    private final int N;
    protected double[] matrix;
    private double determinant;
    private boolean flagForDeterminant;

    public Matrix(final int n) {
        this.N = n;
        this.matrix = new double[N * N];
        this.determinant = 0;
        flagForDeterminant = true;
    }

    public Matrix(final Matrix matrix1) throws MatrixOutOfBoundException {
        this.matrix = new double[matrix1.N * matrix1.N];
        this.N = matrix1.N;
        this.determinant = matrix1.determinant;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i*N  + j] = matrix1.getElem(i, j);
            }
        }
    }

    public int length() {
        return N;
    }

    public double getElem  (final int i, final int j) throws MatrixOutOfBoundException {
        if (i < 0 || i >= this.N || j < 0 || j >= this.N ) {
            throw new MatrixOutOfBoundException("Index out of bound");
        }
        return matrix[i*N + j];
    }

    public void setElem(final int i, final int j, final double elem) throws MatrixOutOfBoundException{
        if (i < 0 || i >= this.N || j < 0 || j >= this.N ) {
            throw new MatrixOutOfBoundException("Index out of bound");
        }
        flagForDeterminant = false;
        this.matrix[i*N + j] = elem;
    }

    protected int firstNotNull(final int j) throws MatrixOutOfBoundException {
        for (int i = j; i < this.length(); i++) {
            if (this.getElem(i, j) != 0) {
                return i;
            }
        }
        return -1;
    }

    public double determinant() throws MatrixOutOfBoundException {
        if (flagForDeterminant) {
            return determinant;
        }

        Matrix tmp = new Matrix(this);
        double multiplier;
        double determinant = 1;
        int notNull;

        for (int i = 0; i < tmp.length(); i++) {
            if (tmp.getElem(i, i) == 0) {
                notNull = tmp.firstNotNull(i);
                if (notNull == -1) {
                    return 0;
                } else {
                    for (int j = i; j < tmp.length(); j++) {
                        tmp.setElem(i, j, tmp.getElem(i, j) + tmp.getElem(notNull, j));
                    }
                }
            }
            for (int nextStr = i + 1; nextStr < tmp.length(); nextStr++) {
                if (tmp.getElem(i, i) != 0) {
                    multiplier = tmp.getElem(nextStr, i) / tmp.getElem(i, i);
                    tmp.setElem(nextStr, i, 0.);
                    for (int k = i + 1; k < tmp.length(); k++) {
                        tmp.setElem(nextStr, k, tmp.getElem(nextStr, k) - tmp.getElem(i, k) * multiplier);
                    }
                }
            }
        }
            for (int i = 0; i < this.length(); i++) {
                determinant *= tmp.getElem(i, i);
            }
            flagForDeterminant = true;
            return determinant;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix1 = (Matrix) o;

        if (N != matrix1.N) return false;
        if (Double.compare(matrix1.determinant, determinant) != 0) return false;
        if (flagForDeterminant != matrix1.flagForDeterminant) return false;

        for (int i = 0; i<N*N; i++){
            if(this.matrix[i]-matrix1.matrix[i]>1E-9){
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(N);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }
}
