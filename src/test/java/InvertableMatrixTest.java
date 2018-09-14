import matrix.InvertableMatrix;
import matrix.Matrix;
import exceptions.MatrixOutOfBoundException;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvertableMatrixTest {
    @Test
    public void calculateInvertableMatrix() throws Exception {



        InvertableMatrix m = new InvertableMatrix(3);

        m.setElem(0, 0, 2.);
        m.setElem(0, 1, 5.);
        m.setElem(0, 2, 7.);
        m.setElem(1, 0, 6.);
        m.setElem(1, 1, 3.);
        m.setElem(1, 2, 4.);
        m.setElem(2, 0, 5.);
        m.setElem(2, 1, -2.);
        m.setElem(2, 2, -3.);

        InvertableMatrix inv = new InvertableMatrix(m.calculateInvertableMatrix());

        InvertableMatrix e = new InvertableMatrix(3);
        for (int i = 0; i < e.length(); i++) {
            e.setElem(i, i, 1);
        }

        InvertableMatrix mult = m.mult(inv);

        for(int i = 0; i<m.length(); i++){
            for(int j = 0; j<m.length(); j++){
                assertEquals(mult.getElem(i,j), e.getElem(i,j), 1E-9);
            }
        }
    }



}