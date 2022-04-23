package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
