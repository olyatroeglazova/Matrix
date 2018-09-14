package exceptions;

public class MatrixException extends Exception {

    public MatrixException() {
    }

    public MatrixException(final String message) {
        super(message);
    }

    public MatrixException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MatrixException(final Throwable cause) {
        super(cause);
    }

}
