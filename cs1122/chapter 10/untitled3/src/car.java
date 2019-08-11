import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class car extends Application{
    public void start( Stage stage ) {
        Pane canvas = new Pane( );
        Image cari1 = null;
        Image cari2 =null;
        try{
            cari1 = new Image(new FileInputStream("car1.jpg"));
            cari2= new Image(new FileInputStream("car2.jpg"));
        }catch (FileNotFoundException e){

        }
        ImageView car1 = new ImageView(cari1);
        ImageView car2 = new ImageView(cari2);
        car1.setFitHeight(45);
        car1.setFitWidth(50);
        car2.setScaleX(-1);
        car2.setFitHeight(45);
        car2.setFitWidth(50);
        car1.setX(50);
        car1.setY(45);
        car1.setX(300-50);
        car1.setY(300-45);
        car1.setPreserveRatio(true);
        car2.setPreserveRatio(true);
        canvas.getChildren().addAll(car1,car2);
        //Setting title to the Stage
        Timeline timeline = new Timeline( new KeyFrame( Duration.millis( 20 ),event -> {
        car2.setLayoutX( car2.getLayoutX( ) + 1 );
        car1.setLayoutX( car1.getLayoutX( ) - 1 );
        if (car1.getLayoutX()<-250){
            car1.setLayoutX(25);
            car2.setLayoutX(50);
        }
        }));

        //Adding scene to the stage
        Scene scene = new Scene( canvas, 320, 300, Color.WHITE );
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
        timeline.setCycleCount( Timeline.INDEFINITE );
        timeline.play( );
    }

    public static void main( String[] args ) {
        launch( );
    }
}
