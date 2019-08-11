import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class font extends Application {
    public void start(Stage stage) {
        VBox vbox = new VBox();
        Text ln1 = new Text();
        ln1.setText("1234567890-=\nqwertyuiop[]\\\nasdfghjkl;'\n!@#$%^&*()_+\nQWERTYUIOP{}|\nASDFGHJKL:\"\nZXCVBNM<>?");
        ln1.setFont(new Font(30));
        MenuBar menuBar = new MenuBar();
        Menu FontMen= new Menu("Font");
        ArrayList<MenuItem> fountMenu = new ArrayList<>();
        ArrayList<String> founts = new ArrayList<>(Font.getFontNames());
        stage.setTitle("Font Viewer:"+ln1.getFont().getName());
        for (String i : founts) {
            MenuItem temp = new MenuItem(i);
            fountMenu.add(temp);
/*            fountMenu.add(new MenuItem("3"));*/

        }
        for(int i=0;i<founts.size();i++)
        {
            String temp =founts.get(i);
            fountMenu.get(i).setOnAction(e -> ln1.setFont(Font.font(temp,30)));
        }
        for( MenuItem i : fountMenu){
            FontMen.getItems().add(i);
        }

        menuBar.getMenus().addAll(FontMen);

        vbox.getChildren().addAll(menuBar, ln1);
        Scene scene = new Scene(vbox, 320, 300, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}