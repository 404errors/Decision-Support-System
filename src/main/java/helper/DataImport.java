package helper;

import model.CSV;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataImport {
    public static void main(String args[]) {
        CSV myCSV = new CSV();
        try {
            myCSV.open(new File("/home/phucnn/Desktop/sale_jun.csv"));

        } catch (Exception e) {
            System.out.print("Cannot read file");
        }
        //if CSV is not empty, insert into database
        if (!myCSV.isEmpty()) {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "");
                Statement statement = connection.createStatement();
                for (int i = 1; i < myCSV.getNumberOfRow(); i++) {
                    String query = "INSERT INTO sales values (" + myCSV.get(i, 0) + ", "
                            + "'" + myCSV.get(i, 1) + "', "
                            + "'" + myCSV.get(i, 2) + "', "
                            + "'" + myCSV.get(i, 4) + "', "
                            + "'" + myCSV.get(i, 5) + "', "
                            + "'" + myCSV.get(i, 6) + "', "
                            + "'" + myCSV.get(i, 7) + "', "
                            + myCSV.get(i, 8) + ", "
                            + myCSV.get(i, 9) + ", "
                            + myCSV.get(i, 10)
                            + ");";
                    statement.executeQuery(query);
                }

            } catch (SQLException e) {
                System.out.print(e.getMessage());
            }
        }
    }

}