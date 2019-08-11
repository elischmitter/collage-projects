import javafx.scene.Group;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javax.sound.sampled.Control;
import javafx.scene.Scene;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import java.util.Arrays;

import static javafx.stage.Stage.*;

public class ControlCircle extends Application {
    /**
     * The start method. Required by Application
     * @param stage
     */
    public void start(Stage stage) {
        Group root = new Group( );
        Scene scene = new Scene( root, 500, 4000 );
                CategoryAxis xAxis = new CategoryAxis();
                xAxis.setCategories(FXCollections.observableArrayList(Arrays.asList("Golden Gate", "Beooklyn", "Delaware Memorial ", "Mackinac")));
                xAxis.setLabel("Bridge Name");
                NumberAxis yAxis = new NumberAxis();
                yAxis.setLabel("Longest Span (ft)");
                BarChart barChart = new BarChart(xAxis, yAxis);
                XYChart.Series dataSeries1 = new XYChart.Series();
                dataSeries1.setName("Golden Gate");
                XYChart.Series dataSeries2 = new XYChart.Series();
                dataSeries2.setName("Brooklyn");
                XYChart.Series dataSeries3 = new XYChart.Series();
                dataSeries3.setName("Delaware Memorial");
                XYChart.Series dataSeries4 = new XYChart.Series();
                dataSeries4.setName("Mackinac");
                dataSeries1.getData().add(new XYChart.Data("Golden Gate",4200));
                dataSeries2.getData().add(new XYChart.Data("Brooklyn",1595));
                dataSeries3.getData().add(new XYChart.Data("Delaware Memorial",2150));
                dataSeries4.getData().add(new XYChart.Data("Mackinac",3800));
                barChart.getData().add(dataSeries1);
                barChart.getData().add(dataSeries2);
                barChart.getData().add(dataSeries3);
                barChart.getData().add(dataSeries4);
                root.getChildren().addAll(barChart);
        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
