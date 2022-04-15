package sk.stu.fiit.flexemvavaprojekt.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.Main;

import java.io.IOException;

public class RecepcnaEvidenciaView  extends  View{

    private final Stage window;

    public RecepcnaEvidenciaView() throws IOException {

        this.window = Main.window;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("evidencia_vstupu_recepcna.fxml"));
        this.scene = new Scene(fxmlLoader.load(), 820, 440);
        this.window.setTitle("FlexEM");
        this.window.setScene(this.scene);
    }

    @Override
    public void render() throws IOException {
        this.window.show();


    }
}
