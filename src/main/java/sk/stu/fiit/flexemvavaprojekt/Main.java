package sk.stu.fiit.flexemvavaprojekt;

import javafx.application.Application;
import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.SpravaHesla;
import sk.stu.fiit.flexemvavaprojekt.view.LoginView;
import sk.stu.fiit.flexemvavaprojekt.view.View;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {

    public static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        View view = new LoginView();
        view.render();


    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        DbConnector db = new DbConnector();
        launch();
    }
}