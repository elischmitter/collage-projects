import javafx.scene.Group;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Chapter_10_Exercise_2 extends Application {
    /**
     * The main method
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method. Required by Application
     *
     * @param stage
     */
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Bridge Name");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Longest Span (ft)");
        BarChart<String, Number>  barChart = new BarChart<>(xAxis, yAxis);
        XYChart.Series<String, Number>  dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Golden Gate");
        XYChart.Series<String, Number>  dataSeries2 = new XYChart.Series<>();
        dataSeries2.setName("Brooklyn");
        XYChart.Series<String, Number>  dataSeries3 = new XYChart.Series<>();
        dataSeries3.setName("Delaware Memorial");
        XYChart.Series<String, Number>  dataSeries4 = new XYChart.Series<>();
        dataSeries4.setName("Mackinac");
        dataSeries1.getData().add(new XYChart.Data<>(" ", 4200));
        dataSeries2.getData().add(new XYChart.Data<>(" ", 1595));
        dataSeries3.getData().add(new XYChart.Data<>(" ", 2150));
        dataSeries4.getData().add(new XYChart.Data<>(" ", 3800));
        barChart.getData().addAll(dataSeries1,dataSeries2,dataSeries3,dataSeries4);
        barChart.setTitle("Bridges");
        root.getChildren().addAll(barChart);
        stage.setTitle("Bridge");
        stage.setScene(scene);
        stage.show();
    }
}
