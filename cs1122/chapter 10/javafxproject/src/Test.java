import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

    public class Test extends Application {
        @Override // Override the start method in the Application class
        public void start(Stage primaryStage) {
            Button btOK = new Button("OK");
            Button btCancel = new Button("Cancel");

            EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    System.out.println("The OK button is clicked");
                }
            };

            btOK.setOnAction(handler);
            btCancel.setOnAction(handler);

            HBox pane = new HBox(5);
            pane.getChildren().addAll(btOK, btCancel);

            Scene scene = new Scene(pane, 200, 250);
            primaryStage.setTitle("Test"); // Set the stage title
            primaryStage.setScene(scene); // Place the scene in the stage
            primaryStage.show(); // Display the stage
        }

        /**
         * The main method is only needed for the IDE with limited JavaFX
         * support. Not needed for running from the command line.
         */
        public static void main(String[] args) {
            launch(args);
        }
    }

