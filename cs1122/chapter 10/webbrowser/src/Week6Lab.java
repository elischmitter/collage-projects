
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.stage.Stage;
import javafx.concurrent.Worker.State;
import javafx.concurrent.Worker;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
/**
 *  The main class for Week6Lab. Week6Lab constructs the JavaFX window and
 *  handles interactions with the dynamic components contained therein.
 */
public class Week6Lab extends Application {
    // INSTANCE VARIABLES
// These variables are included to get you started.
    private Stage stage = null;
    private BorderPane borderPane = null;
    private WebView view = null;
    private WebEngine webEngine = null;
    private TextField statusbar = null;
// HELPER METHODS

    /**
     * The main( ) method is ignored in JavaFX applications.
     * main( ) serves only as fallback in case the application is
     launched
     * as a regular Java application, e.g., in IDEs with limited FX
     * support.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Retrieves the value of a command line argument specified by the
     index.
     *
     * @param index - position of the argument in the args list.
     * @return The value of the command line argument.
     */
    private String getParameter( int index ) {
        Parameters params = getParameters();
        List<String> parameters = params.getRaw();
        return !parameters.isEmpty() ? parameters.get(index) :
                "";
    }

    /**
     * Creates a WebView which handles mouse and some keyboard events,
     and
     * manages scrolling automatically, so there's no need to put it
     into a ScrollPane.
     * The associated WebEngine is created automatically at construction
     time.
     *
     * @return browser - a WebView container for the WebEngine.
     */
    private WebView makeHtmlView( ) {
        view = new WebView();
        webEngine = view.getEngine();
        return view;
    }
// REQUIRED METHODS

    /**
     * Generates the status bar layout and text field.
     *
     * @return statusbarPane - the HBox layout that contains the
    statusbar.
     */
    private HBox makeStatusBar(){
        HBox statusbarPane = new HBox();
        statusbarPane.setPadding(new Insets(5, 4, 5, 4));
        statusbarPane.setSpacing(10);
        statusbarPane.setStyle("-fx-background-color: #336699;");
        return statusbarPane;
    }

    /**
     * The main entry point for all JavaFX applications. The start
     method is
     * called after the init method has returned, and after the system
     is ready
     * for the application to begin running.
     *
     * NOTE: This method is called on the JavaFX Application Thread.
     *
     * @param stage - the primary stage for this application,
    onto which
     * the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        ArrayList<String>history=new ArrayList<>();
        Button back = new Button("<=");
        Button forward = new Button("=>");
        Button help = new Button("?");
        statusbar = new TextField();
        statusbar.setOnAction(event -> {
           String url= statusbar.getText();
                if(!(url.startsWith("https://")||url.startsWith("http://"))){
                    url="https://"+url;
                }
                else{
                    webEngine.load(url);
                }
            }
        );
        HBox.setHgrow(statusbar, Priority.ALWAYS);
        back.setOnAction(event ->{
            if (webEngine.getHistory().getCurrentIndex()>0){
                webEngine.getHistory().go(-1);
        }
        }
        );
        forward.setOnAction(event -> {
            if (webEngine.getHistory().getCurrentIndex()<webEngine.getHistory().getEntries().size()-1){
                webEngine.getHistory().go(-1);
            }
        });
        help.setOnAction(event -> {});
        makeHtmlView();
        borderPane=new BorderPane();
        try{
            webEngine.load(getParameter(0));
        }catch (IndexOutOfBoundsException e){
            webEngine.load("https://google.com");
        }
        webEngine.getLoadWorker().stateProperty().addListener(event ->
                    stage.setTitle(webEngine.getTitle())
            );
        borderPane.setCenter(view);
        HBox sb = makeStatusBar();
        sb.getChildren().addAll(back,forward,help,statusbar);
        borderPane.setTop(sb);
        history.add(webEngine.getLocation());
        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}

