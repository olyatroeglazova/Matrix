package matrix;

import exceptions.MatrixException;
import exceptions.MatrixOutOfBoundException;
import interfaces.IMatrix;

import java.io.*;
import java.util.Scanner;

public class DemoMatrix {

    private static final String FILE_SER = "serialize";

    private static void writeToStream(OutputStream fout, IMatrix m) throws IOException, MatrixException {

        for (int i = 0; i < m.length(); i++) {
            for (int j = 0; j < m.length(); j++) {
                fout.write(Double.toString(m.getElem(i, j)).replace('.', ',').getBytes());
                fout.write(' ');
            }
            fout.write('\n');
        }
    }

    private static void readFromStream(InputStream fin, IMatrix m) throws MatrixOutOfBoundException {
        Scanner scanner = new Scanner(fin);
        for (int i = 0; i < m.length(); i++) {
            for (int j = 0; j < m.length(); j++) {
                if (!scanner.hasNext()) {
                    throw new RuntimeException("Not enough information");
                }
                double value = scanner.nextDouble();
                m.setElem(i, j, value);
            }
        }
    }

    public static double sumAll(IMatrix m) throws MatrixOutOfBoundException {
        double result = 0;
        for (int i = 0; i < m.length(); i++)
            for (int j = 0; j < m.length(); j++)
                result += m.getElem(i, j);
        return result;
    }

    public static void writeMatrix(IMatrix m) throws MatrixOutOfBoundException{
        for (int i = 0; i < m.length(); i++) {
            for (int j = 0; j < m.length(); j++) {
                System.out.print(m.getElem(i, j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args)throws MatrixException, ClassNotFoundException {

        InvertableMatrix matrix2x2 = new InvertableMatrix(2);

        // заполняем матрицу из файла
        try (FileInputStream fin = new FileInputStream("src/main/resources/matrix2x2.txt")) {
            readFromStream(fin, matrix2x2);
        }  catch (FileNotFoundException e) {
            System.err.println("This file doesn't exist.");
        } catch (IOException e) {
            System.err.println("Something is wrong with reading from file.");
        }

        System.out.println("Matrix 2x2: ");
        writeMatrix(matrix2x2);
        System.out.println();

        System.out.println("Sum of elems: " + sumAll(matrix2x2));
        System.out.println("Determinant: " + matrix2x2.determinant());
        System.out.println();

        InvertableMatrix matrix3x3 = new InvertableMatrix(3);

        // заполняем матрицу из файла
        try (FileInputStream fin = new FileInputStream("src/main/resources/matrix3x3.txt")) {
            readFromStream(fin, matrix3x3);
        }  catch (FileNotFoundException e) {
            System.err.println("This file doesn't exist.");
        } catch (IOException e) {
            System.err.println("Something is wrong with reading from file.");
        }

        System.out.println("Matrix 3x3: ");
        writeMatrix(matrix3x3);
        System.out.println();

        System.out.println("Sum of elems: " + sumAll(matrix3x3));
        System.out.println("Determinant: " + matrix3x3.determinant());
        System.out.println();

        InvertableMatrix matrix3x3Inv = matrix3x3.calculateInvertableMatrix();

        System.out.println("Invertable matrix 3x3: ");
        writeMatrix(matrix3x3Inv);
        System.out.println();


        // матрица сериализуется в файл
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_SER))) {
            writeToStream(outputStream, matrix3x3Inv);
        }
        catch (FileNotFoundException e) {
            System.err.println("This file doesn't exist.");
        }
        catch (IOException e) {
            System.err.println("Something is wrong with writing to file.");
        }

        // матрица десериализуется из файла
        Matrix newMatrix = new Matrix(3);
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_SER))) {
            readFromStream(inputStream, newMatrix);
        } catch (FileNotFoundException e) {
            System.err.println("This file doesn't exist.");
        } catch (IOException e) {
            System.err.println("Something is wrong with reading from file.");
        }

        System.out.println("Invertable matrix 3x3 after deserialization: ");
        writeMatrix(newMatrix);
        System.out.println();
    }
}