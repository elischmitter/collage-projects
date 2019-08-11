import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Lab5 extends Application {
    /**
     * The start method. Required by Application
     * @param stage
     */
    public void start(Stage stage) {
        Group root = new Group( );
        Scene scene = new Scene( root, 400, 300 );

        Rectangle ground = new Rectangle(0,(2*scene.getHeight( )/3),scene.getWidth( ), (scene.getHeight( )/3));
        ground.setFill(Color.GREEN);
        Circle bush = new Circle(120,200,10);
        bush.setFill(Color.DARKGREEN);
        Circle bush2 = new Circle(120,210,10);
        Circle bush3 = new Circle(120,190,10);
        bush.setFill(Color.DARKGREEN);
        bush2.setFill(Color.DARKGREEN);
        bush3.setFill(Color.DARKGREEN);
        Box house = new Box();
        house.setWidth(100.0);
        house.setHeight(100.0);
        house.setDepth(100.0);
        house.setTranslateX(200);
        house.setTranslateY(200);
        house.setTranslateZ(0);
        house.setRotationAxis(Rotate.Y_AXIS);
        house.setRotate(20);
        house.setRotationAxis(Rotate.X_AXIS);
        house.setRotate(20);
        PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.BLUE);
        PerspectiveCamera camera = new PerspectiveCamera(false);
        camera.setTranslateX(0);
        camera.setTranslateY(0);
        camera.setTranslateZ(0);
        scene.setCamera(camera);

        house.setMaterial(greyMaterial);
        root.getChildren().addAll(ground,bush,bush3,bush2,house);
        stage.setTitle("Chapter 10 Exercises 1");
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