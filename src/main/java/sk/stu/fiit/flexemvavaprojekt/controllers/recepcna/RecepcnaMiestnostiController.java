package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class    RecepcnaMiestnostiController implements Initializable {

    @FXML
    private DatePicker miestnostiRDateTimePicker;

    @FXML
    private TextField miestnostiRIzbaField;

    @FXML
    private TextField miestnostiRPopisField;

    @FXML
    private TextField miestnostiRSportField;

    @FXML
    private ChoiceBox<String> miestnostiRTrenerChoiceB;

    @FXML
    private ChoiceBox<String> miestnostiRCasChoiceB;
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
    protected void evidenciaVstupu() {

        try {
            Router.goTo(RouterEnum.RECEPCNAEVIDENCIAVIEW);
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
    protected void clenovia() {

        try {
            Router.goTo(RouterEnum.RECEPCNACLENOVIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujCasChoiceBox(miestnostiRCasChoiceB);
        Inicializator.inicializujTrenerovChoiceBox(miestnostiRTrenerChoiceB);
    }

    @FXML
    protected boolean validateRoom(){
        String room = miestnostiRIzbaField.getText();
        if(!InputValidation.validateReview(room) || !InputValidation.isSqlInjectionSafe(room)){
            miestnostiRIzbaField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            miestnostiRIzbaField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateDate(){
        String localDate = miestnostiRDateTimePicker.getValue().toString();
        System.out.println(localDate);
        if(!InputValidation.validateDate(localDate) || !InputValidation.isSqlInjectionSafe(localDate)){
            miestnostiRDateTimePicker.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            miestnostiRDateTimePicker.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateDesc(){
        String desc = miestnostiRPopisField.getText();
        if(!InputValidation.validateReview(desc) || !InputValidation.isSqlInjectionSafe(desc)){
            miestnostiRPopisField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            miestnostiRPopisField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateSport(){
        String sport = miestnostiRSportField.getText();
        if(!InputValidation.validateName(sport) || !InputValidation.isSqlInjectionSafe(sport)){
            miestnostiRSportField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            miestnostiRSportField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected void addTraining(){
        if(!validateRoom() || !validateSport() || !validateDate() ||  !validateDesc() || miestnostiRTrenerChoiceB.getValue() == null || miestnostiRCasChoiceB.getValue() == null){
            actionLabel.setText("Invalid input");
            return;
        }
        actionLabel.setText("Member added");
    }
}
