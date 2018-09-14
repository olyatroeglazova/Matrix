package interfaces;
import exceptions.MatrixOutOfBoundException;

public interface IMatrix {
    public  int length();
    public double getElem(int i, int j) throws MatrixOutOfBoundException;
    public void setElem(int i, int j, double elem) throws MatrixOutOfBoundException;
    public double determinant() throws MatrixOutOfBoundException;
}
