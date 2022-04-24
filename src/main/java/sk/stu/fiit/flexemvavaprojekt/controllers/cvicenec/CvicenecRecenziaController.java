package sk.stu.fiit.flexemvavaprojekt.controllers.cvicenec;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sk.stu.fiit.flexemvavaprojekt.models.Cvicenec;
import sk.stu.fiit.flexemvavaprojekt.models.InputValidation;
import sk.stu.fiit.flexemvavaprojekt.models.Jazyk;
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
    private TableColumn recenziaCIzbaStlpec;

    @FXML
    private TableColumn recenziaCTrenerStlpec;

    @FXML
    private TableColumn recenziaCSportStlpec;

    @FXML
    private TableView<String> recenziaCTabulka;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        recenziaCIzbaField.setText("uz");
        recenziaCTrenerField.setText("by");
        recenziaCSportField.setText("fakt");
        recenziaCHviezdyField.setText("trebalo");
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
    protected void publish(){
        if(!validateStars() || !validateReview()){
            actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("invalidinput.key"));
            return;
        }
        actionLabel.setText(Jazyk.getInstance().prelozeneSlovo("reviewpublished.key"));
    }

}
