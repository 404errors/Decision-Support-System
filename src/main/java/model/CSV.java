package model;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CSV {
    // _cols and _rows are used to count the maximum number of column and row in the csv file
    private int _cols;
    private int _rows;
    // _maps is used to map the position (determine by row and column number) of the string
    private HashMap<Point, String> _maps = new HashMap<>();

    public static void main(String[] args) {
        CSV test = new CSV();
        try {
            test.open(new File("/home/phucnn/Desktop/sale_jun.csv"));
            test.showContent();
            System.out.print("There are " + test.getNumberOfRow() + " records in this file");
        } catch (FileNotFoundException fileNotFound) {
            System.out.print("Input file was not found!");
        } catch (IOException ioException) {
            System.out.print("Can not read file");
        }
    }

    public void open(File file) throws IOException {
        open(file, ',');
    }

    public void open(File file, char delimiter) throws IOException {
        Scanner fileScanner = new Scanner(file);
        fileScanner.useDelimiter(Character.toString(delimiter));
        clear();
        while (fileScanner.hasNextLine()) {
            //get the next line of the file
            String[] elementsOfCurrentLine = fileScanner.nextLine().split(Character.toString(delimiter));

            //set the column of the first element to zero
            int column = 0;
            for (String element : elementsOfCurrentLine) {
                _maps.put(new Point(_rows, column), element);

                //set the value of maximum column number base on column of current line
                _cols = Math.max(_cols, ++column);
            }
            //increase _rows by 1, this is the number of rows, not the index of rows
            _rows++;
        }
        // close file stream
        fileScanner.close();
    }

    //clear the hashmap and set number of column and row to zero
    private void clear() {
        _maps.clear();
        _cols = 0;
        _rows = 0;
    }

    public int getNumberOfColumn() {
        return _cols;
    }

    public int getNumberOfRow() {
        return _rows;
    }

    public void showContent() {
        for (int i = 0; i < _rows; i++) {
            for (int j = 0; j < _cols; j++) {
                System.out.print(_maps.get(new Point(i, j)) + " ");
            }
            System.out.print("\n");
        }
    }

    public String get(int rowIndex, int columnIndex) {
        return _maps.get(new Point(rowIndex, columnIndex));
    }

    public boolean isEmpty() {
        return _cols == 0 && _rows <= 1;
    }
}
