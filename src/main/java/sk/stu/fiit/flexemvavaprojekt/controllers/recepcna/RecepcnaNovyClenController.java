package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private ChoiceBox<String> novyclenRTrenerChoiceB;
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
    protected void recenzia() {

        try {
            Router.goTo(RouterEnum.RECEPCNARECENZIAVIEW);
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

    @FXML
    protected void novyTrener() {

        try {
            Router.goTo(RouterEnum.RECEPCNANOVYTRENERVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Inicializator.inicializujTrenerovChoiceBox(novyclenRTrenerChoiceB);
    }

    @FXML
    protected boolean validateName(){
        String name = novyclenRMenoField.getText();
        if(!InputValidation.validateName(name) || !InputValidation.isSqlInjectionSafe(name)){
            novyclenRMenoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            novyclenRMenoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateSurname(){
        String surname = novyclenRPriezviskoField.getText();
        if(!InputValidation.validateName(surname) || !InputValidation.isSqlInjectionSafe(surname)){
            novyclenRPriezviskoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            novyclenRPriezviskoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateEmail(){
        String email = novyclenREmailField.getText();
        if(!InputValidation.validateEmail(email) || !InputValidation.isSqlInjectionSafe(email)){
            novyclenREmailField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            novyclenREmailField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validatePhoneNumber(){
        String phoneNumber = novyclenRTelefonField.getText();
        if(!InputValidation.validatePhone(phoneNumber) || !InputValidation.isSqlInjectionSafe(phoneNumber)){
            novyclenRTelefonField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            novyclenRTelefonField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected void pridajClena(){
        if(!validateName() || !validateSurname() || !validateEmail() ||  !validatePhoneNumber() || novyclenRTrenerChoiceB.getValue() == null){
            actionLabel.setText("Invalid input");
            return;
        }
        actionLabel.setText("Member added");
    }
}
