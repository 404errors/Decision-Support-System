package controller;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author phucnn
 */
public class VisualizationController implements Initializable {
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc =
            new StackedBarChart<String, Number>(xAxis, yAxis);
    final XYChart.Series<String, Number> series1 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series2 =
            new XYChart.Series<String, Number>();
    final XYChart.Series<String, Number> series3 =
            new XYChart.Series<String, Number>();
    public AnchorPane container;

    String query = "SELECT T.month_name, SUM(F.total_money) AS Money FROM  sales_fact F INNER JOIN time_dimension T ON (F.time_id = T.time_id) GROUP BY T.month_name ORDER BY T.month_number ASC;";
    Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeBarChart();
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
    }

    private void makeBarChart() {
//        stage.setTitle("Bar Chart Sample");
        sbc.setTitle("Country Summary");
        xAxis.setLabel("Country");
        xAxis.setCategories(FXCollections.<String>observableArrayList(
                Arrays.asList(austria, brazil, france, italy, usa)));
        yAxis.setLabel("Value");
        series1.setName("2003");
        series1.getData().add(new XYChart.Data<String, Number>(austria, 25601.34));
        series1.getData().add(new XYChart.Data<String, Number>(brazil, 20148.82));
        series1.getData().add(new XYChart.Data<String, Number>(france, 10000));
        series1.getData().add(new XYChart.Data<String, Number>(italy, 35407.15));
        series1.getData().add(new XYChart.Data<String, Number>(usa, 12000));
        series2.setName("2004");
        series2.getData().add(new XYChart.Data<String, Number>(austria, 57401.85));
        series2.getData().add(new XYChart.Data<String, Number>(brazil, 41941.19));
        series2.getData().add(new XYChart.Data<String, Number>(france, 45263.37));
        series2.getData().add(new XYChart.Data<String, Number>(italy, 117320.16));
        series2.getData().add(new XYChart.Data<String, Number>(usa, 14845.27));
        series3.setName("2005");
        series3.getData().add(new XYChart.Data<String, Number>(austria, 45000.65));
        series3.getData().add(new XYChart.Data<String, Number>(brazil, 44835.76));
        series3.getData().add(new XYChart.Data<String, Number>(france, 18722.18));
        series3.getData().add(new XYChart.Data<String, Number>(italy, 17557.31));
        series3.getData().add(new XYChart.Data<String, Number>(usa, 92633.68));
//        Scene scene = new Scene(sbc, 800, 600);
        sbc.getData().addAll(series1, series2, series3);
        container.getChildren().add(sbc);
//        stage.setScene(scene);
//        stage.show();
    }

}
