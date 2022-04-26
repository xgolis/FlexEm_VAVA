package sk.stu.fiit.flexemvavaprojekt;

import javafx.application.Application;
import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.Cvicenec;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.view.LoginView;
import sk.stu.fiit.flexemvavaprojekt.view.View;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    public static Stage window;
    public final static Logger logger = Logger.getLogger("MojLogger");

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        View view = new LoginView();
        view.render();


    }

    public static void main(String[] args) throws IOException {
        FileHandler fh = new FileHandler("C:\\Users\\tberezny\\Documents\\2_rocnik\\letny_semester\\vava\\FlexEm_VAVA\\flexemLogs.log");
        fh.setLevel(Level.FINER);
        logger.addHandler(fh);
        DbConnector db = new DbConnector();
        launch();
    }
}