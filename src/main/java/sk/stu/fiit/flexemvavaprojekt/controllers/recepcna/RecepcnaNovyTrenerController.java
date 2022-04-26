package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class RecepcnaNovyTrenerController implements Initializable {

    @FXML
    private TextField novytrenerREmailField;
    @FXML
    private TextField novytrenerRMenoField;
    @FXML
    private TextField novytrenerRPriezviskoField;
    @FXML
    private TextField novytrenerRSportField;
    @FXML
    private TextField novytrenerRTelefonField;
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
    protected void profil() {

        try {
            Router.goTo(RouterEnum.RECEPCNAPROFILVIEW);
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    protected boolean validateName(){
        String name = novytrenerRMenoField.getText();
        if(!InputValidation.validateName(name) || !InputValidation.isSqlInjectionSafe(name)){
            novytrenerRMenoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            novytrenerRMenoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateSurname(){
        String surname = novytrenerRPriezviskoField.getText();
        if(!InputValidation.validateName(surname) || !InputValidation.isSqlInjectionSafe(surname)){
            novytrenerRPriezviskoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            novytrenerRPriezviskoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateEmail(){
        String email = novytrenerREmailField.getText();
        if(!InputValidation.validateEmail(email) || !InputValidation.isSqlInjectionSafe(email)){
            novytrenerREmailField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            novytrenerREmailField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validatePhoneNumber(){
        String phoneNumber = novytrenerRTelefonField.getText();
        if(!InputValidation.validatePhone(phoneNumber) || !InputValidation.isSqlInjectionSafe(phoneNumber)){
            novytrenerRTelefonField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            novytrenerRTelefonField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateSport(){
        String sport = novytrenerRSportField.getText();
        if(!InputValidation.validateName(sport) || !InputValidation.isSqlInjectionSafe(sport)){
            novytrenerRSportField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            novytrenerRSportField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected void pridajTrenera(){
        if(!validateName() || !validateSurname() || !validateEmail() ||  !validatePhoneNumber() || !validateSport()){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }
        byte[] salt = SpravaHesla.salt();
        String prvotneHeslo = SpravaHesla.vygenerovaneHeslo();
        System.out.println(prvotneHeslo);

        try {
            byte[] clenovHash = SpravaHesla.hash(prvotneHeslo, salt);

            Trener novyClen = new Trener(0, novytrenerRMenoField.getText(), novytrenerRPriezviskoField.getText(),
                    novytrenerREmailField.getText(), novytrenerRTelefonField.getText(), novytrenerRSportField.getText(),
                    clenovHash, salt);

            DbConnector.getInstance().createTrener(novyClen);
            System.out.println(novyClen);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("memberadded.key"));
    }
}
