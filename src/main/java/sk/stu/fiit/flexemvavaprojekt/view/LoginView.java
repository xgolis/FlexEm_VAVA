package sk.stu.fiit.flexemvavaprojekt.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.view.View;

import java.io.IOException;

public class LoginView extends View {

    private final Stage window;

    public LoginView() throws IOException {

        this.window = Main.window;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        this.scene = new Scene(fxmlLoader.load(), 820, 440);
        this.window.setTitle("FlexEM");
        this.window.setScene(this.scene);
    }

    @Override
    public void render() throws IOException {
            this.window.show();


    }




}
