package sk.stu.fiit.flexemvavaprojekt.controllers.trener;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.models.Pouzivatel;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TrenerInvidPlanController implements Initializable {


    @FXML
    private ChoiceBox<String> indivTCasChoiceB;

    @FXML
    private TextField indivTCvik1Field;

    @FXML
    private TextField indivTCvik2Field;

    @FXML
    private TextField indivTCvik3Field;

    @FXML
    private TextField indivTCvik4Field;

    @FXML
    private DatePicker indivTDatumPicker;

    @FXML
    private TextField indivTMenoField;

    @FXML
    private TableColumn<Pouzivatel, String> indivTMenoCollum;

    @FXML
    private TableColumn<Pouzivatel, String> indivTPriezviskoCollum;

    @FXML
    private TableView<Pouzivatel> indivTTabulka;

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
    protected void zobrazProfil() {

        try {
            Router.goTo(RouterEnum.TRENERPROFILVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void zobrazRecenzie() {

        try {
            Router.goTo(RouterEnum.TRENERRECENZIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void zobrazRozvrh() {

        try {
            Router.goTo(RouterEnum.TRENERROZVRHVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    protected void zobrazSkupinovyPlan() {

        try {
            Router.goTo(RouterEnum.TRENERSKUPINOVYVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujCasChoiceBox(indivTCasChoiceB);
        Inicializator.inicializujTabulkuTreningovyPlanCvicenci(indivTTabulka, indivTMenoCollum, indivTPriezviskoCollum);

    }


    @FXML
    protected boolean validateExercise1(){
        String exercise = indivTCvik1Field.getText();
        if(!InputValidation.validateExercise(exercise) || !InputValidation.isSqlInjectionSafe(exercise)){
            indivTCvik1Field.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            indivTCvik1Field.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateExercise2(){
        String exercise = indivTCvik2Field.getText();
        if(!InputValidation.validateExercise(exercise) || !InputValidation.isSqlInjectionSafe(exercise)){
            indivTCvik2Field.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            indivTCvik2Field.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateExercise3(){
        String exercise = indivTCvik3Field.getText();
        if(!InputValidation.validateExercise(exercise) || !InputValidation.isSqlInjectionSafe(exercise)){
            indivTCvik3Field.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            indivTCvik3Field.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateExercise4(){
        String exercise = indivTCvik4Field.getText();
        if(!InputValidation.validateExercise(exercise) || !InputValidation.isSqlInjectionSafe(exercise)){
            indivTCvik4Field.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            indivTCvik4Field.setStyle("-fx-border-width: 0px");
            return true;
        }
    }


    @FXML
    protected boolean validateNameTraining(){
        String name = indivTMenoField.getText();
        if(!InputValidation.validateName(name) || !InputValidation.isSqlInjectionSafe(name)){
            indivTMenoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            indivTMenoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected void addTraining(){
        if(!validateExercise1() || !validateExercise2() || !validateExercise3() ||  !validateExercise4() || indivTCasChoiceB.getValue() == null){
            actionLabel.setText("Invalid input");
            return;
        }
        actionLabel.setText("Training added");
    }
}
