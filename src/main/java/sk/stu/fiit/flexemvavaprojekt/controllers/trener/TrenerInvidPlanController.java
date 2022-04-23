package sk.stu.fiit.flexemvavaprojekt.controllers.trener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.models.Inicializator;
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
    private TextField indivTCvik4Field;

    @FXML
    private DatePicker indivTDatumPicker;

    @FXML
    private TextField indivTMenoField;

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

    }
}
