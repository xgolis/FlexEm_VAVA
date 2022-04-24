package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.models.PrihlasenyPouzivatel;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
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
    protected void changePassword(){
        if(profilRHeslo1Field.getText().equals("") || profilRHeslo2Field.getText().equals("")){
            actionLabel.setText("Fill all fields !");
            return;
        }
        if(profilRHeslo1Field.getText().equals(profilRHeslo2Field.getText())){
            actionLabel.setText("Cant change to the same password");
            return;
        }
        actionLabel.setText("Password changed");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profilREmailField.setText("toto na heslo treba prerobit");
        profilRMenoField.setText(PrihlasenyPouzivatel.getInstance().getPouzivatel().getMeno());
    }
}
