package sk.stu.fiit.flexemvavaprojekt.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Trieda views od ktorej dedia všetky ostatné views
 * @author jozefvlcek
 */
public class View  {
    protected Stage window;
    protected Scene scene;


    /**
     * Nastavia sa potrebné resource bundle,fxml súbory ako aj veľkosť scény
     * @param fxml hovorí o tom, aký fxml súbor by sa mal načítat
     * @throws IOException
     */
    public View(String fxml) throws IOException {
        this.window = Main.window;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
        fxmlLoader.setResources(ResourceBundle.getBundle("bundle", Jazyk.getInstance().getAktualnyJazyk()));
        this.scene = new Scene(fxmlLoader.load(), 820, 440);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        this.window.setTitle("FlexEm");
        Image icon = new Image(getClass().getResourceAsStream("/sk/stu/fiit/flexemvavaprojekt/img/logo_bicak.png"));
        this.window.getIcons().add(icon) ;

        this.window.setScene(this.scene);
    }

    public void render(){
        this.window.show();
    };

}

