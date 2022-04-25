package sk.stu.fiit.flexemvavaprojekt.controllers.cvicenec;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.IndividualnyPlan;
import sk.stu.fiit.flexemvavaprojekt.models.SkupinovyPlan;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CvicenecPlanController implements Initializable {

    @FXML
    private TextField trainplanCCvik1Field;

    @FXML
    private TableView<IndividualnyPlan> planCTabulka;

    @FXML
    private TableColumn<IndividualnyPlan,String> planCDenStlpec;

    @FXML
    private TableColumn<IndividualnyPlan,String> planCNazovStlpec;

    @FXML
    private TextField trainplanCCvik2Field;

    @FXML
    private RadioButton planCRButtonCompleted;

    @FXML
    private RadioButton planCRButtonUpcoming;

    @FXML
    private TextField trainplanCCvik3Field;

    @FXML
    private TextField trainplanCCvik4Field;

    @FXML
    private TextField trainplanCDatumField;

    @FXML
    private TextField trainplanCNazovField;

    @FXML
    protected void recenzia() {

        try {
            Router.goTo(RouterEnum.CVICENECRECENZIAVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void profil() {

        try {
            Router.goTo(RouterEnum.CVICENECPROFILVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void miestnosti() {

        try {
            Router.goTo(RouterEnum.CVICENECMIESTNOSTIVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void odhlasenie() {

        try {
            Router.goTo(RouterEnum.LOGINVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    protected  void nastavHodnoty() {
        IndividualnyPlan ip = planCTabulka.getSelectionModel().getSelectedItem();
        if (ip != null) {
            Inicializator.nastavHodnotyCPlan(trainplanCDatumField,trainplanCNazovField,trainplanCCvik1Field,trainplanCCvik2Field,
                                             trainplanCCvik3Field,trainplanCCvik4Field,ip);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujCCvicenia(planCTabulka,planCDenStlpec,planCNazovStlpec);
    }
}
