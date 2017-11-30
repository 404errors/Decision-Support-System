package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author phucnn
 */
public class VisualizationController implements Initializable {
    private final String SQL_REVENUE_OF_12_MONTHS = "SELECT T.month_name, SUM(F.total_money) AS Revenue " +
            "FROM  sales_fact F INNER JOIN time_dimension T " +
            "ON (F.time_id = T.time_id) " +
            "GROUP BY T.month_name ORDER BY T.month_number ASC;";
    private final String SQL_REVENUE_BY_CATEGORY = "select C.category_name, SUM(F.total_money) as Revenue from sales_fact F INNER JOIN category_dimension C ON (C.category_id = F.category_id) GROUP BY C.category_name";
    public AnchorPane chartContent;
    public AnchorPane container;
    public ComboBox combo_box;

    ;
    CHART_TYPE selectedChart;
    //    public MaterialDesignIconView select_chart;
//    public AnchorPane menu;
    private ResultSet resultSet;
    private Connection connection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        container.getStylesheets().add("/styles/visual.css");
        selectedChart = CHART_TYPE.REVENUE_OF_12_MONTH;
        createConnection();
        makeChart();
        initComboBox();
        // TODO
    }

    private void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sales_prediction?user=root&password=not");
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    private void makeChart() {
        //todo add chart selections, allow users to choose the chart they want
        chartContent.getChildren().clear();
        switch (selectedChart) {
            case REVENUE_OF_12_MONTH:
                makeBarChartOfTwelveMonthRevenue();
                break;
            case REVENUE_BY_CATEGORY:
                makeChartOfCategory();
                break;
            case REVENUE_BY_DL:
                makeChartByCategory("DL");
                break;
            case REVENUE_BY_DT:
                makeChartByCategory("DT");
                break;
            case REVENUE_BY_TV:
                makeChartByCategory("TV");
                break;
            case REVENUE_BY_GD:
                makeChartByCategory("GD");
                break;
        }
//        makeBarChartOfTwelveMonthRevenue();
    }

    private void makeBarChartOfTwelveMonthRevenue() {
        //TODO get data from db
        getData(SQL_REVENUE_OF_12_MONTHS);
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Tháng");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Doanh thu (triệu đồng)");
        // create bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("2013");
        try {
            while (resultSet.next()) {
                dataSeries.getData().add(new XYChart.Data<>(resultSet.getString("month_name"), resultSet.getDouble("Revenue") / 1000000));
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        barChart.getData().add(dataSeries);
        barChart.setTitle("Doanh thu 12 tháng");
        barChart.setPrefSize(950, 500);
        barChart.setStyle("-fx-padding: 30 0 0 0;");
        chartContent.getChildren().clear();
        chartContent.getChildren().add(barChart);
    }

    private void makeChartOfCategory() {
        getData(SQL_REVENUE_BY_CATEGORY);
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Loại hàng");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Doanh thu (triệu đồng)");
        // create bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("2013");
        try {
            while (resultSet.next()) {
                dataSeries.getData().add(new XYChart.Data<>(resultSet.getString("category_name"), resultSet.getDouble("Revenue") / 1000000));
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        barChart.getData().add(dataSeries);
        barChart.setTitle("Doanh thu theo loại hàng");
        barChart.setPrefSize(950, 500);
        barChart.setStyle("-fx-padding: 30 0 0 0;");
        chartContent.getChildren().clear();
        chartContent.getChildren().add(barChart);
    }

    private void makeChartByCategory(String category) {
        String sql = "select T.month_name, SUM(F.total_money) as Revenue from sales_fact F INNER JOIN time_dimension T ON (F.time_id = T.time_id) where F.category_id='" + category + "' group by T.month_name ORDER by T.month_number";
        getData(sql);
        CategoryAxis xAxis = new CategoryAxis();
        String label = category.equals("DL") ? "Điện lạnh" : category.equals("DT") ? "Điện tử" : category.equals("GD") ? "Gia dụng" : "Tivi";

        xAxis.setLabel("Tháng");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Doanh thu (triệu đồng)");
        // create bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName(label);
        try {
            while (resultSet.next()) {
                dataSeries.getData().add(new XYChart.Data<>(resultSet.getString("month_name"), resultSet.getDouble("Revenue") / 1000000));
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        barChart.getData().add(dataSeries);
        barChart.setTitle("Doanh thu theo tháng");
        barChart.setPrefSize(950, 500);
        barChart.setStyle("-fx-padding: 30 0 0 0;");
        chartContent.getChildren().clear();
        chartContent.getChildren().add(barChart);

    }

    private void getData(String sql) {
        try {
            createConnection();
            Statement stmt = connection.createStatement();
            resultSet = stmt.executeQuery(sql);
            stmt.close();
            connection.close();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }

    private void initComboBox() {
        combo_box.getItems().addAll("Doanh thu 12 tháng", "Doanh thu theo loại hàng", "Doanh thu đồ Điện tử", "Doanh thu đồ Điện lạnh", "Doanh thu đồ Gia dụng", "Doanh thu Tivi");
        combo_box.setValue("Doanh thu 12 tháng");
        combo_box.setButtonCell(new ListCell<String>() {
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    setAlignment(Pos.CENTER_RIGHT);
                    Insets old = getPadding();
                    setPadding(new Insets(old.getTop(), 0, old.getBottom(), 0));
                }
            }
        });
    }

    public void changeChart(ActionEvent actionEvent) {
        String seletedChart = (String) combo_box.getValue();
        switch (seletedChart) {
            case "Doanh thu 12 tháng":
                selectedChart = CHART_TYPE.REVENUE_OF_12_MONTH;
                makeChart();
                break;
            case "Doanh thu theo loại hàng":
                selectedChart = CHART_TYPE.REVENUE_BY_CATEGORY;
                makeChart();
                break;
            case "Doanh thu đồ Điện tử":
                selectedChart = CHART_TYPE.REVENUE_BY_DT;
                makeChart();
                break;
            case "Doanh thu đồ Điện lạnh":
                selectedChart = CHART_TYPE.REVENUE_BY_DL;
                makeChart();
                break;
            case "Doanh thu đồ Gia dụng":
                selectedChart = CHART_TYPE.REVENUE_BY_GD;
                makeChart();
                break;
            case "Doanh thu Tivi":
                selectedChart = CHART_TYPE.REVENUE_BY_TV;
                makeChart();
                break;

        }
    }

    private enum CHART_TYPE {REVENUE_OF_12_MONTH, REVENUE_BY_CATEGORY, REVENUE_BY_DT, REVENUE_BY_DL, REVENUE_BY_GD, REVENUE_BY_TV}
}
