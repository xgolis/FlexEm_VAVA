package sk.stu.fiit.flexemvavaprojekt.controllers.trener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.Plan;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class TrenerRozvrhController implements Initializable {

    @FXML
    private TableColumn<Plan, String> rozvrhTDatumStlpec;

    @FXML
    private TableColumn<Plan, String> rozvrhTIzbaStlpec;

    @FXML
    private TableColumn<Plan, String> rozvrhTNazovStlpec;

    @FXML
    private TableColumn<Plan, String> rozvrhTSportStlpec;

    @FXML
    private TableView<Plan> rozvrhTTabulka;


    @FXML
    protected void odhlasenie() {

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to login view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void zobrazProfil() {

        try {
            Router.goTo(RouterEnum.TRENERPROFILVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to trener profil view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void zobrazRecenzie() {

        try {
            Router.goTo(RouterEnum.TRENERRECENZIAVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to trener recenzia view", e);
            throw new RuntimeException(e);
        }
    }


    @FXML
    protected void zobrazIndividualnyPlan() {

        try {
            Router.goTo(RouterEnum.TRENERINDIVIDUALNYVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to trener individualny plan view", e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujMojRozvrh(rozvrhTTabulka, rozvrhTIzbaStlpec, rozvrhTNazovStlpec, rozvrhTSportStlpec,
                                           rozvrhTDatumStlpec);

    }
}
