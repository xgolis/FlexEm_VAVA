package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecepcnaClenoviaController implements Initializable {

    @FXML
    private TextField clenoviaRMenoField;

    @FXML
    private CheckBox clenoviaRTreneriCheckBox;


    @FXML
    private CheckBox clenoviaRCvicenciCheckBox;

    @FXML
    private TextField clenoviaRPriezviskoField;

    @FXML
    private TextField clenoviaRRegIDField;

    @FXML
    protected void odhlasenie() {

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
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
    protected void miestnosti() {

        try {
            Router.goTo(RouterEnum.RECEPCNAMIESTNOSTIVIEW);
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
    protected void profil() {

        try {
            Router.goTo(RouterEnum.RECEPCNAPROFILVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clenoviaRCvicenciCheckBox.setSelected(true);
        clenoviaRTreneriCheckBox.setSelected(true);
        clenoviaRMenoField.setText("bum");
        clenoviaRPriezviskoField.setText("bac");
        clenoviaRRegIDField.setText("1");
    }


    @FXML
    protected boolean validateName(){
        String name = clenoviaRMenoField.getText();
        if(!InputValidation.validateName(name) || !InputValidation.isSqlInjectionSafe(name)){
            clenoviaRMenoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            clenoviaRMenoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateSurname(){
        String surname = clenoviaRPriezviskoField.getText();
        if(!InputValidation.validateName(surname) || !InputValidation.isSqlInjectionSafe(surname)){
            clenoviaRPriezviskoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            clenoviaRPriezviskoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateRegid(){
        String regId = clenoviaRRegIDField.getText();
        if(!InputValidation.validateDigit(regId) || !InputValidation.isSqlInjectionSafe(regId)){
            clenoviaRRegIDField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            clenoviaRRegIDField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }
}
