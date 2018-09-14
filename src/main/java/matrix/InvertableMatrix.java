package matrix;

import exceptions.MatrixOutOfBoundException;
import exceptions.ZeroDeterminantException;
import interfaces.IInvertableMatrix;

public class InvertableMatrix extends Matrix implements IInvertableMatrix {

    public InvertableMatrix(final int size) {
        super(size);
    }

   public InvertableMatrix(final Matrix m) throws ZeroDeterminantException, MatrixOutOfBoundException {
        super(m);
        if (Math.abs(this.determinant())<1E-9) {
            throw new ZeroDeterminantException("Error. Determinant is zero");
        }
    }
    public InvertableMatrix calculateInvertableMatrix() throws ZeroDeterminantException, MatrixOutOfBoundException {

        double multiplier;
        int notNull;
        InvertableMatrix newMatrix = new InvertableMatrix(this.length());
        InvertableMatrix temp = new InvertableMatrix(this);

        for (int i = 0; i < newMatrix.length(); i++) {
            newMatrix.setElem(i, i, 1.);
        }

        for (int i = 0; i < temp.length(); i++) {

            //если элемент на главной диагонали равен 0, то ищем строку с ненулевым элементом
            //в данном столбце и прибавляем строку
            if (temp.getElem(i, i) == 0) {
                notNull = temp.firstNotNullBelowZeroElem(i);
                if (notNull == -1) {
                    throw new ZeroDeterminantException("Matrix can't be inverted");
                } else {
                    for (int j = i; j < temp.length(); j++) {
                        temp.setElem(i, j, temp.getElem(i, j) + temp.getElem(notNull, j));
                        newMatrix.setElem(i, j, newMatrix.getElem(i, j) + newMatrix.getElem(notNull, j));
                    }
                }
            }

            //приводим к верхнетреугольному виду
            for (int nextStr = i + 1; nextStr < temp.length(); nextStr++) {
                if (temp.getElem(i, i) != 0) {
                    multiplier = temp.getElem(nextStr, i) / temp.getElem(i, i);
                    temp.setElem(nextStr, i, 0.); //зануляем j-й элемент i-й строки
                    for (int k = i + 1; k < temp.length(); k++) { //считаем остальные элементы строки
                        temp.setElem(nextStr, k, temp.getElem(nextStr, k) - temp.getElem(i, k) * multiplier);
                    }
                    for (int k = 0; k < temp.length(); k++) {
                        newMatrix.setElem(nextStr, k, newMatrix.getElem(nextStr, k) - newMatrix.getElem(i, k) * multiplier);
                    }
                }
            }
        }

        //зануляем элементы выше гланой диагонали
        for (int i = temp.length() - 1; i >= 0; i--) {
            if (temp.getElem(i, i) == 0) {
                notNull = temp.firstNotNullAboveZeroElem(i);
                if (notNull == -1) {
                    throw new ZeroDeterminantException("Matrix can not be inverted");
                } else {
                    for (int j = i; j >= 0; j--) {
                        temp.setElem(i, j, temp.getElem(i, j) + temp.getElem(notNull, j));
                        newMatrix.setElem(i, j, newMatrix.getElem(i, j) + newMatrix.getElem(notNull, j));
                    }
                }
            }

            for (int prevStr = i - 1; prevStr >= 0; prevStr--) {
                if (temp.getElem(i, i) != 0) {
                    multiplier = temp.getElem(prevStr,i) / temp.getElem(i, i);
                    temp.setElem(prevStr,i, 0.);
                    for (int k = i - 1; k >= 0; k--) {
                        temp.setElem(prevStr, k,temp.getElem(prevStr, k) - temp.getElem(i, k) * multiplier);
                    }

                    for (int k = temp.length() - 1; k >= 0; k--) {
                        newMatrix.setElem(prevStr, k, newMatrix.getElem(prevStr, k) - newMatrix.getElem(i, k) * multiplier);
                    }
                }
            }
        }

        //приводим мтрицу к единичной
        for (int i = 0; i < newMatrix.length(); i++) {
            multiplier = 1 / temp.getElem(i, i);
            for (int j = 0; j < newMatrix.length(); j++) {
                newMatrix.setElem(i, j, newMatrix.getElem(i, j) * multiplier);
                temp.setElem(i, j, temp.getElem(i, j) * multiplier);
            }
        }

        return newMatrix;
    }
    private int firstNotNullBelowZeroElem(int j) throws MatrixOutOfBoundException {
        for (int i = j; i < this.length(); i++) {
            if (this.getElem(i, j) != 0) {
                return i;
            }
        }
        return -1;
    }

    private int firstNotNullAboveZeroElem(int j) throws MatrixOutOfBoundException {
        for (int i = j-1; i >= 0; i--) {
            if (this.getElem(i, j) != 0) {
                return i;
            }
        }
        return -1;
    }

    public InvertableMatrix mult(InvertableMatrix matr) throws MatrixOutOfBoundException {
        InvertableMatrix res = new InvertableMatrix(matr.length());
        for (int i=0; i< matr.length(); i++) {
            for(int j=0; j<matr.length(); j++) {
                double temp = 0;
                for(int k = 0; k < matr.length(); k++) {
                    temp+=this.getElem(i,k) * matr.getElem(k,j);
                }
                res.setElem(i,j, temp);
            }
        }
        return res;
    }
}
