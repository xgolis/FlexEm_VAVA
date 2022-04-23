package sk.stu.fiit.flexemvavaprojekt.controllers.trener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.PrihlasenyPouzivatel;
import sk.stu.fiit.flexemvavaprojekt.models.Trener;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrenerProfilController implements Initializable {

    @FXML
    private TextField profilTEmailField;

    @FXML
    private TextField profilTHeslo1Field;

    @FXML
    private TextField profilTHeslo2Field;

    @FXML
    private TextField profilTMenoField;

    @FXML
    private TextField profilTPriezviskoField;

    @FXML
    private TextField profilTTelefonField;

    Trener trener = DbConnector.getInstance().getTrener(1);

    @FXML
    protected void odhlasenie() {

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void zobrazRecenzie() {

        try {
            Router.goTo(RouterEnum.TRENERRECENZIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void zobrazRozvrh() {

        try {
            Router.goTo(RouterEnum.TRENERROZVRHVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void zobrazIndividualnyPlan() {

        try {
            Router.goTo(RouterEnum.TRENERINDIVIDUALNYVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
          profilTMenoField.setText(PrihlasenyPouzivatel.getInstance().getPouzivatel().getMeno());
//        profilTPriezviskoField.setText(trener.getPriezvisko());
//        profilTEmailField.setText(trener.getEmail());
//        profilTTelefonField.setText(trener.getTelefonneCislo());
    }
}
