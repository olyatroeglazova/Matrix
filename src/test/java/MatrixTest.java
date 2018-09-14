import exceptions.MatrixOutOfBoundException;
import matrix.Matrix;
import exceptions.MatrixException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void testSetElem() throws MatrixException {
        Matrix m = new Matrix(2);
        m.setElem(0,1,3.);
        assertEquals(3., m.getElem(0,1), 1E-10);
    }

    @Test
    public void testDeterminant2x2() throws MatrixException {
        Matrix m = new Matrix(2);
        m.setElem(0, 0, 0.);
        m.setElem(0, 1, 3.);
        m.setElem(1, 0, -3.);
        m.setElem(1, 1, 4.);
        assertEquals(9, m.determinant(), 1E-10);
    }

    @Test
    public void testDeterminant4x4() throws MatrixException {
        Matrix m = new Matrix(4);
        m.setElem(0, 0, 3.);
        m.setElem(0, 1, -3.);
        m.setElem(0, 2, -5.);
        m.setElem(0, 3, 8.);
        m.setElem(1, 0, -3.);
        m.setElem(1, 1, 2.);
        m.setElem(1, 2, 4.);
        m.setElem(1, 3, -6.);
        m.setElem(2, 0, 2.);
        m.setElem(2, 1, -5.);
        m.setElem(2, 2, -7.);
        m.setElem(2, 3, 5.);
        m.setElem(3, 0, -4.);
        m.setElem(3, 1, 3.);
        m.setElem(3, 2, 5.);
        m.setElem(3, 3, -6.);
        assertEquals(18, m.determinant(), 1E-10);
    }

    @Test
    public void testEquals() throws MatrixException {
        Matrix m = new Matrix(2);
        m.setElem(0, 0, 0.);
        m.setElem(0, 1, 3.);
        m.setElem(1, 0, -3.);
        m.setElem(1, 1, 4.);

        Matrix m1 = new Matrix(2);
        m.setElem(0, 0, 1.);
        m.setElem(0, 1, 3.);
        m.setElem(1, 0, -3.);
        m.setElem(1, 1, 4.);
        assertEquals(m.equals(m1),false);
    }

    @Test(expected = MatrixOutOfBoundException.class)
    public void testOutOfBoundsException() throws MatrixException {
        Matrix m = new Matrix(4);
        m.getElem(5,5 );
    }
}