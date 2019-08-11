import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Lab5Problem3 extends Application {
        /**
         * The start method. Required by Application
         * @param stage
         */
        public void start(Stage stage) {
            Group root = new Group( );
            Scene scene = new Scene( root, 400, 300 );
            int x = 0;
            for(int y=150;y>=0;y-=10){
                Ellipse ellip =new Ellipse(200,150,x,y);
                ellip.setFill(null);
                ellip.setStroke(Color.BLACK);
                root.getChildren().add(ellip);
                x+=40/3;
            }
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
