package sk.stu.fiit.flexemvavaprojekt.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.Main;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class View  {
    protected Stage window;
    protected Scene scene;


    public View(String fxml) throws IOException {
        this.window = Main.window;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        Locale l = new Locale("SK");
        fxmlLoader.setResources(ResourceBundle.getBundle("bundle",l));
        this.scene = new Scene(fxmlLoader.load(), 820, 440);
        this.window.setTitle("FlexEM");
        this.window.setScene(this.scene);
    }

    public void render(){
        this.window.show();
    };

}

