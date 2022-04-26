package sk.stu.fiit.flexemvavaprojekt.controllers.cvicenec;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sk.stu.fiit.flexemvavaprojekt.controllers.Inicializator;
import sk.stu.fiit.flexemvavaprojekt.db.DbConnector;
import sk.stu.fiit.flexemvavaprojekt.models.*;
import sk.stu.fiit.flexemvavaprojekt.router.Router;
import sk.stu.fiit.flexemvavaprojekt.router.RouterEnum;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CvicenecRecenziaController implements Initializable {

    @FXML
    private TextField recenziaCHviezdyField;
    @FXML
    private TextField recenziaCIzbaField;
    @FXML
    private TextField recenziaCSportField;
    @FXML
    private TextField recenziaCTrenerField;

    @FXML
    private TextArea recenziaCRecenziaArea;

    @FXML
    private Label actionLabel;

    @FXML
    private TableColumn<SkupinovyPlan,String> recenziaCIzbaStlpec;

    @FXML
    private TableColumn<SkupinovyPlan,String> recenziaCTrenerStlpec;

    @FXML
    private TableColumn<SkupinovyPlan,String> recenziaCSportStlpec;

    @FXML
    private TableView<SkupinovyPlan> recenziaCTabulka;

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
    protected void plan() {

        try {
            Router.goTo(RouterEnum.CVICENECPLANVIEW);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected  void nastavHodnoty(){
        SkupinovyPlan sp = recenziaCTabulka.getSelectionModel().getSelectedItem();
        if (sp != null) {
            Inicializator.nastavHodnotyCRecenzia(recenziaCIzbaField,recenziaCTrenerField,recenziaCSportField,sp);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Inicializator.inicializujAbsolvovaneSkupTreningy(recenziaCTabulka,recenziaCIzbaStlpec,recenziaCTrenerStlpec,
                                                         recenziaCSportStlpec);
    }

    @FXML
    protected boolean validateStars(){
        String stars = recenziaCHviezdyField.getText();
        if(!InputValidation.validateStars(stars) || !InputValidation.isSqlInjectionSafe(stars)){
            recenziaCHviezdyField.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            recenziaCHviezdyField.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected boolean validateReview(){
        String review = recenziaCRecenziaArea.getText();
        if(!InputValidation.validateReview(review) || !InputValidation.isSqlInjectionSafe(review)){
            recenziaCRecenziaArea.setStyle("-fx-text-box-border: red ; -fx-focus-color: red ;");
            return false;
        }
        else {
            recenziaCRecenziaArea.setStyle("-fx-border-width: 0px");
            return true;
        }
    }

    @FXML
    protected void publikovat(){
        if(!validateStars() || !validateReview()){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }
        Pouzivatel p = PrihlasenyPouzivatel.getInstance().getPouzivatel();
        SkupinovyPlan sp = recenziaCTabulka.getSelectionModel().getSelectedItem();
        Recenzia recenzia = new Recenzia(sp.getId(),sp.getSport(),
                Integer.valueOf(recenziaCHviezdyField.getText()),recenziaCRecenziaArea.getText(),
                p.getMeno(),p.getPriezvisko(),p.getId(),sp.getTrenerId());
        DbConnector.getInstance().createRecenzia(recenzia);
        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("reviewpublished.key"));
    }

}
