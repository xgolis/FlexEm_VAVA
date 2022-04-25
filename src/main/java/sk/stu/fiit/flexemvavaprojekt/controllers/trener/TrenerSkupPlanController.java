package sk.stu.fiit.flexemvavaprojekt.controllers.trener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sk.stu.fiit.flexemvavaprojekt.Main;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.controllers.MainController;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TrenerSkupPlanController implements Initializable {

    @FXML
    private ChoiceBox<String> skupTCasChoiceB;

    @FXML
    private TableColumn<SkupinovyPlan,String> datumPlanStlpec;

    @FXML
    private TableColumn<SkupinovyPlan,String> izbaPlanStlpec;

    @FXML
    private TableColumn<SkupinovyPlan,String> sportPlanStlpec;

    @FXML
    private TableColumn<SkupinovyPlan,String> trenerPlanStlpec;

    @FXML
    private TableView<SkupinovyPlan> tabulkaTrenerSkup;

    @FXML
    private DatePicker skupTDatumPicker;

    @FXML
    private TextField skupTIzbaField;

    @FXML
    private TextField skupTMenoField;

    @FXML
    private TextField skupTSportField;

    @FXML
    private Label actionLabel;

    @FXML
    private TableColumn<Miestnost, String> izbaPlanStlpec2;

    @FXML
    private TableColumn<Miestnost, String> kapacitaTStlpec;

    @FXML
    private TableColumn<Miestnost, String> sportPlanStlpec2;

    @FXML
    private TableView<Miestnost> tabulkaTrenerIzby;

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
    protected void zobrazIndividualnyPlan() {

        try {
            Router.goTo(RouterEnum.TRENERINDIVIDUALNYVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void vyplnIzbu(){
        Miestnost miestnost = tabulkaTrenerIzby.getSelectionModel().getSelectedItem();
        if (miestnost != null) {
            Inicializator.nastavIzbu(skupTIzbaField, miestnost);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujCasChoiceBox(skupTCasChoiceB);
        Inicializator.inicializujSkupinoveTreningy(tabulkaTrenerSkup,izbaPlanStlpec,trenerPlanStlpec,datumPlanStlpec,sportPlanStlpec);
        Inicializator.inicializujTabulkuMiestnosti(tabulkaTrenerIzby, izbaPlanStlpec2, kapacitaTStlpec, sportPlanStlpec2);
    }

    @FXML
    protected boolean validateRoom(){
        String room = skupTIzbaField.getText();
        if(!InputValidation.validateReview(room) || !InputValidation.isSqlInjectionSafe(room)){
            skupTIzbaField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            skupTIzbaField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateSport(){
        String sport = skupTSportField.getText();
        if(!InputValidation.validateName(sport) || !InputValidation.isSqlInjectionSafe(sport)){
            skupTSportField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;

        }
        else {
            skupTSportField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }


    @FXML
    protected boolean validateName(){
        String name = skupTMenoField.getText();
        if(!InputValidation.validateName(name) || !InputValidation.isSqlInjectionSafe(name)){
            skupTMenoField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            skupTMenoField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected void addTraining(){
        if(!validateRoom() || !validateSport()  ||  !validateName() || skupTCasChoiceB.getValue() == null || skupTDatumPicker.getValue() == null){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }

        Timestamp time = Timestamp.valueOf(skupTDatumPicker.getValue().atTime(LocalTime.parse(skupTCasChoiceB.getValue())));

        int trener_id = PrihlasenyPouzivatel.getInstance().getPouzivatel().getId();
        String trener_meno = PrihlasenyPouzivatel.getInstance().getPouzivatel().getMeno();
        Miestnost miestnost = tabulkaTrenerIzby.getSelectionModel().getSelectedItem();

        SkupinovyPlan plan = new SkupinovyPlan(0, miestnost.getId(), miestnost.getNazov(), trener_id, trener_meno,
                                                skupTSportField.getText(),null, time, false,
                                                skupTMenoField.getText());

        DbConnector.getInstance().createSkupinovyPlan(plan);

        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("trainingadded.key"));
    }
}
