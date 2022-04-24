package sk.stu.fiit.flexemvavaprojekt.controllers.cvicenec;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.models.SkupinovyPlan;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CvicenecMiestnostiController implements Initializable{


    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostiCMSlpecSport;

    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostiCMStlpecCas;

    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostiCMStlpecMmiestnost;

    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostiCMStlpecTrener;

    @FXML
    private TableView<SkupinovyPlan> miestnostiCMTabulka;

    @FXML
    private TextField miestnostiCDateTimeField;

    @FXML
    private TableView<SkupinovyPlan> miestnostiCTabulkaZoznamuTreningov;

    @FXML
    private TextField miestnostiCIzbaField;

    @FXML
    private TextField miestnostiCPopisField;

    @FXML
    private TextField miestnostiCSportField;

    @FXML
    private TextField miestnostiCTrenerField;

    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostiCStlpecMiestnost;

    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostiCStlpecDatumACas;

    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostiCStlpecSport;

    @FXML
    private TableColumn<SkupinovyPlan, String> miestnostiCStlpecTrener;


    @FXML
    protected void recenzia() {

        try {
            Router.goTo(RouterEnum.CVICENECRECENZIAVIEW);
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
    protected void plan() {

        try {
            Router.goTo(RouterEnum.CVICENECPLANVIEW);
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
    protected void vyberTrening() {
        SkupinovyPlan sp = miestnostiCTabulkaZoznamuTreningov.getSelectionModel().getSelectedItem();
        if (sp != null) {
            Inicializator.nastavHodnotyCMiestnost(miestnostiCIzbaField,miestnostiCSportField,miestnostiCDateTimeField,miestnostiCPopisField,miestnostiCTrenerField,sp);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Inicializator.inicializujSkupinoveTreningy(miestnostiCTabulkaZoznamuTreningov, miestnostiCStlpecMiestnost,
                                                    miestnostiCStlpecTrener, miestnostiCStlpecDatumACas,
                                                    miestnostiCStlpecSport);
        Inicializator.inicializujMojeSkupinoveTreningy(miestnostiCMTabulka, miestnostiCMStlpecMmiestnost,
                                                        miestnostiCMStlpecTrener, miestnostiCMStlpecCas, miestnostiCMSlpecSport);
    }




}
