package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.models.Pouzivatel;
import sk.stu.fiit.flexemvavaprojekt.models.Regex;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecepcnaClenoviaController implements Initializable {

    @FXML
    private TextField clenoviaRMenoField;

    @FXML
    private TextField clenoviaRPriezviskoField;

    @FXML
    private TextField clenoviaRRegIDField;

    @FXML
    private TableColumn<Pouzivatel, String> clenoviaRRegIDStlpec;

    @FXML
    private TableView<Pouzivatel> clenoviaRTabulka;

    @FXML
    private TableColumn<Pouzivatel, String> clenoviaRTelefonStlpec;

    @FXML
    private TableColumn<Pouzivatel, String> clenoviaRPriezviskoStlpec;

    @FXML
    private TableColumn<Pouzivatel, String> clenoviaRMenoStlpec;

    @FXML
    private TableColumn<Pouzivatel, String> clenoviaREmailStlpec;

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

    @FXML
    protected void filtruj(){
        String name = clenoviaRMenoField.getText();
        String surname = clenoviaRPriezviskoField.getText();
        String regId = clenoviaRRegIDField.getText();
        int checker = 0;

        if(!InputValidation.isSqlInjectionSafe(name) || !InputValidation.validateNameFilter(name)){
            checker = 1;
            clenoviaRMenoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
        }
        else {
            clenoviaRMenoField.setStyle("-fx-border-width: 0px");
        }
        if(!InputValidation.isSqlInjectionSafe(surname) || !InputValidation.validateNameFilter(surname)){
            checker = 1;
            clenoviaRPriezviskoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
        }
        else {
            clenoviaRPriezviskoField.setStyle("-fx-border-width: 0px");
        }
        if(!InputValidation.isSqlInjectionSafe(regId) || !InputValidation.validateREGIDFilter(regId)){
            checker = 1;
            clenoviaRRegIDField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
        }
        else {
            clenoviaRRegIDField.setStyle("-fx-border-width: 0px");
        }
        if (checker == 1) {
            return;
        }

        String query = Regex.vytvorQuery(clenoviaRMenoField, clenoviaRPriezviskoField, clenoviaRRegIDField);
        Regex.regexuj(query, clenoviaRTabulka, clenoviaRMenoStlpec, clenoviaRPriezviskoStlpec, clenoviaREmailStlpec, clenoviaRTelefonStlpec, clenoviaRRegIDStlpec);
    }

    @FXML
    protected void vynulujHodnoty(){
        clenoviaRMenoField.setText("");
        clenoviaRPriezviskoField.setText("");
        clenoviaRRegIDField.setText("");
        filtruj();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = Regex.vytvorQuery(clenoviaRMenoField, clenoviaRPriezviskoField, clenoviaRRegIDField);
        Regex.regexuj(query, clenoviaRTabulka, clenoviaRMenoStlpec, clenoviaRPriezviskoStlpec, clenoviaREmailStlpec, clenoviaRTelefonStlpec, clenoviaRRegIDStlpec);
    }


}
