package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
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

public class RecepcnaNovyClenController implements Initializable {

    @FXML
    private TextField novyclenREmailField;
    @FXML
    private TextField novyclenRMenoField;
    @FXML
    private TextField novyclenRPriezviskoField;
    @FXML
    private TextField novyclenRTelefonField;
    @FXML
    private ComboBox<Pouzivatel> novyclenRTrenerComboB;
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
    protected void clenovia() {

        try {
            Router.goTo(RouterEnum.RECEPCNACLENOVIAVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna clenovia view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void miestnosti() {

        try {
            Router.goTo(RouterEnum.RECEPCNAMIESTNOSTIVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna miestnosti view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void recenzia() {

        try {
            Router.goTo(RouterEnum.RECEPCNARECENZIAVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna recenzia view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void profil() {

        try {
            Router.goTo(RouterEnum.RECEPCNAPROFILVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna profil view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void evidenciaVstupu() {

        try {
            Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna evidencia view", e);
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void novyTrener() {

        try {
            Router.goTo(RouterEnum.RECEPCNANOVYTRENERVIEW);
        } catch (IOException e) {
            Main.logger.log(Level.WARNING, "Could not route to recepcna novy trener view", e);
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujTrenerovComboBox(novyclenRTrenerComboB);
}

    @FXML
    protected boolean validateName(){
        String name = novyclenRMenoField.getText();
        if(!InputValidation.validateName(name) || !InputValidation.isSqlInjectionSafe(name)){
            novyclenRMenoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            Main.logger.log(Level.WARNING, "Name validates unsuccessfully");
            return false;
        }
        else {
            novyclenRMenoField.setStyle("-fx-border-width: 0px");
            Main.logger.log(Level.INFO, "Name validates successfully");
            return true;
        }
    }

    @FXML
    protected boolean validateSurname(){
        String surname = novyclenRPriezviskoField.getText();
        if(!InputValidation.validateName(surname) || !InputValidation.isSqlInjectionSafe(surname)){
            novyclenRPriezviskoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            Main.logger.log(Level.WARNING, "Name validates unsuccessfully");
            return false;
        }
        else {
            novyclenRPriezviskoField.setStyle("-fx-border-width: 0px");
            Main.logger.log(Level.INFO, "Name validates successfully");
            return true;
        }
    }

    /**
     * Vzorová funkcia na validáciu TextFieldu volaná onKeyTyped
     * Nastaví border field na červenú ak vstup nie je validný
     * Ak je vstup validný červený border odstráni.
     * @return 'true' ak validný vstup, 'false' ak ivalidný vstup.
     */

    @FXML
    protected boolean validateEmail(){
        String email = novyclenREmailField.getText();
        if(!InputValidation.validateEmail(email) || !InputValidation.isSqlInjectionSafe(email)){
            novyclenREmailField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            Main.logger.log(Level.WARNING, "Email validates unsuccessfully");
            return false;
        }
        else {
            novyclenREmailField.setStyle("-fx-border-width: 0px");
            Main.logger.log(Level.INFO, "Email validates successfully");
            return true;
        }
    }

    @FXML
    protected boolean validatePhoneNumber(){
        String phoneNumber = novyclenRTelefonField.getText();
        if(!InputValidation.validatePhone(phoneNumber) || !InputValidation.isSqlInjectionSafe(phoneNumber)){
            novyclenRTelefonField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            Main.logger.log(Level.WARNING, "Phone validates unsuccessfully");
            return false;

        }
        else {
            novyclenRTelefonField.setStyle("-fx-border-width: 0px");
            Main.logger.log(Level.INFO, "Phone validates successfully");
            return true;
        }
    }

    @FXML
    protected void pridajClena(){
        if(!validateName() || !validateSurname() || !validateEmail() ||  !validatePhoneNumber() || novyclenRTrenerComboB.getValue() == null){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }

        byte[] salt = SpravaHesla.salt();
        String prvotneHeslo = SpravaHesla.vygenerovaneHeslo();
        System.out.println(prvotneHeslo);

        try {
            byte[] clenovHash = SpravaHesla.hash(prvotneHeslo, salt);

            Cvicenec novyClen = new Cvicenec(0, novyclenRMenoField.getText(), novyclenRPriezviskoField.getText(),
                    novyclenREmailField.getText(), novyclenRTelefonField.getText(), novyclenRTrenerComboB.getValue().getId(),
                    clenovHash, salt);

            DbConnector.getInstance().createCvicenec(novyClen);
            System.out.println(novyClen);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("memberadded.key"));

    }
}
