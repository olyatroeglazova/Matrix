package exceptions;
import exceptions.MatrixException;

public class MatrixOutOfBoundException extends MatrixException {
    public MatrixOutOfBoundException() {
    }

    public MatrixOutOfBoundException(String message) {
        super(message);
    }

    public MatrixOutOfBoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MatrixOutOfBoundException(Throwable cause) {
        super(cause);
    }
}
