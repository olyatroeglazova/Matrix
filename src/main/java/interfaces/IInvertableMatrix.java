package interfaces;

import exceptions.MatrixOutOfBoundException;
import exceptions.ZeroDeterminantException;

public interface IInvertableMatrix extends IMatrix {
    public IInvertableMatrix calculateInvertableMatrix()throws ZeroDeterminantException, MatrixOutOfBoundException;
}
