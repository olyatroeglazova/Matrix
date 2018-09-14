package interfaces;

import exceptions.MatrixOutOfBoundException;
import exceptions.ZeroDeterminantException;
import matrix.InvertableMatrix;

public interface IInvertableMatrix extends IMatrix {
    public InvertableMatrix calculateInvertableMatrix()throws ZeroDeterminantException, MatrixOutOfBoundException;
}
