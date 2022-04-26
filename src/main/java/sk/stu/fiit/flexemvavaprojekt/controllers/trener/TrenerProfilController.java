package sk.stu.fiit.flexemvavaprojekt.controllers.trener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;

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

    @FXML
    private Label actionLabel;


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
    protected void zobrazRecenzie() {

        try {
            Router.goTo(RouterEnum.TRENERRECENZIAVIEW);
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
            Main.logger.log(Level.WARNING, "Could not route to trener rozvrh view", e);
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
        Inicializator.inicializujProfil(profilTMenoField, profilTPriezviskoField, profilTEmailField, profilTTelefonField);
    }


    @FXML
    protected void changePassword() throws NoSuchAlgorithmException {
        if(profilTHeslo1Field.getText().equals("") || profilTHeslo2Field.getText().equals("")){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("fillallfields.key"));
            return;
        }
        if(profilTHeslo1Field.getText().equals(profilTHeslo2Field.getText())){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("samepassword.key"));
            return;
        }
        byte[] salt = SpravaHesla.salt();
        String heslo = profilTHeslo2Field.getText();
        PrihlasenyPouzivatel.getInstance().zmenHeslo(salt,heslo);
        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("passwordchanged.key"));
    }
}
