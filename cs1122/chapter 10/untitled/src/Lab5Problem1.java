import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Lab5Problem1 extends Application {
    /**
     * The start method. Required by Application
     * @param args
     */
    public void start(Stage stage) {
        Group root = new Group( );
        Scene scene = new Scene( root, 400, 300, Color.BLACK );

        Circle moon = new Circle(150, 75, 60);
        moon.setFill(Color.YELLOW);
        root.getChildren().add(moon);

        Circle shadow = new Circle( 175, 75, 50);
        shadow.setFill(Color.BLACK);
        root.getChildren().add(shadow);

        Circle head = new Circle( 300, 150, 75 );
        head.setFill(Color.rgb(250, 250, 250));
        root.getChildren().add(head);

        Line spine = new Line(300, 225, 300, 300);
        spine.setStroke(Color.rgb(250, 250, 250));
        spine.setStrokeWidth(20);
        root.getChildren().add(spine);

        Line plate = new Line(0, 300, 400, 300);
        plate.setStroke(Color.rgb(250, 250, 250));
        plate.setStrokeWidth(20);
        root.getChildren().add(plate);

        stage.setTitle("Spoopy");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
