package sk.stu.fiit.flexemvavaprojekt.controllers.recepcna;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
import sk.stu.fiit.flexemvavaprojekt.models.SkupinovyPlan;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class    RecepcnaMiestnostiController implements Initializable {

    @FXML
    private TableColumn<SkupinovyPlan, String> datumRStlpec;

    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostRStlpec;

    @FXML
    private TableColumn<SkupinovyPlan, String> sportRStlpec;

    @FXML
    private TableColumn<SkupinovyPlan, String> trenerRStlpec;

    @FXML
    private TableView<SkupinovyPlan> miestnostiRTabulka;

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
//        Inicializator.inicializujCasChoiceBox(miestnostiRCasChoiceB);
//        Inicializator.inicializujTrenerovComboBox(miestnostiRTrenerChoiceB);
        Inicializator.inicializujSkupinoveTreningy(miestnostiRTabulka, miestnostRStlpec, trenerRStlpec, datumRStlpec,
                                                        sportRStlpec);
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
        if(!validateRoom() || !validateSport() || !validateDesc() || miestnostiRTrenerChoiceB.getValue() == null || miestnostiRCasChoiceB.getValue() == null){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }
        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("trainingadded.key"));
    }
}
