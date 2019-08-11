import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class Lab5Problem2 extends Application {
    /**
     * The start method. Required by Application
     *
     * @param stage
     */
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);
        int x=200;
        for(int y=0; y<150;y += 10) {
            x += 40/3;
                Line line = new Line(200, y, x, 150);
                root.getChildren().add(line);
            }
        for(int y=150; y<300;y += 10) {
            x -= 40/3;
            Line line = new Line(200, y, x, 150);
            root.getChildren().add(line);
        }

        for(int y=300; y>150;y -= 10) {
            x -= 40/3;
            Line line = new Line(200, y, x, 150);
            root.getChildren().add(line);
        }
        for(int y=150; y>0;y -= 10) {
            x += 40/3;
            Line line = new Line(200, y, x, 150);
            root.getChildren().add(line);
        }

        stage.setTitle("My JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
