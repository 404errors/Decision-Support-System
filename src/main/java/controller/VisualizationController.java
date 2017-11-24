package controller;

import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author phucnn
 */
public class VisualizationController implements Initializable {

    String query = "SELECT T.month_name, SUM(F.total_money) AS Money FROM  sales_fact F INNER JOIN time_dimension T ON (F.time_id = T.time_id) GROUP BY T.month_name ORDER BY T.month_number ASC;";
    Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createConnection();
        // TODO
    }

    BarChart<String, Number> getChartOfTwelveMonths() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue");
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        return barChart;
    }

    private void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sales_prediction", "root", "not");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("month_name") + "  " + resultSet.getDouble("Money"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

}
