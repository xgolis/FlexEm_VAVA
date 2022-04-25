package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.models.PrihlasenyPouzivatel;
import sk.stu.fiit.flexemvavaprojekt.models.SpravaHesla;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class RecepcnaProfilController implements Initializable {

    @FXML
    private TextField profilREmailField;
    @FXML
    private PasswordField profilRHeslo1Field;
    @FXML
    private PasswordField profilRHeslo2Field;
    @FXML
    private TextField profilRMenoField;
    @FXML
    private TextField profilRPriezviskoField;
    @FXML
    private TextField profilRTelefonField;
    @FXML
    private Label actionLabel;

    @FXML
    protected void odhlasenie() {

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void novyClen() {

        try {
            Router.goTo(RouterEnum.RECEPCNANOVYCLENVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void recenzia() {

        try {
            Router.goTo(RouterEnum.RECEPCNARECENZIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void clenovia() {

        try {
            Router.goTo(RouterEnum.RECEPCNACLENOVIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void miestnosti() {

        try {
            Router.goTo(RouterEnum.RECEPCNAMIESTNOSTIVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void evidenciaVstupu() {

        try {
            Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    protected void changePassword() throws NoSuchAlgorithmException {
        if(profilRHeslo1Field.getText().equals("") || profilRHeslo2Field.getText().equals("")){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("fillallfields.key"));
            return;
        }
        if(profilRHeslo1Field.getText().equals(profilRHeslo2Field.getText())){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("samepassword.key"));
            return;
        }
        byte[] salt = SpravaHesla.salt();
        String heslo = profilRHeslo2Field.getText();
        PrihlasenyPouzivatel.getInstance().zmenHeslo(salt,heslo);
        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("passwordchanged.key"));

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujProfil(profilRMenoField, profilRPriezviskoField, profilREmailField, profilRTelefonField);
    }
}
