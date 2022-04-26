package sk.stu.fiit.flexemvavaprojekt.controllers.trener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.PrihlasenyPouzivatel;
import sk.stu.fiit.flexemvavaprojekt.models.Recenzia;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class TrenerRecenziaController implements Initializable {
    @FXML
    private TextField recenziaTHviezdickyField;

    @FXML
    private TextField recenziaTMenoField;

    @FXML
    private TextField recenziaTPriezviskoField;

    @FXML
    private TextArea recenziaTRecenziaField;

    @FXML
    private TextField recenziaTSportField;

    @FXML
    private TableColumn<Recenzia, String> recenziaTHvStlpec;

    @FXML
    private TableColumn<Recenzia, String> recenziaTMenoStlpec;

    @FXML
    private TableColumn<Recenzia, String> recenziaTPriezviskoStlpec;

    @FXML
    private TableColumn<Recenzia, String> recenziaTSportStlpec;

    @FXML
    private TableView<Recenzia> recenziaTTabulka;

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
    protected void zobrazRozvrh() {

        try {
            Router.goTo(RouterEnum.TRENERROZVRHVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to trener profil view", e);
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

    @FXML
    protected void nastavHodnotyZTabulky() {
        Recenzia recenzia = recenziaTTabulka.getSelectionModel().getSelectedItem();
        if (recenzia != null) {
            Inicializator.nastavRRecenzie(recenziaTMenoField,recenziaTPriezviskoField,recenziaTSportField,recenziaTHviezdickyField,
                    recenziaTRecenziaField,recenzia);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujTabulkuRecenzii(recenziaTTabulka, recenziaTMenoStlpec, recenziaTPriezviskoStlpec,
                                                 recenziaTSportStlpec, recenziaTHvStlpec, PrihlasenyPouzivatel.getInstance().getPouzivatel().getId());


    }
}
